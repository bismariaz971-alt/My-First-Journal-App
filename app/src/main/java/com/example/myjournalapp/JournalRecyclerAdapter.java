package com.example.myjournalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.JournalViewHolder> {
    private List<JournalEntry> journalsList;
    private OnItemClickListner listner;

    public interface OnItemClickListner{
        void onClick(JournalEntry entry);

    }



public JournalRecyclerAdapter(List<JournalEntry> journals,OnItemClickListner listner){
    this.journalsList = journals;
    this.listner = listner;


}

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_row,parent,false);

    return new JournalViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {

    JournalEntry entry = journalsList.get(position);
    holder.tv_Title.setText(entry.getTitle());
    holder.tv_Date.setText(new SimpleDateFormat("MMM dd,yyyy").format(new Date(entry.getDate())));
    holder.tv_journal.setText(entry.getContent());

    holder.itemView.setOnClickListener(v -> {
        if(listner!= null){
            listner.onClick(entry);
        }
    });

    }

    @Override
    public int getItemCount() {
        return journalsList.size();
    }


    public class JournalViewHolder extends RecyclerView.ViewHolder{

    TextView tv_Title;
    TextView tv_Date;
    TextView tv_journal;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Title = itemView.findViewById(R.id.tv_row_title);
            tv_Date = itemView.findViewById(R.id.tv_row_date);
            tv_journal = itemView.findViewById(R.id.tv_Journal);




        }
    }
}
