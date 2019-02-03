package com.ddjohn.androidlauncher;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import static com.ddjohn.androidlauncher.LOG.*;

/**
 * Created by David Johansson on 2017-06-10.
 */

public class ApplicationInfo {
    String label = null;
    String name = null;
    Drawable icon = null;

    ApplicationInfo(PackageManager mgr, ResolveInfo info) {
        METHOD("ApplicationInfo::ApplicationInfo()");

        this.label = info.activityInfo.loadLabel(mgr).toString();
        this.name = info.activityInfo.packageName;
        this.icon = info.loadIcon(mgr);

        INFO("application = {label:" + label + "},{name: " + name + "}");
    }

    ApplicationInfo(Drawable icon, String label, String name) {
        METHOD("ApplicationInfo::ApplicationInfo()");

        this.label = label;
        this.name = name;
        this.icon = icon;

        INFO("application = {label:" + label + "},{name: " + name + "}");
    }
}
