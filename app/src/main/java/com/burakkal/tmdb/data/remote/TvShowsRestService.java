package com.burakkal.tmdb.data.remote;

import com.burakkal.tmdb.data.model.TvShowResponse;
import com.burakkal.tmdb.di.modules.HttpClientModule;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public interface TvShowsRestService {

    @GET(HttpClientModule.POPULAR_TV_SHOWS)
    Single<TvShowResponse> getPopularTvShows(@Query("api_key") String apiKey);

    @GET(HttpClientModule.TOP_RATED_TV_SHOWS)
    Single<TvShowResponse> getTopRatedTvShows(@Query("api_key") String apiKey);
}
