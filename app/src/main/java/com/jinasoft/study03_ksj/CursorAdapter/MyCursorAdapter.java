package com.jinasoft.study03_ksj.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jinasoft.study03_ksj.R;

public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }
// 두번째 생성자로 생성하고 세번째 파라미터값을 바꾼 이유(false) true상태면 DB 변동시 자동으로 쿼리를 다시 수행함 UI스레드에서 수행하여 앱이 느려질수있음
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_photo, viewGroup, false);
    } // newview 맨처음 레이아웃을 생성하는 부분 , bindview는 데이터를 뷰에 바인딩하여 하면에 실제로 표시하는 부분
    //프로바이더를 통해 미디어 디비를 접근할 것이고 그중 미디어스토어 이미스, 미디어 데이타 칼럼에 저장되어있는값을 가져올것
    //실제 경로가 아닌 content:// = URI 형태로 접근할수있다.
    @Override
    public void bindView(View view, Context context, Cursor cursor) { // 첫 파라미터 view는 각 아이템의 레이아웃, 세번째 파라미터 cursor는 각 아이템의 데이터
        //디바이스에 모든 사진 정보를 미디어 DB라고 데이터베이스에 자동으로 저장
        ImageView imageView = (ImageView)view.findViewById(R.id.CursorAct_IV);
        // 사진 경로 가지오기(URI)
        String uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        // 사진을 이미지뷰에 표시하기
//        imageView.setImageURI(Uri.parse(uri));
        Glide.with(context).load(uri).into(imageView);
    }
}
