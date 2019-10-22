package com.example.demojsonactivity;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demojsonactivity.model.Country;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.RowView> {
    private Context mContext;
    private ArrayList<Country> mArrayList;
    private OnclickLister onclickLister;

    public interface OnclickLister{
        void clickListener(Country country );
    }

    ModelAdapter(Context context, ArrayList<Country> modelArrayList, OnclickLister clickListener){
        mContext = context;
        mArrayList = modelArrayList;
        onclickLister = clickListener;
    }
    @NonNull
    @Override
    public RowView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_view_activity,parent,false);
        RowView rowView = new RowView(view);
        return rowView;
    }

    @Override
    public void onBindViewHolder(@NonNull RowView holder, int position) {
        holder.binddata(mArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }



    public class RowView extends RecyclerView.ViewHolder  {
        LinearLayout constraintLayout;
        TextView textView1,textView2;


        public RowView(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_calling_code);
            textView2 = itemView.findViewById(R.id.text_name);
            constraintLayout = itemView.findViewById(R.id.layout_view);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickLister.clickListener(mArrayList.get(getAdapterPosition()));
                }
            });
        }

        public void binddata(Country country){
            textView1.setText(country.getCountryName());
            textView2.setText(country.getShortName());
        }


    }


    public static class CountryCodeAsync extends AsyncTask<String, Void, String> {

        private AsyncListener mAsynsListener;

        interface AsyncListener{
            void onCompleted(String s);
        }

        public CountryCodeAsync(Context context, AsyncListener asyncListener){
            mAsynsListener = asyncListener;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String contryUrl = strings[0];
            try {
                URL url = new URL(contryUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                return convertInputStreamToString(in);
            }catch (MalformedURLException e){
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAsynsListener.onCompleted(s);
        }
        private String convertInputStreamToString(InputStream inputStream){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (IOException e){
            }

            return builder.toString();
        }

    }
}
