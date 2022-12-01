package buscas;

import java.util.ArrayList;
import java.util.List;

import uteis.Util;

public class BuscaEmProfunidade {
    
    private List<Integer> id_vertice; 
    private List<Integer> tempo_descoberta;
    private List<Integer> tempo_visitado;
    private List<Integer> id_pai;

    /* Construtores */

    private void init(){

        this.id_vertice = new ArrayList<>();
        this.tempo_descoberta = new ArrayList<>();
        this.tempo_visitado = new ArrayList<>();
        this.id_pai = new ArrayList<>();
    }

    public BuscaEmProfunidade(){
        init();
    }

    /* Métodos */

    public List<Integer> getIdVertice(){
        return this.id_vertice;
    }

    public void registraIdVertice(int id_vertice){
        this.id_vertice.add(id_vertice);
    }

    public void registraTempoDescoberta(int tempo_descoberta){
        this.tempo_descoberta.add(tempo_descoberta);
    }

    public void registraTempoVisitado(int tempo_visitado){
        this.tempo_visitado.add(tempo_visitado);
    }

    public void registraIdPai(int id_pai){
        this.id_pai.add(id_pai);
    }

    public void alteraTempoVisitado(int id_vertice, int novo_tempo_visitado){
        this.tempo_visitado.set(id_vertice, novo_tempo_visitado);
    }

    public void registroGeral(int id_vertice, int tempo_descoberta, int tempo_visitado, int id_pai){

        registraIdVertice(id_vertice);
        registraTempoDescoberta(tempo_descoberta);
        registraTempoVisitado(tempo_visitado);
        registraIdPai(id_pai);

    }

    public void imprimiBusca(){

        for(int i = 0; i < this.id_vertice.size(); i++){

            Util.ImprimiMensagem("Vértice: " + this.id_vertice.get(i));
            Util.ImprimiMensagem("Tempo descoberta: " + this.tempo_descoberta.get(i));
            Util.ImprimiMensagem("Tempo visitado: " + this.tempo_visitado.get(i));
            Util.ImprimiMensagem("Id do Pai: " + this.id_pai.get(i));

            Util.ImprimiMensagem("");

        }



    }

}
