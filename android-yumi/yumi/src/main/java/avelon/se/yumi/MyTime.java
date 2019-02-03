package avelon.se.yumi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import avelon.se.yumi.pens.MyNormalPen;
import avelon.se.yumi.pens.MyTimePen;

public class MyTime implements Runnable {
    private static final String TAG = MyEyes.class.getSimpleName();

    private final String[] notification = new String[]{null};

    private MyTimePen timePen = new MyTimePen();
    private MyNormalPen normalPen = new MyNormalPen();
    private View parent;
    private String time = "";
    private Timer timer = new Timer();

    public MyTime(View parent) {
        this.parent = parent;

        Thread t = new Thread(this);
        t.start();
    }

    public void setTime(String time) {
        this.time = time;
        parent.invalidate();
    }

    public void setNotification(String n) {
        this.notification[0] = n;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notification[0] = null;
            }
        }, 2 * 1000);
        parent.invalidate();
    }

    public void drawTime(Canvas canvas, int width) {

        if (notification[0] == null) {

            Rect bounds = new Rect();
            timePen.getTextBounds(time, 0, time.length(), bounds);
            int height = bounds.height();

            canvas.drawText(time, width / 2 - bounds.width() / 2, 800, timePen);
        } else {
            Rect bounds = new Rect();
            normalPen.getTextBounds(time, 0, time.length(), bounds);
            int height = bounds.height();

            TextPaint tp = new TextPaint();
            tp.setColor(Color.GREEN);
            tp.setTextSize(64);
            tp.setTextAlign(Paint.Align.CENTER);
            tp.setAntiAlias(true);

            StaticLayout layout = new StaticLayout(notification[0], tp, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
            canvas.save();
            canvas.translate(0, 600);
            layout.draw(canvas);
            canvas.restore();
            //canvas.drawText(notification, width / 2 - bounds.width() / 2, 800, normalPen);
        }
    }

    @Override
    public void run() {
        while (true) {
            DateFormat df = new SimpleDateFormat("HH:mm");
            String date = df.format(Calendar.getInstance().getTime());
            this.setTime(date);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}
