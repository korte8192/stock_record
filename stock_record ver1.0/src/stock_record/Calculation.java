package stock_record;

//統計情報を計算するclass
class Calculation{

    All_data data;

    Calculation(All_data d){
        data=d;
    }

    int total_buy(){

        int sum=0;

        for(Info info:data.data.keySet()){
            sum+=info.get_buy();
        }

        return sum;
    }

    int total_sell(){

        int sum=0;

        for(Info info:data.data.keySet()){
            sum+=info.get_sell();
        }

        return sum;
    }

    int holding_buy(){

        int sum=0;

        for(Info info:data.data.keySet()){

            Boolean has=data.data.get(info);

            if(has){
                sum+=info.get_buy();
            }
        }

        return sum;
    }

    int holding_count(){

        int count=0;
        for(Boolean has:data.data.values()){
            if(has){
                count++;
            }
        }

        return count;
    }

    int max_profit(){

        int max=Integer.MIN_VALUE;
        boolean flag=false;

        for(Info info:data.data.keySet()){

            Boolean has=data.data.get(info);

            if(!has){

                int profit=
                        info.get_sell()
                        -info.get_buy();
                if(profit>max){
                    max=profit;
                }

                flag=true;
            }
        }

        if(flag){
            return max;
        }

        return 0;
    }

    int min_profit(){

        int min=Integer.MAX_VALUE;
        boolean flag=false;
        for(Info info:data.data.keySet()){

            Boolean has=data.data.get(info);

            if(!has){

                int profit=
                        info.get_sell()
                        -info.get_buy();

                if(profit<min){
                    min=profit;
                }

                flag=true;
            }
        }

        if(flag){
            return min;
        }

        return 0;
    }

    double average_profit(){

        int sum=0;
        int count=0;

        for(Info info:data.data.keySet()){

            Boolean has=data.data.get(info);

            if(!has){

                sum+=
                        info.get_sell()
                        -info.get_buy();

                count++;
            }
        }

        if(count==0){
            return 0;
        }
        return (double)sum/count;
    }
    //統計情報を表示する
    void status(){
        System.out.println();
        System.out.println("======== STATUS ========");
        System.out.println("総買付額 : "+total_buy());
        System.out.println("総売却額 : "+total_sell());
        System.out.println();
        System.out.println("保有資産額 : "+holding_buy());
        System.out.println("保有銘柄数 : "+holding_count());
        System.out.println();
        System.out.println("最大利益 : "+max_profit());
        System.out.println("最大損失 : "+min_profit());
        System.out.println("平均利益 : "+average_profit());
        System.out.println("========================");
    }

}