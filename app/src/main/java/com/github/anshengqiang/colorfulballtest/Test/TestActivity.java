package com.github.anshengqiang.colorfulballtest.Test;

import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.anshengqiang.colorfulballtest.R;
import com.github.anshengqiang.colorfulballtest.model.Ball;
import com.github.anshengqiang.colorfulballtest.model.Player;

public class TestActivity extends AppCompatActivity implements
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

    private TestSceneView mTestSceneView;
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mPlayer = new Player();

        mTestSceneView = (TestSceneView) findViewById(R.id.ballView);
        mTestSceneView.mBall.setOnDestroyBallListener(this);

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
        mTestSceneView.setBallPosition(200, 200);
        mSpeedSeekBar.setProgress(0);
        mTestSceneView.mBall.setSpeed(0);

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
        mTestSceneView.mVisualTimer.setColor(color);
        mTestSceneView.mBat.setColor(color);

        mTestSceneView.setBallSpeed(times);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onDestroyBall() {
        mTestSceneView.setBallPosition(200, 200);
        mSpeedSeekBar.setProgress(0);
        mTestSceneView.mBall.setSpeed(0);
        FragmentManager fm = getFragmentManager();
        mPlayer.loseLife(this, fm);
    }
}
