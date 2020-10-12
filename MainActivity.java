package com.example.battlefield1008;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ObjectAnimator _jump,_disappear,_appear,_dash;


    ImageView p1,p2;
    TextView tvMsg;
    Button btnAtk,btnReset;
    Spinner spnAtk;

    int HP_p1=100;
    int dmg[]={10,20,15};
    long animDuration=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = findViewById(R.id.ivP1);
        p2 = findViewById(R.id.ivP2);

        btnAtk = findViewById(R.id.btnAtk);
        btnReset = findViewById(R.id.btnReset);
        tvMsg = findViewById(R.id.tvMsg);
        spnAtk = findViewById(R.id.spnAtkType);

        _dash = ObjectAnimator.ofFloat(p2,"x",650,200,650);
        _dash.setDuration(animDuration/2*3);
        _jump = ObjectAnimator.ofFloat(p1,"y",0,-100,0);
        _jump.setDuration(animDuration*2);
        _disappear = ObjectAnimator.ofFloat(p1,"alpha",1.0f,0.0f);
        _disappear.setDuration(animDuration*6);

    }

    public void slime_attack(View view) {
        int idAtkType = spnAtk.getSelectedItemPosition();
        String atkName[] = getResources().getStringArray(R.array.slime_atk);

        AnimatorSet aa = new AnimatorSet();
        aa.playTogether(_jump,_dash);
        aa.start();


        if(HP_p1-dmg[idAtkType]<=0){
            btnAtk.setEnabled(false);
            btnReset.setEnabled(true);

            tvMsg.setText(R.string.fight_final);

            AnimatorSet bb = new AnimatorSet();
            bb.playTogether(_disappear);
            bb.start();

        }

        HP_p1-=dmg[idAtkType];

        Toast.makeText(this,String.format("%s : %d points dmg\nHP_p1:%d",
                atkName[idAtkType],dmg[idAtkType],HP_p1),Toast.LENGTH_SHORT).show();

    }

    public void bt_reset(View view) {
        HP_p1=100;

        tvMsg.setText(R.string.fight_start);

        _appear = ObjectAnimator.ofFloat(p1,"alpha",0.0f,1.0f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(_appear);
        animSet.start();

        btnAtk.setEnabled(true);
        btnReset.setEnabled(false);
    }
}