/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polio;

/**
 *
 * @author csstudent
 */
public class PolioDataSet{
    private Legend[] dimension;
    private DataEntry[] fact;
    
    public DataEntry[] getDataEntries(){
        return fact;
    }
    
    public String toString() {
       String str = "";
       for(DataEntry d : fact){
           str += d.getData().getCountry() + ": " + d.getValue() + "% immunized" + "\n";
       }
       return str;
    }
}
