package com.example.don.mymweso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Options extends AppCompatActivity {
    String name;

    final Options me = this;
//
String playername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

         final Button ok= (Button) findViewById(R.id.ok) ;
       final EditText nme  = (EditText) findViewById(R.id.EditText01);
       nme.setText("");

        Mydatabase ob  =new Mydatabase(this);
        ob.open();



        ok.setOnClickListener(new Button.OnClickListener( ) {
            public void onClick(View v) {
                name = nme.getText( ).toString( );
                Intent k = new Intent(me,MainActivity.class) ;
                me.startActivity(k);
            }
        });
        ob.Enterwins(name);

        ob.close();

    }
    public void ok(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



    public String getname(String name){
        return  name;
    }



}

