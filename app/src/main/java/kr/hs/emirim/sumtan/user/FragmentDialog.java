package kr.hs.emirim.sumtan.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import kr.hs.emirim.sumtan.R;


public class FragmentDialog extends DialogFragment implements View.OnClickListener {

    public FragmentDialog() {
    }

    public FragmentDialog getInstance() {
        FragmentDialog fd=new FragmentDialog();
        return fd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog, container, false);

        Button btn_confirm=(Button)view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
