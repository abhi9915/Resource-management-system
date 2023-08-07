package Adapter_classes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rms.My_alloted_hod;
import com.example.rms.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import Data_classes.View_allocated_resources_admin;

public class Hod_alloted_Adapter extends RecyclerView.Adapter<Hod_alloted_Adapter.ViewHolder> {

    Context context;
    ArrayList<View_allocated_resources_admin> data;
    int sno = 1;
    String key;

    public Hod_alloted_Adapter(Context context, ArrayList<View_allocated_resources_admin> data) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hod_alloted_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            View_allocated_resources_admin ham=data.get(position);

//            int Serial=ham.getSno();
            String Id=ham.getId();
            //String category=ham.getCategory();
            String item= ham.getItem();
            String loc=ham.getLocation();
            key = data.get(position).getId();
//        System.out.println("**********************************************");
//        System.out.println(key);
        String str2=""+Id.charAt(1)+Id.charAt(5)+Id.charAt(6)+Id.charAt(9)+Id.charAt(10);
        holder.t1.setText(String.valueOf(sno++));
        holder.t2.setText(loc);
        holder.t3.setText(item);
        holder.t4.setText(str2);



        holder.c1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view)
            {
                deleteDialog(Id,item);
//                int pos=holder.getAdapterPosition();
//                DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
//                ref.child("Allocated Resources").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot ds: snapshot.getChildren())
//                        {
////                            if(ds.getKey().equals(ds.child("key").getValue().toString()))
////                            {
//                            key=ds.getKey();
//
//
//                            break;
////                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private void deleteDialog(String Id,String item) {

        System.out.println(Id+" "+item);
        //alert
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference();
        builder.setTitle("Delete");
        //set builder
        builder.setMessage("Do you want to Delete this Resource?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPostReference.child("Allocated Resources").child(Id).removeValue();
                HashMap<String , String > mp = new HashMap<>();
                String cat;
                if(item.equals("Chair") || item.equals("Table"))
                    cat="furniture";
                else
                    cat="electric";
                mp.put("cat_name",cat);
                mp.put("alloted","No");
                mPostReference.child("resources").child(item).child(Id).setValue(mp);
                Intent k=new Intent(context, My_alloted_hod.class);
                context.startActivity(k);


            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        //create and show dialog
        builder.create().show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3,t4;

        CardView c1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.Sno_tv);
            t2=(TextView) itemView.findViewById(R.id.cat_tv);
            t3=(TextView) itemView.findViewById(R.id.item_tv);
            t4=(TextView) itemView.findViewById(R.id.qunatity_tv);

            c1= itemView.findViewById(R.id.constraint_card);


        }
    }

}
