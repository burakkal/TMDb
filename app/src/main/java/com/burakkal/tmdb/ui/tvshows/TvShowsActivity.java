package com.burakkal.tmdb.ui.tvshows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.burakkal.tmdb.R;
import com.burakkal.tmdb.data.TvShowsRepository;
import com.burakkal.tmdb.data.remote.HttpClient;

import butterknife.ButterKnife;
import timber.log.Timber;

public class TvShowsActivity extends AppCompatActivity implements TvShowsContract.View {

    private TvShowsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows);

        ButterKnife.bind(this);

        TvShowsRepository repository = new TvShowsRepository(
                HttpClient.provideTvShowsRestService());
        mPresenter = new TvShowsPresenter(this, repository);
        mPresenter.loadPopularTvShows();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }
}
