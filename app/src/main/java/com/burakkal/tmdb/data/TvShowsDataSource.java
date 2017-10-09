package com.burakkal.tmdb.data;

import com.burakkal.tmdb.data.model.TvShowResponse;

import io.reactivex.Single;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public interface TvShowsDataSource {

    Single<TvShowResponse> getPopularTvShows();

    Single<TvShowResponse> getTopRatedTvShows();
}
