package com.example.fruitapp

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class BoxingActivity : AppCompatActivity() {
    private lateinit var videoView1: VideoView
    private lateinit var videoView2: VideoView
    private lateinit var videoView3: VideoView
    private lateinit var videoView4: VideoView
    private lateinit var videoView5: VideoView
    private lateinit var playButton1: Button
    private lateinit var playButton2: Button
    private lateinit var playButton3: Button
    private lateinit var playButton4: Button
    private lateinit var playButton5: Button
    private var isPlaying1 = false
    private var isPlaying2 = false
    private var isPlaying3 = false
    private var isPlaying4 = false
    private var isPlaying5 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boxing)

        //inisialisasi videoview
        videoView1 = findViewById(R.id.videoView1)
        videoView2 = findViewById(R.id.videoView2)
        videoView3 = findViewById(R.id.videoView3)
        videoView4 = findViewById(R.id.videoView4)
        videoView5 = findViewById(R.id.videoView5)

        //inisialisasi button
        playButton1 = findViewById(R.id.play1)
        playButton2 = findViewById(R.id.play2)
        playButton3 = findViewById(R.id.play3)
        playButton4 = findViewById(R.id.play4)
        playButton5 = findViewById(R.id.play5)


//        //inisialisasi judul video
//        val judul1 = findViewById<VideoView>(R.id.judul1)
//        val judul2 = findViewById<VideoView>(R.id.judul2)
//        val judul3 = findViewById<VideoView>(R.id.judul3)
//        val judul4 = findViewById<VideoView>(R.id.judul4)
//        val judul5 = findViewById<VideoView>(R.id.judul5)


        val videoUri1 = Uri.parse("android.resource://" + packageName + "/" + R.raw.boxing_hook)
        val videoUri2 = Uri.parse("android.resource://" + packageName + "/" + R.raw.boxing_jab)
        val videoUri3 = Uri.parse("android.resource://" + packageName + "/" + R.raw.boxing_jabstrike)
        val videoUri4 = Uri.parse("android.resource://" + packageName + "/" + R.raw.boxing_strike)
        val videoUri5 = Uri.parse("android.resource://" + packageName + "/" + R.raw.boxing_uppercut)


        videoView1.setVideoURI(videoUri1)
        videoView2.setVideoURI(videoUri2)
        videoView3.setVideoURI(videoUri3)
        videoView4.setVideoURI(videoUri4)
        videoView5.setVideoURI(videoUri5)

        // Tambahkan OnClickListener ke VideoView
        videoView1.setOnClickListener { playVideo(videoView1, playButton1, isPlaying1) }
        videoView2.setOnClickListener { playVideo(videoView2, playButton2, isPlaying2) }
        videoView3.setOnClickListener { playVideo(videoView3, playButton3, isPlaying3) }
        videoView4.setOnClickListener { playVideo(videoView4, playButton4, isPlaying4) }
        videoView5.setOnClickListener { playVideo(videoView5, playButton5, isPlaying5) }


        // Tambahkan OnClickListener ke tombol Play
        playButton1.setOnClickListener {
            isPlaying1 = !isPlaying1
            playVideo(videoView1, playButton1, isPlaying1)
        }

        playButton2.setOnClickListener {
            isPlaying2 = !isPlaying2
            playVideo(videoView2, playButton2, isPlaying2)
        }

        playButton3.setOnClickListener {
            isPlaying3 = !isPlaying3
            playVideo(videoView3, playButton3, isPlaying3)
        }

        playButton4.setOnClickListener {
            isPlaying4 = !isPlaying4
            playVideo(videoView4, playButton4, isPlaying4)
        }

        playButton5.setOnClickListener {
            isPlaying5 = !isPlaying5
            playVideo(videoView5, playButton5, isPlaying5)
        }
    }

    private fun playVideo(videoView: VideoView, playButton: Button, isPlaying: Boolean) {
        if (isPlaying) {
            videoView.pause() // Jeda video
            playButton.text = "Play"
        } else {
            videoView.start() // Mulai video
            playButton.text = "Pause"
        }
    }
}