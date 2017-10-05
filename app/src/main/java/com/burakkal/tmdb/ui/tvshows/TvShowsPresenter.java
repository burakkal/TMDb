package com.burakkal.tmdb.ui.tvshows;

import com.burakkal.tmdb.data.TvShowsRepository;
import com.burakkal.tmdb.data.model.TvShow;
import com.burakkal.tmdb.data.model.TvShowResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Burak on 4.10.2017.
 * burakkal54@gmail.com
 */

public class TvShowsPresenter implements TvShowsContract.Presenter {

    private TvShowsContract.View mView;
    private TvShowsRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public TvShowsPresenter(TvShowsContract.View view, TvShowsRepository repository) {
        mView = view;
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadPopularTvShows() {
        Disposable disposable = mRepository
                .getPopularTvShows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TvShowResponse, List<TvShow>>() {
                    @Override
                    public List<TvShow> apply(@NonNull TvShowResponse tvShowResponse) throws Exception {
                        return tvShowResponse.getResults();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribeWith(new DisposableSingleObserver<List<TvShow>>() {
                    @Override
                    public void onSuccess(@NonNull List<TvShow> tvShows) {
                        mView.showPopularTvShows(tvShows);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showErrorMessage(e.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }


}
