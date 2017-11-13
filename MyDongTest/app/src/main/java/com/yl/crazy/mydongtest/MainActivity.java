package com.yl.crazy.mydongtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yl.crazy.mydongtest.Test_HeadAndFoot.TestHeadActivity;
import com.yl.crazy.mydongtest.activity.DesignActivity;
import com.yl.crazy.mydongtest.activity.ExActivity;
import com.yl.crazy.mydongtest.activity.ScrollingActivity;
import com.yl.crazy.mydongtest.activity.ShoppingActivity;
import com.yl.crazy.mydongtest.activity.SucceessedActivity;
import com.yl.crazy.mydongtest.expandableListable.Main3Activity;
import com.yl.crazy.mydongtest.test.MainActivity2;
import com.yl.crazy.mydongtest.utils.BitmapUtils;
import com.yl.crazy.mydongtest.view.MyImageView;
import com.yl.crazy.mydongtest.view.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mImg)
    MyImageView mImg;
    @BindView(R.id.mImg2)
    MyView mImg2;
    @BindView(R.id.mImg3)
    ImageView mImg3;
    private Button brn, brn2, brn3, btn4, btn5, btn6;

    String s = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510288060486&di=64207175a593ea4d4914ff6705990432&imgtype=0&src=http%3A%2F%2Fimg.bzdao.com%2F11563%2F2320759.jpg";
    String s1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510885016&di=a596ef31834a996e07562c0448160b63&imgtype=jpg&er=1&src=http%3A%2F%2Fimages15.fotki.com%2Fv246%2Fphotos%2F8%2F809019%2F3029546%2Fbitmap38738-vi.jpg";
    String s2 ="http://opq5wk7p7.bkt.clouddn.com/ef600f56b6b1ba88e09748a3a0150cc7.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        brn = (Button) findViewById(R.id.ben);
        brn2 = (Button) findViewById(R.id.ben2);
        brn3 = (Button) findViewById(R.id.ben3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);

        brn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestHeadActivity.class));
            }
        });

        brn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DesignActivity.class));
            }
        });

        brn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ExActivity.class));
                Snackbar.make(view, "泥在跳什么？", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShoppingActivity.class));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SucceessedActivity.class));
            }
        });



        Glide.with(this).load(s2).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                Bitmap twoCircleBitmap = BitmapUtils.getTwoCircleBitmap(resource,mImg3);
                mImg3.setImageBitmap(twoCircleBitmap);
            }
        });

        Glide.with(this).load(s1).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Bitmap twoCircleBitmap = BitmapUtils.getTwoCircleBitmap(resource,mImg);
                mImg.setImageBitmap(twoCircleBitmap);
            }
        });

    }

}
