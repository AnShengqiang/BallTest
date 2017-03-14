package com.github.anshengqiang.colorfulballtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anshengqiang on 2017/3/11.
 */

public class SceneView extends View {

    public Ball mBall;
    public Bat mBat;
    private Handler mHandler;
    public VisualTimer mVisualTimer;



    public SceneView(Context context) {
        super(context);
        this.init();
    }

    public SceneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.init();
    }

    public SceneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.init();
    }

    public Rect getBatFrame(){
        return mBat.getFrame();
    }


    public void setBallSpeed(int times){
        mBall.setSpeed(times);
    }

    public void setBallPosition(int x, int y){
        Point point = new Point(x ,y);
        mBall.setPosition(point);
    }

    private void init(){
        Rect frame = new Rect(getLeft(), getTop(), getRight(), getBottom());
        mBall = new Ball(frame, this);
        mVisualTimer = new VisualTimer();
        mBat = new Bat(this);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message message){
                switch (message.what){
                    case 1:
                        Rect frame = new Rect(getLeft(), getTop(), getRight(),getBottom());
                        mBall.changeFrame(frame);
                        invalidate();
                }
            }
        };

        startBallThread();
    }

    private void startBallThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1);

                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        this.mBall.draw(canvas);

        Rect rect = getClockRect();
        mVisualTimer.setFrame(rect);

        mVisualTimer.draw(canvas);
        mBat.draw(canvas);
    }

    @NonNull
    private Rect getClockRect() {
        double left = mBall.position.x - mBall.radius;
        double top = mBall.position.y - mBall.radius;
        double right = mBall.position.x + mBall.radius;
        double bottom = mBall.position.y + mBall.radius;
        return new Rect((int)left, (int)top, (int)right, (int)bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mBat.move((int) event.getX());
                invalidate();
                break;
        }
        return true;
    }
}
