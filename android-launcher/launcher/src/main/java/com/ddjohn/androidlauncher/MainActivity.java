package com.ddjohn.androidlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;
import static com.ddjohn.androidlauncher.LOG.*;

/**
 * Created by David Johansson on 2017-06-10.
 */

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_INTENT = "android.intent.action.MAIN";
    private static final String LAUNCHER_CATEGORY = "android.intent.category.LAUNCHER";

    public MainActivity() {
        super();
        APPLICATION("Android-Launcher");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        METHOD("MainActivity::onCreate()");

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);

        INFO("Query package manager for launchable activities");

        Intent intent = new Intent(MAIN_INTENT, null);
        intent.addCategory(LAUNCHER_CATEGORY);

        final PackageManager mgr = this.getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, 0);

        final List<ApplicationInfo> apps = new ArrayList<ApplicationInfo>();
        for(ResolveInfo info : list) {
            apps.add(new ApplicationInfo(mgr, info));
        }

        ((GridView)this.findViewById(R.id.grid)).setAdapter(new ApplicationAdapter(MainActivity.this, apps));
    }
}
