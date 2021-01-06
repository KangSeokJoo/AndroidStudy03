package com.jinasoft.study03_ksj.CountDown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jinasoft.study03_ksj.R;

public class CountMain extends AppCompatActivity {

    private TextView mTextView;
    private CountTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_main);

        mTextView = (TextView)findViewById(R.id.CountAct_cntTV);
    }

    public class CountTask extends AsyncTask<Integer, Integer, Integer>{
        // 어싱크테스크의 세가지 제네릭을 모두 사용,
        // CountTask 실행시 Integer 값을 받음 초기값 0
        @Override
        protected Integer doInBackground(Integer... params) { // 1초간 멈춤 효과 퍼빌리시프로그래스 params0 으로 UI 갱신을 요청
            do {
                //1초 섞기
                try{
                    Thread.sleep(1000);
                    //0부터 10까지 1씩 증가
                    params[0]++;
                    //증가된 값을 텍스트뷰에 표시해 줘
                    publishProgress(params[0]);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }while (params[0] < 10);
            return params[0]; // 10번반복 후 마지막 온포스트익스큐트로 나감
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //1증가한 값이 텍스트뷰에 표시
            //텍스트뷰에 증가된 값 표기
            mTextView.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Integer integer) {
            // 종료시 마지막 값 텍스트뷰에 표시시
            mTextView.setText(String.valueOf(integer));
        }
    }


    public void clear(View view) {
        mTask.cancel(true);
        mTextView.setText("0");
    }

    public void start(View view) {
        mTask = new CountTask();
        //익스큐트 탈출
        mTask.execute(0);
    }
}