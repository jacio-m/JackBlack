package Telas;

import Classes.*;
import java.awt.*;
import javax.swing.*;

public class TelaInicio extends javax.swing.JFrame {
    private UsuarioCRUD rg;
    public static String usuarioSelecionado;
    //a variavel pra pegar o usuario selecionado na tabela, é usado em todas as telas
    
    public TelaInicio() {

        ImageIcon imgBg = new ImageIcon(getClass().getResource("BG.png"));//pra definir o background
        JLabel lbBg = new JLabel(imgBg);
        lbBg.setLayout(new BorderLayout());
        setContentPane(lbBg);
        
        initComponents();
        setVisible(true);
        Image IconeApp = new ImageIcon(getClass().getResource("BACK.png")).getImage(); //o icone do jogo
        setIconImage(IconeApp);
        
        rg = new UsuarioCRUD();
        SetarLbBemVindo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitulo = new javax.swing.JLabel();
        btnJogar = new javax.swing.JButton();
        btnPlacar = new javax.swing.JButton();
        lbBemVindo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu JackBlack");
        setBackground(new java.awt.Color(0, 153, 0));
        setLocation(new java.awt.Point(0, 0));
        setName("menu"); // NOI18N
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Showcard Gothic", 0, 48)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setText("BlackJack");
        lbTitulo.setFocusable(false);
        lbTitulo.setName(""); // NOI18N

        btnJogar.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnJogar.setText("Jogar");
        btnJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJogarActionPerformed(evt);
            }
        });

        btnPlacar.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnPlacar.setText("Placar");
        btnPlacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlacarActionPerformed(evt);
            }
        });

        lbBemVindo.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lbBemVindo.setForeground(new java.awt.Color(255, 255, 153));
        lbBemVindo.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPlacar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(lbTitulo)))
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBemVindo)
                .addGap(72, 72, 72)
                .addComponent(btnJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnPlacar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void SetarLbBemVindo(){
        if(rg.listar().isEmpty()){//se n tiver usuario nos arquivos
            lbBemVindo.setText("");
            return;
        }
        if(usuarioSelecionado == null || usuarioSelecionado.isEmpty())
            usuarioSelecionado = rg.listar().get(0).getNome();
        
        lbBemVindo.setText("Bem vindo, "+usuarioSelecionado+"!");
    }
    
    private void btnJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJogarActionPerformed
        if(rg.listar().isEmpty()){//se n tiver usuario nos arquivos
            JOptionPane.showMessageDialog(this, "Crie um usuário na tela de placar antes de iniciar o jogo.");
            return;
        }
        if(usuarioSelecionado == null || usuarioSelecionado.isEmpty()){
            usuarioSelecionado = rg.listar().get(0).getNome();
        }
        new Jogo();
        dispose();
    }//GEN-LAST:event_btnJogarActionPerformed

    private void btnPlacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlacarActionPerformed
       new Telas.TelaPlacar();
       dispose();
    }//GEN-LAST:event_btnPlacarActionPerformed

    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJogar;
    private javax.swing.JButton btnPlacar;
    private javax.swing.JLabel lbBemVindo;
    private javax.swing.JLabel lbTitulo;
    // End of variables declaration//GEN-END:variables
}
