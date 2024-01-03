package Algoritmo;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Pablo Oca Galeano
 *
 * @version 03/01/2024
 */
public class Tablero {

    /**
     * Esta clase define el tablero de juego. This class defines the board.
     */
    /**
     * Atributos que necesitamos para el tablero, una matriz y un objeto del
     * tipo Random para la generación de los números. Attributes that we need
     * for the board, a matrix and an object of type Random to generate the
     * numbers.
     */
    private int[][] matrix;
    private Random random;

    /**
     * Método Constructor que definirá: - El tablero es definido como una matriz
     * de números enteros de tamaño 4x4, mismo tamaño que en los juegos móviles.
     * The board is define as a 4x4 int matrix, the same dimensions as the
     * mobile phone game. - Inicialización del objeto Random para generar
     * números aleatorios. Inicialization of the Random object for the random
     * numbers generation.
     */
    public Tablero() {
        matrix = new int[4][4];
        random = new Random();
    }

    /**
     * Método para la generación de los números aleatorios y su posicionamiento
     * en el tablero. Method for the generation of the random numbers and its
     * positioning on the board.
     */
    public int[] colocarNumeroAleatorio(int intentos, int tamLista) {
        if (intentos <= 0) {
            System.out.println("No se pudo colocar un número aleatorio después de varios intentos");
            return null;
        } else if (tamLista == 16) {
            System.out.println("El tam de la lista es 16");
            return null;
        } else {

            int fila = random.nextInt(4);
            int columna = random.nextInt(4);
            int valor = (random.nextInt(2) + 1) * 2;

            if (matrix[fila][columna] == 0) {
                matrix[fila][columna] = valor;
                int[] posicion = {fila, columna};
                return posicion;
            } else {
                return colocarNumeroAleatorio(intentos - 1, tamLista);
            }
        }
    }

    /**
     * Método para mostrar por pantalla el tablero Method to display the board
     * on the screen
     */
    public void mostrarMatriz() {
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Método para mover a la izquierda un número Method to move a number to the
     * left
     *
     * @param posicionesOcupadas
     */
    public boolean moverIzquierda(List<int[]> posicionesOcupadas) {
        int index = random.nextInt(posicionesOcupadas.size());
        int[] posicionAleatoria = posicionesOcupadas.get(index);

        if (posicionAleatoria == null) {
            return false;
        } else {

            System.out.println("Posición aleatoria seleccionada: Fila " + posicionAleatoria[0] + ", Columna " + posicionAleatoria[1]);

            int fila = posicionAleatoria[0];
            int columna = posicionAleatoria[1];

            int columnaAnterior = columna - 1;
            if (columnaAnterior >= 0) {
                if (matrix[fila][columnaAnterior] == 0) {
                    matrix[fila][columnaAnterior] = matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {fila, columnaAnterior};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else if (matrix[fila][columnaAnterior] == matrix[fila][columna]) {
                    matrix[fila][columnaAnterior] = matrix[fila][columnaAnterior] + matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {fila, columnaAnterior};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
    }

    public boolean moverDerecha(List<int[]> posicionesOcupadas) {
        int index = random.nextInt(posicionesOcupadas.size());
        int[] posicionAleatoria = posicionesOcupadas.get(index);

        if (posicionAleatoria == null) {
            return false;
        } else {

            System.out.println("Posición aleatoria seleccionada: Fila " + posicionAleatoria[0] + ", Columna " + posicionAleatoria[1]);

            int fila = posicionAleatoria[0];
            int columna = posicionAleatoria[1];

            int columnaSiguiente = columna + 1;
            if (columnaSiguiente < 4) {
                if (matrix[fila][columnaSiguiente] == 0) {
                    matrix[fila][columnaSiguiente] = matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {fila, columnaSiguiente};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else if (matrix[fila][columnaSiguiente] == matrix[fila][columna]) {
                    matrix[fila][columnaSiguiente] = matrix[fila][columnaSiguiente] + matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {fila, columnaSiguiente};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public boolean moverArriba(List<int[]> posicionesOcupadas) {
        int index = random.nextInt(posicionesOcupadas.size());
        int[] posicionAleatoria = posicionesOcupadas.get(index);

        if (posicionAleatoria == null) {
            return false;
        } else {
            System.out.println("Posición aleatoria seleccionada: Fila " + posicionAleatoria[0] + ", Columna " + posicionAleatoria[1]);

            int fila = posicionAleatoria[0];
            int columna = posicionAleatoria[1];

            int filaAnterior = fila - 1;

            if (filaAnterior >= 0) {
                if (matrix[filaAnterior][columna] == 0) {
                    matrix[filaAnterior][columna] = matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {filaAnterior, columna};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else if (matrix[filaAnterior][columna] == matrix[fila][columna]) {
                    matrix[filaAnterior][columna] = matrix[filaAnterior][columna] + matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {filaAnterior, columna};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public boolean moverAbajo(List<int[]> posicionesOcupadas) {
        int index = random.nextInt(posicionesOcupadas.size());
        int[] posicionAleatoria = posicionesOcupadas.get(index);

        if (posicionAleatoria == null) {
            return false;
        } else {
            System.out.println("Posición aleatoria seleccionada: Fila " + posicionAleatoria[0] + ", Columna " + posicionAleatoria[1]);

            int fila = posicionAleatoria[0];
            int columna = posicionAleatoria[1];

            int filaSiguiente = fila + 1;

            if (filaSiguiente < 4) {
                if (matrix[filaSiguiente][columna] == 0) {
                    matrix[filaSiguiente][columna] = matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {filaSiguiente, columna};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else if (matrix[filaSiguiente][columna] == matrix[fila][columna]) {
                    matrix[filaSiguiente][columna] = matrix[filaSiguiente][columna] + matrix[fila][columna];
                    matrix[fila][columna] = 0;
                    posicionesOcupadas.remove(index);
                    int[] nuevaPosicion = {filaSiguiente, columna};
                    posicionesOcupadas.add(nuevaPosicion);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public int obtenerMaximo() {
        int max = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    max = matrix[i][j];
                } else if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

}
