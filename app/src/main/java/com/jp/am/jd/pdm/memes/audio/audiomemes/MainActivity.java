package com.jp.am.jd.pdm.memes.audio.audiomemes;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageButton> butoes;
    private int[] ids = {R.raw.acertou, R.raw.aipaipara, R.raw.bambam_birl, R.raw.borracha, R.raw.foiotimo_foiotimo, R.raw.imggeral_aimeucu, R.raw. imggeral_correberg,
            R.raw.imggeral_funkingles, R.raw.papaco_aindabem, R.raw.picapau_fuitapeado, R.raw.imggeral_pauquebrando, R.raw.kidbengala_taarrombado, R.raw.kidbengala_temato, R.raw.sanguedejesus, R.raw.taldomula, R.raw.tiro,
            R.raw.velhoaiai, R.raw.banbanfdpsai, R.raw.emorreu, R.raw.morrediabo, R.raw.ndaaver, R.raw.seubct, R.raw.soufoda, R.raw.trollei};
    private MediaPlayer media;
    private int index;
    private int position;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        media = new MediaPlayer();

        butoes = new ArrayList<>();

        butoes.add((ImageButton)findViewById(R.id.btn1));
        butoes.add((ImageButton)findViewById(R.id.btn2));
        butoes.add((ImageButton)findViewById(R.id.btn3));
        butoes.add((ImageButton)findViewById(R.id.btn4));
        butoes.add((ImageButton)findViewById(R.id.btn5));
        butoes.add((ImageButton)findViewById(R.id.btn6));
        butoes.add((ImageButton)findViewById(R.id.btn7));
        butoes.add((ImageButton)findViewById(R.id.btn8));
        butoes.add((ImageButton)findViewById(R.id.btn9));
        butoes.add((ImageButton)findViewById(R.id.btn10));
        butoes.add((ImageButton)findViewById(R.id.btn11));
        butoes.add((ImageButton)findViewById(R.id.btn12));
        butoes.add((ImageButton)findViewById(R.id.btn13));
        butoes.add((ImageButton)findViewById(R.id.btn14));
        butoes.add((ImageButton)findViewById(R.id.btn15));
        butoes.add((ImageButton)findViewById(R.id.btn16));
        butoes.add((ImageButton)findViewById(R.id.btn17));
        butoes.add((ImageButton)findViewById(R.id.btn18));
        butoes.add((ImageButton)findViewById(R.id.btn19));
        butoes.add((ImageButton)findViewById(R.id.btn20));
        butoes.add((ImageButton)findViewById(R.id.btn21));
        butoes.add((ImageButton)findViewById(R.id.btn22));
        butoes.add((ImageButton)findViewById(R.id.btn23));
        butoes.add((ImageButton)findViewById(R.id.btn24));


        for (int i = 0; i < butoes.size(); i++){
            Glide.with(getApplicationContext()).load(R.drawable.green).into(butoes.get(i));
        }

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("INDEX");
            position = savedInstanceState.getInt("POSITION");
            if (position > 0) {
                media = MediaPlayer.create(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + ids[index]));
                media.seekTo(position);
                Glide.with(getApplicationContext()).load(R.drawable.red).into(butoes.get(index));
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Glide.with(getApplicationContext()).load(R.drawable.green).into(butoes.get(index));
                    }
                });
                media.start();
            }
        }


        for (final ImageButton btn: butoes){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (media.isPlaying()){
                            Glide.with(getApplicationContext()).load(R.drawable.green).into(butoes.get(index));
                            media.stop();
                            media.reset();
                        }
                        index = butoes.indexOf(btn);
                        media =  MediaPlayer.create(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + ids[index]));
                        Glide.with(getApplicationContext()).load(R.drawable.red).into(btn);
                        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Glide.with(getApplicationContext()).load(R.drawable.green).into(btn);
                            }
                        });
                        media.start();
                    } catch (Exception e){
                        //faz nada
                    }
                }
            });
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (media.isPlaying()){
            media.stop();
            media.reset();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if (media.isPlaying()){
            position = media.getCurrentPosition();
            media.stop();
            media.reset();
        } else {
            position = -1;
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if (position != -1) {
            media = MediaPlayer.create(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + ids[index]));
            media.seekTo(position);
            media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Glide.with(getApplicationContext()).load(R.drawable.green).into(butoes.get(index));
                }
            });
            media.start();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("INDEX", index);
        savedInstanceState.putInt("POSITION", media.getCurrentPosition());
        super.onSaveInstanceState(savedInstanceState);
    }
}
