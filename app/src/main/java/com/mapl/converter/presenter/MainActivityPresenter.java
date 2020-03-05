package com.mapl.converter.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

import com.mapl.converter.MainActivity;
import com.mapl.converter.model.MainActivityModel;
import com.mapl.converter.view.MainActivityView;

import moxy.MvpPresenter;

import static android.app.Activity.RESULT_OK;

@SuppressLint("CheckResult")
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    private MainActivityModel model;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model = new MainActivityModel();
    }

    @Override
    public void attachView(MainActivityView view) {
        super.attachView(view);
    }


    public void selectButtonClick() {
        getViewState().selectImage();
    }

    public void convertButtonClick(Uri uri) {
        //выполнение в потоке
        model.convertImage(uri);
    }

    public void activityResultImage(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.KEY && resultCode == RESULT_OK && data != null && data.getData() != null)
            getViewState().setMainImage(data);
    }
}
