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

public class Frag_search_user extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_search_user,container,false);

        Button searchbtn = (Button)view.findViewById(R.id.search_button);
        Button search_icon_button = (Button)view.findViewById(R.id.search_icon_button);

        search_icon_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getFragmentManager().beginTransaction().replace(R.id.my_search_page, new Course()).commit();
            }
        });

        searchbtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddressCard.class);
            startActivity(intent);
        });

//
        return view;
    }
}