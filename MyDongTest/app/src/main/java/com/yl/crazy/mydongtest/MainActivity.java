package com.yl.crazy.mydongtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yl.crazy.mydongtest.Test_HeadAndFoot.TestHeadActivity;
import com.yl.crazy.mydongtest.activity.ExActivity;
import com.yl.crazy.mydongtest.activity.ShoppingActivity;
import com.yl.crazy.mydongtest.activity.TestActivity;
import com.yl.crazy.mydongtest.expandableListable.Main3Activity;
import com.yl.crazy.mydongtest.test.MainActivity2;

public class MainActivity extends AppCompatActivity {

    private Button brn,brn2,brn3,btn4,btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brn = (Button) findViewById(R.id.ben);
        brn2 = (Button) findViewById(R.id.ben2);
        brn3 = (Button) findViewById(R.id.ben3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);

        brn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TestHeadActivity.class));
            }
        });

        brn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });

        brn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main3Activity.class));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ExActivity.class));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShoppingActivity.class));
            }
        });
    }

}
