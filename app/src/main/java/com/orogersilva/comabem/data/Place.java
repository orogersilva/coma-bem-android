package com.orogersilva.comabem.data;

import android.support.annotation.NonNull;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by orogersilva on 8/3/2016.
 */

public class Place extends RealmObject {

    // region FIELDS

    @PrimaryKey
    private long id;
    private String name;
    private double lat;
    private double lng;
    private double score;

    // endregion

    // region CONSTRUCTORS

    public Place() {}

    public Place(long id, String name, double lat, double lng, double score) {

        setId(id);
        setName(name);
        setLat(lat);
        setLng(lng);
        setScore(score);
    }

    // endregion

    // region GETTERS AND SETTERS

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(@NonNull String name) {

        this.name = name;
    }

    public double getLat() {

        return lat;
    }

    public void setLat(double lat) {

        this.lat = lat;
    }

    public double getLng() {

        return lng;
    }

    public void setLng(double lng) {

        this.lng = lng;
    }

    public double getScore() {

        return score;
    }

    public void setScore(double score) {

        this.score = score;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;

        Place place = (Place) o;

        final double PRECISION = 0.0001;

        return (getId() == place.getId()) &&
                Objects.equals(getName(), place.getName()) &&
                (getLat() - place.getLat() < PRECISION) &&
                (getLng() - place.getLng() < PRECISION) &&
                (getScore() - place.getScore() < PRECISION);
    }

    // endregion
}
