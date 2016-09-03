package com.orogersilva.comabem.places.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orogersilva.comabem.ItemView;
import com.orogersilva.comabem.R;
import com.orogersilva.comabem.data.Place;
import com.orogersilva.comabem.util.NumberUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.orogersilva.comabem.util.NumberUtils.isEven;

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

            if (isEven(getAdapterPosition())) {
                itemView.setBackgroundResource(R.color.indigo);
            } else {
                itemView.setBackgroundResource(R.color.light_indigo);
            }

            mNameTextView.setText(place.getName());
            mScoreTextView.setText(String.valueOf(place.getScore()));
        }

        // endregion
    }

    // endregion
}