package com.github.anshengqiang.colorfulballtest.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.anshengqiang.colorfulballtest.model.Ball;
import com.github.anshengqiang.colorfulballtest.model.Bat;
import com.github.anshengqiang.colorfulballtest.model.Brick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshengqiang on 2017/3/20.
 */

public class SceneView extends View{

    private List<Brick> bricks = new ArrayList<>();
    private Bat mBat;
    private Handler mHandler;
    private DisplayMetrics mDisplayMetrics;
    private Ball mBall;


    public SceneView(Context context, DisplayMetrics metrics) {
        super(context);
        mDisplayMetrics = metrics;
        init();
        initBrick();
    }

    public SceneView(Context context, @Nullable AttributeSet attrs, DisplayMetrics metrics) {
        super(context, attrs);
        mDisplayMetrics = metrics;
        init();
        initBrick();
    }

    public SceneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, DisplayMetrics metrics) {
        super(context, attrs, defStyleAttr);
        mDisplayMetrics = metrics;
        init();
        initBrick();
    }

    private void init(){
        Rect frame = new Rect(getLeft(), getTop(), getRight(), getBottom());
        mBall = new Ball(frame, this);
        mBall.setColor(Color.argb(255, 102, 205, 170));
        mBat = new Bat(this, mDisplayMetrics);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        Rect frame = new Rect(getLeft(), getTop(), getRight(),getBottom());
                        mBall.changeFrame(frame);
                        invalidate();
                }
            }
        };
        startThread();
    }

    private void startThread(){
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

    private void initBrick(){
        int leftBorder = 0;
        int topBorder = 0;
        int rightBorder = mDisplayMetrics.widthPixels;
        int bottomBorder = mDisplayMetrics.heightPixels;

        double brickWidth = (rightBorder - leftBorder)/31*5;
        double brickHeight = (bottomBorder - topBorder)/3/7;
        double minHoriziontalSpace = brickWidth/5;
        double minVerticalSpace = brickHeight;

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 3; j++){

                double left = minHoriziontalSpace * (i+1) + brickWidth * i ;
                double top = minVerticalSpace * (j+1) + brickHeight * j;
                double right = minHoriziontalSpace * (i+1) + brickWidth * (i+1);
                double bottom = minVerticalSpace * (j+1) + brickHeight * (j+1);

                Rect rect = new Rect((int)left, (int)top, (int)right, (int)bottom);
                Brick brick = new Brick(rect);
                bricks.add(brick);
            }
        }
    }

    public Rect getBatFrame(){
        return mBat.getFrame();
    }

    public Ball getBall(){
        return mBall;
    }

    public List<Brick> getBricks(){
        return bricks;
    }

    public DisplayMetrics getDisplayMetrics(){
        return mDisplayMetrics;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBat.draw(canvas);
        if (!mBall.isBallMove()) {
            Point point = new Point(mBat.getBatPoint().x, (int) (mBat.getBatPoint().y - mBall.radius - 10));
            mBall.setPosition(point);
        }
        mBall.draw(canvas);

        for (Brick b: bricks){
            b.draw(canvas);
        }



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mBat.move((int) event.getX());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mBall.setBallMove(true);
        }
        return true;
    }
}
