package com.mapl.converter.model.rest.entities;

import com.google.gson.annotations.SerializedName;

public class SlipModel {
    @SerializedName("advice")
    public String advice;
    @SerializedName("slip_id")
    public int id;
}
