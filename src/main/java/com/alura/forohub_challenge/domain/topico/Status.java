package com.alura.forohub_challenge.domain.topico;


public enum Status {
    ABIERTO("Abierto"),
    CERRADO("Cerrado"),
    RESUELTO("Resuelto");

    private String status;
    Status(String status) {
        this.status = status;
    }

    public static Status fromString(String text) {
        for (Status status : Status.values()) {
            if (status.status.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Ningun status encontrado: " + text);
    }
}
