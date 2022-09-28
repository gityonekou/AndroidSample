/*
 * ListViewアイテムの移動と削除
 *
 * 対象URL:https://akira-watson.com/android/listview_3.html
 *
 * リスト表示させると表示位置を変更したり、追加、削除したいという要望が出てきます。
 * やり方としては、以下に2パターンが考えられますがリスト項目をドラッグ&ドロップさせたり追加・消去を行う場合は
 * 「RecyclerView」を使う方が一般的となります。
 * １．BaseAdapter が持っている、remove()、add() を使う
 * ２．要素自体の位置変更、追加、削除をする。data は配列や ArrayList<T> など
 *
 * 今回のサンプルは２の方で作成します。
 * また、リスト内容を更新後はリストを再描写するメソッド。notifyDataSetChanged()を呼び出す必要があります。
 * 「notifyDataSetChanged()」
 * BaseAdapterのメソッドで現在のリストをすべて再描写します。指定部分だけを再描写するメソッドもあるようなので
 * 後の勉強内容ではありますが、上記RecyclerViewでも使うのかどうかではありそうです。
 * （とりあえず、今回は後の勉強候補からは外しておきます。)
 *
 * また、リストの表示内容とレイアウトはListViewSample0301で使用したものと同一とします。
 * 今回のサンプルではリスト項目の削除・移動があるのでリスト表示項目の実態(メモリ領域)をListで作成するため
 * アダプタとの引数も配列(ListViewSample0301)からリスト(ListViewSample0401)に変更します。
 * ※0301、0401ともにリスト表示項目の値が格納されている変数のメモリはActivity側で持っていることに注意です。
 * notifyDataSetChanged()を呼び出したタイミングでgetViewがリスト項目数分呼び出されます。
 * BaseAdapterにはデータリストの再描写をするnotifyDataSetChanged()しかない？？ようなので注意
 *
 *
 */
package com.example.androidsample.list;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;
import com.example.androidsample.common.OkCancelDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListViewアイテムの移動と削除
 * 「7.ListViewアイテムの移動と削除(本来はRecyclerViewでやるべき)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/listview_3.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0401 extends AppCompatActivity
        implements AdapterView.OnItemClickListener,
        ListViewSample0401ActionDialog.OnItemClickActionListener,
        OkCancelDialog.OkCancelListener {

    // リスト表示内容
    private List<String> nameList;
    private List<Integer> imageIDList;

    // アダプタ
    private BaseAdapter adapter;

    // リストの選択位置
    private int clickItemPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_sample0301);

        // 表示リストを作成v
        Resources res = getResources();
        this.nameList = new ArrayList<>(Arrays.asList(
                res.getStringArray(R.array.listview_sampe0301_name_array)));
        String[] imageIdWorks = res.getStringArray(R.array.listview_sampe0301_drawable_array);
        this.imageIDList = new ArrayList<>(imageIdWorks.length);
        for(String imageIdStr : imageIdWorks) {
            // intの値をIntegerに変換するのはコンパイラの仕事となったので明示的に変換は不要となりました、、、やったら警告がでてまう。。
            this.imageIDList.add(res.getIdentifier(
                    imageIdStr, "drawable", getPackageName()));
        }
        ListView listView = findViewById(R.id.listview_sample0301_listview);
        this.adapter = new ListViewSample0401Adapter(
                getApplicationContext(),
                R.layout.listview_sample0301_list_items,
                this.nameList,
                this.imageIDList
        );
        listView.setAdapter(this.adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        setClickItemPosition(position);
        DialogFragment actionDialog = new ListViewSample0401ActionDialog();
        Bundle args = new Bundle();
        // タイトル＝選択した行の名前の値を設定
        args.putString(ListViewSample0401ActionDialog.TITLE_KEY, this.nameList.get(position));
        // 選択項目の表示内容をリスト数・選択行により振り分け設定
        if(this.nameList.size() == 1) {
            // ラスト1行のみの場合は削除・キャンセルのみ表示
            args.putInt(ListViewSample0401ActionDialog.UP_ACTION_DELETE,
                    ListViewSample0401ActionDialog.DELETE_ON);
            args.putInt(ListViewSample0401ActionDialog.DOWN_ACTION_DELETE,
                    ListViewSample0401ActionDialog.DELETE_ON);
        } else if(position == 0) {
            // 最初の1行を選択時は上に移動を表示しない
            args.putInt(ListViewSample0401ActionDialog.UP_ACTION_DELETE,
                    ListViewSample0401ActionDialog.DELETE_ON);
        } else if(position == this.nameList.size() - 1) {
            // ラスト1行を選択時は下に移動を表示しない
            args.putInt(ListViewSample0401ActionDialog.DOWN_ACTION_DELETE,
                    ListViewSample0401ActionDialog.DELETE_ON);
        }
        actionDialog.setArguments(args);
        actionDialog.show(getSupportFragmentManager(),
                ListViewSample0401ActionDialog.class.getName());
    }

    public int getClickItemPosition() {
        return clickItemPosition;
    }

    public void setClickItemPosition(int clickItemPosition) {
        this.clickItemPosition = clickItemPosition;
    }

    @Override
    public void moveAbove() {
        // 上に移動選択時、上下を入れ替える
        String aboveName = this.nameList.get(getClickItemPosition() - 1);
        this.nameList.set(getClickItemPosition() - 1, this.nameList.get(getClickItemPosition()));
        this.nameList.set(getClickItemPosition(), aboveName);

        Integer aboveId = this.imageIDList.get(getClickItemPosition() - 1);
        this.imageIDList.set(getClickItemPosition() - 1, this.imageIDList.get(getClickItemPosition()));
        this.imageIDList.set(getClickItemPosition(), aboveId);

        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void moveBelow() {
        // 下に移動選択時、下上を入れ替える
        String belowName = this.nameList.get(getClickItemPosition() + 1);
        this.nameList.set(getClickItemPosition() + 1, this.nameList.get(getClickItemPosition()));
        this.nameList.set(getClickItemPosition(), belowName);

        Integer belowId = this.imageIDList.get(getClickItemPosition() + 1);
        this.imageIDList.set(getClickItemPosition() + 1, this.imageIDList.get(getClickItemPosition()));
        this.imageIDList.set(getClickItemPosition(), belowId);

        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void delete() {
        DialogFragment dialog = new OkCancelDialog();
        Bundle args = new Bundle();
        args.putString(OkCancelDialog.TITLE_KEY, "削除");
        args.putString(OkCancelDialog.MESSAGE_KEY,
                this.nameList.get(getClickItemPosition()) + " 本当に削除しますか？");
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), OkCancelDialog.class.getName());

    }

    @Override
    public void onOkClicked() {
        // OKボタン押下時、現在のリストを削除
        this.nameList.remove(getClickItemPosition());
        this.imageIDList.remove(getClickItemPosition());
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelClicked() {}

    @Override
    public void cancel() {onCancelClicked();}
}
