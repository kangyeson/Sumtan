package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Frag_my_user extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_frag_my_user, container, false);

        final Button bt1 = (Button) v.findViewById(R.id.mypage_management);
        final Button bt2 = (Button) v.findViewById(R.id.mypage_lookup);
        Button bt_setting=(Button)v.findViewById(R.id.setting_btn);


        bt_setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(), change_infor.class));
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.rgb(179,152,131));
                bt2.setBackgroundColor(Color.WHITE);
                getFragmentManager().beginTransaction().replace(R.id.my_fragment_page, new Frag_management()).commit();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(Color.rgb(179,152,131));
                bt1.setBackgroundColor(Color.WHITE);
                getFragmentManager().beginTransaction().replace(R.id.my_fragment_page, new Frag_lookup()).commit();
            }
        });

        return v;
    }

}