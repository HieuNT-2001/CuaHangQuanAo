/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import dao.ChiTietSPDAO;
import dao.KichThuocDAO;
import dao.MauSacDAO;
import entity.ChiTietSP;
import entity.KichThuoc;
import entity.MauSac;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;
import utils.Ximage;

/**
 *
 * @author Admin
 */
public class ChiTietSPJDialog extends javax.swing.JDialog {

    private final int labelWidth = 160;
    private final int labelHeight = 120;
    private int maSP = 0;
    ChiTietSPDAO ctspDAO = new ChiTietSPDAO();
    MauSacDAO msDAO = new MauSacDAO();
    KichThuocDAO ktDAO = new KichThuocDAO();

    /**
     * Creates new form ChiTietSPJDialog
     * @param parent
     * @param modal
     * @param maSP
     */
    public ChiTietSPJDialog(java.awt.Frame parent, boolean modal, int maSP) {
        super(parent, modal);
        this.maSP = maSP;
        initComponents();
        fillComboBoxMauSac();
        fillComboBoxKichThuoc();
        fillTable();
    }

    private void fillComboBoxMauSac() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMauSac.getModel();
        model.removeAllElements();
        List<MauSac> list = msDAO.SelectAll();
        for (MauSac ms : list) {
            model.addElement(ms);
        }
    }

    private void fillComboBoxKichThuoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKichThuoc.getModel();
        model.removeAllElements();
        List<KichThuoc> list = ktDAO.SelectAll();
        for (KichThuoc kt : list) {
            model.addElement(kt);
        }
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblChiTietSP.getModel();
        model.setRowCount(0);
        List<ChiTietSP> list = ctspDAO.selectByMaSP(this.maSP);
        for (ChiTietSP ctsp : list) {
            Object[] rowData = {
                ctsp.getId(),
                msDAO.SelectById(ctsp.getMaMS()).getMauSac(),
                ktDAO.SelectById(ctsp.getMaKT()).getKichThuoc(),
                ctsp.getHinh()
            };
            model.addRow(rowData);
        }
    }

    private void insert() {
        if (checkNull()) {
            ChiTietSP ctsp = getForm();
            ctspDAO.insert(ctsp);
            MsgBox.alert(this, "Thêm chi tiết sản phẩm thành công!");
            fillTable();
        }
    }

    private void update(int row) {
        int id = (int) tblChiTietSP.getValueAt(row, 0);
        ChiTietSP ctsp = ctspDAO.selectById(id);

        MauSac ms = (MauSac) cboMauSac.getSelectedItem();
        KichThuoc kt = (KichThuoc) cboKichThuoc.getSelectedItem();
        ctsp.setMaSP(this.maSP);
        ctsp.setMaMS(ms.getMaMS());
        ctsp.setMaKT(kt.getMaKT());
        ctsp.setHinh(lblHinh.getToolTipText());

        ctspDAO.update(ctsp);
        MsgBox.alert(this, "Sửa chi tiết sản phẩm thành công!");
        fillTable();
    }

    private void delete(int row) {
        int id = (int) tblChiTietSP.getValueAt(row, 0);
        ctspDAO.delete(id);
        MsgBox.alert(this, "Xóa chi tiết sản phẩm thành công!");
        fillTable();
    }

    private ChiTietSP getForm() {
        ChiTietSP ctsp = new ChiTietSP();
        MauSac ms = (MauSac) cboMauSac.getSelectedItem();
        KichThuoc kt = (KichThuoc) cboKichThuoc.getSelectedItem();
        ctsp.setMaSP(this.maSP);
        ctsp.setMaMS(ms.getMaMS());
        ctsp.setMaKT(kt.getMaKT());
        ctsp.setHinh(lblHinh.getToolTipText());
        return ctsp;
    }

    private void setForm(int row) {
        int id = (int) tblChiTietSP.getValueAt(row, 0);
        ChiTietSP ctsp = ctspDAO.selectById(id);
        MauSac ms = msDAO.SelectById(ctsp.getMaMS());
        KichThuoc kt = ktDAO.SelectById(ctsp.getMaKT());

        cboMauSac.setSelectedItem(ms);
        cboKichThuoc.setSelectedItem(kt);
        lblHinh.setToolTipText(ctsp.getHinh());
        lblHinh.setIcon(Ximage.read(ctsp.getHinh(), labelWidth, labelHeight));
    }

    private void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Ximage.save(file); // Lưu hình ảnh vào thư mục images
            ImageIcon icon = Ximage.read(file.getName(), labelWidth, labelHeight);
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName());
        }
    }

    private boolean checkNull() {
        if (lblHinh.getToolTipText() == null) {
            MsgBox.alert(this, "Bạn chưa chọn ảnh!");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        lblTieuDe = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        lblMauSac = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblHinh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi tiết sản phẩm");

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(51, 153, 255));
        lblTieuDe.setText("Chi tiết sản phẩm");

        lblMauSac.setText("Màu sắc:");

        lblKichThuoc.setText("Kích thước:");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Màu sắc", "Kích thước", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTieuDe)
                .addGap(239, 239, 239))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa))
                    .addComponent(cboKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTieuDe)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)))
                    .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblChiTietSP.getSelectedRow();
        if (row >= 0) {
            update(row);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblChiTietSP.getSelectedRow();
        if (row >= 0) {
            delete(row);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        // TODO add your handling code here:
        int row = tblChiTietSP.getSelectedRow();
        if (row >= 0) {
            setForm(row);
        }
    }//GEN-LAST:event_tblChiTietSPMouseClicked

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
            java.util.logging.Logger.getLogger(ChiTietSPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSPJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietSPJDialog dialog = new ChiTietSPJDialog(new javax.swing.JFrame(), true, 0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JTable tblChiTietSP;
    // End of variables declaration//GEN-END:variables
}
