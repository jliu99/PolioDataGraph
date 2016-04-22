/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polio;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author csstudent
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BarChart chart;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String s = "http://apps.who.int/gho/athena/data/GHO/WHS4_544.json?profile=simple&filter=YEAR:1980";
        URL dataURL = null;
        try {
            dataURL = new URL(s);
        } catch (Exception e){
            System.out.println("Improper URL " + s);
            System.exit(-1);
        }
        
        Scanner sc = null;
        try{
            sc = new Scanner(dataURL.openStream());
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
        DataEntry[] polioEntries = ds.getDataEntries();
        
        XYChart.Series<String, Number> polioSeries = new XYChart.Series();
        polioSeries.setName("% Polio Immunizations");
        for (DataEntry d : polioEntries){
            if(d.getData().getCountry()!=null){
                Integer value = new Integer(d.getValue());
                polioSeries.getData().add(new XYChart.Data(d.getData().getCountry(), value));
            }
        }
        
        chart.getData().add(polioSeries);
    }   
}
