package com.orogersilva.comabem.places;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.PlaceDataSource.LoadPlacesCallback;
import com.orogersilva.comabem.data.source.PlaceRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by orogersilva on 8/9/2016.
 */
@SmallTest
public class PlacesPresenterTest {

    // region FIELDS

    @Mock
    private PlaceRepository mPlaceRepository;

    @Mock
    private PlacesContract.View mPlacesView;

    @Captor
    private ArgumentCaptor<LoadPlacesCallback> mLoadPlacesCallbackCaptor;

    private PlacesPresenter mPlacePresenter;

    // endregion

    // region SETUP METHODS

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mPlacePresenter = new PlacesPresenter(mPlaceRepository, mPlacesView);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void loadPlaces_whenThereAreNoPlaces_showEmptyPlacesList() {

        // ARRANGE

        when(mPlacesView.isActive()).thenReturn(true);

        List<Place> emptyPlacesList = new ArrayList<>();

        // ACT

        mPlacePresenter.loadPlaces(false, true);

        // ASSERT

        verify(mPlacesView).setLoadingIndicator(true);

        verify(mPlaceRepository).getPlaces(mLoadPlacesCallbackCaptor.capture());
        mLoadPlacesCallbackCaptor.getValue().onPlacesLoaded(emptyPlacesList);

        verify(mPlacesView).setLoadingIndicator(false);

        ArgumentCaptor<List> showPlacesArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(mPlacesView).showPlaces(showPlacesArgumentCaptor.capture());

        assertTrue(showPlacesArgumentCaptor.getValue().size() == emptyPlacesList.size());
    }

    @Test
    public void loadPlaces_whenThereArePlaces_showPlacesList() {

        // ARRANGE

        List<Place> places = Arrays.asList(
                new Place(1, "Le Grand Burguer", -30.020073, -51.202517, 9.76),
                new Place(2, "Vermelho Grill", -30.027611, -51.158661, 9.91),
                new Place(3, "Na Brasa", -30.022871, -51.211777, 9.82)
        );

        when(mPlacesView.isActive()).thenReturn(true);

        // ACT

        mPlacePresenter.loadPlaces(false, true);

        // ASSERT

        verify(mPlacesView).setLoadingIndicator(true);

        verify(mPlaceRepository).getPlaces(mLoadPlacesCallbackCaptor.capture());
        mLoadPlacesCallbackCaptor.getValue().onPlacesLoaded(places);

        verify(mPlacesView).setLoadingIndicator(false);

        ArgumentCaptor<List> showPlacesArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(mPlacesView).showPlaces(showPlacesArgumentCaptor.capture());

        assertTrue(showPlacesArgumentCaptor.getValue().size() == places.size());
    }

    // endregion
}
