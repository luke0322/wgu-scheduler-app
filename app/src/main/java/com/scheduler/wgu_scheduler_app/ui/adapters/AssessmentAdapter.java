package com.scheduler.wgu_scheduler_app.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;
import com.scheduler.wgu_scheduler_app.ui.assessment.AssessmentDetailFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView;
        private final TextView startDateItemView;
        private final TextView endDateItemView;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentTitle);
            startDateItemView = itemView.findViewById(R.id.assessmentStartDate);
            endDateItemView = itemView.findViewById(R.id.assessmentEndDate);

            itemView.setOnClickListener(l -> {
                int position = getAdapterPosition();
                final AssessmentEntity current = assessments.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("courseId", current.getCourseId());
                bundle.putInt("assessmentId", current.getAssessmentId());
                AssessmentDetailFragment adf = AssessmentDetailFragment.newInstance();
                adf.setArguments(bundle);
                Utils.switchFragment(fa, R.id.container_assessment, adf);
            });
        }
    }

    private List<AssessmentEntity> assessments;
    private final Context context;
    private final LayoutInflater inflater;
    private FragmentActivity fa;

    public AssessmentAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (assessments != null){
            AssessmentEntity current = assessments.get(position);
            holder.assessmentItemView.setText(current.getAssessmentName());
            holder.startDateItemView.setText(current.getAssessmentStartDate());
            holder.endDateItemView.setText(current.getAssessmentEndDate());
        }
        else {
            holder.assessmentItemView.setText("No assessments available!");
        }
    }

    public void setAssessments(List<AssessmentEntity> _assessments, FragmentActivity _fa){
        assessments = _assessments;
        fa = _fa;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (assessments != null){
            return assessments.size();
        }
        else return 0;
    }
}
