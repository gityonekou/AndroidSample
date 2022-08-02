package com.example.androidsample.list;
/*
 * RecyclerViewで画像リストの表示
 * 対象URL:https://akira-watson.com/android/recyclerview.html
 * 今回のサンプルは今までのリストサンプルで作成した画像リストをRecyclerViewで表示するサンプルです。
 * RecyclerViewの基本はRecyclerViewSample0101で解説しているのでここでは割愛します。
 *
 */
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewSample0102 extends AppCompatActivity {

    public static final String ITEM_TOUCH_HELPER = "ItemTouchHelper";
    public static final int ITEM_TOUCH_HELPER_ON = 1;
    public static final int ITEM_TOUCH_HELPER_OFF = -1;

    // 表示リスト
    private List<String> nameList;
    private List<Integer> imageIDList;
    // アダプタ
    private RecyclerViewSample0102Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_sample0102);

        // 表示リストを作成
        Resources res = getResources();
        this.nameList = new ArrayList<>(Arrays.asList(
                res.getStringArray(R.array.listview_sampe0301_name_array)));
        String[] imageIdWorks =
                res.getStringArray(R.array.listview_sampe0301_drawable_array);
        this.imageIDList = new ArrayList<>(imageIdWorks.length);
        for(String imageIdStr : imageIdWorks) {
            // intの値をIntegerに変換するのはコンパイラの仕事となったので明示的に変換は不要となりました、、、やったら警告がでてまう。。
            this.imageIDList.add(
                    res.getIdentifier(imageIdStr, "drawable", getPackageName()));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview_sample0102_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.adapter = new RecyclerViewSample0102Adapter(this.nameList, this.imageIDList);
        recyclerView.setAdapter(this.adapter);

        // ドラッグ&ドロップを実装する場合（11.RecyclerViewとItemTouchHelperでドラッグ&ドロップ）
        // ItemTouchHelper.ACTION_STATE_IDLE | ItemTouchHelper.RIGHT
        if(getIntent().getIntExtra(ITEM_TOUCH_HELPER, ITEM_TOUCH_HELPER_OFF)
            == ITEM_TOUCH_HELPER_ON) {
            recyclerView.addItemDecoration(new DividerItemDecoration(
                    getApplicationContext(), DividerItemDecoration.VERTICAL));
            ItemTouchHelper helper = new ItemTouchHelper(
                    new ItemTouchHelper.SimpleCallback(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                            ItemTouchHelper.RIGHT) {
                        @Override
                        public boolean onMove(
                                @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder,
                                @NonNull RecyclerView.ViewHolder target) {
                            // 実際にはアクテビティ側で保持しているリストデータの位置変更が必要になります
                            // 実用的にするにはリスト表示するデータをアダプタ側で保持する形(コピーを作成)し、
                            // リストデータへの移動、削除があった場合にリストデータに変更を加えるメソッドを
                            // アダプタに用意してそのメソッドを呼び出す形がいいかもしれないです。
                            // notifyItemMovedもそのメソッド内で呼び出す
                            int fromPosition = viewHolder.getAbsoluteAdapterPosition();
                            int toPosition = target.getAbsoluteAdapterPosition();
                            String toName = getNameList().get(toPosition);
                            getNameList().set(toPosition, getNameList().get(fromPosition));
                            getNameList().set(fromPosition, toName);

                            Integer toId = getImageIDList().get(toPosition);
                            getImageIDList().set(toPosition, getImageIDList().get(fromPosition));
                            getImageIDList().set(fromPosition, toId);

                            getAdapter().notifyItemMoved(fromPosition, toPosition);
                            return true;
                        }

                        @Override
                        public void onSwiped(
                                @NonNull RecyclerView.ViewHolder viewHolder,
                                int direction) {
                            // 実際にはアクテビティ側で保持しているリストデータの削除が必要になります
                            // 実用的にするにはリスト表示するデータをアダプタ側で保持する形(コピーを作成)し、
                            // リストデータへの移動、削除があった場合にリストデータに変更を加えるメソッドを
                            // アダプタに用意してそのメソッドを呼び出す形がいいかもしれないです。
                            // notifyItemRemovedもそのメソッド内で呼び出す
                            getNameList().remove(viewHolder.getAbsoluteAdapterPosition());
                            getImageIDList().remove(viewHolder.getAbsoluteAdapterPosition());
                            getAdapter().notifyItemRemoved(viewHolder.getAbsoluteAdapterPosition());
                        }
                    });
            helper.attachToRecyclerView(recyclerView);
        }
    }

    public List<String> getNameList() {
        return nameList;
    }

    public List<Integer> getImageIDList() {
        return imageIDList;
    }

    public RecyclerViewSample0102Adapter getAdapter() {
        return adapter;
    }
}
