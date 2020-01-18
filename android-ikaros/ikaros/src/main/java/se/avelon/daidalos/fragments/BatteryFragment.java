package se.avelon.daidalos.fragments;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import se.avelon.daidalos.R;

public class BatteryFragment extends AbstractFragment {
    private static final String TAG = BatteryFragment.class.getSimpleName();

    private EditText charging;
    private EditText capacity;
    private EditText counter;
    private EditText average;
    private EditText now;
    private EditText status;
    private EditText energy;

    public String getTitle() {return "Battery";};
    public int getIcon() {return R.drawable.battery;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.battery, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        charging = (EditText)view.findViewById(R.id.charging);
        capacity = (EditText)view.findViewById(R.id.capacity);
        counter = (EditText)view.findViewById(R.id.counter);
        average = (EditText)view.findViewById(R.id.average);
        now = (EditText)view.findViewById(R.id.now);
        status = (EditText)view.findViewById(R.id.status);
        energy = (EditText)view.findViewById(R.id.energy);

        BatteryManager manager = (BatteryManager)this.getActivity().getSystemService(Context.BATTERY_SERVICE);

        charging.setText("Charging: " + manager.isCharging());
        capacity.setText("Capacity: " + manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) + "%");
         counter.setText("Counter: "  + manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER));
         average.setText("Average: "  + manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE));
             now.setText("Now: "      + manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW));
          status.setText("Status: "   + manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS));
          energy.setText("Energy: "   + manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER));
    }
}