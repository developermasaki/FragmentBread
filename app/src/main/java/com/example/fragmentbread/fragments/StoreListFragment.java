package com.example.fragmentbread.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentbread.R;
import com.example.fragmentbread.adapters.StoreListAdapter;
import com.example.fragmentbread.adapters.selection.StoreListItemDetailsLookup;
import com.example.fragmentbread.databinding.FragmentStoreListBinding;
import com.example.fragmentbread.viewmodels.StoreState;

import java.util.ArrayList;
import java.util.List;

public class StoreListFragment extends Fragment {
    private FragmentStoreListBinding binding;
    private List<StoreState> storeDataSet;
    private SelectionTracker<Long> tracker;

    public StoreListFragment() {
        super(R.layout.fragment_store_list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeDataSet = new ArrayList<>(List.of(
                new StoreState(1, "Sourdough"),
                new StoreState(2, "Baguette"),
                new StoreState(3, "Croissant"),
                new StoreState(4, "Brioche"),
                new StoreState(5, "Bagel"),
                new StoreState(6, "Ciabatta"),
                new StoreState(7, "Rye bread"),
                new StoreState(8, "Focaccia"),
                new StoreState(9, "Pita"),
                new StoreState(10, "White bread")
        ));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentStoreListBinding.bind(view);
        ViewCompat.setOnApplyWindowInsetsListener(binding.recyclerView, (v, insets) -> {
            Insets bars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
            );
            v.setPadding(0, bars.top, 0, bars.bottom);
            return insets;
        });

        final RecyclerView recyclerView = binding.recyclerView;
        StoreListAdapter adapter = new StoreListAdapter(storeDataSet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        tracker = new SelectionTracker.Builder<>(
                "storeList",
                recyclerView,
                new StableIdKeyProvider(recyclerView),
                new StoreListItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .build();

        adapter.setTracker(tracker);

        if (tracker != null && savedInstanceState != null) {
            tracker.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        if (tracker != null) tracker.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
