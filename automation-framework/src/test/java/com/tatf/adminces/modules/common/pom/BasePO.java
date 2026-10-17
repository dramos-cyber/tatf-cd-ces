package com.tatf.adminces.modules.common.pom;

import com.tatf.core.browser.IBrowser;

// Acciones pequeñas que comparten los formularios.
public class BasePO {
    protected final IBrowser browser;

    public BasePO(IBrowser browser) {
        this.browser = browser;
    }

    protected void escribir(String selector, String texto) {
        browser.wait(selector).css();
        browser.find().css(selector).clear().write(texto);
    }

    protected void pulsar(String selector) {
        browser.wait(selector).cssClickable();
        browser.wait(selector).until(() -> {
            // El aviso puede aparecer entre la espera inicial y el clic.
            if (existe(".swal2-container.swal2-backdrop-show")) {
                new AvisoPO(browser).cerrar();
            }
            if (!browser.find().css(selector).isDisplayed() || !browser.find().css(selector).isEnabled()) {
                return false;
            }
            browser.find().css(selector).click();
            return true;
        });
    }

    protected boolean existe(String selector) {
        return !browser.find().cssList(selector).isEmpty();
    }

    protected void esperarRespuesta(String formulario) {
        browser.wait("body").until(() -> existe(".swal2-popup.swal2-show") || !existe(formulario));
    }
}
