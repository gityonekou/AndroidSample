package com.example.androidsample.app;
/*
 * Fragmentサンプルを表示するフラグメント02です。サンプル04にて呼び出されます。
 * 詳細はFragmentSampe0401を参照のこと
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidsample.R;

public class FragmentSampe0401Fragment02 extends Fragment {
    private static final String KEY = "Counter";
    private static final String TITLE = "Fragment02:";

    private int counter = 0;
    /**
     * このインスタンスを生成して返します。
     * @param count カウント値
     * @return このインスタンス
     */
    public static FragmentSampe0401Fragment02 newInstance(int count) {
        FragmentSampe0401Fragment02 fragment = new FragmentSampe0401Fragment02();
        Bundle args = new Bundle();
        args.putInt(KEY, count);
        fragment.setArguments(args);
        return fragment;
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
            // サンプルにあるgetFragmentManager()は非推奨となったため注意
            // 今回のサンプルのようなフラグメントの切り替えは上位(MainActivity)にイベントを通知して
            // 切り替えてもらう形のほうがフラグメントとしての独立性が保たれるのであとでこちらの方法の勉強が必要
            // また、getParentFragmentManagerで取得する場合はgetSupportFragmentManager同様に必ず
            // インスタンスが帰る(nullになることはない)のでif文によるnull判定は不要です
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(null);
            // Mainでフラグメントの貼り付けを行っているのでMainに処理を通知する方法を別サンプルとして作成すること
            transaction.replace(R.id.fragment_sample0401_container,
                    FragmentSampe0401Fragment01.newInstance(this.counter));
            transaction.commit();
        });

        Button returnBtn = view.findViewById(R.id.fragment_sample0401_pop02);
        returnBtn.setOnClickListener( v -> {
            FragmentManager manager = getParentFragmentManager();
            manager.popBackStack();
        });
    }
}
