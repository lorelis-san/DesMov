package com.mi.myapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val videoView: VideoView = findViewById(R.id.videoView)

        videoView.setVideoPath("android.resource://${packageName}/${R.raw.splash}")
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.start()
        }

        videoView.setOnCompletionListener {
            val intent = Intent(this@SplashActivity, ScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed({

            if (!videoView.isPlaying) {
                val intent = Intent(this@SplashActivity, ScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 4000)
    }
}
