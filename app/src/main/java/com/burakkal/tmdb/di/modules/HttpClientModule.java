package com.burakkal.tmdb.di.modules;

import com.burakkal.tmdb.BuildConfig;
import com.burakkal.tmdb.data.TvShowsRepository;
import com.burakkal.tmdb.data.remote.TvShowsRestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

@Module
public class HttpClientModule {

    private static final String API_URL = "https://api.themoviedb.org/";
    private static final int API_VERSION = 3;
    private static final String API_BASE_URL = API_URL + API_VERSION + "/";
    public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;

    public static final String POPULAR_TV_SHOWS = "tv/popular";

    @Singleton
    @Provides
    TvShowsRepository provideTvShowsRepository(TvShowsRestService restService) {
        return new TvShowsRepository(restService);
    }

    @Singleton
    @Provides
    TvShowsRestService provideTvShowsRestService(Retrofit retrofit) {
        return retrofit.create(TvShowsRestService.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetroFitInstance() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
