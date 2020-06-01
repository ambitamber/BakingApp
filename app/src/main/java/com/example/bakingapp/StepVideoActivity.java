package com.example.bakingapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepVideoActivity extends AppCompatActivity {

    public static final String TAG = StepVideoActivity.class.getSimpleName();

    private String shortDescription;
    private String videoURL;
    private String mDescription;
    private Uri uri;

    private SimpleExoPlayer exoPlayer;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @BindView(R.id.Exo_player)
    PlayerView playerView;
    @BindView(R.id.step_TV_description)
    TextView description;
    @BindView(R.id.step_imageview)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepvideo);
        Steps steps = new Steps();
        videoURL = steps.getVideoURL();
        mDescription = steps.getDescription();
        shortDescription = steps.getShortDescription();
        ButterKnife.bind(this);
        description.setText(mDescription);
        setTitle(shortDescription);
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
        if (Util.SDK_INT < 24 || exoPlayer == null) {
            initializePlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if (exoPlayer != null){
            playWhenReady = exoPlayer.getPlayWhenReady();
            playbackPosition = exoPlayer.getCurrentPosition();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @SuppressLint("Assert")
    private void initializePlayer() {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(exoPlayer);
        if (videoURL != null) {
            imageView.setVisibility(View.GONE);
            uri = Uri.parse(videoURL);
        } else {
            assert false;
            if (videoURL.equals("")){
                imageView.setVisibility(View.VISIBLE);
                playerView.setVisibility(View.GONE);
            }
        }
        MediaSource mediaSource = buildMediaSource(uri);
        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(currentWindow, playbackPosition);
        exoPlayer.prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory factory = new DefaultDataSourceFactory(this,"BakingApp");
        return new ProgressiveMediaSource.Factory(factory).createMediaSource(uri);
    }
}
