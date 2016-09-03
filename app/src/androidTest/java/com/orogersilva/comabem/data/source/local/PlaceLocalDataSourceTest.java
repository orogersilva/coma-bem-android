package com.orogersilva.comabem.data.source.local;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.PlaceDataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    @Test
    public void getPlace_whenIdExists_onPlaceLoadedWasSucessful() {

        // ARRANGE

        final long ID = 1;
        final String NAME = "Le Grand Burguer";
        final double LAT = -30.020073;
        final double LNG = -51.202517;
        final double SCORE = 9.76;

        Place place = new Place(ID, NAME, LAT, LNG, SCORE);

        Realm realm = Realm.getInstance(sRealmConfiguration);

        realm.beginTransaction();

        try {

            realm.copyToRealm(place);

        } catch (IllegalArgumentException e) {

            throw e;

        } finally {

            realm.commitTransaction();
        }

        PlaceDataSource.GetPlaceCallback callback = new PlaceDataSource.GetPlaceCallback() {

            @Override
            public void onPlaceLoaded(Place place) {

                // ASSERT

                return;
            }

            @Override
            public void onDataNotAvailable() {

                // ASSERT

                fail();
            }
        };

        // ACT

        sPlaceDataSource.getPlace(ID, callback);
    }

    @Test
    public void getPlace_whenIdNotExists_onDataNotAvailableWasSucessful() {

        // ARRANGE

        final long ID = 1;

        PlaceDataSource.GetPlaceCallback callback = new PlaceDataSource.GetPlaceCallback() {

            @Override
            public void onPlaceLoaded(Place place) {

                // ASSERT

                fail();
            }

            @Override
            public void onDataNotAvailable() {

                // ASSERT

                return;
            }
        };

        // ACT

        sPlaceDataSource.getPlace(ID, callback);
    }

    @Test
    public void getPlaces_whenExistsPlaces_onPlacesLoadedWasSucessful() {

        // ARRANGE

        // Place 1
        final long ID_1 = 1;
        final String NAME_1 = "Le Grand Burguer";
        final double LAT_1 = -30.020073;
        final double LNG_1 = -51.202517;
        final double SCORE_1 = 9.76;

        // Place 2
        final long ID_2 = 2;
        final String NAME_2 = "Vermelho Grill";
        final double LAT_2 = -30.027611;
        final double LNG_2 = -51.158661;
        final double SCORE_2 = 9.91;

        Place place1 = new Place(ID_1, NAME_1, LAT_1, LNG_1, SCORE_1);
        Place place2 = new Place(ID_2, NAME_2, LAT_2, LNG_2, SCORE_2);

        Realm realm = Realm.getInstance(sRealmConfiguration);

        realm.beginTransaction();

        try {

            realm.copyToRealm(place1);
            realm.copyToRealm(place2);

        } catch (IllegalArgumentException e) {

            throw e;

        } finally {

            realm.commitTransaction();
        }

        PlaceDataSource.LoadPlacesCallback callback = new PlaceDataSource.LoadPlacesCallback() {

            @Override
            public void onPlacesLoaded(List<Place> places) {

                // ASSERT

                return;
            }

            @Override
            public void onDataNotAvaiable() {

                // ASSERT

                fail();
            }
        };

        // ACT

        sPlaceDataSource.getPlaces(callback);
    }

    @Test
    public void getPlaces_whenNotExistsPlaces_onDataNotAvaiableWasSucessful() {

        // ARRANGE

        PlaceDataSource.LoadPlacesCallback callback = new PlaceDataSource.LoadPlacesCallback() {

            @Override
            public void onPlacesLoaded(List<Place> places) {

                // ASSERT

                fail();
            }

            @Override
            public void onDataNotAvaiable() {

                // ASSERT

                return;
            }
        };

        // ACT

        sPlaceDataSource.getPlaces(callback);
    }

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

    @Test
    public void deleteAllPlaces_whenExistsPlaces_thenAfterDeletionThereIsNoPlaces() {

        // ARRANGE

        final long ID_1 = 1;
        final String NAME_1 = "Le Grand Burguer";
        final double LAT_1 = -30.020073;
        final double LNG_1 = -51.202517;
        final double SCORE_1 = 9.76;

        // Place 2
        final long ID_2 = 2;
        final String NAME_2 = "Vermelho Grill";
        final double LAT_2 = -30.027611;
        final double LNG_2 = -51.158661;
        final double SCORE_2 = 9.91;

        Place place1 = new Place(ID_1, NAME_1, LAT_1, LNG_1, SCORE_1);
        Place place2 = new Place(ID_2, NAME_2, LAT_2, LNG_2, SCORE_2);

        Realm realm = Realm.getInstance(sRealmConfiguration);

        realm.beginTransaction();

        try {

            realm.copyToRealm(place1);
            realm.copyToRealm(place2);

        } catch (IllegalArgumentException e) {

            throw e;

        } finally {

            realm.commitTransaction();
        }

        // ACT

        sPlaceDataSource.deleteAllPlaces();

        // ASSERT

        realm.where(Place.class);

        assertTrue(realm.isEmpty());
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
