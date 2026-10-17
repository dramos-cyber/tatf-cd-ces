package com.tatf.adminces.modules.usuarios.data;

public class UsuarioDatos {
    public final String nombre;
    public final String apellido;
    public final String email;
    public final String pais;
    public final String perfil;

    public UsuarioDatos(String nombre, String apellido, String email, String pais, String perfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pais = pais;
        this.perfil = perfil;
    }
}
