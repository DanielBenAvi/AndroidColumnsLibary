package com.example.canvaslearn;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Column {

    private int value;
    private String name;
    private int left, top, right, bottom;
    private Paint textPaint;
    private Paint rectPaint;
    private int color;
    private int textColor;
    private float textSize;

    public Column() {
        // Default values
        value = 0;
        name = "";
        left = 0;
        top = 0;
        right = 0;
        bottom = 0;
        color = Color.BLACK;
        textColor = Color.GRAY;
        textSize = 30;
        textPaint = new Paint();
        rectPaint = new Paint();
    }

    public void draw(Canvas canvas) {
        Paint rectPaint = new Paint();
        rectPaint.setColor(color);
        rectPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, rectPaint);

        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        // Handle text overflow
        String displayName = name;
        float textWidth = textPaint.measureText(name);
        float availableWidth = right - left;
        if (textWidth > availableWidth) {
            while (textPaint.measureText(displayName + "...") > availableWidth && !displayName.isEmpty()) {
                displayName = displayName.substring(0, displayName.length() - 1);
            }
            displayName += "...";
        }

        // Draw text in the middle of the column
        canvas.drawText(displayName, left, bottom - 10, textPaint);

        // Draw value at the top of the column
        canvas.drawText(String.valueOf(value), left, top - textSize, textPaint);
    }


    public Column setValue(int value) {
        this.value = value;
        return this;
    }

    public Column setName(String name) {
        this.name = name;
        return this;
    }


    public Column setLeft(int left) {
        this.left = left;
        return this;
    }


    public Column setTop(int top) {
        this.top = (int) (top  + textSize*2);
        return this;
    }


    public Column setRight(int right) {
        this.right = right;
        return this;
    }


    public Column setBottom(int bottom) {
        this.bottom = bottom;
        return this;
    }


    public Column setTextPaint(Paint textPaint) {
        this.textPaint = textPaint;
        return this;
    }


    public Column setRectPaint(Paint rectPaint) {
        this.rectPaint = rectPaint;
        return this;
    }


    public Column setColor(int color) {
        this.color = color;
        return this;
    }


    public Column setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public Column setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public Column setBounds(int left, int top, int right, int bottom) {
        setBottom(bottom);
        setLeft(left);
        setRight(right);
        setTop(top);
        return this;
    }

    public void setTextBold(boolean b) {
        if (b) {
            textPaint.setFakeBoldText(true);
        } else {
            textPaint.setFakeBoldText(false);
        }
    }
}
