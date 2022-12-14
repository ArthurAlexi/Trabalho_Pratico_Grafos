package grafos;


import uteis.Util;
import vertices.Vertice;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class GrafoMutavel extends Grafo {
    
    /* Construtores */
    
    public GrafoMutavel(){
        super();
    }

    /* Métodos abstratos */

    public abstract boolean addAresta(int origem, int destino);

    /* Métodos */

      /**
     * @param path -> o método recebe o caminho do arquivo, localizado em resources.
     * A função principal do método é que atraves de uma matriz de adjacência é obtido os dados referente
     *  ao grafo assim, carregando suas informações para a classe.
     */
    @Deprecated
    public boolean carregarGrafo(String path){

        File arquivo = new File(path);

        try(FileReader fileReader = new FileReader(arquivo)){

            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                
                String linha;
                int linha_referencia = 0;

                linha = bufferedReader.readLine();

                String divisao_matriz[] = linha.split("-");

                for(int i = 0; i < divisao_matriz.length; i++){
                    
                    this.addVertice(new Vertice());

                }


                while(linha != null){

                    divisao_matriz = linha.split("-");

                    for(int i = 0; i < divisao_matriz.length; i++){

                        if(divisao_matriz[i].equals("1")){

                            this.addAresta(i, linha_referencia);

                        }
    
                    }
                    
                    linha_referencia++;
                    linha = bufferedReader.readLine();

                }

                bufferedReader.close();

            } catch (Exception e) {
                return false;
            }

            fileReader.close();
            
            return true;
            
        }catch(Exception e){
            return false;
        }

    } 

    public void carregarJSON(String path) throws FileNotFoundException{

        HashMap<String, String> hash = new HashMap<>();

        File file = new File(path);

        if(file != null){

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()){

                String linha = scanner.nextLine();

                String[] valores = linha.split(":");

                if(valores.length == 2){

                    String key = valores[0].replaceAll("\"", "").replaceAll(" ", "");
                    System.out.print(key + ": ");
                    String value = valores[1].replaceAll("\"", "").replaceAll(" ", "");
                    System.out.println(value);

                    if(key.equals("Vertices")){

                        String[] values = value.split(",");

                        for(int i = 0; i < values.length - 1; i++){
                            this.addVertice(new Vertice());
                        }

                    }

                    if(key.equals("Arestas")){

                        String[] values = value.split(",");

                        for(int i = 0; i < values.length; i++){

                            String origem = values[i].split("-")[0];
                            String destino = values[i].split("-")[1];

                            vertices.get(Integer.parseInt(origem)).addAresta(Integer.parseInt(destino));

                        }

                    }

                }
            }
        }

    }

    /**
     * @param novo -> recebe o vértice a ser adicionado
     * @return -> retorna falso caso o vértice já exista no grafo e true para caso não exista
     */
    public boolean addVertice(Vertice novo){

        for(Vertice vertice_analisar : this.vertices){

            if(vertice_analisar.equals(novo)){
                Util.ImprimiErro("Vértice já existe em grafo");
                return false;
            }

        }

        this.vertices.add(novo);
        return true;

    } 


    /**
     * @param origem -> recebe a origem da aresta a ser deletada
     * @param destino -> recebe o destino da aresta a ser deletada
     * @return -> retorna true para caso a aresta seja deletada e false para caso ocorra algum erro.
     */
    public boolean deletaAresta(int origem, int destino){

        if(this.existeAresta(destino, origem)){

            this.vertices.get(origem).removeAresta(destino, false);
            return true;

        }

        return false;

    }


    /**
     * @param id_vertice -> recebe o id do vértice a ser deletado 
     * @return -> retorna true para caso o vértice seja deletado e false para caso ocorra algum erro.
     */
    public boolean deletaVertice(int id_vertice){
        
        if(this.existeVertice(id_vertice)){

            this.vertices.remove(id_vertice);
            reogarnizaListaVertices(id_vertice);
            return true;
        }
        
        return false;

    }

    public void salvarArquivo(String nome_arquivo){

    }

    /* Overrides */

    @Override
    public boolean existeAresta(int destino, int origem) {
        return super.existeAresta(destino, origem);
    }

    @Override
    public boolean existeVertice(int id_vertice) {
        return super.existeVertice(id_vertice);
    }

    /* Re-Organização */

 
    /**
     * Este método reogarniza a lista dos vértices caso um deles seja deletado, fazendo com que seja refatorado o id e posição vértices
     * na lista. 
     * @param id_vertice -> recebe o id do vértice a ser reogarnizado
     */
    private void reogarnizaListaVertices(int id_vertice){

        List<Vertice> novo_vertices = new ArrayList<>();

        for(Vertice vertice : this.vertices){

            int novo_id = vertice.getID() - 1;

            Vertice novo_vertice = vertice;
            novo_vertice.setID(novo_id);
            Util.ID--;

            novo_vertices.add(novo_vertice);

            vertice.removeAresta(id_vertice, true);

        }

        this.setVertices(novo_vertices);

    }

}
