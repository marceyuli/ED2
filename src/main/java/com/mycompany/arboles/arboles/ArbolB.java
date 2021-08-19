//package com.mycompany.arboles.arboles;
//
//import com.mycompany.arboles.excepciones.ExcepcionClaveNoExiste;
//import com.mycompany.arboles.excepciones.ExcepcionOrdenInvalido;
//import java.util.Stack;
//
//public class ArbolB <K extends Comparable<K>,V> extends ArbolMViasBusqueda<K,V>{
//    private int nroMaximoDeDatos;
//    private int nroMinimoDeDatos;
//    private int nroMinimoDeHijos;
//    public ArbolB(){
//        super();
//        this.nroMaximoDeDatos = 2;
//        this.nroMinimoDeDatos =1;
//        this.nroMinimoDeHijos =2;
//    }
//    public ArbolB(int orden) throws ExcepcionOrdenInvalido{
//        super(orden);
//        this.nroMaximoDeDatos = super.orden - 1;
//        this.nroMinimoDeDatos = this.nroMaximoDeDatos /2;
//        this.nroMinimoDeHijos = this.nroMinimoDeDatos + 1;
//    }
//    public void insertar(K claveAInsertar, V valorAInsertar) {
//        if (valorAInsertar == null) {
//            throw new RuntimeException("No se puede insertar valores nulos");
//        }
//        if (claveAInsertar == null) {
//            throw new RuntimeException("No se permite insertar claves nulas");
//        }
//        if (this.esArbolVacio()) {
//            this.raiz = new NodoMVias<>(this.orden + 1, claveAInsertar, valorAInsertar);
//            return;
//        }
//        Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();
//        NodoMVias<K, V> nodoActual = this.raiz;
//        while (!NodoMVias.esNodoVacio(nodoActual)) {
//            int posicionDeClave = super.obtenerPosicionDeClave(nodoActual, claveAInsertar);
//            if (posicionDeClave != POSICION_INVALIDA) {
//                nodoActual.setValor(posicionDeClave, valorAInsertar);
//                nodoActual = NodoMVias.nodoVacio();
//            } else {
//                if(nodoActual.esHoja()){
//                    if(nodoActual.estanClavesLlenas()){
//                       this.insertarClaveYValorOrdenadoEnNodo(nodoActual,claveAInsertar,valorAInsertar);
//                       if(nodoActual.cantidadDeClavesNoVacias()>this.nroMaximoDeDatos){
//                           this.dividir(nodoActual,pilaDeAncestros);
//                       }
//                       nodoActual = NodoMVias.nodoVacio();
//                    }else{
//                        int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
//                        pilaDeAncestros.push(nodoActual);
//                        nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
//                    }
//                }
//            }
//        }
//    }
//    public V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste {
//        if(claveAEliminar == null){
//            throw new IllegalArgumentException("Clave a eliminar no puede ser nula");
//        }
//        Stack<NodoMVias<K,V>> pilaDeAncestros = new Stack<>();
//        NodoMVias<K,V> nodoActual = this.buscarNodoDeLaClave(claveAEliminar, pilaDeAncestros);
//        if(NodoMVias.esNodoVacio(nodoActual)){
//            throw new ExcepcionClaveNoExiste();
//        }
//        int posicionDeClaveAEliminarEnNodo = super.obtenerPosicionPorDondeBajar(nodoActual, claveAEliminar);
//        V valorARetornar = nodoActual.getValor(posicionDeClaveAEliminarEnElNodo);
//        if(nodoActual.esHoja()){
//            super.eliminarClaveYValorDelNodo(nodoActual, posicionDeClaveAEliminarEnElNodo);
//            if(nodoActual.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos){
//                if(pilaDeAncestros.isEmpty()){
//                    if(nodoActual.cantidadDeClavesNoVacias()==0){
//                        super.vaciar();
//                    }
//                }else{
//                    this.prestarOFusionar(nodoActual, pilaDeAncestros);
//                }
//            }
//        } else{
//            pilaDeAncestros.push(nodoActual);
//            NodoMVias<K,V> nodoDelPredecesor = this.buscarNodoDeClavePredecesora(pilaDeAncestros, nodoActual.getHijo(posicionDeClaveAEliminarEnElNodo));
//            int posicionDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacias() - 1;
//            K clavePredecesora = nodoDelPredecesor.getClave(posicionDelPredecesor);
//            V valorPredecesor = nodoDelPredecesor.getValor(posicionDelPredecesor);
//            super.eliminarClaveYValorDelNodo(nodoDelPredecesor, posicionDelPredecesor);
//            nodoActual.setClave(posicionDeClaveAEliminarEnElNodo, clavePredecesora);
//            nodoActual.setValor(posicionDeClaveAEliminarEnElNodo, valorPredecesor);
//            if(nodoDelPredecesor.cantidadDeClavesNoVacias()< this.nroMaximoDeDatos){
//                this.prestarOFusionar(nodoDelPredecesor, pilaDeAncestros);
//            }
//        }
//        return valorARetornar;
//    }
//}
