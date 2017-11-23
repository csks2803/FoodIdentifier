package com.food.delivery.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.food.delivery.R;
import com.food.delivery.mvp.interfaces.activity.IIDScannerView;
import com.food.delivery.mvp.presenter.activity.IdScannerPresenter;
import com.food.delivery.mvp.view.activity.MvpActivity;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taras on 11/23/2017.
 */

public class IDScannerActivity extends MvpActivity<IdScannerPresenter> implements IIDScannerView {
    private String lastText;

    @BindView(R.id.zxingBarcodeScanner) DecoratedBarcodeView mBarcodeScannerView;

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
                // Prevent duplicate scans
                return;
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };
}
