package com.burakkal.tmdb.di.modules;

import com.burakkal.tmdb.BuildConfig;
import com.burakkal.tmdb.data.TvShowsRepository;
import com.burakkal.tmdb.data.remote.TvShowsRestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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
    public static final String TOP_RATED_TV_SHOWS = "tv/top_rated";

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
    Retrofit provideRetroFitInstance(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }

        return interceptor;
    }
}
