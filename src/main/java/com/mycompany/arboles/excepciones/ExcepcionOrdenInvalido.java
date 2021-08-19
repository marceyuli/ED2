
package com.mycompany.arboles.excepciones;

public class ExcepcionOrdenInvalido extends Exception{
     public ExcepcionOrdenInvalido() {
        super("Orden del arbol debe ser mayor o igual a 3");
    }

    /**
     * Constructs an instance of <code>ExcepcionClaveNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionOrdenInvalido(String msg) {
        super(msg);
    }
}
