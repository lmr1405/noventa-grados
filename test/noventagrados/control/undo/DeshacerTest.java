package noventagrados.control.undo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.opentest4j.MultipleFailuresError;

import noventagrados.control.Arbitro;
import noventagrados.control.TableroConsultor;
import noventagrados.modelo.Celda;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Pieza;
import noventagrados.modelo.Tablero;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.TipoPieza;

/**
 * Tests unitarios sobre el mecanismo de deshacer.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 1.0
 * @version 2.0
 */
@Timeout(value = 3, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD) // Time out global para todos los tests salvo los de ciclo de vida
public abstract class DeshacerTest {

	/**
	 * Mecanismo a testar.
	 */
	MecanismoDeDeshacer deshacer;
		
	/**
	 * Obtiene un mecanismo de deshacer con fecha personalizada.
	 * 
	 * @param date fecha de inicialización
	 * @return mecanismo de deshacer
	 */
	abstract MecanismoDeDeshacer obtenerDeshacerConFechaPersonalizada(Date date);
	
	/**
	 * Comprueba correcta inicialización con fecha.
	 */
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Comprobar que inicializa correctamente con la fecha.")
	void inicializacionConFecha() {
		MecanismoDeDeshacer deshacerLocal = obtenerDeshacerConFechaPersonalizada(new Date(0,1,2,3,4,5));
		assertThat(deshacerLocal.obtenerFechaInicio(), is(new Date(0,1,2,3,4,5)));
	}

	/**
	 * Comprueba si deshace bien si no hay jugada previa.
	 */
	@Test
	@DisplayName("Comprobar que deshace correctamente si no ha habido ninguna jugada previa.")
	void probarDeshacerSinHaberGrabadoNingunaJugada() {
		Arbitro partidaInicial = generarPartidaInicial();
		deshacer.deshacerJugada();
		Arbitro partida = deshacer.consultarArbitroActual();		
		assertThat("Si no hay jugadas grabadas debería devolver la partida inicial.", partida, is(partidaInicial));
	}
	
	/**
	 * Comprueba que gestiona bien el contador de jugadas a deshacer si no había jugada previa.
	 */
	@Test
	@DisplayName("Comprobar que gestiona correctamente el contador si no había ninguna jugada previa al deshacer.")
	void probarGestionDeContadorSinHaberGrabadoNingunaJugada() {
		assertThat("No debe haber jugadas a deshacer al iniciar.", deshacer.consultarNumeroJugadasEnHistorico(),is(0));
		deshacer.deshacerJugada();
		assertThat("No deber haber jugadas a deshacer, pese a que se haya invocado una vez sin jugada a deshacer.", deshacer.consultarNumeroJugadasEnHistorico(),is(0));
	}

	/**
	 * Comprueba si deshace bien si solo hay una jugada previa.
	 * 
	 */
	@Test
	@DisplayName("Comprobar si deshace correctamente si solo había una jugada previa.")
	void probarDeshacerGrabandoUnaJugada() {
		Jugada jugada = new Jugada(new Celda(new Coordenada(0,3)), new Celda(new Coordenada(0,5)));
		deshacer.hacerJugada(jugada);
		deshacer.deshacerJugada();
		Arbitro arbitro = deshacer.consultarArbitroActual();	
		Tablero tablero = arbitro.consultarTablero();
		assertAll("comprobar que deshacer con solo una jugada grabada devuelve una partida en estado inicial",
				() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(0)),
						() -> assertThat("La reina debería estar nuevamente en la esquina.",
						tablero.consultarCelda(new Coordenada(0,0)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.BLANCO))),
				() -> assertThat("El turno es incorrecto.", arbitro.consultarTurno(), is(Color.BLANCO)),
				() -> assertThat("El contador de jugadas es incorrecto.", arbitro.consultarNumeroJugada(), is(0)),
				() -> assertThat("No debería haber ganador.", arbitro.consultarTurnoGanador(), is(nullValue())),
				() -> assertThat("La partida no debería estar acabada.", arbitro.estaFinalizadaPartida(), is(false))

		);
	}
	
	/**
	 * Comprueba si deshace bien si solo hay una jugada previa expulsando a dos peones.
	 * 
	 */
	@Test
	@DisplayName("Comprobar si deshace correctamente si solo había una jugada previa expulsando dos peones.")
	void probarDeshacerGrabandoUnaJugadaExpulsandoDosPeones() {
		Jugada jugada = new Jugada(new Celda(new Coordenada(0,0)), new Celda(new Coordenada(0,4)));
		deshacer.hacerJugada(jugada);
		final Arbitro arbitro = deshacer.consultarArbitroActual();	
		final Tablero tablero = arbitro.consultarTablero();
		final TableroConsultor<Tablero> tc = new TableroConsultor<Tablero>(tablero);
		assertAll("comprobar que se ha movido la reina y se ha expulsado un peón",
				() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(1)),
				() -> assertThat("La reina debería estar en posición (0, 4).",
						tablero.consultarCelda(new Coordenada(0,4)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.BLANCO))),
				() -> assertThat("El turno es incorrecto.", arbitro.consultarTurno(), is(Color.NEGRO)),
				() -> assertThat("El contador de jugadas es incorrecto.", arbitro.consultarNumeroJugada(), is(1)),
				() -> assertThat("No debería haber ganador.", arbitro.consultarTurnoGanador(), is(nullValue())),
				() -> assertThat("Número de piezas en caja blanca incorrecto.", arbitro.consultarCaja(Color.BLANCO).contarPiezas(),is(1)),
				() -> assertThat("Número de peones blancos en tablero incorrecto.", tc.consultarNumeroPiezas(TipoPieza.PEON, Color.BLANCO), is(5)),
				() -> assertThat("Número de peones negros en tablero incorrecto.", tc.consultarNumeroPiezas(TipoPieza.PEON, Color.NEGRO), is(6)),
				() -> assertThat("La partida no debería estar acabada.", arbitro.estaFinalizadaPartida(), is(false))

		);
		
		deshacer.deshacerJugada(); // Deshacemos la jugada y volvemos al estado inicial...
		
		comprobarEstadoInicial();
	}
	
	
	/**
	 * Comprueba si deshace bien si hay dos jugadas previa expulsando a peones
	 * de ambos colores y se deshace solo una jugada.
	 * 
	 */
	@Test
	@DisplayName("Comprobar si deshace correctamente una jugada tras dos jugadas expulsando peones.")
	void probarDeshacerGrabandoDosJugadasExpulsandoDosPeonesDeshaciendoSoloUnaJugada() {
		Jugada jugada = new Jugada(new Celda(new Coordenada(0,0)), new Celda(new Coordenada(0,4)));
		deshacer.hacerJugada(jugada);
		jugada = new Jugada(new Celda(new Coordenada(6,6)), new Celda(new Coordenada(6,1)));
		deshacer.hacerJugada(jugada);
		// las dos reinas movidas en horizontal...
		final Arbitro arbitro = deshacer.consultarArbitroActual();	
		final Tablero tablero = arbitro.consultarTablero();
		final TableroConsultor<Tablero> tc = new TableroConsultor<Tablero>(tablero);
		assertAll("comprobar que se han movido las dos reinas y se han expulsado un peón blanco y dos negras",
				() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(2)),
				() -> assertThat("La reina blanca debería estar en posición (0, 4).",
						tablero.consultarCelda(new Coordenada(0,4)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.BLANCO))),
				() -> assertThat("La reina negra debería estar en posición (6, 1).",
						tablero.consultarCelda(new Coordenada(6,1)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.NEGRO))),
				() -> assertThat("El turno es incorrecto.", arbitro.consultarTurno(), is(Color.BLANCO)),
				() -> assertThat("El contador de jugadas es incorrecto.", arbitro.consultarNumeroJugada(), is(2)),
				() -> assertThat("No debería haber ganador.", arbitro.consultarTurnoGanador(), is(nullValue())),
				() -> assertThat("Número de piezas en caja blanca incorrecto.", arbitro.consultarCaja(Color.BLANCO).contarPiezas(),is(1)),
				() -> assertThat("Número de piezas en caja negra incorrecto.", arbitro.consultarCaja(Color.NEGRO).contarPiezas(),is(2)),

				() -> assertThat("Número de peones blancos en tablero incorrecto.", tc.consultarNumeroPiezas(TipoPieza.PEON, Color.BLANCO), is(5)),
				() -> assertThat("Número de peones negros en tablero incorrecto.", tc.consultarNumeroPiezas(TipoPieza.PEON, Color.NEGRO), is(4)),
				() -> assertThat("La partida no debería estar acabada.", arbitro.estaFinalizadaPartida(), is(false))

		);
		
		deshacer.deshacerJugada(); // Deshacemos la jugada y volvemos al estado previo con solo una jugada
		
		
		final Arbitro arbitro2 = deshacer.consultarArbitroActual();	
		final Tablero tablero2 = arbitro2.consultarTablero();
		final TableroConsultor<Tablero> tc2 = new TableroConsultor<Tablero>(tablero2);

		assertAll("comprobar que deshacer con una jugada grabada devuelve una partida con una sola jugada hecha",
				() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(1)),
				() -> assertThat("La reina blanca debería estar nuevamente en la esquina.",
						tablero2.consultarCelda(new Coordenada(0,4)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.BLANCO))),
				() -> assertThat("La reina negra debería estar nuevamente en la esquina.",
						tablero2.consultarCelda(new Coordenada(6,6)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.NEGRO))),
				() -> assertThat("El turno es incorrecto.", arbitro2.consultarTurno(), is(Color.NEGRO)),
				() -> assertThat("El contador de jugadas es incorrecto.", arbitro2.consultarNumeroJugada(), is(1)),
				() -> assertThat("No debería haber ganador.", arbitro2.consultarTurnoGanador(), is(nullValue())),
				() -> assertThat("Número de piezas en caja blanca incorrecto.", arbitro2.consultarCaja(Color.BLANCO).contarPiezas(),is(1)),
				() -> assertThat("Número de piezas en caja negra incorrecto.", arbitro2.consultarCaja(Color.NEGRO).contarPiezas(),is(0)),
				() -> assertThat("Número de peones blancos en tablero incorrecto.", tc2.consultarNumeroPiezas(TipoPieza.PEON, Color.BLANCO), is(5)),
				() -> assertThat("Número de peones negros en tablero incorrecto.", tc2.consultarNumeroPiezas(TipoPieza.PEON, Color.NEGRO), is(6)),
				() -> assertThat("La partida no debería estar acabada.", arbitro2.estaFinalizadaPartida(), is(false))

		);
	}
	
	/**
	 * Comprueba si deshace tres jugadas volviendo al estado inicial.
	 * 
	 */
	@Test
	@DisplayName("Comprobar si deshace correctamente tres jugadas volviendo al estado inicial.")
	void probarDeshacerGrabandoTresJugadasVolviendoAlEstadoInicial() {
		Jugada jugada = new Jugada(new Celda(new Coordenada(0,0)), new Celda(new Coordenada(0,4)));
		deshacer.hacerJugada(jugada);
		jugada = new Jugada(new Celda(new Coordenada(6,6)), new Celda(new Coordenada(6,1)));
		deshacer.hacerJugada(jugada);
		jugada = new Jugada(new Celda(new Coordenada(0,6)), new Celda(new Coordenada(3,6)));
		deshacer.hacerJugada(jugada);		
		
		deshacer.deshacerJugada(); // Deshacemos la jugada y volvemos al estado previo con solo dos jugadas
		deshacer.deshacerJugada(); // Deshacemos la jugada y volvemos al estado previo con solo una jugada
		deshacer.deshacerJugada(); // Deshacemos la jugada y volvemos al estado inicial

		comprobarEstadoInicial();
	}

	/**
	 * Comprueba el estado inicial.
	 * 
	 * @throws MultipleFailuresError errores varios en el test
	 */
	private void comprobarEstadoInicial() throws MultipleFailuresError {
		final Arbitro arbitro2 = deshacer.consultarArbitroActual();	
		final Tablero tablero2 = arbitro2.consultarTablero();
		assertAll("comprobar que deshacer tres jugadas grabadas devolviendo una partida en estado inicial",
				() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(0)),
				() -> assertThat("La reina debería estar nuevamente en la esquina.",
						tablero2.consultarCelda(new Coordenada(0,0)).consultarPieza(), is(new Pieza(TipoPieza.REINA, Color.BLANCO))),
				() -> assertThat("El turno es incorrecto.", arbitro2.consultarTurno(), is(Color.BLANCO)),
				() -> assertThat("El contador de jugadas es incorrecto.", arbitro2.consultarNumeroJugada(), is(0)),
				() -> assertThat("No debería haber ganador.", arbitro2.consultarTurnoGanador(), is(nullValue())),
				() -> assertThat("La partida no debería estar acabada.", arbitro2.estaFinalizadaPartida(), is(false))

		);
	}

	
	/**
	 * Genera un árbitro inicial.
	 * 
	 * @return árbitro inicial
	 */
	static Arbitro generarPartidaInicial() {
		// Generamos una partida inicial...
		Tablero tablero = new Tablero();
		Arbitro arbitro = new Arbitro(tablero);
		arbitro.colocarPiezasConfiguracionInicial();
		return arbitro;
	}
	
}

