

package com.example.don.mymweso;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Good extends AppCompatActivity {
    Button ply;
    final  Good me=this;

    MediaPlayer music ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mwesologin);
        ply = (Button) findViewById(R.id.button);
        music=MediaPlayer.create(Good.this,R.raw.snk);

        music.start();

     /* ply.setOnClickListener(new Button.OnClickListener(){
          public void onClick(View v){
              Intent k =new Intent(me,MainActivity.class);
              me.startActivity(k);
          }
      }
             );
    }*/
    }
    public void play(View view) {
        Intent i = new Intent(this, Options.class);
        startActivity(i);
    }

    public void exit(View view) {
        finish();
    }

    public void level(View view) {
        Intent k = new Intent(this,Level.class);
        startActivity(k);
    }
    public void score(View view) {
        Intent k = new Intent(this,Scores.class);
        startActivity(k);
    }

    public void about(View view) {
        Intent g =new Intent(this,About.class);
        startActivity(g);
    }
}


