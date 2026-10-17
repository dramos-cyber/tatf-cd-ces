package com.tatf.adminces.modules.login.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.BasePO;

public class ReinicioClavePO extends BasePO {
    private final String formulario = "#formResetPassword";
    private final String email = "#formResetPassword [name='inputEmail']";
    private final String clave = "#formResetPassword [name='inputPassword']";
    private final String repetirClave = "#formResetPassword [name='inputRepeatPassword']";
    private final String guardar = "#formResetPassword #btnReset";

    public ReinicioClavePO(IBrowser browser) {
        super(browser);
    }

    public void cambiar(String usuario, String nuevaClave) {
        escribir(email, usuario);
        escribir(clave, nuevaClave);
        escribir(repetirClave, nuevaClave);
        pulsar(guardar);
        esperarRespuesta(formulario);
    }
}
