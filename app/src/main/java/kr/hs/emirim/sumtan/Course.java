package kr.hs.emirim.sumtan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.List;

public class Course extends Fragment {

    List<Shelter> shelterList;
    Context context;

    public Course(List<Shelter> shelterList, Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.search_course,container,false);

    }
}