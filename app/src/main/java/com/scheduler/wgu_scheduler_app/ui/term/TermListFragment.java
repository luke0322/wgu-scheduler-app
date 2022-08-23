package com.scheduler.wgu_scheduler_app.ui.term;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;
import com.scheduler.wgu_scheduler_app.ui.adapters.TermAdapter;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class TermListFragment extends Fragment {
    private Handler handler = new Handler();
    private TermViewModel mViewModel;

    public static com.scheduler.wgu_scheduler_app.ui.term.TermListFragment newInstance() {
        return new com.scheduler.wgu_scheduler_app.ui.term.TermListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.term_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        mViewModel.getAll(getActivity().getApplication(), result -> {
            if (result instanceof Result.Success){
                RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerview);
                recyclerView.setNestedScrollingEnabled(false);
                final TermAdapter adapter = new TermAdapter(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                List<TermEntity> terms = (List<TermEntity>) ((Result.Success) result).data;
                adapter.setTerms(terms, getActivity());
            }
        }, handler);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Utils.switchFragment(getActivity(), R.id.container_term, TermFragment.newInstance());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
