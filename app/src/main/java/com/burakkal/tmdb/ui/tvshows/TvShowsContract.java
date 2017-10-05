package com.burakkal.tmdb.ui.tvshows;

import com.burakkal.tmdb.data.model.TvShow;

import java.util.List;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

interface TvShowsContract {

    interface View {
        void showLoading();

        void hideLoading();

        void showPopularTvShows(List<TvShow> tvShows);

        void showErrorMessage(String message);
    }

    interface Presenter {
        void loadPopularTvShows();

        void unsubscribe();
    }
}
