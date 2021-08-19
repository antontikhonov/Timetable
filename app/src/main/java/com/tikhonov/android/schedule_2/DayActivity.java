package com.tikhonov.android.schedule_2;

import static com.tikhonov.android.schedule_2.MainActivityKt.IMAGE_BACKGROUND;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DayActivity extends AppCompatActivity {
    public static final String DAY_NAME = "dayName";

    @Override
    public void onResume() {
        super.onResume();
        ImageView imageView = (ImageView) findViewById(R.id.dayBackground);
        ThemeSetter.Companion.setImage(this, getPackageName(), MainActivity.sharedPreferences.getString(IMAGE_BACKGROUND, "alina"), imageView);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        int currentItem = 0;
        String day = getIntent().getExtras().getString(DAY_NAME);
        switch (day) {
            case "1":
                currentItem = 1002;
                break;
            case "2":
                currentItem = 1003;
                break;
            case "3":
                currentItem = 1004;
                break;
            case "4":
                currentItem = 1005;
                break;
            case "5":
                currentItem = 1006;
                break;
            case "6":
                currentItem = 1007;
                break;
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentItem);
    }
}