package com.example.stock_library;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

class All_data implements Serializable{
    Map<Info,Boolean> data=new HashMap<>();

    void add_data(Info a){
        if(data.containsKey(a)){
            buy_C(a);
            //System.out.println("修正を行いました。");
        }else{data.put(a,true);}
    }

    int get_info_count(){
        int point=0;
        for(Map.Entry<Info,Boolean> x:data.entrySet()){
            if(x.getValue()==true){
                ++point;
            }
        }
        return point;
    }
    Info get_info_true(){
        for(Map.Entry<Info,Boolean> x:data.entrySet()){
            if(x.getValue()==true){
                return x.getKey();
            }
        }
        return null;
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
    void buy_C(Info i) {
        if (data.containsKey(i)) {
            for (Info tage : data.keySet()) {
                if (tage.equals(i)) {
                    tage.set_buy(i.get_buy());
                }
            }
            //int kai=i.get_buy();
            //if(data.containsKey(i)){
            //i.set_buy(kai);
            //data.put(i,true);
            //売ったものに対しての変更バグる
        } else {
            throw new IllegalArgumentException("エラー：指定されたカードが存在しません。");
        }
    }
}
