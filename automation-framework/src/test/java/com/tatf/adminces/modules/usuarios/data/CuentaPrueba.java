package com.tatf.adminces.modules.usuarios.data;

import java.util.UUID;

public class CuentaPrueba {
    public final String nombre;
    public final String apellido;
    public final String email;
    public final String pais;
    public final String perfil;
    public final String clave;

    public CuentaPrueba(String tipo, String nombre, String apellido, String pais, String perfil, String clave, String prefijo, String dominio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.perfil = perfil;
        this.clave = clave;
        String codigo = UUID.randomUUID().toString().replace("-", "");
        this.email = prefijo + tipo + codigo + "@" + dominio;
    }
}
