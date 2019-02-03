package avelon.se.yumi;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import avelon.se.yumi.pens.MyDrawingPen;

public class MyEyes {
    private static final String TAG = MyEyes.class.getSimpleName();

    private MyDrawingPen paint = new MyDrawingPen();

    private View parent;
    private String eyes;

    public MyEyes(View parent, String eyes) {
        this.parent = parent;
        this.setEyes(eyes);
    }

    public void setEyes(String eyes) {
        Log.e(TAG, "setEyes=" + eyes);
        this.eyes = eyes;
        parent.invalidate();
    }

    public void drawEyes(Canvas canvas, int width) {
        this.drawEye(canvas,1*width/4, 1100, eyes.charAt(0)); // Left drawEye
        this.drawEye(canvas,3*width/4, 1100, eyes.charAt(1)); // Right drawEye
    }

    private void drawEye(Canvas canvas, int x, int y, char character) {
        Log.e(TAG, "drawEye");

        switch (character) {
            case '.':
                Log.e(TAG, "drawEye .");
                canvas.drawCircle(x, y, 8, paint);
                break;
            case '-':
                Log.e(TAG, "drawEye -");
                canvas.drawLine(x - 40, y, x + 40, y, paint);
                break;
            case 'o':
                Log.e(TAG, "drawEye o");
                canvas.drawCircle(x, y, 40, paint);
                break;
            case 'O':
                Log.e(TAG, "drawEye O");
                canvas.drawCircle(x, y, 80, paint);
                break;
        }
    }
}
