package com.burakkal.tmdb.data;

import com.burakkal.tmdb.data.model.TvShowResponse;
import com.burakkal.tmdb.data.remote.HttpClient;
import com.burakkal.tmdb.data.remote.TvShowsRestService;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public class TvShowsRepository implements TvShowsDataSource {

    private TvShowsRestService mService;

    public TvShowsRepository(TvShowsRestService service) {
        mService = service;
    }

    @Override
    public Single<TvShowResponse> getPopularTvShows() {
        return mService.getPopularTvShows(HttpClient.TMDB_API_KEY);
    }
}
