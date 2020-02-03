package com.filenber.helloerfe;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Add_operator_Contoller extends RecyclerView.Adapter<Add_operator_Contoller.ViewHolder> {
    Context mcx;
    List<Add_operator_model> job_content;
    Context context;
    QueryDocumentSnapshot response;
    // InterstitialAd mInterstitialAd;
    public Add_operator_Contoller( Context cnx, List<Add_operator_model> contents)
    {
        this.mcx = cnx;
        this.job_content=contents;
    }

    public Add_operator_Contoller.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.add_operator_custom_list,null);
        return new Add_operator_Contoller.ViewHolder(view);

    }


    public void onBindViewHolder(@NonNull Add_operator_Contoller.ViewHolder holder, int position) {
        final   Add_operator_model content = job_content.get(position);

        holder.FullName.setText(content.getName());
        holder.mainPhone.setText(content.getPhone());

        Toast.makeText(mcx, job_content.get(position).Id, Toast.LENGTH_SHORT).show();
        //   Picasso.with(context).load(R.drawable.side_nav_bar).into(R.id);
        //  Glide.with(mcx).load(content.getBook_cover()).into(holder.profileimg);
        //holder.cardView.setAnimation(AnimationUtils.loadAnimation(mcx,R.anim.fad_anim_transation));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                //  intent.putExtra("id",content.uid());
                intent.putExtra("Name",content.getName());
                intent.putExtra("Phone",content.getPhone());
             /*   intent.putExtra("Woreda",content.getWoreda());
                intent.putExtra("Unique_Name",content.getUnique_Name());
                Date date = new Date(content.Date.getTime());
                DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mcx);

                intent.putExtra("Date",  dateFormat.format(date));
                intent.putExtra("Zone",content.getZone());
                intent.putExtra("Phone",content.getPhone_number());*/

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
        TextView FullName ;
        TextView Region ;
        TextView Zone ;
        TextView Woreda ;
        TextView Kebele ;
        TextView mainPhone ;
        TextView extraPhone;
        TextView Occapation;
        TextView noofsite;
        TextView sitename ;
        //  Button read;
        //ImageView profileimg;
        CardView cardView;
        public ViewHolder( View itemView) {
            super(itemView);
            FullName = (TextView)itemView.findViewById(R.id.operator_nam);
            mainPhone = (TextView)itemView.findViewById(R.id.phone_num);
            cardView = (CardView)itemView.findViewById(R.id.operator_phone_box);

        }
    }
}