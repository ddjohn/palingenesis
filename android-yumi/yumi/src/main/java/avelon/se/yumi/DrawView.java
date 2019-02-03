package avelon.se.yumi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import avelon.se.yumi.pens.MyDrawingPen;

public class DrawView extends View implements View.OnTouchListener, Runnable {
    private static final String TAG = DrawView.class.getSimpleName();
    private static final Random rand = new Random();
    private MyEyes eyes;
    private MyTime time;
    private MyMouth mouth;
    private MyDrawingPen paint = new MyDrawingPen();
    private String[] random = {".", "-", "o", "O"};
    private String[] random2 = {MyMouth.HAPPY, MyMouth.SAD, MyMouth.SURPRISE, MyMouth.TALKING};

    MyWeather w = new MyWeather();

    public DrawView(Context context) {
        super(context);
        Log.e(TAG, "DrawView");

        eyes = new MyEyes(this, "oo");
        mouth = new MyMouth(this, "\\-/");
        time = new MyTime(this);

        this.setBackgroundColor(Color.BLACK);
        rand.setSeed(System.currentTimeMillis());

        Thread t = new Thread(this);
        t.start();

        this.setOnTouchListener(this);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e(TAG, "canvas=" + canvas.getHeight() + "x" + canvas.getWidth());
        Log.e(TAG, "view=" + getRootView().getWidth());
        Log.e(TAG, "view=" + getRootView().getHeight());
        Log.e(TAG, "view=" + getWidth());
        Log.e(TAG, "view=" + getHeight());

        int y = this.getHeight() - this.getRootView().getHeight();
        int x = this.getWidth() - this.getRootView().getWidth();

        //Log.e(TAG, "global=" + getGlobalVisibleRect());
        Log.e(TAG, "view=" + x + "x" + y);


        //int width = Math.min(canvas.getWidth(), canvas.getHeight());
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();

        canvas.drawRoundRect(0, 900, width, width + 900, 50, 50, paint);

        time.drawTime(canvas, width);
        eyes.drawEyes(canvas, width);
        canvas.drawArc(width / 2 - 40, 1300, width / 2 + 40, 1380, 180F, 180F, false, paint);
        mouth.drawMouth(canvas, width);

        // Mounth
//        canvas.drawLine(width / 2 - 200, 1700, width / 2 + 200, 1700, paint);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.e(TAG, "touch=" + event);

        return false;
    }

    @Override
    public void run() {
        while (true) {
            eyes.setEyes(random[rand.nextInt(4)] + random[rand.nextInt(4)]);
            mouth.setMouth(random2[rand.nextInt(random2.length)]);
            if (rand.nextInt(5) == 0) {
                time.setNotification("Really long text, Really long text, Really long text, Really long text, Really long text, Really long text, Really long text, Really long text, Really long text, ");
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}