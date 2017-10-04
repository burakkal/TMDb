package com.burakkal.tmdb.ui.tvshows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.burakkal.tmdb.R;

public class TvShowsActivity extends AppCompatActivity implements TvShowsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows);
    }
}
