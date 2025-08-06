import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; //vamos usar a arraylist (lista de vetores) para a mão do player, já que com isso podemos armazenar objetos
import java.util.Random; //vamos usar o random para embaralhar o baralho
import javax.swing.*; //vamos usar o swing pra interface grafica


public class Jogo {
    
    ArrayList<Carta> baralho; //para criar o baralho, criamos uma arraylist que vai servir justamente pra armazenar as cartas (objetos)
    Random aleatorio = new Random(); //criando um objeto aleatorio, vamos usar para embaralhar

    //seção para a máquina do jogo (banca, adversario)
    Carta cartaEscondida; //essa variavel serve pra ter uma carta escondida pra a banca, pra o jogo ter mais emocao para o jogador nao saber toda a mao da maquina
    Jogador banca = new Jogador();

    //instanciando o jogador
    Jogador jogador = new Jogador();

    //secao para a janela do jogo
    int janelaLargura = 600;
    int janelaAltura = janelaLargura;
    
    int cartaLargura = 110; //a proporcao deve ser 1/1.4
    int cartaAltura = 154;

    JFrame janela = new JFrame("JackBlack");
    JPanel painelJogo = new JPanel() {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            try {
            //puxar carta escondida
            Image IconeApp = new ImageIcon(getClass().getResource("./cartas/BACK.png")).getImage();
            Image cartaEscondidaImg = new ImageIcon(getClass().getResource("./cartas/BACK.png")).getImage();
            if (!botaoFicar.isEnabled()){
                cartaEscondidaImg = new ImageIcon(getClass().getResource(cartaEscondida.getLocalImagem())).getImage();
            }
            g.drawImage(cartaEscondidaImg, 20, 20, cartaLargura, cartaAltura, null);
            janela.setIconImage(IconeApp);

            //puxando mão da banca
            for (int i = 0; i < banca.mao.size(); i++){
                Carta carta = banca.mao.get(i);
                Image imgCarta = new ImageIcon(getClass().getResource(carta.getLocalImagem())).getImage();
                g.drawImage(imgCarta, cartaLargura + 25 + (cartaLargura + 5)*i, 20, cartaLargura, cartaAltura, null);
            }

            //puxando a mão do jogador
            for(int i = 0; i < jogador.mao.size(); i++){
                Carta carta = jogador.mao.get(i);
                Image imgCarta = new ImageIcon(getClass().getResource(carta.getLocalImagem())).getImage();
                g.drawImage(imgCarta, 20 + (cartaLargura + 5) * i, 320, cartaLargura, cartaAltura, null);
            }

            if(!botaoFicar.isEnabled()){
                banca.reduzirAs();
                jogador.reduzirAs();

                String mensagem = "";
                
                if(jogador.soma > 21){
                    mensagem = "Você perdeu! Você estourou!";
                    botaoReiniciar.setEnabled(true);
                }else if (banca.soma > 21){
                    mensagem = "Você ganhou! A banca estourou!";
                    botaoReiniciar.setEnabled(true);
                }else if(jogador.soma > banca.soma){
                    mensagem = "Você ganhou! Você superou a banca!";
                    botaoReiniciar.setEnabled(true);
                }else if(jogador.soma < banca.soma){
                    mensagem = "Você perdeu! A banca te superou!";
                    botaoReiniciar.setEnabled(true);
                }else if(jogador.soma == banca.soma){
                    mensagem = "Empate!";
                    botaoReiniciar.setEnabled(true);
                }

                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.setColor(Color.white);
                g.drawString(mensagem, 220, 250);
            }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
    JPanel painelBotao = new JPanel();
    JButton botaoPuxar = new JButton("Puxar +1");
    JButton botaoFicar = new JButton("Ficar");
    JButton botaoReiniciar = new JButton("Reiniciar");
    JButton botaoMenu = new JButton("Menu Principal");


    Jogo(){ //quando instanciado a classe jogo, acessa a função de comecar o jogo 
        comecarJogo();

        janela.setVisible(true);
        janela.setSize(janelaLargura, janelaAltura);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelJogo.setLayout(new BorderLayout());
        painelJogo.setBackground(new Color(53,101,77));
        janela.add(painelJogo);

        botaoPuxar.setFocusable(false);
        painelBotao.add(botaoPuxar);
        botaoFicar.setFocusable(false);
        painelBotao.add(botaoFicar);
        botaoReiniciar.setFocusable(false);
        painelBotao.add(botaoReiniciar);
        botaoMenu.setFocusable(false);
        painelBotao.add(botaoMenu);
        janela.add(painelBotao, BorderLayout.SOUTH);
        botaoReiniciar.setEnabled(false);

        botaoPuxar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Carta carta = baralho.remove(baralho.size()-1);
                jogador.soma += carta.getValor();
                jogador.qntAs += carta.eAs()? 1 : 0;
                jogador.mao.add(carta);
                if (jogador.reduzirAs() > 21){
                    botaoPuxar.setEnabled(false);
                    botaoFicar.setEnabled(false);
                } 
                painelJogo.repaint();
            }
        });

        botaoFicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                botaoPuxar.setEnabled(false);
                botaoFicar.setEnabled(false);

                while(banca.soma < 17){
                    Carta carta = baralho.remove(baralho.size()-1);
                    banca.puxarCarta(carta);
                }
                painelJogo.repaint();
            }
        });
        painelJogo.repaint();

         botaoReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                janela.setVisible(false);
                Jogo jogo = new Jogo();
            }
        });
    }
       

    public void comecarJogo(){//metodo responsavel por começar o jogo, onde vai ser criado o baralho de cartas, vai ser embaralhado e depois dada 2 cartas à banca e 2 cartas ao usuario
        //comeca chamando os metodos pra arrumar o baralho
        fazerBaralho();//metodo responsavel que no começo do jogo, um novo baralho seja feito        
        embaralharBaralho(); //metodo responsavel que embaralhe o baralho

        cartaEscondida = baralho.remove(baralho.size()-1); //remove a ultima carta do baralho
        banca.soma += cartaEscondida.getValor();
        banca.qntAs += cartaEscondida.eAs() ? 1 : 0;
        Carta carta = baralho.remove(baralho.size()-1);
        banca.soma += carta.getValor();
        banca.mao.add(carta);

        for (int i = 0; i < 2; i++){
            carta = baralho.remove(baralho.size()-1);
            jogador.soma += carta.getValor();
            jogador.qntAs += carta.eAs() ? 1 : 0;
            jogador.mao.add(carta);
        }
    }

    public void fazerBaralho(){//metodo para fazer o baralho
        baralho = new ArrayList<Carta>(); //basicamente aqui diz que o nosso baralho é uma nova arraylist (lista dinamica) que vai guardar cartas. 
                                          //e aqui para fazer o metodo de fazer baralho funcionar, precisamos listar todos os tipos e valores de cartas usando o "for" para que passe pelos tipos e valores para criar combinacoes (ex: 10 de espadas, 5 de copas...)

        String [] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; //aqui criamos um vetor de String para armazenar os valores 
        String [] tipos = {"Paus", "Espadas", "Copas", "Ouros"}; //aqui criamos um vetor de String para armazenar os tipos

        //agora vamos para os "for" que vão fazer as combinações (ex: 2 de paus, 3 de paus...). ele vai fazendo combinações de um em um até formar o baralho
        for (int i = 0; i < tipos.length; i++){ // basicamente aqui diz "enquanto i for menor que o tamnho do vetor de tipos, faça oq tiver abaixo e adicione +1 pra i". vai repetir até i chegar do tamnho do vetor, que é quandoa acaba.
            for(int j = 0; j < valores.length; j++){//mesma explicação de cima, só que com j e usando o tamanho do vetor de valores
                Carta carta = new Carta(valores[j], tipos[i]); //quando a carta é criada, ela vai assumir o valor que estiver na variavel j e o tipo que estiver na variavel i (ex: quando j for 0 e i for 2, ele vai pegar o valor de string na posição 0 ("A") e pegar o tipo de string na posicao 2 ("Copas"))
                baralho.add(carta); //só adiciona a carta que acabou de ser criada no baralho (arraylist)
            }
        }
    }

    public void embaralharBaralho(){ //metodo que para embaralhar nosso baralho, vamos passar passar de uma em uma carta do baralho, escolher uma aleatoria e trocar elas duas de lugar
        for (int i = 0; i < baralho.size(); i++){
            int j = aleatorio.nextInt(baralho.size()); // aqui definimos que a variavel j vai assumir um valor aleatorio até o tamanho do baralho
            Carta cartaAtual = baralho.get(i); //basicamente aqui a carta atual (carta que estamos mexendo agora), vai ser pegada o valor dela
            Carta cartaAleatoria = baralho.get(j); // //aqui a carta aleatoria vai assumir um valor aleatorio de j, e esse valor aleatorio vai equivaler à carta do baralho
            baralho.set(i, cartaAleatoria);
            baralho.set(j, cartaAtual);
        }
    }
}