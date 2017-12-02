package com.food.identifier.mvp.view.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IIdScannerView;
import com.food.identifier.mvp.presenter.activity.IdScannerPresenter;
import com.food.identifier.other.utility.CloudHelper;
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
    @BindView(R.id.zxingBarcodeScanner)
    DecoratedBarcodeView mBarcodeScannerView;

    @NonNull
    @Override
    public IdScannerPresenter createPresenter() {
        return new IdScannerPresenter(createActivityComponent());
    }

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_code_scanner);
        ButterKnife.bind(this);

        mBarcodeScannerView.decodeContinuous(callback);
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null || result.getText().equals(lastText)) {
                // Prevent duplicate scans
                return;
            }
            if (CloudHelper.isOnline(IdScannerActivity.this)) {
                mPresenter.sendScannedResult(result.getText());
            } else {
                Toast.makeText(IdScannerActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mBarcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBarcodeScannerView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBarcodeScannerView.pause();
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
