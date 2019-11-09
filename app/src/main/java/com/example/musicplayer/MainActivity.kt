package com.example.musicplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var elapsedTimeLabel: TextView
    private lateinit var remainingTimeLabel: TextView

    private lateinit var volumeBar: SeekBar
    private lateinit var positionBar: SeekBar
    private var totalTime: Int = 0

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
        // 楽曲ファイルの長さをミリ秒で取得
        totalTime = mp.duration

        // 再生位置
        positionBar = findViewById(R.id.positionBar)
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                // つまみが動かされたときに呼ばれる
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if(fromUser) {
                        mp.seekTo(progress)
                        positionBar.progress = progress
                    }
                }
                // つまみがタッチされたときに呼ばれる
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                // つまみを離したときに呼ばれる
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                }
            }
        )


        // 音量調節
        volumeBar = findViewById(R.id.volumeBar)
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                // つまみが動かされたときに呼ばれる
                override fun onProgressChanged(
                    seekBar: SeekBar?, volumePosition: Int, isChangeVolume: Boolean
                ) {
                    val volumeLevel = volumePosition / 100f
                    mp.setVolume(volumeLevel, volumeLevel)
                }

                // つまみがタッチされたときに呼ばれる
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                // つまみを離したときに呼ばれる
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )


        // 各更新を別Threadで動作させる
        Thread(Runnable {
            kotlin.run {
                lateinit var message:Message
                message.what = mp.currentPosition
                handler.sendMessage(message)
                Thread.sleep(1000)
            }
        })

    }


    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val currentPosition = msg.what
        }
    }


    fun playBtnClick(view: View) {
        if (!mp.isPlaying) {
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
