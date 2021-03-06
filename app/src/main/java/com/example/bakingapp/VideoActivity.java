package com.example.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bakingapp.model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    public static Steps steps;
    public static List<Steps> step_list;
    private String shortDescription;
    private String videoURL;
    private String mDescription;
    private Uri uri;

    //for savedInstance
    public static final String PLAYERPOSITION = "player_position";
    public static final String CURRENTPOSITION = "current_position";
    public static final String PLAY_WHEN_READY="PLAY_WHEN_READY";

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private int current_position = 0;

    @BindView(R.id.exo_Player)
    PlayerView playerView;
    @BindView(R.id.step_detail_description)
    TextView descriptionView;
    @BindView(R.id.step_short_description)
    TextView short_descriptionview;
    @BindView(R.id.no_video)
    TextView no_video;
    @BindView(R.id.BT_next)
    Button nextButtom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        ButterKnife.bind(this);

        if (savedInstanceState != null){
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY);
            current_position = savedInstanceState.getInt(CURRENTPOSITION);
            playbackPosition = savedInstanceState.getLong(PLAYERPOSITION);
        }

        if (steps != null) {
            videoURL = steps.getVideoURL();
            mDescription = steps.getDescription();
            shortDescription = steps.getShortDescription();
        }else {
            videoURL = "";
        }

        descriptionView.setText(mDescription);
        short_descriptionview.setText(shortDescription);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT < 24 || player == null) {
            initializePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    @Override
    protected void onPause() {
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(PLAYERPOSITION, playbackPosition);
        outState.putInt(CURRENTPOSITION,current_position);
        outState.putBoolean(PLAY_WHEN_READY,playWhenReady);
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        if (videoURL.equals("")) {
            playerView.setVisibility(View.INVISIBLE);
            no_video.setVisibility(View.VISIBLE);
        }  else {
            uri = Uri.parse(videoURL);
            playerView.setVisibility(View.VISIBLE);
        }
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, "BakingApp");
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }
}
