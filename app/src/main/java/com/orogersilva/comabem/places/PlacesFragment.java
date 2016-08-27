package com.orogersilva.comabem.places;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.places.PlacesContract.Presenter;

import java.util.List;

/**
 * Created by orogersilva on 8/11/2016.
 */

public class PlacesFragment extends Fragment implements PlacesContract.View {

    // region FIELDS

    private static final String TAG  = "PlacesFragment";

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

        // TODO: 8/25/2016 TO IMPLEMENT
    }

    @Override
    public void setLoadingIndicator(boolean active) {

        // TODO: 8/25/2016 TO IMPLEMENT
    }

    @Override
    public void showLoadingPlacesError() {

        // TODO: 8/25/2016 TO IMPLEMENT
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
