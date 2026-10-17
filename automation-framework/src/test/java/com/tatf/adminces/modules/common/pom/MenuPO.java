package com.tatf.adminces.modules.common.pom;

import com.tatf.core.browser.IBrowser;

public class MenuPO extends BasePO {
    private final String registrarse = "a.nav-link[href='/adminces/register']";
    private final String crearUsuario = "a.nav-link[href='/adminces/create-user']";
    private final String verUsuarios = "a.nav-link[href='/adminces/view-users']";
    private final String iniciarSesion = "Iniciar sesión";
    private final String reiniciarClave = "Reiniciar contraseña";

    public MenuPO(IBrowser browser) {
        super(browser);
    }

    public void abrirRegistro() {
        pulsar(registrarse);
    }

    public void abrirLogin() {
        browser.wait(iniciarSesion).link();
        browser.find().link(iniciarSesion).click();
    }

    public void abrirReinicio() {
        browser.wait(reiniciarClave).link();
        browser.find().link(reiniciarClave).click();
    }

    public void abrirCreacionTester() {
        pulsar(crearUsuario);
    }

    public void abrirUsuarios() {
        pulsar(verUsuarios);
    }

    public void esperarAccesoAdministrador() {
        browser.wait(verUsuarios).css();
    }

    public boolean tieneAccesoAdministrador() {
        return existe(verUsuarios);
    }

    public boolean estaCargado() {
        return existe(registrarse) || existe(verUsuarios);
    }

    public void cerrarSesion() {
        if (tieneAccesoAdministrador()) {
            pulsar("a.dropdown-toggle[data-bs-toggle='dropdown']");
            pulsar("a.dropdown-item[href='/adminces']");

            AvisoPO aviso = new AvisoPO(browser);
            aviso.cerrar();

            browser.wait(registrarse).css();
        }
    }

}
