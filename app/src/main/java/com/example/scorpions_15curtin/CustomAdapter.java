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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList pet_id, pet_name, pet_type, pet_gender, pet_breed, pet_birthday, pet_color, pet_description;


    CustomAdapter(Activity activity, Context context, ArrayList pet_id, ArrayList pet_name,
                  ArrayList pet_type,
                  ArrayList pet_gender,
                  ArrayList pet_breed,
                  ArrayList pet_birthday,
                  ArrayList pet_color,
                  ArrayList pet_description){
        this.activity =activity;
        this.context = context;
        this.pet_id = pet_id;
        this.pet_name = pet_name;
        this.pet_type = pet_type;
        this.pet_gender = pet_gender;
        this.pet_breed = pet_breed;
        this.pet_birthday = pet_birthday;
        this.pet_color = pet_color;
        this.pet_description = pet_description;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.id.setText(String.valueOf(pet_id.get(position)));
        holder.name.setText(String.valueOf(pet_name.get(position)));
        holder.type.setText(String.valueOf(pet_type.get(position)));
        holder.gender.setText(String.valueOf(pet_gender.get(position)));
        holder.breed.setText(String.valueOf(pet_breed.get(position)));
        holder.birthday.setText(String.valueOf(pet_birthday.get(position)));
        holder.color.setText(String.valueOf(pet_color.get(position)));
        holder.description.setText(String.valueOf(pet_description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updatePet.class);
                intent.putExtra("id",String.valueOf(pet_id.get(position)));
                intent.putExtra("name",String.valueOf(pet_name.get(position)));
                intent.putExtra("type",String.valueOf(pet_type.get(position)));
                intent.putExtra("gender",String.valueOf(pet_gender.get(position)));
                intent.putExtra("breed",String.valueOf(pet_breed.get(position)));
                intent.putExtra("birthday",String.valueOf(pet_birthday.get(position)));
                intent.putExtra("color",String.valueOf(pet_color.get(position)));
                intent.putExtra("description",String.valueOf(pet_description.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
       return pet_id.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, type, gender, breed, birthday, color, description;
        LinearLayout mainLayout;


         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            gender = itemView.findViewById(R.id.gender);
            breed = itemView.findViewById(R.id.breed);
            birthday = itemView.findViewById(R.id.birthday);
            color = itemView.findViewById(R.id.color);
            description = itemView.findViewById(R.id.description);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
