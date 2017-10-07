package com.burakkal.tmdb.ui.tvshows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.burakkal.tmdb.R;
import com.burakkal.tmdb.TmdbApplication;
import com.burakkal.tmdb.data.TvShowsRepository;
import com.burakkal.tmdb.data.model.TvShow;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class TvShowsActivity extends AppCompatActivity implements TvShowsContract.View {

    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;

    @BindView(R.id.tv_error_message)
    TextView mTvErrorMessage;

    @BindView(R.id.rv_tv_shows)
    RecyclerView mRecyclerTvShows;

    private TvShowsPresenter mPresenter;
    private TvShowsAdapter mTvShowsAdapter;

    @Inject
    TvShowsRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows);

        ButterKnife.bind(this);

        TmdbApplication.get(this).getComponent().inject(this);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerTvShows.setLayoutManager(layoutManager);
        mTvShowsAdapter = new TvShowsAdapter(this, null);
        mRecyclerTvShows.setAdapter(mTvShowsAdapter);

        mPresenter = new TvShowsPresenter(this, mRepository, AndroidSchedulers.mainThread());
        mPresenter.loadPopularTvShows();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showLoading() {
        mRecyclerTvShows.setVisibility(View.GONE);
        mTvErrorMessage.setVisibility(View.GONE);
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mRecyclerTvShows.setVisibility(View.VISIBLE);
        mTvErrorMessage.setVisibility(View.GONE);
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showPopularTvShows(List<TvShow> tvShows) {
        mRecyclerTvShows.setVisibility(View.VISIBLE);
        mTvErrorMessage.setVisibility(View.GONE);
        mTvShowsAdapter.setTvShows(tvShows);
    }

    @Override
    public void showErrorMessage(String message) {
        mRecyclerTvShows.setVisibility(View.GONE);
        mTvErrorMessage.setVisibility(View.VISIBLE);
        mTvErrorMessage.setText(message);
    }
}
