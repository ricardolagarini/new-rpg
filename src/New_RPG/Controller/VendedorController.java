package New_RPG.Controller;

import New_RPG.Controller.EstrategiaAtaque.Arqueiro;
import New_RPG.Controller.EstrategiaAtaque.Cavaleiro;
import New_RPG.Controller.EstrategiaAtaque.Feiticeiro;
import New_RPG.Domain.Entidades.Heroi;
import New_RPG.Domain.Entidades.Vendedor;
import New_RPG.Domain.Itens.ArmaPrincipal;
import New_RPG.Domain.Itens.Consumivel;
import New_RPG.Domain.Itens.ItemHeroi;
import New_RPG.Repository.RepositoryItens;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VendedorController {

    public VendedorController() {
    }

    public ArrayList<ItemHeroi> criarLoja(Vendedor vendedor) {
        ArrayList<ItemHeroi> lojaSuspeita = new ArrayList<>(10);
        ArrayList<Integer> itemAleatorio = new ArrayList<>(10);
        Random random = new Random();

        if(vendedor.getLoja().size() >=10) {
            while (itemAleatorio.size() < 10) {
                int tempRandom = random.nextInt(0, vendedor.getLoja().size());
                if (!itemAleatorio.contains(tempRandom)) {
                    itemAleatorio.add(tempRandom);
                    lojaSuspeita.add(vendedor.getLoja().get(tempRandom));
                }
            }
        }else {
            while (itemAleatorio.size() < vendedor.getLoja().size()) {
                int tempRandom = random.nextInt(0, vendedor.getLoja().size());
                if (!itemAleatorio.contains(tempRandom)) {
                    itemAleatorio.add(tempRandom);
                    lojaSuspeita.add(vendedor.getLoja().get(tempRandom));
                }
            }
        }
        return lojaSuspeita;
    }

    public void imprimirLoja(ArrayList<ItemHeroi> lojaSuspeita) {
        int cont=1;

        for (ItemHeroi itemHeroiAtual : lojaSuspeita) {
            System.out.print(cont++ +" - ");
            itemHeroiAtual.mostrarDetalhes();
        }
    }

    public void vender(Vendedor vendedor, Heroi heroi, Scanner input) {

        ArrayList<ItemHeroi> lojaSuspeita = criarLoja(vendedor);

        boolean continuarComprando = true;
        String classHeroi = null;

        if (heroi.getEstrategiaAtaque() instanceof Arqueiro) {
            classHeroi = "Arqueiro";
        }else if (heroi.getEstrategiaAtaque() instanceof Cavaleiro) {
            classHeroi = "Cavaleiro";
        }else if (heroi.getEstrategiaAtaque() instanceof Feiticeiro) {
            classHeroi = "Feiticeiro";
        }

        System.out.println("------------------------------------------------------------------------------------------\n" +
                " _                 _             ____                                 _   _              |\n" +
                " | |       ___     (_)   __ _    / ___|   _   _   ___   _ __     ___  (_) | |_    __ _   |\n" +
                " | |      / _ \\    | |  / _` |   \\___ \\  | | | | / __| | '_ \\   / _ \\ | | | __|  / _` |  |   \n" +
                " | |___  | (_) |   | | | (_| |    ___) | | |_| | \\__ \\ | |_) | |  __/ | | | |_  | (_| |  |\n" +
                " |_____|  \\___/   _/ |  \\__,_|   |____/   \\__,_| |___/ | .__/   \\___| |_|  \\__|  \\__,_|  |\n" +
                "                 |__/                                  |_|                               |\n" +
                "------------------------------------------------------------------------------------------\n");


        while (continuarComprando) {
            imprimirLoja(lojaSuspeita);

            System.out.println("\nSaldo em ouro: "+heroi.getOuro());

            System.out.print("\nQual item deseja comprar? (digite [0] para sair) : ");
            int itemCompra = input.nextInt();

            if (itemCompra == 0) {
                break;
            }

            if (itemCompra < 0 || itemCompra > lojaSuspeita.size()) {
                System.out.println("\n[!] - Opção inválida. Tente novamente. - [!]\n");
                continue;
            }

            ItemHeroi itemEscolha = lojaSuspeita.get(itemCompra - 1);

            if (heroi.getOuro() >= itemEscolha.getPreco()) {
                if (itemEscolha instanceof ArmaPrincipal) {
                    if(itemEscolha.getHeroisPermitidos().contains(classHeroi)) {
                        heroi.setArmaPrincipal((ArmaPrincipal) itemEscolha);

                        System.out.println("\nCompra realizada com sucesso!" + " " + "[" + itemEscolha.getNome() + "]" + " equipado ao " + heroi.getNome() + ".");

                        int ouroAtual = heroi.getOuro() - itemEscolha.getPreco();
                        heroi.setOuro(ouroAtual);
                        lojaSuspeita.remove(itemEscolha);
                        vendedor.getLoja().remove(itemEscolha);

                        System.out.println("\nSeu saldo em ouro é de: " + heroi.getOuro());

                    }else {
                        System.out.println("\n[!] - " +heroi.getNome()+" não pode usar este equipamento! - [!]");
                    }

                } else {
                    if(itemEscolha.getHeroisPermitidos().contains(classHeroi)) {
                        int ouroAtual = heroi.getOuro() - itemEscolha.getPreco();
                        heroi.setOuro(ouroAtual);
                        lojaSuspeita.remove(itemEscolha);
                        vendedor.getLoja().remove(itemEscolha);
                        System.out.println("\nCompra realizada com sucesso!"+ " " +"["+itemEscolha.getNome()+"]" + " adicionado ao inventário.");
                        heroi.addItensInventario((Consumivel) itemEscolha);
                        System.out.println("\nSeu saldo em ouro é de: " + heroi.getOuro());
                    }else {
                        System.out.println("\n[!] - " +heroi.getNome()+" não pode usar este item! - [!]");
                    }
                }
                if (heroi.getOuro() > 0) {
                    int escVend;
                    do {
                        System.out.print("\nQuer continuar comprando? Sim [1], Não [2]: ");
                        escVend = input.nextInt();
                        if (escVend != 1 && escVend != 2) {
                            System.out.println("\n[!] - Opção inválida. Digite 1 ou 2. - [!]");
                        }
                    } while (escVend != 1 && escVend != 2);
                    if (escVend == 2) {
                        continuarComprando = false;
                    }
                }
            } else {
                System.out.println("\n[!] - Você não tem ouro suficiente para comprar " +"["+itemEscolha.getNome()+"]" +" - [!]\n");
            }
        }
    }

}
