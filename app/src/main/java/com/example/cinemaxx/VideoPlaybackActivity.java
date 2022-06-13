package com.example.cinemaxx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlaybackActivity extends YouTubeBaseActivity {

    private String API_KEY = "AIzaSyD8oZ2XsLc3WF3PUllIc-clnPPh6j62ewk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        String videoKey = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        // Get reference to the view of Video player
        YouTubePlayerView ytPlayer = (YouTubePlayerView)findViewById(R.id.ytPlayer);

        ytPlayer.initialize(
                API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    // Implement two methods by clicking on red
                    // error bulb inside onInitializationSuccess
                    // method add the video link or the playlist
                    // link that you want to play In here we
                    // also handle the play and pause
                    // functionality
                    @Override
                    public void onInitializationSuccess(
                            YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b)
                    {
                        youTubePlayer.loadVideo(videoKey);
                        youTubePlayer.play();
                    }

                    // Inside onInitializationFailure
                    // implement the failure functionality
                    // Here we will show toast
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult
                                                                youTubeInitializationResult)
                    {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}