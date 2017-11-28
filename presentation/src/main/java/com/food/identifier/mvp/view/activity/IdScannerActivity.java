package com.food.identifier.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.ProgressBar;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IIdScannerView;
import com.food.identifier.mvp.presenter.activity.IdScannerPresenter;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

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
            mPresenter.sendScannedResult(result.getContents());
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
        mNavigator.replaceActivityAnimation(this, MainActivity.class, android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
