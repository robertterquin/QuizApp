package com.example.quizapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressBar extends View {

    private Paint backgroundPaint;
    private Paint progressPaint;
    private RectF rectF;
    private float progress = 0; // Progress in percentage (0 to 100)

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Background ring (gray)
        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xFFE0E0E0); // Light gray
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(20);
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);

        // Progress ring (purple)
        progressPaint = new Paint();
        progressPaint.setColor(0xFF4CAF50); // Purple
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(20);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        // For drawing the arc
        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int padding = 20;
        rectF.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw background ring
        canvas.drawArc(rectF, 0, 360, false, backgroundPaint);

        // Ensure valid progress (0-100)
        float safeProgress = Math.max(0, Math.min(progress, 100));
        float sweepAngle = (safeProgress / 100) * 360; // Convert progress to degrees

        // Draw progress arc
        canvas.drawArc(rectF, -90, sweepAngle, false, progressPaint);
    }

    // Setter for progress
    public void setProgress(float progress) {
        this.progress = progress;
        postInvalidate(); // Redraw the view
    }
}
