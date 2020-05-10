package com.mapl.converter.model.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(indices = {@Index(value = {Advice.ID, Advice.ADVICE}, unique = true)})
public class Advice implements Serializable {

    public final static String ID = "id";
    public final static String NUMBER = "number";
    public final static String ADVICE = "advice";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public int id;

    @ColumnInfo(name = NUMBER)
    public int number;

    @ColumnInfo(name = ADVICE)
    public String advice;
}
