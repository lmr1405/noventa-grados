package noventagrados.control.undo;

import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;

/**
 * Tests unitarios sobre el mecanismo de deshacer basado en clones de árbitros.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 1.0
 * @version 2.0
 */
@DisplayName("Tests sobre mecanismos para deshacer utilizando clones de árbitros.")
@Tag("IntegrationTest")
@Timeout(value = 3, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD) // Time out global para todos los tests salvo los de ciclo de vida
public class MaquinaDelTiempoConArbitrosTest extends DeshacerTest {

	/**
	 * Inicialización.
	 */
	@BeforeEach
	void iniciar() {
		deshacer = new MaquinaDelTiempoConArbitros(new Date());
	}
	
	@Override
	MecanismoDeDeshacer obtenerDeshacerConFechaPersonalizada(Date date) {
		return new MaquinaDelTiempoConArbitros(date);
	}
}
