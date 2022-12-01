package grafos;

import java.util.ArrayList;
import java.util.List;

import arestas.Aresta;
import buscas.BuscaEmProfunidade;
import uteis.Util;
import vertices.Vertice;

public abstract class Grafo {
    
    public final String nome = Util.getNomeGrafo();

    protected List<Vertice> vertices;

    private int tempo; 

    /* Construtores */

    private void init(List<Vertice> vertices){
        this.vertices = vertices;
    }

    public Grafo(){
        init(new ArrayList<>());
    }
   
    /* Gettres e Setters */

    public void setVertices(List<Vertice> vertices){
        this.vertices = vertices;
    }

    public List<Vertice> getVertices(){
        return this.vertices;
    }

    /* Métodos abstratos */


    /**
     * @param vertices -> através de uma lista de vértices é criado o sub-grafo
     * @return -> retorna o sub-grafo criado caso seja possível
     */
    public abstract Grafo geraSubGrafo(List<Vertice> vertices);

    /* Métodos */

    /**
     * Esta função só pussi o propósito de imprimir o grafo como uma função auxliar de teste
     */
    public void imprimiGrafoNaoPonderado(){

        for (Vertice vertice : this.vertices) {
            
            vertice.imprimiVertice();

        }

        if(this.vertices.size() == 0){
            Util.ImprimiErro("NULL");
        }

    }


    /**
     * @param destino -> recebe em qual grafo será a origem da aresta analisada
     * @param origem -> recebe em qual grafo será o destino da aresta analisada
     * @return -> retorna true para caso a aresta e exista e false caso não exista
     */
    public boolean existeAresta(int destino, int origem){
        
        for(int i = 0; i < this.vertices.size(); i++){

            if(this.vertices.get(i).getID() == origem){

                if (this.vertices.get(i).existeAresta(destino) != null){
                    return true;
                }else{
                    return false;
                }

            }

        }

        return false;


    }

    /**
     * @param id_vertice -> recebe o id do vértice a ser analisado
     * @return -> retorna true caso o vértice exista e false caso não exista
     */
    public boolean existeVertice(int id_vertice){

        for(Vertice vertice : this.vertices){

            if(vertice.getID() == id_vertice){
                return true;
            }

        }

        return false;

    }

    /**
     * @return -> retorna o número de vértices
     */
    public int ordem(){
        return this.vertices.size(); 
    }

    /**
     * 
     * @return -> retorna a soma do total de vétices com o total de arestass
     */
    public int tamanho(){
        int nrVertice = this.ordem();
        int nrAresta = 0;

        for(int i =0; i < nrVertice; i++){
            nrAresta += this.vertices.get(i).getGrau();
        }
        return nrVertice + nrAresta;
    }

    /** 
     * Método para retornar uma lista na ordem do caminho euleriano
     * @return -> Um List<Vertices>, caso o grafo nao seja euleriano, o método devolde null
     */
    public List<Vertice> caminhoEuleriano(){
        int index;
        int destino;
        int numeroArestas = this.tamanho() - this.ordem();

        List<Vertice> verticesAuxiliares = new ArrayList<Vertice>(this.vertices);
        List<Vertice> caminhoEuleriano = new ArrayList<Vertice>();
        
        Vertice verticeAtual = verticesAuxiliares.get(0);
        Aresta arestaAtual;

        if(this.euleriano()){
            while(numeroArestas != 0){
                caminhoEuleriano.add(verticeAtual);

                if(verticeAtual.getGrau() > 1){
                    index = 0;
                    arestaAtual = verticeAtual.getArestas().get(index);
                    while(this.ehPonte(verticesAuxiliares,verticeAtual,arestaAtual) && verticeAtual.getArestas().size() > 1){
                        index++;
                        arestaAtual = verticeAtual.getArestas().get(index);
                    }
                }else{
                    arestaAtual = verticeAtual.getArestas().get(0);
                }
                destino = arestaAtual.getDestino();
                verticeAtual.removeAresta(arestaAtual);
                verticeAtual = verticesAuxiliares.get(destino);
                numeroArestas--;
            }
        }else{
            return null;
        }

        
        return caminhoEuleriano;
    }

    /**
     * @param vertices lista dos vértices do grafo
     * @param idVertice Id do vertice
     * @param aresta aresta a ser testa
     * @return true se aresta nao for ponte e false se aresta for ponte 
     */
    private boolean ehPonte(List<Vertice> vertices,Vertice vertice,Aresta aresta){

        List<Vertice> listaAuxiliar = new ArrayList<Vertice>(vertices);
        Vertice verticeAuxiliar = listaAuxiliar.get(vertice.getID());
        verticeAuxiliar.removeAresta(aresta.getDestino(), false);

        return this.ehConexo(listaAuxiliar);
    }

    /**
     * O grafo é euleriano sé possível realizar um ciclo passando por todas as arestas
     * @return true se o grafo for euleriano
     */
    public boolean euleriano(){
        //TEM q SER UM GRAFO CONEXO -> Não consegui implementar e testar
        // TODOS OS GRAUS DE VERTICE PAR != 0

        if(this.grauImpares()){
            return false;
        }else{
            if(!this.ehConexo(this.vertices))
                return false;
            else
                return true;
        }
    }

    private boolean grauImpares(){
        boolean existeImpar = false;

        for(int i = 0; i < this.ordem();i++){
            if(this.vertices.get(i).getGrau()%2 != 0){
              existeImpar = true;
            }
        }

        return existeImpar;
    }

    /**
     * @return true -> retorna true se o grafo for conexo
     */
    public boolean ehConexo(List<Vertice> vertices){
        this.limparVisitasArestas(this.vertices);
        this.tempo = 0; 

        for(int i = 0; i < this.ordem();i++){
            
            vertices.get(i).setTempoDescobrimento(0);
            vertices.get(i).setTempoTermino(0);
            vertices.get(i).setPai(-1);
        }

        this.passeio(this.vertices.get(0));
        
        if(this.verticeNaoDescorbeto() != null){
            return false;
        }else{
            return true;
        }
        
        
        
    }

    /**
     * Tenta percorrer o grafo todo a partir de um vértice inicial
     * @param v vertice raiz
     */
    private void passeio(Vertice v) {

        int idVertice;
        this.tempo = this.tempo + 1;
        Vertice  verticeAuxiliar;

        v.setTempoDescobrimento(tempo);

        for(int i =0; i < v.getGrau() ;i++){

            idVertice = v.getArestas().get(i).getDestino();
            verticeAuxiliar = this.vertices.get(idVertice);

            if(verticeAuxiliar.getTempoDescobrimento() == 0){

                v.getArestas().get(i).visitarAresta();
                verticeAuxiliar.setPai(v.getID());
                passeio(verticeAuxiliar);

            }else if(verticeAuxiliar.getTempoTermino() ==0 && verticeAuxiliar.getID() != v.getPai()){
                v.getArestas().get(i).visitarAresta();// aresta de retorno

            }
        }
        this.tempo = this.tempo + 1;
        v.setTempoTermino(tempo);
    }
    
    /**
     * 
     * @return retorna true caso algum valor do vetor for igual a 0 -> null
     */
    private Vertice verticeNaoDescorbeto(){
        for(int i =0 ; i <this.ordem() ;i++){
            if(this.vertices.get(i).getTempoDescobrimento() == 0){
                return this.vertices.get(i);
            }

        }
        return null;
    }

    /**
     * Limpa as visitas de todos as arestas
     * @param vertices - > Lista de vertices que terão as visitas das arestas apagadas
     */
    private void limparVisitasArestas(List<Vertice> vertices){
        int numeroArestas;
        Aresta aresta;
        for( int i = 0; i < this.ordem();i++){
            numeroArestas = this.vertices.get(i).getArestas().size();
            for(int j = 0; j < numeroArestas ;j++){
               aresta =  this.vertices.get(i).getArestas().get(j);
               aresta.limparVisita();
            }
        }
    }

    

    /**
     *
     * @return -> retorna verdadeiro se todos os vértices do grafo forem adjacentes (grafo completo)
     * e falso caso não sejam
     */
    public boolean completo() {
        for (int i = 0; i < this.vertices.size(); i++) {
            for (int j = 0; j < this.vertices.size(); j++) {
                if (!existeAresta(this.vertices.get(i).getID(), this.vertices.get(j).getID()) && this.vertices.get(i).getID()!=this.vertices.get(j).getID()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void BuscaProfunidade(int vertice_inicio){

        this.tempo = 0;

        boolean achou_aresta = false;

        BuscaEmProfunidade busca = new BuscaEmProfunidade(); 

        busca.registroGeral(vertice_inicio, tempo, -1, -1);

        this.tempo++;

        Vertice vertice_anterior = null;
        Vertice vertice_atual = this.vertices.get(vertice_inicio);
        Vertice vertice_mais_profundo = this.vertices.get(this.vertices.size()-1);
        Vertice vertice_mais_profundo_do_vertice = null;
        int quantidade_arestas_passadas = 0;

        while(!vertice_mais_profundo.getVisitado()){

            for(Aresta aresta : vertice_atual.getArestas()){

                if(!aresta.getVisitada() && !achou_aresta){

                    aresta.visitarAresta();
                    achou_aresta = true;

                    vertice_anterior = vertice_atual;
                    vertice_atual = this.vertices.get(aresta.getDestino());
                    
                    for(Aresta aresta2 : vertice_atual.getArestas()){
                        if(aresta2.getDestino() == vertice_anterior.getID()){
                            aresta2.visitarAresta();
                        }
                    }

                    if(!busca.getIdVertice().contains(vertice_atual.getID())){
                        busca.registroGeral(vertice_atual.getID(), 
                        this.tempo, -1, vertice_anterior.getID());
                    }

                    break;
                }

                vertice_mais_profundo_do_vertice = this.vertices.get(aresta.getDestino());
            }

            if(achou_aresta){

                for(Aresta aresta : vertice_anterior.getArestas()){

                    if(aresta.getVisitada()){
                        quantidade_arestas_passadas++;
                    }
                }

                if(quantidade_arestas_passadas == vertice_anterior.getGrau()){
                    boolean visita = vertice_anterior.visitarVertice();
                    
                    if(visita){
                        busca.alteraTempoVisitado(vertice_anterior.getID(), tempo);
                    }
                }
            }else{

                boolean visita = vertice_atual.visitarVertice();  
                
                if(visita){
                    busca.alteraTempoVisitado(vertice_atual.getID(), tempo);
                }
                
                vertice_atual = vertice_mais_profundo_do_vertice;

            }
            
            if(vertice_mais_profundo.getVisitado()){
                
                vertice_atual.visitarVertice();
                busca.alteraTempoVisitado(vertice_atual.getID(), tempo);


            }
            
            quantidade_arestas_passadas = 0;
            achou_aresta = false;
            this.tempo++;



            
        }

        

        busca.imprimiBusca();

    }

    /**
     * @return -> retorna o caminho entre vertices
     */
    public List<Vertice> caminhar(Vertice a, Vertice b) {
        this.tempo = this.tempo + 1;
        Vertice  verticeAuxiliar;
        List<Vertice> caminho = new ArrayList<Vertice>();

        a.setTempoDescobrimento(tempo);
        caminho.add(a);

        for(int i =0; i < a.getGrau() ;i++){
            verticeAuxiliar = a;

            if(verticeAuxiliar.getTempoDescobrimento() == 0){

                a.getArestas().get(i).visitarAresta();
                verticeAuxiliar.setPai(a.getID());
                caminho.add(verticeAuxiliar);
                caminhar(verticeAuxiliar, b);

            }else if(verticeAuxiliar.getTempoTermino() ==0 && verticeAuxiliar.getID() != b.getID()){
                a.getArestas().get(i).visitarAresta();// aresta de retorno
            }
        }
        this.tempo = this.tempo + 1;
        a.setTempoTermino(tempo);

        caminho.add(b);
        return caminho;
    }
}

