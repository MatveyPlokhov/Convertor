package com.mapl.converter.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.mapl.converter.MainActivity;
import com.mapl.converter.model.MainActivityModel;
import com.mapl.converter.view.MainActivityView;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public void convertButtonClick() {
        checkAndroidVersion();
    }

    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) getViewState().versionMOrLater();
    }

    public void checkPermission(int result) {
        if (result == PackageManager.PERMISSION_GRANTED) {
            getViewState().havePermission();
        } else {
            getViewState().noPermission();
        }
    }

    public void convertBitmap(Bitmap bitmap) {
        getViewState().startProgressBar();
        Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
            emitter.onNext(model.convertImage(bitmap));
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    getViewState().stopProgressBar();
                    getViewState().conversionResult();
                });
    }

    public void activityResultImage(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.PHOTO_KEY && resultCode == RESULT_OK && data != null)
            getViewState().setMainImage(data);
    }

    public void permissionsResultConvert(int requestCode, int[] grantResults) {
        if (requestCode == MainActivity.PERMISSION_KEY && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            checkAndroidVersion();
    }
}