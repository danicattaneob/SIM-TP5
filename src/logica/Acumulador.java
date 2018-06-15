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
public class Acumulador {
    private double tiempoJefeMostrador;
    private double tiempoJefeCocina;
    private boolean jefeEnMostrador;
    private double tiempoAyudanteTrabajando;
    private double tiempoAyudanteLibre;
    private boolean ayudanteLibre;
    private double tiempoAnt;

    public Acumulador() {
        jefeEnMostrador = true;
        ayudanteLibre = true;
        tiempoJefeMostrador = 0;
        tiempoJefeCocina = 0;
        tiempoAyudanteTrabajando = 0;
        tiempoAyudanteLibre = 0;
        tiempoAnt = 0;
    }
    
    
    public void acumular(double tiempoAct, Gestor g){
        if (jefeEnMostrador) {
            tiempoJefeMostrador += (tiempoAct - tiempoAnt);
        }else{
            tiempoJefeCocina += (tiempoAct - tiempoAnt);
        }
        
        if (ayudanteLibre) {
            tiempoAyudanteLibre += (tiempoAct - tiempoAnt);
        }else{
            tiempoAyudanteTrabajando += (tiempoAct - tiempoAnt);
        }
        
        if (g.getJefe().estaEnCocina()) {
            jefeEnMostrador = false;
        }
        
        if (g.getAyudante().estaOcupado()) {
            ayudanteLibre = false;
        }
        
        tiempoAnt = tiempoAct;
    }

    public double getTiempoJefeMostrador() {
        return tiempoJefeMostrador;
    }

    public double getTiempoJefeCocina() {
        return tiempoJefeCocina;
    }

    public double getTiempoAyudanteTrabajando() {
        return tiempoAyudanteTrabajando;
    }

    public double getTiempoAyudanteLibre() {
        return tiempoAyudanteLibre;
    }
    
    
}
