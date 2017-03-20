package com.github.anshengqiang.colorfulballtest.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.github.anshengqiang.colorfulballtest.R;
import com.github.anshengqiang.colorfulballtest.model.Ball;

public class GameActivity extends AppCompatActivity implements Ball.BallDestroyListener {

    private DisplayMetrics mDisplayMetrics;
    private SceneView mSceneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplayMetrics = getResources().getDisplayMetrics();
        mSceneView = new SceneView(this, mDisplayMetrics);
        mSceneView.getBall().setOnDestroyBallListener(this);
        setContentView(mSceneView);

//        Log.i("gameActivity", "height: " + mDisplayMetrics.heightPixels + " weight: " + mDisplayMetrics.widthPixels);
    }

    @Override
    public void onDestroyBall() {

    }
}
