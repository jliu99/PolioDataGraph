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
public final class Singleton {
    private static final Singleton instance;
    private static Integer minimum;
    private static Integer maximum;
    
    static {
        try{
            instance = new Singleton();
        } catch (Exception e){
            throw new RuntimeException("error", e);
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
    
    public static Integer getMaximum(){
        init();
        return maximum;
    }
    
}
