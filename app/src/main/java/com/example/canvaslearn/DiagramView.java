package com.example.canvaslearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DiagramView extends View {
    private ArrayList<Column> pre_allocatedColumns;
    private ArrayList<Data> dataList;

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

        invalidate(); // Redraw the view with new data
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        int spaceBetweenColumns = 50;
        int columnWidth = (canvasWidth - spaceBetweenColumns * (dataList.size() + 1)) / dataList.size();
        int maxDataSize = 0;
        for (Data data : dataList) {
            if (data.getSize() > maxDataSize) {
                maxDataSize = data.getSize();
            }
        }

        int columnHeight;
        for (int i = 0; i < dataList.size(); i++) {
            columnHeight = dataList.get(i).getSize() * canvasHeight / maxDataSize - 10;
            int left = spaceBetweenColumns * (i + 1) + columnWidth * i;
            int top = canvasHeight - columnHeight;
            int right = left + columnWidth;
            int bottom = canvasHeight - 10;

            // Reuse pre allocated Column objects
            Column column = pre_allocatedColumns.get(i);
            column.setValue(dataList.get(i).getSize())
                    .setName(dataList.get(i).getName())
                    .setTextColor(Color.WHITE)
                    .setBounds(left, top, right, bottom)
                    .draw(canvas);
        }
    }

    public void setTheBackgroundColor(int color) {
        setBackgroundColor(color);
    }

    public void setColumnsTextColor(int color) {
        for (Column column : pre_allocatedColumns) {
            column.setTextColor(color);
        }
        invalidate();
    }

    public void setColumnsTextSize(float size) {
        for (Column column : pre_allocatedColumns) {
            column.setTextSize(size);
        }
        invalidate();
    }

    public void setColumnsColor(int color) {
        for (Column column : pre_allocatedColumns) {
            column.setColor(color);
        }
        invalidate();
    }


}
