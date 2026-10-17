package com.tatf.adminces.modules.login.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.BasePO;

public class LoginPO extends BasePO {
    private final String formulario = "#formLogin";
    private final String email = "#formLogin [name='inputEmail']";
    private final String clave = "#formLogin [name='inputPassword']";
    private final String ingresar = "#formLogin button[type='button']";

    public LoginPO(IBrowser browser) {
        super(browser);
    }

    public void ingresarCredenciales(String usuario, String contrasena) {
        escribir(email, usuario);
        escribir(clave, contrasena);
        pulsar(ingresar);
    }

    public String obtenerError(String selectorError) {
        // El selector real se lee de los datos, sin colocarlo en el test.
        browser.wait(selectorError).css();
        return browser.find().css(selectorError).getText().trim();
    }

    public boolean estaVisible() {
        return existe(formulario) && browser.find().css(formulario).isDisplayed();
    }
}
