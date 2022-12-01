package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grafos.GrafoNaoPonderado;
import uteis.Util;

public class TesteGrafoNaoPonderado{

    private static final String path = "C:\\Users\\Pablo Magalhães\\Documents\\GitHub\\projeto2-grupo-4\\codigo\\resources\\grafo3.txt";
    private static final String pathArthur = "C:\\Users\\arthu\\OneDrive\\Área de Trabalho" + 
    "\\Programação\\exercicios\\exercicios_puc\\3_Periodo\\TB_02\\projeto2-grupo-4\\codigo\\resources\\grafo1.txt";
    private static final String pathGrafoDesconexo = "C:\\Users\\arthu\\OneDrive\\Área de Trabalho" + 
    "\\Programação\\exercicios\\exercicios_puc\\3_Periodo\\TB_02\\projeto2-grupo-4\\codigo\\resources\\grafo2.txt";
    private static final String pathArthur2 = "C:\\Users\\arthu\\OneDrive\\Área de Trabalho" + 
    "\\Programação\\exercicios\\exercicios_puc\\3_Periodo\\TB_02\\projeto2-grupo-4\\codigo\\resources\\grafo2.txt";

    @Test
    public void testCarregamento(){

        Util.ImprimiMensagem("Teste 1: ");

        GrafoNaoPonderado grafo = retornaGrafo();

        boolean carregamento = grafo.carregarGrafo(path);
        
        grafo.imprimiGrafoNaoPonderado();

        assertEquals(true, carregamento);

    }

    @Test
    public void testDeletaVertice(){

        Util.ImprimiMensagem("Teste 2: ");

        GrafoNaoPonderado grafo = retornaGrafo();

        grafo.carregarGrafo(path);

        Util.ImprimiMensagem("Grafo antes de deletar vértice: ");

        grafo.imprimiGrafoNaoPonderado();

        //int antiga_ordem = grafo.getOrdem();
        //boolean deletado = false;

        grafo.deletaVertice(0);

        Util.ImprimiMensagem("Grafo depois de deletar vértice: ");

        grafo.imprimiGrafoNaoPonderado();

        /*if(grafo.getOrdem() != antiga_ordem){

            deletado = true;

        } */

        //assertEquals(true, deletado);

    }

    
    @Test
    public void testaTamanhoDoGrafo(){

        GrafoNaoPonderado grafo = retornaGrafo();

        grafo.carregarGrafo(path);

        assertEquals(15,grafo.ordem());
    }

    private GrafoNaoPonderado retornaGrafo(){
        return new GrafoNaoPonderado();
    }

    @Test 
    public void grafoEhConexo(){
        GrafoNaoPonderado grafo = retornaGrafo();
        grafo.carregarGrafo(pathArthur);

        assertEquals(true,grafo.ehConexo(grafo.getVertices()));
    }

    @Test 
    public void grafoNaoEhConexo(){
        GrafoNaoPonderado grafo = retornaGrafo();
        grafo.carregarGrafo(pathGrafoDesconexo);

        assertEquals(false,grafo.ehConexo(grafo.getVertices()));
    }

    @Test 
    public void grafoEuleriano(){
        GrafoNaoPonderado grafo = retornaGrafo();
        grafo.carregarGrafo(pathArthur);

        assertEquals(true,grafo.euleriano());
    }

    @Test 
    public void grafoNaoEulerianoPorSerDesconexo(){
        GrafoNaoPonderado grafo = retornaGrafo();
        grafo.carregarGrafo(pathGrafoDesconexo);

        assertEquals(false,grafo.euleriano());
    }

    @Test 
    public void grafoNaoEulerianoPorVerticeDeGrauImpar(){
        GrafoNaoPonderado grafo = retornaGrafo();
        grafo.carregarGrafo(pathArthur2);

        assertEquals(false,grafo.euleriano());
    }

    @Test 
    public void testaCaminhoEuleriano(){
        GrafoNaoPonderado grafo = retornaGrafo();
        grafo.carregarGrafo(pathArthur);
        System.out.println(grafo.caminhoEuleriano().toString());
        
    }

    @Test
    public void BuscaEmProfunidade(){

        GrafoNaoPonderado grafo = retornaGrafo();

        grafo.carregarGrafo(path);

        grafo.BuscaProfunidade(0);

        


    }

}