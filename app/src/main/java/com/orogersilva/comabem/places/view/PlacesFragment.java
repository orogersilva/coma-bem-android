package com.orogersilva.comabem.places.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orogersilva.comabem.R;
import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.placedetails.PlaceDetailsActivity;
import com.orogersilva.comabem.places.PlacesContract;
import com.orogersilva.comabem.places.PlacesContract.Presenter;
import com.orogersilva.comabem.places.view.adapter.PlacesAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orogersilva on 8/11/2016.
 */

public class PlacesFragment extends Fragment implements PlacesContract.View {

    // region FIELDS

    private static final String TAG  = "PlacesFragment";

    private Presenter mPlacesPresenter;

    private RecyclerView mPlacesRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private AVLoadingIndicatorView mLoadingView;

    private List<Place> mPlaces = new ArrayList<>();

    private PlaceItemListener mPlaceItemListener = new PlaceItemListener() {

        @Override
        public void onPlaceClick(Place place) {

            mPlacesPresenter.loadPlaceDetails();
        }
    };

    // endregion

    // region INTERFACES

    public interface PlaceItemListener {

        // region METHODS

        void onPlaceClick(Place place);

        // endregion
    }

    // endregion

    // region CONSTRUCTORS

    public PlacesFragment() {}

    // endregion

    // region STATIC METHODS

    public static PlacesFragment newInstance() {

        return new PlacesFragment();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_places, container, false);

        mPlacesRecyclerView = (RecyclerView) view.findViewById(R.id.placesRecyclerView);
        mLoadingView = (AVLoadingIndicatorView) view.findViewById(R.id.loadingView);

        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new PlacesAdapter(mPlaces, mPlaceItemListener);

        mPlacesRecyclerView.setLayoutManager(mLayoutManager);
        mPlacesRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        mPlacesPresenter.start();
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean isActive() {

        return true;
    }

    @Override
    public void showPlaceDetails() {

        Intent intent = new Intent(getContext(), PlaceDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

        if (active) {
            mLoadingView.show();
        } else {
            mLoadingView.hide();
        }
    }

    @Override
    public void showLoadingPlacesError() {

        // TODO: 8/25/2016 TO IMPLEMENT
    }

    @Override
    public void showPlaces(List<Place> places) {

        mPlaces.clear();
        mPlaces.addAll(places);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(Presenter presenter) {

        mPlacesPresenter = presenter;
    }

    // endregion

    // region UTILITY CLASSES

    private class PlacesItemViewHolder extends RecyclerView.ViewHolder {

        // region CONSTRUCTORS

        public PlacesItemViewHolder(View itemView) {

            super(itemView);
        }

        // endregion
    }

    // endregion
}
