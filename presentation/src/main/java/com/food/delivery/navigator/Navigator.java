package com.food.delivery.navigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.food.delivery.mvp.view.activity.BaseActivity;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Class which provide navigation through the whole application
 */
@Singleton
public class Navigator {
    public Fragment getCurrentFragment(final Activity activity, final int fragment_hold) {
        return ((BaseActivity) activity).getSupportFragmentManager().findFragmentById(fragment_hold);
    }

    @Inject
    public Navigator() {
    }

    /**
     * Replace in view current fragment by another.
     *
     * @param classInfo class info of new fragment
     * @param activity  view where is fragment will be replaced
     */
    public void replace(final Activity activity, final Class<? extends Fragment> classInfo) {
        replace(activity, classInfo, null);
    }

    /**
     * Replace in view current fragment by another.
     *
     * @param classInfo class info of new fragment.
     * @param args      arguments for newly created fragment.
     * @param activity  view where is fragment will be replaced
     */
    public void replace(final Activity activity, final Class<? extends Fragment> classInfo, final Bundle args) {
        replace(activity, classInfo, 0, args, false);
    }

    /**
     * Replace in view current fragment by another.
     *
     * @param classInfo class info of new fragment.
     * @param useStack  true - add to "back Stack", otherwise false.
     * @param activity  view where is fragment will be replaced
     */
    public void replace(final Activity activity, final Class<? extends Fragment> classInfo, final boolean useStack) {
        replace(activity, classInfo, 0, null, useStack);
    }

    /**
     * Replace in view current fragment by another.
     *
     * @param classInfo     class info of new fragment.
     * @param args          arguments for newly created fragment.
     * @param useStack      true - add to "back Stack", otherwise false.
     * @param fragmentPlace - current fragment layout id
     * @param activity      view where is fragment will be replaced
     */
    public void replace(final Activity activity, Class<? extends Fragment> classInfo, int fragmentPlace, Bundle args, boolean useStack) {
        if (null != classInfo) {
            final Fragment fragment = Fragment.instantiate(activity, classInfo.getName());

            replace(activity, fragment, args, useStack, fragmentPlace);
        }
    }

    /**
     * Replace in view current fragment by another.
     *
     * @param fragment the object that extends the class Fragment
     * @param activity view where is fragment will be replaced
     */
    public void replace(final Activity activity, final Fragment fragment, int fragmentPlace) {
        replace(activity, fragment, null, fragmentPlace);

    }

    /**
     * Replace in view current fragment by another.
     *
     * @param fragment      the object that extends the class Fragment.
     * @param args          arguments for fragment.
     * @param fragmentPlace - current fragment layout id
     * @param activity      view where is fragment will be replaced
     */
    public void replace(final Activity activity, final Fragment fragment, final Bundle args, int fragmentPlace) {
        replace(activity, fragment, args, false, fragmentPlace);
    }

    /**
     * Replace in view current fragment by another.
     *
     * @param fragment      the object that extends the class Fragment.
     * @param args          arguments for fragment.
     * @param useStack      true - add to "back Stack", otherwise false.
     * @param fragmentPlace - current fragment layout id
     * @param activity      view where is fragment will be replaced
     */
    public void replace(final Activity activity, final Fragment fragment, final Bundle args, final boolean useStack, int fragmentPlace) {
        if (null != fragment && !activity.isFinishing()) {
            final String className = fragment.getClass().getCanonicalName();

            // replace fragment now
            final FragmentManager fm = ((BaseActivity) activity).getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();
            // specify replace parameters for fragment
            if (null != args)
                fragment.setArguments(args);
            ft.replace(fragmentPlace, fragment, className);

            if (useStack)
                ft.addToBackStack(className);

            ft.commitAllowingStateLoss();
        }
    }

    public void replaceActivity(Activity activity, Class className) {
        Intent intent = new Intent(activity, className);
        activity.startActivity(intent);
    }

    public void replaceActivityFlag(Activity activity, Class className, int flags) {
        Intent intent = new Intent(activity, className);
        intent.addFlags(flags);
        activity.startActivity(intent);
    }

    public void replaceActivity(Activity activity, Class className, Bundle bundle) {
        Intent intent = new Intent(activity, className);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public void replaceActivitForResult(Activity activity, Class className, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, className);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public void replaceActivityForResult(Activity activity, Class className, int requestCode) {
        Intent intent = new Intent(activity, className);
        activity.startActivityForResult(intent, requestCode);
    }

    public void replaceActivityForResult(Fragment fragment, Class className, Bundle bundle, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), className);
        intent.putExtras(bundle);
        fragment.startActivityForResult(intent, requestCode);
    }

    public void replaceActivityForResult(Fragment fragment, Class className, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), className);
        fragment.startActivityForResult(intent, requestCode);
    }

    public boolean checkEmptyBackFragmentStack(Activity activity) {
        int backStackEntryCount = activity.getFragmentManager().getBackStackEntryCount();
        return backStackEntryCount == 0;
    }

    public void replaceActivityBundleFlag(Activity activity, Class className, Bundle bundle, int flag) {
        Intent intent = new Intent(activity, className);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.addFlags(flag);
        activity.startActivity(intent);
    }

}
