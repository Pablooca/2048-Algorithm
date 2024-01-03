package Algoritmo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo Oca Galeano
 *
 * @version 03/01/2024
 */
public class Main {

    public static final int NUM_INTENTOS = 5;

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        List<int[]> posicionesOcupadas = new ArrayList<>();
        boolean movimiento;
        int maximo, tamLista;

        tamLista = posicionesOcupadas.size();
        int[] posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
        posicionesOcupadas.add(posicion);
        maximo = tablero.obtenerMaximo();
        tamLista = posicionesOcupadas.size();

        while (maximo != 2048 && tamLista != 17) {
            tablero.mostrarMatriz();
            movimiento = tablero.moverIzquierda(posicionesOcupadas);
            mostrarMovimiento(0, movimiento);

            posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();

            movimiento = tablero.moverDerecha(posicionesOcupadas);
            mostrarMovimiento(1, movimiento);

            posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();

            movimiento = tablero.moverArriba(posicionesOcupadas);
            mostrarMovimiento(2, movimiento);

            posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();

            movimiento = tablero.moverAbajo(posicionesOcupadas);
            mostrarMovimiento(3, movimiento);

            posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();

            maximo = tablero.obtenerMaximo();
        }
        
        System.out.println("El número máximo es " + maximo);
    }

    public static void mostrarMovimiento(int num, boolean movimiento) {
        String[] direcciones = {"la izquierda", "la derecha", "arriba", "abajo"};
        if (movimiento) {
            System.out.println("Se ha efectuado un movimiento hacia " + direcciones[num]);
        } else {
            System.out.println("No se ha podido efectuar ningún movimiento hacia " + direcciones[num]);
        }
    }

}
