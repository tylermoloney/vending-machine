package com.techelevator.models;

import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {
    private File salesReport;
    private PrintWriter salesWriter;
    private List<Items> itemsList = new ArrayList<>();
    private Map<String, Integer> salesMap = new HashMap<>();

    public File getSalesReport() {
        return salesReport;
    }

    public SalesReport(List<Items> itemsList)  {
        this.itemsList = itemsList;
        this.salesMap = createSalesMap();
    }

    public Map<String, Integer> createSalesMap(){
        for(Items item : itemsList){
            this.salesMap.put(item.getName(), 0);
        }
        return this.salesMap;
    }

    public void updateSalesMap(String itemName, Map<String, Integer> salesMap){
        int currentSales = salesMap.get(itemName);
        salesMap.put(itemName, currentSales + 1);
    }

    public Map<String, Integer> getSalesMap(){
        return salesMap;
    }

    public void writeSalesReportFile(Map<String, Integer> salesMap) throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm a"));
        this.salesReport = new File(date + " Sales Report.txt");
        salesReport.createNewFile();
        try {
            this.salesWriter = new PrintWriter(new FileWriter(this.salesReport));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigDecimal totalSales = new BigDecimal("0.00");
        BigDecimal itemPrice = new BigDecimal("0.00");
        for (Map.Entry<String, Integer> entry : salesMap.entrySet()){
            this.salesWriter.println(entry.getKey() + "|" + entry.getValue());
          if (entry.getValue() != 0){
              for (int i = 0; i < itemsList.size(); i++){
                  if (entry.getKey().equals(itemsList.get(i).getName())){
                      itemPrice = itemsList.get(i).getPrice();
                  }
              }
              BigDecimal bigdecimalValue = new BigDecimal(entry.getValue());
              BigDecimal itemSales = new BigDecimal(String.valueOf(itemPrice.multiply(bigdecimalValue)));
              totalSales = totalSales.add(itemSales);
          }
        }
        this.salesWriter.println();
        this.salesWriter.println("TOTAL SALES: $" + totalSales);

    }

    public void close(){
        this.salesWriter.close();
    }
}
