package com.tatf.adminces.modules.common.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;
import org.openqa.selenium.NoAlertPresentException;

// Componente reutilizable para los avisos de la aplicación.
public class AvisoPO extends BasePO {
    private final String error = ".swal2-popup.swal2-icon-error";
    private final String confirmar = ".swal2-container .swal2-confirm";
    private final String fondo = ".swal2-container.swal2-backdrop-show";
    private final String ventana = ".swal2-popup.swal2-show";

    public AvisoPO(IBrowser browser) {
        super(browser);
    }

    public String obtenerError() {
        if (existe(error)) {
            return browser.find().css(error).getText().trim();
        }
        return "";
    }

    public void cerrar() {
        browser.wait("body").until(() -> {
            for (Element boton : browser.find().cssList(confirmar)) {
                if (boton.isDisplayed() && boton.isEnabled()) {
                    boton.click();
                    break;
                }
            }
            return !existe(fondo) && !existe(ventana);
        });
    }

    public void aceptarConfirmacionSiExiste() {
        try {
            browser.interaction().switchToAlert().acceptAlert();
        } catch (NoAlertPresentException excepcion) {
            // Si no hay alerta nativa, puede haber un aviso SweetAlert2.
        }
        cerrar();
    }
}
