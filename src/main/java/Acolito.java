import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Acolito {

    // Variáveis

    Scanner sc = new Scanner(System.in);
    private String nome;
    private List<Integer> DiasIndisponiveis;
    private Map<String, List<String>> disponibilidade;
    private Map<String, List<String>> preferencias;
    private List<Missa> missas = new ArrayList<>();

    // Construtores

    // construtor vazio
    public Acolito() {
    }

    // construtor sem preferencias
    public Acolito(String nome, List<Integer> DiasIndisponiveis, Map<String, List<String>> disponibilidade) {
        this.nome = nome;
        this.DiasIndisponiveis = DiasIndisponiveis;
        this.disponibilidade = disponibilidade;
    }

    // construtor completo
    public Acolito(String nome, List<Integer> DiasIndisponiveis, Map<String, List<String>> disponibilidade,
            Map<String, List<String>> preferencias) {
        this.nome = nome;
        this.DiasIndisponiveis = DiasIndisponiveis;
        this.disponibilidade = disponibilidade;
        this.preferencias = preferencias;
    }

    // Getters

    public List<Missa> getMissas() {
        return missas;
    }

    public Map<String, List<String>> getPreferencias() {
        return preferencias;
    }

    public String getNome() {
        return nome;
    }

    public List<Integer> getDiasIndisponiveis() {
        return DiasIndisponiveis;
    }

    public Map<String, List<String>> getDisponibilidade() {
        return disponibilidade;
    }

    // Setters

    public void setPreferencias(Map<String, List<String>> preferencias) {
        this.preferencias = preferencias;
    }

    public void setPreferenciasFunction() {
        this.preferencias = this.selecionaDias("em que prefere servir");
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiasIndisponiveis(List<Integer> DiasIndisponiveis) {
        this.DiasIndisponiveis = DiasIndisponiveis;
    }

    public void setDiasIndisponiveisFunction() {
        System.out.println("\n\nInsira os dias que o acolito nao pode servir separados por virgulas:");
        String diasString = sc.nextLine();
        String[] diasStringpartes = diasString.split(",");
        List<Integer> lista = new ArrayList<>();
        for (String p : diasStringpartes) {
            lista.add(Integer.parseInt(p.trim()));
        }
        this.DiasIndisponiveis = lista;
    }

    public void setDisponibilidade(Map<String, List<String>> disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setDisponibilidadeFunction() {
        this.disponibilidade = this.selecionaDias("em que pode servir");
    }

    public void setMissas(List<Missa> missas) {
        this.missas = missas;
    }

    public void setMissasUnica(Missa missa) {
        missas.add(missa);
    }

    // Modelagens

    // método de selecionar os dias e horários que o acólito pode ou prefere servir
    public Map<String, List<String>> selecionaDias(String descricao) {
        Map<String, List<String>> selecionados = new HashMap<>();
        System.out.println("Dias da semana e suas correspondencias:");
        String[] semana = {
                "Segunda", "Terca", "Quarta",
                "Quinta", "Sexta", "Sabado", "Domingo"
        };
        for (int i = 0; i < semana.length; i++) {
            System.out.println(semana[i] + ":");

            if (i != 6) {
                System.out.println("\t[" + (i * 3) + "] Manha");
                System.out.println("\t[" + (i * 3 + 1) + "] Tarde");
                System.out.println("\t[" + (i * 3 + 2) + "] Noite\n");
            } else {
                System.out.println("\t[" + (i * 3) + "] 08hrs");
                System.out.println("\t[" + (i * 3 + 1) + "] 10hrs");
                System.out.println("\t[" + (i * 3 + 2) + "] 18hrs\n");
            }
        }
        System.out.printf("Digite as correspondencias %s separadas por virgula:", descricao);
        String entrada = sc.nextLine();
        String[] partes = entrada.split(",");

        for (String p : partes) {
            try {
                int id = Integer.parseInt(p.trim());

                if (id < 0 || id > 20) {
                    throw new IllegalArgumentException("ID fora do intervalo: " + id);
                }

                int diaIndex = id / 3;
                int periodoIndex = id % 3;
                String dia = semana[diaIndex];

                if (diaIndex != 6) {
                    String[] periodos = { "MANHA", "TARDE", "NOITE" };
                    adicionarSemDuplicar(selecionados, dia, periodos[periodoIndex]);
                } else {
                    String[] horarios = { "08", "10", "18" };
                    adicionarSemDuplicar(selecionados, dia, horarios[periodoIndex]);
                }

            } catch (NumberFormatException e) {
                System.out.println("Valor invalido (nao e numero): " + p);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return selecionados;
    }

    // modelagem para não duplicar dias
    private void adicionarSemDuplicar(Map<String, List<String>> selecionados, String dia, String horario) {
        selecionados.putIfAbsent(dia, new ArrayList<>());

        if (!selecionados.get(dia).contains(horario)) {
            selecionados.get(dia).add(horario);
        }
    }

    // limpa as missas
    public void cleanMissas() {
        missas = new ArrayList<>();
    }

    public void apresentaDias(Map<String, List<String>> dias) {
        for (Map.Entry<String, List<String>> entry : dias.entrySet()) {
            String dia = entry.getKey();
            List<String> horarios = entry.getValue();

            System.out.println("\t"+dia + ":");

            for (String horario : horarios) {
                System.out.println("\t\t - " + horario);
            }
        }
    }

    public void apresenta() {
        System.out.println("\n\nNome: " + this.getNome());
        System.out.println("Dias Indisponíveis: " + this.getDiasIndisponiveis());
        System.out.println("Disponibilidade: ");
        this.apresentaDias(this.getDisponibilidade());
        System.out.println("Preferências: ");
        this.apresentaDias(this.getPreferencias());
        if (missas != null && !missas.isEmpty()) {

            System.out.println("\nMissas:");

            for (Missa missa : missas) {

                System.out.println(
                        "- Dia "
                                + missa.getDia()
                                + " de "
                                + missa.getSemana()
                                + " às "
                                + missa.getTime()
                                + "hrs");
            }

        } else {

            System.out.println("\nNenhuma missa cadastrada.");
        }
    }
}
