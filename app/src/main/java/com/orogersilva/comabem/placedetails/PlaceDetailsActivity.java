package com.orogersilva.comabem.placedetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orogersilva.comabem.R;
import com.orogersilva.comabem.placedetails.view.PlaceDetailsFragment;
import com.orogersilva.comabem.util.ActivityUtils;

/**
 * Created by orogersilva on 9/4/2016.
 */

public class PlaceDetailsActivity extends AppCompatActivity {

    // region FIELDS

    private static final String TAG = "PlaceDetailsActivity";

    private PlaceDetailsFragment mPlaceDetailsFragment;
    private PlaceDetailsPresenter mPlaceDetailsPresenter;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        if (mPlaceDetailsFragment == null) {

            mPlaceDetailsFragment = PlaceDetailsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mPlaceDetailsFragment, R.id.contentFrame);
        }

        // TODO: 9/4/2016 TO INSTANTIATE PRESENTER.
    }

    // endregion
}
