package root;
import java.util.Scanner;

public class Missa {

    // Variáveis
    private String id;
    private final static Scanner sc = new Scanner(System.in);
    private int dia;
    private int time;
    private String semana;
    private String local;
    private String celebrações;

    // CONSTRUTORES

    // construtor vazio
    public Missa() {
    }

    public Missa(int dia, String local, String semana, int time) {
        this.id = "@" + dia + "" + time;
        this.dia = dia;
        this.local = local;
        this.semana = semana;
        this.time = time;
    }

    public Missa(int dia, String semana, int time, String celebrações) {
        this.id = "@" + dia + "" + time;
        this.dia = dia;
        this.semana = semana;
        this.time = time;
        this.local = "matriz";
        this.celebrações = celebrações;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        if (dia < 1 || dia > 31) {
            System.out.println("Dia inválido. O dia deve ser entre 1 e 31.");
            return;
        }
        this.dia = dia;
    }

    public int getTime() {
        return time;
    }

    public String getSemana() {
        return semana;
    }

    public String getLocal() {
        return local;
    }

    public String getCelebrações() {
        return celebrações;
    }

    // SETTERS
    public void setTime(int time) {
        this.time = time;
    }

    public void setSemana() {
        String[] semana = {
                "Segunda", "Terca", "Quarta",
                "Quinta", "Sexta", "Sabado", "Domingo"
        };
        System.out.println(
                "Selecione a semana:\n1. Segunda\n2. Terça\n3. Quarta\n4. Quinta\n5. Sexta\n6. Sábado\n7. Domingo");
        int resposta = sc.nextInt();
        this.semana = semana[resposta - 1];
    }

    public void setLocal() {
        System.out.println("Selecione o local:\n1. Matriz\n2. Nª Sª Fátima\n3. Santa Filomena");
        int resposta = sc.nextInt();
        switch (resposta) {
            case 1:
                this.local = "matriz";
                break;
            case 2:
                this.local = "fatima";
                break;
            case 3:
                this.local = "filomena";
                break;
            default:
                this.local = "matriz";
        }
    }

    public void setCelebrações() {
        System.out.println("Tem alguma celebração?\n1. Casamento\n2. Batismo\n 3. Adoração ao Santíssimo\n4. Nenhuma");
        int resposta = sc.nextInt();
        switch (resposta) {
            case 1:
                this.celebrações = "casamento";
                break;
            case 2:
                this.celebrações = "batismo";
                break;
            case 3:
                this.celebrações = "adoracao";
                break;
            default:
                this.celebrações = "nenhuma";
        }
    }

    //FUNCTIONS

     public void apresenta() {
        System.out.println("\n\nMissa ID: " + this.id);
        System.out.println("Dia: " + this.dia);
        System.out.println("Semana: " + this.semana);
        System.out.println("Horário: " + this.time);
        System.out.println("Local: " + this.local);
        System.out.println("Celebrações: " + this.celebrações);
    }


}