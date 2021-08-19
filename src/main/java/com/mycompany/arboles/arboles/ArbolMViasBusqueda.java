package com.mycompany.arboles.arboles;

import com.mycompany.arboles.excepciones.ExcepcionClaveNoExiste;
import com.mycompany.arboles.excepciones.ExcepcionOrdenInvalido;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolMViasBusqueda<K extends Comparable<K>,V>
    implements IArbolBusqueda<K,V>{
    protected NodoMVias<K,V> raiz;
    protected int orden;
    protected int POSICION_INVALIDA=-1;
    
    public ArbolMViasBusqueda(){
        this.orden=3;
    }
    public ArbolMViasBusqueda(int orden) throws ExcepcionOrdenInvalido{
        if(orden<3){
            throw new ExcepcionOrdenInvalido();
        }
        this.orden= orden;
    }
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if(valorAInsertar == null){
            throw new RuntimeException("No se permite insertar valores nulos");
        }
        if(claveAInsertar == null){
            throw new RuntimeException("No se permite insertar claves nulas");
        }
        if(this.esArbolVacio()){
            this.raiz = new NodoMVias<>(this.orden,claveAInsertar, valorAInsertar);
            return;
        }
        NodoMVias<K,V> nodoActual = this.raiz;
        while(!NodoMVias.esNodoVacio(nodoActual)){
            int posicionDeClave= this.obtenerPosicionDeClave(nodoActual, claveAInsertar);
            if(posicionDeClave != POSICION_INVALIDA){ //SI LA CLAVE ESTA EN EL NODO
                nodoActual.setValor(posicionDeClave, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
            }else{
                if(nodoActual.esHoja()){
                    if(nodoActual.estanClavesLlenas()){
                        int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                        NodoMVias<K,V> nuevoHijo = new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                    }else{
                        this.insertarClaveYValorOrdenadaEnNodo(nodoActual,claveAInsertar,valorAInsertar);
                    }
                    nodoActual= NodoMVias.nodoVacio();
                }else{
                    int posicionPorDondeBajar = this.obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
                    if(nodoActual.esHijoVacio(posicionPorDondeBajar)){
                        NodoMVias<K,V> nuevoHijo = new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBajar, nuevoHijo);
                    }else{
                        nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                    }
                    
                }
            }
        }
    }
    private void insertarClaveYValorOrdenadaEnNodo(NodoMVias<K,V> nodoActual, K claveAInsertar, V valorAInsertar){
       int cant = nodoActual.cantidadDeClavesNoVacias();
       for(int i=0; i<cant; i++){
           K claveActual = nodoActual.getClave(i);
           if(claveAInsertar.compareTo(claveActual) < 0){
               for(int k=cant; k>i;k--){
                   K clave= nodoActual.getClave(k-1);
                   V valor = nodoActual.getValor(k-1);
                   nodoActual.setClave(k, clave);
                   nodoActual.setValor(k, valor);
               }
               nodoActual.setClave(i, claveAInsertar);
               nodoActual.setValor(i, valorAInsertar);
               return;
           }
       }
       nodoActual.setClave(cant, claveAInsertar);
       nodoActual.setValor(cant, valorAInsertar);
    }
    private int obtenerPosicionDeClave(NodoMVias<K,V> nodoActual, K claveAInsertar){
        int posicion = -1;
        int i=0;
        while(i<nodoActual.cantidadDeClavesNoVacias()){
            if(claveAInsertar.compareTo(nodoActual.getClave(i))==0){
                posicion=i;
            }
            i++;
        }
        return posicion;
        
    }
    private int obtenerPosicionPorDondeBajar(NodoMVias<K,V> nodoActual, K claveAInsertar){
        int i=0;
        while(i<orden-1){
            if(claveAInsertar.compareTo(nodoActual.getClave(i))<0){
                return i;
            }
            i++;
        }
        return i;
    }
    @Override
    public V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste {
        V valorAEliminar= this.buscar(claveAEliminar);
        if(valorAEliminar == null ){
            throw new ExcepcionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        
        return valorAEliminar;
    }
    private NodoMVias<K,V> eliminar(NodoMVias<K,V> nodoActual, K claveAEliminar){
        //orden 10 - cantidad=9 - i solo llega a 8 
        for(int i=0; i< nodoActual.cantidadDeClavesNoVacias(); i++){
            K claveActual = nodoActual.getClave(i);
            if(claveAEliminar.compareTo(claveActual)==0){
               if(nodoActual.esHoja()){
                   this.eliminarClaveYValorDelNodo(nodoActual,i);
                   if(nodoActual.cantidadDeClavesNoVacias()==0){
                       return NodoMVias.nodoVacio();
                   }
                   return nodoActual;
               } 
               //No es hoja el nodo Actual
               K claveDeReemplazo;
//               if(this.hayHijosMasAdelante(nodoActual, i)){
//                   claveDeReemplazo =this.buscarClaveSucesoraInOrden(nodoActual, claveAEliminar);
//               } else{
//                   claveDeReemplazo =this.buscarClavePredecesoraInOrden(nodoActual, claveAEliminar);
//               }
//               V valorDeReemplazo = buscar(claveDeReemplazo);
//               nodoActual = eliminar(nodoActual, claveDeReemplazo);
//               nodoActual.setClave(i, claveDeReemplazo);
//               nodoActual.setValor(i, valorDeReemplazo);
               return nodoActual;
            }
            //no esta en la posicion i del nodoActual
            if(claveAEliminar.compareTo(claveActual)<0){
                NodoMVias<K,V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            } 
        }
        //si llego aqui sin retornar quiere decir que nunca baje por ningun lado ni lo encontre
        NodoMVias<K,V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), claveAEliminar);
                nodoActual.setHijo(nodoActual.cantidadDeClavesNoVacias(), supuestoNuevoHijo);
                return nodoActual;
    }
private void eliminarClaveYValorDelNodo(NodoMVias<K,V> nodoActual, int i){
    
}
    @Override
    public V buscar(K claveABuscar) {
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            boolean huboCambioDeNodoActual = false;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias()
                    && !huboCambioDeNodoActual; i++) {
                K claveActual = nodoActual.getClave(i);
                if (claveABuscar.compareTo(claveActual) == 0) {
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijo(i);
                    huboCambioDeNodoActual = true;
                }
            }//fin del for
            if (!huboCambioDeNodoActual) {
                nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
            }
        }
        return (V) NodoMVias.datoVacio(); //retornaNulo
    }
    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != NodoMVias.datoVacio();
    }

    @Override
    public int size() {
        //contar la cantidad de nodos
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
       return altura(this.raiz);
    }
    public int altura(NodoMVias<K,V> nodoActual){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaMayor = 0;
        for(int i=0;i<this.orden;i++){
            int alturaActual = altura(nodoActual.getHijo(i));
            if(alturaActual>alturaMayor){
                alturaMayor= alturaActual;
            }
        }
        
        return alturaMayor+1;
        
    }

    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
        
    }

    @Override
    public List<K> recorridoPorNiveles() {
    List<K> recorrido = new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        
        Queue<NodoMVias<K,V>> colaDeNodos =new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                recorrido.add(nodoActual.getClave(i));
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if(!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())){
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()));
            }
        }
        return recorrido;    
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }
    private void recorridoEnPreOrden(NodoMVias<K,V> nodoActual, List<K> recorrido){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorrido.add(nodoActual.getClave(i));
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        recorridoEnPreOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), recorrido);
    }

    @Override
    public List<K> recorridoEnInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new LinkedList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }
    private void recorridoEnPostOrden(NodoMVias<K,V> nodoActual, List<K> recorrido){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorridoEnPostOrden(nodoActual.getHijo(i+1), recorrido);
            recorrido.add(nodoActual.getClave(i));
            }
    }
     public V buscarRec (K clave) {
        return this.buscarRec(this.raiz, clave);
    }

    public V buscarRec (NodoMVias<K, V> nodoActual, K clave) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return (V)NodoMVias.datoVacio();
        }
        int cant = nodoActual.cantidadDeClavesNoVacias();
        for (int i = 0; i < cant; i++) {
            K claveActual = nodoActual.getClave(i);
            V valorActual = nodoActual.getValor(i);
            if(clave.compareTo(claveActual) == 0){
                return valorActual;
            }else if(clave.compareTo(claveActual) < 0) {
                return buscarRec(nodoActual.getHijo(i), clave);
            }
        }
        return buscarRec(nodoActual.getHijo(cant), clave);
    } 
}
