
package com.xixi.funuiprogressdialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private boolean mIsFunui = false;
    private ImageView mBallLeft;
    private ImageView mBallRight;
    private Animation mRotateLeft;
    private Animation mRotateRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog_funui);

        mBallLeft = (ImageView) findViewById(R.id.ball_img_red);
        mBallRight = (ImageView) findViewById(R.id.ball_img_purple);
        mRotateLeft = AnimationUtils.loadAnimation(this, R.anim.funui_ball_rotate_left);
        mRotateRight = AnimationUtils.loadAnimation(this, R.anim.funui_ball_rotate_right);

        mBallLeft.startAnimation(mRotateLeft);
        mRotateLeft.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                mBallRight.startAnimation(mRotateRight);
            }
        });
        mRotateRight.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBallLeft.startAnimation(mRotateLeft);
            }
        });
    }
}
