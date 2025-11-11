package Classes;

import Telas.TelaInicio;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; 
import java.util.Random; 
import javax.swing.*; 

public class Jogo {
    
    UsuarioCRUD rg = new UsuarioCRUD();
    ArrayList<Carta> baralho; 
    Random aleatorio = new Random(); 

    Carta cartaEscondida; 
    Jogador banca = new Jogador();
    Jogador jogador = new Jogador();

    int janelaLargura = 600;
    int janelaAltura = janelaLargura;
    
    int cartaLargura = 110; 
    int cartaAltura = 154;

    JFrame janela = new JFrame("JackBlack");
    JPanel painelJogo = new JPanel() {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            try {
                Image bg = null;
                try {
                    bg = new ImageIcon(getClass().getResource("BG.png")).getImage();
                } catch (Exception ignored) {}
                if (bg != null) g.drawImage(bg,0,0,getWidth(), getHeight(), null);

                Image IconeApp = null;
                try {
                    IconeApp = new ImageIcon(getClass().getResource("./cartas/BACK.png")).getImage();
                } catch (Exception ignored) {}
                if (IconeApp != null) janela.setIconImage(IconeApp);

                Image cartaEscondidaImg = null;
                try {
                    cartaEscondidaImg = new ImageIcon(getClass().getResource("./cartas/BACK.png")).getImage();
                } catch (Exception ignored) {}
                if (!botaoFicar.isEnabled() && cartaEscondida != null) {
                    try {
                        Image tmp = new ImageIcon(getClass().getResource(cartaEscondida.getLocalImagem())).getImage();
                        if (tmp != null) cartaEscondidaImg = tmp;
                    } catch (Exception ignored) {}
                }
                if (cartaEscondidaImg != null) g.drawImage(cartaEscondidaImg, 20, 20, cartaLargura, cartaAltura, null);

                for (int i = 0; i < banca.mao.size(); i++){
                    Carta carta = banca.mao.get(i);
                    try {
                        Image imgCarta = new ImageIcon(getClass().getResource(carta.getLocalImagem())).getImage();
                        g.drawImage(imgCarta, cartaLargura + 25 + (cartaLargura + 5)*i, 20, cartaLargura, cartaAltura, null);
                    } catch (Exception ex) {
                       
                    }
                }

                for(int i = 0; i < jogador.mao.size(); i++){
                    Carta carta = jogador.mao.get(i);
                    try {
                        Image imgCarta = new ImageIcon(getClass().getResource(carta.getLocalImagem())).getImage();
                        g.drawImage(imgCarta, 20 + (cartaLargura + 5) * i, 320, cartaLargura, cartaAltura, null);
                    } catch (Exception ex) {
                        
                    }
                }
                
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(Color.WHITE);
                int posXBanca = cartaLargura - 95; 
                int posYBanca = 20 + cartaAltura + 25; //um pouco abaixo das cartas
                if(botaoFicar.isEnabled())
                    g.drawString("Crupie: ?", posXBanca, posYBanca);

                //soma do jogador abaixo das cartas dele
                String somaJogadorStr = "Você: " + jogador.soma;
                int posXJogador = 20; 
                int posYJogador = 320 + cartaAltura + 25; 
                g.drawString(somaJogadorStr, posXJogador, posYJogador);
                
                
                if(!botaoFicar.isEnabled()){
                    banca.reduzirAs();
                    jogador.reduzirAs();
                    
                    g.drawString("Soma banca: " + banca.soma, posXBanca, posYBanca);
                    String mensagem = "";
                    int resultado = 0; //1 = jogador ganha, -1 = jogador perde, 0 = empate

                    if(jogador.soma > 21){
                        mensagem = "Você perdeu! Você estourou!";
                        resultado = -1;
                    } else if (banca.soma > 21){
                        mensagem = "Você ganhou! A banca estourou!";
                        resultado = 1;
                    } else if(jogador.soma > banca.soma){
                        mensagem = "Você ganhou! Você superou a banca!";
                        resultado = 1;
                    } else if(jogador.soma < banca.soma){
                        mensagem = "Você perdeu! A banca te superou!";
                        resultado = -1;
                    } else { // igual
                        mensagem = "Empate!";
                        resultado = 0;
                    }

                    g.setFont(new Font("Arial", Font.PLAIN, 20));
                    g.setColor(Color.white);
                    g.drawString(mensagem, 220, 250);

                    //registra resultado apenas uma vez
                    if (!resultadoRegistrado) {
                        resultadoRegistrado = true;
                        registrarResultado(resultado);
                        botaoReiniciar.setEnabled(true);
                        botaoMenu.setEnabled(true);
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    };
    JPanel painelBotao = new JPanel();
    JButton botaoPuxar = new JButton("Puxar +1");
    JButton botaoFicar = new JButton("Ficar");
    JButton botaoReiniciar = new JButton("Reiniciar");
    JButton botaoMenu = new JButton("Menu Principal");

    private boolean resultadoRegistrado = false;

    public Jogo(){
        comecarJogo();

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setSize(janelaLargura, janelaAltura);
        janela.setLocationRelativeTo(null);

        painelJogo.setLayout(new BorderLayout());
        painelJogo.setBackground(new Color(100,100,100));
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
        botaoMenu.setEnabled(false);

        botaoPuxar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (baralho.isEmpty()) return;
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
        
        botaoMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                janela.dispose();
                new TelaInicio();
            }
        });

        botaoFicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                botaoPuxar.setEnabled(false);
                botaoFicar.setEnabled(false);

                while(banca.soma < 17){
                    if (baralho.isEmpty()) break;
                    Carta carta = baralho.remove(baralho.size()-1);
                    banca.puxarCarta(carta);
                }
                painelJogo.repaint();
            }
        });
        painelJogo.repaint();

         botaoReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                janela.dispose();
                new Jogo();
            }
        });

        janela.setVisible(true);
    }

    private void registrarResultado(int resultado) {
        String nome = TelaInicio.usuarioSelecionado;
        if (nome == null || nome.trim().isEmpty()) {
            //tenta primeiro da lista se não houver usuario selecionado
            ArrayList<Usuario> lista = rg.listar();
            if (lista != null && !lista.isEmpty()) {
                nome = lista.get(0).getNome();
            } else {
                return; //não tem usuario para registrar
            }
        }

        switch (resultado) {
            case 1:
                rg.registrarVitoria(nome);
                break;
            case -1:
                rg.registrarDerrota(nome);
                break;
            case 0:
                rg.registrarEmpate(nome);
                break;
            default:
                break;
        }
    }

    public void comecarJogo(){
        resultadoRegistrado = false;
        fazerBaralho();       
        embaralharBaralho(); 

        if (baralho == null || baralho.size() < 5) {
            fazerBaralho();
            embaralharBaralho();
        }

        cartaEscondida = baralho.remove(baralho.size()-1); 
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

        //deixa os botões ativados no início
        botaoPuxar.setEnabled(true);
        botaoFicar.setEnabled(true);
        botaoReiniciar.setEnabled(false);
        botaoMenu.setEnabled(false);
    }

    public void fazerBaralho(){
        baralho = new ArrayList<Carta>();

        String [] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String [] tipos = {"Paus", "Espadas", "Copas", "Ouros"};

        for (int i = 0; i < tipos.length; i++){
            for(int j = 0; j < valores.length; j++){
                Carta carta = new Carta(valores[j], tipos[i]);
                baralho.add(carta);
            }
        }
    }

    public void embaralharBaralho(){
        for (int i = 0; i < baralho.size(); i++){
            int j = aleatorio.nextInt(baralho.size());
            Carta cartaAtual = baralho.get(i);
            Carta cartaAleatoria = baralho.get(j);
            baralho.set(i, cartaAleatoria);
            baralho.set(j, cartaAtual);
        }
    }
}