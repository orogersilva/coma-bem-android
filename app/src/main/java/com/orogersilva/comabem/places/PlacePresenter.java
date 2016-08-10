package com.orogersilva.comabem.places;

import android.support.annotation.NonNull;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.PlaceDataSource;
import com.orogersilva.comabem.data.source.PlaceRepository;

import java.util.List;

/**
 * Created by orogersilva on 8/9/2016.
 */

public class PlacePresenter implements PlacesContract.Presenter {

    // region FIELDS

    private final PlaceRepository mPlaceRepository;
    private final PlacesContract.View mPlacesView;

    // endregion

    // region CONSTRUCTORS

    public PlacePresenter(@NonNull PlaceRepository placeRepository, @NonNull PlacesContract.View placesView) {

        mPlaceRepository = placeRepository;
        mPlacesView = placesView;

        mPlacesView.setPresenter(this);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void loadPlaces(final boolean showLoadingUI) {

        if (showLoadingUI) {

            mPlacesView.setLoadingIndicator(true);
        }

        mPlaceRepository.getPlaces(new PlaceDataSource.LoadPlacesCallback() {

            @Override
            public void onPlacesLoaded(List<Place> places) {

                if (!mPlacesView.isActive()) return;

                if (showLoadingUI) {

                    mPlacesView.setLoadingIndicator(false);
                }

                processPlaces(places);
            }

            @Override
            public void onDataNotAvaiable() {

                mPlacesView.showLoadingPlacesError();
            }
        });
    }

    @Override
    public void start() {

        loadPlaces(true);
    }

    // endregion

    // region UTLLITY METHODS

    private void processPlaces(List<Place> places) {

        mPlacesView.showPlaces(places);
    }

    // endregion
}
