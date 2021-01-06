package com.jinasoft.study03_ksj.DataBase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jinasoft.study03_ksj.R;

public class DataBaseMain extends AppCompatActivity {

    private static final int REQUEST_CODE_INSERT = 1000;
    private MemoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivityForResult(new Intent(DataBaseMain.this, MemoActivity.class),REQUEST_CODE_INSERT);
        });
        ListView listView = (ListView)findViewById(R.id.DataBaseAct_list);
        MemoDbHelper dbHelper = MemoDbHelper.getInstance(this);
        Cursor cursor = dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME,
                null,null,null,null,null,null);
//        MemoAdapter adapter = new MemoAdapter(this, cursor);
//        listView.setAdapter(adapter);
        mAdapter = new MemoAdapter(this, cursor);
        listView.setAdapter(mAdapter);
        //리스트 클릭 시 메모 내용 표시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DataBaseMain.this, MemoActivity.class);
                //클릭한 시점의 아이템을 얻음
                Cursor cursor = (Cursor) mAdapter.getItem(i);
                //커서에서 제목과 내용을 얻음
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_TITLE));
                String contents = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS));
                // 인텐트에 id와 함께 저장
                intent.putExtra("id", l);
                intent.putExtra("title", title);
                intent.putExtra("contents", contents);
                //메모 액티비티 시작
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                final long deleteId = id;
                //삭제할 것인지 다이얼로그 표시
                AlertDialog.Builder builder = new AlertDialog.Builder(DataBaseMain.this);

                builder.setTitle("메모 삭제");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db =
                                MemoDbHelper.getInstance(DataBaseMain.this).getWritableDatabase();
                        int deletdCount = db.delete(MemoContract.MemoEntry.TABLE_NAME,
                                MemoContract.MemoEntry._ID + " = " + deleteId, null);
                        if (deletdCount == 0) {
                            Toast.makeText(DataBaseMain.this, "삭제에 문제가 발생하였습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            mAdapter.swapCursor(getMemoCursor());
                            Toast.makeText(DataBaseMain.this, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            }
        });
    }
    private Cursor getMemoCursor(){
        MemoDbHelper dbHelper = MemoDbHelper.getInstance(this);
        return dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME,
                null,null,null,null,null,null, MemoContract.MemoEntry._ID + " DESC");
    } // 끝 리미트에 추가문구 넣으므로 내림차순으로 보여주

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 메모가 정상적으로 삽입되었다면, 메모 목록을 갱신
        if (requestCode == REQUEST_CODE_INSERT && resultCode == RESULT_OK){
            mAdapter.swapCursor(getMemoCursor());
        }
    }

    private static class MemoAdapter extends CursorAdapter{

        public MemoAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tittletext = (TextView)view.findViewById(android.R.id.text1);
            tittletext.setText(cursor.getString(cursor.getColumnIndexOrThrow(
                    MemoContract.MemoEntry.COLUMN_NAME_TITLE)));
        }
    }
}