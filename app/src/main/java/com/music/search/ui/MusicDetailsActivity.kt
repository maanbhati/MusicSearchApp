package com.music.search.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.music.search.BuildConfig
import com.music.search.R
import com.music.search.utils.DurationConverter
import com.music.search.utils.ImageLoader.loadImage
import kotlinx.android.synthetic.main.activity_music_details.*

class MusicDetailsActivity : AppCompatActivity() {
    var bundle: Bundle? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_details)
        bundle = intent.extras
        if (BuildConfig.DEBUG && bundle == null) {
            error("Assertion failed")
        }

        if (bundle?.getString("image") != "") {
            loadImage(this, bundle?.getString("image"), R.drawable.ic_music, icon_track)
        }
        txt_track_name.text = bundle?.getString("name")
        txt_track_artist.text = bundle?.getString("artist")
        txt_plays.text = bundle?.getString("playCount")
        if (bundle?.getString("duration") != "") {
            try {
                txt_duration.text = bundle?.getString("duration")?.toLong()
                    ?.let { DurationConverter.getDurationInMinutesText(it) } + " Minutes"
            } catch (ignored: Exception) {
            }
        }
    }
}