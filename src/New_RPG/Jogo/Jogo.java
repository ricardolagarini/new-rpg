package New_RPG.Jogo;

import New_RPG.Controller.EstrategiaAtaque.Arqueiro;
import New_RPG.Controller.EstrategiaAtaque.Cavaleiro;
import New_RPG.Controller.EstrategiaAtaque.Feiticeiro;
import New_RPG.Controller.VendedorController;
import New_RPG.Domain.Entidades.Heroi;
import New_RPG.Domain.Entidades.NPC;
import New_RPG.Domain.Entidades.Vendedor;
import New_RPG.Domain.Itens.*;
import New_RPG.Repository.RepositoryItens;
import New_RPG.Tools.Audio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static New_RPG.Domain.Entidades.Heroi.exibirConsumiveisPocao;
import static New_RPG.Tools.Audio.playAudio;

public class Jogo {

    private static void pressioneParaVoltar(Scanner scanner) {
        System.out.print("\nPressione qualquer tecla para voltar para o menu anterior:  ");
        scanner.nextLine();
        scanner.nextLine();
    }

    public Heroi criarPersonagem() throws FileNotFoundException {

        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n" +
                "//                                                                                                                                                               //  \n" +
                "//    ____      _               ____                  _          _              ____      //\\        _                     _         _   _            _          //\n" +
                "//   |  _ \\ ___(_)_ __   ___   / ___|  ___  _ __ ___ | |__  _ __(_) ___    _   / ___|_ __|/_\\| _ __ (_) ___ __ _ ___    __| | ___   | \\ | | ___  _ __| |_ ___    //\n" +
                "//   | |_) / _ \\ | '_ \\ / _ \\  \\___ \\ / _ \\| '_ ` _ \\| '_ \\| '__| |/ _ \\  (_) | |   | '__/ _ \\| '_ \\| |/ __/ _` / __|  / _` |/ _ \\  |  \\| |/ _ \\| '__| __/ _ \\   //\n" +
                "//   |  _ <  __/ | | | | (_) |  ___) | (_) | | | | | | |_) | |  | | (_) |  _  | |___| | | (_) | | | | | (_| (_| \\__ \\ | (_| | (_) | | |\\  | (_) | |  | ||  __/   //\n" +
                "//   |_| \\_\\___|_|_| |_|\\___/  |____/ \\___/|_| |_| |_|_.__/|_|  |_|\\___/  (_)  \\____|_|  \\___/|_| |_|_|\\___\\__,_|___/  \\__,_|\\___/  |_| \\_|\\___/|_|   \\__\\___|   //\n" +
                "//                                                                                                                                                               // \n" +
                "//                                                                                                                                                               // \n" +
                "///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");


        System.out.println("");
        System.out.println("\"Em um reino distante, um jovem guerreiro conhecido por sua bravura e habilidades de combate, é convocado pelo Rei Tobias para uma missão vital.\n Uma horda de criaturas sombrias invadiu a região do norte, ameaçando a paz do reino. Assim, nosso herói com determinação inabalável, parte\n em uma jornada perigosa para proteger o reino das garras dos desconhecidos seres sombrios.\"");
        System.out.println("");

        Scanner input = new Scanner(System.in);
        RepositoryItens armasRep = new RepositoryItens();
        ArmaPrincipal espada = (ArmaPrincipal) armasRep.getItensList().get(3); // ArmaPrincipal Cavaleiro definida
        ArmaPrincipal cajado = (ArmaPrincipal) armasRep.getItensList().get(5); // ArmaPrincipal Feiticeiro definida
        ArmaPrincipal arco = (ArmaPrincipal) armasRep.getItensList().get(4); // ArmaPrincipal Arqueiro definida

        int escolhaHeroi = 0;

        while (true) {

            System.out.println("»»———————————⤷ ❔ ⤶—————————««");
            System.out.println(" Escolha a classe de seu herói");
            System.out.println("»»———————————————————————————««");
            System.out.println("[1] - Cavaleiro");
            System.out.println("[2] - Feiticeiro");
            System.out.println("[3] - Arqueiro");
            System.out.print("Qual vai ser? ");

            if (input.hasNextInt()) {
                escolhaHeroi = input.nextInt();
                input.nextLine();// Limpa o buffer do scanner
                if (escolhaHeroi >= 1 && escolhaHeroi <= 3) {
                    break;
                } else {
                    System.out.println("\n[!] - Escolha inválida. Escolha [1], [2] ou [3]. - [!]");
                }
            } else {
                System.out.println("\n[!] - Valor inválido inserido. Insira um número válido. - [!]\n");
                input.next(); // Limpa o buffer do scanner
            }
        }

        System.out.println("");
        System.out.println("»»—————————⤷ ❔ ⤶———————««");
        System.out.println("  Como ele vai se chamar?");
        System.out.println("»»———————————————————————««");
        System.out.print("Nome: ");
        String nome = input.nextLine();

        int escolhaDificuldade = 0;

        while (true) {
            System.out.println("");
            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("  Escolha a dificuldade");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Fácil");
            System.out.println("[2] - Difícil");
            System.out.print("Qual a sua decisão?: ");

            if (input.hasNextInt()) {
                escolhaDificuldade = input.nextInt();
                if (escolhaDificuldade == 1 || escolhaDificuldade == 2) {
                    break;
                } else {
                    System.out.println("\n[!] - Escolha inválida. Escolha [1] para Fácil ou [2] para Difícil. - [!]");
                }
            } else {
                System.out.println("\n[!] - Valor inválido inserido para a dificuldade. Insira um número válido. - [!]");
                input.next(); // Limpa o buffer do scanner
            }
        }

        int pontosCriacao = 0;
        int ouro = 0;
        int forca = 0;
        int hpMaximo = 0;
        int hp = 0;

        switch (escolhaDificuldade) {
            case 1:
                pontosCriacao = 300;
                ouro = 200;
                break;
            case 2:
                pontosCriacao = 200;
                ouro = 100;
                break;
            default:
                System.out.println("[!] - Escolha de dificuldade inválida. - [!]");
                System.out.println("Escolha  [1] Fácil ou [2] Dificil.");
                return null;
        }

        while (pontosCriacao > 0) {
            System.out.println("");
            System.out.println("»»—————⤷ ⚔♡ ⤶—————««");
            System.out.println("   Build do Herói");
            System.out.println("»»—————————————————««");
            System.out.println("Pontos disponiveis: " + pontosCriacao);
            System.out.println("Distribua os pontos entre [Vida] e [Força]");

            int pontosInputVida;
            int pontosInputForca;

            System.out.print("Pontos para [Vida]: ");
            if (input.hasNextInt()) {
                pontosInputVida = input.nextInt();
            } else {
                System.out.println("\n[!] - Valor inválido inserido para Pontos de Vida - [!]");
                input.next(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            System.out.print("Pontos para [Força]: ");
            if (input.hasNextInt()) {
                pontosInputForca = input.nextInt();
            } else {
                System.out.println("\n[!] - Valor inválido inserido para Pontos de Força - [!]");
                input.next(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            if (pontosInputVida + pontosInputForca > pontosCriacao || pontosInputVida + pontosInputForca < pontosCriacao) {
                System.out.println("\n[!] - Você não distribuiu quantidade de pontos disponíveis corretamente - [!]");
                System.out.println("                      [!] - Insira novamente. - [!]");
            } else {
                hp= pontosInputVida*5;
                hpMaximo= pontosInputVida*5;
                forca = pontosInputForca*5;
                pontosCriacao -= pontosInputForca;
                pontosCriacao -= pontosInputVida;
                break;
            }
        }

        switch (escolhaHeroi) {
            case 1:
                return new Heroi(nome,hpMaximo,hp,forca,ouro,espada,new Cavaleiro()); // Cria novo Cavaleiro
            case 2:
                return new Heroi(nome,hpMaximo,hp,forca,ouro,cajado, new Feiticeiro()); // Cria novo Feiticeiro
            case 3:
                return new Heroi(nome,hpMaximo,hp,forca,ouro, arco, new Arqueiro()); // Cria novo Arqueiro
            default:
                System.out.println("[!] - Escolha de herói inválida. - [!]");
                return null;
        }
    }//OK
    public static void reinoNorte (Heroi heroi) throws FileNotFoundException {
        System.out.println("");
        System.out.println("»»————————————⤷ ♕ ♕ ⤶————————————««");
        System.out.println("    Bem vindo ao Reino do Norte!");
        System.out.println("»»—————————————————————————————————««");
        System.out.println("\nVocê está agora na cidade principal do reino.");

        Scanner scanner = new Scanner(System.in);
        int escolha;

        Vendedor vendedor01 = new Vendedor();
        VendedorController loja01 = new VendedorController();

        do {
            if(heroi.isFim()){return;}

            System.out.println("");
            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("  Escolha seu destino:");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Visitar Loja de Itens");
            System.out.println("[2] - Seguir para Floresta Densa");
            System.out.println("[3] - Caminho para a Caverna Isolada");
            System.out.println("[4] - Abrir inventário de Poções");
            System.out.println("[5] - Abrir Status do Jogador");
            System.out.print("O que quer fazer?: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    loja01.vender(vendedor01,heroi,scanner);
                    pressioneParaVoltar(scanner);
                    break;
                case 2:
                    salaFloresta(heroi);
                    break;
                case 3:
                    salaCaminhoCaverna(heroi);
                    break;
                case 4:
                    ArrayList<Integer> invenTemp= exibirConsumiveisPocao(heroi);
                    if (!invenTemp.isEmpty()) {
                        System.out.println("\nEscolha o número da poção ou digite [0] para retornar");
                        System.out.print("\nEscolha:");
                        int consumivelPocao = scanner.nextInt();
                        int indiceSelecionado = consumivelPocao - 1;

                        if (indiceSelecionado >= 0 && indiceSelecionado < invenTemp.size()) {
                            Pocao pocao = (Pocao) heroi.getInventario().get(invenTemp.get(indiceSelecionado));

                            int cura = pocao.getVidaCurar() + heroi.getHp();
                            int novaForca = pocao.getAumentoForca() + heroi.getForca();
                            int diferencaVida = heroi.getMaxHp() - heroi.getHp(); // Verificar se a cura excede a diferença entre a vida máxima e a vida atual

                            heroi.setForca(novaForca);

                            if (cura > heroi.getMaxHp()) {
                                heroi.setHp(heroi.getMaxHp()); // Ajustar para a vida máxima
                                try{
                                    System.out.println("\n["+pocao.getNome()+"] utilizado!");
                                    System.out.println("Incrementou a força em +" +pocao.getAumentoForca() +" e " + diferencaVida+ " de HP recuperados.\n");

                                    playAudio("Ficheiros/heal.wav");

                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                heroi.setHp(cura); // Apenas cura a quantidade necessária
                                try{
                                    System.out.println("\n["+pocao.getNome()+"] utilizado!");
                                    System.out.println("Incrementou a força em +" +pocao.getAumentoForca() +" e " + pocao.getVidaCurar()+ " de HP recuperados.\n");

                                     playAudio("Ficheiros/heal.wav"); // SE ATIVAR A MUSICA DE BATALHA NAO PARA

                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            heroi.getInventario().remove(pocao);

                        } else if (consumivelPocao != 0) {
                            System.out.println("");
                            System.out.println("[!] - Consumível inválido. Escolha novamente. - [!]");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("\n[!] - Não há itens para usar! - [!]");
                        System.out.println("");
                    }
                    pressioneParaVoltar(scanner);
                    break;
                case 5:

                    heroi.exibirStatus();
                    pressioneParaVoltar(scanner);
                    break;

                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        } while (true);
    }//OK
    public static void salaCaminhoCaverna (Heroi heroi) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            if(heroi.isFim()){return;}

            try {
            System.out.println("");
            System.out.println(heroi.getNome() + " a caminho da Caverna Isolada se depara com um homem caído no chão.");
            System.out.println("Ao se aproximar, nosso herói percebe o corpo gelado e aparentemente sem vida.");
            System.out.println("De repente o homem acorda pedindo pelo seu sangue pois está a dias sem se alimentar muito fraco.");
                Thread.sleep(6000);
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("    Vampirão faminto");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Dar sangue");
            System.out.println("[2] - Negar e seguir caminho adiante");
            System.out.print("Qual sua escolha?: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:

                    int novoHpmax = heroi.getMaxHp()-100;
                    int novoHp = heroi.getHp()-100;
                    int novaForca = heroi.getForca()+150;
                    heroi.setHp(novoHp);
                    heroi.setMaxHp(novoHpmax);
                    heroi.setForca(novaForca);

                    if(heroi.getHp()>0) {
                        try{
                            playAudio("Ficheiros/ouch.wav");
                        System.out.println("\n"+heroi.getNome()+" foi contaminado pelo veneno do Vampirão!");
                        System.out.println(heroi.getNome()+" teve HP reduzido permanentemente por 100!");
                            Thread.sleep(2000);
                            System.out.println("");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try{
                        System.out.println(heroi.getNome()+ " sente uma força estranha tomando seu corpo...");
                        System.out.println("É o veneno do Vampirão agindo!");
                            Thread.sleep(4000);
                            System.out.println("");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try{
                        System.out.println(heroi.getNome()+" teve sua força aumentada em 150!");
                            playAudio("Ficheiros/lvlup.wav");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try{
                        System.out.println("\n" +heroi.getNome() + " recupera-se do frenesi e segue viagem até a Caverna.");
                            Thread.sleep(3500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        cavernaIsolada(heroi);

                    }else{
                        System.out.println("\n"+heroi.getNome()+" não aguentou o veneno do Vampirão.");
                        heroi.morrer();
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
                    }
                    break;
                case 2:
                    cavernaIsolada(heroi);
                    break;
                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        } while (escolha != 1 && escolha != 2);

    } //OK
    public static void cavernaIsolada (Heroi heroi) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            if(heroi.isFim()){return;}

            try{
            System.out.println("\nAo adentrar a caverna, a luz do lado de fora diminui rapidamente, mergulhando os intrépidos");
            System.out.println("exploradores em uma escuridão profunda...");
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try{
            System.out.println("\nCom extrema cautela desbravando a caverna, "+ heroi.getNome()+ " dá de cara com o inimigo!!!\n");
                playAudio("Ficheiros/spoted.wav");
                Thread.sleep(3000);
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            NPC morcegao = new NPC("Morcegão Sugador de Sangue",1200,1200,150,50);
            heroi.getEstrategiaAtaque().atacar(heroi,morcegao);

            if(heroi.isFim()){return;}

            try {
                heroi.subirNivel();
                playAudio("Ficheiros/lvlup.wav");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try{
            System.out.println("");
            System.out.println("Após a luta, nosso herói avista um corpo.");
            System.out.println("Olhando de perto o corpo parece estar ali há anos...");
            System.out.println("Analisando as vestimentas aparenta ser de um jovem nobre ou mercador.");
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("");
            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("          Corpo");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Olhar os bolsos das roupas");
            System.out.println("[2] - Ignorar e seguir caminho adiante");
            System.out.print("Qual sua escolha?: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Random random = new Random();
                    int chance = random.nextInt(10) + 1; // Número aleatório entre 1 e 10

                    if (chance <= 3) { // Chance de 30%
                        try{
                        System.out.println("\nVocê encontrou um [Elixir Milagroso]! Que sorte!");
                        System.out.println(" ✧ ദ്ദി( •ᴗ- )");
                        System.out.println("\n[Elixir Milagroso] adicionado ao inventário.");
                            playAudio("Ficheiros/heal.wav");
                            Thread.sleep(4000);
                            System.out.println("");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        RepositoryItens dropRep = new RepositoryItens();
                        Pocao itemDrop = (Pocao) dropRep.getItensList().get(17); // Item linha 17 Elixir Milagroso
                        heroi.addItensInventario(itemDrop);

                    } else {
                        try{
                        System.out.println("\nVocê foi picado por um escorpião!");
                        System.out.println("(x﹏x)");
                        System.out.println(heroi.getNome()+" recebeu dano de 100 em seu HP.");
                        heroi.setHp(heroi.getHp()-100);
                            playAudio("Ficheiros/ouch.wav");
                        Thread.sleep(4000);
                        System.out.println("");
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                        if (heroi.getHp()<0){
                            System.out.println("\nO veneno era muito forte!");
                            heroi.morrer();
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
                        }
                    }
                    break;
                case 2:

                    break;
                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        } while (escolha != 1 && escolha != 2);

        System.out.println("");
        try{
        System.out.println(heroi.getNome()+" com passos silenciosos, avança cautelosamente pelas sombras da caverna onde o eco ");
        System.out.println("sussurrante de águas subterrâneas se mistura ao ruído dos próprios batimentos cardíacos.\n");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
        System.out.println("Cada esquina escura promete o desconhecido, enquanto a presença de monstros adormecidos paira no ar");
        System.out.println("aumentando a tensão a cada passo adiante.\n");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
        System.out.println("Num súbito estrondo, um ogro colossal emerge das sombras!!!\n");
            playAudio("Ficheiros/spoted.wav");
            Thread.sleep(3000);
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NPC bolgrom = new NPC("Bolgrom, o Brutal",2200,2200,250,250);
        heroi.getEstrategiaAtaque().atacar(heroi,bolgrom);

        if(heroi.isFim()){return;}

        if(heroi.getHp()>0) {
            RepositoryItens dropRep = new RepositoryItens();
            ConsumivelCombate combateDrop = (ConsumivelCombate) dropRep.getItensList().get(23); // Item linha 23 - Bomba fedorenta
            heroi.addItensInventario(combateDrop);

            try{
            System.out.println("\nBolgrom, o Brutal dropou o item "+"["+combateDrop.getNome()+"]"+ "!");
            System.out.println("["+combateDrop.getNome()+"]"+ " foi adicionado ao inventário.");
                playAudio("Ficheiros/drop.wav");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                heroi.subirNivel();
                playAudio("Ficheiros/lvlup.wav");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("");
            try{
            System.out.println(heroi.getNome() + " terminou a batalha com algumas feridas mas nada grave... ");
            System.out.println("Porém preocupado com o que vem pela frente.");
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("");

            Scanner input = new Scanner(System.in);
            int escolhaProx;

            do {
                System.out.println("»»———————⤷ ❔ ⤶———————««");
                System.out.println("  Escolha seu destino:");
                System.out.println("»»—————————————————————««");
                System.out.println("[1] - Caminho para Montanhas Ingrimes");
                System.out.println("[2] - Seguir para Floresta Densa");
                System.out.println("[3] - Abrir inventário de Poções");
                System.out.println("[4] - Abrir Status do Jogador");
                System.out.print("O que quer fazer?: ");
                escolhaProx = input.nextInt();

                switch (escolhaProx) {
                    case 1:
                        salaMontanhasIngrimes(heroi);
                        break;
                    case 2:
                        salaFloresta(heroi);
                        break;
                    case 3:
                        ArrayList<Integer> invenTemp= exibirConsumiveisPocao(heroi);
                        if (!invenTemp.isEmpty()) {
                            System.out.println("\nEscolha o número da poção ou digite [0] para retornar");
                            System.out.print("\nEscolha:");
                            int consumivelPocao = scanner.nextInt();
                            int indiceSelecionado = consumivelPocao - 1;

                            if (indiceSelecionado >= 0 && indiceSelecionado < invenTemp.size()) {
                                Pocao pocao = (Pocao) heroi.getInventario().get(invenTemp.get(indiceSelecionado));
                                int cura = pocao.getVidaCurar() + heroi.getHp();
                                int novaForca = pocao.getAumentoForca() + heroi.getForca();
                                int diferencaVida = heroi.getMaxHp() - heroi.getHp(); // Verificar se a cura excede a diferença entre a vida máxima e a vida atual

                                heroi.setForca(novaForca);

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

                            } else if (consumivelPocao != 0) {
                                System.out.println("");
                                System.out.println("[!] - Consumível inválido. Escolha novamente. - [!]");
                                System.out.println("");
                            }
                        } else {
                            System.out.println("\n[!] - Não há itens para usar! - [!]");
                            System.out.println("");
                        }
                        pressioneParaVoltar(scanner);
                        break;
                    case 4:
                        heroi.exibirStatus();
                        pressioneParaVoltar(scanner);
                        break;
                    default:
                        System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]\n");
                        break;
                }
            }while (escolhaProx <1 || escolhaProx> 2 );
        }
    }//OK
    public static void salaFloresta (Heroi heroi) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            if(heroi.isFim()){return;}

            System.out.println("");
            try {
            System.out.println(heroi.getNome() + " fica encantado com a floresta e sua diversidade, mas o que mais lhe chamou\n" +
                    "atenção foi uma estranha árvore com um fruto que nunca viu antes.");
                Thread.sleep(1500);
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("");
            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("    Arvore Misteriosa");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Comer fruto");
            System.out.println("[2] - Seguir caminho adiante");
            System.out.print("Qual sua escolha?: ");

            escolha = scanner.nextInt();


            switch (escolha) {
                case 1:
                    Random random = new Random();
                    int chance = random.nextInt(10) + 1; // Número aleatório entre 1 e 10

                    if (chance <= 4) { // Chance de 40%
                        int novaVidaMax = heroi.getMaxHp() + 100;
                        heroi.setMaxHp(novaVidaMax);
                        int novaVidaAtual = heroi.getHp()+100;
                        heroi.setHp(novaVidaAtual);

                        try {
                        System.out.println("\nVocê comeu o fruto e sua vida máxima aumentou em 100!");
                        System.out.println(" ✧ ദ്ദി( •ᴗ- )");
                            playAudio("Ficheiros/heal.wav");
                            Thread.sleep(1500);
                            System.out.println("");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            playAudio("Ficheiros/ouch.wav");
                        System.out.println("\nVocê comeu o fruto, mas estava bichado.");
                        System.out.println("(╥﹏╥)");
                            Thread.sleep(1500);
                            System.out.println("");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:

                    break;
                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        } while (escolha != 1 && escolha != 2);

        try {
        System.out.println("");
        System.out.println(heroi.getNome()+" segue floresta a dentro...");
        System.out.println("Um cheiro forte de carne apodrecida contamina o ar...");
            Thread.sleep(1800);
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
        System.out.println("Uma Criatura desconhecida aparece!!!");
        playAudio("Ficheiros/spoted.wav");
            Thread.sleep(3000);
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NPC goblin = new NPC("Goblin",1200,1200,150,50);
        heroi.getEstrategiaAtaque().atacar(heroi,goblin);

        if(heroi.isFim()){return;}

        try {
        heroi.subirNivel();
        playAudio("Ficheiros/lvlup.wav");
        Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
        System.out.println("");
        System.out.println("Após a batalha " +heroi.getNome()+" caminha por mais alguns minutos, ");
        System.out.println("e sente que ar começa a ficar denso e umido, inesperadamente algo surge em sua frente!");
        System.out.println("");
        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Chefe da área apareceu!!!");
            playAudio("Ficheiros/spoted.wav");
            Thread.sleep(3000);
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NPC planta = new NPC("Dark Ent",2000,2000,280,200);
        heroi.getEstrategiaAtaque().atacar(heroi,planta);

        if(heroi.isFim()){return;}

        if(heroi.getHp()>0) {
            RepositoryItens dropRep = new RepositoryItens();
            Pocao itemDrop = (Pocao) dropRep.getItensList().get(20); // Item linha 20 - Cogumelo Magico
            heroi.addItensInventario(itemDrop);

            try {
                System.out.println("\nDark Ent dropou o item "+"["+itemDrop.getNome()+"]"+ "!");
                System.out.println("["+itemDrop.getNome()+"]"+ " foi adicionado ao inventário.");
                playAudio("Ficheiros/drop.wav");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                heroi.subirNivel();
                playAudio("Ficheiros/lvlup.wav");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
            System.out.println("");
            System.out.println(heroi.getNome() + " pensou que não iria conseguir escapar dessa vivo... ");
            System.out.println("Após descansar e recuperar o folego, nosso herói segue em frente");
            System.out.println("");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Scanner input = new Scanner(System.in);
            int escolhaProx;

            do {
                System.out.println("»»———————⤷ ❔ ⤶———————««");
                System.out.println("  Escolha seu destino:");
                System.out.println("»»—————————————————————««");
                System.out.println("[1] - Caminho para Montanhas Ingrimes");
                System.out.println("[2] - Seguir para Lago Nebuloso");
                System.out.println("[3] - Abrir inventário de Poções");
                System.out.println("[4] - Abrir Status do Jogador");
                System.out.print("O que quer fazer?: ");
                escolhaProx = input.nextInt();

                switch (escolhaProx) {
                    case 1:
                        salaMontanhasIngrimes(heroi);
                        break;
                    case 2:
                        salaLagoNebuloso(heroi);
                        break;
                    case 3:
                        ArrayList<Integer> invenTemp= exibirConsumiveisPocao(heroi);
                        if (!invenTemp.isEmpty()) {
                            System.out.println("\nEscolha o número da poção ou digite [0] para retornar");
                            System.out.print("\nEscolha:");
                            int consumivelPocao = scanner.nextInt();
                            int indiceSelecionado = consumivelPocao - 1;

                            if (indiceSelecionado >= 0 && indiceSelecionado < invenTemp.size()) {
                                Pocao pocao = (Pocao) heroi.getInventario().get(invenTemp.get(indiceSelecionado));
                                int cura = pocao.getVidaCurar() + heroi.getHp();
                                int novaForca = pocao.getAumentoForca() + heroi.getForca();
                                int diferencaVida = heroi.getMaxHp() - heroi.getHp(); // Verificar se a cura excede a diferença entre a vida máxima e a vida atual

                                heroi.setForca(novaForca);

                                if (cura > heroi.getMaxHp()) {
                                    heroi.setHp(heroi.getMaxHp()); // Ajustar para a vida máxima
                                    try{
                                        System.out.println("\n["+pocao.getNome()+"] utilizado!");
                                        System.out.println("Incrementou a força em +" +pocao.getAumentoForca() +" e " + diferencaVida+ " de HP recuperados.\n");

                                        playAudio("Ficheiros/heal.wav");

                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    heroi.setHp(cura); // Apenas cura a quantidade necessária
                                    try{
                                        System.out.println("\n["+pocao.getNome()+"] utilizado!");
                                        System.out.println("Incrementou a força em +" +pocao.getAumentoForca() +" e " + pocao.getVidaCurar()+ " de HP recuperados.\n");

                                         playAudio("Ficheiros/heal.wav"); // SE ATIVAR A MUSICA DE BATALHA NAO PARA

                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                heroi.getInventario().remove(pocao);

                            } else if (consumivelPocao != 0) {
                                System.out.println("");
                                System.out.println("[!] - Consumível inválido. Escolha novamente. - [!]");
                                System.out.println("");
                            }
                        } else {
                            System.out.println("\n[!] - Não há itens para usar! - [!]");
                            System.out.println("");
                        }
                        pressioneParaVoltar(scanner);
                        break;
                    case 4:
                        heroi.exibirStatus();
                        pressioneParaVoltar(scanner);
                        break;
                    default:
                        System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]\n");
                        break;
                }
            }while (escolhaProx <1 || escolhaProx> 2 );
        }
    } // OK
    public static void salaLagoNebuloso (Heroi heroi) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            if(heroi.isFim()){return;}
            System.out.println("");
            System.out.println("Uma névoa paira sobre suas águas escuras, criando uma sensação de que o lago está sempre ");
            System.out.println("envolto em um segredo profundo, ocultando o que pode estar além de sua superfície refletiva.");

            System.out.println("");
            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("     Lago Nebuloso");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Colocar a mao dentro do lago");
            System.out.println("[2] - Negar e seguir caminho adiante");
            System.out.print("Qual sua escolha?: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    int novoHpmax = heroi.getMaxHp()+100;
                    int novoHp = heroi.getHp()+100;
                    heroi.setHp(novoHp);
                    heroi.setMaxHp(novoHpmax);

                    System.out.println("\nA água do lago possui propriedades milagrosas, sua vida máxima aumentou em 100!");
                    System.out.println(" ✧ ദ്ദി( •ᴗ- )");
                    System.out.println("");

                    Random random = new Random();
                    int chance = random.nextInt(10) + 1; // Número aleatório entre 1 e 10

                    if (chance <= 5) { // Chance de 50%

                        System.out.println("Emerge do lago, uma enorme serpente rompendo as águas com majestade. Suas escamas reluzem à luz do sol");
                        System.out.println("lançando um arco-íris de cores iridescentes enquanto sua cabeça ergue-se imponente, anunciando sua presença.");
                        System.out.println("O lendário Leviathan aparece!!!!");
                        System.out.println(" !!(˚0˚)!!\n");

                        NPC leviathan = new NPC("Leviathan",3000,3000,380,400);
                        heroi.getEstrategiaAtaque().atacar(heroi,leviathan);

                        if(heroi.isFim()){return;}

                        if(heroi.getHp()>0) {
                            RepositoryItens dropRep = new RepositoryItens();
                            Pocao itemDrop = (Pocao) dropRep.getItensList().get(24); // Item linha 24 - Lapis Lazuli
                            heroi.addItensInventario(itemDrop);

                            System.out.println("\nLeviathan dropou o item " + "[" + itemDrop.getNome() + "]" + "!");
                            System.out.println("[" + itemDrop.getNome() + "]" + " foi adicionado ao inventário.");

                            heroi.subirNivel();

                            System.out.println("");
                            System.out.println("Apos a desafiadora batalha. Todo o cenario mudou para uma bela paisagem");
                            System.out.println("Agora o lago possui aguas cristalinas e o ar masi puro que nunca.");
                            System.out.println(heroi.getNome()+ " aproveita a encantadora atmosfera para descansar...");
                            System.out.println("Nosso herói está pronto para continuar.");

                            salaMontanhasIngrimes(heroi);
                        }

                    } else {
                        System.out.println(heroi.getNome()+" sente-se extremamente revigorado! ");
                        System.out.println("Agora está pronto para continuar.");
                        salaMontanhasIngrimes(heroi);
                    }
                    break;
                case 2:
                    salaMontanhasIngrimes(heroi);
                    break;
                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        }while (escolha <1 || escolha> 2 );

    }
    public static void salaMontanhasIngrimes (Heroi heroi) throws FileNotFoundException {




        Scanner scanner = new Scanner(System.in);
        int escolha;;

        Vendedor vendedor01 = new Vendedor();
        VendedorController loja01 = new VendedorController();

        do {
            if(heroi.isFim()){return;}
            System.out.println("");
            System.out.println(heroi.getNome() + " finalmente chega as montanhas e fica deslumbrado ao ver os ");
            System.out.println("picos cobertos por uma neblina enigmática, refletindo as cores do pôr do sol ");
            System.out.println("criando um cenário etéreo de beleza imponente.\n");

            System.out.println("Após contemplar a paisagem, nosso heroi avista um mercador ambulante parado a frente de uma cabana.\n");

            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("  Escolha seu destino:");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Seguir caminho adiante");
            System.out.println("[2] - Seguir para Cabana");
            System.out.println("[3] - Ver itens do mercador");
            System.out.println("[4] - Abrir inventário de Poções");
            System.out.println("[5] - Abrir Status do Jogador");
            System.out.print("O que quer fazer?: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    salaMeioMontanhas(heroi);
                    break;
                case 2:
                    System.out.println("\nA cabana estava vazia, porém havia sinais de que alguem esteve ali poucas");
                    System.out.println("horas atrás."+heroi.getNome()+ " resolveu ficar ali e esperar alguem retornar visto que");
                    System.out.println("já que a moite estava vindo, alem do frio os monstros são mais ativos nessa hora.");
                    System.out.println("Passava da meia noite, e ninguem apareceu... Cansado de sua viagem, "+heroi.getNome()+" pega no sono.\n");

                    try {
                    System.out.println("∩――――――――∩\n" +
                            "||    zZz    ||\n" +
                            "||   ( - ˕ -)აZZzz\n" +
                            "|ﾉ^      ⌒⌒づ`￣  ＼\n" +
                            "(　ノ　     ⌒ ヽ     ＼\n" +
                            "＼　 　||￣￣￣￣￣￣￣￣￣||\n" +
                            "　 ＼,ﾉ||______________||");

                    playAudio("Ficheiros/soninho.wav");
                    Thread.sleep(6000);
                    System.out.println("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    heroi.setHp(heroi.getMaxHp());
                    System.out.println("\nSeu Hp foi totalmente recuperado!\n");

                    Random random = new Random();
                    int chance = random.nextInt(10) + 1; // Número aleatório entre 1 e 10

                    if (chance <= 5) { // Chance de 50%

                        System.out.println("No silêncio da noite, " + heroi.getNome() +" sente uma pressão esmagadora que toma conta do ambiente e rapidamente corre para fora para ver o que está acontecendo.");
                        System.out.println("Aparece imponente um dragão lendário, suas escamas brilhando à luz da lua, fazendo-me despertar com um misto de medo e admiração diante de sua grandiosidade.");
                        System.out.println("O dragão lendário Tiamat aparece!!!!");
                        System.out.println(" !!(˚0˚)!!\n");

                        NPC tiamat = new NPC("Tiamat",4500,4500,450,1000);
                        heroi.getEstrategiaAtaque().atacar(heroi,tiamat);

                        if(heroi.isFim()){return;}

                        if(heroi.getHp()>0) {
                            RepositoryItens dropRep = new RepositoryItens();

                            if(heroi.getEstrategiaAtaque() instanceof Cavaleiro) {
                                ArmaPrincipal itemDrop = (ArmaPrincipal) dropRep.getItensList().get(0); //Excalibur
                                heroi.setArmaPrincipal(itemDrop);

                                System.out.println("\nTiamat dropou  a arma lendária " + "[" + itemDrop.getNome() + "]" + "!");
                                System.out.println("[" + itemDrop.getNome() + "]" + " foi equipada.");
                            }

                            if(heroi.getEstrategiaAtaque() instanceof Feiticeiro) {
                                ArmaPrincipal itemDrop = (ArmaPrincipal) dropRep.getItensList().get(2); //Cajado de Gandalf
                                heroi.setArmaPrincipal(itemDrop);

                                System.out.println("\nTiamat dropou  a arma lendária " + "[" + itemDrop.getNome() + "]" + "!");
                                System.out.println("[" + itemDrop.getNome() + "]" + " foi equipada.");
                            }

                            if(heroi.getEstrategiaAtaque() instanceof Arqueiro) {
                                ArmaPrincipal itemDrop = (ArmaPrincipal) dropRep.getItensList().get(1); //Arco de Auriel
                                heroi.setArmaPrincipal(itemDrop);

                                System.out.println("\nTiamat dropou  a arma lendária " + "[" + itemDrop.getNome() + "]" + "!");
                                System.out.println("[" + itemDrop.getNome() + "]" + " foi equipada.");
                            }

                            heroi.subirNivel();

                            System.out.println("\nApós a é batalha. "+heroi.getNome()+ " deixa a cabana e parte rumo seu destino...");

                            salaMeioMontanhas(heroi);
                        }
                    } else {
                        System.out.println("Amanheceu e "+heroi.getNome()+" sente-se extremamente revigorado! ");
                        System.out.println("Mais do que nunca, está pronto para continuar sua jornada.");
                        salaMeioMontanhas(heroi);
                    }
                    break;
                case 3:
                    loja01.vender(vendedor01,heroi,scanner);
                    pressioneParaVoltar(scanner);
                    break;
                case 4:
                    ArrayList<Integer> invenTemp= exibirConsumiveisPocao(heroi);
                    if (!invenTemp.isEmpty()) {
                        System.out.println("\nEscolha o número da poção ou digite [0] para retornar");
                        System.out.print("\nEscolha:");
                        int consumivelPocao = scanner.nextInt();
                        int indiceSelecionado = consumivelPocao - 1;

                        if (indiceSelecionado >= 0 && indiceSelecionado < invenTemp.size()) {
                            Pocao pocao = (Pocao) heroi.getInventario().get(invenTemp.get(indiceSelecionado));
                            int cura = pocao.getVidaCurar() + heroi.getHp();
                            int novaForca = pocao.getAumentoForca() + heroi.getForca();
                            int diferencaVida = heroi.getMaxHp() - heroi.getHp(); // Verificar se a cura excede a diferença entre a vida máxima e a vida atual

                            heroi.setForca(novaForca);

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

                        } else if (consumivelPocao != 0) {
                            System.out.println("");
                            System.out.println("[!] - Consumível inválido. Escolha novamente. - [!]");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("\n[!] - Não há itens para usar! - [!]");
                        System.out.println("");
                    }
                    pressioneParaVoltar(scanner);
                    break;
                case 5:
                    heroi.exibirStatus();
                    pressioneParaVoltar(scanner);
                    break;

                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        } while (escolha <1 || escolha> 2 );

    }
    public static void salaMeioMontanhas (Heroi heroi) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int escolha;;

        Vendedor vendedor01 = new Vendedor();
        VendedorController loja01 = new VendedorController();

        do {
            if(heroi.isFim()){return;}
            System.out.println("");
            System.out.println("Após caminhar alguns quilômetros, o horizonte revela um palco inesperado: o mercador está em apuros!");
            System.out.println("Uma capivara de aparência singular, adornada por um chifre reluzente e aura misteriosa, investe ferozmente contra ele.\n");

            System.out.println("»»———————⤷ ❔ ⤶———————««");
            System.out.println("  Escolha seu destino:");
            System.out.println("»»—————————————————————««");
            System.out.println("[1] - Salvar o mercador!");
            System.out.println("[2] - Ignorar e seguir para Fortaleza Sombria");
            System.out.println("[3] - Abrir inventário de Poções");
            System.out.println("[4] - Abrir Status do Jogador");
            System.out.print("O que quer fazer?: ");
            System.out.println("");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    NPC capybara = new NPC("Capybara Nosense",5000,5000,500,800);
                    heroi.getEstrategiaAtaque().atacar(heroi,capybara);

                    if(heroi.isFim()){return;}

                    if(heroi.getHp()>0) {
                        RepositoryItens dropRep = new RepositoryItens();

                        if(heroi.getEstrategiaAtaque() instanceof Cavaleiro) {
                            ConsumivelCombate itemDrop = (ConsumivelCombate) dropRep.getItensList().get(11); //Meteoro de Pegasus
                            heroi.addItensInventario(itemDrop);

                            System.out.println("\nCapybara Nosense dropou o pergaminho do ataque proibido " + "[" + itemDrop.getNome() + "]" + "!");
                            System.out.println("[" + itemDrop.getNome() + "]" + " foi adicionado ao inventário.");
                        }

                        if(heroi.getEstrategiaAtaque() instanceof Feiticeiro) {
                            ConsumivelCombate itemDrop = (ConsumivelCombate) dropRep.getItensList().get(10); //Avada Kedava
                            heroi.addItensInventario(itemDrop);

                            System.out.println("\nCapybara Nosense dropou o pergaminho do ataque proibido " + "[" + itemDrop.getNome() + "]" + "!");
                            System.out.println("[" + itemDrop.getNome() + "]" + " foi adicionado ao inventário.");
                        }

                        if(heroi.getEstrategiaAtaque() instanceof Arqueiro) {
                            ConsumivelCombate itemDrop = (ConsumivelCombate) dropRep.getItensList().get(12); //Flecha Fuminante
                            heroi.addItensInventario(itemDrop);

                            System.out.println("\nCapybara Nosense dropou o pergaminho do ataque proibido " + "[" + itemDrop.getNome() + "]" + "!");
                            System.out.println("[" + itemDrop.getNome() + "]" + " foi adicionado ao inventário.");
                        }

                        Pocao pocaoDrop = (Pocao) dropRep.getItensList().get(25);// Pequi Cuiabano com Maxixe
                        heroi.addItensInventario(pocaoDrop);

                        System.out.println("\nCapybara Nosense dropou uma iguaria exótica " + "[" + pocaoDrop.getNome() + "]" + "!");
                        System.out.println("[" + pocaoDrop.getNome() + "]" + " foi adicionado ao inventário.");

                        heroi.subirNivel();

                        System.out.println("\nEm um misto de alívio e gratidão, o mercador expressa seu agradecimento, revelando suas ");
                        System.out.println("mercadorias em uma última exibição durante sua jornada:\n");

                        loja01.vender(vendedor01,heroi,scanner);
                        pressioneParaVoltar(scanner);

                        System.out.println("");
                        System.out.println(heroi.getNome()+" segue rumo ao seu destino final...");

                        salaFortalezaSombria(heroi);
                    }
                    break;
                case 2:
                    System.out.println("");
                    System.out.println(heroi.getNome()+" ignora totalmente o mercador e segue adiante.");
                    System.out.println("E foi a última vez que o viu...");
                    salaFortalezaSombria(heroi);
                    break;
                case 3:
                    ArrayList<Integer> invenTemp= exibirConsumiveisPocao(heroi);
                    if (!invenTemp.isEmpty()) {
                        System.out.println("\nEscolha o número da poção ou digite [0] para retornar");
                        System.out.print("\nEscolha:");
                        int consumivelPocao = scanner.nextInt();
                        int indiceSelecionado = consumivelPocao - 1;

                        if (indiceSelecionado >= 0 && indiceSelecionado < invenTemp.size()) {
                            Pocao pocao = (Pocao) heroi.getInventario().get(invenTemp.get(indiceSelecionado));
                            int cura = pocao.getVidaCurar() + heroi.getHp();
                            int novaForca = pocao.getAumentoForca() + heroi.getForca();
                            int diferencaVida = heroi.getMaxHp() - heroi.getHp(); // Verificar se a cura excede a diferença entre a vida máxima e a vida atual

                            heroi.setForca(novaForca);

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

                        } else if (consumivelPocao != 0) {
                            System.out.println("");
                            System.out.println("[!] - Consumível inválido. Escolha novamente. - [!]");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("\n[!] - Não há itens para usar! - [!]");
                        System.out.println("");
                    }
                    pressioneParaVoltar(scanner);
                    break;
                case 4:
                    heroi.exibirStatus();
                    pressioneParaVoltar(scanner);
                    break;

                default:
                    System.out.println("\n[!] - Escolha inválida. Escolha uma opção válida. - [!]");
                    break;
            }
        } while (escolha <1 || escolha> 2 );
    }
    public static void salaFortalezaSombria (Heroi heroi) throws FileNotFoundException {

        if(heroi.isFim()){return;}
        System.out.println("\nAo alcançar a Fortaleza Sombria, uma aura sinistra paira no ar, onde a escuridão parece dançar");
        System.out.println("nas sombras. Não há sinal de guarda,  a ausência de vigilância parece um convite tenebroso para");
        System.out.println("os visitantes, ecoando uma bem-vinda sombria e perturbadora...\n");

        System.out.println("Logo que " +heroi.getNome()+" cruzou os portões da fortaleza, deparou-se, com uma criatura ");
        System.out.println("postada em uma postura imponente e sombria, seus olhos profundos refletiam uma determinação ");
        System.out.println("sinistra, como se já esperasse por sua chegada.\n");

        System.out.println("Prepare-se para batalha!!!\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NPC tenente = new NPC("Tenente Nightveil",5000,5000,500,800);
        heroi.getEstrategiaAtaque().atacar(heroi,tenente);

        if(heroi.isFim()){return;}

        if(heroi.getHp()>0) {
            RepositoryItens dropRep = new RepositoryItens();

            Pocao pocaoDrop = (Pocao) dropRep.getItensList().get(26);// Elixir das Trevas
            heroi.addItensInventario(pocaoDrop);

            System.out.println("\nTenente Nightveil dropou " + "[" + pocaoDrop.getNome() + "]" + "!");
            System.out.println("[" + pocaoDrop.getNome() + "]" + " foi adicionado ao inventário.");

            heroi.subirNivel();

            System.out.println("\nApós uma batalha árdua e sangrenta, nosso herói se vê diante do confronto final.");
            System.out.println("As feridas marcavam seu corpo, testemunhando a tenacidade do embate anterior.\n");

            System.out.println("Diante dele, erguia-se o imponente General das Sombras, emanando um poder sinistro");
            System.out.println("que pairava no ar. Determinado e corajoso, nosso herói respira fundo, pronto para");
            System.out.println("encarar o desafio decisivo, onde a luz e a escuridão se confrontariam pela última vez.\n");

            System.out.println("!!!!Essa é a batalha final!!!!\n");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            NPC general = new NPC("General das Sombras",6000,6000,600,800);
            heroi.getEstrategiaAtaque().atacar(heroi,general);

            System.out.println("\nUm silêncio sereno tomou conta do ambiente, dissipando as trevas que antes assolavam a fortaleza.");
            System.out.println("A luz timidamente começou a penetrar pelas janelas, desfazendo as sombras que haviam dominado aquele");
            System.out.println("lugar por tanto tempo. Nosso herói, exausto mas triunfante, ergue-se diante da vitória conquistada");
            System.out.println("com bravura e determinação. A paz finalmente retorna, trazendo consigo um novo horizonte de esperança");
            System.out.println("e renovação para a terra outrora atormentada pela escuridão.\n");

            System.out.println("\n" +
                    "  ______ _____ __  __ \n" +
                    " |  ____|_   _|  \\/  |\n" +
                    " | |__    | | | \\  / |\n" +
                    " |  __|   | | | |\\/| |\n" +
                    " | |     _| |_| |  | |\n" +
                    " |_|    |_____|_|  |_|\n" +
                    "                      \n" +
                    "                      \n");


            heroi.setFim(true);
        }

    }

























}
