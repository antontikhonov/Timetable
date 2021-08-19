package com.tikhonov.android.schedule_2;

import static com.tikhonov.android.schedule_2.MainActivityKt.BUTTON_COLOR;
import static com.tikhonov.android.schedule_2.MainActivityKt.BUTTON_TEXT_COLOR;
import static com.tikhonov.android.schedule_2.MainActivityKt.IMAGE_BACKGROUND;
import static com.tikhonov.android.schedule_2.MainActivityKt.LINES_COLOR;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    public List<ImageView> list = new ArrayList<>();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        list.add((ImageView) findViewById(R.id.theme1));
        list.add((ImageView) findViewById(R.id.theme2));
        list.add((ImageView) findViewById(R.id.theme3));
        list.add((ImageView) findViewById(R.id.theme4));
        list.add((ImageView) findViewById(R.id.theme5));
        list.add((ImageView) findViewById(R.id.theme6));

        imageView = findViewById(R.id.image_settings);
    }

    @Override
    public void onResume() {
        super.onResume();
        ThemeSetter.Companion.setImage(this, getPackageName(), MainActivity.sharedPreferences.getString(IMAGE_BACKGROUND, "alina"), imageView);
        setSelectedScreenshots("alina", 0);
        setSelectedScreenshots("loli", 1);
        setSelectedScreenshots("electricity", 2);
        setSelectedScreenshots("iamgay", 3);
        setSelectedScreenshots("pornhub", 4);
        setSelectedScreenshots("satanism", 5);
    }

    public void setSelectedScreenshots(String nameTheme, int number) {
        if (MainActivity.sharedPreferences.getString(IMAGE_BACKGROUND, "").equals(nameTheme)) {
            ThemeSetter.Companion.setImage(this, getPackageName(), nameTheme + "_selected", list.get(number));
        } else {
            ThemeSetter.Companion.setImage(this, getPackageName(), nameTheme + "_theme", list.get(number));
        }
    }

    public void backDay(View view) {
        onBackPressed();
    }

    public void toInstagramChiburick(View view) {
        Uri uri = Uri.parse("http://instagram.com/_u/chiburick");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/chiburick")));
        }
    }

    public void toInstagramAntoha(View view) {
        Uri uri = Uri.parse("http://instagram.com/_u/antoshka_izh");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/antoshka_izh")));
        }
    }

    public void updateTheme(String image, String rounded_theme, String color_button, String color_lines) {
        MainActivity.sharedPreferences.edit()
                .putString(IMAGE_BACKGROUND, image)
                .putString(BUTTON_COLOR, rounded_theme)
                .putString(BUTTON_TEXT_COLOR, color_button)
                .putString(LINES_COLOR, color_lines)
                .apply();

        ThemeSetter.Companion.setImage(this, getPackageName(), image, imageView);
        setSelectedScreenshots("alina", 0);
        setSelectedScreenshots("loli", 1);
        setSelectedScreenshots("electricity", 2);
        setSelectedScreenshots("iamgay", 3);
        setSelectedScreenshots("pornhub", 4);
        setSelectedScreenshots("satanism", 5);
    }

    public void updateAlina(View view) {
        updateTheme("alina", "rounded_alina", "#D9C6BF", "#C88548");
    }

    public void updateLoli(View view) {
        updateTheme("loli", "rounded_loli", "#FDDDD2", "#E69BA2");
    }

    public void updateElectricity(View view) {
        updateTheme("electricity", "rounded_electricity", "#000000", "#EBD8EC");
    }

    public void updateIamgay(View view) {
        updateTheme("iamgay", "rounded_iamgay", "#C4E5EC", "#5E7984");
    }

    public void updatePornhub(View view) {
        updateTheme("pornhub", "rounded_pornhub", "#ffffff", "#f7971c");
    }

    public void updateSatanism(View view) {
        updateTheme("satanism", "rounded_satanism", "#ffffff", "#5D0C10");
    }
}