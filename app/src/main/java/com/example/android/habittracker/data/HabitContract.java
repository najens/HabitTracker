package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Nate on 10/30/2017.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_HABIT_ID = "habit_id";
        public static final String COLUMN_HABIT_DATE = "date";
        public static final String COLUMN_HABIT_COMPLETED = "completed";
        public static final String COLUMN_PRACTICE_TIME = "time";

        /**
         * Possible values for the habit_id
         */
        public static final int HABIT_WAKE_UP = 1;
        public static final int HABIT_MAKE_BED = 2;
        public static final int HABIT_EXERCISE = 3;
        public static final int HABIT_FLOSS = 4;
        public static final int HABIT_JOURNAL = 5;
        public static final int HABIT_PRACTICE_INSTRUMENT = 6;
        public static final int HABIT_READ = 7;

        /**
         * Possible values for completed
         */
        public static final int COMPLETED_TRUE = 1;
        public static final int COMPLETED_FALSE = 0;
    }
}
