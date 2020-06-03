package com.example.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bakingapp.model.Steps;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    public static Steps steps;
    private String shortDescription;
    private String videoURL;
    private String mDescription;
    private Uri uri;

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @BindView(R.id.exo_Player)
    PlayerView playerView;
    @BindView(R.id.step_detail_description)
    TextView descriptionView;
    @BindView(R.id.placeholder_image_view)
    ImageView placeholderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        videoURL = steps.getVideoURL();
        mDescription = steps.getDescription();
        shortDescription = steps.getShortDescription();

        ButterKnife.bind(this);
        descriptionView.setText(mDescription);
    }
}
