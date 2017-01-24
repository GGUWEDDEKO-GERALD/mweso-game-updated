package com.example.don.mymweso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Frether on 1/12/2017.
 */
public class Level extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
    }

    public void lev1(View view) {
        Intent k =new Intent(this,MainActivity.class);
        startActivity(k);
    }
    public void lev2(View view) {
        Intent k =new Intent(this,MainActivity.class);
        startActivity(k);
    }
    public void lev3(View view) {
        Intent k =new Intent(this,MainActivity.class);
        startActivity(k);
    }
    public void lev4(View view) {
        Intent k =new Intent(this,MainActivity.class);
        startActivity(k);
    }

}
