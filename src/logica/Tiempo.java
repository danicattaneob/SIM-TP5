/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Dani
 */
public class Tiempo {
    
    private static Tiempo instance;
    private double tiempo;

    private Tiempo() {
        this.tiempo = 0;
    }
    
    public static Tiempo getInstance(){
        if(instance == null){
            return new Tiempo();
        }else{
            return instance;
        }
    }
    
    public double getTime(){
        return tiempo;
    }
    
    public void agregarTiempo(double t){
        tiempo += t;
    }
    
    public void setTime(double t){
        tiempo = t;
    }
    
    public void clear(){
        tiempo = 0;
    }

    @Override
    public String toString() {
        return Double.toString(tiempo);
    }
    
    
}
