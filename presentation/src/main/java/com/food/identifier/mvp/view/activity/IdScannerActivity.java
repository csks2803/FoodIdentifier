package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IIdScannerView;
import com.food.identifier.mvp.presenter.activity.IdScannerPresenter;
import com.google.zxing.ResultPoint;
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

    @BindView(R.id.zxing_barcode_scanner) DecoratedBarcodeView mBarcodeScannerView;
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

        mBarcodeScannerView.decodeContinuous(callback);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mBarcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private BarcodeCallback callback = new BarcodeCallback() {

        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null || result.getText().equals(lastText)) {
                mPresenter.sendScannedResult(result.getText());
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

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
        mNavigator.replaceActivity(this, MainActivity.class);
    }
}
