package com.scheduler.wgu_scheduler_app.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;
import com.scheduler.wgu_scheduler_app.ui.term.TermDetailFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        private final TextView startDateItemView;
        private final TextView endDateItemView;
        private TermViewHolder(View itemView){
            super(itemView);
            termItemView = itemView.findViewById(R.id.termTitle);
            startDateItemView = itemView.findViewById(R.id.termStartDate);
            endDateItemView = itemView.findViewById(R.id.termEndDate);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final TermEntity current = terms.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("id", current.getTermId());
                bundle.putString("title",current.getTermTitle());
                bundle.putString("startDate", current.getTermStartDate());
                bundle.putString("endDate", current.getTermEndDate());
                TermDetailFragment tdf = TermDetailFragment.newInstance();
                tdf.setArguments(bundle);
                Utils.switchFragment(fa, R.id.container_term, tdf);
            });
        }
    }

    private List<TermEntity> terms;
    private final Context context;
    private final LayoutInflater inflater;
    private FragmentActivity fa;
    public TermAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(terms != null){
            TermEntity current = terms.get(position);
            holder.termItemView.setText(current.getTermTitle());
            holder.startDateItemView.setText(current.getTermStartDate());
            holder.endDateItemView.setText(current.getTermEndDate());
        }
        else {
            holder.termItemView.setText("No terms available!");
        }
    }
    public void setTerms(List<TermEntity> _terms, FragmentActivity _fa){
        terms = _terms;
        fa = _fa;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(terms != null){
            return terms.size();
        }
        else return 0;
    }
}

