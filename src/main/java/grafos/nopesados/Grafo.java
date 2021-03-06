
package grafos.nopesados;

import com.mycompany.arboles.excepciones.ExcepcionAristaNoExiste;
import com.mycompany.arboles.excepciones.ExcepcionAristaYaExiste;
import com.mycompany.arboles.excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {
    protected List<List<Integer>> listaDeAdyacencias;
    
    public Grafo(){
        this.listaDeAdyacencias = new ArrayList<>();
    }
    public Grafo(int nroInicialDeVertices)throws ExcepcionNroVerticesInvalido{
        if(nroInicialDeVertices <=0 ){
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAdyacencias = new ArrayList<>();
        for(int i=0; i< nroInicialDeVertices;i++){
            this.insertarVertice();
        }
    }
    public void insertarVertice(){
        List<Integer> adyacentesDeNuevoVertice = new ArrayList<>();
        this.listaDeAdyacencias.add(adyacentesDeNuevoVertice);
    }
    public int cantidadDeVertices(){
        return listaDeAdyacencias.size();
    }
    
    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        return adyacentesDelVertice.size();
    }

    public void validarVertice(int posDeVertice) {
        if(posDeVertice<0 || posDeVertice>=this.cantidadDeVertices()){
            throw new IllegalArgumentException("No existe vertice en la posicion" + posDeVertice + "en este Grafo");
        }
    }
    
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino)throws ExcepcionAristaYaExiste{
        if(this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        if(posVerticeOrigen!=posVerticeDestino){
              List<Integer> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            adyacentesDelDestino.add(posVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacentesDelOrigen.contains(posVerticeDestino);
    }
    public Iterable<Integer> adyacenciasDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> iterableDeAdyacentes = adyacentesDelVertice;
        return iterableDeAdyacentes;
    }
    public int cantidadDeAristas(){
        int cantAristas =0;
        int cantLazos=0;
        for(int i=0; i<this.listaDeAdyacencias.size();i++){
            List<Integer> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            for(Integer posDeAdyacente: adyacentesDeUnVertice){
                if(i==posDeAdyacente){
                    cantLazos++;
                }else{
                    cantAristas++;
                }
            }
        }
        return cantLazos + (cantAristas / 2);
    }
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)throws ExcepcionAristaNoExiste{
        if(!existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen) ;
        int posicionDelDestino = adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(posicionDelDestino);
        if(posVerticeOrigen!=posVerticeDestino){
            List<Integer> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            int posicionDelOrigen = adyacentesDelDestino.indexOf(posVerticeOrigen);
            adyacentesDelDestino.remove(posicionDelOrigen);
        }
    }
      public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<Integer> adyacentesDeUnVertice: this.listaDeAdyacencias) {
            int posicionDeVerticeEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++){
                int posicionAdyacente = adyacentesDeUnVertice.get(i);
                if (posicionAdyacente > posVerticeAEliminar){
                    adyacentesDeUnVertice.set(i,posicionAdyacente - 1);
                }
            }
        }
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < listaDeAdyacencias.size(); i++) {
            List<Integer> listaDeAdyacentes = listaDeAdyacencias.get(i);
            s = s + i + "  [ " + listaDeAdyacencias.get(i) + " ]-->[ ";
            for (int j = 0; j < listaDeAdyacentes.size(); j++) {
                if (j == listaDeAdyacentes.size() - 1) {
                    s = s + "" + listaDeAdyacentes.get(j);
                } else {
                    s = s + "" + listaDeAdyacentes.get(j) + " , ";
                }
            }
            s = s + " ]" + '\n';
        }
        return s;
    }
}
