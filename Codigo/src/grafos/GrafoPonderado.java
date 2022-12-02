package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import uteis.Util;
import vertices.Vertice;

public class GrafoPonderado extends GrafoMutavel {

    @Override
    public boolean addAresta(int origem, int destino) {
        Vertice v1 = this.vertices.get(origem);
        Vertice v2 = this.vertices.get(destino);
        return v1.addArestaComPeso(destino, this.calcularDistancias(v1, v2));
    }

    public boolean addAresta(int origem, int destino, double peso) {

        Vertice vertice = this.vertices.get(origem);
        return vertice.addArestaComPeso(destino, peso);
    }

    @Override
    public Grafo geraSubGrafo(List<Vertice> vertices_sub_grafo) {

        GrafoPonderado sub_grafo = new GrafoPonderado();

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

    /**
     * 
     * @param v1 vertice de origem
     * @param v2 vertice de destino
     * @return double -> distância em Km
     */
    public double calcularDistancias(Vertice v1, Vertice v2) {

        final int RAIO = 6371;

        // Conversão de graus pra radianos das latitudes
        double firstLatToRad = Math.toRadians(v1.getLatitude());
        double secondLatToRad = Math.toRadians(v2.getLongitude());

        // Diferença das longitudes
        double deltaLongitudeInRad = Math.toRadians(v2.getLongitude() - v1.getLongitude());

        // Cálcula da distância entre os pontos
        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad)
                + Math.sin(firstLatToRad)
                        * Math.sin(secondLatToRad))
                * RAIO;
    }

    @Override
    public void carregarJSON(String path) throws FileNotFoundException {

        HashMap<String, String> hash = new HashMap<>();

        File file = new File(path);

        if (file != null) {

            Scanner scanner = new Scanner(file);
            String linha = scanner.nextLine();
            String[] dadosTratados = new String[3];
            while (scanner.hasNext()) {

                linha = scanner.nextLine();
                // if(linha != "]"){
                // System.out.println("1 linha --->"+linha);
                // }

                if ((linha.contains("{"))) {
                    // System.out.println("Chave Aberta");
                } else if (linha.contains("},") || linha.contains("}")) {
                    Vertice novoVertice = new Vertice(dadosTratados[0], Double.parseDouble(dadosTratados[1]),
                            Double.parseDouble(dadosTratados[2]));
                    this.vertices.add(novoVertice);
                } else {
                    String[] dados = linha.split(":");
                    if (dados[0].contains("city")) {
                        dadosTratados[0] = dados[1].split(",")[0];

                    } else if (dados[0].contains("lat") && !dados[0].contains("population")) {
                        dados[1] = dados[1].split(",")[0];
                        dadosTratados[1] = dados[1].substring(2, dados[1].length() - 1);

                    } else if (dados[0].contains("lng")) {
                        dados[1] = dados[1].split(",")[0];
                        dadosTratados[2] = dados[1].substring(2, dados[1].length() - 1);

                    }

                }

            }
        }

    }

}
