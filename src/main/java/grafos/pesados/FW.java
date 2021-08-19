package grafos.pesados;

import java.util.List;

public class FW {

    private DigrafoPesado digrafo;
    int INF = 99999;
    
    public FW(DigrafoPesado digrafo) {
        this.digrafo = digrafo;
    }

    int[][] floydWarshall()
    {
        int tamaño = digrafo.cantidadDeVertices();
        int[][] dist = minimasDistancias();
        int i, j, k;    
        for (k = 0; k < tamaño; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < tamaño; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < tamaño; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }
    public int[][] minimasDistancias() {
        int tamaño = this.digrafo.cantidadDeVertices();
        int[][] matriz = new int[tamaño][tamaño];
        //inicializa con ceros e infinitos
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < tamaño; i++) {
        List<AdyacentesConPeso> adyacentesDeUnVertice = digrafo.listaDeAdyacencia.get(i);
            for (AdyacentesConPeso posDeAdyacente:adyacentesDeUnVertice) {
                matriz[i][posDeAdyacente.indiceVertice] = (int) posDeAdyacente.peso;
           }
        }
        return matriz;
    }

    public void showMatriz(int[][] matriz) {
        int tamaño = digrafo.cantidadDeVertices();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
