package root;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Index {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println(
                    "\n\nBem-vinda ao Codolito\nO que deseja fazer?\n\n1.\tCadastrar\n2.\tListar\n3.\tFazer escala\n4.\tDeletar\n5.\tEscala atual \n6.\tSair\n\nResposta:");
            int resposta = sc.nextInt();
            switch (resposta) {
                case 1:
                    Cadastrar();
                    break;
                case 2:
                    Listar();
                    break;
                case 3:
                    fazerEscala();
                    break;
                case 4:
                    Deletar();
                    break;
                case 5:
                    EscalaAtual();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }

    // AÇÕES --------------------------------------------------------

    // Cadastrar Missas ou Acólitos
    public static void Cadastrar() {
        System.out.println("\n\nO que deseja cadastrar?\n1. Acólito\n2. Missa\n3. Missas Fixas");
        int resposta = sc.nextInt();
        switch (resposta) {
            case 2:
                adicionarMissa();
                break;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                break;
        }
    }

    // Listar Missas ou Acólitos
    public static void Listar() {
        System.out.println("\n\nO que deseja listar?\n1. Acólitos\n2. Missas");
        int resposta = sc.nextInt();
        switch (resposta) {
            case 1 -> {
                List<Acolito> acolitos = pegaAcolitos();
                if (acolitos == null || acolitos.isEmpty()) {
                    System.out.println("Nenhum acólito cadastrado.");
                    return;
                }
                for (Acolito a : acolitos) {
                    a.apresenta();
                }
            }
            case 2 -> {
                List<Missa> missas = pegaMissa();
                if (missas == null || missas.isEmpty()) {
                    System.out.println("Nenhuma missa cadastrada.");
                    return;
                }
                for (Missa m : missas) {
                    m.apresenta();
                }
            }
            default -> {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    // Deletar Missas ou Acólitos
    public static void Deletar() {
        System.out.println("O que deseja deletar?\n1. Missa\n2. Acólito\n3. Limpar Missas Fixas");
        int resposta = sc.nextInt();
        switch (resposta) {
            case 1:
                List<Missa> Missas = pegaMissa();
                for (Missa m : Missas) {
                    m.apresenta();
                }
                System.out.println("\n\nDigite o ID da missa que deseja deletar:");
                int id = sc.nextInt();
                for (Missa m : Missas) {
                    if (m.getId().equals("@" + id)) {
                        Missas.remove(m);
                        break;
                    }
                }
                salvaJSON(Missas, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\missas.json",
                        "Missas");
                break;

            case 2:
                List<Acolito> Acolitos = pegaAcolitos();
                for (Acolito a : Acolitos) {
                    a.apresenta();
                }
                System.out.println("\n\nDigite o nome do acólito que deseja deletar:");
                sc.nextLine(); // limpa buffer
                String nome = sc.nextLine();
                for (Acolito a : Acolitos) {
                    if (a.getNome().equals(nome)) {
                        Acolitos.remove(a);
                        break;
                    }
                }
                salvaJSON(Acolitos, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\acolitos.json",
                        "Acólitos");
                break;

            case 3:
                limparMissasFixas();
                break;

            default:
                break;
        }
    }

    // ESCALAS --------------------------------------------------------

    private static void limparMissasFixas() {
        List<Missa> missas = pegaMissa();
        missas.removeAll(missas);
        salvaJSON(missas, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\missas.json",
                "Missas");
    }

    // Fazer Escala
    public static void fazerEscala() {
        List<Missa> missas = pegaMissa();
        List<Acolito> acolitos = pegaAcolitos();

        if (acolitos.isEmpty() || missas.isEmpty()) {
            System.out.println("\n\nNenhum acólito ou missa cadastrada.");
            return;
        }

        System.out.println("Vamos atualizar os dias disponiveis para cada acólito antes de começar a escala.");
        List<Acolito> atualizaAcolitos = new ArrayList<>();
        for (Acolito c : acolitos) {
            c.cleanMissas();
            System.out.println("\n\nAcólito: " + c.getNome());
            c.setDiasIndisponiveisFunction();
            atualizaAcolitos.add(c);
        }
        salvaJSON(atualizaAcolitos, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\acolitos.json",
                "Acólito");
        acolitos = atualizaAcolitos;

        for (Missa m : missas) {
            String tempo = "";
            ArrayList<String> disponiveis = new ArrayList<>();
            m.apresenta();
            int controler = 0;
            System.out.println("Acolitos disponíveis para esta missa:");
            for (Acolito a : acolitos) {
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
                System.out.printf("\n[%d]- " + a.getNome(), controler);
                disponiveis.add(a.getNome());
                controler++;
            }
            if(disponiveis.isEmpty()){
                System.out.println("\nNenhum acólito disponível para esta missa.");
                continue;
            }
            System.out.println("\nSelecione o acólito para esta missa, separados por virgulas:");
            sc.nextLine(); // limpa buffer
            String resposta = sc.nextLine();
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

    // Listar Escala Atual
    public static List<String[]> EscalaAtual() {
        List<Acolito> acolitos = pegaAcolitos();
        List<String[]> escala = new ArrayList<>();
        List<Missa> missas = pegaMissa();
        if (acolitos.isEmpty() || missas.isEmpty()) {
            System.out.println("\n\nNenhum acólito ou missa cadastrada.");
            return new ArrayList<>();
        }
        for (Missa m : pegaMissa()) {
            String[] missa = new String[9];
            missa[0] = "" + m.getDia();
            missa[1] = m.getSemana();
            missa[2] = "" + m.getTime()+" hrs";
            missa[3] = m.getLocal();
            String[] acolitoDesignado = new String[4];
            int contador = 0;
            for (Acolito a : acolitos) {
                if (a.getMissas().contains(m)) {
                    acolitoDesignado[contador] = a.getNome();
                    contador++;
                }
            }
            missa[4] = acolitoDesignado[0] != null ? acolitoDesignado[0] : "-";
            missa[5] = acolitoDesignado[1] != null ? acolitoDesignado[1] : "-";
            missa[6] = acolitoDesignado[2] != null ? acolitoDesignado[2] : "-";
            missa[7] = acolitoDesignado[3] != null ? acolitoDesignado[3] : "-";
            missa[8] = m.getCelebrações() != "nenhuma" ? m.getCelebrações() : "-"; // observações
            escala.add(missa);
        }
        return escala;
    }

    // MODELAGENS --------------------------------------------------------

    // modelagem para salvar jsons
    public static void salvaJSON(Object content, String filePath, String nome) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), content);
            String msg = nome + " salvo com sucesso!";
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            String msg = "Erro ao salvar " + nome + ": " + e.getMessage();
             JOptionPane.showMessageDialog(null, msg);
        }
    }

    // pega missas do json
    public static List<Missa> pegaMissa() {
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
    public static List<Acolito> pegaAcolitos() {
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

    // cadastrar missas
    public static void adicionarMissa() {
        List<Missa> missas = pegaMissa();
        Missa missa = new Missa();
        System.out.println("\n\nCadastrar Missa");
        System.out.println("Insira o dia da missa:");
        missa.setDia(sc.nextInt());
        sc.nextLine();
        missa.setLocal();
        missa.setSemana();
        System.out.println("Insira o horário da missa:");
        missa.setTime(sc.nextInt());
        Missa newmissa = new Missa(missa.getDia(), missa.getLocal(), missa.getSemana(), missa.getTime());
        missas.add(newmissa);
        salvaJSON(missas, "C:\\Users\\me250\\projetos\\codolito-master\\src\\main\\java\\JSON\\missas.json", "Missa");
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

}