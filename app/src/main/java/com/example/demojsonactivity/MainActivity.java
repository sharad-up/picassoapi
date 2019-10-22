package com.example.demojsonactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.demojsonactivity.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryCodeAsync.AsyncListener{
    RecyclerView recyclerView;
    ModelAdapter modelAdapter;
    private String urlPortal = "https://pixabay.com/api/?key=8439361-5e1e53a0e1b58baa26ab398f7&page=1&per_page=6";;
    private ArrayList<Country> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
//        Intent intent = getIntent();
//        String getCount =  intent.getStringExtra("Dynamic Count");
//        String addcountUrl = urlPortal + getCount;
        System.out.println(this);
        CountryCodeAsync countryCodeAsysn= new CountryCodeAsync(this, this);
//                new CountryCodeAsync.AsyncListener() {
//            @Override
//            public void onCompleted(String s) {
//                Log.e("data", s);
//                try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray jsonArray= jsonObject.getJSONArray("hits");
//                list = new ArrayList<>();
//                for (int j = 0; j <= jsonArray.length(); j++) {
//                    JSONObject object = jsonArray.getJSONObject(j);
//                    CountryModelClass country = new CountryModelClass();
//                    country.setCountryname(object.getString("id"));
//                    country.setShortname(object.getString("views"));
//                    list.add(country);
//                    Log.e("COUNTRY::::::::", country.getCountryname());
//                    setAdapter();
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            }
//        });
        countryCodeAsysn.execute(urlPortal);

    }
    //
    public void setAdapter(){
        modelAdapter = new ModelAdapter(MainActivity.this, list, new ModelAdapter.OnclickLister() {
            @Override
            public void clickListener(Country country) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DisplayImageUrlActivity.class);
                intent.putExtra("Data", country);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(modelAdapter);
    }

    @Override
    public void onCompleted(String s) {
        Log.e("data", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray= jsonObject.getJSONArray("hits");
            list = new ArrayList<>();
            for (int j = 0; j <= jsonArray.length(); j++) {
                JSONObject object = jsonArray.getJSONObject(j);
                Country country = new Country();
                country.setImageurl(object.getString("largeImageURL"));
                country.setCountryName(object.getString("downloads"));
                country.setShortName(object.getString("user"));
                list.add(country);
                Log.e("COUNTRY::::::::", country.getCountryName());
                setAdapter();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}