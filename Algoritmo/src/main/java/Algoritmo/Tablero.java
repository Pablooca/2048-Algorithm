package Algoritmo;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Pablo Oca Galeano
 *
 * @version 03/01/2024 - Creación de los métodos para realizar los movimientos
 * básicos (arriba, abajo, izquierda, derecha) - Creación del método para la
 * generación aleatoria de los números. - Creación del método para imprimir la
 * matriz por pantalla - Creación del método para la obtención del máximo
 *
 * - Creation of methods to perform basic movements (up, down, left, right) -
 * Creation of the method for the random generation of numbers. - Creation of
 * the method to print the matrix on the screen. - Creation of the method to
 * obtain the maximum
 *
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
     *
     * @param intentos --> Número de veces que se ejecuta el método en el caso
     * de que no se encuentre ninguna posición. Number of times the method is
     * executed if no position is found.
     * @param tamLista --> Tamaño de la lista para comprobar si se puede o no
     * añadir un número al tablero. List size to check whether or not a number
     * can be added to the board.
     * @return - El método devuelve "null" si se ha llegado al máximo de
     * intentos o si el tamaño de la lista es 16 (número total de casillas). En
     * caso contrario devuelve la posición en la que se ha añadido el número. -
     * The method returns "null" if "intentos" is equal to 0 or the list size is
     * 16 (total number of length). Otherwise, it returns the posicion where the
     * number has been added.
     */
    public int[] colocarNumeroAleatorio(int intentos, int tamLista, List<int[]> posicionesDesocupadas) {

        if (posicionesDesocupadas.size() != 0) {
            int index = random.nextInt(posicionesDesocupadas.size());
            System.out.println(index);
            int[] posicionAleatoria = posicionesDesocupadas.get(index);
            int fila = posicionAleatoria[0];
            int columna = posicionAleatoria[1];

            int valor = (random.nextInt(2) + 1) * 2;

            matrix[fila][columna] = valor;
            int[] posicion = {fila, columna};
            return posicion;
        }
        else {
            return null;
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
     * @param posicionesOcupadas --> Lista de la cual se escoge la posición a
     * mover hacia la izquierda. List from which the position to move to the
     * left is chosen.
     * @return - El método devuelve "true" si se ha podido efectuar el
     * movimiento a la izquierda. En caso contrario, devuelve "false" - The
     * method return "true" if the movement to the left could be performed.
     * Otherwise, returns "false"
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

    /**
     * Método para mover a la derecha un número Method to move a number to the
     * right
     *
     * @param posicionesOcupadas --> Lista de la cual se escoge la posición a
     * mover hacia la derecha. List from which the position to move to the right
     * is chosen.
     * @return - El método devuelve "true" si se ha podido efectuar el
     * movimiento a la derecha. En caso contrario, devuelve "false" - The method
     * return "true" if the movement to the right could be performed. Otherwise,
     * returns "false"
     */
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

    /**
     * Método para mover hacia arriba un número Method to move a number up
     *
     * @param posicionesOcupadas --> Lista de la cual se escoge la posición a
     * mover hacia arriba. List from which the position to move up is chosen.
     * @return - El método devuelve "true" si se ha podido efectuar el
     * movimiento hacia arriba. En caso contrario, devuelve "false" - The method
     * return "true" if the upward movement could be carried out. Otherwise,
     * returns "false"
     */
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

    /**
     * Método para mover hacia abajo un número Method to move a number down
     *
     * @param posicionesOcupadas --> Lista de la cual se escoge la posición a
     * mover hacia abajo. List from which the position to move down is chosen.
     * @return - El método devuelve "true" si se ha podido efectuar el
     * movimiento hacia arriba. En caso contrario, devuelve "false" - The method
     * return "true" if the downward movement could be carried out. Otherwise,
     * returns "false"
     */
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

    /**
     * Método para obtener el máximo del tablero. El máximo es importante porque
     * determina cuántas iteraciones realiza el bucle principal en el main.
     * Method to get the maximum of the board. The maximum is important because
     * it determines how many iterations the main loop performs in the main.
     *
     * @return - El método devuelve el máximo del tablero. - The method return
     * the maximum of the board.
     */
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

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

}
