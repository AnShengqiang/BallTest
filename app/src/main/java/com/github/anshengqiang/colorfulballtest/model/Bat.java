package com.github.anshengqiang.colorfulballtest.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.View;

import com.github.anshengqiang.colorfulballtest.Test.TestSceneView;
import com.github.anshengqiang.colorfulballtest.game.SceneView;

/**
 * Created by anshengqiang on 2017/3/11.
 */

public class Bat {

    private View mSceneView;

    private int color = Color.argb(255, 30, 144, 255);

    private Point position;

    private Size size = new Size(250,50);

    private DisplayMetrics mDisplayMetrics;



    public Bat(View sceneView){
        this.mSceneView = sceneView;
        this.position = new Point(200, 200);
    }

    public Bat(View sceneView, DisplayMetrics displayMetrics){
        this.mSceneView = sceneView;
        this.position = new Point(displayMetrics.widthPixels/2, 200);
        this.mDisplayMetrics = displayMetrics;
    }


    public Rect getFrame(){
        return new Rect(this.position.x - this.size.getWidth()/2,
                        this.position.y - this.size.getHeight()/2,
                        this.position.x + this.size.getWidth()/2,
                        this.position.y + this.size.getHeight()/2);
    }

    public Point getBatPoint(){
        return new Point(this.position.x,this.position.y - this.size.getHeight()/2);
    }

    public void move(int x){
        if (mDisplayMetrics == null){
            this.position.x = x;
        }else if (x + this.size.getWidth()/2 < mDisplayMetrics.widthPixels
                && x - this.size.getWidth()/2 > 0) {
            this.position.x = x;
        }
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.color);

        this.position.y = this.mSceneView.getBottom() - 200;

        canvas.drawRect(this.position.x - this.size.getWidth()/2,
                        this.position.y - this.size.getHeight()/2,
                        this.position.x + this.size.getWidth()/2,
                        this.position.y + this.size.getHeight()/2,
                        paint);

    }

}
