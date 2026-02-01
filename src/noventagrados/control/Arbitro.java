package noventagrados.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 2.0
 * @since 1.0
 */
public class Arbitro {

	/**
	 * El tablero principal del juego donde se colocan y mueven las piezas.
	 */
	private Tablero tablero;

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
	 * Crea una copia exacta del objeto actual de tipo {@code Arbitro}.
	 * <p>
	 * Este método genera un nuevo objeto {@code Arbitro} que contiene el mismo
	 * estado que el objeto original, incluyendo el tablero, el número de jugadas,
	 * el turno asignado, el ganador actual (si lo hay) y las cajas de piezas
	 * capturadas.
	 * </p>
	 * <p>
	 * La clonación incluye:
	 * </p>
	 * <ul>
	 * <li>Una copia del tablero a través de su método {@code clonar}.</li>
	 * <li>El número de jugadas realizadas.</li>
	 * <li>El turno actualmente asignado.</li>
	 * <li>El color del ganador (si existe).</li>
	 * <li>Las cajas de piezas capturadas de ambos colores.</li>
	 * </ul>
	 * 
	 * @return Un nuevo objeto {@code Arbitro} que es una copia exacta del objeto
	 *         original.
	 */
	public Arbitro clonar() {
		Arbitro clon = new Arbitro(tablero.clonar());
		clon.contJugadas = this.contJugadas;
		clon.turnoAsignado = this.turnoAsignado;
		clon.turnoGanador = this.turnoGanador;
		clon.cajaDePiezasBlancas = this.cajaDePiezasBlancas.clonar();
		clon.cajaDePiezasNegras = this.cajaDePiezasNegras.clonar();

		return clon;
	}

	/**
	 * Coloca una lista de piezas en sus respectivas coordenadas en el tablero y
	 * asigna el turno actual.
	 * 
	 * @param piezas      Array de piezas a colocar
	 * @param coordenadas Array de coordenada correspondiente a las piezas
	 * @param turnoActual Color del turno actual despues de colocarlo
	 */
	public void colocarPiezas(List<Pieza> piezas, List<Coordenada> coordenadas, Color turnoActual) {
		if (piezas.size() == coordenadas.size()) {

			for (int i = 0; i < piezas.size(); i++) {
				Pieza pieza = piezas.get(i);
				Coordenada coordenada = coordenadas.get(i);

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

		turnoGanador = null;

		Pieza reinaBlanca = new Pieza(TipoPieza.REINA, Color.BLANCO);
		tablero.colocar(reinaBlanca, new Coordenada(0, 0));

		Pieza reinaNegra = new Pieza(TipoPieza.REINA, Color.NEGRO);
		tablero.colocar(reinaNegra, new Coordenada(6, 6));

		ArrayList<Coordenada> posicionesPeonesBlancos = new ArrayList<>();

		posicionesPeonesBlancos.add(new Coordenada(1, 0));
		posicionesPeonesBlancos.add(new Coordenada(2, 0));
		posicionesPeonesBlancos.add(new Coordenada(3, 0));
		posicionesPeonesBlancos.add(new Coordenada(0, 1));
		posicionesPeonesBlancos.add(new Coordenada(0, 2));
		posicionesPeonesBlancos.add(new Coordenada(0, 3));

		for (Coordenada coordenada : posicionesPeonesBlancos) {
			Pieza peonesBlancos = new Pieza(TipoPieza.PEON, Color.BLANCO);
			tablero.colocar(peonesBlancos, coordenada);
		}

		ArrayList<Coordenada> posicionesPeonesNegros = new ArrayList<>();

		posicionesPeonesNegros.add(new Coordenada(3, 6));
		posicionesPeonesNegros.add(new Coordenada(4, 6));
		posicionesPeonesNegros.add(new Coordenada(5, 6));
		posicionesPeonesNegros.add(new Coordenada(6, 3));
		posicionesPeonesNegros.add(new Coordenada(6, 4));
		posicionesPeonesNegros.add(new Coordenada(6, 5));

		for (Coordenada coordenada : posicionesPeonesNegros) {
			Pieza peonesNegros = new Pieza(TipoPieza.PEON, Color.NEGRO);
			tablero.colocar(peonesNegros, coordenada);
		}

		turnoAsignado = Color.BLANCO;
	}

	/**
	 * Consulta la caja de piezas eliminadas para un color especifico.
	 * 
	 * @param color El color de las piezas en la caja consultada.
	 * @return La caja de piezas correspondientes al color.
	 */
	public Caja consultarCaja(Color color) {
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
		TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero.clonar());
		if (!consultor.hayReina(Color.BLANCO) && !consultor.hayReina(Color.NEGRO)) {
			turnoGanador = null; // No hay ganador en caso de empate
			return null;
		}

		if (consultor.estaReinaEnElCentro(Color.BLANCO) || !consultor.hayReina(Color.NEGRO)) {
			turnoGanador = Color.BLANCO;
		}

		else if (consultor.estaReinaEnElCentro(Color.NEGRO) || !consultor.hayReina(Color.BLANCO)) {
			turnoGanador = Color.NEGRO;
		}

		return turnoGanador;
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

	/**
	 * Verifica si una jugada es legal según las reglas del juego.
	 * 
	 * @param jugada La jugada a verificar.
	 * @return true si la jugada es legal, false en caso contrario.
	 */
	public boolean esMovimientoLegal(Jugada jugada) {
		TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero.clonar());
		boolean legal = false;

		Coordenada origen = jugada.origen().consultarCoordenada();
		Coordenada destino = jugada.destino().consultarCoordenada();

		if (tablero.estaEnTablero(origen) && tablero.estaEnTablero(destino)) {
			legal = true;
		}

		if (legal) {
			Pieza pieza = jugada.origen().consultarPieza();
			if (pieza == null || pieza.consultarColor() != turnoAsignado) {
				legal = false;
			}
		}

		if (legal) {
			Sentido sentido = consultor.calcularSentido(origen, destino);
			if (sentido == null) {
				legal = false;
			}
		}

		if (legal) {
			int distanciaHorizontal = consultor.consultarDistanciaEnHorizontal(origen, destino);
			int distanciaVertical = consultor.consultarDistanciaEnVertical(origen, destino);

			int piezasEnHorizontal = consultor.consultarNumeroPiezasEnHorizontal(origen);
			int piezasEnVertical = consultor.consultarNumeroPiezasEnVertical(origen);

			if (distanciaHorizontal == piezasEnVertical || distanciaVertical == piezasEnHorizontal) {
				legal = true;
			} else {
				legal = false;
			}
		}

		return legal;

	}

	/**
	 * Realiza una jugada de empuje si es legal, moviendo la pieza y actualizando el
	 * tablero y el contador de jugadas.
	 * 
	 * @param jugada La jugada a realizar.
	 */
	public void empujar(Jugada jugada) {

		TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero.clonar());
		Coordenada origen = jugada.origen().consultarCoordenada();
		Coordenada destino = jugada.destino().consultarCoordenada();
		Sentido sentido = consultor.calcularSentido(origen, destino);

		realizarEmpuje(origen, destino, sentido);

		Pieza piezaMovida = jugada.origen().consultarPieza();

		tablero.colocar(piezaMovida, destino);
		jugada.origen().eliminarPieza();

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

		while (tablero.estaEnTablero(siguiente) && !siguiente.equals(destino)) {
			moverPieza(actual, siguiente, sentido);
			actual = siguiente;
			siguiente = calcularSiguienteCoordenada(actual, sentido);
		}

		Pieza piezaEnDestino = tablero.consultarCelda(destino).consultarPieza();
		if (piezaEnDestino != null) {
			Coordenada fueraTablero = calcularSiguienteCoordenada(destino, sentido);

			if (!tablero.estaEnTablero(fueraTablero)) {
				Caja cajaCorrespondiente = (piezaEnDestino.consultarColor() == Color.BLANCO) ? cajaDePiezasBlancas
						: cajaDePiezasNegras;
				cajaCorrespondiente.añadir(piezaEnDestino);
				tablero.eliminarPieza(destino);
			} else {
				realizarEmpuje(destino, fueraTablero, sentido);
			}
		}

		moverPieza(actual, destino, null);
	}

	/**
	 * Calcula la siguiente coordenada en la dirección indicada por el sentido.
	 * 
	 * <p>
	 * Este método toma una coordenada actual y un sentido de desplazamiento, y
	 * devuelve la nueva coordenada resultante de aplicar el desplazamiento
	 * especificado.
	 * </p>
	 *
	 * @param actual  La coordenada actual desde donde se calcula el desplazamiento.
	 * @param sentido El sentido del desplazamiento, que indica la dirección
	 *                (horizontal, vertical, etc.).
	 * @return Una nueva instancia de {@code Coordenada} que representa la posición
	 *         calculada.
	 */
	private Coordenada calcularSiguienteCoordenada(Coordenada actual, Sentido sentido) {
		return new Coordenada(actual.fila() + sentido.consultarDesplazamientoEnFilas(),
				actual.columna() + sentido.consultarDesplazamientoEnColumnas());
	}

	/**
	 * Mueve una pieza desde una celda de origen a una celda de destino, manejando
	 * posibles empujes de piezas en la dirección indicada.
	 * 
	 * <p>
	 * Este método realiza los siguientes pasos:
	 * </p>
	 * <ul>
	 * <li>Obtiene la pieza en la celda de origen.</li>
	 * <li>Si el sentido es válido (no nulo), comprueba si hay una pieza en la celda
	 * de destino.</li>
	 * <li>Si hay una pieza en la celda de destino, calcula la nueva coordenada
	 * hacia donde debe empujarse y llama a {@link #realizarEmpuje} de forma
	 * recursiva.</li>
	 * <li>Finalmente, mueve la pieza de la celda de origen a la celda de destino,
	 * actualizando el tablero en consecuencia.</li>
	 * </ul>
	 * 
	 * @param origen  La coordenada de la celda de origen desde donde se mueve la
	 *                pieza.
	 * @param destino La coordenada de la celda de destino hacia donde se mueve la
	 *                pieza.
	 * @param sentido El sentido en el que se realiza el movimiento. Si es
	 *                {@code null}, se asume que es el movimiento final sin
	 *                necesidad de empujar piezas adicionales.
	 */
	private void moverPieza(Coordenada origen, Coordenada destino, Sentido sentido) {
		Pieza piezaEnActual = tablero.consultarCelda(origen).consultarPieza();

		if (sentido != null) {
			Pieza piezaEnSiguiente = tablero.consultarCelda(destino).consultarPieza();
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
		TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero.clonar());
		if (consultarTurnoGanador() != null) {
			return true;
		}
		int piezasBlancas = contarPieza(Color.BLANCO);
		int piezasNegras = contarPieza(Color.NEGRO);

		return piezasBlancas == 0 || piezasNegras == 0 || !consultor.hayReina(Color.BLANCO)
				|| !consultor.hayReina(Color.NEGRO);
	}

	@Override
	public String toString() {
		return "Arbitro [tablero=" + tablero + ", contJugadas=" + contJugadas + ", turnoAsignado=" + turnoAsignado
				+ ", turnoGanador=" + turnoGanador + ", cajaDePiezasBlancas=" + cajaDePiezasBlancas
				+ ", cajaDePiezasNegras=" + cajaDePiezasNegras + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cajaDePiezasBlancas, cajaDePiezasNegras, contJugadas, tablero, turnoAsignado, turnoGanador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitro other = (Arbitro) obj;
		return Objects.equals(cajaDePiezasBlancas, other.cajaDePiezasBlancas)
				&& Objects.equals(cajaDePiezasNegras, other.cajaDePiezasNegras) && contJugadas == other.contJugadas
				&& Objects.equals(tablero, other.tablero) && turnoAsignado == other.turnoAsignado
				&& turnoGanador == other.turnoGanador;
	}

}
