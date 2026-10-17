package com.tatf.adminces.modules.tester.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.BasePO;
import com.tatf.adminces.modules.usuarios.data.CuentaPrueba;

public class CrearTesterPO extends BasePO {
    private final String formulario = "#formCreateUser";
    private final String nombre = "#formCreateUser [name='inputFirstName']";
    private final String apellido = "#formCreateUser [name='inputLastName']";
    private final String email = "#formCreateUser [name='inputEmail']";
    private final String pais = "#formCreateUser [name='inputCountry']";
    private final String clave = "#formCreateUser [name='inputPassword']";
    private final String guardar = "#formCreateUser #btnRegister";

    public CrearTesterPO(IBrowser browser) {
        super(browser);
    }

    public void completar(CuentaPrueba cuenta) {
        escribir(nombre, cuenta.nombre);
        escribir(apellido, cuenta.apellido);
        escribir(email, cuenta.email);
        browser.wait(pais).css();
        browser.find().css(pais).selectValue(cuenta.pais);
        escribir(clave, cuenta.clave);
        pulsar(etiquetaPerfil(cuenta.perfil));
    }

    public boolean perfilSeleccionado(String perfil) {
        return browser.find().css(radioPerfil(perfil)).isSelected();
    }

    public void guardar() {
        pulsar(guardar);
        esperarRespuesta(formulario);
    }

    private String idPerfil(String perfil) {
        switch (perfil) {
            case "Tester Junior": return "testerJunior";
            case "Tester Senior": return "testerSenior";
            case "Tester Líder": return "testerLead";
            default: throw new IllegalArgumentException("Perfil no disponible: " + perfil);
        }
    }

    private String etiquetaPerfil(String perfil) {
        return "#formCreateUser label[for='" + idPerfil(perfil) + "']";
    }

    private String radioPerfil(String perfil) {
        return "#formCreateUser #" + idPerfil(perfil);
    }
}
