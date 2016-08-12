package com.orogersilva.comabem.places;

import android.support.v4.app.Fragment;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.places.PlacesContract.Presenter;

import java.util.List;

/**
 * Created by orogersilva on 8/11/2016.
 */

public class PlacesFragment extends Fragment implements PlacesContract.View {

    // region FIELDS

    private Presenter mPlacesPresenter;

    // endregion

    // region CONSTRUCTORS

    public PlacesFragment() {}

    // endregion

    // region STATIC METHODS

    public static PlacesFragment newInstance() {

        return new PlacesFragment();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public void onResume() {

        super.onResume();

        mPlacesPresenter.start();
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void showPlaces(List<Place> places) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showLoadingPlacesError() {

    }

    @Override
    public boolean isActive() {

        return false;
    }

    @Override
    public void setPresenter(Presenter presenter) {

        mPlacesPresenter = presenter;
    }

    // endregion
}