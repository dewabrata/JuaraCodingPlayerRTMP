package com.juaracoding.playerrtmp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class MainActivity extends AppCompatActivity {

    SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    Uri uri;
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView = findViewById(R.id.player_view);

        btnPlay = findViewById(R.id.retry);

        play();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
            }
        });
    }

    private void play(){
        uri = Uri.parse("rtmp://192.168.43.206:1935/live");
        player =    ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(MainActivity.this),new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);


        ExtractorMediaSource mediaSource = new ExtractorMediaSource(uri, new RtmpDataSourceFactory(), new DefaultExtractorsFactory(), null, null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

    }

    private void stop(){
        player.setPlayWhenReady(false);
        player.release();
    }

    private  void retry(){
        stop();
        play();
    }


}