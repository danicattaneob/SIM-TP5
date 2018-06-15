/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

/**
 *
 * @author Dani
 */
public enum EstadoJefe {
    ATENDIENDO("Atendiendo"),
    LIBRE("Libre"),
    EN_COCINA("En Cocina");

    private final String nombre;

    private EstadoJefe(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}
