package com.example.mylibrary.view;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by wwb on 2018/5/21/021.
 * for:
 */

public class FragmentUtils {
    private FragmentManager fragmentManager;
    private int id;

    public FragmentUtils(FragmentManager fragmentManager, int id) {
        this.fragmentManager = fragmentManager;
        this.id = id;

    }

    public void setFragmentManager(Fragment fragmentManagers) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragmentManagers);
        fragmentTransaction.commit();
    }

    public void setListFragmet(Fragment fragment1) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();

        for (Fragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        if (!fragments.contains(fragment1)) {
            fragmentTransaction.add(id, fragment1);
        } else {
            fragmentTransaction.show(fragment1);
        }
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void setReplace(Fragment fragmentManagers) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragmentManagers);
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void removes(Fragment fragment1) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment1);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
