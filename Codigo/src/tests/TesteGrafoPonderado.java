package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import grafos.GrafoPonderado;
import vertices.Vertice;

public class TesteGrafoPonderado {
    
    Vertice saoPaulo;
    Vertice rioDeJaneiro;

    GrafoPonderado gp;

    @Test
    public void testeCalculoDistancia(){
        gp = new GrafoPonderado();

        saoPaulo = new Vertice("São Paulo", -23.5504, -46.6339);
        rioDeJaneiro = new Vertice("Rio de Janeiro", -22.9083, -43.1964);

        assertEquals(357.37, gp.calcularDistancias(saoPaulo, rioDeJaneiro));
    }
}
