package com.example.fragmentbread.adapters.selection;

import android.view.MotionEvent;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.OnItemActivatedListener;

import org.jspecify.annotations.NonNull;

public class StoreListOnItemActivatedListener implements OnItemActivatedListener<Long> {
    @Override
    public boolean onItemActivated(ItemDetailsLookup.@NonNull ItemDetails item, @NonNull MotionEvent e) {
        return false;
    }
}
