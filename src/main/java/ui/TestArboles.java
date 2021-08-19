package ui;

import com.mycompany.arboles.arboles.AVL;
import com.mycompany.arboles.arboles.ArbolBinarioBusqueda;
import com.mycompany.arboles.arboles.IArbolBusqueda;
import java.util.Scanner;

public class TestArboles {

    public static void main(String[] args) {
        IArbolBusqueda<Integer, String> arbolBusqueda;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Elija un tipo de arbol(ABB,AVL): ");
        String tipoArbol = entrada.next();
        tipoArbol = tipoArbol.toUpperCase();
        switch (tipoArbol) {
            case "ABB":
                arbolBusqueda = new ArbolBinarioBusqueda<>();
                break;
            case "AVL":
                arbolBusqueda = new AVL<>();
            default:
                return;
        }
        arbolBusqueda.insertar(15, "Manzana");
        arbolBusqueda.insertar(7, "Manzana");
        arbolBusqueda.insertar(28, "Manzana");
        arbolBusqueda.insertar(1, "Manzana");
        arbolBusqueda.insertar(9, "Manzana");
        arbolBusqueda.insertar(29, "Manzana");
        arbolBusqueda.insertar(13, "Manzana");

        
        
        System.out.println(arbolBusqueda.recorridoEnPreOrden());
        
    }
}
