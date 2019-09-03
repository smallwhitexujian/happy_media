package com.pince.happy_media

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hapi.player.video.contronller.DefaultController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mHappyVideoPlayer.addController(DefaultController(this))

        mHappyVideoPlayer.setUp(Uri.parse("https://media.w3.org/2010/05/sintel/trailer.mp4"), null, false)

        b2.setOnClickListener {

        }
        btnStart.setOnClickListener {
        }

        btnPause.setOnClickListener {
            mHappyVideoPlayer.pause()
        }

        btnResume.setOnClickListener {
            mHappyVideoPlayer.resume()
        }

    }


}
