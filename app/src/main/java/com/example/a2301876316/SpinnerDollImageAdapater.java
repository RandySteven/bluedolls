package com.example.a2301876316;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class SpinnerDollImageAdapater extends ArrayAdapter<Integer> {

    private Integer[] dollImages;

    public SpinnerDollImageAdapater(Context context, Integer[] dollImages){
        super(context, R.layout.support_simple_spinner_dropdown_item, dollImages);
        this.dollImages = dollImages;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getImagePosition(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getImagePosition(position);
    }

    private View getImagePosition(int position){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(dollImages[position]);
        imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}
