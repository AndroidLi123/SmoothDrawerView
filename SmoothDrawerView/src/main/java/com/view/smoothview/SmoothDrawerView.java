package com.view.smoothview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import me.example.galleryview.R;

/**
 * @author：李晓旺
 * @date：2018/10/10
 * @description：
 */
public class SmoothDrawerView extends FrameLayout {

    private AutoSmoothView smoothView1;
    private AutoSmoothView smoothView2;
    private AutoSmoothView smoothView3;
    private AutoSmoothView smoothView4;
    private AutoSmoothView smoothView5;
    private AutoSmoothView smoothView6;
    private AutoSmoothView smoothView7;
    private AutoSmoothView smoothView8;
    private AutoSmoothView smoothView9;

    public SmoothDrawerView(@NonNull Context context) {
        super(context);
        initView();
    }

    public SmoothDrawerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SmoothDrawerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.smooth_view, this);
        smoothView1 = (AutoSmoothView) findViewById(R.id.gallery1);
        smoothView2 = (AutoSmoothView) findViewById(R.id.gallery2);
        smoothView3 = (AutoSmoothView) findViewById(R.id.gallery3);
        smoothView4 = (AutoSmoothView) findViewById(R.id.gallery4);
        smoothView5 = (AutoSmoothView) findViewById(R.id.gallery5);
        smoothView6 = (AutoSmoothView) findViewById(R.id.gallery6);
        smoothView7 = (AutoSmoothView) findViewById(R.id.gallery7);
        smoothView8 = (AutoSmoothView) findViewById(R.id.gallery8);
        smoothView9 = (AutoSmoothView) findViewById(R.id.gallery9);
    }

    public void startSmooth() {
        smoothView1.startSmooth();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothView2.startSmooth();
                smoothView4.startSmooth();
                smoothView6.startSmooth();
                smoothView8.startSmooth();
            }
        }, 1000);

        smoothView3.startSmooth();

        smoothView5.startSmooth();

        smoothView7.startSmooth();

    }

    public void addGalleryData(List<GalleryEntity> listEntities) {
        List<String> imgList = new ArrayList<>();
        for (GalleryEntity entity : listEntities) {
            imgList.add(entity.imgUrl);
        }
        smoothView1.setImgList(imgList.subList(0, 2));
        smoothView2.setImgList(imgList.subList(2, 4));
        smoothView3.setImgList(imgList.subList(4, 6));
        smoothView4.setImgList(imgList.subList(6, 8));
        smoothView5.setImgList(imgList.subList(8, 10));
        smoothView6.setImgList(imgList.subList(10, 12));
        smoothView7.setImgList(imgList.subList(12, 14));
        smoothView8.setImgList(imgList.subList(14, 15));
        smoothView9.setImgList(imgList.subList(15, 17));

    }
}
