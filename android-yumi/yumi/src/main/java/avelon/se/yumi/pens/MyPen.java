package avelon.se.yumi.pens;

import android.graphics.Color;
import android.graphics.Paint;

public class MyPen extends Paint {

    public MyPen() {
        this.setColor(Color.GREEN);
        this.setAntiAlias(true);

        /*
            this.setStyle(Style.STROKE);
            this.setStrokeJoin(Join.ROUND);
            this.setStrokeCap(Cap.ROUND);
            this.setStrokeWidth(32);
            this.setAntiAlias(true);
            this.setTextSize(240);
            */
    }
}
