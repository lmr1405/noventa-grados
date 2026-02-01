/**
 * Este paquete contiene las clases principales que representan los elementos
 * fundamentales del juego Noventa Grados.
 * 
 * <p>Clases incluidas:</p>
 * <ul>
 *     <li>{@link Celda}: Representa una celda en el tablero, asociada a una coordenada 
 *     y que puede contener una pieza.</li>
 *     <li>{@link Jugada}: Define una jugada en el tablero, compuesta por una celda de 
 *     origen y una de destino.</li>
 *     <li>{@link Pieza}: Representa una pieza del juego, definida por su tipo y color.</li>
 *     <li>{@link Tablero}: Modela el tablero de juego, compuesto por celdas organizadas 
 *     en una matriz bidimensional.</li>
 * </ul>
 * 
 * <p>Propósito:</p>
 * <p>El paquete {@code noventagrados.modelo} encapsula las estructuras de datos y 
 * la lógica fundamental del juego. Proporciona las representaciones de las 
 * entidades básicas del sistema, como celdas, piezas y jugadas, así como la 
 * funcionalidad asociada a la manipulación del tablero.</p>
 * 
 * <p>Detalles importantes:</p>
 * <ul>
 *     <li>El tablero tiene un tamaño fijo de 7x7 y las celdas se inicializan vacías.</li>
 *     <li>Las piezas tienen un tipo y un color que determinan sus propiedades.</li>
 *     <li>Las celdas pueden contener piezas o estar vacías, y están asociadas a 
 *     coordenadas específicas dentro del tablero.</li>
 *     <li>Las jugadas se describen mediante una celda de origen y una de destino, 
 *     permitiendo la interacción y movimiento de las piezas en el tablero.</li>
 * </ul>
 * 
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez</a>
 * @since 1.0
 * @version 1.0
 * @see noventagrados.util
 * @see noventagrados.control
 */
package noventagrados.modelo;
