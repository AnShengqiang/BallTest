package com.github.anshengqiang.colorfulballtest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by anshengqiang on 2017/3/11.
 */

public class Ball {

    public Point position;

    public double radius = 80;

    int color = Color.WHITE;
    public Rect frame;
    public Speed speed;
    public SceneView sceneView;
    private BallDestroyListener mBallDestroyListener;

    public Ball(Rect rect, SceneView sceneView) {
        this.position = new Point(300, 300);
        this.radius = 160;
        this.color = Color.WHITE;
        this.speed = new Speed(1,1);
        this.frame = rect;
        this.sceneView = sceneView;

        changeFrame(frame);
    }

    public void setOnDestroyBallListener(BallDestroyListener ballDestroyListener){
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

        Rect batFrame = this.sceneView.getBatFrame();

        if (this.position.x > batFrame.left &&
                this.position.y + this.radius > batFrame.top &&
                this.position.x < batFrame.right &&
                this.position.y - this.radius < batFrame.bottom )
        {
            this.speed.y = -this.speed.y;
        }

        if (this.position.y + this.radius > batFrame.bottom){
            mBallDestroyListener.onDestroyBall();
        }


    }

    public void draw(Canvas canvas) {
        this.run();
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.color);

        canvas.drawCircle(this.position.x, this.position.y, (float) this.radius, paint);
    }


    public void setSpeed(int times){
        this.speed = speed.mulSpeed(times);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public interface BallDestroyListener{
        void onDestroyBall();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
