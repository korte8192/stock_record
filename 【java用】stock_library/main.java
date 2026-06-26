package stock_record;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Info{
    String name;
    int buy_price;
    int sell_price;
    String day; 

    Info(String n,int b){
        name=n;
        buy_price=b;
        sell_price=0;
        LocalDateTime now = LocalDateTime.now();
        day = now.toString();

    }

    @Override
    public boolean equals(Object obj){
        if(obj==null|| getClass() != obj.getClass()) return false;
        Info other =(Info)obj;
         
        return this.name.equals(other.name);
    }
    @Override
    public int hashCode() {
        // equals で使うフィールドと同じものを使う
        return java.util.Objects.hash(name);
    }



    void set_buy(int x){buy_price=x;}
    void set_sell(int y){sell_price=y;}
    void set_name(String n){name=n;}

    int get_buy(){return buy_price;}
    int get_sell(){return sell_price;}
    String get_name(){return name;}
    String get_day(){return day;}

    void print(){
        System.out.println("--------------------");
        System.out.println("name:"+ name);
        System.out.println("買値:"+ buy_price);
        System.out.println("売値:"+ sell_price);
        System.out.println("買った日:"+ day);
        System.out.println("--------------------");
    }

}

class All_data{
    Map<Info,Boolean> data=new HashMap<>();

    void add_data(Info a){
        if(data.containsKey(a)){
            buy_C(a);
            System.out.println("修正を行いました。");
        }else{data.put(a,true);}
    }

    void sell_C(Info i){
    	if(data.containsKey(i)){
    		for(Info tage:data.keySet()) {
    			if(tage.equals(i)) {
    				tage.set_sell(i.get_buy());
    				data.put(tage, false);
    			}
    			
    			
    		}
        }else{
            throw new IllegalArgumentException("エラー：指定されたカードが存在しません。");
        }
    }
    void buy_C(Info i){
    	if(data.containsKey(i)){
    		for(Info tage:data.keySet()) {
    			if(tage.equals(i)) {
    				tage.set_buy(i.get_buy());
    			}
    		}
        //int kai=i.get_buy();
        //if(data.containsKey(i)){
            //i.set_buy(kai);
            //data.put(i,true);
            //売ったものに対しての変更バグる
        }else{
            throw new IllegalArgumentException("エラー：指定されたカードが存在しません。");
        }
    }
    void print(){
        for(Info info:data.keySet()){
            Boolean has=data.get(info);
            if(has==true){
            info.print();
            }
        }
    }

    void log_print(){
        for(Info info:data.keySet()){
            Boolean has=data.get(info);
            if(has != null && has){
                System.out.println("【 状態：○所持 】");
            } else {
                System.out.println("【 状態：●売却済 】");
            }
            info.print();
            
        }
    }
}



public class main{
    public static void main(String[] args){

        All_data nlist=new All_data();

        Scanner sc=new Scanner(System.in);
        System.out.println("ようこそ、カードリーダーへ。");
        System.out.println("＋ーーーーーーーーーーーーーー＋");
        System.out.println("■ コマンド一覧");
        System.out.println("買いデータ入力 /buy （買った物）（買値）");
        System.out.println("売りデータ入力 /sell (売ったもの) (売値)");
        System.out.println("もう一回入力でデータを修正");
        System.out.println("※売ったものに対して買いデータ修正をしないでください。");
        System.out.println("リスト表示　/list");
        System.out.println("ログ表示　/log");
        System.out.println("＋ーーーーーーーーーーーーーー＋");

        System.out.println("実行受付中");
        while(true) {
        String a=sc.next();
        if(a.equals("/buy")){
            String buy_name=sc.next();
            int buy_price=sc.nextInt();
            Info x=new Info(buy_name,buy_price);
            nlist.add_data(x);
        }
        if(a.equals("/sell")){
            String sell_name=sc.next();
            int sell_price=sc.nextInt();
            Info y=new Info(sell_name,sell_price);
            nlist.sell_C(y);
        }
        if(a.equals("/list")){
            nlist.print();
        }
        if(a.equals("/log")){
            nlist.log_print();
            
        }
        
        }

    }
}