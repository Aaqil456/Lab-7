package com.example.lab_7_a182209;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_language,tv_language2,tv_notification2,tv_darkmode,tv_textSize,tv_textSize2,tv_sound,tv_title;
    LinearLayout ll_main, ll_setting_language,ll_setting_notification,ll_setting_darkmode,ll_setting_textSize;
    LinearLayout ll_setting_sound;

    Switch switch_notification,switch_darkmode,switch_sound;

    MediaPlayer mediaPlayer;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    String SP_LANGUAGE = "language";
    String SP_NOTIFICATION = "notification";
    String SP_DARKMODE="darkmode";
    String SP_TEXTSIZE="textsize";
    String SP_SOUND="sound";

    String[] values={"English","Malay","Mandarin","Tamil","Arabic","Cantonise","Japanese","Korean"};
    String [] sizeValues={"small","medium","large"};
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textviews
        tv_language=findViewById(R.id.tv_language);
        tv_language2=findViewById(R.id.tv_language2);
        tv_notification2=findViewById(R.id.tv_notification2);
        tv_darkmode=findViewById(R.id.tv_darkmode);
        tv_textSize=findViewById(R.id.tv_textSize);
        tv_textSize2=findViewById(R.id.tv_textSize2);
        tv_sound=findViewById(R.id.tv_sound);
        tv_title=findViewById(R.id.tv_title);

        switch_notification=findViewById(R.id.switch_notification);
        switch_darkmode=findViewById(R.id.switch_darkmode);
        switch_sound = findViewById(R.id.switch_sound);


        ll_main=findViewById(R.id.ll_main);
        ll_setting_language=findViewById(R.id.ll_setting_language);
        ll_setting_notification=findViewById(R.id.ll_setting_notification);
        ll_setting_darkmode=findViewById(R.id.ll_setting_darkmode);
        ll_setting_textSize=findViewById(R.id.ll_setting_textSize);
        ll_setting_sound=findViewById(R.id.ll_setting_sound);


        //test

        sharedPref=getSharedPreferences("app_settinga",MODE_PRIVATE);

        editor = sharedPref.edit();

        switch_notification.setChecked(sharedPref.getBoolean(SP_NOTIFICATION,false));
        tv_language.setText(values[sharedPref.getInt(SP_LANGUAGE,0)]);
        switch_darkmode.setChecked(sharedPref.getBoolean(SP_DARKMODE,false));
        tv_textSize.setText(sizeValues[sharedPref.getInt(SP_TEXTSIZE,0)]);
        switch_sound.setChecked(sharedPref.getBoolean(SP_SOUND,false));
        changeDarkMode();
        changeTextSize();
        playSound();


        switch_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean(SP_NOTIFICATION,b);
                editor.commit();
            }
        });

        switch_darkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean(SP_DARKMODE,b);
                changeDarkMode();

                editor.commit();
            }
        });



        switch_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean(SP_SOUND,b);
                playSound();

                editor.commit();
            }
        });


        ll_setting_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean switchState= switch_notification.isChecked();
                switch_notification.setChecked(!switchState);
                editor.putBoolean(SP_NOTIFICATION,!switchState);
                editor.commit();

            }
        });

        ll_setting_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageOptions();

            }
        });

        ll_setting_darkmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean switchState= switch_darkmode.isChecked();
                switch_darkmode.setChecked(!switchState);
                changeDarkMode();

                editor.putBoolean(SP_DARKMODE,!switchState);

                editor.commit();
            }
        });

        ll_setting_textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextSizeOptions();
            }
        });

        ll_setting_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean switchState= switch_sound.isChecked();
                switch_sound.setChecked(!switchState);
                playSound();
                editor.putBoolean(SP_SOUND,!switchState);

                editor.commit();
            }
        });



    }

    private void playSound() {
        if(switch_sound.isChecked()){
            tv_sound.setText("Disable Sound");
            if(mediaPlayer==null){
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.sound);
                 mediaPlayer.start();
            }

        }else{
            tv_sound.setText("Enable Sound");

            if(mediaPlayer!=null){
                mediaPlayer.release();
                mediaPlayer=null;
            }
        }

    }



    public void showLanguageOptions() {
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Your Language");
        builder.setSingleChoiceItems(values, sharedPref.getInt(SP_LANGUAGE,0), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i){
                    case 0:
                        editor.putInt(SP_LANGUAGE,0);
                        editor.commit();
                        break;
                    case 1:
                        editor.putInt(SP_LANGUAGE,1);
                        editor.commit();
                        break;
                    case 2:
                        editor.putInt(SP_LANGUAGE,2);
                        editor.commit();
                        break;
                    case 3:
                        editor.putInt(SP_LANGUAGE,3);
                        editor.commit();
                        break;
                    case 4:
                        editor.putInt(SP_LANGUAGE,4);
                        editor.commit();
                        break;
                    case 5:
                        editor.putInt(SP_LANGUAGE,5);
                        editor.commit();
                        break;
                    case 6:
                        editor.putInt(SP_LANGUAGE,6);
                        editor.commit();
                        break;
                    case 7:
                        editor.putInt(SP_LANGUAGE,7);
                        editor.commit();
                        break;
                }
                alertDialog.dismiss();
                tv_language.setText(values[sharedPref.getInt(SP_LANGUAGE,0)]);
                editor.putInt(SP_LANGUAGE,sharedPref.getInt(SP_LANGUAGE,0));
                editor.commit();
            }
        });


        alertDialog=builder.create();
        alertDialog.show();

    }

    public void showTextSizeOptions() {


        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Text Size");
        builder.setSingleChoiceItems(sizeValues, sharedPref.getInt(SP_TEXTSIZE,0), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i){
                    case 0:
                        editor.putInt(SP_TEXTSIZE,0);
                        editor.commit();
                        break;
                    case 1:
                        editor.putInt(SP_TEXTSIZE,1);
                        editor.commit();
                        break;
                    case 2:
                        editor.putInt(SP_TEXTSIZE,2);
                        editor.commit();



                }
                alertDialog.dismiss();
                tv_textSize.setText(sizeValues[sharedPref.getInt(SP_TEXTSIZE,0)]);
                changeTextSize();

                editor.putInt(SP_TEXTSIZE,sharedPref.getInt(SP_TEXTSIZE,0));
                editor.commit();
            }
        });


        alertDialog=builder.create();
        alertDialog.show();

    }

    public void changeTextSize(){
        if(tv_textSize.getText().equals("small")){
            tv_notification2.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_language.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_language2.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_darkmode.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_textSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_textSize2.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_sound.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        }
        else if(tv_textSize.getText().equals("medium")){
            tv_notification2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv_language.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv_language2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv_darkmode.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv_textSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv_textSize2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            tv_sound.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

            tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);


        }
        else if(tv_textSize.getText().equals("large")){
            tv_notification2.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_language.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_language2.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_darkmode.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_textSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_textSize2.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_sound.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);


        }
    }

    public void changeDarkMode(){
        if(switch_darkmode.isChecked()){
            ll_main.setBackgroundResource(R.drawable.bg_darkmode);

            tv_notification2.setTextColor(Color.WHITE);
            tv_language.setTextColor(Color.WHITE);
            tv_language2.setTextColor(Color.WHITE);
            tv_darkmode.setTextColor(Color.WHITE);
            tv_textSize.setTextColor(Color.WHITE);
            tv_textSize2.setTextColor(Color.WHITE);
            tv_title.setTextColor(Color.WHITE);
            tv_sound.setTextColor(Color.WHITE);
            tv_darkmode.setText("Enable Light Mode");





        }
        else{
            ll_main.setBackgroundResource(R.drawable.bg_whitemode);

            tv_notification2.setTextColor(Color.BLACK);
            tv_language.setTextColor(Color.BLACK);
            tv_language2.setTextColor(Color.BLACK);
            tv_darkmode.setTextColor(Color.BLACK);
            tv_textSize.setTextColor(Color.BLACK);
            tv_textSize2.setTextColor(Color.BLACK);
            tv_title.setTextColor(Color.BLACK);
            tv_sound.setTextColor(Color.BLACK);

            tv_darkmode.setText("Enable Dark Mode");


        }
    }
}