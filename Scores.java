package com.example.don.mymweso;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Frether on 1/17/2017.
 */
public class Scores  extends AppCompatActivity {
    String names;
   final Scores me= this;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        Mydatabase db= new Mydatabase(this);

        Options obj= new Options();

        LinearLayout see = (LinearLayout) findViewById(R.id.scoress);

        final TextView name=(TextView) findViewById(R.id.name);
        final TextView win=(TextView)findViewById(R.id.wins);
        final TextView los=(TextView)findViewById(R.id.loses);



        db.open( );
        Cursor c = db.getAllStats( );
        if (c.moveToFirst( )) {
            do {
                names = obj.name;
                name.setText(names);
                name.toString();

                see.addView(name);
            } while (c.moveToNext());
        }
        db.close();

    }

}
