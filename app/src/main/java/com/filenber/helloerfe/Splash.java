package com.filenber.helloerfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash); final Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            sleep(2000);

                            // User is signed in
                            Intent intent=new Intent(Splash.this,Login_Page.class);
                            startActivity(intent);
                            finish();

                        }
                        catch (Exception ex)
                        {

                            Toast.makeText(Splash.this, ex.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

        );
        thread.start();

    }

}
