package com.jinasoft.study03_ksj.Tread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jinasoft.study03_ksj.R;

public class ThreadMain extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_main);

        mTextView = findViewById(R.id.ThreadAct_TV);
        mProgressBar = findViewById(R.id.THreadAct_PB);
    }

    public void download(View view) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 오래 걸리는 일
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int parcent = i;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //UI갱신
                            mTextView.setText(parcent + "&");
                            mProgressBar.setProgress(parcent);
                        }
                    }); //  작업 코드에서 UI 갱신하는 부분이있어 문제가 됐다.
                }
            }
        }).start();
    }
}