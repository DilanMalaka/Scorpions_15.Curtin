package com.example.scorpions_15curtin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Doc_Custom_Adapter extends RecyclerView.Adapter<Doc_Custom_Adapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList _id, doctor_name, doctor_phone, working_hours, doc_address, doc_province, doc_country;



    Doc_Custom_Adapter(Activity activity,
                       Context context,
                       ArrayList _id,
                       ArrayList doctor_name,
                       ArrayList doctor_phone,
                       ArrayList working_hours,
                       ArrayList doc_address,
                       ArrayList doc_province,
                       ArrayList doc_country) {
        this.activity = activity;
        this.context = context;
        this._id = _id;
        this.doctor_name =doctor_name;
        this.doctor_phone = doctor_phone;
        this.working_hours = working_hours;
        this.doc_address = doc_address;
        this.doc_province = doc_province;
        this.doc_country = doc_country;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_doc, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {



        holder.doc_id_text.setText(String.valueOf(_id.get(position)));
        holder.doc_name_text.setText(String.valueOf(doctor_name.get(position)));
        holder.doc_phone_text.setText(String.valueOf(doctor_phone.get(position)));
        holder.doc_work_text.setText(String.valueOf(working_hours.get(position)));
        holder.doc_address_text.setText(String.valueOf(doc_address.get(position)));
        holder.doc_province_text.setText(String.valueOf(doc_province.get(position)));
        holder.doc_country_text.setText(String.valueOf(doc_country.get(position)));
       holder.mainLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent = new Intent(context, Doc_Update_Activity.class);
                intent.putExtra("id", String.valueOf(_id.get(position)));
               intent.putExtra("name", String.valueOf(doctor_name.get(position)));
               intent.putExtra("phone", String.valueOf(doctor_phone.get(position)));
               intent.putExtra("work", String.valueOf(working_hours.get(position)));
               intent.putExtra("address", String.valueOf(doc_address.get(position)));
               intent.putExtra("province", String.valueOf(doc_province.get(position)));
               intent.putExtra("country", String.valueOf(doc_country.get(position)));
               activity.startActivityForResult(intent, 1);

           }
       });



    }

    @Override
    public int getItemCount() {
        return _id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView doc_id_text, doc_name_text, doc_phone_text, doc_work_text, doc_address_text, doc_province_text, doc_country_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doc_id_text = itemView.findViewById(R.id.doc_id_text);
            doc_name_text = itemView.findViewById(R.id.doc_name_text);
            doc_phone_text = itemView.findViewById(R.id.doc_phone_text);
            doc_work_text = itemView.findViewById(R.id.doc_work_text);
            doc_address_text = itemView.findViewById(R.id.doc_address_text);
            doc_province_text = itemView.findViewById(R.id.doc_province_text);
            doc_country_text = itemView.findViewById(R.id.doc_country_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
