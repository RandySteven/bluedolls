package com.example.a2301876316.fragments;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.a2301876316.DataHelper;
import com.example.a2301876316.MainActivity;
import com.example.a2301876316.R;
import com.example.a2301876316.Utils;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.factory.DollFactory;
import com.example.a2301876316.models.User;

import java.util.ArrayList;

public class DollFragment extends Fragment {

    ViewGroup vg;
    DataHelper dataHelper = null;
    Doll doll;
    User user;
    ArrayList<Doll>dolls;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vg = (ViewGroup)inflater.inflate(R.layout.doll_fragment, container, false);
        init();
        onClick();
        return vg;
    }
    Bundle bundle;

    String dollId;
    public void onCreate() {
        bundle = this.getArguments();
    }

    TextView tvDollName, tvDollDescription, tvUserName;
    ImageView ivDollImage;
    EditText etPhoneNumber;
    Button btnSendSMS;
    private void init(){
        if(dataHelper == null){
            dataHelper = new DataHelper(getContext());
        }
        dolls = dataHelper.getAllDolls();
        ivDollImage = vg.findViewById(R.id.ivDoll);
        tvDollName = vg.findViewById(R.id.tvDollName);
        tvDollDescription = vg.findViewById(R.id.tvDollDescription);
        tvUserName = vg.findViewById(R.id.tvUserName);
        etPhoneNumber = vg.findViewById(R.id.etPhoneNumber);
        btnSendSMS = vg.findViewById(R.id.btnSendSMS);

        doll = dataHelper.getDoll(dollId);
        user = (User) getActivity().getIntent().getSerializableExtra("User");
        ivDollImage.setImageBitmap(Utils.getImage(doll.getDollImage()));
        tvDollName.setText(doll.getDollName());
        tvDollDescription.setText(doll.getDollDescription());
        tvUserName.setText(doll.getUser().getUserName());
    }

    public void setDollId(String dollId) {
        this.dollId = dollId;
    }

    public String getDollId() {
        return dollId;
    }

    public DollFragment(String dollId){
        this.dollId = dollId;
    }

    private void onClick(){
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }
    private String phoneNo = "";

    private void sendMessage(){
        phoneNo = etPhoneNumber.getText().toString();

        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.putExtra("user", user);
        PendingIntent pi = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, intent, 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, doll.getDollDescription(), pi, null);
        Toast.makeText(getActivity().getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();
    }


}
