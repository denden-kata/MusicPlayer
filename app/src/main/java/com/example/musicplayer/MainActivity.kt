package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton: Button = findViewById(R.id.playBtn)
        val elapsedTimeLabel: TextView = findViewById(R.id.elapsedTimeLabel)
        val remainingTimeLabel: TextView = findViewById(R.id.remainingTimeLabel)
        val volumeBar: SeekBar
        val positionBar: SeekBar
        val mp: MediaPlayer


    }
}
