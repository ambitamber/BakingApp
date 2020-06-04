package com.example.bakingapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {
    public static Steps steps;
    private String shortDescription;
    private String videoURL;
    private String mDescription;
    private String thumbnailURL;
    private Uri uri;

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @BindView(R.id.exo_Player)
    PlayerView playerView;
    @BindView(R.id.step_detail_description)
    TextView descriptionView;
    @BindView(R.id.step_short_description)
    TextView shortDescriptionView;

    public VideoFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_player, container,false);
        ButterKnife.bind(this,view);

        if (steps != null){
            videoURL = steps.getVideoURL();
            mDescription = steps.getDescription();
            shortDescription = steps.getShortDescription();
            thumbnailURL = steps.getThumbnailURL();
        }else {
            videoURL = "";
        }
        descriptionView.setText(mDescription);
        shortDescriptionView.setText(shortDescription);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT < 24 || player == null) {
            initializePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        playerView.setPlayer(player);
        if (videoURL != null) {
            uri = Uri.parse(videoURL);
            playerView.setVisibility(View.VISIBLE);
        }  else if (videoURL.equals("") &&thumbnailURL.equals("")){
            playerView.setVisibility(View.INVISIBLE);
        }
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), "BakingApp");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
}
