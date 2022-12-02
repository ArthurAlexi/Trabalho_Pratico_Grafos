import java.io.FileNotFoundException;
import grafos.GrafoNaoPonderado;
import grafos.GrafoPonderado;

//import grafos.GrafoNaoPonderado;

public class App {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        // GrafoNaoPonderado gnp = new GrafoNaoPonderado();

        // gnp.carregarJSON("Codigo\\resources\\grafoJson.json");

        // gnp.imprimiGrafoNaoPonderado();
        

        GrafoPonderado gp = new GrafoPonderado();
        try {
            //Cria os vérices sem nenhuma aresta
            gp.carregarJSON("Codigo\\resources\\br.json");
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encotrado");
        }
        ;
    }
    
}
