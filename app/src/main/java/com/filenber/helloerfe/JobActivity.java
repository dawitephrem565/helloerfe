package com.filenber.helloerfe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class JobActivity extends AppCompatActivity implements View.OnClickListener {
TextView agent_name, agent_phone,zone,woreda,date,kebele,unique_name,note;
CardView call_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
       agent_name=(TextView)findViewById(R.id.Contact_name);
       agent_phone=(TextView)findViewById(R.id.Contact_phone);
       zone=(TextView)findViewById(R.id.Zone_display);
       woreda=(TextView)findViewById(R.id.Operator_Woreda);
       date=(TextView)findViewById(R.id.date_value);
       kebele=(TextView)findViewById(R.id.Kebele_value);
       unique_name=(TextView)findViewById(R.id.unique_name);
       note=(TextView)findViewById(R.id.Note_value);
       call_btn = (CardView)findViewById(R.id.Call_btn);
       call_btn.setOnClickListener(this);
        // set image using Glide
       // Glide.with(JobActivity.this).load(image_url).apply(requestOptions).into(book_cover);
        //String image_url = getIntent().getExtras().getString("anime_img") ;
        zone.setText(getIntent().getStringExtra("Zone"));
        woreda.setText(getIntent().getStringExtra("Woreda"));
        date.setText(getIntent().getStringExtra("Date"));
        unique_name.setText(getIntent().getStringExtra("Unique_Name"));
        kebele.setText(getIntent().getStringExtra("Kebele"));
        agent_name.setText(getIntent().getStringExtra("Agent_name"));
        agent_phone.setText(getIntent().getStringExtra("phone_number"));
        Toast.makeText(this, getIntent().getStringExtra("phone_number"), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Call_btn:
                Intent callIntent =new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9961907453"));
                if (ActivityCompat.checkSelfPermission(JobActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent); break;
        }
    }
}
