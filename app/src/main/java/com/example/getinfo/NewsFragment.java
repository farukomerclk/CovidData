package com.example.getinfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
 Button b;

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<String> parseItems = new ArrayList();
    private ArrayList<String>  URLS= new ArrayList<>();
    String countryURL = "https://www.worldometers.info/coronavirus/country/";
    private ProgressBar progressBar;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_news, container, false);

        final Spinner mySpinner = v.findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                Content content = new Content();
                CountryContent countryContent = new CountryContent();

                String items = mySpinner.getSelectedItem().toString();
                Log.i("Selected item : ", items);
                switch (items){
                    case "World":
                        content.execute();
                        break;
                    case "USA":
                        new CountryContent().execute("us");
                        break;
                    case "Russian":
                        new CountryContent().execute("russia");
                        break;
                    case "Spain":
                        new CountryContent().execute("spain");
                        break;
                    case "Brazil":
                        new CountryContent().execute("brazil");
                        break;
                    case "UK":
                        new CountryContent().execute("uk");
                        break;
                    case "Italy":
                        new CountryContent().execute("italy");
                        break;
                    case "France":
                        new CountryContent().execute("france");
                        break;
                    case "Germany":
                        new CountryContent().execute("germany");
                        break;
                    case "Turkey":
                        new CountryContent().execute("turkey");
                        break;
                    case "Iran":
                        new CountryContent().execute("iran");
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        b = v.findViewById(R.id.button);

        progressBar = v.findViewById(R.id.progressBar);
        recyclerView = v.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new ParseAdapter(parseItems,URLS, this.getContext());
        //recyclerView.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });


        return v;}

        private  class CountryContent extends AsyncTask<String, Void, Void>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
                parseItems.clear();
            }

            @Override
            protected Void doInBackground(String... params) {
                //String countryURL = "https://www.worldometers.info/coronavirus/country/";
                String country = countryURL + params[0];

                try {
                    Document doc = Jsoup.connect(country).timeout(30*1000).get();

                    Elements time = doc.select("div[style*=font-size:13px; color:#999;]");
                    parseItems.add(time.get(0).text());

                    Elements numbers = doc.select("div.maincounter-number");
                    int size = numbers.size();

                    for(int i = 0; i < size; i++){
                        parseItems.add(numbers.get(i).text());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressBar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            parseItems.clear();
            //progressBar.startAnimation(AnimationUtils.loadAnimation(Main2Activity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = "https://www.worldometers.info/coronavirus/";

                Document doc = Jsoup.connect(url).get();
               /* Log.d("doc", "doc: "+doc);
                Log.d("data", "data: "+data);
                Log.d("size", ""+size);*/
               Elements time = doc.select("div[style*=font-size:13px; color:#999;]");
               parseItems.add(time.get(0).text());

                //app:layout_constraintTop_toBottomOf="@id/eee"////////////////////////////////
                Elements veri = doc.select("div.maincounter-number");
                int size = veri.size();
                for (int i = 0; i < size; i++) {
                    parseItems.add(veri.get(i).text());
                }

                //Elements data = doc.getElementsByClass("content-inner").select("div");
                Elements activeCase = doc.select("div.number-table-main");
                int sizeData = activeCase.size();
                parseItems.add(activeCase.get(0).text());

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}

