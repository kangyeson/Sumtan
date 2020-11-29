package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class Frag_home_shelter extends Fragment implements View.OnClickListener {

    private View view;
    private ImageButton ad2_shelter;
    private ImageButton ad3_shelter;
    private ImageButton ad4_shelter;
    private ImageButton ad5_shelter;
    private Button btn_back;
    private WebView wv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_home_shelter,container,false);

        ad2_shelter=(ImageButton)view.findViewById(R.id.ad2_shelter);
        ad3_shelter=(ImageButton)view.findViewById(R.id.ad3_shelter);
        ad4_shelter=(ImageButton)view.findViewById(R.id.ad4_shelter);
        ad5_shelter=(ImageButton)view.findViewById(R.id.ad5_shelter);
        btn_back=(Button)view.findViewById(R.id.btn_back);
        wv=view.findViewById(R.id.wv);

        WebSettings webSettings = wv.getSettings(); //자바 스크립트 사용을 할 수 있도록 합니다.

        webSettings.setJavaScriptEnabled(true); wv.setWebViewClient(new WebViewClient(){ //페이지 로딩이 끝나면 호출됩니다.
            @Override public void onPageFinished(WebView view,String url){

            }
        });

        ad2_shelter.setOnClickListener(this);
        ad3_shelter.setOnClickListener(this);
        ad4_shelter.setOnClickListener(this);
        ad5_shelter.setOnClickListener(this);

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
            case R.id.ad2_shelter:
                address="https://secure.donus.org/animals/pay/step1_direct?dontype=P10101&period=pledge&price=20000&_ga=2.215230295.1369925755.1606368633-1914975462.1605673261&_gac=1.226724719.1606369265.CjwKCAiAnvj9BRA4EiwAuUMDf-BFeMN0KOnZN1HIghRQo8-NE-5ICWHjNqC3-2WaEKOgfrlLCi8bnRoCAucQAvD_BwE";
                wv.loadUrl(address);
                break;
            case R.id.ad3_shelter:
                address="https://www.animals.or.kr/report/print/52229";
                wv.loadUrl(address);
                break;
            case R.id.ad4_shelter:
                address="https://www.animals.or.kr/report/print/39803";
                wv.loadUrl(address);
                break;
            case R.id.ad5_shelter:
                address="https://tumblbug.com/roartale?ref=discover";
                wv.loadUrl(address);
                break;
        }
    }

}