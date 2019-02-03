package se.avelon.daidalos.fragments;

import android.app.NotificationManager;
import android.app.admin.DevicePolicyManager;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import se.avelon.daidalos.R;

public class ScreenFragment extends AbstractFragment {
    private static final String TAG = ScreenFragment.class.getSimpleName();

    public String getTitle() {return "Screen";};
    public int getIcon() {return R.drawable.screen;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // final PowerManager manager = (PowerManager) this.getActivity().getSystemService(Context.POWER_SERVICE);
        final DevicePolicyManager manager = (DevicePolicyManager) this.getActivity().getSystemService(Context.DEVICE_POLICY_SERVICE);

        final ContentResolver resolver = this.getActivity().getContentResolver();

        EditText brightness = (EditText) view.findViewById(R.id.current);
        EditText timeout = (EditText) view.findViewById(R.id.timeout);
        try {
            brightness.setText("Brightness: " + Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS));
            timeout.setText("Timeout: " + Settings.System.getInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT));
        }
        catch (Settings.SettingNotFoundException e) {}

        Button zero = (Button) view.findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, 0);
            }
        });

        Button max = (Button) view.findViewById(R.id.max);
        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, 255);
            }
        });

        Button on = (Button) view.findViewById(R.id.on);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.System.putInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, 60000);
            }
        });

        Button off = (Button) view.findViewById(R.id.off);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.System.putInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, 0);
            }
        });

        Button now = (Button) view.findViewById(R.id.now);
        now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.lockNow();
            }
        });

        final NotificationManager mNotificationManager = (NotificationManager) this.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        final AudioManager myAudioMgr = (AudioManager) this.getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        Button dnd1 = (Button) view.findViewById(R.id.dnd1);
        dnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
                myAudioMgr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        });

        Button dnd0 = (Button) view.findViewById(R.id.dnd0);
        dnd0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
                myAudioMgr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        });


    }
}