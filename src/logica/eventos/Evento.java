/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;


/**
 *
 * @author Dani
 */
public abstract class Evento implements Comparable<Evento> {

    protected double tiempoEjec;
    public static final int DECIMALES = 4;

    public double getTiempoEjec() {
        return tiempoEjec;
    }

    public abstract void ejecutar();
    
    protected static double redondear (double numero, int decimales){
        int factorRed = (int)Math.pow(10, (double) decimales);
        int i = (int)(numero * factorRed);
        double res = ((double)i) / ((double)factorRed);
        return res;
    }
    
    
    @Override
    public int compareTo(Evento e) {
        return (int) ((this.getTiempoEjec() - e.getTiempoEjec()) * 1000);
    }
}
