package org.angmarch.views;

import android.content.Context;
import android.os.Parcelable;

import java.util.List;

public class BetterSpinnerAdapter<T extends Parcelable> extends NiceSpinnerBaseAdapter {
    public final List<T> items;

    BetterSpinnerAdapter(Context context, List<T> items, int textColor, int backgroundSelector, SpinnerTextFormatter spinnerTextFormatter) {
        super(context, textColor, backgroundSelector, spinnerTextFormatter);
        this.items = items;
    }

    public int getCount() {
        return this.items.size() - 1;
    }

    public T getItem(int position) {
        return position >= this.selectedIndex ? this.items.get(position + 1) : this.items.get(position);
    }

    public T getItemInDataset(int position) {
        return this.items.get(position);
    }
}