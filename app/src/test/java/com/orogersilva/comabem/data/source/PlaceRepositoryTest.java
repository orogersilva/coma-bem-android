package com.orogersilva.comabem.data.source;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.local.PlaceLocalDataSource;
import com.orogersilva.comabem.data.source.remote.PlaceRemoteDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by orogersilva on 8/27/2016.
 */
@SmallTest
public class PlaceRepositoryTest {

    // region FIELDS

    @Mock
    private PlaceLocalDataSource mPlaceLocalDataSource;

    @Mock
    private PlaceRemoteDataSource mPlaceRemoteDataSource;

    @Mock
    private PlaceLocalDataSource.LoadPlacesCallback mLoadPlacesCallback;

    @Captor
    private ArgumentCaptor<PlaceLocalDataSource.LoadPlacesCallback> mPlacesCallbackCaptor;

    private PlaceRepository mPlaceRepository;

    // endregion

    // region SETUP METHODS

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mPlaceRepository = PlaceRepository.getInstance(
                mPlaceLocalDataSource, mPlaceRemoteDataSource);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void getPlaces_checkRequestsAllPlacesFromLocalDataSource() {

        // ACT

        mPlaceRepository.getPlaces(mLoadPlacesCallback);

        // ASSERT

        verify(mPlaceLocalDataSource).getPlaces(any(PlaceDataSource.LoadPlacesCallback.class));
    }

    @Test
    public void getPlaces_checkRequestsCacheAfterFirstApiCall() {

        // ACT

        List<Place> places = Arrays.asList(
                new Place(1, "Le Grand Burguer", -30.020073, -51.202517, 9.76),
                new Place(2, "Vermelho Grill", -30.027611, -51.158661, 9.91),
                new Place(3, "Na Brasa", -30.022871, -51.211777, 9.82)
        );

        mPlaceRepository.getPlaces(mLoadPlacesCallback);

        // ASSERT

        verify(mPlaceLocalDataSource).getPlaces(mPlacesCallbackCaptor.capture());

        mPlacesCallbackCaptor.getValue().onDataNotAvaiable();

        verify(mPlaceRemoteDataSource).getPlaces(mPlacesCallbackCaptor.capture());

        mPlacesCallbackCaptor.getValue().onPlacesLoaded(places);

        mPlaceRepository.getPlaces(mLoadPlacesCallback);

        verify(mPlaceLocalDataSource).getPlaces(any(PlaceDataSource.LoadPlacesCallback.class));
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void cleanUp() {

        PlaceRepository.destroyInstance();
    }

    // endregion
}
