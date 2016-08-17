package com.example.wutingying.drawecircle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by wutingying on 16-8-12.
 */
public class CustomeView extends View {
    private float mBorderWidth;
    private int mBorderColor;
    private Paint mPaint;

    private RectF mBounds;
    private float width;
    private float height;
    float radius;
    float smallLength;
    float largeLength;

    private int mHoure;
    private int mMinute;
    private int mSecond;

    private Paint mHourPaint;
    private Paint mMinutePaint;
    public CustomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.CustomeView,0,0
        );
        try {
            mBorderWidth = typedArray.getDimension(R.styleable.CustomeView_border_width,2);
            mBorderColor = typedArray.getColor(R.styleable.CustomeView_border_color,0xff000000);
        }finally {
            typedArray.recycle();
        }
        init();
    }
    public CustomeView(Context context) {
        super(context);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);

        mHourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourPaint.setStyle(Paint.Style.STROKE);
        mHourPaint.setStrokeWidth(mBorderWidth);
        mHourPaint.setColor(mBorderColor);

        mMinutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinutePaint.setStyle(Paint.Style.STROKE);
        mMinutePaint.setStrokeWidth(mBorderWidth);
        mMinutePaint.setColor(mBorderColor);

        timeThread.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds = new RectF(getLeft(),getTop(),getRight(),getBottom());

        width = mBounds.right-mBounds.left;
        height = mBounds.bottom-mBounds.top;

        if (width<height){
            radius = width/4;
        }else{
            radius = height/4;
        }

        smallLength = 10;
        largeLength = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff000000);
        mPaint.setColor(0x66555555);
        canvas.drawRoundRect(new RectF(mBounds.centerX()-(float)0.9*width/2,mBounds.centerY() - (float)0.9*height/2,
                                       mBounds.centerX()+(float)0.9*width/2,mBounds.centerY() + (float)0.9*height/2), 30, 30, mPaint);
        mPaint.setColor(mBorderColor);
        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),radius,mPaint);
        float start_x,start_y;
        float end_x,end_y;
        mPaint.setTextSize(20);
        for(int i=0;i<60;i++){
            start_x = radius *(float)Math.cos(Math.PI/180 * i * 6);
            start_y = radius *(float)Math.sin(Math.PI/180 * i * 6);
            if (i%5==0){
                end_x = start_x-largeLength*(float)Math.cos(Math.PI/180 * i * 6);
                end_y = start_y-largeLength*(float)Math.sin(Math.PI/180 * i * 6);
            }else{
                end_x = start_x-smallLength*(float)Math.cos(Math.PI/180 * i * 6);
                end_y = start_y-smallLength*(float)Math.sin(Math.PI/180 * i * 6);
            }
            start_x+=mBounds.centerX();
            start_y+=mBounds.centerY();

            end_x+=mBounds.centerX();
            end_y+=mBounds.centerY();
            if(i == 0){
                canvas.drawText("3",end_x-10,end_y+8,mPaint);
            }
            if(i == 5){
                canvas.drawText("4",end_x-10,end_y+4,mPaint);
            }
            if(i == 10){
                canvas.drawText("5",end_x-10,end_y+4,mPaint);
            }
            if(i == 15){
                canvas.drawText("6",end_x-6,end_y-4,mPaint);
            }
            if(i == 20){
                canvas.drawText("7",end_x-4,end_y-6,mPaint);
            }
            if(i == 25){
                canvas.drawText("8",end_x-2,end_y+4,mPaint);
            }
            if(i == 30){
                canvas.drawText("9",end_x-2,end_y+6,mPaint);
            }
            if(i == 35){
                canvas.drawText("10",end_x-2,end_y+6,mPaint);
            }
            if(i == 40){
                canvas.drawText("11",end_x-6,end_y+10,mPaint);
            }
            if(i == 45){
                canvas.drawText("12",end_x-14,end_y+8,mPaint);
            }
            if(i == 50){
                canvas.drawText("1",end_x-10,end_y+8,mPaint);
            }
            if(i == 55){
                canvas.drawText("2",end_x-10,end_y+8,mPaint);
            }
            canvas.drawLine(start_x, start_y, end_x, end_y, mPaint);

        }
        mPaint.setColor(0xffff0000);
        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),20,mPaint);

        mMinutePaint.setColor(0xffff0000);
        getCurrentTime();
        drawMinute(canvas);
        drawHoure(canvas);
    }

    private void getCurrentTime(){
        Calendar calender=Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());
        mHoure=calender.get(Calendar.HOUR);
        mMinute = calender.get(Calendar.MINUTE);
        mSecond = calender.get(Calendar.SECOND);
    }
    private Thread timeThread = new Thread() {
        @Override
        public void run() {
            try {
                while(true){
                    updateHandler.sendEmptyMessage(0);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };

    private void drawHoure(Canvas canvas){
        float degree = mHoure*30+mMinute*6/12-mMinute*6;
        canvas.rotate(degree,mBounds.centerX(),mBounds.centerY());
        canvas.drawLine(mBounds.centerX(),mBounds.centerY(),mBounds.centerX(),
                mBounds.centerY()-radius+50,mHourPaint);
    }

    private void drawMinute(Canvas canvas){
        float degree = mMinute*6;
        canvas.rotate(degree,mBounds.centerX(),mBounds.centerY());
        canvas.drawLine(mBounds.centerX(),mBounds.centerY(),mBounds.centerX(),
                mBounds.centerY()-radius+20,mMinutePaint);
    }
}
