package me.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.view.smoothview.GalleryEntity;
import com.view.smoothview.SmoothDrawerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private SmoothDrawerView smoothDrawerView;
    private List<GalleryEntity> mEntities = new ArrayList<>();
    private int b;

    private String[] mImgs = new String[]{
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/4ceb299085c44638bcf7e0ce8679e1de.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/dec2b20b02f645a887efd20afcc6b6c6.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/B4EF7AE40F6EA0EDBCEAD82CFE4BA459.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1492736678357.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1492736678385.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1AE6FAF89DD445357C7708E99CB42AAB.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/3ED30C7D82C856E341FE265FDC67F7DD.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/98C05A517FFA91F87B2043DF0FAF7F79.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/D6BBFA02472690EF30DED349AE39D6ED.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1488345872725.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1488345872749.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/3ED30C7D82C856E341FE265FDC67F7DD.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1492736678385.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/3ED30C7D82C856E341FE265FDC67F7DD.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/98C05A517FFA91F87B2043DF0FAF7F79.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/1492736678385.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/dec2b20b02f645a887efd20afcc6b6c6.png",
            "http://tianniutupian.oss-cn-shanghai.aliyuncs.com/images/98C05A517FFA91F87B2043DF0FAF7F79.png",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=0;
        smoothDrawerView = (SmoothDrawerView) findViewById(R.id.smoothview);
        for (String mImg : mImgs) {
            GalleryEntity entity = new GalleryEntity();
            entity.imgUrl = mImg;
            mEntities.add(entity);
        }
        smoothDrawerView.addGalleryData(mEntities);

    }

    private void startSmooth() {

        smoothDrawerView.post(new Runnable() {
            @Override
            public void run() {
                smoothDrawerView.startSmooth();
            }
        });

    }

    public void onRefresh(View view) {
        startSmooth();
    }

}
