package com.tatf.adminces.modules.administrador.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.BasePO;
import com.tatf.adminces.modules.usuarios.data.CuentaPrueba;

public class RegistroAdministradorPO extends BasePO {
    private final String formulario = "#formAccount";
    private final String nombre = "#formAccount [name='inputFirstName']";
    private final String apellido = "#formAccount [name='inputLastName']";
    private final String email = "#formAccount [name='inputEmail']";
    private final String pais = "#formAccount [name='inputCountry']";
    private final String clave = "#formAccount [name='inputPassword']";
    private final String repetirClave = "#formAccount [name='inputRepeatPassword']";
    private final String guardar = "#formAccount #btnRegister";

    public RegistroAdministradorPO(IBrowser browser) {
        super(browser);
    }

    public void registrar(CuentaPrueba cuenta) {
        escribir(nombre, cuenta.nombre);
        escribir(apellido, cuenta.apellido);
        escribir(email, cuenta.email);
        escribir(pais, cuenta.pais);
        escribir(clave, cuenta.clave);
        escribir(repetirClave, cuenta.clave);
        pulsar(guardar);
        esperarRespuesta(formulario);
    }
}
