package com.orogersilva.comabem.placedetails.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orogersilva.comabem.R;
import com.orogersilva.comabem.placedetails.PlaceDetailsContract;

/**
 * Created by orogersilva on 9/4/2016.
 */

public class PlaceDetailsFragment extends Fragment implements PlaceDetailsContract.View{

    // region FIELDS

    private static final String TAG = "PlaceDetailsFragment";

    // endregion

    // region STATIC METHODS

    public static PlaceDetailsFragment newInstance() {

        return new PlaceDetailsFragment();
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void setPresenter(PlaceDetailsContract.Presenter presenter) {

        // TODO: 9/4/2016 TO IMPLEMENT.
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_place_details, container, false);

        return view;
    }

    // endregion
}
