package noventagrados.textui.excepcion;

/**
 * Excepción comprobable que indica que una opción no está disponible.
 * <p>
 * Esta excepción es utilizada para manejar situaciones en las que se selecciona
 * una opción que no está habilitada o permitida en el contexto actual.
 * </p>
 * 
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez Ramos</a>
 * @version 1.0
 * @since 1.0
 */
public class OpcionNoDisponibleException extends Exception {

	/**
	 * Identificador único de la clase para la serialización.
	 * <p>
	 * Este campo se utiliza para garantizar que una clase serializable sea
	 * compatible durante el proceso de serialización y deserialización. Si no
	 * coincide el valor de este campo en tiempo de deserialización, se lanzará una
	 * {@code InvalidClassException}.
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto.
	 */
	public OpcionNoDisponibleException() {
		super();
	}

	/**
	 * Constructor con mensaje personalizado.
	 * 
	 * @param mensaje El mensaje de detalle de la excepción.
	 */
	public OpcionNoDisponibleException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor con mensaje y causa.
	 * 
	 * @param mensaje El mensaje de detalle de la excepción.
	 * @param causa   La causa original de la excepción.
	 */
	public OpcionNoDisponibleException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	/**
	 * Constructor con causa.
	 * 
	 * @param causa La causa original de la excepción.
	 */
	public OpcionNoDisponibleException(Throwable causa) {
		super(causa);
	}
}
