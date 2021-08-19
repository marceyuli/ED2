package grafos.nopesados;

import com.mycompany.arboles.excepciones.ExcepcionAristaYaExiste;
import com.mycompany.arboles.excepciones.ExcepcionNroVerticesInvalido;

public class Test {

    public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        Grafo grafo = new Grafo(6);
        grafo.insertarArista(0,1);
        grafo.insertarArista(1,2);
        grafo.insertarArista(0,2);
        grafo.insertarArista(2,3);
        grafo.insertarArista(1,4);
        
        EjerciciosGrafo ejecutarG = new EjerciciosGrafo(grafo);
        System.out.println(ejecutarG.islas());
//        ejecutar.verticesCiclo();
      //  System.out.println(ejecutar.esConexo());

//        BFS grafoBFS = new BFS(grafo,0);
//        System.out.println(grafoBFS.elRecorrido());
//        
//        grafo.eliminarVertice(1);
//        
//        BFS grafoBFS2 = new BFS(grafo,0);
//        System.out.println(grafoBFS2.elRecorrido());
//        
//        System.out.println(grafo);
////
        Digrafo digrafo = new Digrafo(6);
       digrafo.insertarArista(0, 1);
        digrafo.insertarArista(1, 0);
        digrafo.insertarArista(0, 2);
        digrafo.insertarArista(3, 2);
       digrafo.insertarArista(5, 3);
       digrafo.insertarArista(4, 5);
       digrafo.insertarArista(4, 3);
       digrafo.insertarArista(4, 4);
//        digrafo.insertarArista(3, 4);
//        digrafo.insertarArista(4, 2);
//       // System.out.println(digrafo.cantidadDeAristas());
//       MatrizDeCaminos matriz = new MatrizDeCaminos(digrafo);
//        System.out.println("Matriz de adyacencia: " );
//       matriz.showMatriz(matriz.matrizDeAdyacencia());
////        matriz.showMatriz(matriz.matrizDeCaminos());
        AW aw = new AW(digrafo);
       aw.showMatriz(aw.algoritmoWharsall());
        EjerciciosDigrafo ejecutar = new EjerciciosDigrafo(digrafo);
        //System.out.println(ejecutar.cantIslas());
//        System.out.println(ejecutar.esDebilmenteConexo());
        System.out.println(digrafo);
//        System.out.println(ejecutar.hayCiclo());
       ejecutar.elementosIslas2();
     //   System.out.println(ejecutar.elementosPadre(3));
    }

}
