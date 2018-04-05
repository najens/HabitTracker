package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        insertHabit();
        displayDatabaseInfo();
    }

    // Method to create a new habit from UI activity
    private void insertHabit() {

        Long timeMillis = System.currentTimeMillis();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_USER_ID, 1);
        values.put(HabitEntry.COLUMN_HABIT_ID, HabitEntry.HABIT_EXERCISE);
        values.put(HabitEntry.COLUMN_HABIT_DATE, timeMillis);
        values.put(HabitEntry.COLUMN_HABIT_COMPLETED, HabitEntry.COMPLETED_TRUE);
        values.put(HabitEntry.COLUMN_PRACTICE_TIME, 30);

        //displayDatabaseInfo();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.v("CatalogActivity", "New row ID" + newRowId);
    }

    private Cursor cursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_USER_ID,
                HabitEntry.COLUMN_HABIT_ID,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_COMPLETED,
                HabitEntry.COLUMN_PRACTICE_TIME
        };

        return db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    // Method to read content in database
    private void displayDatabaseInfo() {
        Cursor cursor = cursor();

        TextView displayView = (TextView) findViewById(R.id.habit_text_view);

        try {
            // Display the number of rows in the Cursor
            displayView.setText(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_USER_ID + " - " +
                    HabitEntry.COLUMN_HABIT_ID + " - " +
                    HabitEntry.COLUMN_HABIT_DATE + " - " +
                    HabitEntry.COLUMN_HABIT_COMPLETED + " - " +
                    HabitEntry.COLUMN_PRACTICE_TIME + "\n"
            );

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int userColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_USER_ID);
            int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int completedColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_COMPLETED);
            int timeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_PRACTICE_TIME);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                int currentUser = cursor.getInt(userColumnIndex);
                int currentHabit = cursor.getInt(habitColumnIndex);
                int currentdate = cursor.getInt(dateColumnIndex);
                int currentCompleted = cursor.getInt(completedColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);

                displayView.append("\n" + currentID + " - " +
                        currentUser + " - " +
                        currentHabit + " - " +
                        currentdate + " - " +
                        currentCompleted + " - " +
                        currentTime
                );
            }
        } finally {
            cursor.close();
        }
    }
}
