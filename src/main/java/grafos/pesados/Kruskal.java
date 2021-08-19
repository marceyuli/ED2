package grafos.pesados;

import com.mycompany.arboles.excepciones.ExcepcionAristaYaExiste;
import com.mycompany.arboles.excepciones.ExcepcionNroVerticesInvalido;
import java.util.LinkedList;

public class Kruskal {
    private GrafoPesado grafo;

    public Kruskal(GrafoPesado grafo) {
        this.grafo = grafo;
    }
    
     public GrafoPesado ProcesarKruskal() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste
    {
        GrafoPesado aux = new GrafoPesado(grafo.cantidadDeVertices());
        AristasOrdenadas ejecutar = new AristasOrdenadas(grafo);
        LinkedList<Arista> a = ejecutar.aristasDelGrafo();
        for(int i=0; i< a.size(); i++){
            aux.insertarArista(a.get(i).verticeOrigen, a.get(i).verticeDestino, a.get(i).peso);
            if(aux.hayCiclo()){
                aux.eliminarArista(a.get(i).verticeOrigen, a.get(i).verticeDestino);
            }
            
        }
        return aux;
    }
}
