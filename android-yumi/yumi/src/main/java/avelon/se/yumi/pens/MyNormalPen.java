package avelon.se.yumi.pens;

import android.graphics.Color;

public class MyNormalPen extends MyPen {

    public MyNormalPen() {
        this.setStrokeJoin(Join.ROUND);
        this.setStrokeCap(Cap.ROUND);
        this.setStrokeWidth(8);
        this.setStyle(Style.STROKE);

        this.setTextSize(64);
    }
}
