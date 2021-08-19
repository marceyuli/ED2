package com.mycompany.arboles.arboles;

import com.mycompany.arboles.excepciones.ExcepcionOrdenInvalido;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws ExcepcionOrdenInvalido {
//        List<Integer> clavesInOrden= new LinkedList();
//        clavesInOrden.add(1);
//        clavesInOrden.add(7);
//        clavesInOrden.add(9);
//        clavesInOrden.add(13);
//        clavesInOrden.add(15);
//        clavesInOrden.add(28);
//        clavesInOrden.add(29);
//        List<Integer> valoresInOrden= new LinkedList();
//        valoresInOrden.add(1);
//        valoresInOrden.add(7);
//        valoresInOrden.add(9);
//        valoresInOrden.add(13);
//        valoresInOrden.add(15);
//        valoresInOrden.add(28);
//        valoresInOrden.add(29);
//       List<Integer> clavesNoInOrden= new LinkedList();
//        clavesNoInOrden.add(1);
//        clavesNoInOrden.add(13);
//        clavesNoInOrden.add(9);
//        clavesNoInOrden.add(7);
//        clavesNoInOrden.add(29);
//        clavesNoInOrden.add(28);
//        clavesNoInOrden.add(15);
//       List<Integer> valoresNoInOrden= new LinkedList();
//        valoresNoInOrden.add(1);
//        valoresNoInOrden.add(13);
//        valoresNoInOrden.add(9);
//        valoresNoInOrden.add(7);
//        valoresNoInOrden.add(29);
//        valoresNoInOrden.add(28);
//        valoresNoInOrden.add(15);
//        
//        ArbolBinarioBusqueda<Integer,Integer> arbol1 = new ArbolBinarioBusqueda(clavesInOrden,valoresInOrden,
//                                                                          clavesNoInOrden,valoresNoInOrden,false);
//        
//        ArbolBinarioBusqueda<Integer,String> arbolBusqueda = new ArbolBinarioBusqueda();
//        arbolBusqueda.insertar(15, "Manzana");
//        arbolBusqueda.insertar(7, "Manzana");
//        arbolBusqueda.insertar(28, "Manzana");
//        arbolBusqueda.insertar(1, "Manzana");
//        arbolBusqueda.insertar(9, "Manzana");
//        arbolBusqueda.insertar(29, "Manzana");
//        arbolBusqueda.insertar(13, "Manzana");
//
//        //System.out.println(arbolBusqueda.recorridoEnPreOrden());
//        System.out.println(arbolBusqueda.arbolLleno());
//        System.out.println(arbolBusqueda.arbolLlenoR());
//        
        ArbolMViasBusqueda<Integer,Integer> arbolM = new ArbolMViasBusqueda(5);
        arbolM.insertar(7,7);
        arbolM.insertar(8,8);
        arbolM.insertar(15,15);
        arbolM.insertar(21,21);
        arbolM.insertar(6,6);
        arbolM.insertar(9,9);
        arbolM.insertar(25,25);
        arbolM.insertar(10,10);
        System.out.println("M VIAS");
        System.out.println(arbolM.recorridoPorNiveles());
    }
}
