package com.company.outputs;
import com.company.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Log {
    // private final String logFile;
    private static Log instance;
    private String logPath = "output/log.txt";
    private FileWriter fw;
    //private BufferedWriter bw = null;


    public Log() {
    }


    public static synchronized Log getInstance() throws IOException {

        synchronized(Log.class){
            if(instance==null){
                instance = new Log();
            }
        }
        return instance;
    }

    public void log_order_server(Order new_order, int id_server) throws IOException {
        String[] order_log = new String[6];
        order_log[0] = Integer.toString(new_order.getCustomerID());
        order_log[1] = new_order.getTimestamp();
        order_log[2] = Double.toString(new_order.getDiscountPrice());
        order_log[3] = Double.toString(new_order.getItems().size());
        order_log[4] = new_order.getName();
        order_log[5] = Integer.toString(id_server);

        //Specifying the file name and path


        // If the file does not exist, it will be created

        fw = new FileWriter(logPath, true);

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("New order processed at  :" + order_log[1] + "\n");
        bw.write("  CustomerId :" + order_log[0] + "\n");
        bw.write("  Server ID " + order_log[5] + "\n");
        bw.write("  Customer name " + order_log[4] + "\n");
        bw.write("  Price :" + order_log[2] + "\n");
        bw.write("  Number of Items : " + order_log[3] + "\n");

        bw.newLine();
        bw.newLine();
        bw.close();
    }


    public void log_order_new(Order new_order) throws IOException {
        String[] order_log = new String[5];
        order_log[0] = Integer.toString(new_order.getCustomerID());
        order_log[1] = new_order.getTimestamp();
        order_log[2] = Double.toString(new_order.getDiscountPrice());
        order_log[3] = Double.toString(new_order.getItems().size());
        order_log[4] = new_order.getName();


        //Specifying the file name and path


        // If the file does not exist, it will be created

        fw = new FileWriter(logPath, true);

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("New order added to the queue (Shared object) at  :" + order_log[1] + "\n");
        bw.write("  CustomerId :" + order_log[0] + "\n");
        bw.write("  Customer name " + order_log[4] + "\n");
        bw.write("  Price :" + order_log[2] + "\n");
        bw.write("  Number of Items : " + order_log[3] + "\n");

        bw.newLine();
        bw.newLine();
        bw.close();
    }

    public void log_barista(int id_barista, int server_id) throws IOException {



        fw = new FileWriter(logPath, true);

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("Barista " + id_barista + " take care of order from the server: " + server_id + "\n");
        bw.newLine();
        bw.close();
    }

}

