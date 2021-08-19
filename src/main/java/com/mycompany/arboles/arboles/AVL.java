package com.mycompany.arboles.arboles;

import com.mycompany.arboles.excepciones.ExcepcionClaveNoExiste;

public class AVL<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K,V> {
    private static final byte DIFERENCIA_PERMITIDA=1;
    @Override
    //EJERCICO 1
    public void insertar(K claveAInsertar, V valorAInsertar){
        if(valorAInsertar==null){
            throw new RuntimeException("No se permite insertar valores nulos");
        }
        this.raiz= insertar(this.raiz,claveAInsertar, valorAInsertar);
    }
    
    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual, K claveAInsertar, V valorAInsertar){
        if(NodoBinario.esNodoVacio(nodoActual)){
            NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar,valorAInsertar);
            return nuevoNodo;
        }
        K claveActual= nodoActual.getClave();
        if(claveAInsertar.compareTo(claveActual)<0){
            NodoBinario<K,V> nuevoSupuestoHijo = insertar(nodoActual.getHijoIzquierdo(),
                                                          claveAInsertar, valorAInsertar);
            nodoActual.setHijoIzquierdo(nuevoSupuestoHijo);
            return balancear(nodoActual);
        }
        if(claveAInsertar.compareTo(claveActual)>0){
            NodoBinario<K,V> nuevoSupuestoHijo = insertar(nodoActual.getHijoDerecho(),
                                                          claveAInsertar, valorAInsertar);
            nodoActual.setHijoDerecho(nuevoSupuestoHijo);
            return balancear(nodoActual);
        }
        //Si llego hasta aqui quiere decir que en encontre en el arbol la clave que queria insertar.Entonces, actualiza
        //el valor
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
    }
    //EJERCICIO 2
    
    @Override
    public V eliminar(K claveAEliminar)throws ExcepcionClaveNoExiste{
        V valorAEliminar = this.buscar(claveAEliminar);
        if(valorAEliminar==null){
            throw new ExcepcionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz,claveAEliminar);
        return valorAEliminar;
    }
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveAEliminar){
        K claveActual = nodoActual.getClave();
        if(claveAEliminar.compareTo(claveActual)<0){
            NodoBinario<K,V> supuestoNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(),claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        if(claveAEliminar.compareTo(claveActual)>0){
            NodoBinario<K,V> supuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return balancear(nodoActual);
        }
        //encontro el valor a eliminar
        //1) ES HOJA
        if(nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //2) TIENE SOLO UN HIJO
        if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
            return balancear(nodoActual.getHijoIzquierdo());
        }
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return balancear(nodoActual.getHijoDerecho());
        }
        //3) Tiene ambos hijos
        NodoBinario<K,V> nodoSucesor = buscarSucesor(nodoActual.getHijoDerecho());
        NodoBinario<K,V> supuestoNuevoHijo = eliminar(nodoActual.getHijoDerecho(),nodoSucesor.getClave());
        nodoActual.setHijoDerecho(supuestoNuevoHijo);
        nodoActual.setClave(nodoSucesor.getClave());
        nodoActual.setValor(nodoSucesor.getValor());
        return balancear(nodoActual);
    }
    
    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha= altura(nodoActual.getHijoDerecho());
        int diferenciaDeAltura= alturaPorIzquierda - alturaPorDerecha;
        if(diferenciaDeAltura > DIFERENCIA_PERMITIDA){
            //Rotacion a Derecha
            NodoBinario<K,V> hijoIzquierdoDelActual = nodoActual.getHijoIzquierdo();
            alturaPorIzquierda = altura(hijoIzquierdoDelActual.getHijoIzquierdo());
            alturaPorDerecha = altura(hijoIzquierdoDelActual.getHijoDerecho());
            if(alturaPorDerecha > alturaPorIzquierda){
                return rotacionDobleADerecha(nodoActual);
            }
            return rotacionSimpleADerecha(nodoActual);
        } else if (diferenciaDeAltura < -DIFERENCIA_PERMITIDA){
            NodoBinario<K,V> hijoDerechoDelActual = nodoActual.getHijoDerecho();
            alturaPorIzquierda = altura(hijoDerechoDelActual.getHijoIzquierdo());
            alturaPorDerecha = altura(hijoDerechoDelActual.getHijoDerecho());
            if(alturaPorIzquierda > alturaPorDerecha){
                return rotacionDobleAIzquierda(nodoActual);
            }
                return rotacionSimpleAIzquierda(nodoActual);
        }
        //si estoy por acá, quiere decir que no hay que hacer rotaciones
        return nodoActual;
    }
    private NodoBinario<K,V> rotacionSimpleADerecha(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }
    private NodoBinario<K,V> rotacionDobleADerecha(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoDePrimeraRotacion = rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(nodoDePrimeraRotacion);
        return rotacionSimpleADerecha(nodoActual);
    }
     private NodoBinario<K,V> rotacionSimpleAIzquierda(NodoBinario<K,V> nodoActual){
         NodoBinario<K,V> nodoQueRota = nodoActual.getHijoDerecho();
         nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
         nodoQueRota.setHijoIzquierdo(nodoActual);
         return nodoActual;
     }
     private NodoBinario<K,V> rotacionDobleAIzquierda(NodoBinario<K,V> nodoActual){
         NodoBinario<K,V> nodoDePrimeraRotacion= rotacionSimpleADerecha(nodoActual.getHijoDerecho());
         nodoActual.setHijoDerecho(nodoDePrimeraRotacion);
         return rotacionSimpleAIzquierda(nodoActual);
     }
    
}
