package stock_record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class File_library {

    public static void save(All_data all) throws IOException {

    	BufferedWriter bw =
    	        new BufferedWriter(
    	                new FileWriter("stock.csv"));
        				

        for(Info info : all.data.keySet()) {

            boolean has = all.data.get(info);

            bw.write(
                    info.get_name() + "," +
                    info.get_buy() + "," +
                    info.get_sell() + "," +
                    info.get_day() + "," +
                    has
            );

            bw.newLine();
        }

        bw.close();
    }

    public static All_data load() throws IOException {

        All_data all = new All_data();

        File file = new File("stock.csv");

        if(!file.exists()) {
            return all;
        }

        BufferedReader br =
                new BufferedReader(
                        new FileReader(file));

        String line;

        while((line = br.readLine()) != null) {

            String[] data = line.split(",");

            String name = data[0];
            int buy = Integer.parseInt(data[1]);
            int sell = Integer.parseInt(data[2]);
            String day = data[3];
            boolean has =
                    Boolean.parseBoolean(data[4]);

            Info info =
                    new Info(name,buy,sell,day);

            all.data.put(info,has);
        }

        br.close();

        return all;
    }
}