package com.food.identifier.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.ITutorialScreenView;
import com.food.identifier.mvp.presenter.fragments.TutorialScreenPresenter;
import com.food.identifier.mvp.view.activity.LoginActivity;
import com.food.identifier.mvp.view.activity.RegisterActivity;
import com.food.identifier.mvp.view.activity.SelectRoleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;
import static com.food.identifier.other.Constants.TUTORIAL_KEY;
import static com.food.identifier.other.Constants.TUTORIAL_POSITION;


/**
 * Created by taras on 9/24/2017.
 */

public class TutorialScreenFragment extends MvpFragment<TutorialScreenPresenter> implements ITutorialScreenView {

    @BindView(R.id.tv_package_desc) TextView mTvTitle;
    @BindView(R.id.bt_sign_in) Button mBtSignIn;
    @BindView(R.id.bt_sign_up) Button mBtSignUp;

    private Unbinder unbinder;

    public static TutorialScreenFragment getInstance(String info, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(TUTORIAL_KEY, info);
        bundle.putInt(TUTORIAL_POSITION, position);

        TutorialScreenFragment fragment = new TutorialScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @NonNull
    @Override
    public TutorialScreenPresenter createPresenter() {
        return new TutorialScreenPresenter(createFragmentComponent());
    }

    @NonNull
    @Override
    protected String getName() {
        return getClass().getName();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void bindTitle() {
        if (getArguments() != null) {
            String result = getArguments().getString(TUTORIAL_KEY);
            mPresenter.bindTitle(result);
        }
    }

    @Override
    public void setTitle(String text) {
        mTvTitle.setText(text);
    }

    @Override
    public void showHideButtons() {
        if (getArguments() != null) {
            int position = getArguments().getInt(TUTORIAL_POSITION);
            mPresenter.showHideButtons(getActivity(), position);
        }
    }

    @Override
    public void showHideLogin(int visibilityState) {
        mBtSignIn.setVisibility(visibilityState);
    }


    @Override
    public void showHideRegister(int visibilityState) {
        mBtSignUp.setVisibility(visibilityState);
    }

    @Override
    public void replaceToLogin() {
        mNavigator.replaceActivityAnimationFlag(getActivity(), LoginActivity.class, android.R.anim.fade_in, android.R.anim.fade_out, FLAG_ACTIVITY_NO_HISTORY);
    }

    @Override
    public void replaceToSelectRole() {
        mNavigator.replaceActivityAnimationFlag(getActivity(), SelectRoleActivity.class, android.R.anim.fade_in, android.R.anim.fade_out, FLAG_ACTIVITY_NO_HISTORY);
    }

    @OnClick({R.id.bt_sign_in, R.id.bt_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sign_in:
                mPresenter.replaceToLogin();
                break;
            case R.id.bt_sign_up:
                mPresenter.replaceToRegister();
                break;
        }
    }
}
