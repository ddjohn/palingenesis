package se.avelon.daidalos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import se.avelon.daidalos.R;

public class CellularFragment extends AbstractFragment implements TextToSpeech.OnInitListener {
    private static final String TAG = CellularFragment.class.getSimpleName();

    private TextToSpeech tts = null;

    public String getTitle() {
        return "Cellular";
    }

    public int getIcon() {
        return R.drawable.cellular;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cellular, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        this.startActivityForResult(intent, 8);
        Log.e(TAG, "inetnt");
    }

    public void onActivityResult(int request, int result, Intent data) {
        Log.e(TAG, data.toString());
        if (8 != request) {
            Log.e(TAG, "Wrong request id");
            return;
        }

        if (TextToSpeech.Engine.CHECK_VOICE_DATA_PASS != result) {
            Log.e(TAG, "TTS Engine cannot be initiated");
            return;
        }

        tts = new TextToSpeech(this.getContext(), this);
    }

    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS != status) {
            Log.e(TAG, "Failed to init tts");
            return;
        }

        if (tts == null) {
            Log.e(TAG, "FTts is null");
            return;
        }

        int res = tts.setLanguage(Locale.ENGLISH);
        Log.e(TAG, "res=" + res);

        // tts.speak("Dimitrios, what time is it?", TextToSpeech.QUEUE_ADD, null);
        tts.speak("Magnus, see you at the airport!!", TextToSpeech.QUEUE_ADD, null);
    }
}
