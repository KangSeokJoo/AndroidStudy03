package com.jinasoft.study03_ksj.DynamicUI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.jinasoft.study03_ksj.R;

public class DynamicMain extends AppCompatActivity
implements HeadlinesFragment.OnHeadlineSelectedListener {


    // Bundle 객체가 argument로 전달함 아그먼트로 기사 번호를 전달 받아 기사를 표시,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_main);

        //layout-large의 레이아웃에는 DynmicAct_FL가 없음
        if(findViewById(R.id.DynmicAct_FL) != null) {
            //화면 회전 시에 HeadlinesFragment가 재생성되는 것을 방지
            if (savedInstanceState == null) {
                HeadlinesFragment headlinesFragment = new HeadlinesFragment();
                // headlinesFragment를 R.id.fragment_container 영역에 추가
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.DynmicAct_FL, headlinesFragment)
                        .commit();
            }
        }
    }

    // HeadlinesFragment의 기사 제목이 선택되었을 때 호출
    @Override
    public void onHeadlineSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment)getSupportFragmentManager().findFragmentById(R.id.DynmicAct_ArticlelargeFL);
        // layout-large의 경우 null이 아님
        if(articleFragment == null) {
            //ArticleFragment프래그먼트를 생성
            ArticleFragment newArticleFragment = new ArticleFragment();
            // Argument로 기사 번호 전달
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newArticleFragment.setArguments(args);
            //R.id.DynmicAct_FL 아이디를 가진 영역의
            //프래그먼트를 articleFragment로 교체하고
            //프래그먼트 매니저의 backstack에 쌓는다
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.DynmicAct_FL, newArticleFragment) //교체
                    .addToBackStack(null) // 교체할때 프래그먼트 백스특에 추가 내부적으로 관리하는 스택택
                    .commit();
        }else {
            articleFragment.updateArticleView(position);
        }
    }
    //해드라인프레그먼트를 표시하고 기사를 클릭했을때 해드라인 셀렉티드 콜백이 호출됨 아티클프래그먼트로 교체
    // oncreate 메서드에서 세이브인스턴스스테이트가 널일때만 헤드라인 프레그먼트를 추가하고있다, 이 조건이 없으면 화면이 회전될 때마다
    // 기존 해드라인프래그먼트가 생성되기 때문에 반드시 세이브인스턴스스테이트가 널인지 검사하는 로직을 잊으면 안됌*
}