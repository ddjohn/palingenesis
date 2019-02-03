package se.avelon.daidalos.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Set;

import se.avelon.daidalos.Debug;
import se.avelon.daidalos.R;

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
        Debug.w(TAG, "enabled=" + bluetooth.isEnabled());

        Debug.w(TAG, "" + bluetooth.getAddress());
        Debug.w(TAG, "" + bluetooth.getName());
        Debug.w(TAG, "" + bluetooth.getState());

        Set<BluetoothDevice> pairedDevices = bluetooth.getBondedDevices();

            for (BluetoothDevice device : pairedDevices) {
                Debug.w(TAG, "dev=" + device.getName());
                Debug.w(TAG, "dev=" + device.getAddress());
        }

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