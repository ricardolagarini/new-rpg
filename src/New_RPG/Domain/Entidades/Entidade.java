package New_RPG.Domain.Entidades;

public abstract class Entidade {

    private String nome;
    private int maxHp;
    private int hp ;
    private int forca;

    public Entidade(String nome, int maxHp, int hp, int forca) {
        this.nome = nome;
        this.maxHp = maxHp;
        this.hp = hp;
        this.forca = forca;
    }

    public int getHp() { return hp;}
    public int getMaxHp() {return maxHp;}

    public String getNome() { return nome;}
    public int getForca() { return forca;}

    public void setNome(String nome) {this.nome = nome;}
    public void setMaxHp(int maxHp) {this.maxHp = maxHp;}
    public void setHp(int hp) {this.hp = hp;}
    public void setForca(int forca) {this.forca = forca;}


    public void mostrarDetalhes(){
        System.out.println( "Nome: " + this.nome);
        System.out.println( "HP" + this.hp+ "/" +this.maxHp);
        System.out.println( "Força: " + this.forca);
    }


    public boolean estaVivo() {
        return hp > 0;
    }



}
