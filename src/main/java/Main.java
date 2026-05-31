/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;

import root.Index;

/**
 *
 * @author me250
 */
public class Main extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        CadastrarMenu = new javax.swing.JMenu();
        FixasItem = new javax.swing.JMenuItem();
        AcolitosItem = new javax.swing.JMenuItem();
        MissasItem = new javax.swing.JMenuItem();
        ListarMenu = new javax.swing.JMenu();
        MissasListarItem = new javax.swing.JMenuItem();
        AcolitosListarItem = new javax.swing.JMenuItem();
        AtualItem = new javax.swing.JMenuItem();
        EscalaMenu = new javax.swing.JMenu();
        DeletarMenu = new javax.swing.JMenu();
        MissasDeletarItem = new javax.swing.JMenuItem();
        AcolitoDeletarItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        CadastrarMenu.setText("Cadastrar");

        FixasItem.setText("MissasFixas");
        FixasItem.addActionListener(this::FixasItemActionPerformed);
        CadastrarMenu.add(FixasItem);

        AcolitosItem.setText("Acólitos");
        AcolitosItem.addActionListener(this::AcolitosItemActionPerformed);
        CadastrarMenu.add(AcolitosItem);

        MissasItem.setText("Missas");
        CadastrarMenu.add(MissasItem);

        jMenuBar1.add(CadastrarMenu);

        ListarMenu.setText("Listar");

        MissasListarItem.setText("Missas");
        ListarMenu.add(MissasListarItem);

        AcolitosListarItem.setText("Acólitos");
        ListarMenu.add(AcolitosListarItem);

        AtualItem.setText("Escala Atual");
        AtualItem.addActionListener(this::AtualItemActionPerformed);
        ListarMenu.add(AtualItem);

        jMenuBar1.add(ListarMenu);

        EscalaMenu.setText("Fazer escala");
        jMenuBar1.add(EscalaMenu);

        DeletarMenu.setText("Deletar");

        MissasDeletarItem.setText("Missas");
        DeletarMenu.add(MissasDeletarItem);

        AcolitoDeletarItem.setText("Acolitos");
        DeletarMenu.add(AcolitoDeletarItem);

        jMenuBar1.add(DeletarMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AcolitosItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcolitosItemActionPerformed
        Cadastrar.Acolitos Acolitos = new Cadastrar.Acolitos();
        jDesktopPane1.add(Acolitos);
        Acolitos.setVisible(true);
    }//GEN-LAST:event_AcolitosItemActionPerformed

    private void FixasItemActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Cadastrar.MissasFixas MissasFixas = new Cadastrar.MissasFixas();
        jDesktopPane1.add(MissasFixas);
        MissasFixas.setVisible(true);
    }

    private void AtualItemActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Listar.EscalaAtual escalaAtual = new Listar.EscalaAtual();
        jDesktopPane1.add(escalaAtual);
        escalaAtual.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AcolitoDeletarItem;
    private javax.swing.JMenuItem AcolitosItem;
    private javax.swing.JMenuItem AcolitosListarItem;
    private javax.swing.JMenuItem AtualItem;
    private javax.swing.JMenu CadastrarMenu;
    private javax.swing.JMenu DeletarMenu;
    private javax.swing.JMenu EscalaMenu;
    private javax.swing.JMenuItem FixasItem;
    private javax.swing.JMenu ListarMenu;
    private javax.swing.JMenuItem MissasDeletarItem;
    private javax.swing.JMenuItem MissasItem;
    private javax.swing.JMenuItem MissasListarItem;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

}
