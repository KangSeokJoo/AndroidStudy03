package com.jinasoft.study03_ksj.DynamicUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jinasoft.study03_ksj.R;

public class HeadlinesFragment extends ListFragment {
    // 이 프래그먼트를 포함하는 액티비티는 반드시 이 인터페이스를 구현해야함
    interface OnHeadlineSelectedListener{
        void onHeadlineSelected(int position);
    }
    private OnHeadlineSelectedListener headlistener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Articles의 Headlines 배열을 사용하여 리스트 뷰를 위한 ArrayAdapter를 생성
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Articles.Headlines));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 이 프래그먼트를 포함하는 Context는
        // 반드시 Onheadlinlistenr를 구현해야 한다
        // 그렇지 않으면 ClasssCastException 발생 인터페이스를 구현하도록 onAttach() 메서드에서 강제로 연결  하고 있음
        try {
            headlistener = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // 선택된 위치를 액티비티에 알려 줌
        if (headlistener != null){
            headlistener.onHeadlineSelected(position);
        }
    }
}