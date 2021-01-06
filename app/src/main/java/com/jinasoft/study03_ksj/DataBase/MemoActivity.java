package com.jinasoft.study03_ksj.DataBase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.jinasoft.study03_ksj.R;

public class MemoActivity extends AppCompatActivity {

    private EditText TitleEDT;
    private EditText ContentEDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        TitleEDT = (EditText)findViewById(R.id.DataBaseAct_EDT);
        ContentEDT = (EditText)findViewById(R.id.DataBaseAct_EDT2);
    }

    @Override
    public void onBackPressed() {
        //DB에 저장하는 처리
        String title = TitleEDT.getText().toString();
        String contents = ContentEDT.getText().toString();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, title);
        contentValues.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS, contents);
        SQLiteDatabase db = MemoDbHelper.getInstance(this).getWritableDatabase();
        long newRowId = db.insert(MemoContract.MemoEntry.TABLE_NAME, null, contentValues);
        if (newRowId == -1){
            Toast.makeText(this, "저장에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
        }// 뒤로 가기 원래의 동작이 실행됨
        super.onBackPressed();
    }
}