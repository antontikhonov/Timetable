package com.tikhonov.android.schedule_2.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.tikhonov.android.schedule_2.R
import com.tikhonov.android.schedule_2.ThemeSetter
import com.tikhonov.android.schedule_2.ViewPagerAdapter

const val DAY_NAME = "dayName"

class DayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)
        var currentItem = 0
        when (intent.extras?.getString(DAY_NAME)) {
            "1" -> currentItem = 1002
            "2" -> currentItem = 1003
            "3" -> currentItem = 1004
            "4" -> currentItem = 1005
            "5" -> currentItem = 1006
            "6" -> currentItem = 1007
        }
        findViewById<ViewPager>(R.id.viewPager).also {
            it.adapter = ViewPagerAdapter(supportFragmentManager)
            it.currentItem = currentItem
        }
    }

    override fun onResume() {
        super.onResume()
        val imageView = findViewById<ImageView>(R.id.dayBackground)
        ThemeSetter.setImage(
            this,
            packageName,
            MainActivity.sharedPreferences.getString(IMAGE_BACKGROUND, "alina")!!,
            imageView
        )
    }

    fun back(view: View) {
        onBackPressed()
    }
}