package grafos;

import java.util.List;

import uteis.Util;
import vertices.Vertice;

public class GrafoCompleto extends Grafo {

    public GrafoCompleto(){
        super();
    }

    /**
     * @return -> retorna verdadeiro caso o grafo completo tenha sido criado com sucesso
     */
    public Grafo gerarGrafoCompleto(List<Vertice> vertices_completo){

        GrafoCompleto grafo_completo = new GrafoCompleto();

        for(Vertice vertice : vertices_completo){

            grafo_completo.vertices.add(vertice);

        }

        for(Vertice verticeA : vertices_completo){

            for(Vertice verticeB : vertices_completo){

                verticeA.addAresta(verticeB.getID());

            }

        }

        return grafo_completo;
    }

    /**
     *
     * @return -> retorna a ordem de um vertice
     */
    public int ordem(Vertice vertice){
        return (vertice.getArestas().size()) -1;
    }

    @Override
    public boolean existeVertice(int id_vertice){
            if(existeAresta(this.vertices.get(0).getID(), id_vertice)){
                return true;
            }
        return false;
    }

    @Override
    public boolean existeAresta(int verticeA, int verticeB) {
        if (verticeA != verticeB) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Grafo geraSubGrafo(List<Vertice> vertices_sub_grafo) {
       
        GrafoCompleto sub_grafo = new GrafoCompleto();
        
        if(vertices_sub_grafo.size() == 0){

            Util.ImprimiErro("Não possui vértices");
            return null;
        
        }else{

            for(Vertice vertice : vertices_sub_grafo){

                sub_grafo.vertices.add(vertice);

            }

            return sub_grafo;
        }
    }
    
}
