package com.mapl.converter.model.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdviceSlip {
    private static AdviceSlip singleton = null;
    private AdviceSlipGET API;

    private AdviceSlip() {
        API = createAdapter();
    }

    public static AdviceSlip getSingleton() {
        if (singleton == null) {
            singleton = new AdviceSlip();
        }

        return singleton;
    }

    public AdviceSlipGET getAPI() {
        return API;
    }

    private AdviceSlipGET createAdapter() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api.adviceslip.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return adapter.create(AdviceSlipGET.class);
    }
}
