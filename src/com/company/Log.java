package com.company;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class Log {
   // private final String logFile;
    private static Log instance;
    private File file = new File(".\\log.txt");
    private FileWriter fw;
    //private BufferedWriter bw = null;


    private Log() throws IOException {
        FileWriter fw = new FileWriter(file);

    }

    /*public void closebw(){
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static synchronized Log getInstance() throws IOException {

        synchronized(Log.class){
            if(instance==null){
                instance = new Log();
            }
        }
        return instance;
    }

    public void log_order(Order new_order) throws IOException {
        String[] order_log = new String[4];
        order_log[0] = Integer.toString(new_order.getCustomerID());
        order_log[1] = new_order.getTimestamp();
        order_log[2] = Double.toString(new_order.getDiscountPrice());
        order_log[3] = Double.toString(new_order.getItems().size());

        //Specifying the file name and path


        // If the file does not exist, it will be created

        fw = new FileWriter(file, true);

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("New order processed at  :" + order_log[1] + "\n");
        bw.write("  CustomerId :" + order_log[0] + "\n");
        //bw.write("  Timestamp " + order_log[1] + "\n");
        bw.write("  Price :" + order_log[2] + "\n");
        bw.write("  Number of Items : " + order_log[3] + "\n");

        bw.newLine();
        bw.newLine();
        bw.close();
    }
}
