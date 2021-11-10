package com.example.a2301876316;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollsAdpater extends RecyclerView.Adapter<DollsAdpater.ViewHolder>{
    private ArrayList<Doll> dolls = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    User user = DollsFragment.user;
    DollsAdpater(Context context, ArrayList<Doll> dolls){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.dolls = dolls;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivDollImage;
        private TextView tvDollName;
        private TextView tvDollDescription;
        private Button btnSeeDoll;
        private Button btnDelete;
        private Button btnUpdate;
        public ViewHolder(View view){
            super(view);
            ivDollImage = view.findViewById(R.id.ivDoll);
            tvDollName = view.findViewById(R.id.tvDollName);
            tvDollDescription = view.findViewById(R.id.tvDollDescription);
            btnSeeDoll = view.findViewById(R.id.btnSeeDoll);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnUpdate = view.findViewById(R.id.btnUpdate);
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

        public Button getBtnDelete(){
            return btnDelete;
        }

        public Button getBtnUpdate(){
            return btnUpdate;
        }

        public ImageView getIvDollImage(){
            return ivDollImage;
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
        System.out.println(user.getUserName());
        viewHolder.getIvDollImage().setImageResource(dolls.get(position).getDollImage());
        viewHolder.getTvDollName().setText(dolls.get(position).getDollName());
        viewHolder.getTvDollDescription().setText(dolls.get(position).getDollDescription());
        viewHolder.getBtnSeeDoll().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position);
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new DollFragment(position))
                        .commit();
            }
        });
        if(user.getUserName().equals(dolls.get(position).getUser().getUserName())
        && user.getUserEmail().equals(dolls.get(position).getUser().getUserEmail())){
            viewHolder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dolls.remove(position);
                    notifyDataSetChanged();
                }
            });
        }else{
            if(!user.getUserRole().equals("Admin")){
                viewHolder.getBtnDelete().setVisibility(View.GONE);
            }
        }

        if(user.getUserName().equals(dolls.get(position).getUser().getUserName())
                && user.getUserEmail().equals(dolls.get(position).getUser().getUserEmail())){
            viewHolder.getBtnUpdate().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, new UpdateDollFragment(position))
                            .commit();
                }
            });
        }else{
            if(!user.getUserRole().equals("Admin")){
                viewHolder.getBtnUpdate().setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return dolls.size();
    }
}
