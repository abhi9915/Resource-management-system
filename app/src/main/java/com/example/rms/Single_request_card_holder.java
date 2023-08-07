package com.example.rms;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Single_request_card_holder extends RecyclerView.ViewHolder {
    TextView t1,t2,t3,t4;
    Button acc,rej;
    public Single_request_card_holder(@NonNull View itemView) {
        super(itemView);

        t1=(TextView) itemView.findViewById(R.id.name);
        t2=(TextView) itemView.findViewById(R.id.email_id);
        t3=(TextView) itemView.findViewById(R.id.textView14);
        t4=(TextView) itemView.findViewById(R.id.textView16);
        acc=(Button) itemView.findViewById(R.id.buttonaccept);
        rej=(Button) itemView.findViewById(R.id.buttonreject);




    }
}
