package com.mapl.converter.model;

import android.graphics.Bitmap;
import android.os.Environment;

import com.mapl.converter.model.database.AdviceSingleton;
import com.mapl.converter.model.database.dao.AdvicesDao;
import com.mapl.converter.model.database.model.Advice;
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
import java.util.Random;

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
        AdvicesDao dao = AdviceSingleton.getInstance().getAdvicesDao();
        Random random = new Random();
        int count = dao.getCount();
        try {
            Response<AdviceSlipModel> response = AdviceSlip.getSingleton().getAPI().loadAdvice().execute();
            AdviceSlipModel model = response.body();
            if (response.isSuccessful() && model != null) {
                Advice advice = new Advice();
                advice.number = model.slipModel.number;
                advice.advice = model.slipModel.advice;
                dao.insertAdvice(advice);

                return getHashMap(model.slipModel.number, model.slipModel.advice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (count == 0) return new HashMap<>();
        Advice advice = dao.getAdviceById(1 + random.nextInt(count));
        return getHashMap(advice.number, advice.advice);
    }

    private HashMap<String, String> getHashMap(int number, String advice) {
        HashMap<String, String> map = new HashMap<>();
        map.put("number", String.valueOf(number));
        map.put("advice", advice);
        return map;
    }
}
