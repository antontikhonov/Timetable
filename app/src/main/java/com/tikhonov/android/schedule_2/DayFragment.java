package com.tikhonov.android.schedule_2;

import static com.tikhonov.android.schedule_2.activity.MainActivityKt.LINES_COLOR;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tikhonov.android.schedule_2.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DayFragment extends Fragment {
    private ArrayList<TextView> pairs = new ArrayList<>();
    private TextView dayName;
    ArrayList<View> lines = new ArrayList<>();
    private String day;
    Cursor cursor;
    FirebaseDatabase database;
    ArrayList<DatabaseReference> dbReferences = new ArrayList<>();
    DatabaseReference dayReference;

    public DayFragment(String day) {
        this.day = day;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lines.add(view.findViewById(R.id.line_1));
        lines.add(view.findViewById(R.id.line_2));
        lines.add(view.findViewById(R.id.line_3));
        lines.add(view.findViewById(R.id.line_4));
        lines.add(view.findViewById(R.id.line_5));
        lines.add(view.findViewById(R.id.line_6));
        lines.add(view.findViewById(R.id.line_7));
        lines.add(view.findViewById(R.id.line_8));
        lines.add(view.findViewById(R.id.line_9));
        lines.add(view.findViewById(R.id.line_10));
        lines.add(view.findViewById(R.id.line_11));
        lines.add(view.findViewById(R.id.line_12));
        lines.add(view.findViewById(R.id.line_13));
        lines.add(view.findViewById(R.id.line_14));

        pairs.add((TextView) view.findViewById(R.id.m_a));
        pairs.add((TextView) view.findViewById(R.id.m_b));
        pairs.add((TextView) view.findViewById(R.id.m_c));
        pairs.add((TextView) view.findViewById(R.id.m_d));
        pairs.add((TextView) view.findViewById(R.id.m_e));
        pairs.add((TextView) view.findViewById(R.id.m_f));
        pairs.add((TextView) view.findViewById(R.id.m_g));
        pairs.add((TextView) view.findViewById(R.id.m_h));
        pairs.add((TextView) view.findViewById(R.id.m_i));
        pairs.add((TextView) view.findViewById(R.id.m_j));
        pairs.add((TextView) view.findViewById(R.id.m_k));
        pairs.add((TextView) view.findViewById(R.id.m_l));
        pairs.add((TextView) view.findViewById(R.id.m_m));
        pairs.add((TextView) view.findViewById(R.id.m_n));
        dayName = (TextView) view.findViewById(R.id.name_of_day);

        database = FirebaseDatabase.getInstance();
        dbReferences.add(database.getReference(day).child("1_on"));
        dbReferences.add(database.getReference(day).child("1_under"));
        dbReferences.add(database.getReference(day).child("2_on"));
        dbReferences.add(database.getReference(day).child("2_under"));
        dbReferences.add(database.getReference(day).child("3_on"));
        dbReferences.add(database.getReference(day).child("3_under"));
        dbReferences.add(database.getReference(day).child("4_on"));
        dbReferences.add(database.getReference(day).child("4_under"));
        dbReferences.add(database.getReference(day).child("5_on"));
        dbReferences.add(database.getReference(day).child("5_under"));
        dbReferences.add(database.getReference(day).child("6_on"));
        dbReferences.add(database.getReference(day).child("6_under"));
        dbReferences.add(database.getReference(day).child("7_on"));
        dbReferences.add(database.getReference(day).child("7_under"));
        dayReference = database.getReference(day).child("day");

        ThemeSetter.Companion.setBackgroundViews(lines, MainActivity.sharedPreferences.getString(LINES_COLOR, "#C88548"));

        cursor = MainActivity.db.query("DAY",
                new String[]{"ON1", "UNDER1", "ON2", "UNDER2", "ON3", "UNDER3", "ON4", "UNDER4", "ON5", "UNDER5", "ON6", "UNDER6", "ON7", "UNDER7", "NAME"},
                "NAME = ?", new String[]{day}, null, null, null);
        cursor.moveToFirst();
        dayName.setText(cursor.getString(14));

        for (int i = 0; i < pairs.size(); i++) {
            String value = cursor.getString(i);
            if (!value.equals("")) {
                switch (i) {
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                    case 8:
                    case 10:
                    case 12:
                        if (pairs.get(i + 1).getVisibility() == View.VISIBLE) {
                            lines.get(i).setVisibility(View.VISIBLE);
                        }
                        pairs.get(i).setVisibility(View.VISIBLE);
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 9:
                    case 11:
                    case 13:
                        if (pairs.get(i - 1).getVisibility() == View.VISIBLE) {
                            lines.get(i - 1).setVisibility(View.VISIBLE);
                        }
                        pairs.get(i).setVisibility(View.VISIBLE);
                        break;
                }
            }
            pairs.get(i).setText(value);
        }
        cursor.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day, container, false);
    }

    public void recoverOnSpace(int numberLine, int checkingForExistence, String numberPair, String value) {
        ContentValues dayValues = new ContentValues();
        dayValues.put(numberPair, value);
        MainActivity.db.update("DAY", dayValues, "NAME = ?", new String[]{day});
        lines.get(numberLine).setVisibility(View.GONE);
        pairs.get(checkingForExistence).setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < dbReferences.size(); i++) {
            final int finalI = i;
            dbReferences.get(i).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot snapshot) {
                    String value = snapshot.getValue(String.class);
                    assert value != null;
                    if (value.equals("")) {
                        switch (finalI) {
                            case 0:
                                recoverOnSpace(0, finalI, "ON1", value);
                                break;
                            case 1:
                                recoverOnSpace(0, finalI, "UNDER1", value);
                                break;
                            case 2:
                                recoverOnSpace(2, finalI, "ON2", value);
                                break;
                            case 3:
                                recoverOnSpace(2, finalI, "UNDER2", value);
                                break;
                            case 4:
                                recoverOnSpace(4, finalI, "ON3", value);
                                break;
                            case 5:
                                recoverOnSpace(4, finalI, "UNDER3", value);
                                break;
                            case 6:
                                recoverOnSpace(6, finalI, "ON4", value);
                                break;
                            case 7:
                                recoverOnSpace(6, finalI, "UNDER4", value);
                                break;
                            case 8:
                                recoverOnSpace(8, finalI, "ON5", value);
                                break;
                            case 9:
                                recoverOnSpace(8, finalI, "UNDER5", value);
                                break;
                            case 10:
                                recoverOnSpace(10, finalI, "ON6", value);
                                break;
                            case 11:
                                recoverOnSpace(10, finalI, "UNDER6", value);
                                break;
                            case 12:
                                recoverOnSpace(12, finalI, "ON7", value);
                                break;
                            case 13:
                                recoverOnSpace(12, finalI, "UNDER7", value);
                                break;
                        }
                    } else {
                        switch (finalI) {
                            case 0:
                                recoverOnPairs(0, finalI, "ON1", value);
                                break;
                            case 1:
                                recoverUnderPairs(0, finalI, "UNDER1", value);
                                break;
                            case 2:
                                recoverOnPairs(2, finalI, "ON2", value);
                                break;
                            case 3:
                                recoverUnderPairs(2, finalI, "UNDER2", value);
                                break;
                            case 4:
                                recoverOnPairs(4, finalI, "ON3", value);
                                break;
                            case 5:
                                recoverUnderPairs(4, finalI, "UNDER3", value);
                                break;
                            case 6:
                                recoverOnPairs(6, finalI, "ON4", value);
                                break;
                            case 7:
                                recoverUnderPairs(6, finalI, "UNDER4", value);
                                break;
                            case 8:
                                recoverOnPairs(8, finalI, "ON5", value);
                                break;
                            case 9:
                                recoverUnderPairs(8, finalI, "UNDER5", value);
                                break;
                            case 10:
                                recoverOnPairs(10, finalI, "ON6", value);
                                break;
                            case 11:
                                recoverUnderPairs(10, finalI, "UNDER6", value);
                                break;
                            case 12:
                                recoverOnPairs(12, finalI, "ON7", value);
                                break;
                            case 13:
                                recoverUnderPairs(12, finalI, "UNDER7", value);
                                break;
                        }
                    }
                    pairs.get(finalI).setText(value);
                }

                @Override
                public void onCancelled(@NotNull DatabaseError error) {
                }
            });
        }
        dayReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                dayName.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void recoverOnPairs(int numberLine, int checkingForExistence, String numberPair, String value) {
        if (pairs.get(checkingForExistence + 1).getVisibility() == View.VISIBLE) {
            lines.get(numberLine).setVisibility(View.VISIBLE);
        }
        ContentValues dayValues = new ContentValues();
        dayValues.put(numberPair, value);
        MainActivity.db.update("DAY", dayValues, "NAME = ?", new String[]{day});
        pairs.get(checkingForExistence).setVisibility(View.VISIBLE);
    }

    public void recoverUnderPairs(int numberLine, int checkingForExistence, String numberPair, String value) {
        if (pairs.get(checkingForExistence - 1).getVisibility() == View.VISIBLE) {
            lines.get(numberLine).setVisibility(View.VISIBLE);
        }
        ContentValues dayValues = new ContentValues();
        dayValues.put(numberPair, value);
        MainActivity.db.update("DAY", dayValues, "NAME = ?", new String[]{day});
        pairs.get(checkingForExistence).setVisibility(View.VISIBLE);
    }
}
