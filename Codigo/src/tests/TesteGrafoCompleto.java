package tests;

import grafos.GrafoCompleto;
import org.junit.Test;
import vertices.Vertice;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TesteGrafoCompleto {

    private GrafoCompleto grafo = new GrafoCompleto();

    @Test
    public void testaGrafoCompleto(){

        List<Vertice> teste = new ArrayList<Vertice>();

        Vertice a = new Vertice();
        a.setID(3);
        teste.add(a);
        Vertice b = new Vertice();
        b.setID(10);
        teste.add(b);
        Vertice c = new Vertice();
        c.setID(15);
        teste.add(c);
        Vertice d = new Vertice();
        d.setID(17);
        teste.add(d);
        Vertice e = new Vertice();
        e.setID(19);
        teste.add(e);
        Vertice f = new Vertice();
        f.setID(20);
        teste.add(f);

        grafo.gerarGrafoCompleto(teste);

        assertEquals(5, grafo.ordem(a));
    }

    @Test
    public void testaCaminho(){
        List<Vertice> caminho = new ArrayList<Vertice>();

        Vertice a = new Vertice();
        a.setID(3);
        caminho.add(a);
        Vertice b = new Vertice();
        b.setID(10);
        //    caminho.add(b);
        Vertice c = new Vertice();
        c.setID(15);
        //    caminho.add(c);
        Vertice d = new Vertice();
        d.setID(17);
        //    caminho.add(d);
        Vertice e = new Vertice();
        e.setID(19);
        //    caminho.add(e);
        Vertice f = new Vertice();
        f.setID(20);
        caminho.add(f);

        grafo.gerarGrafoCompleto(caminho);

        assertEquals(caminho, grafo.caminhar(a, f));
    }
}

