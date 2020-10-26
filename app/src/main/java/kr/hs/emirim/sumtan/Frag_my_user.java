package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class Frag_my_user extends Fragment implements View.OnClickListener {

    private TextView apply;
    private Button btn_logout;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_frag_my_user, container, false);

        mAuth = FirebaseAuth.getInstance();

        apply=(TextView)v.findViewById(R.id.apply);
        btn_logout=(Button)v.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}