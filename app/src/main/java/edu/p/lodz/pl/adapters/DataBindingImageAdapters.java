package edu.p.lodz.pl.adapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingImageAdapters {

    @BindingAdapter("android:src")
    public static void setActivityIcon(ImageView view, int resource) {
        view.setImageResource(resource);
    }

}
