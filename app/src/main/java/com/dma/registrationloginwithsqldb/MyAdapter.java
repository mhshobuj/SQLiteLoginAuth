package com.dma.registrationloginwithsqldb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{

    ArrayList<Model> arrayList;
    Context context;

    public MyAdapter(ArrayList<Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_data_card_view,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.category_name_tv.setText(new StringBuilder("Category Name: ").append(arrayList.get(position).getCategory()));
        holder.sub_category_name_tv.setText(new StringBuilder("Sub_Category Name: ").append(arrayList.get(position).getSub_category()));
        holder.name_tv.setText(new StringBuilder("Name: ").append(arrayList.get(position).getU_name()));
        holder.phone_tv.setText(new StringBuilder("Phone: ").append(arrayList.get(position).getPhone()));
        holder.address_tv.setText(new StringBuilder("Address: ").append(arrayList.get(position).getAddress()));
        holder.date_tv.setText(new StringBuilder("Date: ").append(arrayList.get(position).getDate()));

        holder.setListener((view, pos) -> {
            Intent intent = new Intent(context, DetailsUsers.class);
            intent.putExtra("ID", arrayList.get(pos).getId());
            context.startActivity(intent);

        });

        holder.delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Conformation !!!");
                builder.setMessage("Are you sure to delete ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(context);
                        int result = dbHelper.deleteUser(arrayList.get(position).getId());
                        if (result > 0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            arrayList.remove(arrayList.get(position));
                            notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface RecyclerViewClickListener{
        void onClick(View view, int pos);
    }

    public class myViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        TextView category_name_tv, sub_category_name_tv, name_tv,phone_tv, address_tv, date_tv;
        ImageView delete_iv;

        RecyclerViewClickListener listener;

        public void setListener(RecyclerViewClickListener listener) {
            this.listener = listener;
        }

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            category_name_tv = (TextView) itemView.findViewById(R.id.category_name_tv);
            sub_category_name_tv = (TextView) itemView.findViewById(R.id.sub_category_name_tv);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
            phone_tv = (TextView) itemView.findViewById(R.id.phone_tv);
            address_tv = (TextView) itemView.findViewById(R.id.address_tv);
            date_tv = (TextView) itemView.findViewById(R.id.date_tv);

            delete_iv = (ImageView) itemView.findViewById(R.id.delete_iv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
