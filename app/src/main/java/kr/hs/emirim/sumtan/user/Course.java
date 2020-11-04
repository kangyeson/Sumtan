package kr.hs.emirim.sumtan.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kr.hs.emirim.sumtan.R;

public class Course extends Fragment implements View.OnClickListener {

    private View view;
    private Button submission_Button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.search_course,container,false);

        submission_Button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submission_Button:
                FragmentDialog dialog=new FragmentDialog();
                //dialog.show();
                break;
        }
    }
}