package se.avelon.daidalos.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.io.IOException;
import se.avelon.daidalos.Debug;
import se.avelon.daidalos.R;

public class CameraFragment extends AbstractFragment implements SurfaceHolder.Callback {
    private static final String TAG = CameraFragment.class.getSimpleName();
    private Camera camera;

    public String getTitle() {return "Camera";};
    public int getIcon() {return R.drawable.camera;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            if(this.getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

                Debug.i(TAG, "Scanning for camera");
                camera = Camera.open();

                Camera.Parameters parameters = camera.getParameters();
                Debug.i(TAG, "size=" + parameters.getPictureSize().height + ":" + parameters.getPictureSize().width);

                SurfaceView surface = new SurfaceView(this.getContext());
                SurfaceHolder holder = surface.getHolder();

                FrameLayout preview = (FrameLayout)view.findViewById(R.id.cameraPreview);
                preview.addView(surface);

                holder.addCallback(this);
            }
            else {
                Debug.e(TAG, "Camera was NOT found");
            }
        }
        else {
            Debug.e(TAG, "Camera permissions where not granted");
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            Debug.d(TAG, "Surface created");

            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch(IOException e) {}
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if (holder.getSurface() == null){
            return;
        }

        try {
            camera.stopPreview();
        }
        catch(Exception e) {}

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch(Exception e) {

        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}
}