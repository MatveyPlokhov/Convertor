package com.mapl.converter.model.rest;

import com.mapl.converter.model.rest.entities.AdviceSlipModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdviceSlipGET {
    @GET("advice")
    Call<AdviceSlipModel> loadAdvice();
}
