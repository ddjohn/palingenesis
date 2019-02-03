package se.avelon.daidalos.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import se.avelon.daidalos.Debug;
import se.avelon.daidalos.R;
import se.avelon.daidalos.Utilities;

public class SensorFragment extends AbstractFragment implements SensorEventListener {
    private static final String TAG = SensorFragment.class.getSimpleName();

    private EditText gravity;
    private EditText accelerometer;
    private EditText rotation;
    private EditText steps;

    public String getTitle() {return "Sensor";};
    public int getIcon() {return R.drawable.sensor;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sensor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SensorManager manager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList  = manager.getSensorList(Sensor.TYPE_ALL);
        ArrayList list = new ArrayList();
        list.add("Sensors:");
        for (Sensor currentSensor : sensorList ) {
            Log.i(TAG, "sensor=" + currentSensor.getName());
            list.add(currentSensor.getName());
        }
        Utilities.spinner(view, R.id.sensorSensors, list);

        Sensor gravitySensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        Sensor accelerationSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor rotationSensor = manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        Sensor motionSensor = manager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        Sensor stepsSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        manager.registerListener(this,      gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, accelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,     rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,        stepsSensor, SensorManager.SENSOR_DELAY_NORMAL);

        manager.requestTriggerSensor(new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                Log.e(TAG, "mungo=" + event);
            }
        }, gravitySensor);

        gravity = (EditText)view.findViewById(R.id.sensorGravity);
        accelerometer = (EditText)view.findViewById(R.id.sensorAccelerometer);
        rotation = (EditText)view.findViewById(R.id.sensorRotation);
        steps = (EditText)view.findViewById(R.id.sensorSteps);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Debug.i(TAG, "" + event.sensor);

        switch(event.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                gravity.setText("Gravity: " + Utilities.float2String(event.values));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelerometer.setText("Accelerometer: " + Utilities.float2String(event.values));
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                rotation.setText("Rotation: " + Utilities.float2String(event.values));
                break;
            case Sensor.TYPE_STEP_COUNTER:
                steps.setText("Steps: " + Utilities.float2String(event.values));
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG, "accuracy=" + sensor);
    }
}