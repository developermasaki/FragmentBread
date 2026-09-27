package com.example.fragmentbread.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentbread.databinding.ViewStoreListItemBinding;
import com.example.fragmentbread.viewmodels.StoreState;

import java.util.List;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder> {
    private final List<StoreState> _storeDataSet;
    private SelectionTracker<Long> tracker;

    public StoreListAdapter(List<StoreState> storeDataSet) {
        super();

        setHasStableIds(true);

        _storeDataSet = storeDataSet;
    }

    public void setTracker(SelectionTracker<Long> tracker) {
        this.tracker = tracker;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewStoreListItemBinding binding = ViewStoreListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(_storeDataSet.get(position).getStoreName());

        if (tracker != null) {
            boolean isSelected = tracker.isSelected(getItemId(position));
            viewHolder.getConstraintLayout().setActivated(isSelected);
        }
    }

    @Override
    public int getItemCount() {
        return _storeDataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return _storeDataSet.get(position).getStoreId();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ConstraintLayout constraintLayout;

        public ViewHolder(ViewStoreListItemBinding binding) {
            super(binding.getRoot());

            textView = binding.storeName;
            constraintLayout = binding.storeListItem;
        }

        public TextView getTextView() {
            return textView;
        }

        public ConstraintLayout getConstraintLayout() {
            return constraintLayout;
        }

        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return new ItemDetailsLookup.ItemDetails<>() {
                @Override
                public int getPosition() {
                    return getBindingAdapterPosition();
                }

                @Override
                public Long getSelectionKey() {
                    final int positon = getBindingAdapterPosition();
                    if (positon != RecyclerView.NO_POSITION) {
                        return getItemId();
                    }
                    return null;
                }
            };
        }
    }
}
