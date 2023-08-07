package com.example.rms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import Data_classes.Hod_alloted_model;

public class View_ResourcesHod_Adapter extends RecyclerView.Adapter<View_ResourcesHod_Adapter.ViewHolder> {

    Context context;
    ArrayList<Hod_alloted_model> data;

    public View_ResourcesHod_Adapter(Context context, ArrayList<Hod_alloted_model> data) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_resource_hod,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Hod_alloted_model ham=data.get(position);
            int Serial=ham.getId();
            String category=ham.getCategory();
            String item= ham.getItem();
            String quantity=ham.getQunatity();

        System.out.println(Serial+category+item+quantity+"****************************(");

//        System.out.println("**********************************************");
//        System.out.println(category+" "+item);
        holder.t1.setText(String.valueOf(Serial));
        holder.t2.setText(category);
        holder.t3.setText(item);
        holder.t4.setText(quantity);





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3,t4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.Sno_tv);
            t2=(TextView) itemView.findViewById(R.id.cat_tv);
            t3=(TextView) itemView.findViewById(R.id.item_tv);
            t4=(TextView) itemView.findViewById(R.id.qunatity_tv);



        }
    }

}
