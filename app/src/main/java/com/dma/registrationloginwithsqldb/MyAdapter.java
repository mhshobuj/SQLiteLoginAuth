package com.dma.registrationloginwithsqldb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
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
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
