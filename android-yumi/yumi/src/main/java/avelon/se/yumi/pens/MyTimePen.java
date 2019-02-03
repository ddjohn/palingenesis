package avelon.se.yumi.pens;

public class MyTimePen extends MyPen {

    public MyTimePen() {

        this.setStrokeJoin(Join.ROUND);
        this.setStrokeCap(Cap.ROUND);

        this.setStrokeWidth(24);
        this.setStyle(Style.STROKE);

        this.setTextSize(240);
    }
}
