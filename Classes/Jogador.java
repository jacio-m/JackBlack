package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogador implements Serializable {
    protected int soma;
    protected int qntAs;
    protected ArrayList<Carta> mao; 

    public Jogador() {
        soma = 0;
        qntAs = 0;
        mao = new ArrayList<>();
    }

    public int getSoma() {
        return soma;
    }

    public void setSoma(int soma) {
        this.soma = soma;
    }

    public int getQntAs() {
        return qntAs;
    }

    public void setQntAs(int qntAs) {
        this.qntAs = qntAs;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void setMao(ArrayList<Carta> mao) {
        this.mao = mao;
    }
    
    public void puxarCarta(Carta carta) {
        mao.add(carta);
        soma += carta.getValor();

        if (carta.eAs()) {
            qntAs++;
        }

        reduzirAs();
    }

    public int reduzirAs() {
        while (soma > 21 && qntAs > 0) {
            soma -= 10;
            qntAs -= 1;
        }
        return soma;
    }
}