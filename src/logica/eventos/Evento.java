/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import java.util.LinkedList;

/**
 *
 * @author Dani
 */
public abstract class Evento implements Comparable<Evento> {

    protected double tiempoEjec;

    public double getTiempoEjec() {
        return tiempoEjec;
    }

    public abstract void ejecutar();
    

    @Override
    public int compareTo(Evento e) {
        return (int) (this.getTiempoEjec() - e.getTiempoEjec());
    }
}
