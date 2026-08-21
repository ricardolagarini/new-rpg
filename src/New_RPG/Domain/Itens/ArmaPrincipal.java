package New_RPG.Domain.Itens;

public class ArmaPrincipal extends ItemHeroi {

    private int ataque;
    private int ataqueEspecial;

    public ArmaPrincipal(String nome, int preco, int ataque, int ataqueEspecial) {
        super(nome, preco);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getAtaque() {return ataque;}
    public int getAtaqueEspecial() {return ataqueEspecial;}

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println(" ( "+"Ataque: " + this.ataque+ " | "+ "Classe: "+getHeroisPermitidos()+") ");
    }


}
