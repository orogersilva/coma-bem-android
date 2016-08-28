package com.orogersilva.comabem.data.source.remote;

import android.support.annotation.NonNull;

import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.data.source.PlaceDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by orogersilva on 8/11/2016.
 */

public class PlaceRemoteDataSource implements PlaceDataSource {

    // region FIELDS

    private static PlaceRemoteDataSource INSTANCE;

    // TODO: 8/11/2016 Specify the base url.
    private static final String BASE_URL = "";

    private static Retrofit sRetrofit;

    // endregion

    // region CONSTRUCTORS

    private PlaceRemoteDataSource() {}

    // endregion

    // region STATIC METHODS

    public static PlaceRemoteDataSource getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new PlaceRemoteDataSource();

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getPlace(long id, @NonNull GetPlaceCallback callback) {

    }

    @Override
    public void getPlaces(final LoadPlacesCallback callback) {

        PlaceEndpoint service = sRetrofit.create(PlaceEndpoint.class);

        Call<List<Place>> call = service.getPlaces();

        call.enqueue(new Callback<List<Place>>() {

            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {

                final int OK_STATUS = 200;

                int statusCode = response.code();

                if (statusCode == OK_STATUS) {
                    callback.onPlacesLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {

                callback.onDataNotAvaiable();
            }
        });
    }

    @Override
    public void savePlace(@NonNull Place place) {

    }

    @Override
    public void deleteAllPlaces() {

    }

    @Override
    public void refreshPlaces() {

        // TODO: 8/27/2016 TO IMPLEMENT
    }

    // endregion
}
