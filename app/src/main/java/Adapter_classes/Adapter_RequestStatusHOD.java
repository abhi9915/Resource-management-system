package Adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rms.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Data_classes.AllResourcesAttribute;

public class Adapter_RequestStatusHOD extends RecyclerView.Adapter<Adapter_RequestStatusHOD.Complaint_card_holder>{

    public Adapter_RequestStatusHOD(Context context,ArrayList<AllResourcesAttribute> data) {
        this.context=context;
        this.list = data;
    }

    ArrayList<AllResourcesAttribute> list;
    Context context;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference();
    @NonNull
    @Override
    public Complaint_card_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        System.out.println("hello**************");
        View view=inflater.inflate(R.layout.card_request_status_hod,parent,false);
        return new Complaint_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Complaint_card_holder holder, int position) {
        AllResourcesAttribute ccm=list.get(position);


        System.out.println(ccm.email+"   "+ccm.status+"   "+ccm.item+"   "+ccm.location+"8783792034378290");
        holder.t1.setText(ccm.email);
        holder.t2.setText(ccm.item);
        holder.t3.setText(ccm.quantity);



        if(ccm.status.equals("0"))
            holder.t4.setText("Pending");
        else if(ccm.status.equals("1"))
            holder.t4.setText("Resource Allotted");
        else if(ccm.status.equals("-1"))
            holder.t4.setText("Request Rejected");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Complaint_card_holder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4;
        public Complaint_card_holder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.name);
            t2=(TextView) itemView.findViewById(R.id.email_id);
            t3=(TextView) itemView.findViewById(R.id.textView14);
            t4=(TextView) itemView.findViewById(R.id.textView16);
        }
    }
}

