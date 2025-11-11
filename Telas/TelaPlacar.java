package Telas;

import Classes.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaPlacar extends javax.swing.JFrame {
    private UsuarioCRUD rg;
    static String usuarioSelecionado;
    private String c; 
    public TelaPlacar() {
        ImageIcon imgBg = new ImageIcon(getClass().getResource("BG.png"));
        JLabel lbBg = new JLabel(imgBg);
        lbBg.setLayout(new BorderLayout());
        setContentPane(lbBg);
        
        initComponents();
        
        setVisible(true); 
        tbUsuarios.setShowGrid(true);
        tbUsuarios.setGridColor(Color.GRAY);
        Image IconeApp = new ImageIcon(getClass().getResource("BACK.png")).getImage();
        setIconImage(IconeApp);
        rg = new UsuarioCRUD(); 
        atualizarTabela();  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbPlacar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        btnCriar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Placar JackBlack");
        setName("janela"); // NOI18N
        setResizable(false);

        lbPlacar.setFont(new java.awt.Font("Showcard Gothic", 0, 48)); // NOI18N
        lbPlacar.setForeground(new java.awt.Color(255, 255, 255));
        lbPlacar.setText("Placar");

        tbUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        tbUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Vitorias", "Derrotas", "Empates"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsuarios.setAutoscrolls(false);
        tbUsuarios.setShowHorizontalLines(false);
        tbUsuarios.setShowVerticalLines(false);
        tbUsuarios.getTableHeader().setReorderingAllowed(false);
        tbUsuarios.setUpdateSelectionOnSort(false);
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbUsuarios);
        tbUsuarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbUsuarios.getColumnModel().getColumnCount() > 0) {
            tbUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(3).setResizable(false);
        }

        btnCriar.setText("Criar Usuario");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        btnDeletar.setText("Deletar Usuario");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnMenu.setText("Menu Principal");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(btnCriar)
                .addGap(81, 81, 81)
                .addComponent(btnDeletar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(btnMenu)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lbPlacar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lbPlacar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeletar)
                    .addComponent(btnMenu)
                    .addComponent(btnCriar))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void selecionarLinha(int a) {
        if(tbUsuarios.getRowCount() != 0){
            tbUsuarios.setRowSelectionInterval(a, a);
            TelaInicio.usuarioSelecionado = tbUsuarios.getValueAt(a, 0).toString();
        } else {
            TelaInicio.usuarioSelecionado = "";
        }
    }
    
    private void atualizarTabela() {
        ArrayList<Usuario> usuarios = rg.listar();
        DefaultTableModel modelo = (DefaultTableModel) tbUsuarios.getModel();
        modelo.setRowCount(0);

        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        tbUsuarios.getColumnModel().getColumn(1).setCellRenderer(centro);
        tbUsuarios.getColumnModel().getColumn(2).setCellRenderer(centro);
        tbUsuarios.getColumnModel().getColumn(3).setCellRenderer(centro);

        for (Usuario u : usuarios) {
            Object[] linha = {
                u.getNome(),
                u.getVitorias(),
                u.getDerrotas(),
                u.getEmpates()
            };
            modelo.addRow(linha);
        }
    }
    
    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        new TelaInicio();
        dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do jogador:", 
                                                     "Cadastro", JOptionPane.QUESTION_MESSAGE);
        if (nome != null && !nome.trim().isEmpty()) {
            rg.cadastrar(nome);
            atualizarTabela();
            selecionarLinha(tbUsuarios.getRowCount() - 1);
        } else {
            JOptionPane.showMessageDialog(null, "Nome inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCriarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        int linhaSelecionada = tbUsuarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String nome = tbUsuarios.getValueAt(linhaSelecionada, 0).toString();
            rg.deletar(nome);
            atualizarTabela();
            selecionarLinha(0);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para deletar");
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked
        int linhaSelecionada = tbUsuarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            TelaInicio.usuarioSelecionado = tbUsuarios.getValueAt(linhaSelecionada, 0).toString();
            JOptionPane.showMessageDialog(this, "Usuário selecionado: " + TelaInicio.usuarioSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!");
        }
    }//GEN-LAST:event_tbUsuariosMouseClicked

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
            java.util.logging.Logger.getLogger(TelaPlacar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPlacar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPlacar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPlacar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPlacar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbPlacar;
    private javax.swing.JTable tbUsuarios;
    // End of variables declaration//GEN-END:variables
}
