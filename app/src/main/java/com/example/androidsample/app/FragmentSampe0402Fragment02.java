package com.example.androidsample.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidsample.R;

/**
 * Fragmentサンプルを表示するフラグメント02です。サンプル0402にて呼び出されます。
 * 詳細はFragmentSampe0401を参照のこと
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class FragmentSampe0402Fragment02 extends Fragment {
    private static final String KEY = "Counter";
    private static final String TITLE = "(通知版)Fragment02:";

    private int counter = 0;
    /**
     * このインスタンスを生成して返します。
     * @param count カウント値
     * @return このインスタンス
     */
    public static FragmentSampe0402Fragment02 newInstance(int count) {
        FragmentSampe0402Fragment02 fragment = new FragmentSampe0402Fragment02();
        Bundle args = new Bundle();
        args.putInt(KEY, count);
        fragment.setArguments(args);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_sample0402, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            int count = args.getInt(KEY);
            String str = TITLE + count;
            this.counter = count + 1;

            TextView textView = view.findViewById(R.id.fragment_sample0401_textview02);
            textView.setText(str);
        }
        Button moveBtn = view.findViewById(R.id.fragment_sample0401_button02);
        moveBtn.setOnClickListener( v -> {
            Activity activity = getActivity();
            if(activity instanceof FragmentSampe0402FragmentActionListener) {
                FragmentSampe0402FragmentActionListener action =
                        (FragmentSampe0402FragmentActionListener)activity;
                action.replaceFragment(
                        FragmentSampe0402FragmentActionListener.FRAGMENT_SAMPLE0402_FRAGMENT01,
                        this.counter);
            }
        });
        Button returnBtn = view.findViewById(R.id.fragment_sample0401_pop02);
        returnBtn.setOnClickListener( v -> {
            Activity activity = getActivity();
            if(activity instanceof FragmentSampe0402FragmentActionListener) {
                FragmentSampe0402FragmentActionListener action =
                        (FragmentSampe0402FragmentActionListener)activity;
                action.removeFragment(
                        FragmentSampe0402FragmentActionListener.FRAGMENT_SAMPLE0402_FRAGMENT02);
            }
        });
    }
}
