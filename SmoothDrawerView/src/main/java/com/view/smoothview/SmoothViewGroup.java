package com.view.smoothview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import me.example.galleryview.R;

/**
 * @author：李晓旺
 * @date：2018/10/12
 * @description：自定义自动滚动的控件
 */
public abstract class SmoothViewGroup extends ViewGroup {

    //ViewGroup宽高
    protected int mWidth, mHeight;
    //变化的marginTop值
    protected int mSmoothMarginTop;
    //变化的marginLeft值
    protected int mSmoothMarginLeft;
    //滚动时间间隔
    protected int mDuration = 500;
    //滑动方向 1为向下 2为向上 3为向左 4为向右
    protected int smoothDirection;

    protected int delayDuration = 2000;


    public SmoothViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmoothViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SmoothViewGroupStyle);
        smoothDirection = array.getInt(R.styleable.SmoothViewGroupStyle_orientation, 4);
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mSmoothMarginTop = -h;
        mSmoothMarginLeft = -w;
        initView();
    }

    protected abstract void initView();

    /**
     * 创建Animatort
     * @param startValue 开始值
     * @param endValue 结束值
     * @param duration 执行时间
     * @param direction 滑动方向
     * @return
     */
    public ValueAnimator createAnimator(final int startValue, final int endValue, final int duration, final int direction) {
        ValueAnimator animator = null;
        animator = ValueAnimator.ofFloat(startValue, endValue);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (direction == SmoothDirection.LEFT.getDirection() || direction == SmoothDirection.RIGHT.getDirection()) {
                    mSmoothMarginLeft = (int) value;
                } else {
                    mSmoothMarginTop = (int) value;
                }
                if (value == endValue) {
                    repeatAnimator(endValue, startValue, duration, direction);
                } else {
                    doAnim();
                }
            }
        });
        return animator;
    }

    private void repeatAnimator(int endValue, int startValue, int duration, final int direction) {
        final ValueAnimator animator = ValueAnimator.ofFloat(endValue, startValue);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (direction == SmoothDirection.LEFT.getDirection() || direction == SmoothDirection.RIGHT.getDirection()) {
                    mSmoothMarginLeft = (int) value;
                } else {
                    mSmoothMarginTop = (int) value;
                }
                doAnim();

            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.start();
            }
        }, delayDuration);
    }

    /**
     * 开启滑动
     */
    public void startSmooth() {
        if (smoothDirection == SmoothDirection.DOWN.getDirection()) {
            //向下滑动
            createAnimator(-mHeight, 0, mDuration, SmoothDirection.DOWN.getDirection()).start();
        } else if (smoothDirection == SmoothDirection.UP.getDirection()) {
            //向上滑动
            createAnimator(-mHeight, -mHeight * 2, mDuration, SmoothDirection.UP.getDirection()).start();
        } else if (smoothDirection == SmoothDirection.LEFT.getDirection()) {
            //向左滑动
            createAnimator(-mWidth, -mWidth * 2, mDuration, SmoothDirection.LEFT.getDirection()).start();
        } else {
            //向右滑动
            createAnimator(-mWidth, 0, mDuration, SmoothDirection.RIGHT.getDirection()).start();
        }

    }

    //动画进行时
    protected abstract void doAnim();


    public int getDelayDuration() {
        return delayDuration;
    }

    public void setDelayDuration(int delayDuration) {
        this.delayDuration = delayDuration;
    }

    public int getSmoothDirection() {
        return smoothDirection;
    }

    public void setSmoothDirection(int smoothDirection) {
        this.smoothDirection = smoothDirection;
    }


}
