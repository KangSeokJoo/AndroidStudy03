package com.jinasoft.study03_ksj.DynamicUI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinasoft.study03_ksj.R;

public class ArticleFragment extends Fragment { //article은 기사를 의미

    public static final String ARG_POSITION = "position";
    private int mCurrentPosition = -1;


    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 화면이 회전되면 이전에 선택된 위치를 복원
        if(savedInstanceState != null){
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        // 화면 레이아웃은 TextView 하나만 있는 레이아웃을 사용

        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();

        if (args != null){
            //프래그먼트가 생성되었을 경우
            updateArticleView(args.getInt(ARG_POSITION));
        }else if (mCurrentPosition != -1 ){
            //화면 회전 등의 경우
            updateArticleView(mCurrentPosition);
        }
    }
    // 선택된 기사를 표시
    public void updateArticleView(int position){
        TextView article = (TextView)getView().findViewById(R.id.DynmicAct_ArticleTV);
        article.setText(Articles.Articles[position]); // Articles 는 static으로 아티클즈 자바에서 갖구온 포지션을 사용
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // 화면이 회전될 때, 선택된 위치를 저장
        outState.putInt(ARG_POSITION, mCurrentPosition); // 이 스테이트에서 나가면 포지션을 기억해놔라 (화면 회전 동일)
    }
}