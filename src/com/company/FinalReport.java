package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class FinalReport {
    private double income;
    private Manager manager;

    public FinalReport(Manager manager) {
        this.manager = manager;
    }


    // method to compute the income of all the orders
    public void calculateIncome() {
        for(Order order : manager.getOrders()) {
            System.out.println(order.toString());
            this.income += order.getDiscountPrice();
        }
        System.out.println("Final Income: "  + income);
    }


    // method to compute the variation of stock for each item
    public String calculateVariationClass(String id){

        StringBuilder variationStock = new StringBuilder();

        float variation = manager.menu.get(id).getInitialStock() - manager.menu.get(id).getStock();
        variationStock.append(id).append(" : ").append(variation);

        return variationStock.toString();

    }


    // method to generate the final report
    private void generateReport() {
        BufferedWriter bw = null;

        try {

            //Specifying the file name and path
            File file = new File(".\\report.txt");

            // If the file does not exist, it will be created
            if (!file.exists()) {
                boolean resu = file.createNewFile();
                if(!resu)
                    System.out.println("Impossible to create the file");
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write("This is the report of the day  (" +
                    java.time.LocalDateTime.now() + ")");
            bw.newLine();
            bw.newLine();

            bw.write("Items in the menu: ");
            bw.newLine();


            // loop into the menu map to get the details(name and stock) of each item
            for (String id: manager.menu.keySet()){
                String value = manager.menu.get(id).toStringReport();
                bw.write(value);
                bw.newLine();
            }
            bw.newLine();
            bw.write("Items that have been sold: ");
            bw.newLine();

            //loop into all the items
            for (String id : manager.menu.keySet()) {
                bw.write(calculateVariationClass(id));
                bw.newLine();
            }

            bw.newLine();
            bw.write("--------------------------------------------");
            bw.newLine();
            bw.write( "Total incomes for all orders: ");

            bw.write(NumberFormat.getCurrencyInstance(Locale.UK).format(income));
            bw.newLine();
            bw.write("--------------------------------------------");

            System.out.println("File written successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }


    // method to write the final report when we exit the GUI
    void launch() {
        calculateIncome();
        generateReport();
    }

    public double getIncome() {
        return this.income;
    }
}


