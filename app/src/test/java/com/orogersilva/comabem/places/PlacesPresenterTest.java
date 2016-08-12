package com.orogersilva.comabem.places;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.PlaceDataSource.LoadPlacesCallback;
import com.orogersilva.comabem.data.source.PlaceRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by orogersilva on 8/9/2016.
 */
@SmallTest
public class PlacesPresenterTest {

    // region FIELDS

    private List<Place> mPlaces;

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

        mPlaces = Arrays.asList(
                new Place(1, "Le Grand Burguer", -30.020073, -51.202517, 9.76),
                new Place(2, "Vermelho Grill", -30.027611, -51.158661, 9.91),
                new Place(3, "Na Brasa", -30.022871, -51.211777, 9.82)
        );
    }

    // endregion

    // region TEST METHODS

    @Test
    public void loadPlaces_whenThereAreNoPlaces_showEmptyPlacesList() {

        // ARRANGE

        when(mPlacesView.isActive()).thenReturn(true);

        List<Place> expectedEmptyPlacesList = new ArrayList<>();

        // ACT

        mPlacePresenter.loadPlaces(true);

        // ASSERT

        verify(mPlacesView).setLoadingIndicator(true);

        verify(mPlaceRepository).getPlaces(mLoadPlacesCallbackCaptor.capture());
        mLoadPlacesCallbackCaptor.getValue().onPlacesLoaded(expectedEmptyPlacesList);

        verify(mPlacesView).setLoadingIndicator(false);

        ArgumentCaptor<List> showPlacesArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(mPlacesView).showPlaces(showPlacesArgumentCaptor.capture());

        Assert.assertTrue(showPlacesArgumentCaptor.getValue().size() == expectedEmptyPlacesList.size());
    }

    // endregion
}