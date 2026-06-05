package stock_record;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//基本情報を保存しているclass
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

    Info(String n,int b,int s,String d){
        name=n;
        buy_price=b;
        sell_price=s;
        day=d;

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


    //setter,getter等
    void set_buy(int x){buy_price=x;}
    void set_sell(int y){sell_price=y;}
    void set_name(String n){name=n;}

    int get_buy(){return buy_price;}
    int get_sell(){return sell_price;}
    String get_name(){return name;}
    String get_day(){return day;}
    
    //基本情報のUI部分
    void print(){
        System.out.println("--------------------");
        System.out.println("name:"+ name);
        System.out.println("買値:"+ buy_price);
        System.out.println("売値:"+ sell_price);
        System.out.println("買った日:"+ day);
        System.out.println("--------------------");
    }

}
//infoクラスをつかい今まで入力したすべてのデータを保存しておくclass
//save以外の関数を実装しています。
class All_data{
    Map<Info,Boolean> data=new HashMap<>();

    void add_data(Info a){
        if(data.containsKey(a)){
            buy_C(a);
            System.out.println("修正を行いました。");
        }else{data.put(a,true);}
    }
    
    Map<Info,Boolean> getData(){
        return data;
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
            throw new IllegalArgumentException("エラー：指定された株が存在しません。");
        }
    }
    void buy_C(Info i){
    	if(data.containsKey(i)){
    		for(Info tage:data.keySet()) {
    			if(tage.equals(i)) {
    				tage.set_buy(i.get_buy());
    			}
    		}
        }else{
            throw new IllegalArgumentException("エラー：指定された株が存在しません。");
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

        All_data nlist;
        try{
            nlist = File_library.load();
            System.out.println("データを読み込みました。");
        }
        catch(Exception e){
            nlist = new All_data();
            System.out.println("保存データが見つかりません。");
        }
        Calculation cal=new Calculation(nlist);
        
        
        Scanner sc=new Scanner(System.in);
        System.out.println("ようこそ、stock_recorderへ。");
        System.out.println("＋ーーーーーーーーーーーーーー＋");
        System.out.println("■ コマンド一覧");
        System.out.println("買いデータ入力 /buy （買った物）（買値）");
        System.out.println("売りデータ入力 /sell (売ったもの) (売値)");
        System.out.println("自分の手持ちの品の表示　/list");
        System.out.println("手放したものも含めた記録の表示　/log");
        System.out.println("保存 /save");
        System.out.println("終了 /exit");
        System.out.println("ステータス /status");
        System.out.println("＋ーーーーー補足ーーーーーーー＋");
        System.out.println("もう一回入力でデータを修正");
        System.out.println("※売ったものに対して買いデータ修正をしないでください。");
        System.out.println("※statusは総買付額、総売却額、保有資産額、保有銘柄数");
        System.out.println("最大/最少利益、平均利益などを出力してくれます。");
        System.out.println("＋ーーーーーーーーーーーーーー＋");

        System.out.println("実行受付中");
        while(true) {
        String a=sc.next();
        if(a.equals("/buy")){
        	try {
            String buy_name=sc.next();
            int buy_price=sc.nextInt();
            Info x=new Info(buy_name,buy_price);
            nlist.add_data(x);
            System.out.println("購入完了。");
        	}
            catch(Exception e){
                System.out.println("売却失敗");
        	}
        }
        if(a.equals("/sell")){
        	try {
            String sell_name=sc.next();
            int sell_price=sc.nextInt();
            Info y=new Info(sell_name,sell_price);
            nlist.sell_C(y);
            System.out.println("売却完了。");
        	}
        	catch(Exception e){
                System.out.println("売却失敗");
        	}
        }
        if(a.equals("/list")){
            nlist.print();
        }
        if(a.equals("/log")){
            nlist.log_print();
            
        }
        if(a.equals("/status")){
            cal.status();
        }
        if(a.equals("/save")){

            try{
                File_library.save(nlist);
                System.out.println("保存完了");
            }
            catch(Exception e){
                System.out.println("保存失敗");
            }
        }
            if(a.equals("/exit")){

                try{
                    File_library.save(nlist);
                    System.out.println("自動保存しました。");
                }
                catch(Exception e){
                    System.out.println("保存に失敗しました。");
                }

                break;
            }
        
        }

    }
}