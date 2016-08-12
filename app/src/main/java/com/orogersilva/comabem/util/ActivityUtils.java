package com.orogersilva.comabem.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by orogersilva on 8/11/2016.
 */

public class ActivityUtils {

    // region STATIC METHODS

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

        fragmentManager.beginTransaction()
                .add(frameId, fragment)
                .commit();
    }

    // endregion
}
