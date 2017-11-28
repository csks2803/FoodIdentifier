package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.ITutorialScreenView;
import com.food.identifier.mvp.presenter.fragments.TutorialScreenPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.food.identifier.other.Constants.TUTORIAL_KEY;


/**
 * Created by taras on 9/24/2017.
 */

public class TutorialScreenFragment extends MvpFragment<TutorialScreenPresenter> implements ITutorialScreenView {

    @BindView(R.id.tv_package_desc) TextView mTvDescription;
    private Unbinder unbinder;

    public static TutorialScreenFragment getInstance(String info) {
        Bundle bundle = new Bundle();
        bundle.putString(TUTORIAL_KEY, info);

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
        mPresenter.bindTitle(getArguments());
    }

    @Override
    public void setTitle(String text) {
        mTvDescription.setText(text);
    }
}
