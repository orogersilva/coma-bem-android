package com.orogersilva.comabem.places;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orogersilva.comabem.R;
import com.orogersilva.comabem.data.source.PlaceRepository;
import com.orogersilva.comabem.data.source.local.PlaceLocalDataSource;
import com.orogersilva.comabem.data.source.remote.PlaceRemoteDataSource;
import com.orogersilva.comabem.places.view.PlacesFragment;
import com.orogersilva.comabem.util.ActivityUtils;

/**
 * Created by orogersilva on 8/9/2016.
 */

public class PlacesActivity extends AppCompatActivity {

    // region FIELDS

    private static final String TAG  = "PlacesActivity";

    private PlacesFragment mPlacesFragment;
    private PlacesPresenter mPlacesPresenter;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        if (mPlacesFragment == null) {

            mPlacesFragment = PlacesFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mPlacesFragment, R.id.contentFrame);
        }

        mPlacesPresenter = new PlacesPresenter(PlaceRepository.getInstance(
                PlaceLocalDataSource.getInstance(this), PlaceRemoteDataSource.getInstance()),
                mPlacesFragment);
    }

    // endregion
}
