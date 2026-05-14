import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Acolito {

    Scanner sc = new Scanner(System.in);
    private String nome;
    private List<Integer> diasdisponiveisNumeros;
    private Map<String, List<String>> disponibilidade;
    private Map<String, List<String>> preferencias;

    public Map<String, List<String>> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Map<String, List<String>> preferencias) {
        this.preferencias = preferencias;
    }

    public Acolito(String nome, List<Integer> diasdisponiveisNumeros, Map<String, List<String>> disponibilidade) {
        this.nome = nome;
        this.diasdisponiveisNumeros = diasdisponiveisNumeros;
        this.disponibilidade = disponibilidade;
    }

    public Acolito(String nome, List<Integer> diasdisponiveisNumeros, Map<String, List<String>> disponibilidade,
            Map<String, List<String>> preferencias) {
        this.nome = nome;
        this.diasdisponiveisNumeros = diasdisponiveisNumeros;
        this.disponibilidade = disponibilidade;
        this.preferencias = preferencias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getDiasdisponiveisNumeros() {
        return diasdisponiveisNumeros;
    }

    public void setDiasdisponiveisNumeros(List<Integer> diasdisponiveisNumeros) {
        this.diasdisponiveisNumeros = diasdisponiveisNumeros;
    }

    public Map<String, List<String>> getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Map<String, List<String>> disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void pegaDiasdisponiveisNumeros() {
        System.out.println("\n\nInsira os dias que o acolito nao pode servir separados por virgulas:");
        String diasString = sc.nextLine();
        String[] diasStringpartes = diasString.split(",");
        List<Integer> lista = new ArrayList<>();
        for (String p : diasStringpartes) {
            lista.add(Integer.parseInt(p.trim()));
        }
        this.setDiasdisponiveisNumeros(lista);
    }

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
                    String[] periodos = {"MANHA", "TARDE", "NOITE"};
                    adicionarSemDuplicar(selecionados, dia, periodos[periodoIndex]);
                } else {
                    String[] horarios = {"08", "10", "18"};
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

    private void adicionarSemDuplicar(Map<String, List<String>> selecionados, String dia, String horario) {
        selecionados.putIfAbsent(dia, new ArrayList<>());

        if (!selecionados.get(dia).contains(horario)) {
            selecionados.get(dia).add(horario);
        }
    }

    public void pegarDisponibilidade() {
        this.setDisponibilidade(this.selecionaDias("em que pode servir"));
    }

    public void pegarPreferencias() {
        this.setPreferencias(this.selecionaDias("em que prefere servir"));
    }
}
