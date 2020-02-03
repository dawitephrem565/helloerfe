package com.filenber.helloerfe;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Job_list_Contoller extends  RecyclerView.Adapter<Job_list_Contoller.ViewHolder> {  Context mcx;
    List<Job_list_model> job_content;
    Context context;
    QueryDocumentSnapshot response;
   // InterstitialAd mInterstitialAd;
    public Job_list_Contoller( Context cnx, List<Job_list_model> contents)
    {
        this.mcx = cnx;
        this.job_content=contents;
    }

    public Job_list_Contoller.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.job_list_custom,null);
        return new ViewHolder(view);

    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final   Job_list_model content = job_content.get(position);

        holder.Zone.setText(content.getZone());
        holder.Call_Center_note.setText(content.getWoreda());

        Toast.makeText(mcx, content.getUnique_Name(), Toast.LENGTH_SHORT).show();
      //   Picasso.with(context).load(R.drawable.side_nav_bar).into(R.id);
      //  Glide.with(mcx).load(content.getBook_cover()).into(holder.profileimg);
        //holder.cardView.setAnimation(AnimationUtils.loadAnimation(mcx,R.anim.fad_anim_transation));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
              //  intent.putExtra("id",content.uid());
                intent.putExtra("Agent_name",content.getAgent_name());
                intent.putExtra("Kebele",content.getKebele());
                intent.putExtra("Woreda",content.getWoreda());
                intent.putExtra("Unique_Name",content.getUnique_Name());
                Date date = new Date(content.Date.getTime());
                DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mcx);

                intent.putExtra("Date",  dateFormat.format(date));
                intent.putExtra("Zone",content.getZone());
                intent.putExtra("Phone",content.getPhone_number());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(mcx,JobActivity.class);
                mcx.startActivity(intent);
            }
        });


    }


    public int getItemCount() {
        return job_content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Zone ;
        TextView Call_Center_note ;
      //  Button read;
        //ImageView profileimg;
        CardView cardView;
        public ViewHolder( View itemView) {
            super(itemView);
            Zone = (TextView)itemView.findViewById(R.id.project_zone);
            Call_Center_note = (TextView)itemView.findViewById(R.id.call_center_note);

            cardView = (CardView)itemView.findViewById(R.id.Card_box);

        }
    }
}