package avelon.se.yumi;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import avelon.se.yumi.pens.MyDrawingPen;

public class MyMouth {
    private static final String TAG = MyMouth.class.getSimpleName();

    public static final String HAPPY = "\\-/";
    public static final String SAD = "\\-/";
    public static final String SURPRISE = " O ";
    public static final String TALKING = "  o";

    private MyDrawingPen paint = new MyDrawingPen();

    private View parent;
    private String mouth;

    public MyMouth(View parent, String mouth) {
        this.parent = parent;
        this.setMouth(mouth);
    }

    public void setMouth(String mouth) {
        Log.e(TAG, "setMouth=" + mouth);
        this.mouth = mouth;
        parent.invalidate();
    }

    public void drawMouth(Canvas canvas, int width) {
        this.drawMouth(canvas,1*width/4, 1700, mouth.charAt(0)); // Left drawEye
        this.drawMouth(canvas,2*width/4, 1700, mouth.charAt(1)); // Right drawEye
        this.drawMouth(canvas,3*width/4, 1700, mouth.charAt(2)); // Right drawEye
    }

    private void drawMouth(Canvas canvas, int x, int y, char character) {
        Log.e(TAG, "drawMouth");

        switch (character) {
            case '.':
                Log.e(TAG, "drawMouth .");
                canvas.drawCircle(x, y, 8, paint);
                break;
            case '\\':
                Log.e(TAG, "drawMouth .");
                canvas.drawLine(x - 40, y-40, x + 40, y+40, paint);
                break;
            case '/':
                Log.e(TAG, "drawMouth .");
                canvas.drawLine(x - 40, y+40, x + 40, y-40, paint);
                break;
            case '-':
                Log.e(TAG, "drawMouth -");
                canvas.drawLine(x - 40, y, x + 40, y, paint);
                break;
            case 'o':
                Log.e(TAG, "drawMouth o");
                canvas.drawCircle(x, y, 40, paint);
                break;
            case 'O':
                Log.e(TAG, "drawMouth O");
                canvas.drawCircle(x, y, 80, paint);
                break;
        }
    }
}
