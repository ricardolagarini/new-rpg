package New_RPG.Domain.Itens;

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int preco, int ataqueInstantaneo) {
        super(nome, preco);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }
    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println(" ( "+"Dano: "+ this.ataqueInstantaneo +" | Classe: "+getHeroisPermitidos()+" )");
    }

}
