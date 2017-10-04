package com.burakkal.tmdb.ui.tvshows;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

interface TvShowsContract {

    interface View {

    }

    interface Presenter {
        void loadPopularTvShows();
        void unsubscribe();
    }
}
