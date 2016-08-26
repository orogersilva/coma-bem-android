package com.orogersilva.comabem.data.source.remote;

import com.orogersilva.comabem.data.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by orogersilva on 8/11/2016.
 */

public interface PlaceEndpoint {

    // region METHODS

    @GET("places")
    Call<List<Place>> getPlaces();

    // endregion
}
