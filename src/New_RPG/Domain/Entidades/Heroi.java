package New_RPG.Domain.Entidades;

import New_RPG.Controller.EstrategiaAtaque.EstrategiaAtaque;
import New_RPG.Domain.Itens.*;
import New_RPG.Tools.Audio;
import java.util.ArrayList;

public class Heroi extends Entidade {

    private int nivel =1;
    private int ouro=0;
    private ArmaPrincipal armaPrincipal;
    private ArrayList<Consumivel> inventario;
    private EstrategiaAtaque estrategiaAtaque;
    private boolean fim = false;

    public Heroi(String nome, int maxHp, int hp, int forca, int ouro, ArmaPrincipal armaPrincipal, EstrategiaAtaque estrategiaAtaque) {
        super(nome, maxHp, hp, forca);
        this.ouro = ouro;
        this.armaPrincipal = armaPrincipal;
        this.inventario = new ArrayList<>();
        this.estrategiaAtaque = estrategiaAtaque;
    }

    //~~GETTERS E SETTERS~~//
    public int getNivel() {return nivel; }
    public void setNivel(int nivel) {this.nivel = nivel;}
    public int getOuro() {return ouro;}
    public ArmaPrincipal getArmaPrincipal() {return armaPrincipal;}
    public ArrayList<Consumivel> getInventario() {return inventario;}
    public void setOuro(int ouro) { this.ouro = ouro;}
    public void setArmaPrincipal(ArmaPrincipal armaPrincipal) { this.armaPrincipal = armaPrincipal; }
    public EstrategiaAtaque getEstrategiaAtaque() {return estrategiaAtaque;}
    public void setEstrategiaAtaque(EstrategiaAtaque estrategiaAtaque) { this.estrategiaAtaque = estrategiaAtaque; }
    public boolean isFim() {return fim;}
    public void setFim(boolean fim) {this.fim = fim;}

    // Metodo retorna adicionar itens ao inventario do heroi
    public void addItensInventario(Consumivel novoItem){inventario.add(novoItem);} // Metodo para add itens no inventario

    // Metodo retorna exibir status do heroi
    public void exibirStatus(){
        System.out.println("");
        System.out.println("»»———————⤷ ⚜ ⤶———————««");
        System.out.println("         Status");
        System.out.println("»»————————————————————««");
        System.out.println("Nome: "+this.getNome());
        System.out.println("Nivel: "+this.getNivel());
        System.out.println("HP: "+this.getHp() + "/" + this.getMaxHp());
        System.out.println("Força: "+this.getForca());
        System.out.println("Ouro: "+this.getOuro()+" $");
        System.out.print("Arma equipada: ");this.getArmaPrincipal().armaDetStatus();
        System.out.println("");
    }

    // Metodo retorna exibir a lista de consumiveis de combate no inventario
    public static ArrayList<Integer> exibirConsumiveisCombate(Heroi heroi){
        ArrayList<Integer> consumiveis = new ArrayList<>();

        System.out.println("\n                                        »»———————————————⤷ ☢ ⤶—————————————««");
        System.out.println("                                              Menu Consumiveis de Combate");
        System.out.println("                                        »»———————————————————————————————————««\n");

        int contador = 1;

        for (Consumivel consumivelAtual : heroi.getInventario()){

            if (consumivelAtual instanceof ConsumivelCombate){// verifica no ficheiro o tipo do consumível.
                System.out.print("["+contador++ +"]"+" - ");
                consumivelAtual.mostrarDetalhes();
                consumiveis.add(heroi.getInventario().indexOf(consumivelAtual));
            }
        }
        return consumiveis;
    }

    // Metodo retorna exibir a lista de consumiveis de cura no inventario
    public static ArrayList<Integer> exibirConsumiveisPocao(Heroi heroi){
        ArrayList<Integer> consumiveis = new ArrayList<>();

        System.out.println("\n                                        »»———————————⤷ ♡ ⤶—————————««");
        System.out.println("                                                 Menu de Poções");
        System.out.println("                                        »»———————————————————————————««\n");

        int contador = 1;

        for (Consumivel consumivelAtual : heroi.getInventario()){

            if (consumivelAtual instanceof Pocao){// verifica no ficheiro o tipo do consumível.
                System.out.print("["+contador++ +"]"+" - ");
                consumivelAtual.mostrarDetalhes();
                consumiveis.add(heroi.getInventario().indexOf(consumivelAtual));
            }
        }
        return consumiveis;
    }

    // Metodo retorna subir o nivel do heroi
    public void subirNivel() {
        System.out.println("");
        System.out.println(this.getNome()+" subiu de nivel!");
        this.nivel+=1;
        this.setMaxHp(this.getMaxHp()+200);
        this.setForca(this.getForca()+50);
        System.out.println("Nivel: "+this.getNivel());
        System.out.println("Hp Máximo: "+this.getMaxHp());
        System.out.println("Força: "+this.getForca());
    }

    // Metodo retorna o menu Game Over apos o jogador morrer
    public void morrer() {
        this.setHp(0);

        System.out.println("");
        System.out.println(this.getNome() + " morreu!!!");
        System.out.println("                                    \n" +
                "███▀▀▀██ ███▀▀▀███ ███▀█▄█▀███ ██▀▀▀" + "   ███▀▀▀███ ▀███  ██▀ ██▀▀▀ ██▀▀▀▀██▄\n" +
                "██    ██ ██     ██ ██   █   ██ ██   " + "   ██     ██   ██  ██  ██    ██     ██\n" +
                "██   ▄▄▄ ██▄▄▄▄▄██ ██   ▀   ██ ██▀▀▀" + "   ██     ██   ██  ██  ██▀▀▀ ██▄▄▄▄▄▀▀\n" +
                "██    ██ ██     ██ ██       ██ ██   " + "   ██     ██   ██  █▀  ██    ██     ██\n" +
                "███▄▄▄██ ██     ██ ██       ██ ██▄▄▄" + "   ███▄▄▄███    ▀█▀    ██▄▄▄ ██     ██▄");

        System.out.println("");


    }
}
