package main.java.ar.edu.unlam.pb2.sala;

package com.morga.saladecine;

import java.util.*;

public class SalaCine {
    private Map<String, Asiento> butacas; // clave tipo "A1", "B3"
    private Pelicula pelicula;

    public SalaCine(int filas, int columnas) {
        this.butacas = new HashMap<>();
        for (int i = 0; i < filas; i++) {
            char letra = (char) ('A' + i);
            for (int j = 1; j <= columnas; j++) {
                String clave = letra + String.valueOf(j);
                butacas.put(clave, new Asiento());
            }
        }
    }

    public Map<String, Asiento> getButacas() {
        return butacas;
    }

    public int contarAsientosOcupados() {
        int contador = 0;
        for (Asiento a : butacas.values()) {
            if (a.estaOcupado()) contador++;
        }
        return contador;
    }

    public int getTotalAsientos() {
        return butacas.size();
    }

    public void cambiarPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Pelicula getPeliculaActual() {
        return pelicula;
    }

    public boolean venderBoleto(String clave, int edad, String nombreComprador) throws ButacaNoDisponibleException {
        Asiento asiento = butacas.get(clave);
        if (asiento == null) {
            throw new ButacaNoDisponibleException("Butaca no existe: " + clave);
        }
        if (asiento.estaOcupado()) {
            throw new ButacaNoDisponibleException("Butaca ya ocupada: " + clave);
        }
        if (this.pelicula.getEdadMinima() > edad) {
            return false;
        }
        if (nombreComprador == null) {
            return false;
        }
        asiento.ocupar(nombreComprador);
        return true;
    }

    public boolean liberarAsiento(String clave) throws ButacaNoDisponibleException {
        Asiento asiento = butacas.get(clave);
        if (asiento == null) {
            throw new ButacaNoDisponibleException("Butaca no existe: " + clave);
        }
        asiento.liberar();
        return true;
    }

    public String getTitulo() {
        return pelicula.getTitulo();
    }

    // Listado de asientos libres
    public List<String> listarAsientosLibres() {
        List<String> libres = new ArrayList<>();
        for (Map.Entry<String, Asiento> entry : butacas.entrySet()) {
            if (!entry.getValue().estaOcupado()) {
                libres.add(entry.getKey());
            }
        }
        return libres;
    }
}
