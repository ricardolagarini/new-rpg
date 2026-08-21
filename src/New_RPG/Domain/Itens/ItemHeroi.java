package New_RPG.Domain.Itens;

import New_RPG.Domain.Entidades.Heroi;
import java.util.ArrayList;

public abstract class ItemHeroi {
    private String nome;
    private int preco;
    private ArrayList<String> heroisPermitidos;

    public ItemHeroi(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = new ArrayList<>();
    }

    public void mostrarDetalhes(){
        System.out.print(this.nome+" - "+"Preço: " + this.preco+"$");
    }

    public ArrayList<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }

    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public void addHeroiPermItem (String classePermitda) {
        heroisPermitidos.add(classePermitda);
    }

    public void armaDetStatus() {
        System.out.println(this.nome);
    }

}
