package avelon.se.yumi.pens;

import android.graphics.Color;
import android.graphics.Paint;

public class MyDrawingPen extends MyPen {

    public MyDrawingPen() {
        this.setStyle(Paint.Style.STROKE);
        this.setStrokeWidth(64);
        this.setStrokeCap(Paint.Cap.ROUND);
        this.setStrokeJoin(Paint.Join.ROUND);
    }
}
