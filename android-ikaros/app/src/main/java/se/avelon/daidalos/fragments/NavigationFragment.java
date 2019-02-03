package se.avelon.daidalos.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;

import se.avelon.daidalos.Debug;
import se.avelon.daidalos.R;

public class NavigationFragment extends AbstractFragment implements LocationListener, OnNmeaMessageListener {
    private static final String TAG = NavigationFragment.class.getSimpleName();

    private LocationManager manager;

    private EditText latitude;
    private EditText longitude;
    private EditText altitude;
    private EditText accuracy;
    private EditText bearing;
    private EditText realtime;
    private EditText speed;
    private EditText time;
    private EditText status;

    public String getTitle() {return "Navigation";};
    public int getIcon() {return R.drawable.navigation;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.manager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);

        if(     ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Debug.i(TAG, "Register for GPS");
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Debug.i(TAG, "Register for NMEA");
            manager.addNmeaListener(this);
//            manager.registerGnssMeasurementsCallback(new GnssMeasurements.Callback() {});
            manager.registerGnssStatusCallback(new GnssStatus.Callback() {

            });
//            manager.registerGnssNavigationMessageCallback(new GnssNavgationMessage.Callback() {});
        }
        else {
            Debug.e(TAG, "Navigations permissions where not granted");
        }

        latitude = (EditText)view.findViewById(R.id.navLatitude);
        longitude = (EditText)view.findViewById(R.id.navLongitude);
        altitude = (EditText)view.findViewById(R.id.navAltitude);
        accuracy = (EditText)view.findViewById(R.id.navAccuracy);
        bearing = (EditText)view.findViewById(R.id.navBearing);
        realtime = (EditText)view.findViewById(R.id.navRealTime);
        speed = (EditText)view.findViewById(R.id.navSpeed);
        time = (EditText)view.findViewById(R.id.navTime);
        status = (EditText)view.findViewById(R.id.navStatus);
    }

    @Override
    public void onNmeaMessage(String message, long timestamp) {
        Debug.i(TAG, "nmea=" + message);
    }

    @Override
    public void onLocationChanged(Location location) {
        Debug.i(TAG, "" + location);

        latitude.setText("Latitude: " + location.getLatitude());
        longitude.setText("Longitude: " + location.getLongitude());
        altitude.setText(String.format("Altitude: %.2f m", location.getAltitude()));
        accuracy.setText("Accuracy: " + location.getAccuracy());
        bearing.setText("Bearing: " + location.getBearing());
        realtime.setText("Realtime: " + location.getElapsedRealtimeNanos());
        speed.setText("Speed: " + location.getSpeed() + " mph (" + location.getSpeed()*1.609344 + " km/h)");
        time.setText("Time: " + new Date(location.getTime()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Debug.i(TAG, "status=" + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Debug.i(TAG, "GPS enabled");
        status.setText("Status: enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Debug.i(TAG, "GPS disabled");
        status.setText("Status: disabled");
    }
}