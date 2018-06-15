/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import java.util.LinkedList;
import logica.Gestor;
import logica.entidades.Cliente;

/**
 *
 * @author Dani
 */
public class FinEntrPed extends Evento {
    private Gestor g;
    private final double min = 5;
    private final double max = 10;
    private final Cliente cliente;
    private double rndTiempPrep;
    private double tiempoPrep;

    public FinEntrPed(Gestor g, Cliente cliente, double tiempoFin) {
        this.g = g;
        this.cliente = cliente;
        this.tiempoEjec = randomTiempoAt(tiempoFin);
    }

    public void duplicarTiempo(double tiempoAct){
        tiempoEjec = (tiempoEjec - tiempoAct) * 2 + tiempoAct; 
    }
    
    public void dividirTiempo(double tiempoAct){
        tiempoEjec = (tiempoEjec - tiempoAct) / 2 + tiempoAct;
    }

    public double randomTiempoAt(double tiempoActual) {
        rndTiempPrep = Math.random();
        tiempoPrep = rndTiempPrep * (max - min) + min;
        return tiempoActual + tiempoPrep;
    }
    
    @Override
    public void ejecutar() {
        
        if (!g.hayPedidos()) {
            g.getAyudante().liberar();
            if (g.getJefe().estaEnCocina()) {
                g.getJefe().liberar();
            }
        }
    }

    @Override
    public String toString() {
        return "FinEntrPed " + tiempoEjec;
    }

    public double getRndTiempPrep() {
        return rndTiempPrep;
    }

    public double getTiempoPrep() {
        return tiempoPrep;
    }
    
    
    
}
