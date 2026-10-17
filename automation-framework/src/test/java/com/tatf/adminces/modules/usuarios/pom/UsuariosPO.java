package com.tatf.adminces.modules.usuarios.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.common.pom.BasePO;
import com.tatf.adminces.modules.common.pom.AvisoPO;
import com.tatf.adminces.modules.usuarios.data.UsuarioDatos;

public class UsuariosPO extends BasePO {
    private final String tabla = "#dataTable";

    public UsuariosPO(IBrowser browser) {
        super(browser);
    }

    public void esperarTabla() {
        browser.wait(tabla).css();
    }

    public void esperarUsuario(String email) {
        browser.wait(xpathFila(email)).xpath();
    }

    public int cantidad(String email) {
        return browser.find().xpathList(xpathFila(email)).size();
    }

    public UsuarioDatos obtenerUsuario(String email) {
        esperarUsuario(email);
        FilaUsuarioPO fila = new FilaUsuarioPO(browser.find().xpath(xpathFila(email)));
        return fila.obtenerDatos();
    }

    public void eliminar(String email) {
        esperarUsuario(email);
        FilaUsuarioPO fila = new FilaUsuarioPO(browser.find().xpath(xpathFila(email)));
        fila.eliminar(email);
        AvisoPO aviso = new AvisoPO(browser);
        browser.wait("body").until(() -> {
            aviso.aceptarConfirmacionSiExiste();
            return cantidad(email) == 0;
        });
    }

    private String xpathFila(String email) {
        if (email.contains("'")) {
            throw new IllegalArgumentException("El email no puede contener comillas simples.");
        }
        return "//*[@id='bodyTable']/tr[td[3][normalize-space(.)='" + email + "']]";
    }
}
