package com.filenber.helloerfe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Add_operator extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reference = db.collection("operator");
    private StorageReference storageReference;

    Add_operator_Contoller reading_adapter;
    List<Add_operator_model> list;
    private Uri filePath;
    ProgressDialog progressDialog;

    private   RecyclerView list_container;
    Context ctx;
    FloatingActionButton add;

    public Add_operator_model read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operator);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        add = (FloatingActionButton)findViewById(R.id.add_new_operator);
        list = new ArrayList<>();
        reading_adapter = new Add_operator_Contoller(this,list);
        list_container = (RecyclerView) findViewById(R.id.operator_list_recycle);
        storageReference = FirebaseStorage.getInstance().getReference();
        list_container.setHasFixedSize(true);
        //mp = MediaPlayer.create(this, R.raw.eventually);
        list_container.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        list_container.setAdapter(reading_adapter);
      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
startActivity(new Intent(Add_operator.this,Add_new_Operator.class));
          }
      });
        ReadSingleContact();
    }

    private boolean ReadSingleContact() {
        db.collection("operator").whereEqualTo("phone","0922091179").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("firestoremessage", e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                          String doc_id = doc.getDocument().getId();
                        //  Toast.makeText(ctx,queryDocumentSnapshots.size() , Toast.LENGTH_SHORT).show();
                        Add_operator_model read = doc.getDocument().toObject(Add_operator_model.class).withId(doc_id);

                        list.add(read);
                        reading_adapter.notifyDataSetChanged();

                    }
                }
            }
        });
        return true;

    }

}
