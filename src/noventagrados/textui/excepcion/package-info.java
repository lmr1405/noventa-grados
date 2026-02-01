/**
 * Este paquete contiene las excepciones utilizadas en la interfaz de usuario en modo texto del juego.
 * <p>
 * Las clases de excepción proporcionan una manera estructurada de manejar errores específicos
 * que pueden ocurrir durante la ejecución de la interfaz en modo texto. Estas excepciones son 
 * comprobables y están diseñadas para asegurar una respuesta clara y amigable ante errores del usuario.
 * </p>
 *
 * <h2>Clases principales:</h2>
 * <ul>
 *     <li>{@link noventagrados.textui.excepcion.OpcionNoDisponibleException}:
 *     Excepción que se lanza cuando se selecciona una opción inválida o no disponible.</li>
 * </ul>
 * 
 * <h2>Características del paquete:</h2>
 * <p>
 * Este paquete está diseñado para ser utilizado exclusivamente por el paquete {@code textui}.
 * Ayuda a garantizar que las entradas del usuario se validen adecuadamente y que cualquier 
 * error pueda manejarse de manera controlada.
 * </p>
 *
 * <h2>Uso típico:</h2>
 * <p>
 * Ejemplo de manejo de la excepción {@code OpcionNoDisponibleException}:
 * </p>
 * <pre>
 * try {
 *     extraerModoDeshacer(args);
 * } catch (OpcionNoDisponibleException ex) {
 *     System.err.println("Error: " + ex.getMessage());
 * }
 * </pre>
 *
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez Ramos</a>
 * @version 1.0
 * @since 1.0
 */
package noventagrados.textui.excepcion;
