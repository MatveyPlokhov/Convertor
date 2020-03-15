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
    void checkPermission();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void startProgressBar();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void stopProgressBar();
}
