package com.example.canvaslearn;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Column {
    public static final int DEFAULT_COLOR = Color.BLACK;
    public static final int DEFAULT_TEXT_COLOR = Color.GRAY;
    public static final int DEFAULT_TEXT_SIZE = 30;


    private String title;
    private int value;
    private int color;
    private int textColor;
    private int textSize;
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Column(String title, int value, int left, int top, int right, int bottom) {
        color = DEFAULT_COLOR;
        textColor = DEFAULT_TEXT_COLOR;
        textSize = DEFAULT_TEXT_SIZE;
    }



    public void draw(Canvas canvas) {
        Paint rectPaint = new Paint();
        rectPaint.setColor(color);
        rectPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, rectPaint);

        Paint textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, left + (right - left) / 2, bottom - textSize, textPaint);
        canvas.drawText(String.valueOf(value), left + (right - left) / 2, bottom - textSize * 2, textPaint);

    }


    // Getters and setters

    public String getTitle() {
        return title;
    }

    public Column setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Column setValue(int value) {
        this.value = value;
        return this;
    }

    public int getColor() {
        return color;
    }

    public Column setColor(int color) {
        this.color = color;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public Column setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public Column setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getLeft() {
        return left;
    }

    public Column setLeft(int left) {
        this.left = left;
        return this;
    }

    public int getTop() {
        return top;
    }

    public Column setTop(int top) {
        this.top = top;
        return this;
    }

    public int getRight() {
        return right;
    }

    public Column setRight(int right) {
        this.right = right;
        return this;
    }

    public int getBottom() {
        return bottom;
    }

    public Column setBottom(int bottom) {
        this.bottom = bottom;
        return this;
    }
}
