package lurn.sixchess.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.LinkedList;

/**
 * Created by Administrator 可爱的路人 on 2017/9/16.
 */

public class SixChess extends FrameLayout implements View.OnClickListener {
    private LinkedList<SixChessDot> blackDot = new LinkedList<>();
    private LinkedList<SixChessDot> whiteDot = new LinkedList<>();
    private SixChessBoard sixChessBoard;

    public SixChess(@NonNull Context context) {
        super(context);
        init();
    }

    public SixChess(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SixChess(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SixChess(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        sixChessBoard = new SixChessBoard(getContext());
        addView(sixChessBoard);
        reset();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int boardSize = width > height ? height : width;
        int xEx = width > height ? (width - height) / 2 : 0;
        int yEx = height > width ? (height - width) / 2 : 0;
        int dotSize = boardSize / 4 - 6;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof SixChessBoard) {
                ((LayoutParams) childAt.getLayoutParams()).gravity = Gravity.CENTER;
            } else if (childAt instanceof SixChessDot) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.width = dotSize;
                layoutParams.height = dotSize;
                int xposition = ((SixChessDot) childAt).getxPosition();
                int yposition = ((SixChessDot) childAt).getyPosition();
                layoutParams.leftMargin = xposition * boardSize / 4 + 3 + xEx;
                layoutParams.topMargin = yposition * boardSize / 4 + 3 + yEx;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void reset() {
//       0  1  2  3
//      0┏ ┳ ┳ ┓
//      1┣ ╋ ╋ ┫
//      2┣ ╋ ╋ ┫
//      3┗ ┻ ┻ ┛
        for (SixChessDot w :
            whiteDot) {
            removeView(w);
        }
        for (SixChessDot w :
            blackDot) {
            removeView(w);
        }
        whiteDot.clear();
        blackDot.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0 || i == 3 || j == 0 || j == 3) {
                    if (j < 2) {
                        SixChessDot sixChessDot = new SixChessDot(getContext(), Color.WHITE);
                        sixChessDot.setPosition(i, j);
                        sixChessDot.setOnClickListener(this);
                        whiteDot.add(sixChessDot);
                        addView(sixChessDot);
                    } else {
                        SixChessDot sixChessDot = new SixChessDot(getContext(), Color.BLACK);
                        sixChessDot.setPosition(i, j);
                        sixChessDot.setOnClickListener(this);
                        blackDot.add(sixChessDot);
                        addView(sixChessDot);
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof SixChessDot) {

        }
    }
}
