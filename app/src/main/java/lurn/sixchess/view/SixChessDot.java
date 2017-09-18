package lurn.sixchess.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator 可爱的路人 on 2017/9/16.
 */

public class SixChessDot extends View {
    private int color = Color.BLACK;
    private Paint paint;
    private int xPosition;
    private int yPosition;
    private int type = 0;//0 或 1

    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public SixChessDot(Context context) {
        super(context);
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    public SixChessDot(Context context, int color ,int type) {
        super(context);
        this.color = color;
        this.type = type;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    public SixChessDot(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SixChessDot(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SixChessDot(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getMeasuredWidth() / 2 - 50, paint);
    }
}
