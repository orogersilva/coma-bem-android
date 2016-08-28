package com.orogersilva.comabem.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orogersilva.comabem.data.Place;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by orogersilva on 8/3/2016.
 */

public class PlaceRepository implements PlaceDataSource {

    // region FIELDS

    private static PlaceRepository INSTANCE = null;

    private final PlaceDataSource mPlaceLocalDataSource;
    private final PlaceDataSource mPlaceRemoteDataSource;

    private boolean mCacheIsDirty = false;

    Map<Long, Place> mCachedPlaces = null;

    // endregion

    // region CONSTRUCTORS

    private PlaceRepository(@NonNull PlaceDataSource placeLocalDataSource,
                            @NonNull PlaceDataSource placeRemoteDataSource) {

        mPlaceLocalDataSource = placeLocalDataSource;
        mPlaceRemoteDataSource = placeRemoteDataSource;
    }

    // endregion

    // region STATIC METHODS

    /**
     * Returns the single instance of this class, creating it if necessary.
     * @param placeLocalDataSource the device storage data source.
     * @param placeRemoteDataSource the backend data source.
     * @return the {@link PlaceRepository} instance.
     */
    public static PlaceRepository getInstance(@NonNull PlaceDataSource placeLocalDataSource,
                                              @NonNull PlaceDataSource placeRemoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new PlaceRepository(placeLocalDataSource, placeRemoteDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {

        INSTANCE = null;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getPlace(final long id, @NonNull final GetPlaceCallback callback) {

        Place cachedPlace = getPlaceById(id);

        if (cachedPlace != null) {

            callback.onPlaceLoaded(cachedPlace);
            return;
        }

        mPlaceLocalDataSource.getPlace(id, new GetPlaceCallback() {

            @Override
            public void onPlaceLoaded(Place place) {

                callback.onPlaceLoaded(place);
            }

            @Override
            public void onDataNotAvailable() {

                mPlaceRemoteDataSource.getPlace(id, new GetPlaceCallback() {

                    @Override
                    public void onPlaceLoaded(Place place) {

                        callback.onPlaceLoaded(place);
                    }

                    @Override
                    public void onDataNotAvailable() {

                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void getPlaces(final LoadPlacesCallback callback) {

        if (mCachedPlaces != null && !mCacheIsDirty) {

            callback.onPlacesLoaded(new ArrayList<>(mCachedPlaces.values()));
            return;
        }

        if (mCacheIsDirty) {

            getPlacesFromRemoteDataSource(callback);

        } else {

            mPlaceLocalDataSource.getPlaces(new LoadPlacesCallback() {

                @Override
                public void onPlacesLoaded(List<Place> places) {

                    refreshCache(places);
                    callback.onPlacesLoaded(new ArrayList<>(mCachedPlaces.values()));
                }

                @Override
                public void onDataNotAvaiable() {

                    getPlacesFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void savePlace(@NonNull Place place) {

        mPlaceLocalDataSource.savePlace(place);
        mPlaceRemoteDataSource.savePlace(place);

        if (mCachedPlaces == null) {
            mCachedPlaces = new LinkedHashMap<>();
        }

        mCachedPlaces.put(place.getId(), place);
    }

    @Override
    public void deleteAllPlaces() {

        mPlaceLocalDataSource.deleteAllPlaces();
        mPlaceRemoteDataSource.deleteAllPlaces();

        if (mCachedPlaces != null) {
            mCachedPlaces.clear();
        }
    }

    @Override
    public void refreshPlaces() {

        mCacheIsDirty = true;
    }

    // endregion

    // region UTILITY METHODS

    @Nullable
    private Place getPlaceById(long id) {

        if (mCachedPlaces == null || mCachedPlaces.isEmpty()) {
            return null;
        } else {
            return mCachedPlaces.get(id);
        }
    }

    private void refreshCache(List<Place> places) {

        if (mCachedPlaces == null) {
            mCachedPlaces = new LinkedHashMap<>();
        }

        mCachedPlaces.clear();

        for (Place place : places) {

            mCachedPlaces.put(place.getId(), place);
        }
    }

    private void refreshLocalDataSource(List<Place> places) {

        mPlaceLocalDataSource.deleteAllPlaces();

        for (Place place : places) {
            mPlaceLocalDataSource.savePlace(place);
        }
    }

    private void getPlacesFromRemoteDataSource(@NonNull final LoadPlacesCallback callback) {

        mPlaceRemoteDataSource.getPlaces(new LoadPlacesCallback() {

            @Override
            public void onPlacesLoaded(List<Place> places) {

                refreshCache(places);
                refreshLocalDataSource(places);

                callback.onPlacesLoaded(places);
            }

            @Override
            public void onDataNotAvaiable() {

                callback.onDataNotAvaiable();
            }
        });
    }

    // endregion
}
