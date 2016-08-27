package com.orogersilva.comabem.data.source;

import android.support.annotation.NonNull;

import com.orogersilva.comabem.data.Place;

import java.util.List;

/**
 * Created by orogersilva on 8/3/2016.
 */

public interface PlaceDataSource {

    // region CALLBACKS

    interface GetPlaceCallback {

        void onPlaceLoaded(Place place);
        void onDataNotAvailable();
    }

    interface LoadPlacesCallback {

        void onPlacesLoaded(List<Place> places);
        void onDataNotAvaiable();
    }

    // endregion

    // region METHODS

    void getPlace(long id, @NonNull GetPlaceCallback callback);

    void getPlaces(LoadPlacesCallback callback);

    void savePlace(@NonNull Place place);

    void deleteAllPlaces();

    // endregion
}
