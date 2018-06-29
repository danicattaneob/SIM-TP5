/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import logica.Gestor;

/**
 *
 * @author Dani
 */
public class FinPurgado extends Evento {

    private final Gestor g;
    private static final double TIEMPOPURGADO = 5;

    public FinPurgado(double inicioPurg, Gestor g) {
        this.g = g;
        tiempoEjec = inicioPurg + TIEMPOPURGADO;
    }

    public double getTIEMPOPURGADO() {
        return TIEMPOPURGADO;
    }

    @Override
    public void ejecutar() {
        g.getJefe().finPurga();
        
        g.setTiempoUltimoPurgado(tiempoEjec);
        Purgado proxPurg = new Purgado(g);
        g.setProxPurg(proxPurg);
        g.agregarEvento(proxPurg);
    }
    
    public static double getTiempoPurgado(){
        return TIEMPOPURGADO;
    }

    @Override
    public String toString() {
        return "Fin Purgado";
    }

    
}
