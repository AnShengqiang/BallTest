package com.github.anshengqiang.colorfulballtest.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by anshengqiang on 2017/3/20.
 */

public class Brick {

    private int color;
    private int life;
    private double width;
    private double height;
    private Rect rect;

    public Brick(Rect rect){
        color = Color.argb(255, 30, 144, 255);
        life = 1;
        this.rect = rect;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLife() {
        return life;
    }

    public void loseLife() {
        this.life--;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rect getRect() {
        return rect;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(this.color);

        canvas.drawRect(this.rect.left, this.rect.top, this.rect.right, this.rect.bottom, paint);
    }
}
