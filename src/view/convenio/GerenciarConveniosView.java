/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.convenio;

import model.bean.ConvenioBean;
import controller.ConvenioController;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.jtable.ConvenioTableModel;
import util.GerViewUtil;
import util.MaskFormatTextUtil;

/**
 *
 * @author vande
 */
public class GerenciarConveniosView extends javax.swing.JFrame {

    public static ConvenioTableModel tablemodelConvenio = null;
    private ConvenioBean conv = null;

    /**
     * Creates new form GerenciarConvenios
     */
    public GerenciarConveniosView() {
        initComponents();
        jtConvenios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defColumnFuncionarioTable();
        defConvenioTableModelBySearch();
    }

    /**
     * Método que define o tamanho das colunas da tabela de convênios.
     */
    private void defColumnFuncionarioTable() {
        jtConvenios.getColumnModel().getColumn(0).setPreferredWidth(100);
        jtConvenios.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtConvenios.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtConvenios.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    /**
     * Método usado para quando digitado um valor no campo de busca de
     * convênios, além de setar um model para tabela.
     */
    private void defConvenioTableModelBySearch() {
        tablemodelConvenio = new ConvenioTableModel(txtSearchConvenio.getText());
        jtConvenios.setModel(tablemodelConvenio);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpGerConvenios = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearchConvenio = new javax.swing.JTextField();
        btnCadConvenio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtConvenios = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jmbGerConvenios = new javax.swing.JMenuBar();
        jmSobre = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cila's System - Gerenciar convênios");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Nome:");

        txtSearchConvenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchConvenioKeyReleased(evt);
            }
        });

        btnCadConvenio.setText("CADASTRAR NOVO");
        btnCadConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadConvenioActionPerformed(evt);
            }
        });

        jtConvenios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtConvenios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtConveniosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtConvenios);

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGerConveniosLayout = new javax.swing.GroupLayout(jpGerConvenios);
        jpGerConvenios.setLayout(jpGerConveniosLayout);
        jpGerConveniosLayout.setHorizontalGroup(
            jpGerConveniosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGerConveniosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGerConveniosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jpGerConveniosLayout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpGerConveniosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCadConvenio, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpGerConveniosLayout.setVerticalGroup(
            jpGerConveniosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGerConveniosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGerConveniosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadConvenio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGerConveniosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jmSobre.setText("Sobre");
        jmbGerConvenios.add(jmSobre);

        setJMenuBar(jmbGerConvenios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGerConvenios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGerConvenios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadConvenioActionPerformed
        // TODO add your handling code here:
        CadastrarConvenioView cad = new CadastrarConvenioView(this, true);
        cad.setVisible(true);
    }//GEN-LAST:event_btnCadConvenioActionPerformed

    private void txtSearchConvenioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchConvenioKeyReleased
        // TODO add your handling code here:
        defConvenioTableModelBySearch();
        defColumnFuncionarioTable();
    }//GEN-LAST:event_txtSearchConvenioKeyReleased

    private void jtConveniosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtConveniosMouseClicked
        // TODO add your handling code here:
        conv = new ConvenioBean();
        conv.setNome((String) jtConvenios.getValueAt(jtConvenios.getSelectedRow(), 0));
        conv.setCnpj(MaskFormatTextUtil.onlyNumbers((String) jtConvenios.getValueAt(jtConvenios.getSelectedRow(), 1)));
        conv.setTelefone((String) jtConvenios.getValueAt(jtConvenios.getSelectedRow(), 2));
        conv.setEmail((String) jtConvenios.getValueAt(jtConvenios.getSelectedRow(), 3));
    }//GEN-LAST:event_jtConveniosMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (jtConvenios.getSelectedRowCount() != 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o convênio " + conv.getNome() + "?");
            switch (confirm) {
                case JOptionPane.YES_OPTION:
                    ConvenioController.deleteFuncionario(conv);
                    break;
                default:
                    break;
            }
        } else {
            conv = null;
            JOptionPane.showMessageDialog(null, "Nenhum convênio selecionado.");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (jtConvenios.getSelectedRowCount() != 0) {
            EditarConvenioView edi = new EditarConvenioView(this, true, conv);
            edi.setVisible(true);
        } else {
            conv = null;
            JOptionPane.showMessageDialog(null, "Nenhum convênio foi selecionado.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciarConveniosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarConveniosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarConveniosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarConveniosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarConveniosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadConvenio;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu jmSobre;
    private javax.swing.JMenuBar jmbGerConvenios;
    private javax.swing.JPanel jpGerConvenios;
    private javax.swing.JTable jtConvenios;
    private javax.swing.JTextField txtSearchConvenio;
    // End of variables declaration//GEN-END:variables
}
