
package centro_deporte;

import centro_deporte.manager.CentroDeporte;
import centro_deporte.manager.ICentroDeporte;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CentroDeporteTest {

    private ICentroDeporte centroDeporte;

    @BeforeAll
    static void iniciarPruebas() {
        System.out.println("INICIO DE LAS PRUEBAS");
    }

    @BeforeEach
    void prepararPrueba() {
        centroDeporte = ICentroDeporte.create();
    }

    @Test
    void obtenerDeportesTest() {

        List<String> deportes = centroDeporte.obtenerDeportes();

        assertThat(deportes)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void obtenerDeportesPorLetraTest() {

        List<String> todosLosDeportes = centroDeporte.obtenerDeportes();

        String deporte = todosLosDeportes.get(0);
        String letra = deporte.substring(0, 1);

        List<String> resultado = centroDeporte.obtenerDeportes(letra);

        assertThat(resultado)
                .isNotEmpty();

        assertThat(resultado)
                .allMatch(nombre -> nombre.startsWith(letra));
    }

    @Test
    void crearDeporteTest() {

        String nuevoDeporte = "Padel";

        centroDeporte.crearDeporte(nuevoDeporte);

        List<String> deportes = centroDeporte.obtenerDeportes();

        assertThat(deportes)
                .contains(nuevoDeporte + "[DEPORTE]");
    }

    @Test
    void modificarDeporteTest() {

        List<String> deportesIniciales = centroDeporte.obtenerDeportes();

        String deporteOriginal = deportesIniciales.get(0);
        String nuevoNombre = "NuevoDeporte";

        centroDeporte.modificarDeporte(deporteOriginal, nuevoNombre);

        List<String> deportesFinales = centroDeporte.obtenerDeportes();

        assertThat(deportesFinales)
                .contains(nuevoNombre);

        assertThat(deportesFinales)
                .doesNotContain(deporteOriginal);
    }

    @Test
    void eliminarDeporteTest() {

        List<String> deportesIniciales = centroDeporte.obtenerDeportes();

        String deporte = deportesIniciales.get(0);

        centroDeporte.eliminarDeporte(deporte);

        List<String> deportesFinales = centroDeporte.obtenerDeportes();

        assertThat(deportesFinales)
                .doesNotContain(deporte);
    }


    @AfterEach
    void finalizarPrueba() {
        centroDeporte = null;
    }

    @AfterAll
    static void finalizarPruebas() {
        System.out.println("FIN DE LAS PRUEBAS");
    }
}