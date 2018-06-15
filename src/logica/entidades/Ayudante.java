/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;

import logica.estados.EstadoAyudante;

/**
 *
 * @author Dani
 */
public class Ayudante {

    private EstadoAyudante estado;

    public Ayudante() {
        this.estado = EstadoAyudante.OCIO;
    }

    public void ocupar() {
        estado = EstadoAyudante.TRABAJANDO;
    }

    public boolean estaOcupado() {
        return estado.equals(EstadoAyudante.TRABAJANDO);
    }

    public void liberar() {
        estado = EstadoAyudante.OCIO;

    }
}
