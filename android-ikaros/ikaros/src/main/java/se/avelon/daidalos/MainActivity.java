package se.avelon.daidalos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


import se.avelon.daidalos.fragments.DebugFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        this.setSupportActionBar((Toolbar) this.findViewById(R.id.toolbar));

        /*
        new Thread() {
            public void run() {
                ExtractDecodeEditEncodeMuxTest test = new ExtractDecodeEditEncodeMuxTest();
                test.setContext(MainActivity.this);
                try {
                    test.testExtractDecodeEditEncodeMuxAudioVideo();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }.start();

        if(true) return;
*/
        ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);


/*
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        WindowManager.LayoutParams params;
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 0;
        getWindow().setAttributes(params);
*/

        Debug.d(TAG, "Request permissions");
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE,
                        Manifest.permission_group.MICROPHONE,
                        Manifest.permission_group.STORAGE,
                },
                7);

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cleared your log", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                DebugFragment.clear();
            }
        });

        final PagerAdapter adapter = new PagerAdapter(this, getSupportFragmentManager());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Debug.d(TAG, "Results from permission request");
        switch (requestCode) {
            case 7: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Debug.d(TAG, "Got my permissions granted");

                } else {
                    Debug.e(TAG, "User did not approve the permissions");
                }
                return;
            }
        }
    }
}
