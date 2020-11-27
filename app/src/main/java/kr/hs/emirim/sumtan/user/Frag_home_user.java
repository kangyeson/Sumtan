package kr.hs.emirim.sumtan.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kr.hs.emirim.sumtan.R;

public class Frag_home_user extends Fragment implements View.OnClickListener {

    private View view;
    private ImageButton ad1_user;
    private ImageButton ad2_user;
    private ImageButton ad3_user;
    private ImageButton ad4_user;
    private ImageButton ad6_user;
    private ImageButton ad7_user;
    private ImageButton ad8_user;
    private ImageButton ad9_user;
    private Button btn_back;
    private WebView wv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_home_user,container,false);

        ad1_user=(ImageButton)view.findViewById(R.id.ad1_user);
        ad2_user=(ImageButton)view.findViewById(R.id.ad2_user);
        ad3_user=(ImageButton)view.findViewById(R.id.ad3_user);
        ad4_user=(ImageButton)view.findViewById(R.id.ad4_user);
        ad6_user=(ImageButton)view.findViewById(R.id.ad6_user);
        ad7_user=(ImageButton)view.findViewById(R.id.ad7_user);
        ad8_user=(ImageButton)view.findViewById(R.id.ad8_user);
        ad9_user=(ImageButton)view.findViewById(R.id.ad9_user);
        btn_back=(Button)view.findViewById(R.id.btn_back);

        wv=view.findViewById(R.id.wv);

        WebSettings webSettings = wv.getSettings(); //자바 스크립트 사용을 할 수 있도록 합니다.

        webSettings.setJavaScriptEnabled(true); wv.setWebViewClient(new WebViewClient(){ //페이지 로딩이 끝나면 호출됩니다.
            @Override public void onPageFinished(WebView view,String url){
                Toast.makeText(getActivity(),"로딩 끝", Toast.LENGTH_SHORT).show();
            }
        });

        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;

                if(keyCode==KeyEvent.KEYCODE_BACK){
                    if(wv.canGoBack()){
                        wv.goBack();
                    }else{
                        wv.setVisibility(View.INVISIBLE);
                    }
                }

                return true;
            }
        });

        ad1_user.setOnClickListener(this);
        ad2_user.setOnClickListener(this);
        ad3_user.setOnClickListener(this);
        ad4_user.setOnClickListener(this);
        ad6_user.setOnClickListener(this);
        ad7_user.setOnClickListener(this);
        ad8_user.setOnClickListener(this);
        ad9_user.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String address;
        wv.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.setVisibility(View.INVISIBLE);
            }
        });
        switch (v.getId()){
            case R.id.ad1_user:
                address="https://ekara.org/thebom/article/read/13594";
                wv.loadUrl(address);
                break;
            case R.id.ad2_user:
                address="https://www.ekara.org/thebom/article/read/13672";
                wv.loadUrl(address);
                break;
            case R.id.ad3_user:
                address="https://www.youtube.com/watch?v=5NE4n0WsYh0";
                wv.loadUrl(address);
                break;
            case R.id.ad4_user:
                address="https://www.youtube.com/watch?v=cyAE2oht71w";
                wv.loadUrl(address);
                break;
            case R.id.ad6_user:
                address="https://www.youtube.com/watch?v=mRjO7Vs2-08";
                wv.loadUrl(address);
                break;
            case R.id.ad7_user:
                address="https://paju.ekara.org/#video-section";
                wv.loadUrl(address);
                break;
            case R.id.ad8_user:
                address="http://www.pawinhand.kr/goods/goods_list.php?cateCd=001";
                wv.loadUrl(address);
                break;
            case R.id.ad9_user:
                address="http://www.pawinhand.kr/main/html.php?htmid=service/statistics.html";
                wv.loadUrl(address);
                break;

        }
    }

}