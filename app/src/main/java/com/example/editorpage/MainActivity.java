package com.example.editorpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {
    Animation open;
    Animation close;
    ConstraintLayout layout;
//    LinearLayout layout;
    FloatingActionButton floatingActionButton;
    Boolean click=false;
    Button textView,imageView;
    Context context;
   /* EditText textheight;
    EditText textwidth;
    EditText textxcoordinate;
    EditText textycoordinate;*/
    int height=200,width=200;
    int x=200,y=200;

    TextView textView1;
    ImageView imageview1;

    boolean removingValueInTextview = false;
    boolean removeimage=false;
    boolean imagerotate=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       floatingActionButton =  findViewById(R.id.floatingActionButton);
       layout=(ConstraintLayout) findViewById(R.id.addLayout) ;
       /*textheight=(EditText) findViewById(R.id.height);
       textwidth=(EditText) findViewById(R.id.width);
       textxcoordinate=(EditText) findViewById(R.id.x_coordinate);
       textycoordinate=(EditText) findViewById(R.id.y_coordinate);*/

       textView=(Button) findViewById(R.id.text_view);
       imageView=(Button) findViewById(R.id.image_view);
       open= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.from_0_to_45);
       close=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.from_45_to_0);
       context=getApplicationContext();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                setVisibility(click);
                setAnimation();
                       }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click=true;
                if(!removeimage){
                    imageView();

                }else{
//                    layout.removeView(imageview1);
                   layout.removeView(imageview1);
                    imageView();
                }
                dialog();



            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click=true;
                if (!removingValueInTextview){
                 textview();

                }else {
                    layout.removeView(textView1);
                    textview();
               }
                dialog();
            }
        });

    }

    private void dialog() {
        //creating dialog box
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
     //   View dialogview=getLayoutInflater().inflate(R.layout.dialog,null);
        View dialogview=getLayoutInflater().inflate(R.layout.dialog,null);
                //getting dialog ids
        Button left=(Button)dialogview.findViewById(R.id.left_btn);
        Button right=(Button)dialogview.findViewById(R.id.right_btn);
        Button up=(Button) dialogview.findViewById(R.id.up_btn);
        Button down=(Button) dialogview.findViewById(R.id.down_btn);

        EditText textheight=(EditText) dialogview.findViewById(R.id.height);
        EditText textwidth=(EditText) dialogview.findViewById(R.id.width);
        EditText textxcoordinate=(EditText) dialogview.findViewById(R.id.x_coordinate);
        EditText textycoordinate=(EditText) dialogview.findViewById(R.id.y_coordinate);

//text changers
        textxcoordinate.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged","s : "+s+"start : "+start+"before : "+before+"count : "+count);
                if(!imagerotate){
                    layout.removeView(textView1);
                    textview();

                }else{
                    layout.removeView(imageview1);
                    imageView();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        textycoordinate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!imagerotate){
                    layout.removeView(textView1);
                    textview();

                }else{
                    layout.removeView(imageview1);
                    imageView();
                };
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textheight.setText(Integer.toString(height));
        textwidth.setText(Integer.toString(width));
        textxcoordinate.setText(Integer.toString(x));
        textycoordinate.setText(Integer.toString(y));



            //seting texts
        //button actions
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("left : ","left before : "+x);
                x=x-50;
                Log.d("left : ","left after : "+x);


                textxcoordinate.setText(Integer.toString(x));
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("left : ","right before : "+x);
                x=x+50;
                Log.d("left : ","right after : "+x);
                textxcoordinate.setText(Integer.toString(x));
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("left : ","up before : "+y);

                y=y-35;

                Log.d("left : ","up after : "+y);
                textycoordinate.setText(Integer.toString(y));


            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("left : ","down before : "+y);


                y=y+35;

                Log.d("left : ","down after : "+y);
                textycoordinate.setText(Integer.toString(y));

            }
        });


//        textwidth.setText(textView.getWidth());



        builder.setView(dialogview)
                .setCancelable(false)
                .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                         height=Integer.parseInt(textheight.getText().toString());
                         width=Integer.parseInt(textwidth.getText().toString());

                         x=Integer.parseInt(textxcoordinate.getText().toString());
                         y=Integer.parseInt(textycoordinate.getText().toString());
                    }
                });
        AlertDialog dialo= builder.create();
        dialo.show();

    }

    private void imageView() {

        imageview1=new ImageView(this);
        imageview1.setImageResource(R.drawable.ic_baseline_image_24);
        imageview1.setLayoutParams(new LinearLayout.LayoutParams(height,width));
        imageview1.setX(x);
        imageview1.setY(y);

        layout.addView(imageview1);
        removeimage=true;
        imagerotate=true;
    }

    private void textview() {
        //creating text views
            textView1=new TextView(this);
//        textView.setId(2);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(height,
                    width));
            textView1.setX(x);
            textView1.setY(y);


        textView1.setText("TextVew");

        layout.addView(textView1);
        removingValueInTextview=true;
        imagerotate=false;


    }


    private void setAnimation( ) {
        if(!click){
            floatingActionButton.setAnimation(open);
            click=true;

        }else{
            floatingActionButton.setAnimation(close);
            click=false;
        }
    }

    private void setVisibility(Boolean click) {
        if(!click) {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            click=true;
        }else{
            textView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            click=false;

        }
    }

}