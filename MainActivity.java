package com.example.don.mymweso;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
ImageView oppSource, playerSource;
int player= R.drawable.btn_star_big_off,opp= R.drawable.oppdrawable;
int[][] possibleCombination=new int[8][3];
int[] gridLIDs;
ImageView[] grid;
LinearLayout[] gridL;
LinearLayout oppSourceL,playerSourceL;
Random randomBuilder = new Random();
ArrayList<Integer> idRecent=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        oppSource=(ImageView) findViewById(R.id.oppSource);
        playerSource=(ImageView) findViewById(R.id.playerSource);

        oppSourceL=(LinearLayout) findViewById(R.id.oppSourceL);
        playerSourceL=(LinearLayout) findViewById(R.id.playerSourceL);

        possibleCombination[0]=new int[]{1,2,3};
        possibleCombination[1]=new int[]{4,5,6};
        possibleCombination[2]=new int[]{7,8,9};
        possibleCombination[3]=new int[]{1,4,7};
        possibleCombination[4]=new int[]{2,5,8};
        possibleCombination[5]=new int[]{3,6,9};
        possibleCombination[6]=new int[]{1,5,9};
        possibleCombination[7]=new int[]{3,5,7};


        grid= new ImageView[]{(ImageView) findViewById(R.id.button1) ,(ImageView) findViewById(R.id.button2)
                              ,(ImageView) findViewById(R.id.button3)  ,(ImageView) findViewById(R.id.button4)
                              , (ImageView) findViewById(R.id.button5) ,(ImageView) findViewById(R.id.button6)
                              ,(ImageView) findViewById(R.id.button7)  ,(ImageView) findViewById(R.id.button8) ,(ImageView) findViewById(R.id.button9) };


        gridLIDs= new int[]{R.id.button1L ,R.id.button2L ,R.id.button3L ,R.id.button4L ,R.id.button5L ,R.id.button6L ,R.id.button7L ,R.id.button8L ,R.id.button9L , };



        gridL= new LinearLayout[]{(LinearLayout) findViewById(R.id.button1L) ,(LinearLayout) findViewById(R.id.button2L)
                              ,(LinearLayout) findViewById(R.id.button3L)  ,(LinearLayout) findViewById(R.id.button4L)
                              , (LinearLayout) findViewById(R.id.button5L) ,(LinearLayout) findViewById(R.id.button6L)
                              ,(LinearLayout) findViewById(R.id.button7L)  ,(LinearLayout) findViewById(R.id.button8L) ,(LinearLayout) findViewById(R.id.button9L) };


    playerSource.setOnTouchListener( new MyTouchListenerForSourcePlayer());
        playerSourceL.setOnDragListener(new MyDragLister());

        for(ImageView l:grid)
            l.setOnDragListener(new MyDragLister());



    }


    private final class MyTouchListenerForSourcePlayer implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }

        }
    }

    class MyDragLister implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.oppdrawable);
        Drawable normalShape = getResources().getDrawable(R.drawable.btn_star_big_off);

        //LinearLayout containerGlobal=new LinearLayout(MainActivity.this);
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                  //  v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                   // v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);

                    idRecent.add(v.getId());

                    ImageView container = (ImageView) v;
                    container.setBackgroundDrawable(normalShape);
                    view.setVisibility(View.VISIBLE);

                    ImageView view2=new ImageView(MainActivity.this);
                    view2.setLayoutParams( new ViewGroup.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT));
                    view2.setBackgroundResource(player);

                    owner.addView(view2);
                    view2.setOnTouchListener(new MyTouchListenerForSourcePlayer());

                    LinearLayout oppLinearLayout= getLayoutNotOccupied();
                    if(oppLinearLayout!=null) {
                        ImageView child = (ImageView) oppLinearLayout.getChildAt(0);
                        idRecent.add(oppLinearLayout.getId());
                        child.setBackgroundResource(opp);
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                   // v here corresponds to all onDrag Listening views

                default:
                    break;
            }
            return true;
        }
    }

public LinearLayout getLayoutNotOccupied(){

    for(int count=0;count<100;count++) {
        int RandomInt = randomBuilder.nextInt(9);
        for (int ix=0;ix<idRecent.size();ix++)
            if (gridLIDs[RandomInt] != idRecent.get(ix))
                return gridL[RandomInt];
            else{
                break;
            }

    }
    return null;
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ONWIN(){
        for(int i=0;i<gridLIDs.length;i++){
        gridLIDs[0]=gridLIDs[1]=gridLIDs[2];
            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("win");
            dialog.show();

    }

}}
