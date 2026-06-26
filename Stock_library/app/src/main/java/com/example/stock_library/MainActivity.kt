package com.example.stock_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //購入ボタン
        val buy_Button =findViewById<Button>(R.id.buy_botton)
        val buy_name=findViewById<EditText>(R.id.stock_name_buy)
        val buy_price=findViewById<EditText>(R.id.stock_price_buy)

        val All_list = All_data();

        buy_Button.setOnClickListener {
            val Bname =buy_name.text.toString()
            val priceStr =buy_price.text.toString()
            val Bprice: Int?=priceStr.toIntOrNull()

            if(Bname.isNotEmpty() && Bprice !=null ) {
                val set_buy = Info(Bname,Bprice)
                All_list.add_data(set_buy)

            Toast.makeText(this,"入力成功",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"数値と名前の両方を入力をしてください",Toast.LENGTH_LONG).show()
            }
        }

        //sellへのページ移動管理
        val sellTitle=findViewById<ImageButton>(R.id.sell_icon)

        sellTitle.setOnClickListener{
            val intent= Intent(this,SellActivity::class.java).apply{
                putExtra("All_list", All_list)
            }
            startActivity(intent)
        }
    }
}