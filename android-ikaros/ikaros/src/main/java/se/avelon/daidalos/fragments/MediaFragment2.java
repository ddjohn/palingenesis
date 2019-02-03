package se.avelon.daidalos.fragments;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import se.avelon.daidalos.Debug;
import se.avelon.daidalos.R;
import se.avelon.daidalos.Utilities;

public class MediaFragment2 extends AbstractFragment {
    private static final String TAG = MediaFragment2.class.getSimpleName();

    private MediaCodec encoder;

    public String getTitle() {return "Medida";};
    public int getIcon() {return R.drawable.media;};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.media, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /* DISPLAYS */
        DisplayManager manager = (DisplayManager)this.getContext().getSystemService(Context.DISPLAY_SERVICE);
        Debug.e(TAG, "manager=" + manager);

        /* CODECS */
        ArrayList codecList = new ArrayList();
        codecList.add("Codecs:");
        MediaCodecList codecs = new MediaCodecList(MediaCodecList.REGULAR_CODECS);
        for(MediaCodecInfo info : codecs.getCodecInfos()) {
            for(String type : info.getSupportedTypes()) {
                codecList.add("" + type + " (" + info.getName() + ")");
            }
        }
        Utilities.spinner(view, R.id.mediaCodecs, codecList);

        /* VIDEO FORMAT */
        MediaFormat format = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, 1280, 720);
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        format.setInteger(MediaFormat.KEY_BIT_RATE, 6000000);
        format.setInteger(MediaFormat.KEY_FRAME_RATE, 15); //fps
        format.setInteger(MediaFormat.KEY_CAPTURE_RATE, 30);
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 10); //seconds

        ArrayList formatList = new ArrayList();;
        formatList.add("Format:");
        formatList.add("KEY_MIME="             + format.getString(MediaFormat.KEY_MIME));
        formatList.add("KEY_COLOR_FORMAT="     + format.getInteger(MediaFormat.KEY_COLOR_FORMAT));
        formatList.add("KEY_BIT_RATE="         + format.getInteger(MediaFormat.KEY_BIT_RATE));
        formatList.add("KEY_FRAME_RATE="       + format.getInteger(MediaFormat.KEY_FRAME_RATE));
        formatList.add("KEY_I_FRAME_INTERVAL=" + format.getInteger(MediaFormat.KEY_I_FRAME_INTERVAL));
        formatList.add("KEY_WIDTH="            + format.getInteger(MediaFormat.KEY_WIDTH));
        formatList.add("KEY_HEIGHT="           + format.getInteger(MediaFormat.KEY_HEIGHT));
        formatList.add("KEY_CAPTURE_RATE="     + format.getInteger(MediaFormat.KEY_CAPTURE_RATE));
        Utilities.spinner(view, R.id.mediaFormat, formatList);

        /* SELECT A CODEC THAT FITS */
        String codec = codecs.findEncoderForFormat(format);
        Debug.e(TAG, "codec=" + codec);

        try {
            encoder = MediaCodec.createByCodecName(codec);
            encoder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            Debug.e(TAG, "encorder=" + encoder.getCodecInfo());

            final Surface surface = encoder.createInputSurface();
            Debug.e(TAG, "surface=" + surface);
            VirtualDisplay display = manager.createVirtualDisplay("MyVirtualDevice", 640, 400, DisplayMetrics.DENSITY_HIGH, surface, 0);

            encoder.setCallback(new MediaCodec.Callback() {
                
                @Override
                public void onInputBufferAvailable(@NonNull MediaCodec codec, int index) {
                    Debug.e(TAG, "onInputBufferAvailable");
                    ByteBuffer inputBuffer = codec.getInputBuffer(index);
                    //codec.queueInputBuffer(index, -1);
                }

                @Override
                public void onOutputBufferAvailable(@NonNull MediaCodec codec, int index, @NonNull MediaCodec.BufferInfo info) {
                    Debug.e(TAG, "onOutputBufferAvailable");
                    Debug.e(TAG, "index=" + index);
                    Debug.e(TAG, "info=" + info.size);

                    MediaFormat format = codec.getOutputFormat(index); // option A
                    Debug.e(TAG, "format=" + format);

                    ByteBuffer buffer = codec.getOutputBuffer(index);
                    ShortBuffer samples = buffer.asShortBuffer();

                    //encoder.flush();
                    //encoder.dequeueInputBuffer(-1);
//                    for(int i = 0 ; i < info.size; i++) {
//                        Debug.e(TAG, "array["+ i + "] = " + buffer.get());
//                    }
                }


                // 3618 = 2 * 3 * 3 * 3 * 67 
                @Override
                public void onError(@NonNull MediaCodec codec, @NonNull MediaCodec.CodecException e) {
                    Debug.e(TAG, "onError");
                }

                @Override
                public void onOutputFormatChanged(@NonNull MediaCodec codec, @NonNull MediaFormat format) {
                    Debug.e(TAG, "onOutputFormatChanged");
                    Debug.e(TAG, "format=" + format);
                }
            });

            encoder.start();

            //Canvas canvas = surface.lockHardwareCanvas();
            //Paint paint = new Paint();
            //paint.setStyle(Paint.Style.FILL);
            //canvas.drawCircle(100, 100, 50, paint);
            Debug.e(TAG, "Here we leave");

            Camera camera = Camera.open();
            camera.setPreviewCallback(new Camera.PreviewCallback() {
                public void onPreviewFrame(byte[] data, Camera camera) {
                    Debug.e(TAG, "Camera " + data.length);

                    Camera.Parameters parameters = camera.getParameters();
                    Debug.e(TAG, "size=" + parameters.getPictureSize().height + ":" + parameters.getPictureSize().width);
                    Debug.e(TAG, "flatten=" + parameters.flatten());

                    encode(null);
                }
            });
            camera.startPreview();

            //encoder.stop();
            //encoder.release();
            //display.release();
        }
        catch(IOException e) {
            Debug.e(TAG, "exception=" + e);

        }

        ArrayList displayList = new ArrayList();
        displayList.add("Displays:");
        VirtualDisplay vDisplay = manager.createVirtualDisplay("me", 640, 400, 16, null, 0);
        for(Display display : manager.getDisplays()) {
            for(Display.Mode mode : display.getSupportedModes()) {
                displayList.add("" + display.getDisplayId() + ". " + display.getName() + " (" + mode.getPhysicalWidth() + "x" + mode.getPhysicalHeight() + " " + mode.getRefreshRate() + " fps)");
            }
        }
        Utilities.spinner(view, R.id.mediaDisplays, displayList);
    }

    public synchronized void encode(byte[] bytes) {
            Debug.e(TAG, "encode()");

            //ByteBuffer[] ob = encoder.getOutputBuffers();
            //ByteBuffer[] ib = encoder.getInputBuffers();


    }
}