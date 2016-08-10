package com.orogersilva.comabem.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.PlaceDataSource;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by orogersilva on 8/3/2016.
 */

public class PlaceLocalDataSource implements PlaceDataSource {

    // region FIELDS

    private static PlaceLocalDataSource INSTANCE = null;

    private static Realm mRealm = null;

    // endregion

    // region CONSTRUCTORS

    private PlaceLocalDataSource() {
    }

    // endregion

    // region STATIC METHODS

    public static PlaceLocalDataSource getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = new PlaceLocalDataSource();

            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                    .name(DB_NAME)
                    .build();

            mRealm = Realm.getInstance(realmConfiguration);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getPlace(long id, @NonNull GetPlaceCallback callback) {

        Place place = mRealm.where(Place.class)
                .equalTo("id", id)
                .findFirst();

        if (place != null) {
            callback.onPlaceLoaded(place);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getPlaces(LoadPlacesCallback callback) {

        List<Place> places = mRealm.where(Place.class)
                .findAll();

        if (places != null) {
            callback.onPlacesLoaded(places);
        } else {
            callback.onDataNotAvaiable();
        }
    }

    @Override
    public void savePlace(@NonNull Place place) {

        mRealm.beginTransaction();

        try {

            mRealm.copyToRealm(place);

        } catch (IllegalArgumentException e) {

            throw e;

        } finally {

            mRealm.commitTransaction();
        }
    }

    @Override
    public void deleteAllPlaces() {

        mRealm.beginTransaction();
        mRealm.deleteAll();
        mRealm.commitTransaction();
    }

    // endregion
}
