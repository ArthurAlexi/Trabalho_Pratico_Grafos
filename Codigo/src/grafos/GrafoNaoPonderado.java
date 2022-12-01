package grafos;

import java.util.List;

import uteis.Util;
import vertices.Vertice;

public class GrafoNaoPonderado extends GrafoMutavel {

    public GrafoNaoPonderado() {
        super();
    }

    /**
     * @param origem  -> recebe em qual grafo será a origem da aresta
     * @param destino -> recebe em qual grafo será o destino da aresta
     * @return -> retorna true para caso não exista uma aresta com origem e destino
     *         igual dos paramêtros
     *         e true, para caso a aresta seja adicionada com sucesso
     */
    @Override
    public boolean addAresta(int origem, int destino) {

        if (this.vertices.size() == 0) {
            Util.ImprimiErro("Grafo não possui vértices suficientes");
            return false;
        }

        for (int i = 0; i < this.vertices.size(); i++) {

            if (this.vertices.get(i).getID() == origem) {
                this.vertices.get(i).addAresta(destino);
                return true;
            }

        }

        return false;

    }

    @Override
    public Grafo geraSubGrafo(List<Vertice> vertices_sub_grafo) {

        GrafoNaoPonderado sub_grafo = new GrafoNaoPonderado();

        if (vertices_sub_grafo.size() == 0) {

            Util.ImprimiErro("Não possui vértices");
            return null;

        } else {

            for (Vertice vertice : vertices_sub_grafo) {

                sub_grafo.addVertice(vertice);

            }

            return sub_grafo;
        }
    }

}
