package Classes;

import java.io.Serializable;

public class Usuario extends Jogador implements Serializable {

    private String nome;
    private int vitorias;
    private int derrotas;
    private int empates;

    //construtor que aprendemos
    public Usuario(String nome) {
        this.nome = nome;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
    }

    //construtor completo, caso precise depois
    public Usuario(String nome,int vitorias, int derrotas, int empates) {
        this.nome = nome;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getVitorias() {
        return vitorias;
    }
    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }
    public int getDerrotas() {
        return derrotas;
    }
    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    public int getEmpates() {
        return empates;
    }
    public void setEmpates(int empates) {
        this.empates = empates;
    }

    
    public void addVitoria() {
        vitorias++;
    }
    public void addDerrota() {
        derrotas++;
    }
    public void addEmpate() {
        empates++;
    }

    @Override
    public String toString() {
        return nome + ";" + vitorias + ";" + derrotas + ";" + empates;
    }
    
    //dentro do arquivo tem que ter todas as variaveis no mesmo arquivo então precisa de um método
    //pra desfazer isso. E separar cada dado em suas correspondentes variaveis temporarias
    public static Usuario fromString(String linha) {
        String[] partes = linha.split(";");
        String nome = partes.length > 0 ? partes[0] : "";
        int vitorias = 0;
        int derrotas = 0;
        int empates = 0;

        try {
            if (partes.length > 1) vitorias = Integer.parseInt(partes[1]);
            if (partes.length > 2) derrotas = Integer.parseInt(partes[2]);
            if (partes.length > 3) empates = Integer.parseInt(partes[3]);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter números no fromString do Usuario");
        }

        return new Usuario(nome, vitorias, derrotas, empates);
    }
}
