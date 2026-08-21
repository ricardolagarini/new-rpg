package New_RPG.Repository;

import New_RPG.Domain.Itens.ItemHeroi;
import New_RPG.Tools.CsvReaderItens;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RepositoryItens {
    private ArrayList<ItemHeroi> itensList;
    public RepositoryItens() throws FileNotFoundException {
        CsvReaderItens csvListReader = new CsvReaderItens();
        this.itensList = csvListReader.readCSVToRepository(); // Lê o arquivo CSV e armazena em itensList
    }

    public ArrayList<ItemHeroi> getItensList() {
        return itensList;
    }


}
