package se.avelon.daidalos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.avelon.daidalos.R;

public class CellularFragment extends AbstractFragment {
    private static final String TAG = CellularFragment.class.getSimpleName();

    public String getTitle() {return "Cellular";};
    public int getIcon() {return R.drawable.cellular;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.battery, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); }
}