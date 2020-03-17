package com.mapl.converter;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.mapl.converter.presenter.MainActivityPresenter;
import com.mapl.converter.view.MainActivityView;

import java.io.IOException;
import java.util.Objects;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity
        implements MainActivityView, View.OnClickListener {

    @InjectPresenter
    MainActivityPresenter presenter;

    public static final int PHOTO_KEY = 12;
    public static final int PERMISSION_KEY = 11;
    private Bitmap bitmap;
    private Button btnSelect, btnConvert;
    private TextView textUri, advice_number, advice;
    private ImageView imgMain;
    private ProgressBar progressBar;
    private View adviceLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelect = findViewById(R.id.button_select);
        btnConvert = findViewById(R.id.button_convert);
        textUri = findViewById(R.id.text_uri);
        advice_number = findViewById(R.id.advice_number);
        advice = findViewById(R.id.advice);
        imgMain = findViewById(R.id.image_main);
        progressBar = findViewById(R.id.progressBar);
        adviceLayout = findViewById(R.id.advice_layout);

        btnSelect.setOnClickListener(this);
        btnConvert.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.loadAdvice();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_select:
                presenter.selectButtonClick();
                break;
            case R.id.button_convert:
                presenter.convertButtonClick();
                break;
        }
    }

    @Override
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PHOTO_KEY);
    }

    @Override
    public void setMainImage(Intent data) {
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            imgMain.setImageBitmap(bitmap);
            btnConvert.setEnabled(true);
            textUri.setText(Objects.requireNonNull(data.getData()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void conversionResult() {
        Toast.makeText(this, "Saved in the download", Toast.LENGTH_SHORT).show();
        btnConvert.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.activityResultImage(requestCode, resultCode, data);
    }

    @Override
    public void versionMOrLater() {
        int result = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        presenter.checkPermission(result);
    }

    @Override
    public void havePermission() {
        btnConvert.setEnabled(false);
        presenter.convertBitmap(bitmap);
    }

    @Override
    public void noPermission() {
        btnConvert.setEnabled(true);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_KEY);
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.loadAdvice();
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        presenter.loadAdvice();
    }

    @Override
    public void showAdvice(String adviceText, String id) {
        adviceLayout.setVisibility(View.VISIBLE);
        advice_number.setText(id);
        advice.setText(adviceText);
    }

    @Override
    public void hideAdvice() {
        adviceLayout.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.permissionsResultConvert(requestCode, grantResults);
    }
}
