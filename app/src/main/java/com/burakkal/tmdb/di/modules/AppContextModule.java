package com.burakkal.tmdb.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Burak on 6.10.2017.
 * burakkal54@gmail.com
 */

@Module
public class AppContextModule {

    private Context context;

    public AppContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Singleton
    @Provides
    Context providesApplication() {
        return context;
    }
}