/**
 * Este paquete contiene las implementaciones del mecanismo de deshacer jugadas
 * para el juego
 * <p>
 * Incluye las siguientes clases:
 * </p>
 * <ul>
 * <li>{@link noventagrados.control.undo.MecanismoDeDeshacer}: Interfaz que
 * define los métodos necesarios para implementar un mecanismo de deshacer.</li>
 * <li>{@link noventagrados.control.undo.MecanismoDeDeshacerAbstracto}: Clase
 * abstracta base que proporciona una estructura común para los mecanismos de
 * deshacer.</li>
 * <li>{@link noventagrados.control.undo.MaquinaDelTiempoConJugadas}:
 * Implementación del mecanismo de deshacer que almacena un historial de jugadas
 * realizadas.</li>
 * <li>{@link noventagrados.control.undo.MaquinaDelTiempoConArbitros}:
 * Implementación del mecanismo de deshacer que almacena un historial de estados
 * completos del árbitro.</li>
 * </ul>
 * 
 * <h2>Características del paquete</h2>
 * <p>
 * Este paquete proporciona mecanismos de deshacer con dos enfoques principales:
 * </p>
 * <ol>
 * <li>Almacenamiento de jugadas: Más eficiente en memoria pero requiere
 * reconstrucción del estado.</li>
 * <li>Almacenamiento de estados completos del árbitro: Más rápido para
 * consultas pero utiliza más memoria.</li>
 * </ol>
 * 
 * 
 *
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez Ramos</a>
 * @since 1.0
 * @version 1.0
 * @see noventagrados.modelo
 * @see noventagrados.util
 */
package noventagrados.control.undo;