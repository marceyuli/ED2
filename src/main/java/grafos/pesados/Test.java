
package grafos.pesados;

import com.mycompany.arboles.excepciones.ExcepcionAristaYaExiste;
import com.mycompany.arboles.excepciones.ExcepcionNroVerticesInvalido;

public class Test {
    public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        GrafoPesado grafo = new GrafoPesado(6);
        grafo.insertarArista(0, 1, 3);
        grafo.insertarArista(0, 2, 2);
        grafo.insertarArista(1, 3, 5);
        grafo.insertarArista(2, 3, 1);
        grafo.insertarArista(3, 4, 8);
        grafo.insertarArista(4, 5, 9);
        
        Prim arbol = new Prim(grafo);
        GrafoPesado resultado = arbol.arbol();
        System.out.println(resultado);
//        
//        DigrafoPesado digrafo = new DigrafoPesado(4);
//        digrafo.insertarArista(0, 1, 50);
//        digrafo.insertarArista(0, 2, 30);
//        digrafo.insertarArista(1, 3, 10);
//        digrafo.insertarArista(2, 1, 1);
//         FW ejecutar = new FW(digrafo);
////         ejecutar.showMatriz(ejecutar.minimasDistancias());
//         ejecutar.showMatriz(ejecutar.floydWarshall());
//         EjerciciosDigrafoPesado execute = new EjerciciosDigrafoPesado(digrafo);
//         execute.caminosDesdeVertice(2);
//         Kruskal procesar = new Kruskal(grafo);
//         procesar.ProcesarKruskal();
    }
}
