package com.burakkal.tmdb.ui.tvshows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.burakkal.tmdb.R;
import com.burakkal.tmdb.data.model.TvShow;
import com.burakkal.tmdb.di.modules.HttpClientModule;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Burak on 5.10.2017.
 * burakkal54@gmail.com
 */

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder> {

    private Context context;
    private List<TvShow> tvShows;

    public TvShowsAdapter(Context context, List<TvShow> tvShows) {
        this.context = context;
        this.tvShows = tvShows;
    }

    @Override
    public TvShowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_tv_shows, parent, false);
        return new TvShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TvShowsViewHolder holder, int position) {
        TvShow tvShow = tvShows.get(position);

        holder.tvName.setText(tvShow.getName());
        holder.tvOverview.setText(tvShow.getOverview());
        holder.tvVoteAverage.setText(String.valueOf(tvShow.getVote_average()));

        Picasso.with(context)
                .load(HttpClientModule.API_IMAGE_URL + tvShow.getPoster_path())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        if (tvShows == null) return 0;
        return tvShows.size();
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }

    class TvShowsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_overview)
        TextView tvOverview;

        @BindView(R.id.img_poster)
        ImageView imgPoster;

        @BindView(R.id.tv_vote_average)
        TextView tvVoteAverage;

        public TvShowsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
