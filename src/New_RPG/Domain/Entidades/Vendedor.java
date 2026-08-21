package New_RPG.Domain.Entidades;

import New_RPG.Domain.Itens.ItemHeroi;
import New_RPG.Repository.RepositoryItens;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Vendedor {
    private ArrayList<ItemHeroi> loja;

    public Vendedor() throws FileNotFoundException {
        RepositoryItens repositoryItens = new RepositoryItens();
        this.loja = repositoryItens.getItensList();
    }

    public ArrayList<ItemHeroi> getLoja() {
        return loja;
    }
}
