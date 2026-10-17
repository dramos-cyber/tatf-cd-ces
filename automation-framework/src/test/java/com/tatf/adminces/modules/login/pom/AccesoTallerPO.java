package com.tatf.adminces.modules.login.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.BasePO;
import com.tatf.adminces.modules.common.pom.MenuPO;

public class AccesoTallerPO extends BasePO {
    private final String formulario = "#loginForm";
    private final String clave = "#loginForm #pass";
    private final String ingresar = "#loginForm button[type='submit']";

    public AccesoTallerPO(IBrowser browser) {
        super(browser);
    }

    public void entrar(String url, String claveTaller) {
        browser.interaction().navigateTo(url);
        MenuPO menu = new MenuPO(browser);
        browser.wait("body").until(() -> existe(formulario) || menu.estaCargado());
        if (existe(formulario)) {
            escribir(clave, claveTaller);
            pulsar(ingresar);
            browser.wait("body").until(() -> !existe(formulario) && menu.estaCargado());
        }
    }
}
