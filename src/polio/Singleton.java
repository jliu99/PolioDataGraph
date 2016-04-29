/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polio;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author csstudent
 */
public final class Singleton {
    private static Singleton instance;
    private static Integer minimum;
    private static Integer maximum;
    
    
    public static void init(){
        try{
            File f = new File("settings.ser");
            
        } catch(IOException e){
            //blah
        }
    }
    
    public static Singleton getInstance(){
        return instance;
    }
    
    private Singleton() { }
    
    public static Integer getMinimum(){
        init();
        return minimum;
    } 
    
    public static void setMinimum(Integer i){
        init();
        minimum = i;
    }
    
    public static Integer getMaximum(){
        init();
        return maximum;
    }
    
    public static void setMaximum(Integer i){
        init();
        maximum = i;
    }
    
}
