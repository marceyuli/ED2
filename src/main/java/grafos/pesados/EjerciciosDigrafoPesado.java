package grafos.pesados;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosDigrafoPesado {

    private DigrafoPesado digrafo;
    private int[][] fw;
    int INF = 99999;

    public EjerciciosDigrafoPesado(DigrafoPesado digrafo) {
        this.digrafo = digrafo;
        FW ejecutar = new FW(digrafo);
        fw = ejecutar.floydWarshall();
    }
//    Para un grafo dirigido usando la implementación del algoritmo de Floyd-Wharsall encontrar los
//    caminos de costo mínimo entre un vértice a y el resto. Mostrar los costos y cuáles son los caminos

    public List<List<Integer>> caminosDesdeVertice(int vertice) {
        List<List<Integer>> verticesDestino = new ArrayList<>();
        int tamaño = digrafo.cantidadDeVertices();
        FW ejecutar = new FW(digrafo);
        int dist[][] = ejecutar.minimasDistancias();
        int i, j, k;
        i = vertice;
        for (j = 0; j < tamaño; j++) {
            List<Integer> camino = new ArrayList<>();
            for (k = 0; k < tamaño; k++) {
                if (k != i && i != j) {
                    if ((Math.abs(dist[i][k] + dist[k][j]) < dist[i][j])) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        if (camino.isEmpty()) {
                            camino.add(vertice);
                        }
                        camino.add(k);
                    } else if (Math.abs(dist[i][k] + dist[k][j]) == dist[i][j]) {
                        if (dist[i][j] != INF) {
                            if (camino.isEmpty()) {
                                camino.add(vertice);
                            }
                            camino.add(k);
                        }
                    }

                }

            }
            if (!camino.isEmpty() && !camino.contains(j)) {
                camino.add(j);
            }
            verticesDestino.add(camino);
            System.out.println("" + vertice + "-" + j + ":" + camino);

        }
        return verticesDestino;
    }
}
