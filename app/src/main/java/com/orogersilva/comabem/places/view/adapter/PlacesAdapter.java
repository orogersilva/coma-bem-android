package com.orogersilva.comabem.places.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orogersilva.comabem.ItemView;
import com.orogersilva.comabem.R;
import com.orogersilva.comabem.data.Place;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by orogersilva on 8/29/2016.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ItemViewHolder> {

    // region FIELDS

    private List<Place> mPlaces;

    // endregion

    // region CONSTRUCTORS

    public PlacesAdapter(List<Place> places) {

        mPlaces = places;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_place, parent, false);

        v.setTag(new PlacesItemPresenter());

        ItemViewHolder placesItemViewHolder = new ItemViewHolder(v);

        return placesItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ((PlacesItemPresenter) holder.itemView.getTag()).presentListItem(holder, mPlaces.get(position));
    }

    @Override
    public int getItemCount() {

        return mPlaces.size();
    }

    // endregion

    // region UTILITY CLASSES

    class ItemViewHolder extends RecyclerView.ViewHolder implements ItemView<Place> {

        // region FIELDS

        @BindView(R.id.nameTextView)
        TextView mNameTextView;
        @BindView(R.id.latTextView)
        TextView mLatTextView;
        @BindView(R.id.lngTextView)
        TextView mLngTextView;
        @BindView(R.id.scoreTextView)
        TextView mScoreTextView;

        // endregion

        // region CONSTRUCTORS

        public ItemViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        // endregion

        // region OVERRIDED METHODS

        @Override
        public void setItem(Place place) {

            mNameTextView.setText(place.getName());
            mLatTextView.setText(String.valueOf(place.getLat()));
            mLngTextView.setText(String.valueOf(place.getLng()));
            mScoreTextView.setText(String.valueOf(place.getScore()));
        }

        // endregion
    }

    // endregion
}