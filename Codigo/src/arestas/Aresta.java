package arestas;
import uteis.Util;

public class Aresta{

    private int destino;
    private boolean visitada;
    private double peso;

    private void init(int destino, boolean visitada, double peso){

        this.destino = destino;
        this.visitada = visitada;
        this.peso = peso;
    
    }

    public Aresta(){
        init(-1, false, 0);
    }

    public Aresta(int destino){
        init(destino, false, peso);
    }

    public Aresta(int destino, double peso){
        init(destino, false, peso);
    }

    public int getDestino(){
        return this.destino;
    }

    public double getPeso(){
        return this.peso;
    }

    public void setDestino(int destino){
        this.destino = destino;
    }

    public boolean getVisitada(){
        return this.visitada;
    }

    public void limparVisita(){

        if(this.visitada){
            this.visitada = false;
        }else{
            Util.ImprimiMensagem("Essa aresta não foi visitada ainda");
        }

    }

    public void visitarAresta(){

        if(!this.visitada){
            this.visitada = true;
        }else{
            Util.ImprimiMensagem("Essa aresta já foi visitada");
        }

    }

    


}