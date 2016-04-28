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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author csstudent
 */
public class FXMLDocumentController implements Initializable {
    private DataEntry[] polioEntries;
    private XYChart.Series<String, Number> polioSeries;
    
    @FXML
    private BarChart chart;
    
    @FXML
    private TextField min;
    
    @FXML
    private TextField max;
    
    @FXML
    private Button filter;
    
    @FXML
    private MenuBar menu;
    
    @FXML
    private Menu fileMenu;
    
    @FXML
    private MenuItem close;
    
    @FXML
    private Menu editMenu;
    
    @FXML
    private Menu helpMenu;
    
    @FXML
    private MenuItem about;
    
    @FXML
    private void handleChangeFilter(MouseEvent event){
        Integer minimum = 0;
        Integer maximum = 0;
        try {
            minimum = Integer.parseInt(min.getCharacters().toString());
            maximum = Integer.parseInt(max.getCharacters().toString());
            if(minimum < 100 && minimum < maximum && maximum > 0){
            chart.getData().clear();
            polioSeries = new XYChart.Series();
                for (DataEntry d : polioEntries){
                    Integer value = Integer.parseInt(d.getValue());
                    if(d.getData().getCountry()!= null && value >= minimum && value <= maximum){
                        polioSeries.getData().add(new XYChart.Data(d.getData().getCountry(), value));
                    }
                }
            chart.getData().add(polioSeries);
            }
        } catch (Exception e){
             System.out.println("Something went wrong.");  
        }
    }
    
    @FXML
    private void handleCloseMenu(ActionEvent event){
        System.exit(-1);
    }
    
    @FXML
    private void handleAboutMenu(ActionEvent event){
        Alert dialogBox = new Alert(AlertType.INFORMATION);
        dialogBox.setTitle("About Project");
        dialogBox.setHeaderText(null);
        dialogBox.setContentText("This project graphs data provided by the World Health Organization regarding"
                               + " the percent population of countries over the world that have been vaccinated"
                               + " for polio. To filter for specific minimum and maximum percentage thresholds,"
                               + " enter appropriate values in the textfields and press the Filter button.");
        dialogBox.showAndWait();
    }
    
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
        polioEntries = ds.getDataEntries();
        
        polioSeries = new XYChart.Series();
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
