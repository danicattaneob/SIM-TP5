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
public enum EstadoCliente {
    ESPERANDO_ATENCION("Esperando atencion"),
    SIENDO_ATENDIDO("Siendo atendido"),
    ESPERANDO_TRANSMISION("Esperando transmision"),
    ESPERANDO_PEDIDO("Esperando pedido");

    private final String nombre;

    private EstadoCliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}
