package com.mycompany.arboles.arboles;

import com.mycompany.arboles.excepciones.ExcepcionClaveNoExiste;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ArbolBinarioBusqueda<K extends Comparable<K>,V> 
    implements IArbolBusqueda<K,V>{

    protected NodoBinario<K,V> raiz;

    public ArbolBinarioBusqueda() {
    }
    //EJERCICIO 13
     public ArbolBinarioBusqueda(List<K> clavesInOrden,List<V> valoresInOrden,
                                 List<K> clavesNoInOrden, List<V> valoresNoInOrden, boolean esConPreOrden){
       if(clavesInOrden.size()!= clavesNoInOrden.size() || 
          valoresInOrden.size()!= valoresNoInOrden.size() ||
          clavesInOrden.size() != valoresNoInOrden.size()){
           throw new RuntimeException("No se permiten tamaños distintos");
       } 
       if(esConPreOrden){
           this.raiz = reconstruccionConPreOrden( clavesInOrden,valoresInOrden, clavesNoInOrden, valoresNoInOrden);
       }
       else{
           this.raiz= reconstruccionConPostOrden(clavesInOrden,valoresInOrden, clavesNoInOrden, valoresNoInOrden);
       }
    }

   
     private NodoBinario<K,V>reconstruccionConPostOrden(List<K> clavesInOrden,List<V> valoresInOrden,
                                 List<K> clavesPostOrden, List<V> valoresPostOrden){
          if(clavesInOrden.isEmpty()){
            return null;
        }
        int posicionDeClavePadreEnPostOrden =clavesPostOrden.size()-1;
        K clavePadre = clavesPostOrden.get(posicionDeClavePadreEnPostOrden);
        V valorPadre = valoresPostOrden.get(posicionDeClavePadreEnPostOrden);
        int posicionDeClavePadreEnInOrden = this.posicionDeClave(clavePadre, clavesInOrden);
        //Armar rama por Izquierda
        List<K> clavesInOrdenPorIzquierda = clavesInOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<V> valoresInOrdenPorIzquierda = valoresInOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<K> clavesPostOrdenPorIzquierda = clavesPostOrden.subList(0, posicionDeClavePadreEnInOrden);
        List<V> valoresPostOrdenPorIzquierda = valoresPostOrden.subList(0, posicionDeClavePadreEnInOrden);
        NodoBinario<K,V> hijoIzquierdo = reconstruccionConPostOrden(clavesInOrdenPorIzquierda,valoresInOrdenPorIzquierda,
                                                                    clavesPostOrdenPorIzquierda,valoresPostOrdenPorIzquierda);
        //Armar rama derecha
        List<K> clavesInOrdenPorDerecha = clavesInOrden.subList(posicionDeClavePadreEnInOrden+1, clavesInOrden.size());
        List<V> valoresInOrdenPorDerecha = valoresInOrden.subList(posicionDeClavePadreEnInOrden+1, clavesInOrden.size());
        List<K> clavesPostOrdenPorDerecha = clavesPostOrden.subList(posicionDeClavePadreEnInOrden,clavesPostOrden.size()-1);
        List<V> valoresPostOrdenPorDerecha = valoresPostOrden.subList(posicionDeClavePadreEnInOrden,clavesPostOrden.size()-1);
        NodoBinario<K,V> hijoDerecho = reconstruccionConPostOrden(clavesInOrdenPorDerecha,valoresInOrdenPorDerecha,
                                                                  clavesPostOrdenPorDerecha,valoresPostOrdenPorDerecha);
        // Aramar nodo Actual
        NodoBinario<K,V> nodoActual = new NodoBinario<>(clavePadre,valorPadre);
        nodoActual.setHijoDerecho(hijoDerecho);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        return nodoActual;
     }
    private int posicionDeClave(K claveAEncontrar, List<K> listaDeClaves){
        for(int i=0; i< listaDeClaves.size(); i++){
            K claveActual=listaDeClaves.get(i);
            if(claveActual.compareTo(claveAEncontrar)==0){
                return i;
            }
        }
        return -1;
    }
    private NodoBinario<K,V>reconstruccionConPreOrden(List<K> clavesInOrden,List<V> valoresInOrden,
                                 List<K> clavesPreOrden, List<V> valoresPreOrden){
        if(clavesInOrden.isEmpty()){
            return null;
        }
        int posicionDeClavePadrePreOrden = 0;
        K clavePadre = clavesPreOrden.get(posicionDeClavePadrePreOrden);
        V valorPadre = valoresPreOrden.get(posicionDeClavePadrePreOrden);
        int posicionDeClavePadreInOrden = posicionDeClave(clavePadre, clavesInOrden);
        //Armando Rama Izquierda
        List<K> clavesInOrdenPorIzquierda= clavesInOrden.subList(0, posicionDeClavePadreInOrden);
        List<V> valoresInOrdenPorIzquierda= valoresInOrden.subList(0, posicionDeClavePadreInOrden);
        List<K> clavesPreOrdenPorIzquierda= clavesInOrden.subList(1, posicionDeClavePadreInOrden+1);
        List<V> valorespreOrdenPorIzquierda= valoresInOrden.subList(1, posicionDeClavePadreInOrden+1);
        NodoBinario<K,V> hijoIzquierdo = reconstruccionConPreOrden(clavesInOrdenPorIzquierda,valoresInOrdenPorIzquierda,clavesPreOrdenPorIzquierda,valorespreOrdenPorIzquierda);
        //Armando Rama Derecha
         List<K> clavesInOrdenPorDerecha= clavesInOrden.subList(posicionDeClavePadreInOrden+1, clavesInOrden.size());
        List<V> valoresInOrdenPorDerecha= valoresInOrden.subList(posicionDeClavePadreInOrden+1, clavesInOrden.size());
        List<K> clavesPreOrdenPorDerecha= clavesInOrden.subList(posicionDeClavePadreInOrden+1, clavesInOrden.size());
        List<V> valorespreOrdenPorDerecha= valoresInOrden.subList(posicionDeClavePadreInOrden+1, clavesInOrden.size());
        NodoBinario<K,V> hijoDerecho = reconstruccionConPreOrden(clavesInOrdenPorDerecha,valoresInOrdenPorDerecha,clavesPreOrdenPorDerecha,valorespreOrdenPorDerecha);
        //Armamos el arbol
        NodoBinario<K,V> nodoActual = new NodoBinario<>(clavePadre, valorPadre);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);
        return nodoActual;
    }
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if(valorAInsertar == null){
            throw new RuntimeException("No se permite insertar valores nulos");
        }
        if(this.esArbolVacio()){
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return;
        }
        NodoBinario<K,V> nodoAnterior =NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual =this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            nodoAnterior= nodoActual;
            if(claveAInsertar.compareTo(claveActual)<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            } else if (claveAInsertar.compareTo(claveActual)>0){
                nodoActual=nodoActual.getHijoDerecho();
            } else{
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        //Si llego a este punto quiere decir, que encontre donde insertar
        NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar,valorAInsertar);
        K claveAnterior = nodoAnterior.getClave();
        if(claveAInsertar.compareTo(claveAnterior)<0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
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
    
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveAEliminar){
        K claveActual= nodoActual.getClave();
        
        if(claveAEliminar.compareTo(claveActual)<0){
            NodoBinario<K,V> supuestoNuevoHijoIzq = eliminar(nodoActual.getHijoIzquierdo(),
                                                             claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzq);
            return nodoActual;
        }
        
        if(claveAEliminar.compareTo(claveActual)>0){
            NodoBinario<K,V> supuestoNuevoHijoDer = eliminar(nodoActual.getHijoDerecho(),
                                                             claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoDer);
            return nodoActual;
        }
        //si llego aca, ya encontre el nodo con la clave a eliminar
        //revisamos que caso es 
        //CASO 1
        if(nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //Caso 2
        //Caso 2.1
        if(!nodoActual.esVacioHijoIzquierdo() && 
            nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
        //Caso 2.2
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoDerecho();
        }
        //Caso 3 
        NodoBinario<K,V> nodoDelSucesor = buscarSucesor(nodoActual.getHijoDerecho());
        
        NodoBinario<K,V> supuestoNuevoHijo= eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());
        
        nodoActual.setHijoDerecho(supuestoNuevoHijo);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());
        
        return nodoActual;
    }
    
    public NodoBinario<K,V> buscarSucesor(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
    @Override
    public V buscar(K claveABuscar) {
        NodoBinario<K,V> nodoActual= this.raiz;
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            if(claveABuscar.compareTo(claveActual)<0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            else if(claveABuscar.compareTo(claveActual)>0){
                nodoActual=nodoActual.getHijoDerecho();
            }
            else{
                return nodoActual.getValor();
            }
        }
        return null;
    }

    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != null;
    }

    @Override
    public int size() {
        if(this.esArbolVacio()){
            return 0;
        }
        int cantidadDeNodos= 0;
        Queue<NodoBinario<K,V>> colaDeNodos =new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            cantidadDeNodos++;
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return cantidadDeNodos; 
    }
    
    public int sizeRec() {
        return sizeRec(this.raiz);
    }
    private int sizeRec(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadPorIzquierda= sizeRec(nodoActual.getHijoIzquierdo());
        int cantidadPorDerecha = sizeRec(nodoActual.getHijoDerecho());
        return cantidadPorIzquierda + cantidadPorDerecha + 1;
    }
    @Override
    public int altura() {
        return altura(this.raiz);
    }
    public int altura(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
        
        return alturaPorIzquierda > alturaPorDerecha ? alturaPorIzquierda + 1:
                alturaPorDerecha + 1;
        
    }
    public int alturaIt(){
         if(this.esArbolVacio()){
            return 0;
        }
        int alturaDelArbol= 0;
        Queue<NodoBinario<K,V>> colaDeNodos =new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        
        while(!colaDeNodos.isEmpty()){
            int nroDeNodosDelNivel = colaDeNodos.size();
            int posicion =  0;
            while (posicion < nroDeNodosDelNivel) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                posicion++;
            }
            alturaDelArbol++; 
        }
        return alturaDelArbol;
    }

    @Override
    public int nivel() {
        int nivel = this.alturaIt() - 1;
        return nivel;
    }

    @Override
    public void vaciar() {
        this.raiz=NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        
        Queue<NodoBinario<K,V>> colaDeNodos =new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido= new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            
            if(!nodoActual.esVacioHijoDerecho()){
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return recorrido;
    }

    public List<K> recorridoEnPreOrdenRec(){
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrdenRec(this.raiz, recorrido);
        return recorrido;
    }
    private void recorridoEnPreOrdenRec(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenRec(nodoActual.getHijoDerecho(), recorrido);
    }
    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        NodoBinario<K, V> nodoActual = this.raiz;
        insertarEnPilaRamaIzquierda(nodoActual, pilaDeNodos);

        //iniciando a sacar nodos de la pila
        while (!pilaDeNodos.isEmpty()) {
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoDerecho()){
               insertarEnPilaRamaIzquierda(nodoActual.getHijoDerecho(), pilaDeNodos);
            }
        }
        return recorrido;
    }
    private void insertarEnPilaRamaIzquierda(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
            }
        }
    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido= new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = this.raiz;
        insertarEnPilaParaPostOrden(nodoActual, pilaDeNodos);
        
        //iniciando a sacar nodos de la pila
        while(!pilaDeNodos.isEmpty()){
            nodoActual= pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if(!pilaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoDelTope = pilaDeNodos.peek();
                if(!nodoDelTope.esVacioHijoDerecho() && 
                    nodoDelTope.getHijoDerecho() != nodoActual){
                    insertarEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(),pilaDeNodos);
                }
            }
        }
        return recorrido;
    }

    private void insertarEnPilaParaPostOrden(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            if(!nodoActual.esVacioHijoIzquierdo()){
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            else{
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }
        public int cantidadHijosVacios(){
        return cantidadHijosVacios(this.raiz);
    }
    private int cantidadHijosVacios(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        if(nodoActual.esHoja()){
            return 2;
        }
        int c=0;
        if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
            c++;
        }
        if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
            c++;
        }
        int cantidadHijosVaciosPorIzquierda= cantidadHijosVacios(nodoActual.getHijoIzquierdo());
        int cantidadHijosVaciosPorDerecha= cantidadHijosVacios(nodoActual.getHijoDerecho()); 
        return cantidadHijosVaciosPorIzquierda+cantidadHijosVaciosPorDerecha+c;
    }
    //3:Implemente un método iterativo que retorne la cantidad de nodos 
    //que tienen ambos hijos distintos de vacío en un árbol binario
    public int cantidadNodosAmbosHijos(){
        int cantidad=0;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            if(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
                cantidad++;
            }
            if(!nodoActual.esVacioHijoDerecho()){
            colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
            colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
        }
        return cantidad;
    }
    //4:Implemente un método recursivo que retorne la cantidad de nodos 
    //que tienen ambos hijos distintos de vacío en un árbol binario
    public int cantidadNodosAmbosHijosR(){
        return cantidadNodosAmbosHijosR(this.raiz);
    }
    private int cantidadNodosAmbosHijosR(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadDeHijosPorIzq= cantidadNodosAmbosHijosR(nodoActual.getHijoIzquierdo());
        int cantidadDeHijosPorDer= cantidadNodosAmbosHijosR(nodoActual.getHijoDerecho());
        int cantidadTotal= cantidadDeHijosPorIzq+cantidadDeHijosPorDer;
        if(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
            cantidadTotal++;
        }
        return cantidadTotal;
    }
    //5. Implemente un método iterativo que retorne la cantidad de nodos 
    //que tienen ambos hijos distintos de vacío en un árbol binario, pero solo en el nivel N
    public int cantidadNodosAmbosHijosEnNivel(int nivelN) {
        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            if (nivelDeNodo(nodoActual) == nivelN) {
                if (!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
                    cantidad++;
                }
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
        }
        return cantidad;
    }
    public int nivelDeNodo(NodoBinario<K,V> nodo){
        int nivel =0;
        NodoBinario<K,V> nodoActual = this.raiz;
        K claveABuscar = nodo.getClave();
        while(claveABuscar.compareTo(nodoActual.getClave())!=0 && !NodoBinario.esNodoVacio(nodoActual)){
            if(claveABuscar.compareTo(nodoActual.getClave())<0){
                nodoActual = nodoActual.getHijoIzquierdo();
                nivel++;
            }
            else{
                nodoActual = nodoActual.getHijoDerecho();
                nivel++;
            }
            
        }
        if(NodoBinario.esNodoVacio(nodoActual)){
            return -1;
        }
        return nivel;
    }
    //6. Implemente un método recursivo que retorne la cantidad de nodos 
    //que tienen ambos hijos distintos de vacío en un árbol binario, pero solo en el nivel N
    public int cantidadNodosAmbosHijosEnNivelR(int nivelN){
        return cantidadNodosAmbosHijosEnNivelR(this.raiz,nivelN,0);
    }
    private int cantidadNodosAmbosHijosEnNivelR(NodoBinario<K,V> nodoActual, int nivelN, int nivelActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadPorIzquierda = cantidadNodosAmbosHijosEnNivelR(nodoActual.getHijoIzquierdo(),nivelN,nivelActual+1);
        int cantidadPorDerecha= cantidadNodosAmbosHijosEnNivelR(nodoActual.getHijoDerecho(),nivelN,nivelActual+1);
        int cantidad= cantidadPorIzquierda+cantidadPorDerecha;
        if(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo() && nivelActual==nivelN){
            cantidad++;
        }
        return cantidad;
    }
    //7. Implemente un método iterativo que retorne la cantidad nodos que tienen un solo hijo diferente de vacío 
    //en un árbol binario, pero solo antes del nivel N
    public int cantidadNodosUnHijoAntesN(int nivelN){
        int cantidad=0;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList();
        colaDeNodos.offer(this.raiz);
       while(!colaDeNodos.isEmpty()){
           NodoBinario<K,V> nodoActual = colaDeNodos.poll();
           if(nivelDeNodo(nodoActual)<nivelN){
               if((!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()) ||
                   (nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo())){
                   cantidad++;
               }
           }
          if(!nodoActual.esVacioHijoDerecho()){
              colaDeNodos.offer(nodoActual.getHijoDerecho());
          }
          if(!nodoActual.esVacioHijoIzquierdo()){
              colaDeNodos.offer(nodoActual.getHijoIzquierdo());
          }
       }
               
              
        return cantidad;
    }
    //12. Implemente un método iterativo con la lógica de un recorrido en inOrden que retorne el número 
    //de nodos que tiene un árbol binario.
   public int cantNodosInOrden(){
       int cantidad=0;
       Stack<NodoBinario<K,V>> pilaDeNodos = new Stack();
       NodoBinario<K,V> nodoActual = this.raiz;
       insertarEnPilaRamaIzquierda(nodoActual,pilaDeNodos);
       while(!pilaDeNodos.isEmpty()){
           nodoActual=pilaDeNodos.pop();
           cantidad++;
           if(!nodoActual.esVacioHijoDerecho()){
               insertarEnPilaRamaIzquierda(nodoActual.getHijoDerecho(),pilaDeNodos);
           }
       }
       return cantidad;
   }
   //SUCESOR IN ORDEN
  public NodoBinario<K, V> sucesorInOrden(NodoBinario<K, V> nodoRecibido) {
        List<K> recorridoInOrden = new LinkedList<>();
        recorridoInOrden = this.recorridoEnInOrden();
        int posicionRecibido = this.posicionDeClave(nodoRecibido.getClave(), recorridoInOrden);
        if((posicionRecibido == recorridoInOrden.size() - 1)) {
            //throw new RuntimeException("Nodo no tiene sucesor");
            return null;
        }
        int posicionDelSucesor = this.posicionDeClave(nodoRecibido.getClave(), recorridoInOrden) + 1;
        K claveDelSucesor = recorridoInOrden.get(posicionDelSucesor);
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);


        while(!colaDeNodos.isEmpty()) {
           NodoBinario<K, V> nodoActual = colaDeNodos.poll();
           if(nodoActual.getClave() == claveDelSucesor) {
               return nodoActual;
           }
           if(!nodoActual.esVacioHijoIzquierdo()) {
               colaDeNodos.offer(nodoActual.getHijoIzquierdo());
           }
           if(!nodoActual.esVacioHijoDerecho()) {
               colaDeNodos.offer(nodoActual.getHijoDerecho());
           }
        }
        return null;
    }
  //Predecesor in orden 
public NodoBinario<K, V> predecesorInOrden(NodoBinario<K, V> nodoRecibido) {
        List<K> recorridoInOrden = new LinkedList<>();
        recorridoInOrden = this.recorridoEnInOrden();
        int posicionRecibido = this.posicionDeClave(nodoRecibido.getClave(), recorridoInOrden);
        if(posicionRecibido == 0) {
            //throw new RuntimeException("Nodo no tiene sucesor");
            return null;
        }
        int posicionDelPredecesor = this.posicionDeClave(nodoRecibido.getClave(), recorridoInOrden) - 1;
        K claveDelSucesor = recorridoInOrden.get(posicionDelPredecesor);
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();
            if(nodoActual.getClave() == claveDelSucesor) {
                return nodoActual;
            }
            if(!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return null;
    }
//16. Implemente un método que retorne la menor llave en un árbol binario de búsqueda
        public K menorLlave(){
            NodoBinario<K,V> nodoActual = this.raiz;
            NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
            while(!NodoBinario.esNodoVacio(nodoActual)){
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            return nodoAnterior.getClave();     
        }
//21. Implemente un método que retorne verdadero si un árbol binario esta lleno. Falso en caso
//contrario
        public boolean arbolLleno(){
            Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual = colaDeNodos.poll();
                if(!nodoActual.esHoja()){
                    if(!(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo())){
                        return false;
                    }
                }
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
            return true;
        }
         public boolean arbolLlenoR(){
          boolean flag=true;
          flag= arbolLlenoR(this.raiz,flag);
          return flag;
      }
      private boolean arbolLlenoR(NodoBinario<K,V> nodoActual,boolean flag){
          if(NodoBinario.esNodoVacio(nodoActual)){
              flag=false;
          }
          else{
             boolean verificaDerecha= arbolLlenoR(nodoActual.getHijoDerecho(),flag);
             boolean verificaIzquierda= arbolLlenoR(nodoActual.getHijoIzquierdo(),flag);
             boolean total= verificaDerecha&&verificaIzquierda;
             if(total && (!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) ||
                (nodoActual.esHoja())   ){
                 flag=true;
             }else
                 flag=false;
          }
          return flag;
      }
      public int sizeR(){
          return sizeR(this.raiz);
      }
      private int sizeR(NodoBinario<K,V> nodoActual){
          if(NodoBinario.esNodoVacio(nodoActual)){
              return 0;
          }
          int sizeIzquierda = sizeR(nodoActual.getHijoIzquierdo());
          int sizeDerecha = sizeR(nodoActual.getHijoDerecho());
          int total = sizeIzquierda+sizeDerecha;
          total = total +1;
          return total;
      }
}
