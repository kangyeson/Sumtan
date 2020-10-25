package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_my_shelter extends Fragment {

    private View view;
    private static final String TAG="My Tag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_my_shelter,container,false);

        Button iv_modify=(Button)view.findViewById(R.id.iv_modify);
        iv_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Modify_shelter.class));
            }
        });

        return view;
    }
}