package com.orogersilva.comabem.places.view.adapter;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.comabem.data.Place;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by orogersilva on 8/29/2016.
 */
@SmallTest
public class PlacesItemPresenterTest {

    // region FIELDS

    @Mock
    private PlacesAdapter.ItemViewHolder mItemView;

    private PlacesItemPresenter mPlaceItemPresenter;

    // endregion

    // region SETUP METHODS

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mPlaceItemPresenter = new PlacesItemPresenter();
    }

    // endregion

    // region TEST METHODS

    @Test
    public void presentListItem_whenThereIsPlace_showPlaceItem() {

        // ARRANGE

        final long ID = 1;
        final String NAME = "Le Grand Burguer";
        final double LAT = -30.020073;
        final double LNG = -51.202517;
        final double SCORE = 9.76;

        Place place = new Place(ID, NAME, LAT, LNG, SCORE);

        // ACT

        mPlaceItemPresenter.presentListItem(mItemView, place);

        // ASSERT

        verify(mItemView).setItem(place);
    }

    // endregion
}
