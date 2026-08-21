package New_RPG.View;

import New_RPG.Domain.Entidades.Heroi;
import New_RPG.Jogo.Jogo;
import java.io.FileNotFoundException;
import static New_RPG.Jogo.Jogo.reinoNorte;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Jogo jogo = new Jogo();

        Heroi heroi = jogo.criarPersonagem();
        reinoNorte(heroi);

    }
}


