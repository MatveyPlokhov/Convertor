package com.mapl.converter.view;

import android.content.Intent;

import moxy.MvpView;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = SingleStateStrategy.class)
public interface MainActivityView extends MvpView {
    void selectImage();
    void setMainImage(Intent data);
}
