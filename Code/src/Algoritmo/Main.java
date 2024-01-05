package Algoritmo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que representa el juego.
 * @author Pablo Oca Galeano
 *
 * @version 03/01/2024
 * 
 * - Creación de los objetos iniciales.
 * - Creación del bucle principal
 */
public class Main {

    /**
     * Constante que identifica el número máximo de intentos.
    */
    public static final int NUM_INTENTOS = 15;

    public static void main(String[] args) {
        /**
        * tablero 
        *      Creación del objeto Tablero. 
        *      Representa el tablero de juego. 
        *      Nos permitirá usar generar los números en las posiciones aleatorias y mover los números de casillas.
        */
        Tablero tablero = new Tablero();
        /**
        * posicionesOcupadas
        *      Lista de las posiciones ocupadas en el tablero. 
        *      Nos permitirá tener un control de las posiciones que están ocupadas para no situar números aleatorios allí. 
        *      También nos permitirá tener un control de las posiciones sobre las que podemos hacer movimientos
        */
        List<int[]> posicionesOcupadas = new ArrayList<>();
        /**
        * movimiento
        *      Variable para saber si se ha efectuado o no un movimiento.
        *      Nos permite imprimir el mensaje acerca de si se ha realizado o no un movimiento
        */
        boolean movimiento;
        /**
        * maximo
        *      Representa el número máximo del tablero.
        *      Nos permite tener un control sobre el número de iteraciones.
        * tamLista
        *      Representa el tamaño de la lista de posiciones.
        *      Nos permite tener un control sobre el número de iteraciones.
        */
        int maximo, tamLista;

        /**
        *   Primera iteración antes del bucle.
        *   
        *   Se obtiene el tamaño de la lista para pasarlo como parámetro al método de generación de números aleatorios.
        *   Tras haber situado un número en unap posición aleatoria del tablero, se inicializa la lista de posiciones ocupadas.
        *   Se obtiene el máximo y se cambia el valor del tamaño de la lista.
        */
        tamLista = posicionesOcupadas.size();
        int[] posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
        posicionesOcupadas.add(posicion);
        maximo = tablero.obtenerMaximo();
        tamLista = posicionesOcupadas.size();

        /**
        *   Bucle principal que se ejecutará mientras que el máximo sea 2048 que será indicativo de que se ha terminado el juevo o
        *   que el tamaño de la lista sea diferente de 17 puesto que el método size() nos devuelve el tamaño + 1.
        * 
        *   El bucle muestra el principio y al final la matriz, es decir el tablero, de cara a ver como avanza el juego.
        *   Intenta hacer movimientos a la izquierda, derecha, arriba y abajo, de cara a hacer avanzar el juego.
        * 
        *   Tras intentar realizar todos los movimientos, genera un número número aleatorio.
        */
        while (maximo != 2048 && tamLista != 17) {
            tablero.mostrarMatriz();
            
            movimiento = tablero.moverIzquierda(posicionesOcupadas);
            mostrarMovimiento(0, movimiento);
            
            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();*/

            movimiento = tablero.moverDerecha(posicionesOcupadas);
            mostrarMovimiento(1, movimiento);

            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();*/

            movimiento = tablero.moverArriba(posicionesOcupadas);
            mostrarMovimiento(2, movimiento);

            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();*/

            movimiento = tablero.moverAbajo(posicionesOcupadas);
            mostrarMovimiento(3, movimiento);

            
            posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();

            maximo = tablero.obtenerMaximo();
            tablero.mostrarMatriz();
        }
        /**
        *   Tras la finalización del bucle, muestra cuál es el número máximo que se ha obtenido.
        */
        System.out.println("El número máximo es " + maximo);
    }

    /**
     * Método para mostrar el movimiento realizado.
     * Method to show the movement performed
     * @param num --> Número que identifica el movimiento realizado. Number to identify the movement made.
     * @param movimiento --> Booleano que identifica si se ha realizado o no el movimiento. Boolean that identifies whether or not the movement has been performed.
    */
    public static void mostrarMovimiento(int num, boolean movimiento) {
        String[] direcciones = {"la izquierda", "la derecha", "arriba", "abajo"};
        if (movimiento) {
            System.out.println("Se ha efectuado un movimiento hacia " + direcciones[num]);
        } else {
            System.out.println("No se ha podido efectuar ningún movimiento hacia " + direcciones[num]);
        }
    }

}
