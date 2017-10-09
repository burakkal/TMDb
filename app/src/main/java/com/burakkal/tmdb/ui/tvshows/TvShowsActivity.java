package com.burakkal.tmdb.ui.tvshows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @BindView(R.id.rv_popular_tv_shows)
    RecyclerView mRecyclerPopularTvShows;

    @BindView(R.id.rv_top_rated_tv_shows)
    RecyclerView mRecyclerTopRatedTvShows;

    private TvShowsPresenter mPresenter;
    private TvShowsAdapter mPopularTvShowsAdapter;
    private TvShowsAdapter mTopRatedTvShowsAdapter;

    @Inject
    TvShowsRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows);

        ButterKnife.bind(this);

        TmdbApplication.get(this).getComponent().inject(this);

        mPopularTvShowsAdapter = new TvShowsAdapter(this, null);
        mTopRatedTvShowsAdapter = new TvShowsAdapter(this, null);
        mRecyclerPopularTvShows.setAdapter(mPopularTvShowsAdapter);
        mRecyclerTopRatedTvShows.setAdapter(mTopRatedTvShowsAdapter);

        mPresenter = new TvShowsPresenter(this, mRepository, AndroidSchedulers.mainThread());
        mPresenter.loadPopularTvShows();
        mPresenter.loadTopRatedTvShows();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void showLoading() {
        mRecyclerPopularTvShows.setVisibility(View.GONE);
        mRecyclerTopRatedTvShows.setVisibility(View.GONE);
        mTvErrorMessage.setVisibility(View.GONE);
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mRecyclerPopularTvShows.setVisibility(View.VISIBLE);
        mRecyclerTopRatedTvShows.setVisibility(View.VISIBLE);
        mTvErrorMessage.setVisibility(View.GONE);
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showPopularTvShows(List<TvShow> tvShows) {
        mRecyclerPopularTvShows.setVisibility(View.VISIBLE);
        mTvErrorMessage.setVisibility(View.GONE);
        mPopularTvShowsAdapter.setTvShows(tvShows);
    }

    @Override
    public void showTopRatedTvShows(List<TvShow> tvShows) {
        mRecyclerTopRatedTvShows.setVisibility(View.VISIBLE);
        mTvErrorMessage.setVisibility(View.GONE);
        mTopRatedTvShowsAdapter.setTvShows(tvShows);
    }

    @Override
    public void showErrorMessage(String message) {
        mRecyclerPopularTvShows.setVisibility(View.GONE);
        mRecyclerTopRatedTvShows.setVisibility(View.GONE);
        mTvErrorMessage.setVisibility(View.VISIBLE);
        mTvErrorMessage.setText(message);
    }
}
