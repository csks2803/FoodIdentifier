package com.food.identifier.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IIdScannerView;
import com.food.identifier.mvp.presenter.activity.IdScannerPresenter;
import com.food.identifier.other.utility.CloudHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by taras on 11/23/2017.
 */

public class IdScannerActivity extends MvpActivity<IdScannerPresenter> implements IIdScannerView {
    private String lastText;

    @BindView(R.id.pr_loader) ProgressBar mProgressBar;

    @NonNull
    @Override
    public IdScannerPresenter createPresenter() {
        return new IdScannerPresenter(createActivityComponent());
    }

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_code_scanner);
        ButterKnife.bind(this);

//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.initiateScan();
        mPresenter.sendScannedResult("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null && !result.getContents().equals(lastText)) {
            lastText = result.getContents();
            if (CloudHelper.isOnline(this)) {
                mPresenter.sendScannedResult(result.getContents());
            } else {
                Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startLoading() {
        mProgressBar.setVisibility(VISIBLE);
    }

    @Override
    public void finishLoading() {
        mProgressBar.setVisibility(GONE);
    }

    @Override
    public void replaceToFoodProduct() {
        mNavigator.replaceActivityAnimation(this, ProductActivity.class, android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
