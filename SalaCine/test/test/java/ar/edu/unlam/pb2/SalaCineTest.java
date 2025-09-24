package test.java.ar.edu.unlam.pb2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.ar.edu.unlam.pb2.sala.*;

public class SalaCineTest {

    Pelicula peliculas[] = new Pelicula[4];
    SalaCine sala1;

    PeliculaAccion accion;
    PeliculaComedia comedia;
    PeliculaDrama drama;
    PeliculaTerror terror;
    PeliculaInfantil infantil;

    @BeforeEach
    void metodoQueSeEjecutaAntesDeTodo() {

        // Películas de Acción
        peliculas[0] = new PeliculaAccion("Piratas del Caribe 1", 200, 14);
        peliculas[1] = new PeliculaAccion("Misión Imposible", 150, 13);
        peliculas[2] = new PeliculaAccion("John Wick 4", 169, 16);
        peliculas[3] = new PeliculaAccion("Top Gun: Maverick", 130, 13);

        sala1 = new SalaCine(2, 3);

        // Más películas para tests de sinopsis
        accion = new PeliculaAccion("Accion Test", 120, 13);
        comedia = new PeliculaComedia("Comedia Test", 90, 0);
        drama = new PeliculaDrama("Drama Test", 100, 10);
        terror = new PeliculaTerror("Terror Test", 110);
        infantil = new PeliculaInfantil("Infantil Test", 80, 0);
    }

    @Test
    void crearSalaOk() {
        PeliculaAccion pelicomparacion = new PeliculaAccion("Piratas del Caribe 1", 200, 14);

        sala1.cambiarPelicula(peliculas[0]);
        assertEquals(2, sala1.getButacas().length);
        assertEquals(3, sala1.getButacas()[0].length);

        assertEquals(pelicomparacion, sala1.getPeliculaActual());
    }

    @Test
    void venderBoletoExitoso() {
        sala1.cambiarPelicula(peliculas[0]);
        assertTrue(sala1.venderBoleto(0, 1, 14, "Julian Morga"));
        assertTrue(sala1.venderBoleto(0, 2, 16, "Julian Morga"));
    }

    @Test
    void venderBoletoNoExitosoPorqueSeIntentaVenderYaVendido() {
        sala1.cambiarPelicula(peliculas[0]);
        assertTrue(sala1.venderBoleto(0, 1, 18, "Julian Morga"));
        assertFalse(sala1.venderBoleto(0, 1, 18, "Julian Morga"));
    }

    @Test
    void venderBoletoNoExitosoPorqueEdadMinimaNoCumplida() {
        sala1.cambiarPelicula(peliculas[0]);
        assertFalse(sala1.venderBoleto(0, 1, 12, "Julian Morga"));
        assertFalse(sala1.venderBoleto(0, 1, -5, "Julian Morga"));
    }

    @Test
    void venderBoletoNoExitosoNombreNullOIndicesFueraDeRango() {
        sala1.cambiarPelicula(peliculas[0]);
        assertFalse(sala1.venderBoleto(0, 0, 18, null));
        assertFalse(sala1.venderBoleto(0, 5, 18, "Juan"));
        assertFalse(sala1.venderBoleto(-1, 0, 18, "Juan"));
    }

    @Test
    void obtenerTotalAscientosTest() {
        SalaCine sala = new SalaCine(2, 3);
        assertEquals(6, sala.getTotalAsientos());
    }

    @Test
    void contarAsientosOcupadosTest() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto(0, 1, 18, "Julian Morga");
        sala1.venderBoleto(0, 2, 18, "Julian Morga");
        sala1.venderBoleto(1, 2, 18, "Julian Morga");
        assertEquals(3, sala1.contarAsientosOcupados());
    }

    @Test
    void liberarAsientoVendido() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto(0, 1, 18, "Julian Morga");
        sala1.venderBoleto(0, 2, 18, "Julian Morga");
        sala1.venderBoleto(1, 2, 18, "Julian Morga");

        assertEquals(3, sala1.contarAsientosOcupados());
        assertTrue(sala1.liberarAsiento(0, 1));
        assertEquals(2, sala1.contarAsientosOcupados());
        sala1.venderBoleto(0, 1, 18, "Julian Morga");
        assertEquals(3, sala1.contarAsientosOcupados());
    }

    @Test
    void liberarAsientoVendidoFueraDeRangoTest() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto(0, 1, 18, "Julian Morga");
        sala1.venderBoleto(0, 2, 18, "Julian Morga");
        sala1.venderBoleto(1, 2, 18, "Julian Morga");

        assertEquals(3, sala1.contarAsientosOcupados());
        assertFalse(sala1.liberarAsiento(5, 10));
        assertFalse(sala1.liberarAsiento(-1, -1));
        assertEquals(3, sala1.contarAsientosOcupados());
    }

    @Test
    void reiniciarSalaTest() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto(0, 0, 18, "Juan");
        sala1.venderBoleto(1, 1, 18, "Ana");
        sala1.reiniciarSala();
        assertEquals(0, sala1.contarAsientosOcupados());
    }

    @Test
    void mostrarButacasYDetalle() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto(0, 0, 18, "Juan");
        sala1.mostrarButacas();
        sala1.mostrarButacasDetalle();
        // solo ejecutamos las líneas para coverage
    }

    @Test
    void testPeliculasMostrarSinopsisYEquals() {
        assertNotNull(accion.mostrarSinopsis());
        assertNotNull(comedia.mostrarSinopsis());
        assertNotNull(drama.mostrarSinopsis());
        assertNotNull(terror.mostrarSinopsis());
        assertNotNull(infantil.mostrarSinopsis());

        assertEquals(new PeliculaAccion("Accion Test", 120, 13), accion);
        assertTrue(!accion.equals(comedia));
    }


    @Test
    void testCambiarPelicula() {
    sala1.cambiarPelicula(comedia);
    assertEquals(comedia, sala1.getPeliculaActual());
    sala1.cambiarPelicula(drama);
    assertEquals(drama, sala1.getPeliculaActual());
}
}