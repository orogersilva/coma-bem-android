package com.orogersilva.comabem.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.comabem.data.source.local.PlaceLocalDataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by orogersilva on 8/8/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlaceLocalDataSourceTest {

    // region FIELDS

    private static RealmConfiguration sRealmConfiguration;
    private static PlaceLocalDataSource sPlaceDataSource;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        sPlaceDataSource = PlaceLocalDataSource.getInstance(
                InstrumentationRegistry.getTargetContext()
        );
    }

    @Before
    public void setup() {
    }

    // endregion

    // region TEST METHODS

    @Test(expected = IllegalArgumentException.class)
    public void savePlace_whenPlaceIsNull_throwsIllegalArgumentException() {

        // ACT / ASSERT

        sPlaceDataSource.savePlace(null);
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void cleanUp() {

        sPlaceDataSource.deleteAllPlaces();
    }

    @AfterClass
    public static void teardownClass() {

        sPlaceDataSource = null;
    }

    // endregion
}
