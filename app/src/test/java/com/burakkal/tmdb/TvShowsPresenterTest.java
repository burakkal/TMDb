package com.burakkal.tmdb;

import com.burakkal.tmdb.data.TvShowsRepository;
import com.burakkal.tmdb.data.model.TvShow;
import com.burakkal.tmdb.data.model.TvShowResponse;
import com.burakkal.tmdb.ui.tvshows.TvShowsContract;
import com.burakkal.tmdb.ui.tvshows.TvShowsPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Burak on 6.10.2017.
 * burakkal54@gmail.com
 */

public class TvShowsPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TvShowsRepository repository;

    @Mock
    TvShowsContract.View view;

    TvShowsPresenter presenter;

    private List<TvShow> MANY_TV_SHOWS = Arrays.asList(new TvShow(), new TvShow(), new TvShow());
    private TvShowResponse RESPONSE = new TvShowResponse();
    private String ERROR_MESSAGE = "error";

    @Before
    public void setUp() {
        presenter = new TvShowsPresenter(view, repository, Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RESPONSE.setResults(MANY_TV_SHOWS);
    }

    @After
    public void cleanUp() {
        RxJavaPlugins.reset();
    }

    @Test
    public void shouldPassTvShowsToView() {
        Mockito.when(repository.getPopularTvShows()).thenReturn(Single.just(RESPONSE));

        presenter.loadPopularTvShows();

        Mockito.verify(view).showPopularTvShows(MANY_TV_SHOWS);
    }

    @Test
    public void shouldHandleError() {
        Mockito.when(repository.getPopularTvShows())
                .thenReturn(Single.<TvShowResponse>error(new Throwable(ERROR_MESSAGE)));

        presenter.loadPopularTvShows();

        Mockito.verify(view).showErrorMessage(ERROR_MESSAGE);
    }

}
