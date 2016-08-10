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

        void showPlaces(List<Place> places);

        void setLoadingIndicator(boolean active);

        void showLoadingPlacesError();

        boolean isActive();

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS

        void loadPlaces(boolean showLoadingUI);

        // endregion
    }

    // endregion
}
