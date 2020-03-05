package com.mapl.converter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mapl.converter.presenter.MainActivityPresenter;
import com.mapl.converter.view.MainActivityView;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity
        implements MainActivityView, View.OnClickListener {

    @InjectPresenter
    MainActivityPresenter presenter;

    public static final int KEY = 12;
    private Uri uri;
    private Button btnSelect, btnConvert;
    private TextView textUri;
    private ImageView imgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelect = findViewById(R.id.button_select);
        btnConvert = findViewById(R.id.button_convert);
        textUri = findViewById(R.id.text_uri);
        imgMain = findViewById(R.id.image_main);

        btnSelect.setOnClickListener(this);
        btnConvert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_select:
                presenter.selectButtonClick();
                break;
            case R.id.button_convert:
                presenter.convertButtonClick(uri);
                break;
        }
    }

    @Override
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), KEY);
    }

    @Override
    public void setMainImage(Intent data) {
        uri = data.getData();
        imgMain.setImageURI(uri);
        btnConvert.setEnabled(true);
        textUri.setText(uri.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.activityResultImage(requestCode, resultCode, data);
    }
}
