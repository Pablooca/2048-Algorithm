package Algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Clase principal que representa el juego.
 *
 * @author Pablo Oca Galeano
 *
 * @version 03/01/2024
 *
 * - Creación de los objetos iniciales. - Creación del bucle principal
 */
/**
 * Clase principal que representa el juego.
 *
 * @author Pablo Oca Galeano
 *
 * @version 05/01/2024
 *
 * - Comentarios de las clases Main y Tablero. - Exportación del proyecto desde
 * Java Ant a Java Maven - Creación de la conexión con la base de datos MongoDB
 */
/**
 * Clase principal que representa el juego.
 *
 * @author Pablo Oca Galeano
 *
 * @version 24/01/2024
 *
 * - Creación de una lista de posicionesDesocupadas al tener el juego demasiada
 * aleatoriedad, se crea una nueva lista para escoger de ahí la posición a
 * guardar de manera aleatoria.
 * - Creación de los métodos 
 *      - addPosicionesDesocupadas(List <int[]> posicionesDesocupadas)
 *      - actualizarPosicionesDesocupadas(List <int[]> posicionesOcupadas, List <int []> posicionesDesocupadas)
 *      - actualizarPosicionesDesocupadasTrasMovimiento (List <int[]> posicionesDesocupadas, int[][] tablero)
 */
public class Main {

    /**
     * Constante que identifica el número máximo de intentos.
     */
    public static final int NUM_INTENTOS = 15;

    public static void main(String[] args) {
        /**
         * tablero Creación del objeto Tablero. Representa el tablero de juego.
         * Nos permitirá usar generar los números en las posiciones aleatorias y
         * mover los números de casillas.
         */
        Tablero tablero = new Tablero();
        /**
         * posicionesOcupadas Lista de las posiciones ocupadas en el tablero.
         * Nos permitirá tener un control de las posiciones que están ocupadas
         * para no situar números aleatorios allí. También nos permitirá tener
         * un control de las posiciones sobre las que podemos hacer movimientos
         */
        List<int[]> posicionesOcupadas = new ArrayList<>();

        List<int[]> posicionesDesocupadas = new ArrayList<>();
        /**
         * movimiento Variable para saber si se ha efectuado o no un movimiento.
         * Nos permite imprimir el mensaje acerca de si se ha realizado o no un
         * movimiento
         */
        boolean movimiento;
        boolean eliminado;
        boolean listaActualizada;
        /**
         * maximo Representa el número máximo del tablero. Nos permite tener un
         * control sobre el número de iteraciones. tamLista Representa el tamaño
         * de la lista de posiciones. Nos permite tener un control sobre el
         * número de iteraciones.
         */
        int maximo, tamLista;

        addPosicionesDesocupadas(posicionesDesocupadas);

        /**
         * Primera iteración antes del bucle.
         *
         * Se obtiene el tamaño de la lista para pasarlo como parámetro al
         * método de generación de números aleatorios. Tras haber situado un
         * número en unap posición aleatoria del tablero, se inicializa la lista
         * de posiciones ocupadas. Se obtiene el máximo y se cambia el valor del
         * tamaño de la lista.
         */
        tamLista = posicionesOcupadas.size();
        int[] posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista, posicionesDesocupadas);
        posicionesOcupadas.add(posicion);
        maximo = tablero.obtenerMaximo();
        tamLista = posicionesOcupadas.size();

        eliminado = actualizarPosionesDesocupadas(posicionesDesocupadas, posicionesOcupadas);

        if (eliminado) {
            System.out.println("Se ha actualizado la lista de Posiciones Desocupadas");
        } else {
            System.out.println("No se ha podido actualizar la lista de Posiciones Desocupadas");
        }
        /**
         * Bucle principal que se ejecutará mientras que el máximo sea 2048 que
         * será indicativo de que se ha terminado el juevo o que el tamaño de la
         * lista sea diferente de 17 puesto que el método size() nos devuelve el
         * tamaño + 1.
         *
         * El bucle muestra el principio y al final la matriz, es decir el
         * tablero, de cara a ver como avanza el juego. Intenta hacer
         * movimientos a la izquierda, derecha, arriba y abajo, de cara a hacer
         * avanzar el juego.
         *
         * Tras intentar realizar todos los movimientos, genera un número número
         * aleatorio.
         */
        int movimientosSinHacer = 0;
        while (maximo != 2048) {
            //guardarMatrizEnBBDD(tablero.getMatrix());
            tablero.mostrarMatriz();

            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista, posicionesDesocupadas);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();

            eliminado = actualizarPosionesDesocupadas(posicionesDesocupadas, posicionesOcupadas);
            if (eliminado) {
                System.out.println("Se ha actualizado la lista de Posiciones Desocupadas");
            } else {
                System.out.println("No se ha podido actualizar la lista de Posiciones Desocupadas");
            }*/
            movimiento = tablero.moverIzquierda(posicionesOcupadas);
            mostrarMovimiento(0, movimiento);

            listaActualizada = actualizarPosicionesDesocupadasTrasMovimiento(posicionesDesocupadas, tablero.getMatrix());
            if (listaActualizada) {
                System.out.println("Se ha modificado la lista de posiciones desocupadas");
            } else {
                System.out.println("No se ha modificado la lista de posiciones desocupadas");
            }
            if (movimiento) {
                posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista, posicionesDesocupadas);
                posicionesOcupadas.add(posicion);
                tamLista = posicionesOcupadas.size();

                eliminado = actualizarPosionesDesocupadas(posicionesDesocupadas, posicionesOcupadas);
                if (eliminado) {
                    System.out.println("Se ha actualizado la lista de Posiciones Desocupadas");
                }
            }

            movimiento = tablero.moverDerecha(posicionesOcupadas);
            mostrarMovimiento(1, movimiento);

            listaActualizada = actualizarPosicionesDesocupadasTrasMovimiento(posicionesDesocupadas, tablero.getMatrix());
            if (listaActualizada) {
                System.out.println("Se ha modificado la lista de posiciones desocupadas");
            } else {
                System.out.println("No se ha modificado la lista de posiciones desocupadas");
            }
            if (movimiento) {
                posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista, posicionesDesocupadas);
                posicionesOcupadas.add(posicion);
                tamLista = posicionesOcupadas.size();

                eliminado = actualizarPosionesDesocupadas(posicionesDesocupadas, posicionesOcupadas);
                if (eliminado) {
                    System.out.println("Se ha actualizado la lista de Posiciones Desocupadas");
                }
            }

            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();*/
            movimiento = tablero.moverArriba(posicionesOcupadas);
            mostrarMovimiento(2, movimiento);

            listaActualizada = actualizarPosicionesDesocupadasTrasMovimiento(posicionesDesocupadas, tablero.getMatrix());
            if (listaActualizada) {
                System.out.println("Se ha modificado la lista de posiciones desocupadas");
            } else {
                System.out.println("No se ha modificado la lista de posiciones desocupadas");
            }

            if (movimiento) {
                posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista, posicionesDesocupadas);
                posicionesOcupadas.add(posicion);
                tamLista = posicionesOcupadas.size();

                eliminado = actualizarPosionesDesocupadas(posicionesDesocupadas, posicionesOcupadas);
                if (eliminado) {
                    System.out.println("Se ha actualizado la lista de Posiciones Desocupadas");
                }
            }

            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();*/
            movimiento = tablero.moverAbajo(posicionesOcupadas);
            mostrarMovimiento(3, movimiento);

            listaActualizada = actualizarPosicionesDesocupadasTrasMovimiento(posicionesDesocupadas, tablero.getMatrix());
            if (listaActualizada) {
                System.out.println("Se ha modificado la lista de posiciones desocupadas");
            } else {
                System.out.println("No se ha modificado la lista de posiciones desocupadas");
            }

            if (movimiento) {
                posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista, posicionesDesocupadas);
                posicionesOcupadas.add(posicion);
                tamLista = posicionesOcupadas.size();

                eliminado = actualizarPosionesDesocupadas(posicionesDesocupadas, posicionesOcupadas);
                if (eliminado) {
                    System.out.println("Se ha actualizado la lista de Posiciones Desocupadas");
                }
            }

            /*posicion = tablero.colocarNumeroAleatorio(NUM_INTENTOS, tamLista);
            posicionesOcupadas.add(posicion);
            tamLista = posicionesOcupadas.size();*/
            maximo = tablero.obtenerMaximo();
            tablero.mostrarMatriz();

            //guardarMatrizEnBBDD(tablero.getMatrix());
        }
        /**
         * Tras la finalización del bucle, muestra cuál es el número máximo que
         * se ha obtenido.
         */
        //System.out.println("El número máximo es " + maximo);
        //guardarMatrizEnBBDD(tablero.getMatrix());
    }

    /**
     * Método para mostrar el movimiento realizado. Method to show the movement
     * performed
     *
     * @param num --> Número que identifica el movimiento realizado. Number to
     * identify the movement made.
     * @param movimiento --> Booleano que identifica si se ha realizado o no el
     * movimiento. Boolean that identifies whether or not the movement has been
     * performed.
     */
    public static void mostrarMovimiento(int num, boolean movimiento) {
        String[] direcciones = {"la izquierda", "la derecha", "arriba", "abajo"};
        if (movimiento) {
            System.out.println("Se ha efectuado un movimiento hacia " + direcciones[num]);
        } else {
            System.out.println("No se ha podido efectuar ningún movimiento hacia " + direcciones[num]);
        }
    }

    /**
     * Guarda una matriz en una base de datos MongoDB junto con la fecha y hora
     * del sistema. Saves a matrix in a MongoDB database along with the current
     * date and time.
     *
     * @param matriz La matriz a guardar. The matrix to be saved.
     */
    public static void guardarMatrizEnBBDD(int[][] matriz) {
        /**
         * Conexión a la base de datos de MongoDB Connectiong to the MongoDB
         * database
         */
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("2048");
        MongoCollection<Document> collection = db.getCollection("Matrices 2048");

        /**
         * Obtener la fecha y hora actual del sistema. Getting the current date
         * and time.
         */
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);

        /**
         * Crear un nuevo documento para la matriz. Creating a document for the
         * matrix.
         */
        Document doc = new Document();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String key = "elemento_" + i + "_" + j;
                doc.append(key, matriz[i][j]);
            }
        }

        /**
         * Agregar la fecha y hora al documento Adding the date and time to the
         * document
         */
        String key = "Fecha y Hora: ";
        doc.append(key, formattedDate);

        /**
         * Insertar el documento en la colección Inserting the document into the
         * collection
         */
        collection.insertOne(doc);

        /**
         * Cerrar la conexión a la base de datos. Closing the database
         * connection
         */
        mongoClient.close();
    }

    /**
     * Al inicio del programa inicializa la lista de posicionesDesocupadas para poder empezar a insertar de manera aleatoria
     * números en la matriz. At the beginning of the program, it initializes the posicionesDesocupadas list in order to be able
     * to insert numbers into de matrix in a random way.
     * @param posicionesDesocupadas la lista a inicializar. The list which is going to be initialized.
     */
    public static void addPosicionesDesocupadas(List<int[]> posicionesDesocupadas) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println("Se añade la posicion (" + i + "," + j + ")");
                int[] nuevaPosicion = {i, j};
                posicionesDesocupadas.add(nuevaPosicion);
            }
        }
    }

    /**
     * Método para actualizar la lista de posiciones desocupadas tras la inserción de un número aleatorio.
     * Method to update the list of unoccupied positions after inserting a random number.
     * @param posicionesDesocupadas lista con todas las posiciones que no están ocupadas. list with all positions that are not occupied
     * @param posicionesOcupadas lista con todas las posiciones que están ocupadas. list with all the positions that are occupied.
     * @return devuelve un booleano, dependiendo si se ha actualizado o no la posición. returns a boolean, depending on whether or not the position has been updated.
     */
    public static boolean actualizarPosionesDesocupadas(List<int[]> posicionesDesocupadas, List<int[]> posicionesOcupadas) {
        int[] ultimaPosicion = posicionesOcupadas.get(posicionesOcupadas.size() - 1);
        int indice = -1;
        int i;

        i = 0;
        while (indice == -1) {
            if (ultimaPosicion[0] == posicionesDesocupadas.get(i)[0] && ultimaPosicion[1] == posicionesDesocupadas.get(i)[1]) {
                indice = i;
                System.out.println("Se ha encontrado el índice");
                posicionesDesocupadas.remove(indice);
                return true;
            } else {
                i++;
            }
        }

        return false;
    }

    /**
     * Método que actualiza la lista de posiciones desocupadas tras un movimiento.
     * Method that updates the list of unoccupied positions after a movement
     * @param posicionesDesocupadas lista con todas las posiciones que no están ocupadas. list with all positions that are not occupied
     * @param tablero matriz con la que se representa el juego. matrix with which the game is represented
     * @return devuelve un booleano, dependiendo si se ha añadido o no la posición. returns a boolean, depending on whether or not the position has been added.
     */
    public static boolean actualizarPosicionesDesocupadasTrasMovimiento(List<int[]> posicionesDesocupadas, int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                int[] nuevaPosicion = {i, j};
                if (!posicionesDesocupadas.contains(nuevaPosicion)) {
                    System.out.println("Se ha añadido una nueva posición a la lista de Posiciones Desocupadas");
                    posicionesDesocupadas.add(nuevaPosicion);
                    return true;
                }
            }
        }
        return false;
    }

}
