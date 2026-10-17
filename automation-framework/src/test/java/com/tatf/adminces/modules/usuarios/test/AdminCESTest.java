package com.tatf.adminces.modules.usuarios.test;

import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.usuarios.data.CuentaPrueba;
import com.tatf.adminces.modules.usuarios.data.UsuarioDatos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.provider.CsvFileSource;

@Tag("regresion")
public class AdminCESTest extends BaseTest {

    @Tag("accesos")
    @ParameterizedTest(name = "GP-01 Administrador de {2}")
    @CsvFileSource(resources = "/adminces/administradores.csv", numLinesToSkip = 1)
    void crearAdministrador(String nombre, String apellido, String pais) {
        CuentaPrueba cuenta = crearCuenta("admin", nombre, apellido, pais,
                config.leer("perfil.administrador"));
        System.out.println("Cuenta de prueba: " + cuenta.email);

        String error = tarea.registrarAdministrador(cuenta);
        verify.verify("", error, "El registro no debe mostrar un error.");

        nuevaSesion();
        tarea.ingresar(cuenta.email, cuenta.clave);
        verificarUsuario(cuenta);
    }

    @Tag("accesos")
    @ParameterizedTest(name = "GP-02 Reiniciar contraseña de {0}")
    @CsvFileSource(resources = "/adminces/reinicio.csv", numLinesToSkip = 1)
    void reiniciarContrasena(String nombre, String apellido, String pais) {
        String nuevaClave = config.leer("prueba.nuevaClave");
        config.leer("login.error.css");
        String mensajeEsperado = config.leer("login.error.texto");
        verify.notVerify(config.leer("prueba.clave"), nuevaClave, "La nueva clave debe ser diferente.");
        CuentaPrueba cuenta = crearCuenta("reset", nombre, apellido, pais,
                config.leer("perfil.administrador"));
        System.out.println("Cuenta de prueba: " + cuenta.email);

        verify.verify("", tarea.registrarAdministrador(cuenta), "Registro sin errores.");
        nuevaSesion();
        tarea.ingresar(cuenta.email, cuenta.clave);
        verificarUsuario(cuenta);

        nuevaSesion();
        verify.verify("", tarea.reiniciarClave(cuenta.email, nuevaClave), "Reinicio sin errores.");

        nuevaSesion();
        tarea.ingresar(cuenta.email, nuevaClave);
        verificarUsuario(cuenta);

        nuevaSesion();
        tarea.intentarIngreso(cuenta.email, cuenta.clave);
        verify.verify(mensajeEsperado, tarea.obtenerMensajeRechazo(), "Mensaje de rechazo correcto.");
        verify.verifyFalse(tarea.tieneAccesoAdministrador(), "No debe haber acceso al menú privado.");
        verify.verifyTrue(tarea.muestraLogin(), "El login debe continuar visible.");
    }

    @Tag("usuarios")
    @ParameterizedTest(name = "GP-03 Crear {3} de {2}")
    @CsvFileSource(resources = "/adminces/testers.csv", numLinesToSkip = 1)
    void crearTester(String nombre, String apellido, String pais, String perfil) {
        config.leer("admin.email");
        config.leer("admin.clave");
        CuentaPrueba cuenta = crearCuenta("tester", nombre, apellido, pais, perfil);
        System.out.println("Cuenta de prueba: " + cuenta.email);

        tarea.ingresarComoAdministrador();
        tarea.completarTester(cuenta);
        verify.verifyTrue(tarea.perfilTesterSeleccionado(perfil), "Perfil seleccionado correctamente.");

        verify.verify("", tarea.guardarTester(cuenta), "Creación sin errores.");
        tarea.abrirUsuarios();
        verificarUsuario(cuenta);

        tarea.volverACargarUsuarios();
        verificarUsuario(cuenta);
    }

    @Tag("usuarios")
    @ParameterizedTest(name = "GP-04 Eliminar {3} de {2}")
    @CsvFileSource(resources = "/adminces/bajas.csv", numLinesToSkip = 1)
    void eliminarTester(String nombre, String apellido, String pais, String perfil) {
        config.leer("admin.email");
        config.leer("admin.clave");
        CuentaPrueba cuenta = crearCuenta("baja", nombre, apellido, pais, perfil);
        System.out.println("Cuenta de prueba: " + cuenta.email);

        tarea.ingresarComoAdministrador();
        tarea.completarTester(cuenta);
        verify.verifyTrue(tarea.perfilTesterSeleccionado(cuenta.perfil), "Perfil seleccionado.");
        verify.verify("", tarea.guardarTester(cuenta), "Creación sin errores.");
        tarea.abrirUsuarios();
        verificarUsuario(cuenta);

        tarea.eliminarTester(cuenta);
        verify.verify(0, tarea.cantidadUsuarios(cuenta.email), "La cuenta debe desaparecer.");

        tarea.volverACargarUsuarios();
        verify.verify(0, tarea.cantidadUsuarios(cuenta.email), "La eliminación debe persistir.");
    }

    private void verificarUsuario(CuentaPrueba esperado) {
        UsuarioDatos obtenido = tarea.obtenerUsuario(esperado.email);
        verify.verify(1, tarea.cantidadUsuarios(esperado.email), "Cuenta única en la tabla.");
        verify.verify(esperado.nombre, obtenido.nombre, "Nombre correcto.");
        verify.verify(esperado.apellido, obtenido.apellido, "Apellido correcto.");
        verify.verify(esperado.email, obtenido.email, "Email correcto.");
        verify.verify(esperado.pais, obtenido.pais, "País correcto.");
        verify.verify(esperado.perfil, obtenido.perfil, "Perfil correcto.");
    }
}
