import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Index {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "\n\nBem-vinda ao Codolito\nO que deseja fazer?\n\n1.\tCadastrar novo acólito\n2.\tAdicionar missa no mês\n3.\tListar missas\n4.\tFazer escala\n5.\tListar acólitos\n6.\tCadastrar Missas Fixas\n7.\tSair");
            int resposta = scanner.nextInt();
            switch (resposta) {
                case 1:
                    cadastrarAcolito();
                    break;
                case 2:
                    adicionarMissa();
                    break;
                case 3:
                    listarMissas();
                    break;
                case 4:
                    fazerEscala();
                    break;
                case 5:
                    listarAcolitos();
                    break;
                case 6:
                    MissasFixas();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }

    private static void salvaJSON(Object content, String filePath, String nome) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), content);
            System.out.println(nome + " salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar " + nome + ": " + e.getMessage());
        }
    }

    // pega missas do json
    private static List<Missa> pegaMissa() {
        try {
            File file = new File("C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\missas.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<List<Missa>>() {
            });
        } catch (Exception e) {
            System.out.println("Erro ao pegar missas: " + e.getMessage());
            return null;
        }

    }

    // pega acolitos do json
    private static List<Acolito> pegaAcolitos() {
        try {
            File file = new File(
                    "C:\\\\Users\\\\me250\\\\projetos\\\\codolito-master\\\\src\\\\main\\\\java\\\\JSON\\\\acolitos.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<List<Acolito>>() {
            });
        } catch (Exception e) {
            System.out.println("Erro ao pegar acólitos: " + e.getMessage());
            return null;
        }

    }

    // listar acólitos
    private static void listarAcolitos() {
        try {
            List<Acolito> acolitos = pegaAcolitos();

            if (acolitos == null || acolitos.isEmpty()) {
                System.out.println("Nenhum acólito cadastrado.");
                return;
            }

            for (Acolito acolito : acolitos) {
                acolito.apresenta();
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar acólitos: " + e.getMessage());
        }
    }

    // cadastrar Acolitos
    private static void cadastrarAcolito() {
        List<Acolito> acolitos = pegaAcolitos();
        Scanner scanner = new Scanner(System.in);
        Acolito acolito = new Acolito();
        System.out.println("\n\nCadastrar Acólito");
        System.out.println("Insira o nome do acólito:");
        acolito.setNome(scanner.nextLine());
        acolito.setDiasIndisponiveisFunction();
        acolito.setDisponibilidadeFunction();
        acolito.setPreferenciasFunction();
        acolitos.add(acolito);
        salvaJSON(acolitos, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\acolitos.json",
                "Acólito");
    }

    // cadastrar missas
    public static void adicionarMissa() {
        List<Missa> missas = pegaMissa();
        Scanner scanner = new Scanner(System.in);
        Missa missa = new Missa();
        System.out.println("\n\nCadastrar Missa");
        System.out.println("Insira o dia da missa:");
        missa.setDia(scanner.nextInt());
        scanner.nextLine();
        missa.setLocal();
        missa.setSemana();
        System.out.println("Insira o horário da missa:");
        missa.setTime(scanner.nextInt());
        Missa newmissa = new Missa(missa.getDia(), missa.getLocal(), missa.getSemana(), missa.getTime());
        missas.add(newmissa);
        salvaJSON(missas, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\missas.json", "Missa");
    }

    // listar missas
    public static void listarMissas() {
        try {
            List<Missa> missas = pegaMissa();

            if (missas.isEmpty()) {
                System.out.println("Nenhuma missa cadastrada.");
                return;
            }

            for (Missa missa : missas) {
                missa.apresenta();
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar missas: " + e.getMessage());
        }

    }

    // Fazer Escala
    public static void fazerEscala() {
        List<Missa> missas = pegaMissa();
        List<Acolito> acolitos = pegaAcolitos();
        Scanner scanner = new Scanner(System.in);

        if (acolitos.isEmpty()) {
            System.out.println("Nenhum acólito cadastrado.");
            return;
        }
        if (missas.isEmpty()) {
            System.out.println("Nenhuma missa cadastrada.");
            return;
        }

        for (Missa m : missas) {
            String tempo = "";
            ArrayList<String> disponiveis = new ArrayList<>();
            m.apresenta();
            int controler = 0;
            System.out.println("Acolitos disponíveis para esta missa:");
            for (Acolito a : acolitos) {
                a.cleanMissas();
                if (a.getDiasIndisponiveis().contains(m.getDia())) {
                    continue;
                }
                if (a.getDisponibilidade().get(m.getSemana()) == null) {
                    continue;
                }
                if (m.getSemana().equals("Domingo")) {
                    if (!a.getDisponibilidade().get(m.getSemana()).contains(String.valueOf(m.getTime()))) {
                        continue;
                    }
                } else {

                    if (m.getTime() >= 6 && m.getTime() < 12) {
                        tempo = "MANHA";
                    } else if (m.getTime() >= 12 && m.getTime() < 18) {
                        tempo = "TARDE";
                    } else {
                        tempo = "NOITE";
                    }
                    if (!a.getDisponibilidade().get(m.getSemana()).contains(tempo)) {
                        continue;
                    }
                }
                if (a.getPreferencias().get(m.getSemana()) != null) {
                    if (a.getPreferencias().get(m.getSemana()).contains(String.valueOf(m.getTime()))
                            || a.getPreferencias().get(m.getSemana()).contains(tempo)) {
                        System.out.printf("\n[%d]* " + a.getNome() + " (preferencia)", controler);
                        disponiveis.add(a.getNome());
                        controler++;
                        continue;
                    }
                }
                System.out.printf("\n[%d]- " + a.getNome(), controler);
                disponiveis.add(a.getNome());
                controler++;
            }
            System.out.println("\nSelecione o acólito para esta missa, separados por virgulas:");
            String resposta = scanner.nextLine();
            String[] partes = resposta.split(",");
            for (String parte : partes) {
                int index = Integer.parseInt(parte.trim());
                if (index >= 0 && index < disponiveis.size()) {
                    System.out.println(
                            "Acólito " + disponiveis.get(index) + " selecionado para a missa do dia " + m.getDia());
                    for (Acolito a : acolitos) {
                        if (a.getNome().equals(disponiveis.get(index))) {
                            a.setMissasUnica(m);
                        }
                    }
                } else {
                    System.out.println("Índice inválido: " + index);
                }
            }
        }

        for (Acolito a : acolitos) {
            System.out.println("\n\nAcólito: " + a.getNome());
            System.out.println("Missas designadas:");
            for (Missa m : a.getMissas()) {
                System.out.println("- Dia " + m.getDia() + " às " + m.getTime() + "h (" + m.getSemana() + ")");
            }
        }
    }

    // modelagem de cadastros missas fixas
    public static List<Missa> cadastrarMissas(String semana, int time, String[] dias) {
        List<Missa> missas = new ArrayList<>();
        for (String dia : dias) {
            if (dia == dias[0]) {
                Missa missa = new Missa(Integer.parseInt(dia.trim()), semana, time,
                        "primeira(o) " + semana + " do mês");
                missas.add(missa);
            } else {
                Missa missa = new Missa(Integer.parseInt(dia.trim()), semana, time, "nenhuma");
                missas.add(missa);
            }
        }
        return missas;
    }

    //pegar dias do mês para missas fixas
    public static String[] pegaDiasMes(int primeiroDia) {
        List<Integer> diasMes = new ArrayList<>();
        int dia = primeiroDia;
        while (dia <= 31) {
            diasMes.add(dia);
            dia += 7;
        }
        return diasMes.stream().map(String::valueOf).toArray(String[]::new);
    }

    // missas fixas cadastros
    public static void MissasFixas() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n\nCadastrar Missas Fixas:");
            List<Missa> missasFixas = new ArrayList<>();
            System.out.println("Começaremos pelo Domingo, qual dia cai o primeiro domingo? :");
            int respostaDias = sc.nextInt();
            String[] dias = pegaDiasMes(respostaDias);
            for (String dia : dias) {
                if (dia == dias[0]) {
                    Missa dom1 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 8, "nenhuma");
                    Missa dom2 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 10, "Missa da Família");
                    Missa dom3 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 18,
                            "Adoração ao Santíssimo Sacramento");
                    missasFixas.add(dom1);
                    missasFixas.add(dom2);
                    missasFixas.add(dom3);
                    continue;
                }
                if (dia == dias[2]) {
                    Missa dom1 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 8, "Missa do Dizimista");
                    Missa dom2 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 10, "Missa do Dizimista");
                    Missa dom3 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 18, "Missa do Dizimista");
                    missasFixas.add(dom1);
                    missasFixas.add(dom2);
                    missasFixas.add(dom3);
                    continue;
                }
                if (dia == dias[3]) {
                    Missa dom1 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 8, "nenhuma");
                    Missa dom2 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 10, "Batismos");
                    Missa dom3 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 18, "Missa Jovem");
                    missasFixas.add(dom1);
                    missasFixas.add(dom2);
                    missasFixas.add(dom3);
                    continue;
                }
                Missa dom1 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 8, "nenhuma");
                Missa dom2 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 10, "nenhuma");
                Missa dom3 = new Missa(Integer.parseInt(dia.trim()), "Domingo", 18, "nenhuma");
                missasFixas.add(dom1);
                missasFixas.add(dom2);
                missasFixas.add(dom3);
            }
            System.out.println("Agora as quartas, qual dia cai a primeira quarta?:");
            int respostaQuartas = sc.nextInt();
            String[] quartas = pegaDiasMes(respostaQuartas);
            missasFixas.addAll(cadastrarMissas("Quarta", 20, quartas));
            String[] sextas = pegaDiasMes(respostaQuartas + 2);
            missasFixas.addAll(cadastrarMissas("Sexta", 15, sextas));
            String[] sabados = pegaDiasMes(respostaQuartas + 3);
            missasFixas.addAll(cadastrarMissas("Sábado", 19, sabados));
            System.out.println("Missas à cadastrar:");
            missasFixas.sort(Comparator.comparing(Missa::getDia));
            for (Missa missa : missasFixas) {
                missa.apresenta();
            }
            System.out.println("Todas corretas? (true/false)");
            boolean resposta = sc.nextBoolean();
            if (resposta) {
                List<Missa> missas = pegaMissa();
                missas.addAll(missasFixas);
                salvaJSON(missas, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\missas.json",
                        "Missa Fixas");
                break;
            } else {
                System.out.println("Vamos refazer o processo.");
            }

        }

    }
}