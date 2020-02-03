package com.filenber.helloerfe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Main_Display extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profile ;
    EditText username, age, grade,school;
    TextView usertext;
    Button update,skip_btn;
    String userid;
    private StorageReference storageReference;
    private Uri filePath;
    ProgressDialog progressDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
   // private StorageReference storageReference;
   private   RecyclerView list_container;
    Context ctx;
    FloatingActionButton play;
    FirebaseAuth firebaseAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
     MediaPlayer mp;

    public Job_list_model read;
    Job_list_Contoller reading_adapter;
    List<Job_list_model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        list = new ArrayList<>();
        reading_adapter = new Job_list_Contoller(getBaseContext(),list);
        list_container = (RecyclerView) findViewById(R.id.job_list_recycle);
        storageReference = FirebaseStorage.getInstance().getReference();
        list_container.setHasFixedSize(true);
         mp = MediaPlayer.create(this, R.raw.eventually);
        list_container.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        list_container.setAdapter(reading_adapter);
        ReadSingleContact();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
   startActivity(new Intent(Main_Display.this,Track_web.class));

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
    private boolean ReadSingleContact() {
        db.collection("Book").whereEqualTo("Assigned",SharedPrefManager.getInstance(this).saved()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("firestoremessage", e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        //  Toast.makeText(ctx,queryDocumentSnapshots.size() , Toast.LENGTH_SHORT).show();
                        Job_list_model read = doc.getDocument().toObject(Job_list_model.class);

                        list.add(read);
                        reading_adapter.notifyDataSetChanged();
                        if (mp.isPlaying()) {
                            mp.stop();
                            mp.release();
                            mp = MediaPlayer.create(Main_Display.this, R.raw.eventually);
                        } mp.start();
                    }
                }
            }
        });
        return true;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add) {
            startActivity(new Intent(Main_Display.this,Add_operator.class));
            // Handle the camera action
        }  else if (id == R.id.gps) {
            Intent intent = new Intent(Main_Display.this,Track_web.class);
            startActivity(intent);

        } else if (id == R.id.acc) {

        } else if (id == R.id.dec) {

        } else if (id == R.id.lan) {
            startActivity(new Intent(Main_Display.this,Language.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
