/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polio;

import com.google.gson.Gson;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 *
 * @author csstudent
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BarChart barChart;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String s = "http://apps.who.int/gho/athena/data/GHO/WHS4_544.json?profile=simple&filter=YEAR:1980";
        URL u = null;
        try {
            u = new URL(s);
        } catch (Exception e){
            System.out.println("Improper URL " + s);
            System.exit(-1);
        }
        
        Scanner sc = null;
        try{
            sc = new Scanner(url.openStream());
        } catch (Exception e){
            System.out.println("Could not connect to " + s);
            System.exit(-1);
        }
        
        String str = new String();
        while (sc.hasNext()){
            str += sc.nextLine() + "\n";
        }
        sc.close();
       
        Gson gson = new Gson();
        PolioDataSet ds = gson.fromJson(str, PolioDataSet.class);
  
        XYChart.Series<String, Number> polioSeries = new XYChart.Series();
        polioSeries.setName("% Polio Immunizations");
        DataEntry[] polioEntries = ds.getDataEntries();
        for (DataEntry d : polioEntries){
            polioSeries.getData().add(new XYChart.Data(d.getData().getCountry(), d.getValue()));
        }
        barChart.getData().add(polioSeries);
    }   
}
