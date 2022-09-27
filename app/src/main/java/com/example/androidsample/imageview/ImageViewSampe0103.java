/*
 * ImageViewを使って画像を表示する：(assetsに画像を置きそれを取り込む)
 * 　対象URL：https://akira-watson.com/android/imageview.html
 *
 * assetsディレクトリ以下は自由にディレクトリを作成できます。
 * (デフォルトではassetsディレクトリはないのでヴィザードを使って作成する必要があります。
 * ＜つまり、用途によってディレクトリを分けることができるようになります。
 *
 * assets からはファイルの読み出しはtry catchを使って例外処理をするようにします。
 * これは例外が発生した場合でもアプリが回復できる余地を作ることになります。
 * また、リソース開放漏れが無いようにfinalyでclose()を入れるか、try-with-resourcesを使います。
 *
 * try{
 * ...
 * } catch{
 * ...
 * }finally {
 *     close();
 * }
 *
 * // try-with-resources
 * try (クローズが必要なインスタンスをここで生成) {
 *...
 * } catch{
 * ...
 * }
 *
 */
package com.example.androidsample.imageview;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.io.InputStream;

/**
 * ImageViewサンプル03
 * 「3. ImageViewを使って画像を表示する：(assetsに画像を置きそれを取り込む)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/imageview.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ImageViewSampe0103 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトは前のサンプルと同じなのでそのまま再利用します
        setContentView(R.layout.activity_imageview_sample0102);
        ImageView imageView = findViewById(R.id.imageview_sample0102_imageview);
        AssetManager assetManager = getResources().getAssets();
        // try-with-resources
        try (InputStream in = assetManager.open(
                "view_image/imageview_sample_image3.jpg")) {
            Bitmap btmp = BitmapFactory.decodeStream(in);
            imageView.setImageDrawable(new BitmapDrawable(getResources(), btmp));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
