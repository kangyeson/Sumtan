package kr.hs.emirim.sumtan.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.hs.emirim.sumtan.R;

public class AddressCard extends DialogFragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private AddressItem adapter;
    private Fragment fragment;
    private String addressForSearch;
    private DialogResult dialogResult;

    public AddressCard() {

    }

    public AddressCard getInstance() {
        AddressCard ad=new AddressCard();
        return ad;
    }

    interface DialogResult{
        void onClick(String addressForSearch);
    }

    public void setDialogResult(DialogResult dialogResult){
        this.dialogResult = dialogResult;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.address_card, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        Button seoul=(Button)view.findViewById(R.id.seoul);
        Button gyeonggi=(Button)view.findViewById(R.id.gyeonggi);
        Button incheon=(Button)view.findViewById(R.id.incheon);
        Button gangwon=(Button)view.findViewById(R.id.gangwon);
        Button daejeon=(Button)view.findViewById(R.id.daejeon);
        Button sejong=(Button)view.findViewById(R.id.sejong);
        Button chungnam=(Button)view.findViewById(R.id.chungnam);
        Button chungbuk=(Button)view.findViewById(R.id.chungbuk);
        Button busan=(Button)view.findViewById(R.id.busan);
        Button ulsan=(Button)view.findViewById(R.id.ulsan);
        Button gyeongnam=(Button)view.findViewById(R.id.gyeongnam);
        Button geyongbuk=(Button)view.findViewById(R.id.geyongbuk);
        Button daegu=(Button)view.findViewById(R.id.daegu);
        Button gwangju=(Button)view.findViewById(R.id.gwangju);
        Button jeonllanam=(Button)view.findViewById(R.id.jeonllanam);
        Button jeollabuk=(Button)view.findViewById(R.id.jeollabuk);
        Button jeju=(Button)view.findViewById(R.id.jeju);

        Button address_card_down=(Button)view.findViewById(R.id.address_card_down);
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        /////////확인버튼/////////
        address_card_down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (fragment != null) {
                    DialogFragment dialogFragment = (DialogFragment) fragment;
                    dialogResult.onClick(addressForSearch);
                    dialogFragment.dismiss();
                }
            }
        });

        ///////////서울 클릭했을때////////////////
        seoul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                seoul.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("강남구"));
                adapter.addItem(new Address("강동구"));
                adapter.addItem(new Address("강북구"));
                adapter.addItem(new Address("강서구"));
                adapter.addItem(new Address("관악구"));
                adapter.addItem(new Address("광진구"));
                adapter.addItem(new Address("구로구"));
                adapter.addItem(new Address("금천구"));
                adapter.addItem(new Address("노원구"));
                adapter.addItem(new Address("도봉구"));
                adapter.addItem(new Address("동대문구"));
                adapter.addItem(new Address("동작구"));
                adapter.addItem(new Address("마포구"));
                adapter.addItem(new Address("서대문구"));
                adapter.addItem(new Address("서초구"));
                adapter.addItem(new Address("성동구"));
                adapter.addItem(new Address("성북구"));
                adapter.addItem(new Address("양천구"));
                adapter.addItem(new Address("영등포구"));
                adapter.addItem(new Address("용산구"));
                adapter.addItem(new Address("은평구"));
                adapter.addItem(new Address("종로구"));
                adapter.addItem(new Address("중구"));
                adapter.addItem(new Address("중랑구"));

                recyclerView.setAdapter(adapter);//
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "서울 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////경기 클릭했을때////////////////
        gyeonggi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gyeonggi.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("가평군"));
                adapter.addItem(new Address("고양시 덕양구"));
                adapter.addItem(new Address("고양시 일산동구"));
                adapter.addItem(new Address("고양시 일산서구"));
                adapter.addItem(new Address("과천시"));
                adapter.addItem(new Address("광명시"));
                adapter.addItem(new Address("광주시"));
                adapter.addItem(new Address("구리시"));
                adapter.addItem(new Address("군포시"));
                adapter.addItem(new Address("김포시"));
                adapter.addItem(new Address("남양주시"));
                adapter.addItem(new Address("동두천시"));
                adapter.addItem(new Address("부천시"));
                adapter.addItem(new Address("성남시 분당구"));
                adapter.addItem(new Address("성남시 수정구"));
                adapter.addItem(new Address("성남시 중원구"));
                adapter.addItem(new Address("수원시 권선구"));
                adapter.addItem(new Address("수원시 영통구"));
                adapter.addItem(new Address("수원시 장안구"));
                adapter.addItem(new Address("수원시 팔달구"));
                adapter.addItem(new Address("시흥시"));
                adapter.addItem(new Address("안산시 단원구"));
                adapter.addItem(new Address("안산시 상록구"));
                adapter.addItem(new Address("안성시"));
                adapter.addItem(new Address("안양시 동안구"));
                adapter.addItem(new Address("안양시 만안구"));
                adapter.addItem(new Address("양주시"));
                adapter.addItem(new Address("양평군"));
                adapter.addItem(new Address("여주시"));
                adapter.addItem(new Address("연천군"));
                adapter.addItem(new Address("오산시"));
                adapter.addItem(new Address("용인시 기흥구"));
                adapter.addItem(new Address("용인시 수지구"));
                adapter.addItem(new Address("용인시 처인구"));
                adapter.addItem(new Address("의왕시"));
                adapter.addItem(new Address("의정부시"));
                adapter.addItem(new Address("이천시"));
                adapter.addItem(new Address("파주시"));
                adapter.addItem(new Address("평택시"));
                adapter.addItem(new Address("포천시"));
                adapter.addItem(new Address("하남시"));
                adapter.addItem(new Address("화성시"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "경기 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////인천 클릭했을때////////////////
        incheon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                incheon.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("강화군"));
                adapter.addItem(new Address("계양구"));
                adapter.addItem(new Address("남동구"));
                adapter.addItem(new Address("동구"));
                adapter.addItem(new Address("미추홀구"));
                adapter.addItem(new Address("부평구"));
                adapter.addItem(new Address("서구"));
                adapter.addItem(new Address("연수구"));
                adapter.addItem(new Address("옹진군"));
                adapter.addItem(new Address("중구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "인천 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////강원 클릭했을때////////////////
        gangwon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gangwon.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("강릉시"));
                adapter.addItem(new Address("고성군"));
                adapter.addItem(new Address("동해시"));
                adapter.addItem(new Address("삼척시"));
                adapter.addItem(new Address("속초시"));
                adapter.addItem(new Address("양구군"));
                adapter.addItem(new Address("영양군"));
                adapter.addItem(new Address("영월군"));
                adapter.addItem(new Address("원주시"));
                adapter.addItem(new Address("인제군"));
                adapter.addItem(new Address("정선군"));
                adapter.addItem(new Address("철원군"));
                adapter.addItem(new Address("춘천시"));
                adapter.addItem(new Address("태백시"));
                adapter.addItem(new Address("평창군"));
                adapter.addItem(new Address("홍천군"));
                adapter.addItem(new Address("화천군"));
                adapter.addItem(new Address("횡성군"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "강원 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////대전 클릭했을때////////////////
        daejeon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                daejeon.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("대덕구"));
                adapter.addItem(new Address("동구"));
                adapter.addItem(new Address("서구"));
                adapter.addItem(new Address("유성구"));
                adapter.addItem(new Address("중구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "대전 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////세종 클릭했을때////////////////
        sejong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sejong.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("세종시"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "세종 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////충남 클릭했을때////////////////
        chungnam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                chungnam.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("계룡시"));
                adapter.addItem(new Address("공주시"));
                adapter.addItem(new Address("금산군"));
                adapter.addItem(new Address("논산시"));
                adapter.addItem(new Address("당진시"));
                adapter.addItem(new Address("보령시"));
                adapter.addItem(new Address("부여군"));
                adapter.addItem(new Address("서산시"));
                adapter.addItem(new Address("서천군"));
                adapter.addItem(new Address("아산시"));
                adapter.addItem(new Address("예산군"));
                adapter.addItem(new Address("천안시 동남구"));
                adapter.addItem(new Address("천안시 서북구"));
                adapter.addItem(new Address("청양군"));
                adapter.addItem(new Address("태안군"));
                adapter.addItem(new Address("홍선군"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "충남 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////충북 클릭했을때////////////////
        chungbuk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                chungbuk.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("괴산군"));
                adapter.addItem(new Address("단양군"));
                adapter.addItem(new Address("보은군"));
                adapter.addItem(new Address("영동군"));
                adapter.addItem(new Address("옥천군"));
                adapter.addItem(new Address("음성군"));
                adapter.addItem(new Address("제천시"));
                adapter.addItem(new Address("증평군"));
                adapter.addItem(new Address("진천군"));
                adapter.addItem(new Address("청주시 상당구"));
                adapter.addItem(new Address("청주시 서원구"));
                adapter.addItem(new Address("청주시 청원구"));
                adapter.addItem(new Address("청주시 흥덕구"));
                adapter.addItem(new Address("충주시"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "충북 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////부산 클릭했을때////////////////
        busan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                busan.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("강서구"));
                adapter.addItem(new Address("금정구"));
                adapter.addItem(new Address("기장군"));
                adapter.addItem(new Address("남구"));
                adapter.addItem(new Address("동구"));
                adapter.addItem(new Address("동래구"));
                adapter.addItem(new Address("부산진구"));
                adapter.addItem(new Address("북구"));
                adapter.addItem(new Address("사상구"));
                adapter.addItem(new Address("사하구"));
                adapter.addItem(new Address("서구"));
                adapter.addItem(new Address("수영구"));
                adapter.addItem(new Address("연제구"));
                adapter.addItem(new Address("영도구"));
                adapter.addItem(new Address("중구"));
                adapter.addItem(new Address("해운대구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "부산 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////울산 클릭했을때////////////////
        ulsan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ulsan.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("남구"));
                adapter.addItem(new Address("동구"));
                adapter.addItem(new Address("북구"));
                adapter.addItem(new Address("울주군"));
                adapter.addItem(new Address("중구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "울산 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////경남 클릭했을때////////////////
        gyeongnam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gyeongnam.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("거제시"));
                adapter.addItem(new Address("거창군"));
                adapter.addItem(new Address("고성군"));
                adapter.addItem(new Address("김해시"));
                adapter.addItem(new Address("남해군"));
                adapter.addItem(new Address("밀양시"));
                adapter.addItem(new Address("사천시"));
                adapter.addItem(new Address("산청군"));
                adapter.addItem(new Address("양산시"));
                adapter.addItem(new Address("의령군"));
                adapter.addItem(new Address("진주시"));
                adapter.addItem(new Address("창원시 마산합포구"));
                adapter.addItem(new Address("창원시 마산회원구"));
                adapter.addItem(new Address("창원시 성산구"));
                adapter.addItem(new Address("창원시 의창구"));
                adapter.addItem(new Address("창원시 진해구"));
                adapter.addItem(new Address("통영시"));
                adapter.addItem(new Address("하동군"));
                adapter.addItem(new Address("함안군"));
                adapter.addItem(new Address("함양군"));
                adapter.addItem(new Address("합천군"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "경남 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////경북 클릭했을때////////////////
        geyongbuk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                geyongbuk.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("경산시"));
                adapter.addItem(new Address("경주시"));
                adapter.addItem(new Address("고령군"));
                adapter.addItem(new Address("구미시"));
                adapter.addItem(new Address("군위군"));
                adapter.addItem(new Address("김천시"));
                adapter.addItem(new Address("문경시"));
                adapter.addItem(new Address("봉화군"));
                adapter.addItem(new Address("성주시"));
                adapter.addItem(new Address("성주군"));
                adapter.addItem(new Address("안동시"));
                adapter.addItem(new Address("영덕군"));
                adapter.addItem(new Address("영양군"));
                adapter.addItem(new Address("영천시"));
                adapter.addItem(new Address("예천군"));
                adapter.addItem(new Address("울릉군"));
                adapter.addItem(new Address("울진군"));
                adapter.addItem(new Address("의성군"));
                adapter.addItem(new Address("청도군"));
                adapter.addItem(new Address("칠곡군"));
                adapter.addItem(new Address("포항시 남구"));
                adapter.addItem(new Address("포항시 북구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "경북 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////대구 클릭했을때////////////////
        daegu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                daegu.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("남구"));
                adapter.addItem(new Address("달서구"));
                adapter.addItem(new Address("달성군"));
                adapter.addItem(new Address("동구"));
                adapter.addItem(new Address("북구"));
                adapter.addItem(new Address("서구"));
                adapter.addItem(new Address("수성구"));
                adapter.addItem(new Address("중구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "대구 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////경주 클릭했을때////////////////
        gwangju.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                gwangju.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("광산구"));
                adapter.addItem(new Address("남구"));
                adapter.addItem(new Address("동구"));
                adapter.addItem(new Address("북구"));
                adapter.addItem(new Address("서구"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "경주 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////전남 클릭했을때////////////////
        jeonllanam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                jeonllanam.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("강진군"));
                adapter.addItem(new Address("고흥군"));
                adapter.addItem(new Address("곡성군"));
                adapter.addItem(new Address("광양시"));
                adapter.addItem(new Address("구례군"));
                adapter.addItem(new Address("나주시"));
                adapter.addItem(new Address("담양군"));
                adapter.addItem(new Address("목포시"));
                adapter.addItem(new Address("무안군"));
                adapter.addItem(new Address("보성군"));
                adapter.addItem(new Address("순천시"));
                adapter.addItem(new Address("신안군"));
                adapter.addItem(new Address("여수시"));
                adapter.addItem(new Address("영광군"));
                adapter.addItem(new Address("영암군"));
                adapter.addItem(new Address("완도군"));
                adapter.addItem(new Address("장성군"));
                adapter.addItem(new Address("장흥군"));
                adapter.addItem(new Address("진도군"));
                adapter.addItem(new Address("함평군"));
                adapter.addItem(new Address("해남군"));
                adapter.addItem(new Address("화순군"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "전남 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////전북 클릭했을때////////////////
        jeollabuk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                jeollabuk.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("고창군"));
                adapter.addItem(new Address("군산시"));
                adapter.addItem(new Address("김제시"));
                adapter.addItem(new Address("남원시"));
                adapter.addItem(new Address("무주군"));
                adapter.addItem(new Address("부안군"));
                adapter.addItem(new Address("순창군"));
                adapter.addItem(new Address("완주군"));
                adapter.addItem(new Address("익산시"));
                adapter.addItem(new Address("임실군"));
                adapter.addItem(new Address("장수군"));
                adapter.addItem(new Address("전주시 덕진구"));
                adapter.addItem(new Address("전주시 완산구"));
                adapter.addItem(new Address("정읍시"));
                adapter.addItem(new Address("진안군"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "전북 " + item.getAddress2();
                    }
                });

            }
        });

        ///////////제주 클릭했을때////////////////
        jeju.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                jeju.setOnClickListener(this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AddressItem();

                adapter.addItem(new Address("서귀포시"));
                adapter.addItem(new Address("제주시"));

                recyclerView.setAdapter(adapter);
                adapter.setItemClickListeneer(new AddressItemClickListeneer() {
                    @Override
                    public void onItemClick(AddressItem.ViewHolder holder, View view, int position) {
                        Address item = adapter.getItem(position);
                        addressForSearch = "제주 " + item.getAddress2();
                    }
                });

            }
        });

        return view;
    }

    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    @Override
    public void onClick(View v) {

    }
}
