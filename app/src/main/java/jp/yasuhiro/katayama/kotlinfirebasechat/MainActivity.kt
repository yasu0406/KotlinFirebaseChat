package jp.yasuhiro.katayama.kotlinfirebasechat

//▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼追加▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
//▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲追加▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼追加▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
        // 送信ボタンクリックイベント登録
        sendButton.setOnClickListener { onClick() }// Kotlin SAM変換

        // FirebaseDBのリアルタイム同期開始
        FirebaseDBHelper.fetchMessages(
                {list -> listView.adapter = MessageListAdapter(this,list)},
                {e -> Log.d("debug","データ取得エラー:" + e)}
        )
        //▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲追加▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
    }

    //▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼追加▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
    // 送信ボタンクリック時
    fun onClick() {
        val msg = Message(textField.text.toString(), Date(System.currentTimeMillis()))
        FirebaseDBHelper.addMessage(msg, { e -> Log.d("debug","データ登録エラー" + e)})
        textField.setText("")
    }
    //▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲追加▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
}