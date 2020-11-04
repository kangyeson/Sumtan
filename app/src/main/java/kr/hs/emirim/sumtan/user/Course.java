package kr.hs.emirim.sumtan.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import kr.hs.emirim.sumtan.R;

public class Course extends Fragment{

    private Button submission_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        return inflater.inflate(R.layout.search_course,container,false);

    }
}