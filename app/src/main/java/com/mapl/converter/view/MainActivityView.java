package com.mapl.converter.view;

import android.content.Intent;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainActivityView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void selectImage();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setMainImage(Intent data);

    @StateStrategyType(value = SkipStrategy.class)
    void conversionResult();

    @StateStrategyType(value = SkipStrategy.class)
    void versionMOrLater();

    @StateStrategyType(value = SkipStrategy.class)
    void havePermission();

    @StateStrategyType(value = SkipStrategy.class)
    void noPermission();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void startProgressBar();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void stopProgressBar();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showAdvice(String advice, String id);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void connectionError();
}
