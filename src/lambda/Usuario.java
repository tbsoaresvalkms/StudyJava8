package lambda;

import java.util.Comparator;

public class Usuario implements Comparable<Usuario> {
    private String nome;
    private int pontos;
    private boolean moderador;

    public Usuario(String nome, int pontos, boolean moderador) {
        this.nome = nome;
        this.pontos = pontos;
        this.moderador = moderador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public boolean isModerador() {
        return moderador;
    }

    public void setModerador() {
        this.moderador = true;
    }

    @Override
    public int compareTo(Usuario o) {
        if (moderador || o.moderador) {
            return moderador ? -1 : 1;
        }
        return nome.compareTo(o.nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}
