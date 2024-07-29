/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JPanel;
import utils.Auth;
import utils.MsgBox;

/**
 *
 * @author HP
 */
public class HomeJFrame extends javax.swing.JFrame {

    private JPanel childPanel;

    /**
     * Creates new form NewJFrame
     */
    public HomeJFrame() {
        initComponents();
        showPanel(new ThongKeJPanel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNavigate = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        btnNV = new javax.swing.JButton();
        btnSP = new javax.swing.JButton();
        btnNCC = new javax.swing.JButton();
        btnKM = new javax.swing.JButton();
        btnBH = new javax.swing.JButton();
        btnHD = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        btnSignout = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        panelMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        panelNavigate.setBackground(new java.awt.Color(0, 204, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Cửa hàng");
        lbTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnNV.setBackground(new java.awt.Color(0, 204, 255));
        btnNV.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnNV.setForeground(new java.awt.Color(255, 255, 255));
        btnNV.setText("Nhân viên");
        btnNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNVActionPerformed(evt);
            }
        });

        btnSP.setBackground(new java.awt.Color(0, 204, 255));
        btnSP.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnSP.setForeground(new java.awt.Color(255, 255, 255));
        btnSP.setText("Sản phẩm");
        btnSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPActionPerformed(evt);
            }
        });

        btnNCC.setBackground(new java.awt.Color(0, 204, 255));
        btnNCC.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnNCC.setText("Nhà cung cấp");
        btnNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNCCActionPerformed(evt);
            }
        });

        btnKM.setBackground(new java.awt.Color(0, 204, 255));
        btnKM.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnKM.setForeground(new java.awt.Color(255, 255, 255));
        btnKM.setText("Khuyến mãi");
        btnKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKMActionPerformed(evt);
            }
        });

        btnBH.setBackground(new java.awt.Color(0, 204, 255));
        btnBH.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnBH.setForeground(new java.awt.Color(255, 255, 255));
        btnBH.setText("Bán hàng");
        btnBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBHActionPerformed(evt);
            }
        });

        btnHD.setBackground(new java.awt.Color(0, 204, 255));
        btnHD.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnHD.setForeground(new java.awt.Color(255, 255, 255));
        btnHD.setText("Hóa đơn");
        btnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDActionPerformed(evt);
            }
        });

        btnTK.setBackground(new java.awt.Color(0, 204, 255));
        btnTK.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnTK.setForeground(new java.awt.Color(255, 255, 255));
        btnTK.setText("Thống kê");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        btnSignout.setBackground(new java.awt.Color(0, 204, 255));
        btnSignout.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnSignout.setForeground(new java.awt.Color(255, 255, 255));
        btnSignout.setText("Đăng xuất");
        btnSignout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignoutActionPerformed(evt);
            }
        });

        btnChangePass.setBackground(new java.awt.Color(0, 204, 255));
        btnChangePass.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnChangePass.setForeground(new java.awt.Color(255, 255, 255));
        btnChangePass.setText("Đổi mật khẩu");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNavigateLayout = new javax.swing.GroupLayout(panelNavigate);
        panelNavigate.setLayout(panelNavigateLayout);
        panelNavigateLayout.setHorizontalGroup(
            panelNavigateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSignout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        panelNavigateLayout.setVerticalGroup(
            panelNavigateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNavigateLayout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(btnSignout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNavigate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNavigate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNVActionPerformed
        // TODO add your handling code here:
        if (Auth.isAdmin()) {
            showPanel(new PanelNV());
        } else {
            MsgBox.alert(this, "Bạn có không quyền truy cập chức năng này!");
        }
    }//GEN-LAST:event_btnNVActionPerformed

    private void btnSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPActionPerformed
        // TODO add your handling code here:
        if (Auth.isAdmin()) {
            showPanel(new PanelSP());
        } else {
            MsgBox.alert(this, "Bạn có không quyền truy cập chức năng này!");
        }
    }//GEN-LAST:event_btnSPActionPerformed

    private void btnNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNCCActionPerformed
        // TODO add your handling code here
        if (Auth.isAdmin()) {
            showPanel(new PanelNCC());
        } else {
            MsgBox.alert(this, "Bạn có không quyền truy cập chức năng này!");
        }
    }//GEN-LAST:event_btnNCCActionPerformed

    private void btnKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKMActionPerformed
        // TODO add your handling code here:
        if (Auth.isAdmin()) {
            showPanel(new PanelKM());
        } else {
            MsgBox.alert(this, "Bạn có không quyền truy cập chức năng này!");
        }
    }//GEN-LAST:event_btnKMActionPerformed

    private void btnBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBHActionPerformed
        // TODO add your handling code here:
        showPanel(new BanHangJPanel());
    }//GEN-LAST:event_btnBHActionPerformed

    private void btnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDActionPerformed
        // TODO add your handling code here:
        showPanel(new PanelHoaDon());
    }//GEN-LAST:event_btnHDActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
        showPanel(new ThongKeJPanel());
    }//GEN-LAST:event_btnTKActionPerformed

    private void btnSignoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignoutActionPerformed
        // TODO add your handling code here:
        DangNhapJFrame DnJFrame = new DangNhapJFrame();
        DnJFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSignoutActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        // TODO add your handling code here:
        showPanel(new DoiMatKhauJPanel());
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void showPanel(JPanel panel) {
        childPanel = panel;
        panelMain.removeAll();
        panelMain.add(childPanel);
        panelMain.validate();
    }

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBH;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnHD;
    private javax.swing.JButton btnKM;
    private javax.swing.JButton btnNCC;
    private javax.swing.JButton btnNV;
    private javax.swing.JButton btnSP;
    private javax.swing.JButton btnSignout;
    private javax.swing.JButton btnTK;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelNavigate;
    // End of variables declaration//GEN-END:variables
}
