package com.example.videoview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.videoview.entity.quality.VideoWithQuality;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {

    //private List<VideosDb> videoWithQualityList;
    private List<VideoWithQuality> videoWithQualityList;
    private RecyclerClickListener recyclerClickListener;

    public VideoListAdapter(List<VideoWithQuality> videoWithQualityList) {
        this.videoWithQualityList = videoWithQualityList;
    }

    @NonNull
    @Override
    public VideoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_video_list, parent, false);
        return new VideoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListViewHolder holder, int position) {
        //VideosDb videosDb = videoWithQualityList.get(position);
        VideoWithQuality videosDb = videoWithQualityList.get(position);

        holder.tvItemTitle.setText(videosDb.getVideoTitle());
    }

    @Override
    public int getItemCount() {
        return videoWithQualityList.size();
    }

    class VideoListViewHolder extends RecyclerView.ViewHolder {

        private PlayerView itemPlayerView;
        private TextView tvItemTitle;
        private SimpleExoPlayer itemPlayer;

        public VideoListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemPlayerView = itemView.findViewById(R.id.item_player_view);
            tvItemTitle = itemView.findViewById(R.id.item_tv_video_title);
        }
    }
}
