package com.example.mytest;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private final int VOICE_REQUEST = 1999;
    EditText searchText;
    private java.util.List<Film> list;
    private java.util.List<Film> films;

    int id;
    FilmAdapter filmAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        list = new ArrayList<>();
        films = new ArrayList<>();

        filmAdapter =new FilmAdapter(films,this);
         recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        searchText = findViewById(R.id.search_edit_text);
       searchText.addTextChangedListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(filmAdapter);
        extractFilms();
        filmAdapter.notifyDataSetChanged();

    }

    private void extractFilms() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=aa11e6f5731f0e9911208449db69dea6&language=en-US&page=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results =   response.getJSONArray("results");
                            for (int i = 0; i<10 ; i++){
                            JSONObject filmObj =results.getJSONObject(i);
                            Film film =new Film();
                            film.setName(filmObj.getString("title").toString());
                            film.setDescription(filmObj.getString("overview").toString());
                            film.setImageUML("https://image.tmdb.org/t/p/w780"+filmObj.getString("poster_path").toString());
                            films.add(film);
                            }

                            recyclerView.setAdapter(filmAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("tag", "eeedfce");

                    }
                });
        queue.add(jsonObjectRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.voice:
                id = R.id.voice;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, VOICE_REQUEST);
                return true;

        }


        return false;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null) {
                    searchText.setText(result.get(0));

                }
            }
        }


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

       // showProduct();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    public void showProduct() {
/*
        String key = searchText.getText().toString();
        if (!key.isEmpty()) {
            films.clear();
            Log.d("key",key);
            int i = 0;
            Log.d("size",list.size()+"");

            while ( list.size()!=i) {

                if (list.get(i).getName().contains(key) || list.get(i).getDescription().contains(key)) {
                    films.add(new Film(list.get(i).getName(),list.get(i).getDescription()));
                    Log.d("work","done!");
                    filmAdapter.notifyDataSetChanged();
                }


                i++;
            }
            Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_LONG);
            filmAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG);
        }
    */}

}