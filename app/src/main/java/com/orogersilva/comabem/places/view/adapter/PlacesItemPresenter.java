package com.orogersilva.comabem.places.view.adapter;

import com.orogersilva.comabem.data.Place;

/**
 * Created by orogersilva on 8/29/2016.
 */

public class PlacesItemPresenter {

    // region PUBLIC METHODS

    public void presentListItem(PlacesAdapter.ItemViewHolder itemViewHolder, Place place) {

        itemViewHolder.setItem(place);
    }

    // endregion
}
