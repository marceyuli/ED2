package grafos.nopesados;

import java.util.LinkedList;
import java.util.List;

public class EjerciciosDigrafo {
    private Digrafo digrafo;
  
    private DFS dfs;
    private UtilsRecorridos controlMarcados;


    public EjerciciosDigrafo (Digrafo unDigrafo) {
        this.digrafo = unDigrafo;
        //this.conexo = new Conexo(diGrafo);
        controlMarcados = new UtilsRecorridos(digrafo.cantidadDeVertices());
    }
    
    public boolean hayCiclo() {
        for(int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            if(hayCiclo(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCiclo(int posVertice) {
        controlMarcados.marcarVertice(posVertice);
        Iterable<Integer> adyacentesDeVerticeEnTurno = digrafo.adyacenciasDeVertice(posVertice);
        for(Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno) {
            if(controlMarcados.estaVerticeMarcado(posVerticeAdyacente)) {
               return true;
            } else {
                if(hayCiclo(posVerticeAdyacente) == true) {
                    return true;
                    
                }
            }
        }
        controlMarcados.desmarcarVertice(posVertice);
        for(Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno) {
            controlMarcados.desmarcarVertice(posVerticeAdyacente);
        }
        return false;
    }
    
    public int cantIslas() {
       Grafo grafo = transformar(digrafo);
       EjerciciosGrafo ejecutar = new EjerciciosGrafo(grafo);
       int cantIslas= ejecutar.islas();
        return cantIslas;
    }
    public void elementosIslas() {
        dfs = new DFS(digrafo, 0);
        System.out.println("0" + dfs.recorrido);
            for (int i = 0; i < digrafo.listaDeAdyacencias.size(); i++) {
                if (!dfs.controlMarcados.estaVerticeMarcado(i)) {
                    dfs.procesarDFS(i);
                    DFS dfs2 = new DFS(digrafo,i);
                    String pos = "" + i;
                    System.out.println(pos + dfs2.recorrido);
                }
            }
    }
     public void elementosIslas2() {
        dfs = new DFS(digrafo, 0);
        System.out.println("0" + dfs.elRecorrido());
            for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
                if (!dfs.hayCaminoAVertice(i)) {
                    dfs.procesarDFS(i);
                    DFS dfs2 = new DFS(digrafo,i);
                    String pos = "" + i;
                    System.out.println(pos + dfs2.elRecorrido());
                }
            }
    }
    public LinkedList<Integer> elementosPadre(int x){
        LinkedList<Integer> L1 = new LinkedList();
        AW ejecutar = new AW(digrafo);
        int[][] matrizDeCaminos = ejecutar.algoritmoWharsall();
        for(int i=0; i<digrafo.cantidadDeVertices() ;i++){
            if(sePuedeLlegar(x,i,matrizDeCaminos) && x!=i){
                L1.add(i);
            }
        }
        return L1;
    }
    public boolean sePuedeLlegar(int x, int pos, int[][] m) {
        if(m[pos][x]==1){
            return true;
        }
       return false;
    }
    public LinkedList<Integer> elementosPadre2(int x){
        LinkedList<Integer> L1 = new LinkedList();
        for(int i=0; i<digrafo.cantidadDeVertices() ;i++){
            if(sePuedeLlegar2(x,i) && x!=i){
                L1.add(i);
            }
        }
        return L1;
    }
    private boolean sePuedeLlegar2(int x, int pos) {
        DFS dfs = new DFS(digrafo,pos);
        if(dfs.controlMarcados.estaVerticeMarcado(x)){
            return true;
        }
        return false;
    }
    public boolean esDebilmenteConexo(){
        Grafo grafo = transformar(digrafo);
        EjerciciosGrafo ejecutar = new EjerciciosGrafo(grafo);
        if(ejecutar.esConexo()){
            return true;
        }
        return false;
    }

    private Grafo transformar(Digrafo digrafo) {
        int cantidad= digrafo.cantidadDeVertices();
        Grafo grafo = new Grafo();
        for(int i=0; i<digrafo.cantidadDeVertices();i++){
            grafo.insertarVertice();
        }
        for(int i=0; i<digrafo.cantidadDeVertices();i++){
            List<Integer> L1 = digrafo.listaDeAdyacencias.get(i);
            for(int j=0;j<L1.size();j++ ){
                grafo.listaDeAdyacencias.get(i).add(L1.get(j));
                grafo.listaDeAdyacencias.get(L1.get(j)).add(i);
            }
        }
        
        return grafo;
    }

    
}
