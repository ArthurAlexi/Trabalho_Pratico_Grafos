import java.io.FileNotFoundException;
import grafos.GrafoNaoPonderado;

//import grafos.GrafoNaoPonderado;

public class App {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        GrafoNaoPonderado gnp = new GrafoNaoPonderado();

        gnp.carregarJSON("Codigo\\resources\\grafoJson.json");
        
    }
    
}
