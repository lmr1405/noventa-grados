package noventagrados;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suite ejecutando los tests de nivel 9 de la práctica NoventaGrados-2.0 (ver README.txt).
 * Equivalente a ejecutar {@link noventagrados.SuiteAllTests} con todos lo tests.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 1.0
 * @version 1.0
 */
@SelectPackages({
	"noventagrados.control",
	"noventagrados.modelo",
	"noventagrados.util",
	"noventagrados.textui.excepcion"})

@Suite
@SuiteDisplayName("Tests de paquetes control (completo), modelo y util completos con partidas completas y situación de empate, incluyendo paquete para deshacer jugadas.")
public class SuiteLevel9Tests {

}
