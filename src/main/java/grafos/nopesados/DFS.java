package grafos.nopesados;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    public UtilsRecorridos controlMarcados;
    private Grafo grafo;
    public List<Integer> recorrido;
    
    public DFS(Grafo unGrafo, int posVerticePartida){
        this.grafo =unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlMarcados = new UtilsRecorridos(this.grafo.cantidadDeVertices()); //ya esta todo desmarcado
        procesarDFS(posVerticePartida);
    }

    public void procesarDFS(int posVertice) {
        controlMarcados.marcarVertice(posVertice);
        recorrido.add(posVertice);
        Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacenciasDeVertice(posVertice);
        for(Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno){
            if(!controlMarcados.estaVerticeMarcado(posVerticeAdyacente)){
                procesarDFS(posVerticeAdyacente);
            }
        }
        
    }
    public boolean hayCaminoAVertice(int posVertice){
        grafo.validarVertice(posVertice);
        return controlMarcados.estaVerticeMarcado(posVertice);
    }
    public Iterable<Integer> elRecorrido(){
        return this.recorrido;
    }
    public boolean hayCaminoATodos(){
        return controlMarcados.estanTodosMarcados();
    }
}
