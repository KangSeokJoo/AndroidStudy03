package com.jinasoft.study03_ksj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.jinasoft.study03_ksj.Broadcast.LocalBroad;
import com.jinasoft.study03_ksj.CountDown.CountMain;
import com.jinasoft.study03_ksj.CursorAdapter.CursorMain;
import com.jinasoft.study03_ksj.DataBase.DataBaseMain;
import com.jinasoft.study03_ksj.DynamicUI.DynamicMain;
import com.jinasoft.study03_ksj.Language.LanguageMain;
import com.jinasoft.study03_ksj.Tread.ThreadMain;


public class SelectStudyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_study_list);

        Button btn1 = (Button) findViewById(R.id.Selcet_DynamicBTN);
        Button btn2 = (Button) findViewById(R.id.Selcet_LanguageBTN);
        Button btn3 = (Button) findViewById(R.id.Selcet_BroadBTN);
        Button btn4 = (Button) findViewById(R.id.Selcet_CursorBTN);
        Button btn5 = (Button) findViewById(R.id.Selcet_ThreadBTN);
        Button btn6 = (Button) findViewById(R.id.Selcet_CountBTN);
        Button btn7 = (Button) findViewById(R.id.Selcet_DataBaseBTN);
//
        btn1.setOnClickListener(view -> {
            Log.d("btn1_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, DynamicMain.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(view -> {
            Log.d("btn2_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, LanguageMain.class);
            startActivity(intent);
        });
        btn3.setOnClickListener(view -> {
            Log.d("btn3_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, LocalBroad.class);
            startActivity(intent);
        });
        btn4.setOnClickListener(view -> {
            Log.d("btn4_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, CursorMain.class);
            startActivity(intent);
        });
        btn5.setOnClickListener(view -> {
            Log.d("btn5_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, ThreadMain.class);
            startActivity(intent);
        });
        btn6.setOnClickListener(view -> {
            Log.d("btn6_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, CountMain.class);
            startActivity(intent);
        });
        btn7.setOnClickListener(view -> {
            Log.d("btn7_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, DataBaseMain.class);
            startActivity(intent);
        });

//        btn.setOnClickListener(new View.OnClickListener() {  원래 쓰던 Onclick 식
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SelectStudyList.this, EmailSave.class);
//                startActivity(intent);
//            }
//        });

//      public void GO_Act(view view){ // 다른 버튼을 눌러도 그 버튼이 한바퀴 스위치문 한바퀴를 도는게 느리지 않나 생각
//      swich(v.getId()){
//          case R.id. ~~~ ;
//              Intent in1 = new Intent ~~~~
//              startActivity(1);
//              break;
//      }


//    public void Go_Act7(View view){                         메소드로 온크릭을 기능 구현했었으나 위 방법으로 작성할 예정이였으나,
//        Intent intent = new Intent(this, ScrollView.class);
//        startActivity(intent);
//    }
//    public void Go_Act6(View view){
//        Intent intent = new Intent(this, Browser.class);
//        startActivity(intent);
//    }
//    public void Go_Act5(View view){
//        Intent intent = new Intent(this, OptionMenu.class);
//        startActivity(intent);
//    }
//    public void Go_Act4(View view){
//        Intent intent = new Intent(this, AppInQuest.class);
//        startActivity(intent);
//    }
//    public void Go_Act3(View view){
//        Intent intent = new Intent(this, KakaoMessage.class);
//        startActivity(intent);
//    }
//    public void Go_Act2(View view){
//        Intent intent = new Intent(this, ActionCall.class);
//        startActivity(intent);
//    }
//    public void Go_Act1(View view){
//        Intent intent = new Intent(this, ActivityDataSend.class);
//        startActivity(intent);
//    }

    }
}