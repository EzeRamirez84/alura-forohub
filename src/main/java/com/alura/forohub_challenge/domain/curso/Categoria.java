package com.alura.forohub_challenge.domain.curso;

public enum Categoria {
    FRONTEND("Frontend"),
    BACKEND("Backend"),
    DESARROLLO_PERSONAL("Desarrollo Personal");

    private String categoria;

    Categoria(String s) {
        this.categoria = s;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoria.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
