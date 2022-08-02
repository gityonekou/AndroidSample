package com.example.androidsample.app;
/*
 * Fragmentサンプルを表示するフラグメントです。サンプル03にて呼び出されます。
 * 詳細はFragmentSampe0301を参照のこと
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidsample.R;

public class FragmentSampe0301Fragment extends Fragment {

    private static final String KEY = "Message";

    /**
     * このフラグメントのインスタンスを生成して返します。
     * @param name 表示するメッセージ
     * @return このフラグメントのインスタンス
     */
    public static FragmentSampe0301Fragment newInstance(String name) {
        // このインスタンスを生成
        FragmentSampe0301Fragment fragment = new FragmentSampe0301Fragment();
        // Bundleに渡された文字列を設定
        Bundle barg = new Bundle();
        barg.putString(KEY, name);
        fragment.setArguments(barg);
        return fragment;
    }

    /** フラグメントのViewを生成して返します。 */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_sample0301, container, false);
    }

    /*
     * onViewCreatedメソッドは上記onCreateViewメソッドが呼ばれた後にすぐに呼び出されるメソッドです。
     * onViewCreatedメソッドの処理内容をonCreateViewメソッドの中に記述しているものも多々あるようです。
     * ただ、このメソッドが用意されているのでonCreateViewではViewを生成、onViewCreatedではViewの中身の設定
     * という風に分けて考えたほうがいいとは思います。
     *
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            TextView textView = view.findViewById(R.id.fragment_sample0301_text_fragment);
            textView.setText(args.getString(KEY));
        }
    }
}
