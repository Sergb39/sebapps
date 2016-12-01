package com.seb.debuger;

import android.util.Log;

import java.util.ArrayList;

/**
 * SempleApp
 * Created by sergb on 30-Nov-16.
 */

public class DEBUG {
    protected static boolean DEBUG_MOD = true;
    private static final String TAG = " ► ";
    private static boolean showCodeHyperLink = true;
    private static ArrayList<Timer> mTimerHolder = new ArrayList<>();

    /**
     * Prints debug message if debug is true
     */
    public static void d(Object message) {
        d(null, message);
    }

    public static void d(String tag, Object message) {
        if (!DEBUG_MOD) {
            return;
        }
        String print;
        if (message == null) {
            print = "Message is null";
        } else if (message instanceof String) {
            print = (String) message;
        } else {
            print = String.valueOf(message);
        }
        String msg = trace(Thread.currentThread().getStackTrace(), 3);
        if (tag == null) {
            Log.d("DEBUG" + TAG, print + msg);
        } else {
            Log.d("DEBUG " + tag, print + msg);
        }
    }


    public static void i(Object message) {
        i(null, message);
    }

    public static void i(String tag, Object message) {
        if (!DEBUG_MOD) {
            return;
        }
        String print;
        if (message == null) {
            print = "Message is null";
        } else if (message instanceof String) {
            print = (String) message;
        } else {
            print = String.valueOf(message);
        }
        String msg = trace(Thread.currentThread().getStackTrace(), 3);
        if (tag == null) {
            Log.i("INFO" + TAG, print + msg);
        } else {
            Log.i("INFO " + tag, print + msg);
        }
    }


    public static void w(Object message) {
        w(null, message);
    }

    public static void w(String tag, Object message) {
        if (!DEBUG_MOD) {
            return;
        }
        String print;
        if (message == null) {
            print = "Message is null";
        } else if (message instanceof String) {
            print = (String) message;
        } else {
            print = String.valueOf(message);
        }
        String msg = trace(Thread.currentThread().getStackTrace(), 3);
        if (tag == null) {
            Log.w("WARNING" + TAG, print + msg);
        } else {
            Log.w("WARNING " + tag, print + msg);
        }
    }

    public static void e(Object message) {
        e(null, message);
    }

    public static void e(String tag, Object message) {
        if (!DEBUG_MOD) {
            return;
        }
        String print;
        if (message == null) {
            print = "Message is null";
        } else if (message instanceof String) {
            print = (String) message;
        } else {
            print = String.valueOf(message);
        }
        String msg = trace(Thread.currentThread().getStackTrace(), 3);
        if (tag == null) {
            Log.e("ERROR" + TAG, print + msg);
        } else {
            Log.e("ERROR " + tag, print + msg);
        }
    }

    /**
     * get link to code
     *
     * @param e current stack trace
     * @return get link to code
     */
    private static String trace(final StackTraceElement e[], final int level) {
        if (e != null && e.length >= level && showCodeHyperLink) {
            final StackTraceElement s = e[level];

            String fullClassName = s.getClassName();
            String className = fullClassName.substring(fullClassName
                    .lastIndexOf(".") + 1);
            String lineNumber = String
                    .valueOf(s.getLineNumber());

            return "        » " + fullClassName + "." + s.getMethodName() + "("
                    + className + ".java:" + lineNumber + ")";

        }
        return "";
    }


    /**
     * starting Timer. can start multiple instances.
     *
     * @param id id for this timer, must be unique.
     */
    public static void startTimer(int id) {
        Timer t = new Timer(System.currentTimeMillis(), id);
        d("TIMER: " + t.toString());
        mTimerHolder.add(t);
    }

    /**
     * stop timer previously created.
     *
     * @param id id of a timer to stop.
     */
    public static void stopTimer(int id) {
        Timer t = null;
        int p = -1;
        for (int i = 0; i < mTimerHolder.size(); i++) {
            if (mTimerHolder.get(i).getId() == id) {
                t = mTimerHolder.get(i);
                p = i;
                break;
            }
        }
        if (t == null || p == -1) {
            e("Timer ID not exist");
            return;
        }

        long totalTime = System.currentTimeMillis() - t.getStartTime();
        d("TIMER: " + t.id + " End time: " + totalTime);
        mTimerHolder.remove(p);
    }

    private static class Timer {
        private long startTime;
        private int id;

        Timer(long startTime, int id) {
            this.startTime = startTime;
            this.id = id;
        }

        long getStartTime() {
            return startTime;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Timer{" +
                    "startTime=" + startTime +
                    ", id=" + id +
                    '}';
        }
    }
}
