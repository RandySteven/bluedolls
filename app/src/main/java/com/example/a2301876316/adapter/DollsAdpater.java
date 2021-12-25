package com.example.a2301876316.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.a2301876316.DataHelper;
import com.example.a2301876316.R;
import com.example.a2301876316.Utils;
import com.example.a2301876316.fragments.DollFragment;
import com.example.a2301876316.fragments.DollsFragment;
import com.example.a2301876316.fragments.UpdateDollFragment;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollsAdpater extends ArrayAdapter<Doll> {
    DataHelper dataHelper = null;
    private ArrayList<Doll> dolls;
    private LayoutInflater mInflater;
    private Context context;

    User user = DollsFragment.user;
    public DollsAdpater(Context context, ArrayList<Doll> dolls){
        super(context, 0, dolls);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.dolls = dolls;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Doll doll = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.doll_item, parent, false);
        }
        ImageView ivDollImage;
        TextView tvDollName;
        TextView tvDollDescription;
        Button btnSeeDoll;
        Button btnDelete;
        Button btnUpdate;
        ivDollImage = convertView.findViewById(R.id.ivDoll);
        tvDollName = convertView.findViewById(R.id.tvDollName);
        tvDollDescription = convertView.findViewById(R.id.tvDollDescription);
        btnSeeDoll = convertView.findViewById(R.id.btnSeeDoll);
        btnDelete = convertView.findViewById(R.id.btnDelete);
        btnUpdate = convertView.findViewById(R.id.btnUpdate);

        ivDollImage.setImageBitmap(Utils.getImage(doll.getDollImage()));
        tvDollName.setText(doll.getDollName());
        tvDollDescription.setText(doll.getDollDescription());
        btnSeeDoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position);
                String dollId = dolls.get(position).getDollId();
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new DollFragment(dollId))
                        .commit();
            }
        });

        if(user.getUserName().equals(dolls.get(position).getUser().getUserName())
        && user.getUserEmail().equals(dolls.get(position).getUser().getUserEmail())){
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dataHelper == null)
                        dataHelper = new DataHelper(getContext());

                    dataHelper.deleteDoll(dolls.get(position).getDollId());

                    notifyDataSetChanged();
                }
            });
        }else{
            if(!user.getUserRole().equals("Admin")){
                btnDelete.setVisibility(View.GONE);
            }
        }

        if(user.getUserName().equals(dolls.get(position).getUser().getUserName())
                && user.getUserEmail().equals(dolls.get(position).getUser().getUserEmail())){
            btnUpdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, new UpdateDollFragment(dolls.get(position).getDollId()))
                            .commit();
                }
            });
        }else{
            if(!user.getUserRole().equals("Admin")){
                btnUpdate.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    public int getItemCount() {
        return dolls.size();
    }
}
