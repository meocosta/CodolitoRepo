public class Missa{
    private String id;
    private int dia;
    private int time;
    private String semana;
    private String local;

    public Missa(int dia, String local, String semana, int time) {
        this.id = "@"+dia+""+time;
        this.dia = dia;
        this.local = local;
        this.semana = semana;
        this.time = time;
    }

    public Missa(int dia, String semana, int time) {
        this.id = "@"+dia+""+time;
        this.dia = dia;
        this.semana = semana;
        this.time = time;
        this.local = "matriz";
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getLocal() {
        return local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }

}