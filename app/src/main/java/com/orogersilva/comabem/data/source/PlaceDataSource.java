package com.orogersilva.comabem.data.source;

import android.support.annotation.NonNull;

import com.orogersilva.comabem.data.Place;

/**
 * Created by orogersilva on 8/3/2016.
 */

public interface PlaceDataSource {

    // region FIELDS

    String DB_NAME = "comabem.realm";

    // endregion

    // region CALLBACKS

    interface GetPlaceCallback {

        void onPlaceLoaded(Place place);
        void onDataNotAvailable();
    }

    // endregion

    // region METHODS

    void getPlace(long id, @NonNull GetPlaceCallback callback);

    void savePlace(@NonNull Place place);

    void deleteAllPlaces();

    // endregion
}
