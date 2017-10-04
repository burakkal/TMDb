package com.burakkal.tmdb.data.remote;

import com.burakkal.tmdb.data.model.TvShowResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public interface TvShowsRestService {

    @GET(HttpClient.POPULAR_TV_SHOWS)
    Single<TvShowResponse> getPopularTvShows(@Query("api_key") String apiKey);
}
