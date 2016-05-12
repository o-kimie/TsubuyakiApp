package com.example.mika.tsubuyakiapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener{

    //つぶやき表示用のリストビュー
    private ListView tsbuyakiLV;
    //つぶやき入力欄
    private EditText commentEtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Tsubuyaki t = new Tsubuyaki(1, "つぶやきテスト");
//       t.save();//テーブルに保存
//
//        List<Tsubuyaki> list = Tsubuyaki.listAll(Tsubuyaki.class);
//        for (Tsubuyaki tsubuyaki: list){
//            Log.d("データベーステスト",tsubuyaki.comment);
//        }
        //レイアウトより、つぶやくボタンの情報を取得
        Button commitBtn = (Button) findViewById(R.id.main_commit_btn);
        commitBtn.setOnClickListener(this);

        // レイアウトより、リストビューの表示
        tsbuyakiLV = (ListView) findViewById(R.id.main_tsubuyaki_lv);

        //リストビューの内容を更新
        updateListView();


    }

    @Override
    public void onClick(View v) {
        //レイアウトより、入力欄の情報を取得
        commentEtx = (EditText) findViewById(R.id.main_comment_etx);


        Tsubuyaki tsubuyaki = new Tsubuyaki();
        tsubuyaki.id =1;
        tsubuyaki.comment = commentEtx.getText().toString() +"にゃー";
        tsubuyaki.save();

        updateListView();
        commentEtx.setText("");
        //↑ボタンを押したタイミングで消えてほしい
}


    //リストビューの内容を更新する
    private void updateListView(){
    List<Tsubuyaki> list = Tsubuyaki.listAll(Tsubuyaki.class);
//        昇順、小さい順ASK、降順、大きい順DESC
    list = Tsubuyaki.listAll(Tsubuyaki.class,"ID DESC");

//        リストビューにデータをセット
//        Adapter：特定のデータをひとまとめにしてビューに渡すときに利用する
        AdapterListTsubuyaki adapter =
                new AdapterListTsubuyaki(this,R.layout.list_tsubuyaki,list);
        tsbuyakiLV.setAdapter(adapter);

//削除していいですか？の文
    }
    public boolean onItemLongClick(AdapterView<?>adapterView,View view,int i, long l){

        ListView list = (ListView) adapterView;
        final Tsubuyaki selectedItem = (Tsubuyaki) list.getItemAtPosition(i);

        new AlertDialog.Builder(this)
                .setTitle("ちゅうい")
                .setMessage("削除してもよろしいですか？")
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {

                        Tsubuyaki tsubuyaki = Tsubuyaki.findById(Tsubuyaki.class,selectedItem.getId());
                        tsubuyaki.delete();

                        updateListView();
                    }

                })
                .setNegativeButton("キャンセル",null)
                .show();
        return false;
    }
}
