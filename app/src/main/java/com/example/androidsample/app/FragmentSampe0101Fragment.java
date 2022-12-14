package com.example.androidsample.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidsample.R;
//import android.support.v4.app.Fragment;

/**
 * Fragmentサンプルを表示するフラグメントです。サンプル01、02にて呼び出されます。
 * 詳細はFragmentSampe0101を参照のこと
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class FragmentSampe0101Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_sample0101, container, false);
    }
}
