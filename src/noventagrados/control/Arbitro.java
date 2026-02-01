package noventagrados.control;

import noventagrados.modelo.Celda;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Pieza;
import noventagrados.modelo.Tablero;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.Sentido;
import noventagrados.util.TipoPieza;

/**
 * La clase Arbitro controla el estado de la partida, incluyendo el turno actual
 * y el tablero de juego.
 * <p>
 * El arbitro maneja el cambio de turno y mantiene un registro del numero de
 * jugadas realizas.
 * 
 * @author Luis Menendez Ramos
 * @version 1.0
 * @since 04/11/2024
 */

public class Arbitro {

	/**
	 * El tablero principal del juego donde se colocan y mueven las piezas.
	 */
	private Tablero tablero;

	/**
	 * Consultor de tablero que proporciona métodos auxiliares para consultar el
	 * estado del tablero y realizar cálculos como distancias y sentidos de
	 * movimiento.
	 */
	private TableroConsultor consultor;

	/**
	 * Contador del número de jugadas realizadas en la partida.
	 */
	private int contJugadas;

	/**
	 * Indica el color del turno asignado actualmente, ya sea BLANCO o NEGRO.
	 */
	private Color turnoAsignado;

	/**
	 * Indica el color del jugador ganador de la partida. Si aún no hay ganador,
	 * permanece en null.
	 */
	private Color turnoGanador;

	/**
	 * Caja que contiene las piezas capturadas del jugador con color BLANCO.
	 */
	private Caja cajaDePiezasBlancas;

	/**
	 * Caja que contiene las piezas capturadas del jugador con color NEGRO.
	 */
	private Caja cajaDePiezasNegras;

	/**
	 * Constructor que inicializa el arbitro con un tablero y contador de jugadas a
	 * cero Inicialmente no hay turno asignado.
	 * 
	 * @param tablero El tablero del juego
	 */
	public Arbitro(Tablero tablero) {
		this.tablero = tablero;
		consultor = new TableroConsultor(tablero);
		contJugadas = 0;
		turnoAsignado = null;
		cajaDePiezasBlancas = new Caja(Color.BLANCO);
		cajaDePiezasNegras = new Caja(Color.NEGRO);
	}

	/**
	 * Cambia el turno actual al otro jugador.
	 */
	public void cambiarTurno() {
		if (turnoAsignado == Color.BLANCO) {
			turnoAsignado = Color.NEGRO;
		} else if (turnoAsignado == Color.NEGRO) {
			turnoAsignado = Color.BLANCO;
		}
	}

	/**
	 * Coloca un array de piezas en sus respectivas coordenadas en el tablero y
	 * asigna el turno actual.
	 * 
	 * @param piezas      Array de piezas a colocar
	 * @param coordenadas Array de coordenada correspondiente a las piezas
	 * @param turnoActual Color del turno actual despues de colocarlo
	 */
	public void colocarPiezas(Pieza[] piezas, Coordenada[] coordenadas, Color turnoActual) {
		// comprobar que los array tienen el mismo tamaño
		if (piezas.length == coordenadas.length) {

			// recorremos las 2 matrices al mismo tiempo
			for (int i = 0; i < piezas.length; i++) {
				Pieza pieza = piezas[i];
				Coordenada coordenada = coordenadas[i];

				tablero.colocar(pieza, coordenada);
			}
		}
		turnoAsignado = turnoActual;
	}

	/**
	 * Coloca las piezas en su configuracion inicial, con reinas y peones en sus
	 * posiciones iniciales.
	 */
	public void colocarPiezasConfiguracionInicial() {
		turnoGanador = null; // aseguramos que no haya un ganador al iniciar la partida
		// colocamos las reinas y los peones en sus coordenadas correspondiente
		Pieza reinaBlanca = new Pieza(TipoPieza.REINA, Color.BLANCO);
		tablero.colocar(reinaBlanca, new Coordenada(0, 0));

		Pieza reinaNegra = new Pieza(TipoPieza.REINA, Color.NEGRO);
		tablero.colocar(reinaNegra, new Coordenada(6, 6));

		Coordenada[] posicionesPeonesBlancos = { new Coordenada(1, 0), new Coordenada(2, 0), new Coordenada(3, 0),
				new Coordenada(0, 1), new Coordenada(0, 2), new Coordenada(0, 3) };
		//coloca cada peon en el tablero en su posicion correspondiente
		for (Coordenada coordenada : posicionesPeonesBlancos) {
			Pieza peonesBlancos = new Pieza(TipoPieza.PEON, Color.BLANCO);
			tablero.colocar(peonesBlancos, coordenada);
		}

		Coordenada[] posicionesPeonesNegros = { new Coordenada(3, 6), new Coordenada(4, 6), new Coordenada(5, 6),
				new Coordenada(6, 3), new Coordenada(6, 4), new Coordenada(6, 5) };
		//coloca cada peon en el tablero en su posicion correspondiente
		for (Coordenada coordenada : posicionesPeonesNegros) {
			Pieza peonesNegros = new Pieza(TipoPieza.PEON, Color.NEGRO);
			tablero.colocar(peonesNegros, coordenada);
		}

		turnoAsignado = Color.BLANCO; //inicia la partida las piezas de color blanco
	}

	/**
	 * Consulta la caja de piezas eliminadas para un color especifico.
	 * 
	 * @param color El color de las piezas en la caja consultada.
	 * @return La caja de piezas correspondientes al color.
	 */
	public Caja consultarCaja(Color color) {
		//si color es blanco, retorna la caja blanca sino la caja negra 
		return (color == Color.BLANCO) ? cajaDePiezasBlancas : cajaDePiezasNegras;
	}

	/**
	 * Consulta el número de jugadas realizadas.
	 * 
	 * @return Número de jugadas.
	 */
	public int consultarNumeroJugada() {
		return contJugadas;
	}

	/**
	 * Consulta el estado actual del tablero, devolviendo una copia.
	 * 
	 * @return Una copia del tablero actual.
	 */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}

	/**
	 * Consulta el turno actual.
	 * 
	 * @return El color del turno actual.
	 */
	public Color consultarTurno() {
		return turnoAsignado;
	}

	/**
	 * Consulta el turno del ganador, si existe uno.
	 * 
	 * @return El color del jugador ganador o null si no hay ganador.
	 */
	public Color consultarTurnoGanador() {
		if (!consultor.hayReina(Color.BLANCO) && !consultor.hayReina(Color.NEGRO)) {
			turnoGanador = null; // No hay ganador en caso de empate
			return null;
		}

		//comprobamos si las blancas ganan
		if (consultor.estaReinaEnElCentro(Color.BLANCO) || !consultor.hayReina(Color.NEGRO)) {
			turnoGanador = Color.BLANCO;
		}

		//comprobamos si las negras ganan
		else if (consultor.estaReinaEnElCentro(Color.NEGRO) || !consultor.hayReina(Color.BLANCO)) {
			turnoGanador = Color.NEGRO;
		}

		return turnoGanador;
	}


	/**
	 * Verifica si una jugada es legal según las reglas del juego.
	 * 
	 * @param jugada La jugada a verificar.
	 * @return true si la jugada es legal, false en caso contrario.
	 */
	public boolean esMovimientoLegal(Jugada jugada) {
		Coordenada origen = jugada.origen().consultarCoordenada();
		Coordenada destino = jugada.destino().consultarCoordenada();

		if (!sonCoordenadasValidas(origen, destino)) {
			return false;
		}


		// verificamos las condiciones necesarias para que sea legal la jugada
		return esMovimiento90GradosValido(origen, destino) && esPiezaParaMoverValida(origen)
				&& esSentidoValido(origen, destino);
	}

	

	/**
	 * Verifica si las coordenadas de origen y destino son válidas dentro del
	 * tablero.
	 * 
	 * @param origen  Coordenada de la celda de origen.
	 * @param destino Coordenada de la celda de destino.
	 * @return true si ambas coordenadas están dentro del tablero, false en caso
	 *         contrario.
	 */
	private boolean sonCoordenadasValidas(Coordenada origen, Coordenada destino) {
		return tablero.estaEnTablero(origen) && tablero.estaEnTablero(destino);
	}

	/**
	 * Verifica si el movimiento cumple con las reglas de un movimiento de 90
	 * grados.
	 * 
	 * @param origen  Coordenada de la celda de origen.
	 * @param destino Coordenada de la celda de destino.
	 * @return true si el movimiento es válido, false en caso contrario.
	 */
	private boolean esMovimiento90GradosValido(Coordenada origen, Coordenada destino) {
		// calculamos la distancia horizontal y vertical
		int distanciaHorizontal = consultor.consultarDistanciaEnHorizontal(origen, destino);
		int distanciaVertical = consultor.consultarDistanciaEnVertical(origen, destino);

		// consultamos el numero de piezas que tenemos en horizontal y vertical
		int piezasEnHorizontal = consultor.consultarNumeroPiezasEnHorizontal(origen);
		int piezasEnVertical = consultor.consultarNumeroPiezasEnVertical(origen);
		// Si coincide la distancia con la otra el movimiento es valido
		return piezasEnVertical == distanciaHorizontal || piezasEnHorizontal == distanciaVertical;
	}

	/**
	 * Verifica si la pieza en la celda de origen es válida para realizar el
	 * movimiento.
	 * 
	 * @param origen Coordenada de la celda de origen.
	 * @return true si hay una pieza válida en origen, false en caso contrario.
	 */
	private boolean esPiezaParaMoverValida(Coordenada origen) {
		// Consulta la celda real del tablero usando la coordenada
		Celda celdaReal = tablero.consultarCelda(origen); // Devuelve el clon profundo
		Pieza pieza = (celdaReal != null) ? celdaReal.consultarPieza() : null; // Obtén la pieza
		// Valida la pieza
		if (pieza == null || pieza.consultarColor() != turnoAsignado) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica si el movimiento tiene un sentido válido según las reglas del juego.
	 * 
	 * @param origen  Coordenada de la celda de origen.
	 * @param destino Coordenada de la celda de destino.
	 * @return true si el movimiento tiene un sentido válido, false en caso
	 *         contrario.
	 */
	private boolean esSentidoValido(Coordenada origen, Coordenada destino) {
		Sentido sentido = consultor.calcularSentido(origen, destino);
		if (sentido == null) {
			System.out.println("Movimiento ilegal: El sentido no es válido.");
			return false;
		}
		return true;
	}

	

	/**
	 * Realiza una jugada de empuje si es legal, moviendo la pieza y actualizando el
	 * tablero y el contador de jugadas.
	 * 
	 * @param jugada La jugada a realizar.
	 */
	public void empujar(Jugada jugada) {
	
		   
			Coordenada origen = jugada.origen().consultarCoordenada();
			Coordenada destino = jugada.destino().consultarCoordenada();
			Sentido sentido = consultor.calcularSentido(origen, destino);

			realizarEmpuje(origen, destino, sentido);

			//recuperamos la pieza, la eliminamos y la colocamos en el destino y aumentamos el contador
			Pieza piezaMovida = jugada.origen().consultarPieza();
			jugada.origen().eliminarPieza();
			tablero.colocar(piezaMovida, destino);
			contJugadas++;
		
		   
	}

	/**
	 * Realiza el proceso de empuje de una pieza en la dirección indicada.
	 * 
	 * @param actual  Coordenada actual de la pieza.
	 * @param destino Coordenada destino de la pieza.
	 * @param sentido Dirección en la que se realiza el empuje.
	 */
	private void realizarEmpuje(Coordenada actual, Coordenada destino, Sentido sentido) {
	    Coordenada siguiente = calcularSiguienteCoordenada(actual, sentido);

	    // Mientras la celda siguiente esté dentro del tablero y no sea la celda de destino
	    while (tablero.estaEnTablero(siguiente) && !siguiente.equals(destino)) {
	        moverPieza(actual, siguiente, sentido); // Mueve la pieza y maneja posibles empujes recursivos
	        actual = siguiente;
	        siguiente = calcularSiguienteCoordenada(actual, sentido);
	    }

	    // Manejar la pieza en la celda de destino si está ocupada
	    Pieza piezaEnDestino = tablero.consultarCelda(destino).consultarPieza();
	    if (piezaEnDestino != null) {
	        Coordenada fueraTablero = calcularSiguienteCoordenada(destino, sentido);

	        // Si la pieza debe salir del tablero
	        if (!tablero.estaEnTablero(fueraTablero)) {
	            Caja cajaCorrespondiente = (piezaEnDestino.consultarColor() == Color.BLANCO)
	                    ? cajaDePiezasBlancas : cajaDePiezasNegras;
	            cajaCorrespondiente.añadir(piezaEnDestino);
	            tablero.eliminarPieza(destino);
	        } else {
	            realizarEmpuje(destino, fueraTablero, sentido); // Realiza el empuje recursivo
	        }
	    }

	    // Finalmente mueve la pieza actual al destino
	    moverPieza(actual, destino, null); // No se necesita `sentido aquí porque es el movimiento final
	}

	/**
	 * Calcula la siguiente coordenada en la dirección indicada por el sentido.
	 * 
	 * <p>Este método toma una coordenada actual y un sentido de desplazamiento,
	 * y devuelve la nueva coordenada resultante de aplicar el desplazamiento
	 * especificado.</p>
	 *
	 * @param actual  La coordenada actual desde donde se calcula el desplazamiento.
	 * @param sentido El sentido del desplazamiento, que indica la dirección (horizontal, vertical, etc.).
	 * @return Una nueva instancia de {@code Coordenada} que representa la posición calculada.
	 */
	private Coordenada calcularSiguienteCoordenada(Coordenada actual, Sentido sentido) {
	    return new Coordenada(
	        actual.fila() + sentido.consultarDesplazamientoEnFilas(),
	        actual.columna() + sentido.consultarDesplazamientoEnColumnas()
	    );
	}
	
	/**
	 * Mueve una pieza desde una celda de origen a una celda de destino, manejando
	 * posibles empujes de piezas en la dirección indicada.
	 * 
	 * <p>Este método realiza los siguientes pasos:</p>
	 * <ul>
	 *     <li>Obtiene la pieza en la celda de origen.</li>
	 *     <li>Si el sentido es válido (no nulo), comprueba si hay una pieza en la celda de destino.</li>
	 *     <li>Si hay una pieza en la celda de destino, calcula la nueva coordenada
	 *         hacia donde debe empujarse y llama a {@link #realizarEmpuje} de forma recursiva.</li>
	 *     <li>Finalmente, mueve la pieza de la celda de origen a la celda de destino,
	 *         actualizando el tablero en consecuencia.</li>
	 * </ul>
	 * 
	 * @param origen  La coordenada de la celda de origen desde donde se mueve la pieza.
	 * @param destino La coordenada de la celda de destino hacia donde se mueve la pieza.
	 * @param sentido El sentido en el que se realiza el movimiento. Si es {@code null},
	 *                se asume que es el movimiento final sin necesidad de empujar piezas adicionales.
	 */
	private void moverPieza(Coordenada origen, Coordenada destino, Sentido sentido) {
	    Pieza piezaEnActual = tablero.consultarCelda(origen).consultarPieza();

	    if (sentido != null) {
	        Pieza piezaEnSiguiente = tablero.consultarCelda(destino).consultarPieza();

	        // Si hay una pieza en la siguiente celda, moverla recursivamente
	        if (piezaEnSiguiente != null) {
	            Coordenada nuevaCoordenada = calcularSiguienteCoordenada(destino, sentido);
	            realizarEmpuje(destino, nuevaCoordenada, sentido);
	        }
	    }

	    tablero.colocar(piezaEnActual, destino);
	    tablero.eliminarPieza(origen);
	}



	/**
	 * Verifica si la partida está finalizada, según las reglas del juego.
	 * 
	 * @return true si la partida está finalizada, false en caso contrario.
	 */
	public boolean estaFinalizadaPartida() {
		if (consultarTurnoGanador() != null) {
			return true;
		}
		int piezasBlancas = contarPieza(Color.BLANCO);
		int piezasNegras = contarPieza(Color.NEGRO);

		return piezasBlancas == 0 || piezasNegras == 0 || !consultor.hayReina(Color.BLANCO)
				|| !consultor.hayReina(Color.NEGRO);
	}
	

	/**
	 * Cuenta el número de piezas de un color específico en el tablero.
	 * 
	 * @param color El color de las piezas que se desean contar (BLANCO o NEGRO).
	 * @return El número de piezas en el tablero que coinciden con el color
	 *         especificado.
	 */
	private int contarPieza(Color color) {
		int cont = 0;
		for (int i = 0; i < tablero.consultarNumeroFilas(); i++) {
			for (int j = 0; j < tablero.consultarNumeroColumnas(); j++) {
				Celda celda = tablero.consultarCelda(new Coordenada(i, j));
				Pieza pieza = celda.consultarPieza();
				if (pieza != null && pieza.consultarColor() == color) {
					cont++;
				}
			}
		}
		return cont;
	}

	@Override
	public String toString() {
		return "Arbitro [tablero=" + tablero + ", contJugadas=" + contJugadas + ", turnoAsignado=" + turnoAsignado
				+ ", turnoGanador=" + turnoGanador + ", cajaDePiezasBlancas=" + cajaDePiezasBlancas
				+ ", cajaDePiezasNegras=" + cajaDePiezasNegras + "]";
	}
	
	

}