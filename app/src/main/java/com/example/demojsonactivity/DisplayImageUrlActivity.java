package com.example.demojsonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demojsonactivity.model.Country;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;

public class DisplayImageUrlActivity extends AppCompatActivity  {
    ImageView imageView;
    TextView textCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image_url);
       imageView = findViewById(R.id.image1);
        textCheck = findViewById(R.id.textshow);
        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("Data");
        textCheck.setText(country.getImageurl());
        Picasso.get().load(country.getImageurl()).into(imageView);


    }
}
