package Adapter_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rms.R;
import com.example.rms.Service_team_approval;
import com.example.rms.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import papaya.in.sendmail.SendMail;

public class Adapter_ServiceTeamApproval extends RecyclerView.Adapter<Adapter_ServiceTeamApproval.ST_card_holder>{

    public Adapter_ServiceTeamApproval(Context context,ArrayList<UserDetails> data) {
        this.context=context;
        this.list = data;
    }

    ArrayList<UserDetails> list;
    Context context;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference().child("ServiceTeam");
    @NonNull
    @Override
    public ST_card_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        System.out.println("hello**************");
        View view=inflater.inflate(R.layout.service_team_approval,parent,false);
        return new ST_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ST_card_holder holder, int position) {
        UserDetails ccm=list.get(position);

            holder.t1.setText(ccm.username);
            holder.t2.setText(ccm.email);
            String id = ccm.getId();
            holder.b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SendMail mail = new SendMail("nitcresourcemanagement01@gmail.com", "nitc1234",
                            "alokpandey181@gmail.com",
                            "Testing Email Sending",
                            "Yes, it's working well\nI will use it always.");
                    mail.execute();
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ref.child(id).child("approved").setValue("1");
                            Intent i = new Intent(context, Service_team_approval.class);
                            context.startActivity(i);
                            ((Activity)view.getContext()).finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            });


            holder.b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ref.child(id).removeValue();
                            Intent i = new Intent(context,Service_team_approval.class);
                            context.startActivity(i);
                            ((Activity)view.getContext()).finish();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ST_card_holder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        Button b1,b2;
        public ST_card_holder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.name);
            t2=(TextView) itemView.findViewById(R.id.email_id);
            b1=(Button) itemView.findViewById(R.id.buttonaccept);
            b2=(Button) itemView.findViewById(R.id.buttonreject);
        }
    }
}

