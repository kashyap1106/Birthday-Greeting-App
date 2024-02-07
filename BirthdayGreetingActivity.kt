package com.example.birthdaygreet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.media.MediaPlayer

class BirthdayGreetingActivity : AppCompatActivity() {
    companion object {
        const val NAME_EXTRA = "name_extra"
    }

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greeting)

        val name = intent.getStringExtra(NAME_EXTRA)
        val birthdaygreet: TextView = findViewById(R.id.birthdaygreet)
        birthdaygreet.text = "Happy Birthday\n $name❤️"

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.birthday)

        // Start playing audio
        mediaPlayer?.start()

        // Set a listener to release the media player when audio finishes playing
        mediaPlayer?.setOnCompletionListener {
            releaseMediaPlayer()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Stop and release the audio playback when the back button is pressed
        releaseMediaPlayer()
    }

    override fun onStop() {
        super.onStop()
        // Stop and release the audio playback when the activity is stopped (navigating to another activity)
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
    }
}
