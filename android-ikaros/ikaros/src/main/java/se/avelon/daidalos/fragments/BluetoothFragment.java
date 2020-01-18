package se.avelon.daidalos.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Set;

import se.avelon.daidalos.Debug;
import se.avelon.daidalos.R;
import se.avelon.daidalos.Utilities;

public class BluetoothFragment extends AbstractFragment {
    private static final String TAG = BluetoothFragment.class.getSimpleName();

    public String getTitle() {return "Bluetooth";};
    public int getIcon() {return R.drawable.bluetooth;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bluetooth, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

        ((EditText)view.findViewById(R.id.enabled)).setText("Enabled: " + bluetooth.isEnabled());
        ((EditText)view.findViewById(R.id.address)).setText("Enabled: " + bluetooth.getAddress());
        ((EditText)view.findViewById(R.id.name)).setText("Name: " + bluetooth.getName());
        ((EditText)view.findViewById(R.id.state)).setText("State: " + bluetooth.getState());

        ArrayList list = new ArrayList();
        list.add("Bluetooth Devices:");

        Set<BluetoothDevice> pairedDevices = bluetooth.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            list.add("name=" + device.getName() +",address=" + device.getAddress());
        }
        Utilities.spinner(view, R.id.devices, list);

        new BluetoothProfile.ServiceListener() {
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                Debug.e(TAG, "onServiceConnected= " + profile);
            }

            public void onServiceDisconnected(int profile) {
                Debug.e(TAG, "onServiceDisconnected= " + profile);
            }
        };
    }
}