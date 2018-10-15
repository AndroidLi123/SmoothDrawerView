package com.view.smoothview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：李晓旺
 * @date：2018/10/12
 * @description：自定义自动滚动的控件
 */
public class AutoSmoothView extends SmoothViewGroup {

    private List<String> mImgList = new ArrayList<>();
    private ImageView[] mImgs = new ImageView[2];

    public AutoSmoothView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoSmoothView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {

        //如果没有内容，则不进行初始化操作
        if (mImgList.size() == 0) {
            return;
        }

        removeAllViews();

        MarginLayoutParams params = new MarginLayoutParams(mWidth, mHeight);

        for (int i = 0; i < mImgs.length; i++) {
            mImgs[i] = new ImageView(getContext());
            addViewInLayout(mImgs[i], -1, params, true);
            Glide.with(getContext()).load(getImgPath(i)).centerCrop().into(mImgs[i]);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cCount = getChildCount();
        MarginLayoutParams cParams;

        //向下滑动
        if (smoothDirection == SmoothDirection.DOWN.getDirection()) {
            for (int i = 0; i < cCount; i++) {
                View childView = getChildAt(i);
                cParams = (MarginLayoutParams) childView.getLayoutParams();
                int cl = 0, ct = 0, cr, cb;
                if (i == 1) {
                    cl = cParams.leftMargin;
                    ct = mSmoothMarginTop + mHeight;
                } else if (i == 0) {
                    cl = cParams.leftMargin;
                    ct = mSmoothMarginTop;
                }

                cr = cl + mWidth;
                cb = ct + mHeight;
                childView.layout(cl, ct, cr, cb);
            }
        } else if (smoothDirection == SmoothDirection.UP.getDirection()) {
            //向上滑动
            for (int i = 0; i < cCount; i++) {
                View childView = getChildAt(i);
                cParams = (MarginLayoutParams) childView.getLayoutParams();
                int cl = 0, ct = 0, cr, cb;
                if (i == 1) {
                    cl = cParams.leftMargin;
                    ct = mSmoothMarginTop + mHeight + mHeight;
                } else if (i == 0) {
                    cl = cParams.leftMargin;
                    ct = mSmoothMarginTop + mHeight;
                }

                cr = cl + mWidth;
                cb = ct + mHeight;
                childView.layout(cl, ct, cr, cb);
            }
        } else if (smoothDirection == SmoothDirection.LEFT.getDirection()) {
            //向左滑动
            for (int i = 0; i < cCount; i++) {
                View childView = getChildAt(i);
                cParams = (MarginLayoutParams) childView.getLayoutParams();
                int cl = 0, ct = 0, cr, cb;
                if (i == 1) {
                    cl = mSmoothMarginLeft + mWidth + mWidth;
                    ct = cParams.topMargin;
                } else if (i == 0) {
                    cl = mSmoothMarginLeft + mWidth;
                    ct = cParams.topMargin;
                }
                cr = cl + mWidth;
                cb = ct + mHeight;
                childView.layout(cl, ct, cr, cb);
            }
        } else if (smoothDirection == SmoothDirection.RIGHT.getDirection()) {
            //向右滑动
            for (int i = 0; i < cCount; i++) {
                View childView = getChildAt(i);
                cParams = (MarginLayoutParams) childView.getLayoutParams();
                int cl = 0, ct = 0, cr, cb;
                if (i == 1) {
                    cl = mSmoothMarginLeft + mWidth;
                    ct = cParams.topMargin;
                } else if (i == 0) {
                    cl = mSmoothMarginLeft;
                    ct = cParams.topMargin;
                }
                cr = cl + mWidth;
                cb = ct + mHeight;
                childView.layout(cl, ct, cr, cb);
            }
        }

    }

    @Override
    protected void doAnim() {
        requestLayout();
    }

    public void setImgList(List<String> imgList) {
        mImgList = imgList;
        initView();
    }

    /**
     * 获取图片地址
     *
     * @param position 位置
     * @return 图片地址
     */
    private String getImgPath(int position) {
        position = position % mImgList.size();
        return mImgList.get(position);
    }

}
