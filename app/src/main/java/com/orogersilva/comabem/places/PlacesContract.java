package com.orogersilva.comabem.places;

import com.orogersilva.comabem.BasePresenter;
import com.orogersilva.comabem.BaseView;
import com.orogersilva.comabem.data.Place;

import java.util.List;

/**
 * Created by orogersilva on 8/9/2016.
 */

public interface PlacesContract {

    // region INTERFACES

    interface View extends BaseView<Presenter> {

        // region METHODS

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showLoadingPlacesError();

        void showPlaceDetails();

        void showPlaces(List<Place> places);

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS

        void loadPlaceDetails();

        void loadPlaces(boolean forceUpdate, boolean showLoadingUI);

        // endregion
    }

    // endregion
}
