package com.example.yantai.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yantai.Bean.YanBean;
import com.example.yantai.R;

import com.example.yantai.REAdapter;
import com.example.yantai.Show;

import java.util.ArrayList;
import java.util.List;


public class One extends Fragment {

    private List<YanBean> list;
    private RecyclerView recyclerView;
    private REAdapter reAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initView(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reAdapter = new REAdapter(list,getContext());
        recyclerView.setAdapter(reAdapter);
        reAdapter.setOnItemClickListener(new REAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("content",list.get(position).getContent());
                intent.putExtra("img",list.get(position).getImg());
                intent.setClass(getContext(), Show.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
    public void initView(View view){
        recyclerView = view.findViewById(R.id.recycler);
        list = new ArrayList<>();
        YanBean bean = new YanBean("蓬莱阁","蓬莱阁与黄鹤楼、岳阳楼、滕王阁并称中国四大名楼。\n" +
                "·以“人间仙境”著称，广为流传的“八仙过海”神话传说便源于此。\n" +
                "·蓬莱阁临海，常年云雾缭绕，恍如身处仙境一般。运气好的话还能看到“海市蜃楼”。\n" +
                "·除了蓬莱阁外，蓬莱阁风景区还包含了田横山、登州博物馆、古船博物馆、黄渤海分界线等景点。\n" +
                "·山顶可远眺黄海与渤海的交界处，黄色与蓝色两股海水的交界线非常明显，还能远眺海中间的长岛",R.drawable.penglaidao);
        YanBean bean1 = new YanBean("长岛","长岛由151个岛屿组成，宛若一串柔美的珍珠洒落在渤海海峡，素有人间海市、海上仙山之美誉。长岛岛陆面积56平方公里左右，海域面积3541平方公里左右，海岸线长约187.8公里，有居民岛10个。这里气候宜人，风光秀丽，人文资源丰富，是理想的旅游避暑圣地。\n" +
                "\n" + "在辽东半岛与山东半岛之间的渤海海峡上，镶嵌着32颗苍翠如黛的明珠，这就是被世人誉为“海上仙山”的美丽群岛——长岛。长岛即庙岛群岛，蓝天碧水，阳光海滩，空气清新，山水相依，如诗如画，各岛有各岛之神奇。\n" +
                "\n" + "长岛组成：长山岛（南、北长山岛）、高山岛、侯矶岛、车由岛、大黑山岛、小黑山岛等。\n" +
                "\n" + "长岛旅游景区,包含九丈崖、月牙湾、林海、望福礁、黄渤海交汇线等多处景点景观。随着长岛旅游景区旅游基础设施和服务设施不断完善，景区档次大幅提升，知名度和美誉度不断提高，先后被评为国家级风景名胜区、国家级自然保护区、国家森林公园、国家地质公园。\n" +
                "\n" + "来长岛游玩的游客一定要先从蓬莱码头坐船上岛，想要真正体验长岛的风情还是建议在岛上住上一晚。",R.drawable.changdao);
        YanBean bean2 = new YanBean("养马岛","养马岛历史悠久，相传秦始皇东巡时在此饲养御马，并封为“皇家养马岛”，故而得此名。\n" +
                "·这里气候宜人，东端碧水金沙，是优良浴场，适合游泳玩水，而西端水深浪小，是天然良港。\n" +
                "·岛上有天马广场、赛马场、海滨浴场、海上世界、御笔苑等娱乐景点，是度假观光的好去处。\n" +
                "·岛四周盛产海参、扇贝、鲍鱼、对虾、牡蛎等海产品，也是海鲜爱好者的天堂。",R.drawable.yangmadao);
        list.add(bean);
        list.add(bean1);
        list.add(bean2);


    }
}