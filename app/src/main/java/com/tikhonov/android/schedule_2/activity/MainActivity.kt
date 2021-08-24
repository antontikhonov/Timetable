package com.tikhonov.android.schedule_2.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tikhonov.android.schedule_2.R
import com.tikhonov.android.schedule_2.ThemeSetter
import java.util.*

const val SHARED_PREFERENCES_FILE_NAME = "my_settings";
const val IMAGE_BACKGROUND = "IMAGE";
const val BUTTON_COLOR = "BUTTONBACK";
const val BUTTON_TEXT_COLOR = "BUTTONTEXT";
const val LINES_COLOR = "LINES";

class MainActivity : AppCompatActivity() {

    private lateinit var todayButton: Button
    private lateinit var tomorrowButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.mainBackgroundImageView)
        progressBar = findViewById(R.id.progressBar)
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE)

        if (!sharedPreferences.contains(LINES_COLOR)) {
            sharedPreferences.edit()
                .putString(IMAGE_BACKGROUND, "alina")
                .putString(BUTTON_COLOR, "rounded_alina")
                .putString(BUTTON_TEXT_COLOR, "#D9C6BF")
                .putString(LINES_COLOR, "#C88548")
                .apply()
        }

        todayButton = findViewById<Button>(R.id.todayButton).also {
            it.setOnClickListener {
                var numberDay = GregorianCalendar.DAY_OF_WEEK - 1
                if (numberDay == 0) {
                    numberDay = 1
                    Toast.makeText(
                        this,
                        "Сегодня воскресенье, поэтому вот тебе понедельник",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                progressBar.visibility = View.VISIBLE
                startActivity(
                    Intent(this, DayActivity::class.java).putExtra(
                        DAY_NAME,
                        numberDay.toString()
                    )
                )
            }
        }
        tomorrowButton = findViewById<Button>(R.id.tomorrowButton).also {
            it.setOnClickListener {
                var numberDay = GregorianCalendar.DAY_OF_WEEK
                if (numberDay == 7) {
                    numberDay = 1
                    Toast.makeText(
                        this,
                        "Завтра воскресенье, поэтому вот тебе понедельник",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                progressBar.visibility = View.VISIBLE
                startActivity(
                    Intent(this, DayActivity::class.java).putExtra(
                        DAY_NAME,
                        numberDay.toString()
                    )
                )
            }
        }
        findViewById<ImageView>(R.id.settingsImageView).setOnClickListener {
            progressBar.visibility = View.VISIBLE
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        ThemeSetter.setImage(
            this,
            packageName,
            sharedPreferences.getString(IMAGE_BACKGROUND, null)!!,
            imageView
        )
        ThemeSetter.setButtonsTextColor(
            listOf(todayButton, tomorrowButton),
            sharedPreferences.getString(BUTTON_TEXT_COLOR, null)!!
        )
        ThemeSetter.setButtonsColor(
            this,
            packageName,
            sharedPreferences.getString(BUTTON_COLOR, null)!!,
            listOf(todayButton, tomorrowButton)
        )
    }

    override fun onStop() {
        progressBar.visibility = View.GONE
        super.onStop()
    }
}