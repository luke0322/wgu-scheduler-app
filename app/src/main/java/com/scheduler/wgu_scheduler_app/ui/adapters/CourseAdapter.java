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
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;
import com.scheduler.wgu_scheduler_app.ui.course.CourseDetailFragment;
import com.scheduler.wgu_scheduler_app.ui.term.TermDetailFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        private final TextView startDateItemView;
        private final TextView endDateItemView;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.courseTitle);
            startDateItemView = itemView.findViewById(R.id.courseStartDate);
            endDateItemView = itemView.findViewById(R.id.courseEndDate);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final CourseEntity current = courses.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("courseId", current.getCourseId());
                bundle.putInt("termId", current.getTermId());
                CourseDetailFragment cdf = CourseDetailFragment.newInstance();
                cdf.setArguments(bundle);
                Utils.switchFragment(fa, R.id.container_course, cdf);
            });
        }
    }

    private List<CourseEntity> courses;
    private final Context context;
    private final LayoutInflater inflater;
    private FragmentActivity fa;

    public CourseAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if (courses != null){
            CourseEntity current = courses.get(position);
            holder.courseItemView.setText(current.getCourseTitle());
            holder.startDateItemView.setText(current.getCourseStartDate());
            holder.endDateItemView.setText(current.getCourseEndDate());
        }
        else {
            holder.courseItemView.setText("No terms available!");
        }
    }

    public void setCourses(List<CourseEntity> _courses, FragmentActivity _fa){
        courses = _courses;
        fa = _fa;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (courses != null){
            return courses.size();
        }
        else return 0;
    }

}
