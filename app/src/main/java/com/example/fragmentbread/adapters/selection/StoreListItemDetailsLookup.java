package com.example.fragmentbread.adapters.selection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentbread.adapters.StoreListAdapter;

public class StoreListItemDetailsLookup extends ItemDetailsLookup<Long> {
    private final RecyclerView _RecyclerView;

    public StoreListItemDetailsLookup(RecyclerView recyclerView) {
        _RecyclerView = recyclerView;
    }

    @Override
    @Nullable
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent event) {
        View view = _RecyclerView.findChildViewUnder(event.getX(), event.getY());
        if (view != null) {
            RecyclerView.ViewHolder holder = _RecyclerView.getChildViewHolder(view);
            if (holder instanceof StoreListAdapter.ViewHolder) {
                return ((StoreListAdapter.ViewHolder) holder).getItemDetails();
            }
        }
        return null;
    }
}