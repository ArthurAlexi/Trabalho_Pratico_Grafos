package vertices;
import java.util.ArrayList;
import java.util.List;

import arestas.Aresta;
import uteis.Util;

public class Vertice {

    private int id = Util.retornaID();
    private List<Aresta> arestas;
    private boolean visitado;
    private int tempoDescobrimento;
    private int tempoTermino;
    private int pai;

    /* Construtor */

    private void init(boolean visitado){

        this.arestas = new ArrayList<>();
        this.visitado = visitado;

    }

    public Vertice(){

        init(false);

    }

    /* Métodos */


    /**
     * @param destino -> recebe o destino para adiconar uma nova aresta 
     * @return -> caso seja possível adiconar a aresta, é retornado verdadeiro, caso não, falso
     */
    public boolean addAresta(int destino){

        Aresta arestaAdd = new Aresta(destino);

        if(!Util.IsListaArestasVazia(this.arestas)){

            if(existeAresta(destino) == null){
                
                addAresta(arestaAdd);
                return true;

            }else{

                Util.ImprimiErro("Já existe uma aresta nesse destino");
                return false;

            }

        }else{
            
            addAresta(arestaAdd);
            return true;
        
        }
        
    }


    /**
     * @param destino -> recebe o destino e verifica se existe alguma aresta que realiza esse destino, verificando sua existência
     * @return -> caso exista uma aresta ela é retornada, caso não é voltado uma aresta nula
     */
    public Aresta existeAresta(int destino){

        if(!Util.IsListaArestasVazia(this.arestas)){

            for(Aresta aresta : this.arestas){

                if(aresta.getDestino() == destino){
                    return aresta;
                }

            }

            return null;
        
        }else{

            return null;

        }

    }

    public boolean visitarVertice(){

        if(!this.visitado){
            this.visitado = true;
            return true;
        }else{
            Util.ImprimiErro("Vértice já visitado");
            return false;
        }


    }

    public void limparVisita(){

        if(this.visitado){
            this.visitado = false;
        }else{
            Util.ImprimiErro("Vértice não foi visitado ainda");
        }

    }

    /* Getters and Setters */

    public int getGrau(){
        return this.arestas.size();
    }

    public boolean getVisitado(){
        return this.visitado;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public List<Aresta> getArestas(){
        return this.arestas;
    }

    public int getTempoDescobrimento(){
        return this.tempoDescobrimento;
    }

    public void setTempoDescobrimento(int tempo){
        this.tempoDescobrimento = tempo;
    }

    public int getTempoTermino(){
        return this.tempoTermino;
    }

    public void setTempoTermino(int tempo){
        this.tempoTermino = tempo;
    }

    public int getPai(){
        return this.pai;
    }

    public void setPai(int id){
        this.pai = id;
    }

    /* Util */

    
    /**
     * @param aresta -> recebe a aresta á ser adicionada
     */
    private void addAresta(Aresta aresta){
        this.arestas.add(aresta);
    }


    /**
     * @param destino -> recebe o destino da aresta a ser removida
     * @param reogarnizar -> recebe a informação de se a lista de arestas precise ser refatorada.
     */
    public void removeAresta(int destino, boolean reogarnizar){

        this.arestas.remove(destino);

        if(reogarnizar && this.arestas.size() > 0){
            this.reogarnizaListaArestas();
        }


    }

    public void removeAresta(Aresta aresta){
        this.arestas.remove(aresta);
    }

    public void imprimiVertice(){

        if(!Util.IsListaArestasVazia(this.arestas)){

            Util.ImprimiMensagemSemQuebraLinha("Vertice (" + this.id + "): ");

            for(int i = 0; i < this.getGrau(); i++){

                if(i == this.getGrau()-1){
                    Util.ImprimiMensagem("{" + this.arestas.get(i).getDestino() + "}");
                }else{
                    Util.ImprimiMensagemSemQuebraLinha("{" + this.arestas.get(i).getDestino() + "}, ");
                }

            }

        }else{

            Util.ImprimiErro("Vértice (" + this.id + "): Vazio");

        }

        

    }

    /* Re-Ogarnização */
    
    public void reogarnizaListaArestas(){

        List<Aresta> novas_arestas = new ArrayList<>();

        for(Aresta aresta: this.arestas){

            int novo_destino = aresta.getDestino() - 1; 

            aresta.setDestino(novo_destino);
            novas_arestas.add(aresta);

        }

        this.arestas = novas_arestas;

    }

}
