package com.yl.crazy.mydongtest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.fragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

public class SucceessedActivity extends AppCompatActivity {

    @BindView(R.id.frameL)
    FrameLayout frameL;

    FragmentManager manager;

    private ShoppingFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succeessed);
        ButterKnife.bind(this);

        fragment = new ShoppingFragment();
        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.frameL, fragment).commit();
    }
}
