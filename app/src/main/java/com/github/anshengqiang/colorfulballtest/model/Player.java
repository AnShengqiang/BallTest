package com.github.anshengqiang.colorfulballtest.model;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;

import com.github.anshengqiang.colorfulballtest.GameOverDialogFragment;

/**
 * Created by anshengqiang on 2017/3/15.
 */

public class Player {
    private static final String DIALOG_OVER = "Dialog_Over";

    public int score;
    public int life;

    public Player(){
        this.life = 3;
        this.score = 0;
    }

    public void loseLife(Context context, FragmentManager fragmentManager){
        if (life > 1){
            life--;
        }else {
            life--;
            GameOverDialogFragment dialogFragment = new GameOverDialogFragment();
            dialogFragment.setCancelable(false);
            dialogFragment.show(fragmentManager, DIALOG_OVER);
        }
    }

}
