package com.tatf.adminces.modules.usuarios.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.*;
import com.tatf.adminces.modules.login.pom.*;
import com.tatf.adminces.modules.administrador.pom.RegistroAdministradorPO;
import com.tatf.adminces.modules.tester.pom.CrearTesterPO;
import com.tatf.adminces.modules.usuarios.pom.UsuariosPO;
import com.tatf.adminces.modules.usuarios.data.*;

public class AdminCESTask {
    private final AdminCESConfig config;
    private final AccesoTallerPO taller;
    private final MenuPO menu;
    private final LoginPO login;
    private final AvisoPO aviso;
    private final RegistroAdministradorPO registro;
    private final ReinicioClavePO reinicio;
    private final CrearTesterPO tester;
    private final UsuariosPO usuarios;
    private String emailSesion;
    private String claveSesion;
    private String emailTesterCreado;

    public AdminCESTask(IBrowser browser, AdminCESConfig config) {
        this.config = config;
        taller = new AccesoTallerPO(browser);
        menu = new MenuPO(browser);
        login = new LoginPO(browser);
        aviso = new AvisoPO(browser);
        registro = new RegistroAdministradorPO(browser);
        reinicio = new ReinicioClavePO(browser);
        tester = new CrearTesterPO(browser);
        usuarios = new UsuariosPO(browser);
    }

    public void entrarAlTaller() {
        taller.entrar(config.leer("url"), config.leer("claveTaller"));
    }

    public String registrarAdministrador(CuentaPrueba cuenta) {
        entrarAlTaller();
        menu.abrirRegistro();
        registro.registrar(cuenta);
        return leerYCerrarAviso();
    }

    public void ingresar(String email, String clave) {
        entrarAlTaller();
        menu.abrirLogin();
        login.ingresarCredenciales(email, clave);
        menu.esperarAccesoAdministrador();
        aviso.cerrar();
        emailSesion = email;
        claveSesion = clave;
        abrirUsuarios();
        usuarios.esperarUsuario(email);
    }

    public void ingresarComoAdministrador() {
        ingresar(config.leer("admin.email"), config.leer("admin.clave"));
    }

    public String reiniciarClave(String email, String nuevaClave) {
        entrarAlTaller();
        menu.abrirReinicio();
        reinicio.cambiar(email, nuevaClave);
        return leerYCerrarAviso();
    }

    public void intentarIngreso(String email, String clave) {
        entrarAlTaller();
        menu.abrirLogin();
        login.ingresarCredenciales(email, clave);
    }

    public String obtenerMensajeRechazo() {
        return login.obtenerError(config.leer("login.error.css"));
    }

    public boolean muestraLogin() {
        return login.estaVisible();
    }

    public boolean tieneAccesoAdministrador() {
        return menu.tieneAccesoAdministrador();
    }

    public void completarTester(CuentaPrueba cuenta) {
        aviso.cerrar();
        menu.abrirCreacionTester();
        tester.completar(cuenta);
    }

    public boolean perfilTesterSeleccionado(String perfil) {
        return tester.perfilSeleccionado(perfil);
    }

    public String guardarTester(CuentaPrueba cuenta) {
        tester.guardar();
        String error = leerYCerrarAviso();
        if (error.isEmpty()) {
            emailTesterCreado = cuenta.email;
        }
        return error;
    }

    public void abrirUsuarios() {
        aviso.cerrar();
        menu.abrirUsuarios();
        usuarios.esperarTabla();
    }

    public UsuarioDatos obtenerUsuario(String email) {
        return usuarios.obtenerUsuario(email);
    }

    public int cantidadUsuarios(String email) {
        return usuarios.cantidad(email);
    }

    public void eliminarTester(CuentaPrueba cuenta) {
        if (!cuenta.email.equals(emailTesterCreado)) {
            throw new IllegalArgumentException("Solo se puede eliminar el Tester creado en esta prueba.");
        }
        usuarios.eliminar(cuenta.email);
    }

    public void volverACargarUsuarios() {
        // Cargar la raíz: /adminces/view-users no permite recarga directa.
        entrarAlTaller();
        if (menu.tieneAccesoAdministrador()) {
            abrirUsuarios();
        } else {
            ingresar(emailSesion, claveSesion);
        }
        usuarios.esperarUsuario(emailSesion);
    }

    private String leerYCerrarAviso() {
        String error = aviso.obtenerError();
        aviso.cerrar();
        return error;
    }
    public void cerrarSesion() {
        aviso.cerrar();
        menu.cerrarSesion();
    }


}
