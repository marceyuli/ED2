package grafos.nopesados;

import com.mycompany.arboles.excepciones.ExcepcionAristaYaExiste;
import com.mycompany.arboles.excepciones.ExcepcionNroVerticesInvalido;

public class EjerciciosGrafo {
    private Grafo grafo;
    private UtilsRecorridos controlMarcados;
   // private Conexo conexo;
    //private DFS dfs;
    
     public EjerciciosGrafo(Grafo grafo) {
        this.grafo = grafo;
        controlMarcados = new UtilsRecorridos(grafo.cantidadDeVertices());
    }
     
    public int islas() {
        int cantIslas = 1;
            DFS dfs = new DFS(grafo, 0);
            for (int i = 0; i < grafo.listaDeAdyacencias.size(); i++) {
                if (!dfs.controlMarcados.estaVerticeMarcado(i)) {
                    cantIslas++;
                    dfs.procesarDFS(i);
                }
            }

        return cantIslas;
    }
    
    public boolean esConexo(){
        DFS dfs = new DFS(grafo,0);
        if(dfs.hayCaminoATodos()){
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean hayCiclo(Grafo grafoAux) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste  {
        for(int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if(hayCiclo(grafoAux, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCiclo(Grafo grafoAux, int posVertice) throws ExcepcionAristaYaExiste {
        
        controlMarcados.marcarVertice(posVertice);
        Iterable<Integer> adyacentesDeVertice = grafo.listaDeAdyacencias.get(posVertice);
        for (Integer adyacentes : adyacentesDeVertice) {
            if(!controlMarcados.estaVerticeMarcado(adyacentes)) {
                if(!grafoAux.existeAdyacencia(posVertice, adyacentes) && posVertice != adyacentes) {
                    grafoAux.insertarArista(posVertice, adyacentes);
                    hayCiclo(grafoAux, adyacentes);
                }
            } else {
                if(!grafoAux.existeAdyacencia(posVertice, adyacentes) && posVertice != adyacentes) {
                    grafoAux.insertarArista(posVertice, adyacentes);
                    return true;
                }
            }
        }

        return false;
    }
    
    public void verticesCiclo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste  {
        Grafo grafoAux = new Grafo(grafo.cantidadDeVertices());
        for(int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if(hayCiclo(grafoAux, i)) {
                verticesCiclo(grafoAux,i);
            }
        }
    }
     public void verticesCiclo(Grafo grafoAux, int posVertice) throws ExcepcionAristaYaExiste {
         System.out.println(controlMarcados);
         for (int i = 0; i < grafo.listaDeAdyacencias.size(); i++) {
                if (controlMarcados.estaVerticeMarcado(i)) {
                    String pos = "" ;
                    System.out.println(pos + i);
                }
            }
    }

}
