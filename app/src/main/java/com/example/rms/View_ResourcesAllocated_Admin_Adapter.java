package com.example.rms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import Data_classes.Hod_alloted_model;
import Data_classes.View_allocated_resources_admin;
import Data_classes.View_resources_model;

public class View_ResourcesAllocated_Admin_Adapter extends RecyclerView.Adapter<View_ResourcesAllocated_Admin_Adapter.ViewHolder> {

    Context context;
    ArrayList<View_allocated_resources_admin> data;

    public View_ResourcesAllocated_Admin_Adapter(Context context, ArrayList<View_allocated_resources_admin> data) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_allocated_resource_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View_allocated_resources_admin ham=data.get(position);
            String email=ham.getEmail();
            String str[] = email.split("hod");

            String Hod=str[0].toUpperCase();;
            String loc=ham.getLocation();
            String item= ham.getItem();
            String uid=ham.getId();
            String str2=""+uid.charAt(1)+uid.charAt(5)+uid.charAt(6)+uid.charAt(9)+uid.charAt(10);
        //System.out.println(Serial+category+item+quantity+"****************************(");

//        System.out.println("**********************************************");
//        System.out.println(category+" "+item);
        holder.t1.setText(Hod);
        holder.t2.setText(loc);
        holder.t3.setText(item);
        holder.t4.setText(str2);
        System.out.println("#E^%^%&^*(&(*&(*&(*^*&%^%$^%$&^(*)(");






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
//            c1=(ConstraintLayout) itemView.findViewById(R.id.constraint_card);



        }
    }

}
