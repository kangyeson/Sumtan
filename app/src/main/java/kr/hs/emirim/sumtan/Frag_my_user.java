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

public class Frag_my_user extends Fragment {

    private TextView apply;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_frag_my_user, container, false);

       apply=(TextView)v.findViewById(R.id.apply);




        return v;
    }

}