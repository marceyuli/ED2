/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arboles.excepciones;

/**
 *
 * @author USER
 */
public class ExcepcionNroVerticesInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionNroVerticesInvalido</code>
     * without detail message.
     */
    public ExcepcionNroVerticesInvalido() {
        super("No se aceptan cantidad de vertices inferior a 1");
    }

    /**
     * Constructs an instance of <code>ExcepcionNroVerticesInvalido</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionNroVerticesInvalido(String msg) {
        super(msg);
    }
}
