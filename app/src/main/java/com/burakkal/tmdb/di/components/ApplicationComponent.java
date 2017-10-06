package com.burakkal.tmdb.di.components;

import com.burakkal.tmdb.TmdbApplication;
import com.burakkal.tmdb.di.modules.AppContextModule;
import com.burakkal.tmdb.di.modules.HttpClientModule;
import com.burakkal.tmdb.ui.tvshows.TvShowsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Burak on 5.10.2017.
 * burakkal54@gmail.com
 */

@Singleton
@Component(modules = {AppContextModule.class, HttpClientModule.class})
public interface ApplicationComponent {

    void inject(TmdbApplication application);

    void inject(TvShowsActivity tvShowsActivity);

}
