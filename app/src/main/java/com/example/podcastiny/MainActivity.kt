package com.example.podcastiny

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.upstream.DataSource


class MainActivity : AppCompatActivity() {
    private var playerControlView : PlayerControlView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playerControlView = findViewById(R.id.player_control_view)
    }

    fun handleChooserClick(view: View) {
        val intent = Intent()
            .setType("audio/*")
            .setAction(Intent.ACTION_OPEN_DOCUMENT)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        resultData?.data?.also { myUri ->
            val player = SimpleExoPlayer.Builder(baseContext).build()
            playerControlView?.player = player
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                baseContext,
                Util.getUserAgent(baseContext, "yourApplicationName")
            )
// This is the MediaSource representing the media to be played.
            // This is the MediaSource representing the media to be played.
            val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(myUri)
// Prepare the player with the source.
            // Prepare the player with the source.
            player.prepare(videoSource)

        }
    }
}
