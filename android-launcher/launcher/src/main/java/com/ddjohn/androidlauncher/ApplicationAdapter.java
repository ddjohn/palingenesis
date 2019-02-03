package com.ddjohn.androidlauncher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import static com.ddjohn.androidlauncher.LOG.*;

/**
 * Created by David Johansson on 2017-06-10.
 */

public class ApplicationAdapter extends BaseAdapter implements View.OnClickListener {
    private List<ApplicationInfo> apps = null;
    private Context ctx = null;

    public ApplicationAdapter(Context ctx, List<ApplicationInfo> apps) {
        METHOD("ApplicationAdapter::ApplicationAdapter()");

        this.apps = apps;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        METHOD("ApplicationAdapter::getCount()");

        return apps.size();
    }

    @Override
    public Object getItem(int no) {
        METHOD("ApplicationAdapter::getItem()");

        return apps.get(no);
    }

    @Override
    public long getItemId(int no) {
        METHOD("ApplicationAdapter::getItemId()");

        return no;
    }

    @Override
    public View getView(int no, View view, ViewGroup parent) {
        METHOD("ApplicationAdapter::getView()");

        LayoutInflater inflator = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View grid = inflator.inflate(R.layout.application_view, parent, false);

        ((TextView)grid.findViewById(R.id.label)).setText(apps.get(no).label);
        ((TextView)grid.findViewById(R.id.name)).setText(apps.get(no).name);
        ((ImageView)grid.findViewById(R.id.icon)).setImageDrawable(apps.get(no).icon);

        grid.setOnClickListener(this);
        return grid;
    }

    @Override
    public void onClick(View view) {
        METHOD("ApplicationAdapter::onClick()");

        String activity = ((TextView)view.findViewById(R.id.name)).getText().toString();
        Intent intent = ctx.getPackageManager().getLaunchIntentForPackage(activity);
        INFO("Sending intent: " + intent);
        ctx.startActivity(intent);
    }
}
