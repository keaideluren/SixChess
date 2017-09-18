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

public class SixChess extends FrameLayout implements View.OnClickListener, View.OnTouchListener {
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
        sixChessBoard.setOnTouchListener(this);
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
        int dotSize = boardSize / 4;
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
                layoutParams.leftMargin = xposition * boardSize / 4 + xEx;
                layoutParams.topMargin = yposition * boardSize / 4 + yEx;
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
                        SixChessDot sixChessDot = new SixChessDot(getContext(), Color.WHITE, 1);
                        sixChessDot.setPosition(i, j);
                        sixChessDot.setOnClickListener(this);
                        whiteDot.add(sixChessDot);
                        addView(sixChessDot);
                    } else {
                        SixChessDot sixChessDot = new SixChessDot(getContext(), Color.BLACK, 0);
                        sixChessDot.setPosition(i, j);
                        sixChessDot.setOnClickListener(this);
                        blackDot.add(sixChessDot);
                        addView(sixChessDot);
                    }
                }
            }
        }
    }

    private void doRuleRemoveChess(SixChessDot movedDot) {
        int xPosition = movedDot.getxPosition();
        int yPosition = movedDot.getyPosition();
        int type = movedDot.getType();

        int[] rowColor = new int[]{2, 2, 2, 2};
        int[] colColor = new int[]{2, 2, 2, 2};
        //1220  0122 2210 0221可以吃的样子 0黑 1白 2空
        for (int i = 0; i < 4; i++) {
            for (SixChessDot s : whiteDot) {
                if (s.getxPosition() == xPosition && s.getyPosition() == i) {
                    //与移动后的棋子同一行
                    rowColor[i] = 1;
                } else if (s.getxPosition() == i && s.getyPosition() == yPosition) {
                    //与移动后的棋子同一列
                    colColor[i] = 1;
                }
            }
            for (SixChessDot s : blackDot) {
                if (s.getxPosition() == xPosition && s.getyPosition() == i) {
                    //与移动后的棋子同一行
                    rowColor[i] = 0;
                } else if (s.getxPosition() == i && s.getyPosition() == yPosition) {
                    //与移动后的棋子同一列
                    colColor[i] = 0;
                }
            }
        }
    }

    SixChessDot preMoveDot;

    @Override
    public void onClick(View v) {
        if (v instanceof SixChessDot) {
            preMoveDot = (SixChessDot) v;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (preMoveDot == null) {
                return true;
            }
            int moveToX = (int) (event.getX() / v.getMeasuredWidth() * 4);
            int moveToY = (int) (event.getY() / v.getMeasuredHeight() * 4);
            moveChess(preMoveDot, moveToX, moveToY);
            return true;
        }
        return false;
    }

    public LinkedList<SixChessDot> getBlackDot() {
        return blackDot;
    }

    public void setBlackDot(LinkedList<SixChessDot> blackDot) {
        this.blackDot = blackDot;
    }

    public LinkedList<SixChessDot> getWhiteDot() {
        return whiteDot;
    }

    public void setWhiteDot(LinkedList<SixChessDot> whiteDot) {
        this.whiteDot = whiteDot;
    }

    public void moveChess(SixChessDot preMoveDot, int toX, int toY) {
        if ((Math.abs(preMoveDot.getxPosition() - toX) + Math.abs(preMoveDot.getyPosition() - toY)) == 1) {
            preMoveDot.setPosition(toX, toY);
            doRuleRemoveChess(preMoveDot);
            requestLayout();
        }
    }

    public void moveChess(int fromX, int fromY, int toX, int toY) {
        for (SixChessDot s : whiteDot) {
            if (s.getxPosition() == fromX && s.getyPosition() == fromY) {
                moveChess(s, toX, toY);
            }
        }
    }

    public void removeChess(int fromX, int fromY) {
        for (SixChessDot s : whiteDot) {
            if (s.getxPosition() == fromX && s.getyPosition() == fromY) {
                removeChess(s);
            }
        }
    }

    public void removeChess(SixChessDot sixChessDot) {
        whiteDot.remove(sixChessDot);
        blackDot.remove(sixChessDot);
        requestLayout();
    }
}
