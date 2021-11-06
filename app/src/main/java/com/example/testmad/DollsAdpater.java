package com.example.testmad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        private Button btnSeeDoll;
        public ViewHolder(View view){
            super(view);
            tvDollName = view.findViewById(R.id.tvDollName);
            tvDollDescription = view.findViewById(R.id.tvDollDescription);
            btnSeeDoll = view.findViewById(R.id.btnSeeDoll);
        }

        public TextView getTvDollName(){
            return tvDollName;
        }

        public TextView getTvDollDescription(){
            return tvDollDescription;
        }

        public Button getBtnSeeDoll(){
            return btnSeeDoll;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.doll_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTvDollName().setText(dolls.get(position).getDollName());
        viewHolder.getTvDollDescription().setText(dolls.get(position).getDollDescription());
        viewHolder.getBtnSeeDoll().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dolls.size();
    }
}
