package com.github.anshengqiang.colorfulballtest.model;

/**
 * Created by anshengqiang on 2017/3/11.
 */

public class Speed {

    public double x ;
    public double y ;
    static double originX = 1;
    static double originY = 1 ;

    public Speed(){}

    public Speed(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Speed mulSpeed(int times){


//        originX = this.x > 0? 1: (this.x < 0? -1: originX);
//        originY = this.y > 0? 1: (this.y < 0? -1: originY);
        if (this.x > 0){
            this.originX = 1;
        }else if (this.x < 0){
            this.originX = -1;
        }

        if (this.y > 0){
            this.originY = 1;
        }else if (this.y < 0){
            this.originY = -1;
        }

        return new Speed(originX * times, originY * times);
    }

}
