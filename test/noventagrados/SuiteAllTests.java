package noventagrados;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suite ejecutando TODOS los tests de la práctica NoventaGrados-2.0.
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
@SuiteDisplayName("Ejecución de todos los tests de la práctica NoventaGrados-2.0.")
public class SuiteAllTests {
}
