package New_RPG.Tools;

import New_RPG.Domain.Itens.ArmaPrincipal;
import New_RPG.Domain.Itens.ConsumivelCombate;
import New_RPG.Domain.Itens.ItemHeroi;
import New_RPG.Domain.Itens.Pocao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvReaderItens {

    private String filePath;

    public CsvReaderItens() {
        this.filePath = "Ficheiros/itens.csv";
    }

    public ArrayList<ItemHeroi> readCSVToRepository() throws FileNotFoundException {

        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        String linha = scanner.nextLine();

        ArrayList<ItemHeroi> itensList = new ArrayList<>();

        while (scanner.hasNextLine()) { // Lê cada linha do arquivo CSV enquanto houver linhas disponíveis

            linha = scanner.nextLine();
            String[] linhaDividida = linha.split(";");

            String tipo = linhaDividida[0];
            String nome = linhaDividida[1];
            int preco = Integer.parseInt(linhaDividida[2]);

            String heroisPermitidos = linhaDividida[3]; // Separa os tipos de heróis permitidos para este item
            heroisPermitidos=heroisPermitidos.replace("[", "");
            heroisPermitidos=heroisPermitidos.replace("]", "");
            String [] itemClass = heroisPermitidos.split(",");

            int ataque = Integer.parseInt(linhaDividida[4]);
            int ataqueEspecial = Integer.parseInt(linhaDividida[5]);
            int ataqueInstantaneo = Integer.parseInt(linhaDividida[6]);
            int vida = Integer.parseInt(linhaDividida[7]);
            int forca = Integer.parseInt(linhaDividida[8]);

            ItemHeroi novoItemHeroi = null;


            // Cria um novo objeto com base no tipo de item lido no CSV
            if(tipo.equals("ArmaPrincipal")) {
                novoItemHeroi = new ArmaPrincipal(nome, preco, ataque, ataqueEspecial);
            }

            if(tipo.equals("ConsumivelCombate")) {
                novoItemHeroi = new ConsumivelCombate(nome, preco, ataqueInstantaneo);
            }

            if(tipo.equals("Pocao")) {
                novoItemHeroi = new Pocao(nome, preco, vida, forca);
            }

            for (int i = 0; i < itemClass.length; i++) { // Adiciona os tipos de heróis permitidos para o item criado
                novoItemHeroi.addHeroiPermItem(itemClass[i]);
            }

            itensList.add(novoItemHeroi);
        }
        return itensList;
    }




}
