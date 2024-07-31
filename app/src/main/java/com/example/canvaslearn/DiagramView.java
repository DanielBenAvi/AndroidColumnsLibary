package com.example.canvaslearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class DiagramView extends View {
    private Paint paint;
    private Paint textPaint;
    private ArrayList<Data> dataList;

    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        dataList = new ArrayList<>();

        textPaint = new Paint();
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(30);

    }

    public void setDataList(ArrayList<Data> dataList) {
        this.dataList = dataList;
        invalidate(); // Redraw the view with new data
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
            int bottom = canvasHeight;

            Column column = new Column(dataList.get(i).getName(), dataList.get(i).getSize(), left, top, right, bottom);
            column.draw(canvas);
        }
    }


}
