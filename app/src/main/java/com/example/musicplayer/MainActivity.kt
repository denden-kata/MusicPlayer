package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var elapsedTimeLabel: TextView
    private lateinit var remainingTimeLabel: TextView

    private lateinit var volumeBar: SeekBar
    private lateinit var positionBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mp = MediaPlayer.create(this, R.raw.test01)
        playButton = findViewById(R.id.playBtn)
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel)
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel)

        // Media Player の初期化
        mp.isLooping
        mp.seekTo(0)
        mp.setVolume(0.5f, 0.5f)
    }

    public fun playBtnClick(view: View){
        if(!mp.isPlaying) {
            // 停止中なら再生する
            mp.start()
            playBtn.setBackgroundResource(R.drawable.stop)

        } else {
            // 再生中なら停止する
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.play)
        }
    }
}
