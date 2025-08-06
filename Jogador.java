import java.util.ArrayList;

public class Jogador {
    int soma;
    int qntAs;
    ArrayList<Carta> mao; 

    public Jogador(){
        soma = 0;
        qntAs = 0;
        mao = new ArrayList<Carta>();
    }
    
    public void puxarCarta(Carta carta){
        mao.add(carta);
        soma += carta.getValor();

        if(carta.eAs()){
            qntAs++;
        }

        reduzirAs();
    }

    public int reduzirAs(){
        while (soma > 21 && qntAs > 0){
            soma -= 10;
            qntAs -= 1;
        }
        return soma;
    }
}
