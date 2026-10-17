package com.tatf.adminces.modules.usuarios.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AdminCESConfig {
    private final Properties datos = new Properties();

    public AdminCESConfig() throws IOException {
        cargarRecurso("adminces/entorno.properties");
        cargarRecurso("adminces/esperados.properties");

        // Las contraseñas se completan en un archivo local.
        Path archivo = Path.of("adminces.properties");
        if (!Files.exists(archivo)) {
            throw new IllegalStateException("Copiar adminces.properties.example a adminces.properties y completar los datos.");
        }
        try (Reader entrada = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            datos.load(entrada);
        }
    }

    private void cargarRecurso(String nombre) throws IOException {
        try (InputStream entrada = getClass().getClassLoader().getResourceAsStream(nombre)) {
            if (entrada == null) {
                throw new IllegalStateException("No se encontró el archivo " + nombre);
            }
            datos.load(new InputStreamReader(entrada, StandardCharsets.UTF_8));
        }
    }

    public String leer(String nombre) {
        String valor = datos.getProperty(nombre);
        if (valor == null || valor.isBlank() || valor.equals("COMPLETAR")) {
            throw new IllegalStateException("Falta completar el dato: " + nombre);
        }
        return valor;
    }
}
