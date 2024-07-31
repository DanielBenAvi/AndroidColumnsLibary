package com.example.canvaslearn;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DiagramView extends View {
    private ArrayList<Column> pre_allocatedColumns;
    private ArrayList<Data> dataList;
    private boolean animateColumns = false;
    private int spaceBetweenColumns = 50;

    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        dataList = new ArrayList<>();
        pre_allocatedColumns = new ArrayList<>();
    }

    public void setDataList(ArrayList<Data> dataList) {
        this.dataList = dataList;

        // Preallocate Column objects
        pre_allocatedColumns.clear();
        for (int i = 0; i < dataList.size(); i++) {
            pre_allocatedColumns.add(new Column());
        }

        if (animateColumns) {
            startAnimation();
        } else {
            for (int i = 0; i < dataList.size(); i++) {
                pre_allocatedColumns.get(i).setAnimatedValue(dataList.get(i).getSize());
                pre_allocatedColumns.get(i).setCurrentValue(dataList.get(i).getSize());
            }
            invalidate();
        }
    }

    private void startAnimation() {
        for (int i = 0; i < dataList.size(); i++) {
            final Column column = pre_allocatedColumns.get(i);
            final int value = dataList.get(i).getSize();
            ValueAnimator animator = ValueAnimator.ofInt(0, value);
            animator.setDuration(1000);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                column.setAnimatedValue(animatedValue);
                column.setCurrentValue(animatedValue);
                invalidate();
            });
            animator.start();
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        int columnWidth = (getWidth() - spaceBetweenColumns * (dataList.size() + 1)) / dataList.size();
        int maxDataSize = 0;
        for (Data data : dataList) {
            if (data.getSize() > maxDataSize) {
                maxDataSize = data.getSize();
            }
        }

        int columnHeight;
        for (int i = 0; i < dataList.size(); i++) {
            columnHeight = dataList.get(i).getSize() * getHeight() / maxDataSize - 10;
            int left = spaceBetweenColumns * (i + 1) + columnWidth * i;
            int top = getHeight() - columnHeight;
            int right = left + columnWidth;
            int bottom = getHeight() - 10;

            // Reuse pre allocated Column objects
            Column column = pre_allocatedColumns.get(i);
            column.setValue(dataList.get(i).getSize())
                    .setName(dataList.get(i).getName())
                    .setBounds(left, top, right, bottom)
                    .draw(canvas);
        }
    }

    public void setTheBackgroundColor(int color) {
        if (color == 0) {
            throw new IllegalArgumentException("Color cannot be 0");
        }
        setBackgroundColor(color);
    }

    public void setColumnsTextColor(int color) {
        if (color == 0) {
            throw new IllegalArgumentException("Color cannot be 0");
        }
        for (Column column : pre_allocatedColumns) {
            column.setTextColor(color);
        }
        invalidate();
    }

    public void setColumnsTextSize(float size) {
        if (size < 0) {
            throw new IllegalArgumentException("Text size cannot be negative");
        }
        for (Column column : pre_allocatedColumns) {
            column.setTextSize(size);
        }
        invalidate();
    }

    public void setColumnsColor(int color) {
        if (color == 0) {
            throw new IllegalArgumentException("Color cannot be 0");
        }
        for (Column column : pre_allocatedColumns) {
            column.setColor(color);
        }
        invalidate();
    }

    public void setSpaceBetweenColumns(int space) {
        if (space < 0) {
            throw new IllegalArgumentException("Space between columns cannot be negative");
        }
        spaceBetweenColumns = space;
        invalidate();
    }

    public void setTextBold(boolean b) {
        for (Column column : pre_allocatedColumns) {
            column.setTextBold(b);
        }
        invalidate();
    }

    public void setAnimateColumns(boolean animate) {
        this.animateColumns = animate;
    }
}
