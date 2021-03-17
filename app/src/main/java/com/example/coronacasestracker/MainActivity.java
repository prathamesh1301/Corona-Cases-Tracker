package com.example.coronacasestracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CoronaApiService coronaApiService;
    Gson gson;
    ArrayAdapter<String> adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);

        Retrofit retrofit=new Retrofit.Builder()
                                .baseUrl("https://covid2019-api.herokuapp.com/v2/current/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        coronaApiService=retrofit.create(CoronaApiService.class);
        getCoronaDetails();

    }

    public void getCoronaDetails(){
        Call<StartPage> call=coronaApiService.getData();
        call.enqueue(new Callback<StartPage>() {
            @Override
            public void onResponse(Call<StartPage> call, Response<StartPage> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Not successful",Toast.LENGTH_SHORT).show();
                    return;
                }
                StartPage startPage= response.body();
                List<Data> array=startPage.getListData();
                List<String> names=new ArrayList<>();
                List<Long> confirmedA=new ArrayList<>();
                List<Long> recoveredA=new ArrayList<>();
                List<Long> deathsA=new ArrayList<>();
                List<Long> activeA=new ArrayList<>();
                for(Data d:array){
                    names.add(d.getLocation());
                    confirmedA.add(d.getConfirmed());
                    recoveredA.add(d.getRecovered());
                    deathsA.add(d.getDeath());
                    activeA.add(d.getActive());

                }
                adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,names);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String content="";
                        content+="Location: "+names.get(position)+"\n";
                        content+="Confirmed: "+confirmedA.get(position)+"\n";
                        content+="Recovered: "+recoveredA.get(position)+"\n";
                        content+="Deaths: "+deathsA.get(position)+"\n";
                        content+="Active: "+activeA.get(position)+"\n";

                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Corona Tracker");
                        builder.setMessage(content);
                        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                });
            }

            @Override
            public void onFailure(Call<StartPage> call, Throwable t) {

            }
        });
    }


}