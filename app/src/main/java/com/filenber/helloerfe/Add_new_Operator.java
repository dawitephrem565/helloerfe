package com.filenber.helloerfe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_new_Operator extends AppCompatActivity implements View.OnClickListener {
TextInputEditText FName,Region,Zone,Woreda,Kebele,Phone ,optionPhone,Occupation,No_of_Site,Site_Name;
SharedPreferences sharedPreferences;
Button Save;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__operator);
        FName=(TextInputEditText)findViewById(R.id.full_name);
        Region=(TextInputEditText)findViewById(R.id.region_op);
        Zone=(TextInputEditText)findViewById(R.id.zone_op);
        Woreda=(TextInputEditText)findViewById(R.id.woreda_op);
        Kebele=(TextInputEditText)findViewById(R.id.kebele_op);
        Phone=(TextInputEditText)findViewById(R.id.phone_op);
        optionPhone=(TextInputEditText)findViewById(R.id.optional_op);
        //Occupation=(TextInputEditText)findViewById(R.id.Operator_occupation);
      //  No_of_Site=(TextInputEditText)findViewById(R.id.Operator_num_site);
        // Site_Name=(TextInputEditText)findViewById(R.id.Operator_site_name);
       Save=(Button)findViewById(R.id.operator_save);
Save.setOnClickListener(this);


    }
    private void showCustomDialog(){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.info_box, viewGroup, false);
         dialogView.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Add_new_Operator.this,Main_Display.class));
             }
         });


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.operator_save:
                if (FName.getText().toString().isEmpty()||Region.getText().toString().isEmpty()||Zone.getText().toString().isEmpty()||Woreda.getText().toString().isEmpty()||Kebele.getText().toString().isEmpty()||Phone.getText().toString().isEmpty()||optionPhone.getText().toString().isEmpty())

            {
                Toast.makeText(this, getString(R.string.fill_form_error_add_operator).toString(), Toast.LENGTH_SHORT).show();
            }
            else{
                Map<String, Object> city = new HashMap<>();
                city.put("name", FName.getText().toString());
                city.put("Region", Region.getText().toString());
                city.put("Zone", Zone.getText().toString());
                city.put("Woreda", Woreda.getText().toString());
                city.put("Kebele", Kebele.getText().toString());
                city.put("phone", Phone.getText().toString());
                city.put("option_phone", optionPhone.getText().toString());
                //city.put("")
                city.put("Owner",SharedPrefManager.getInstance(this).saved())  ;

                    db.collection("operator")
                            .add(city)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                  showCustomDialog();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Add_new_Operator.this, getString(R.string.fill_form_error_add_operator), Toast.LENGTH_SHORT).show();
                                }
                            });

                }

                break;
        }

    }

}
