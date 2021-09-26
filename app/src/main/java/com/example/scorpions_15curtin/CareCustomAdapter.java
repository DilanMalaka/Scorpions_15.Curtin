package com.example.scorpions_15curtin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CareCustomAdapter extends RecyclerView.Adapter<CareCustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList care_id, pet_name, vaccine_name, vaccinated_date, due_date;



    CareCustomAdapter(Activity activity, Context context, ArrayList care_id, ArrayList pet_name,
                      ArrayList vaccine_name, ArrayList vaccinated_date,
                      ArrayList due_date){
        this.activity = activity;
        this.context = context;
        this.care_id = care_id;
        this.pet_name = pet_name;
        this.vaccine_name = vaccine_name;
        this.vaccinated_date = vaccinated_date;
        this.due_date = due_date;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_care, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.care_id_txt.setText(String.valueOf(care_id.get(position)));
        holder.pet_name_txt.setText(String.valueOf(pet_name.get(position)));
        holder.vaccine_name_txt.setText(String.valueOf(vaccine_name.get(position)));
        holder.vaccinate_date_txt.setText(String.valueOf(vaccinated_date.get(position)));
        holder.due_date_txt.setText(String.valueOf(due_date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateCareActivity.class);
                intent.putExtra("id", String.valueOf(care_id.get(position)));
                intent.putExtra("name", String.valueOf(pet_name.get(position)));
                intent.putExtra("vaccine", String.valueOf(vaccine_name.get(position)));
                intent.putExtra("date", String.valueOf(vaccinated_date.get(position)));
                intent.putExtra("due", String.valueOf(due_date.get(position)));

                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return care_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView care_id_txt, pet_name_txt, vaccine_name_txt, vaccinate_date_txt, due_date_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            care_id_txt = itemView.findViewById(R.id.care_id_txt);
            vaccine_name_txt = itemView.findViewById(R.id.vaccine_name_txt);
            pet_name_txt = itemView.findViewById(R.id.pet_name_txt);
            vaccinate_date_txt = itemView.findViewById(R.id.vaccinate_date_txt);
            due_date_txt = itemView.findViewById(R.id.due_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
