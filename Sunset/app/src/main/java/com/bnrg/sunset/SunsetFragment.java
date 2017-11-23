package com.bnrg.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;

/**
 * Created by palance on 2017/11/22.
 */

public class SunsetFragment extends Fragment {
    private View mSceneView;
    private View mSunView;
    private View mSkyView;
    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;

    public static SunsetFragment newInstance(){
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_sunset, container,
                false);
        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);

        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);
        // 点击背景播放动画
        mSceneView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startAnimation();
            }
        });
        return view;
    }

    private void startAnimation(){
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();
        // 太阳落山动画
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(
                mSunView, "y", sunYStart, sunYEnd)
                .setDuration(3000);
        // 设置时间插值
        heightAnimator.setInterpolator(new AccelerateInterpolator());
        // 蓝天背景色过渡动画
        ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView,
                "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
                .setDuration(3000);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());
        // 太阳落山后天黑动画
        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView,
                "backgroundColor", mSunsetSkyColor, mNightSkyColor)
                .setDuration(1500);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());

        boolean bUseSet = true;
        if(bUseSet){     // 执行动画集合
            AnimatorSet animationSet = new AnimatorSet();
            animationSet.play(heightAnimator)
                    .with(sunsetSkyAnimator)
                    .before(nightSkyAnimator);
            animationSet.start();
        }else{          // 执行两个动画
            heightAnimator.start();
            sunsetSkyAnimator.start();
        }
    }
}
