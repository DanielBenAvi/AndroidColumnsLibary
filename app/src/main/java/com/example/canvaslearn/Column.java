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
        textColor = Color.WHITE;
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
        // Draw text in the middle of the column
        canvas.drawText(name, left, bottom - 10, textPaint);

        // Draw value at the top of the column
        canvas.drawText(String.valueOf(value), left, top - textSize, textPaint);
    }



    // Getters and setters


    public int getValue() {
        return value;
    }

    public Column setValue(int value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public Column setName(String name) {
        this.name = name;
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

    public Paint getTextPaint() {
        return textPaint;
    }

    public Column setTextPaint(Paint textPaint) {
        this.textPaint = textPaint;
        return this;
    }

    public Paint getRectPaint() {
        return rectPaint;
    }

    public Column setRectPaint(Paint rectPaint) {
        this.rectPaint = rectPaint;
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

    public float getTextSize() {
        return textSize;
    }

    public Column setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public Column setBounds(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = (int) (top + textSize*2);
        this.right = right;
        this.bottom = bottom;
        return this;
    }
}
