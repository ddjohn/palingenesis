package avelon.se.yumi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends MyActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "MyActivity");

        LinearLayout layout = (LinearLayout)findViewById(R.id.view);
        Log.e(TAG, "size=" + layout.getWidth() + "x" + layout.getHeight());

        layout.addView(new DrawView(this));
    }
}
