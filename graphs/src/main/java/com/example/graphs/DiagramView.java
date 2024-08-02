package com.example.graphs;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DiagramView extends View {
    private ArrayList<Column> pre_allocatedColumns;
    private ArrayList<GraphData> graphDataList;
    private boolean animateColumns = false;
    private int spaceBetweenColumns = 50;

    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        graphDataList = new ArrayList<>();
        pre_allocatedColumns = new ArrayList<>();
    }

    public void setDataList(ArrayList<GraphData> graphDataList) {
        this.graphDataList = graphDataList;

        // Preallocate Column objects
        pre_allocatedColumns.clear();
        for (int i = 0; i < graphDataList.size(); i++) {
            pre_allocatedColumns.add(new Column());
        }

        if (animateColumns) {
            startAnimation();
        } else {
            for (int i = 0; i < graphDataList.size(); i++) {
                pre_allocatedColumns.get(i).setAnimatedValue(graphDataList.get(i).getSize());
                pre_allocatedColumns.get(i).setCurrentValue(graphDataList.get(i).getSize());
            }
            invalidate();
        }
    }

    private void startAnimation() {
        for (int i = 0; i < graphDataList.size(); i++) {
            final Column column = pre_allocatedColumns.get(i);
            final int value = graphDataList.get(i).getSize();
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
        if (graphDataList == null || graphDataList.isEmpty()) {
            return;
        }

        int columnWidth = (getWidth() - spaceBetweenColumns * (graphDataList.size() + 1)) / graphDataList.size();
        int maxDataSize = 0;
        for (GraphData graphData : graphDataList) {
            if (graphData.getSize() > maxDataSize) {
                maxDataSize = graphData.getSize();
            }
        }

        int columnHeight;
        for (int i = 0; i < graphDataList.size(); i++) {
            columnHeight = graphDataList.get(i).getSize() * getHeight() / maxDataSize - 10;
            int left = spaceBetweenColumns * (i + 1) + columnWidth * i;
            int top = getHeight() - columnHeight;
            int right = left + columnWidth;
            int bottom = getHeight() - 10;

            // Reuse pre allocated Column objects
            Column column = pre_allocatedColumns.get(i);
            column.setValue(graphDataList.get(i).getSize())
                    .setName(graphDataList.get(i).getName())
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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Column column : pre_allocatedColumns) {
                if (column.contains((int) event.getX(), (int) event.getY())) {
                    column.performClick();
                    showColumnDialog(column.getName(), column.getValue());
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void showColumnDialog(String columnName, int columnValue) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(columnName);
        builder.setMessage("Value: " + columnValue);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}
