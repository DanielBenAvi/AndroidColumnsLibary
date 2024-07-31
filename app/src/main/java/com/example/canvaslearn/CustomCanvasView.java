package com.example.canvaslearn;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomCanvasView extends View {
    private Paint paint;

    public CustomCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int boxSize = width / 2;
        int left = (width - boxSize) / 2;
        int top = (height - boxSize) / 2;
        int right = left + boxSize;
        int bottom = top + boxSize;
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private void drawText(Canvas canvas, String text, float x, float y) {
        paint.setColor(Color.BLACK); // Set text color
        paint.setTextAlign(Paint.Align.CENTER); // Center align text
        canvas.drawText(text, x, y, paint);
    }


}
