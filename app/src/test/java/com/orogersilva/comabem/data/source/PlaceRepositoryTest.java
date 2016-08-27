package com.orogersilva.comabem.data.source;

import android.test.suitebuilder.annotation.SmallTest;

import com.orogersilva.comabem.data.source.local.PlaceLocalDataSource;
import com.orogersilva.comabem.data.source.remote.PlaceRemoteDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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

        mPlaceRepository.getPlaces(mLoadPlacesCallback);

        verify(mPlaceLocalDataSource).getPlaces(any(PlaceDataSource.LoadPlacesCallback.class));
    }

    // endregion
}
