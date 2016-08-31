package com.orogersilva.comabem.data.source.local;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.local.PlaceLocalDataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by orogersilva on 8/8/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlaceLocalDataSourceTest {

    // region FIELDS

    private static Context sContext;

    private static RealmConfiguration sRealmConfiguration;

    private static PlaceLocalDataSource sPlaceDataSource;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        final String DB_NAME = "comabem.sqlite";

        sContext = InstrumentationRegistry.getTargetContext();

        sRealmConfiguration = new RealmConfiguration.Builder(sContext)
                .name(DB_NAME)
                .build();

        sPlaceDataSource = PlaceLocalDataSource.getInstance(sRealmConfiguration);
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

    @Test
    public void savePlace_whenPlaceIsValid_operationWasSuccessful() {

        // ARRANGE

        final long ID = 1;
        final String NAME = "Le Grand Burguer";
        final double LAT = -30.020073;
        final double LNG = -51.202517;
        final double SCORE = 9.76;

        Place expectedPlace = new Place(ID, NAME, LAT, LNG, SCORE);

        // ACT

        sPlaceDataSource.savePlace(expectedPlace);

        // ASSERT

        Realm realm = Realm.getInstance(sRealmConfiguration);

        Place place = realm.where(Place.class)
                .equalTo("id", ID)
                .findFirst();

        assertEquals(expectedPlace, place);
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
