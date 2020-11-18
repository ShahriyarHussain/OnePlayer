package com.example.oneplayer;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    PlayerView playerView;
    ProgressBar progressBar;
    ImageView fullscreen;
    SimpleExoPlayer simpleExoPlayer;
    Boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        playerView = findViewById(R.id.PlayerView);
        progressBar = findViewById(R.id.progress_bar);
        fullscreen = findViewById(R.id.fullscreen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Uri videourl = Uri.parse("https://i.imgur.com/7bMqysJ.mp4");

        LoadControl loadControl = new DefaultLoadControl();

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelector trackSelector = new DefaultTrackSelector
                (new AdaptiveTrackSelection.Factory(bandwidthMeter));

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance
                (VideoActivity.this, trackSelector, loadControl);

        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory
                ("exoplayerVideo");

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource
                (videourl, factory, extractorsFactory, null, null);

        playerView.setPlayer(simpleExoPlayer);
        playerView.setKeepScreenOn(true);

        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_BUFFERING)
                    progressBar.setVisibility(View.VISIBLE);
                else if (playbackState == Player.STATE_READY)
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag) {
                    fullscreen.setImageDrawable(getResources().getDrawable(R.drawable.fullscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    flag = false;
                } else {
                    fullscreen.setImageDrawable(getResources().getDrawable(R.drawable.exitscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    flag = true;
                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }
}
