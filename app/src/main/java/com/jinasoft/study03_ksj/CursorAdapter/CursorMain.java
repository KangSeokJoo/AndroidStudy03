package com.jinasoft.study03_ksj.CursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;

import com.jinasoft.study03_ksj.R;

import java.util.ArrayList;

public class CursorMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_main);

        GridView photoListView = (GridView)findViewById(R.id.CursorAct_GV);
        //사진 데이터              getContentResolver을 이용해서  프로바이더 접근

        Cursor cursor = getContentResolver().query
                (MediaStore.Images.Media.EXTERNAL_CONTENT_URI,  // FROM 절  이미지 정보를 가리키는 대표 URI EXTERNAL_~~~
                null,   // Select 절
                null,    // Where 절
                null,// Where 절
                MediaStore.Images.ImageColumns.DATE_TAKEN + "DESC"); // Order By 절
        //어댑터
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
        photoListView.setAdapter(adapter);


    }
}