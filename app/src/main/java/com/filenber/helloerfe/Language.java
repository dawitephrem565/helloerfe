package com.filenber.helloerfe;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class Language extends AppCompatActivity implements View.OnClickListener{
CardView amh_card_box,eng_card_box;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        amh_card_box=findViewById(R.id.amh_box);
        eng_card_box=findViewById(R.id.eng_box);
  setLanguge("am");

    amh_card_box.setOnClickListener(this);
    eng_card_box.setOnClickListener(this);
    }
    public void setLanguge(String lan)
    {
        Resources res = getResources();
        DisplayMetrics metrics=res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(lan.toLowerCase()));
        }
        else {
            conf.locale=new Locale(lan.toLowerCase());
        }
        res.updateConfiguration(conf,metrics);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.amh_box:
                //SharedPrefManager.getInstance(this).save_lan("am");
                Locale myLocale = new Locale("am");
                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.locale = myLocale;
                Toast.makeText(this, "Languge Changed to Amharic", Toast.LENGTH_SHORT).show();

                break;
            case R.id.eng_box:
                SharedPrefManager.getInstance(this).save_lan("amh");
               // setLanguge("eng");

                break;
        }
    }
}
