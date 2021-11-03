package com.example.testmad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testmad.models.Doll;

import java.util.ArrayList;

public class DollsAdpater extends RecyclerView.Adapter<DollsAdpater.ViewHolder>{
    private ArrayList<Doll> dolls = new ArrayList<>();
    private LayoutInflater mInflater;

    DollsAdpater(Context context, ArrayList<Doll> dolls){
        this.mInflater = LayoutInflater.from(context);
        this.dolls = dolls;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDollName;
        private TextView tvDollDescription;
        public ViewHolder(View view){
            super(view);
            tvDollName = view.findViewById(R.id.tvDollName);
            tvDollDescription = view.findViewById(R.id.tvDollDescription);
        }

        public TextView getTvDollName(){
            return tvDollName;
        }

        public TextView getTvDollDescription(){
            return tvDollDescription;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.doll_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTvDollName().setText(dolls.get(position).getDollName());
        viewHolder.getTvDollDescription().setText(dolls.get(position).getDollDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dolls.size();
    }
}
