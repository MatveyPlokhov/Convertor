package com.mapl.converter.model;

import android.graphics.Bitmap;
import android.os.Environment;

import com.mapl.converter.model.rest.AdviceSlip;
import com.mapl.converter.model.rest.entities.AdviceSlipModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Response;

public class MainActivityModel {
    public boolean convertImage(Bitmap bitmap) {
        boolean result = false;
        int quality = 10;
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

    public HashMap<String, String> getAdvice() {
        try {
            Response<AdviceSlipModel> response = AdviceSlip.getSingleton().getAPI().loadAdvice().execute();
            AdviceSlipModel model = response.body();
            if (response.isSuccessful() && model != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put("advice", model.slipModel.advice);
                map.put("id", String.valueOf(model.slipModel.id));
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
