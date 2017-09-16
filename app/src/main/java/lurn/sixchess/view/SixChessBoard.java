package lurn.sixchess.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import lurn.sixchess.R;

/**
 * Created by Administrator 可爱的路人 on 2017/9/16.
 */

public class SixChessBoard extends View {

    private Paint paint;

    public SixChessBoard(@NonNull Context context) {
        super(context);
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackground));
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
    }

    public SixChessBoard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SixChessBoard(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SixChessBoard(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth > measuredHeight) {
            setMeasuredDimension(measuredHeight, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int gridSize = width / 8;
        for (int i = 0; i < 4; i++) {
            canvas.drawLine((i * 2 + 1) * gridSize, gridSize, (i * 2 + 1) * gridSize, width - gridSize, paint);
            canvas.drawLine(gridSize, (i * 2 + 1) * gridSize, width - gridSize, (i * 2 + 1) * gridSize, paint);
        }
    }
}
