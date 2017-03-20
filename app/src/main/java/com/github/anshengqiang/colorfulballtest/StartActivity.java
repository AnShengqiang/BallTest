package com.github.anshengqiang.colorfulballtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.anshengqiang.colorfulballtest.Test.TestActivity;
import com.github.anshengqiang.colorfulballtest.game.GameActivity;

public class StartActivity extends AppCompatActivity {

    private Button mStartButton;
    private Button mTestButton;
    private Button mQuitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initView();
    }

    private void initView() {
        /*View v = getWindow().getDecorView();
        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);*/
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/


        mStartButton = (Button) findViewById(R.id.start_game_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        mTestButton = (Button) findViewById(R.id.test_game_button);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        mQuitButton = (Button) findViewById(R.id.quit_game_button);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
