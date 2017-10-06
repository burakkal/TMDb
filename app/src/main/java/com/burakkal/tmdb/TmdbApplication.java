package com.burakkal.tmdb;

import android.app.Activity;
import android.app.Application;

import com.burakkal.tmdb.di.components.ApplicationComponent;
import com.burakkal.tmdb.di.components.DaggerApplicationComponent;
import com.burakkal.tmdb.di.modules.AppContextModule;
import com.burakkal.tmdb.di.modules.HttpClientModule;

import timber.log.Timber;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public class TmdbApplication extends Application {

    private ApplicationComponent component;

    public static TmdbApplication get(Activity activity) {
        return (TmdbApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        component = DaggerApplicationComponent.builder()
                .appContextModule(new AppContextModule(this))
                .httpClientModule(new HttpClientModule())
                .build();

        component.inject(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
