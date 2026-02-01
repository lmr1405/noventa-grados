/**
 * Este paquete contiene las clases responsables de controlar y gestionar
 * las reglas, la lógica y el estado del juego Noventa Grados.
 *
 * <p>Clases incluidas:</p>
 * <ul>
 *     <li>{@link Arbitro}: Clase principal encargada de gestionar el flujo del juego,
 *     los turnos, las jugadas y la validación de movimientos según las reglas del juego.</li>
 *     <li>{@link Caja}: Representa un contenedor para piezas capturadas, con un color
 *     específico y capacidad limitada.</li>
 *     <li>{@link TableroConsultor}: Clase auxiliar que proporciona métodos para consultar
 *     el estado del tablero, calcular movimientos y contar piezas.</li>
 * </ul>
 *
 * <p>Propósito:</p>
 * <p>El paquete {@code noventagrados.control} encapsula la lógica central del juego.
 * Permite validar jugadas, controlar turnos, realizar movimientos y gestionar
 * las piezas capturadas.</p>
 *
 * <p>Detalles importantes:</p>
 * <ul>
 *     <li>El {@link Arbitro} delega tareas específicas de consultas al {@link TableroConsultor}
 *     para mantener la claridad del código y separar responsabilidades.</li>
 *     <li>La clase {@link Caja} asegura que solo se almacenen piezas del color correspondiente,
 *     evitando inconsistencias.</li>
 *     <li>El diseño general del paquete sigue el principio de separación de responsabilidades,
 *     agrupando funciones relacionadas con el control del juego.</li>
 * </ul>
 *
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez Ramos</a>
 * @since 1.0
 * @version 1.0
 * @see noventagrados.modelo
 * @see noventagrados.util
 */
package noventagrados.control;
