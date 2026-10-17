package com.tatf.adminces.modules.base;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.adminces.modules.usuarios.data.AdminCESConfig;
import com.tatf.adminces.modules.usuarios.data.CuentaPrueba;
import com.tatf.adminces.modules.usuarios.task.AdminCESTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {
    protected IBrowser browser;
    protected IVerify verify;
    protected AdminCESConfig config;
    protected AdminCESTask tarea;

    @BeforeEach
    public void preparar() throws Exception {
        config = new AdminCESConfig();
        config.leer("url");
        config.leer("claveTaller");
        config.leer("prueba.clave");
        verify = IVerify.create();
        abrirNavegador();
    }

    @AfterEach
    public void cerrar() {
        if (browser != null) {
            BrowserFactory.quitBrowser();
            browser = null;
        }
    }

    protected void nuevaSesion() {
        tarea.cerrarSesion();
    }

    protected CuentaPrueba crearCuenta(String tipo, String nombre, String apellido, String pais, String perfil) {
        return new CuentaPrueba(tipo, nombre, apellido, pais, perfil,
                config.leer("prueba.clave"), config.leer("cuenta.prefijo"), config.leer("cuenta.dominio"));
    }

    private void abrirNavegador() {
        browser = BrowserFactory.getBrowser();
        tarea = new AdminCESTask(browser, config);
    }
}
