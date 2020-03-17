package com.mapl.converter.model;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivityModel {
    public boolean convertImage(Bitmap bitmap) {
        boolean result = false;
        int quality = 100;
        String fileName = getFileName();
        try {
            OutputStream out = new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName);
            result = bitmap.compress(Bitmap.CompressFormat.PNG, quality, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getFileName() {
        Date date = new Date();
        DateFormat timeFormat = new SimpleDateFormat("_dd.MM.yyyy_HH:mm:ss", Locale.getDefault());
        return "IMG" + timeFormat.format(date) + ".png";
    }
}
