package com.tikhonov.android.schedule_2.activity

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tikhonov.android.schedule_2.R
import com.tikhonov.android.schedule_2.ThemeSetter
import com.tikhonov.android.schedule_2.TimetableDatabaseHelper
import java.util.*

const val fileName = "mysettings";
const val IMAGE_BACKGROUND = "IMAGE";
const val BUTTON_COLOR = "BUTTONBACK";
const val BUTTON_TEXT_COLOR = "BUTTONTEXT";
const val LINES_COLOR = "LINES";

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var db: SQLiteDatabase
        lateinit var sharedPreferences: SharedPreferences
    }

    private val buttons = arrayListOf<Button>()
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons.add(findViewById(R.id.button1))
        buttons.add(findViewById(R.id.button2))
        imageView = findViewById(R.id.image_main)
        progressBar = findViewById(R.id.progress_bar)

        db = TimetableDatabaseHelper(this).writableDatabase
        sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE)

        if (!sharedPreferences.contains(LINES_COLOR)) {
            sharedPreferences.edit()
                .putString(IMAGE_BACKGROUND, "alina")
                .putString(BUTTON_COLOR, "rounded_alina")
                .putString(BUTTON_TEXT_COLOR, "#D9C6BF")
                .putString(LINES_COLOR, "#C88548")
                .apply()
        }
    }

    override fun onResume() {
        super.onResume()
        ThemeSetter.setImage(
            this,
            packageName,
            sharedPreferences.getString(IMAGE_BACKGROUND, null)!!,
            imageView
        );
        ThemeSetter.setButtonsTextColor(
            buttons,
            sharedPreferences.getString(BUTTON_TEXT_COLOR, null)!!
        )
        ThemeSetter.setButtonsColor(
            this,
            packageName, sharedPreferences.getString(BUTTON_COLOR, null)!!,
            buttons
        )
    }

    override fun onStop() {
        progressBar.visibility = View.GONE
        super.onStop()
    }

    fun toToday(view: View) {
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

    fun toTomorrow(view: View) {
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

    fun toSettings(view: View) {
        progressBar.visibility = View.VISIBLE
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}