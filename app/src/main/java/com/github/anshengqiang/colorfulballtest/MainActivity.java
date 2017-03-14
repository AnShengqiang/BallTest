package com.github.anshengqiang.colorfulballtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements 
        SeekBar.OnSeekBarChangeListener, TextView.OnClickListener, Ball.BallDestroyListener{

    private TextView mRgbTextView;
    
    private TextView mRTextView;
    private TextView mGTextView;
    private TextView mBTextView;
    private TextView mSpeedTextView;
    
    private SeekBar mRSeekBar;
    private SeekBar mGSeekBar;
    private SeekBar mBSeekBar;
    private SeekBar mSpeedSeekBar;

    private SceneView mSceneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSceneView = (SceneView) findViewById(R.id.ballView);
        mSceneView.mBall.setOnDestroyBallListener(this);

        initView();
    }

    private void initView(){
        mRgbTextView = (TextView) findViewById(R.id.rgb_text_view);
        mRgbTextView.setOnClickListener(this);
        
        mRTextView = (TextView) findViewById(R.id.rgb_r_text_view);
        mGTextView = (TextView) findViewById(R.id.rgb_g_text_view);
        mBTextView = (TextView) findViewById(R.id.rgb_b_text_view);

        mSpeedTextView = (TextView) findViewById(R.id.speed_text_view);
        
        mRSeekBar = (SeekBar) findViewById(R.id.rgb_r_seek_bar);
        mRSeekBar.setOnSeekBarChangeListener(this);
        mGSeekBar = (SeekBar) findViewById(R.id.rgb_g_seek_bar);
        mGSeekBar.setOnSeekBarChangeListener(this);
        mBSeekBar = (SeekBar) findViewById(R.id.rgb_b_seek_bar);
        mBSeekBar.setOnSeekBarChangeListener(this);
        mSpeedSeekBar = (SeekBar) findViewById(R.id.speed_seek_bar);
        mSpeedSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        mSceneView.setBallPosition(200, 200);
        mSpeedSeekBar.setProgress(0);
        mSceneView.mBall.setSpeed(0);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int red = mRSeekBar.getProgress();
        int green = mGSeekBar.getProgress();
        int blue = mBSeekBar.getProgress();
        int times = mSpeedSeekBar.getProgress();
        
        mRTextView.setText(String.valueOf(red));
        mGTextView.setText(String.valueOf(green));
        mBTextView.setText(String.valueOf(blue));
        mSpeedTextView.setText(String.valueOf(times));

        int color = Color.argb(255, red, green, blue);
        mRgbTextView.setBackgroundColor(color);
        mSceneView.mVisualTimer.setColor(color);
        mSceneView.mBat.setColor(color);

        mSceneView.setBallSpeed(times);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onDestroyBall() {
        mSceneView.setBallPosition(200, 200);
        mSpeedSeekBar.setProgress(0);
        mSceneView.mBall.setSpeed(0);
    }
}
