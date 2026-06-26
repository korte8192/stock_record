package com.example.stock_library

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.R.attr.inputType
import androidx.appcompat.app.AlertDialog

class SellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sell)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //All_listをpassedに引き渡し。
        val Alldata_passed = if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra("All_list",All_data::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("All_list") as? All_data
        }

        //実際に表示
        if(Alldata_passed!=null){
            for(i in 0 until Alldata_passed.get_info_count()){
                val Info_passed =Alldata_passed.get_info_true()

                Toast.makeText(this,"${Info_passed.get_name()}を追加しました。",Toast.LENGTH_SHORT).show()

                val sell_add_layout =findViewById<LinearLayout>(R.id.Sell_add)

                //つくったxml_layoutを追加する
                val inflater =layoutInflater
                val itemView= inflater.inflate(R.layout.item_sell_info,sell_add_layout,false)
                //つくったレイアウト内のパーツを取得
                val textName = itemView.findViewById<TextView>(R.id.item_price_sell)
                val textValues = itemView.findViewById<TextView>(R.id.item_price_sell)
                val btnAction = itemView.findViewById<Button>(R.id.btn_action)

                textName.text="◆名前:${Info_passed.get_name()}"
                textValues.text="値段:${Info_passed.get_buy()}/入手日:${Info_passed.get_day()}"

                //ボタンがクリックされた時の処理
                btnAction.setOnClickListener{
                    Toast.makeText(this,"${Info_passed.get_name()}だよ。",Toast.LENGTH_SHORT).show()

                    val sell_Textbox=EditText(this).apply{
                        inputType=android.text.InputType.TYPE_CLASS_NUMBER
                        hint="売値を入力"
                }

                    AlertDialog.Builder(this)
                        .setTitle("売却設定")
                        .setMessage("名前：${Info_passed.get_name()}　買値:${Info_passed.get_buy()}を売りますか？")

                        .setView(sell_Textbox)
                        //ダイアログのボタン処理
                        .setPositiveButton("売却") {dialog,which->
                            val inputText = sell_Textbox.text.toString()
                            if (inputText.isNotEmpty()) {
                                //入力読み込み時処理
                                val inputText_int =inputText.toInt()
                                Info_passed.set_sell(inputText_int)
                                Toast.makeText(this, "${Info_passed.get_name()}を${inputText_int}で売却",Toast.LENGTH_SHORT).show()
                            } else {
                                //データ空入力時処理
                                Toast.makeText(this,"売値を入力してください。",Toast.LENGTH_SHORT).show()

                            }
                        }
                        .setNegativeButton("キャンセル",null)
                        .show()
                    }


                sell_add_layout.addView(itemView)
                }
            }

        //sellボタン処理

    }
}