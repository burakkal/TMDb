package com.burakkal.tmdb;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public class TmdbApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
