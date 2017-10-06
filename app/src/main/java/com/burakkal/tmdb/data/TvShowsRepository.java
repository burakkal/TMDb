package com.burakkal.tmdb.data;

import com.burakkal.tmdb.data.model.TvShowResponse;
import com.burakkal.tmdb.di.modules.HttpClientModule;
import com.burakkal.tmdb.data.remote.TvShowsRestService;

import io.reactivex.Single;

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
        return mService.getPopularTvShows(HttpClientModule.TMDB_API_KEY);
    }
}
