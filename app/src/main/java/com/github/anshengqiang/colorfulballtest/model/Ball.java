package com.github.anshengqiang.colorfulballtest.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.github.anshengqiang.colorfulballtest.Test.TestSceneView;
import com.github.anshengqiang.colorfulballtest.game.SceneView;

import java.util.List;

/**
 * Created by anshengqiang on 2017/3/11.
 */

public class Ball {

    public Point position;

    public double radius = 80;

    int color = Color.WHITE;
    public Rect frame;
    public Speed speed;
    private Boolean isBallMove;
    public SceneView mSceneView;
    private TestSceneView mTestSceneView;
    private BallDestroyListener mBallDestroyListener;
    private List<Brick> mBricks;

    public Ball(Rect rect, SceneView sceneView) {
        this.mSceneView = sceneView;
        this.position = new Point(300, 300);
        this.radius = 60;
        this.color = Color.WHITE;
        this.speed = new Speed(6, -6);
        this.frame = rect;
        this.isBallMove = false;
        this.mBricks = sceneView.getBricks();

        changeFrame(frame);
    }

    public Ball(Rect rect, TestSceneView testSceneView) {
        this.position = new Point(300, 300);
        this.radius = 160;
        this.color = Color.WHITE;
        this.speed = new Speed(1, 1);
        this.frame = rect;
        this.mTestSceneView = testSceneView;

        changeFrame(frame);
    }

    public void setOnDestroyBallListener(BallDestroyListener ballDestroyListener) {
        mBallDestroyListener = ballDestroyListener;
    }

    public void changeFrame(Rect frame) {
        this.frame = frame;
    }

    private void run() {

        this.position.x += this.speed.x;
        this.position.y += this.speed.y;


        if ((this.position.x - this.radius) < this.frame.left ||
                (this.position.x + this.radius) > this.frame.right) {
            this.speed.x = -this.speed.x;
        }

        if ((this.position.y - this.radius) < this.frame.top ||
                (this.position.y + this.radius) > this.frame.bottom) {
            this.speed.y = -this.speed.y;
        }

        Rect batFrame = mSceneView == null ? mTestSceneView.getBatFrame() : mSceneView.getBatFrame();

        if (this.position.x > batFrame.left &&
                this.position.y + this.radius > batFrame.top &&
                this.position.x < batFrame.right &&
                this.position.y - this.radius < batFrame.bottom) {
            this.speed.y = -this.speed.y;
        }

        double m = this.radius * Math.pow(2, 1 / 2) / 2;

        if (mBricks != null)
        for (int i = 0; i < mBricks.size(); i++) {
            Brick b = mBricks.get(i);
            if (this.position.y > b.getRect().top - m &&
                    this.position.y < b.getRect().bottom + m &&
                    this.position.x > b.getRect().left - m &&
                    this.position.x < b.getRect().right + m) {
                if (this.position.y > b.getRect().bottom ||
                        this.position.y < b.getRect().top) {
                    this.speed.y = -this.speed.y;
                    mBricks.remove(i);
                    Log.i("y轴", "y的速度改变");
                } else {
                    this.speed.x = -this.speed.x;
                    mBricks.remove(i);
                    Log.i("x轴", "x的速度改变");
                }
            }


        }

        if (this.position.y + this.radius > batFrame.bottom) {
            mBallDestroyListener.onDestroyBall();
        }


    }

    public void draw(Canvas canvas) {
        if (mSceneView != null) {
            if (mSceneView.getBricks().size() >= 1) {
                this.run();
            }
        } else {
            this.run();
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.color);

        canvas.drawCircle(this.position.x, this.position.y, (float) this.radius, paint);
    }


    public void setSpeed(int times) {
        this.speed = speed.mulSpeed(times);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public interface BallDestroyListener {
        void onDestroyBall();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isBallMove() {
        return this.isBallMove;
    }

    public void setBallMove(Boolean isBallMove) {
        this.isBallMove = isBallMove;
    }
}
