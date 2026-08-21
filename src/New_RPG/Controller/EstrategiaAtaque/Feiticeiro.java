package New_RPG.Controller.EstrategiaAtaque;

import New_RPG.Domain.Entidades.Entidade;
import New_RPG.Domain.Entidades.Heroi;
import New_RPG.Domain.Entidades.NPC;
import New_RPG.Domain.Itens.ConsumivelCombate;
import New_RPG.Domain.Itens.Pocao;
import New_RPG.Jogo.Jogo;
import New_RPG.Tools.Audio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static New_RPG.Domain.Entidades.Heroi.exibirConsumiveisCombate;
import static New_RPG.Domain.Entidades.Heroi.exibirConsumiveisPocao;
import static New_RPG.Jogo.Jogo.reinoNorte;
import static New_RPG.Tools.Audio.playAudio;

public class Feiticeiro implements EstrategiaAtaque {
    @Override
    public Entidade atacar(Heroi heroi, NPC npc) throws FileNotFoundException {
        boolean especialUsado =false;

        Audio.playMusica("Ficheiros/luta.wav");

        do {
            Scanner input = new Scanner(System.in);

            System.out.println("»»——————————⤷ ♡ ⤶——————————««");
            System.out.println(" "+npc.getNome()+" : HP"+npc.getHp()+"/"+npc.getMaxHp());
            System.out.println(" "+heroi.getNome()+" : HP"+heroi.getHp()+"/"+heroi.getMaxHp());
            System.out.println("");

            System.out.println("»»—————————⤷ ⚔ ⤶—————————««");
            System.out.println(" Escolha o tipo de ataque ");
            System.out.println("»»———————————————————————««");
            System.out.println("[1] - Ataque normal");
            System.out.println("[2] - Ataque especial");
            System.out.println("[3] - Ataque consumível");
            System.out.println("[4] - Usar poção");
            System.out.print("Qual ação quer executar?: ");
            int tipoDeAtaque = input.nextInt();
            int danoHeroi=0;
            int danoNPC = (int) (0.1 * npc.getForca()) + npc.getForca(); // Ataca primeiro mas leva 10% a mais de dano
            boolean critical = true;

            switch (tipoDeAtaque) {
                case 1:
                    Random random = new Random();
                    int chance = random.nextInt(10) + 1; // Número aleatório entre 1 e 10

                    if (chance <= 2) { // Chance de 20% de ataque critical
                        danoHeroi = (int) ((heroi.getForca() + heroi.getArmaPrincipal().getAtaque())*1.3);

                    } else {
                        danoHeroi = heroi.getForca() + heroi.getArmaPrincipal().getAtaque();
                        critical = false;
                    }
                    break;
                case 2:
                    if (!especialUsado) {
                        danoHeroi = heroi.getForca() + heroi.getArmaPrincipal().getAtaqueEspecial();
                        especialUsado = true;
                        critical = false;
                    }else {
                        System.out.println("");
                        System.out.println("[!] - Ataque Especial não pode ser usado! - [!]");
                        System.out.println("[!] - O limite é apenas uma vez por batalha - [!]");
                        System.out.println("");
                    }
                    break;
                case 3:
                    ArrayList<Integer> consTemp= exibirConsumiveisCombate(heroi);

                    String classHeroi = null;

                    if (heroi.getEstrategiaAtaque() instanceof Feiticeiro) {
                        classHeroi = "Feiticeiro";
                    }

                    if(!consTemp.isEmpty()) {
                        System.out.print("\nEscolha o consumível:");
                        int consumivel = input.nextInt();

                        if (consumivel >0 && consumivel <= consTemp.size()) {

                            ConsumivelCombate itemConsCombate = (ConsumivelCombate) heroi.getInventario().get(consumivel - 1);

                            if (itemConsCombate.getHeroisPermitidos().contains(classHeroi)) {
                                danoHeroi = itemConsCombate.getAtaqueInstantaneo();
                                heroi.getInventario().remove(itemConsCombate);

                            } else {
                                System.out.println("\n[!] - Consumível inválido. Escolha novamente. - [!]\n");
                            }
                        } else {
                            System.out.println("\n[!] - Consumível inválido. Escolha novamente. - [!]\n");
                        }
                    } else {
                        System.out.println("\n[!] - Não há itens para usar! - [!]\n");
                    }
                    break;
                case 4:
                    ArrayList<Integer> invenTemp= exibirConsumiveisPocao(heroi);
                    if (!invenTemp.isEmpty()) {
                        System.out.print("\nEscolha a poção:");
                        int consumivelPocao = input.nextInt();
                        int indiceSelecionado = consumivelPocao - 1;

                        if (indiceSelecionado >= 0 && indiceSelecionado < invenTemp.size()) {
                            Pocao pocao = (Pocao) heroi.getInventario().get(invenTemp.get(indiceSelecionado));
                            int cura = pocao.getVidaCurar() + heroi.getHp();
                            int novaForca = pocao.getAumentoForca() + heroi.getForca();
                            int diferencaVida = heroi.getMaxHp() - heroi.getHp(); // Verificar se a cura excede a diferença entre a vida máxima e a vida atual

                            heroi.setForca(novaForca);

                            playAudio("Ficheiros/heal.wav");

                            if (cura > heroi.getMaxHp()) {
                                heroi.setHp(heroi.getMaxHp()); // Ajustar para a vida máxima
                                System.out.println("\n["+pocao.getNome()+"] utilizado!");
                                System.out.println("Incrementou a força em +" +pocao.getAumentoForca() +" e " + diferencaVida+ " de HP recuperados.\n");
                            } else {
                                heroi.setHp(cura); // Apenas cura a quantidade necessária
                                System.out.println("\n["+pocao.getNome()+"] utilizado!");
                                System.out.println("Incrementou a força em +" +pocao.getAumentoForca() +" e " + pocao.getVidaCurar()+ " de HP recuperados.\n");
                            }

                            heroi.getInventario().remove(pocao);

                        } else {
                            System.out.println("");
                            System.out.println("[!] - Consumível inválido. Escolha novamente. - [!]");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("\n[!] - Não há itens para usar! - [!]");
                        System.out.println("");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    continue;
            }

            // Lógica de combate (Heroi ataca primeiro)
            if(danoHeroi>0) {
                if (danoHeroi < npc.getHp()) {
                    npc.setHp(npc.getHp() - danoHeroi);
                    System.out.println("");

                    if (critical) {
                        playAudio("Ficheiros/crit.wav");
                        System.out.println(heroi.getNome() + " deu uma tremenda marretada causando ataque [critical] de " + danoHeroi + " de dano.\n");
                    } else {
                        playAudio("Ficheiros/atk.wav");
                        System.out.println(heroi.getNome() + " atacou causando " + danoHeroi + " de dano.\n");
                    }
                    if (danoNPC < heroi.getHp()) {
                        heroi.setHp(heroi.getHp() - danoNPC);
                        playAudio("Ficheiros/atk.wav");
                        System.out.println(npc.getNome() + " atacou causando " + danoNPC + " de dano.\n");
                    } else {
                        playAudio("Ficheiros/atk.wav");
                        System.out.println(npc.getNome() + " atacou causando " + danoNPC + " de dano e venceu o combate!");
                        Audio.stopMusica();
                        heroi.morrer(); // HEROI MORREU

                        int escolhaMorte;
                        do {
                            System.out.println("\n»»—————————⤷ ♱ ⤶—————————««");
                            System.out.println("      Seu herói morreu ");
                            System.out.println("»»———————————————————————««");
                            System.out.println("[1] - Criar novo herói");
                            System.out.println("[2] - Continuar com herói");
                            System.out.println("[3] - Fechar jogo");
                            System.out.print("Como quer prosseguir?: ");
                            Scanner falecido = new Scanner(System.in);
                            escolhaMorte = falecido.nextInt();

                            switch (escolhaMorte) {
                                case 1:
                                    Jogo jogo = new Jogo();
                                    heroi = jogo.criarPersonagem();
                                    reinoNorte(heroi);
                                    break;
                                case 2:
                                    heroi.setHp(1);
                                    System.out.println("");
                                    System.out.println("\n»»—————————————————————————⤷ ♱ ⤶—————————————————————————««");
                                    System.out.println("  " +heroi.getNome()+" foi ressuscitado por alguma força misteriosa!");
                                    System.out.println("   Renascido com [1 de HP], carregue antes de prosseguir. ");
                                    System.out.println("»»———————————————————————————✧————————————————————————————««");
                                    reinoNorte(heroi);
                                    break;
                                case 3:
                                    heroi.setFim(true);
                                    break;
                                default:
                                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                                    break;
                            }
                        } while (escolhaMorte != 3);

                        return npc;
                    }
                } else {
                    System.out.println("");
                    Audio.stopMusica();
                    playAudio("Ficheiros/vitoria.wav");
                    System.out.println(heroi.getNome() + " deu um golpe mortal causando " + danoHeroi + " de dano e venceu o combate!");
                    System.out.println(npc.getNome() + " morre deixando " + npc.getOuro() + " de ouro.");
                    int ouroAtual = heroi.getOuro() + npc.getOuro();
                    heroi.setOuro(ouroAtual);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return heroi;
                }
            }
        } while (heroi.getHp() > 0 && npc.getHp() > 0);

        Audio.stopMusica();
        return null;
    }
}
