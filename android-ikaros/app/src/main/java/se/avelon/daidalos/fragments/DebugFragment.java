package se.avelon.daidalos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import se.avelon.daidalos.R;

public class DebugFragment extends AbstractFragment {
    private static final String TAG = DebugFragment.class.getSimpleName();

    private static TextView text = null;
    private static ScrollView scrollView = null;

    public String getTitle() {return "Debug";};
    public int getIcon() {return R.drawable.debug;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DebugFragment.text = (TextView)view.findViewById(R.id.debugView);
        DebugFragment.scrollView = (ScrollView)view.findViewById(R.id.debugScroll);

        DebugFragment.log("");
        DebugFragment.log("");
        DebugFragment.log("");
        DebugFragment.log("");
        DebugFragment.log("");
        DebugFragment.log("");
        DebugFragment.log("");
        DebugFragment.log("=== D E B U G ===");
    }

    public static void log(String msg) {
        if(text != null) {
            text.append(msg + "\n");
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);;
        }
    }

    public static void clear() {
        if(text != null) {
            text.setText("");
        }
    }
}