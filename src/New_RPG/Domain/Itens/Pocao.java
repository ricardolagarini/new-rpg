package New_RPG.Domain.Itens;

public class Pocao extends Consumivel{
    private int vida;  // vida a curar
    private int forca; // força a aumentar

    public Pocao(String nome, int preco, int vida, int forca) {
        super(nome, preco);
        this.vida = vida;
        this.forca = forca;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println(" ( "+"Poder de cura: "+ this.vida+ " |"+" Força: +"+ this.forca+" |"+" Classe: "+getHeroisPermitidos()+" )");;
    }

    public int getVidaCurar() {
        return vida;
    }

    public int getAumentoForca() {
        return forca;
    }
}
