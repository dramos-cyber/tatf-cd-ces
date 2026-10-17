package com.tatf.adminces.modules.usuarios.pom;

import com.tatf.core.element.Element;
import com.tatf.adminces.modules.usuarios.data.UsuarioDatos;

public class FilaUsuarioPO {
    private final Element fila;
    private final String nombre = "td:nth-child(1)";
    private final String apellido = "td:nth-child(2)";
    private final String email = "td:nth-child(3)";
    private final String pais = "td:nth-child(4)";
    private final String perfil = "td:nth-child(5)";
    private final String eliminar = "td:nth-child(6) button";

    public FilaUsuarioPO(Element fila) {
        this.fila = fila;
    }

    public UsuarioDatos obtenerDatos() {
        return new UsuarioDatos(
                fila.css(nombre).getText().trim(),
                fila.css(apellido).getText().trim(),
                fila.css(email).getText().trim(),
                fila.css(pais).getText().trim(),
                fila.css(perfil).getText().trim());
    }

    public void eliminar(String emailEsperado) {
        UsuarioDatos datos = obtenerDatos();
        Element boton = fila.css(eliminar);
        // Protección de la acción: impedir una baja de otra cuenta.
        if (!datos.email.equals(emailEsperado) || !datos.perfil.startsWith("Tester ")
                || !emailEsperado.equals(boton.getAttribute("id"))) {
            throw new IllegalArgumentException("El botón no pertenece al Tester solicitado.");
        }
        boton.click();
    }
}
