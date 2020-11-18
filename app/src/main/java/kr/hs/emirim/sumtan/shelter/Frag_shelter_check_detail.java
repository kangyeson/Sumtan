package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.hs.emirim.sumtan.R;

public class Frag_shelter_check_detail extends Fragment {

    private View view;
    private String user_name;
    private String user_email;
    private String user_tele;
    private EditText userName;
    private EditText userEmail;
    private EditText userTele;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_shelter_check_detail,container,false);

        user_name=getActivity().getIntent().getExtras().getString("uname");
        user_email=getActivity().getIntent().getExtras().getString("uemail");
        user_tele=getActivity().getIntent().getExtras().getString("utele");

        userName=view.findViewById(R.id.userName);
        userEmail=view.findViewById(R.id.userEmail);
        userTele=view.findViewById(R.id.userTele);

        userName.setText(user_name);
        userEmail.setText(user_email);
        userTele.setText(user_tele);

        return view;
    }
}