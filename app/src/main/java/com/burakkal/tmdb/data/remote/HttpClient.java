package com.burakkal.tmdb.data.remote;

import com.burakkal.tmdb.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public class HttpClient {

    private static final String API_URL = "https://api.themoviedb.org/";
    private static final int API_VERSION = 3;
    public static final String API_BASE_URL = API_URL + API_VERSION + "/";
    public static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;



    public static final String POPULAR_TV_SHOWS = "tv/popular";

    private static Retrofit provideRetroFitInstance() {

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static TvShowsRestService provideTvShowsRestService() {

        return provideRetroFitInstance().create(TvShowsRestService.class);

    }
}
