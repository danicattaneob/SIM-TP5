/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import logica.Gestor;
import static logica.eventos.Evento.redondear;

/**
 *
 * @author Dani
 */
public class Purgado extends Evento {

    private double porcInest;
    private double randomPorcInest;
    private double tiempEntrePurg;
    private Gestor g;

    public double getPorcInest() {
        return porcInest;
    }

    public double getRandomPorcInest() {
        return randomPorcInest;
    }

    public double getTiempEntrePurg() {
        return tiempEntrePurg;
    }
    
    

    public Purgado(Gestor g) {
        generarPorcInest();
        this.g = g;
        tiempEntrePurg = calcularRK(T, H, ALFA, porcInest);
        tiempoEjec = g.getTiempoUltimoPurgado() + tiempEntrePurg;
    }

    private double randomProxPurg() {
        randomPorcInest = redondear(Math.random(), DECIMALES);
        return randomPorcInest;
    }

    private void generarPorcInest() {
        randomProxPurg();
        if (randomPorcInest < 0.5) {
            porcInest = 100;
        } else {
            if (randomPorcInest < 0.8) {
                porcInest = 70;
            } else {
                porcInest = 50;
            }
        }
    }

    @Override
    public void ejecutar() {
        
        g.getJefe().purgar();
        FinPurgado finPur = new FinPurgado(tiempoEjec, g);
        g.agregarEvento(finPur);
    }

    @Override
    public String toString() {
        return "Purgado";
    }

    
}
