/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.*;
import entity.*;
import dao.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

/**
 *
 * @author HP
 */
public class PanelSP extends javax.swing.JPanel {

    int index = -1;
    SanPhamDAO spd = new SanPhamDAO();
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    DanhMucDAO dmDAO = new DanhMucDAO();

    /**
     * Creates new form PanelSP
     */
    public PanelSP() {
        initComponents();
        this.fillComboBoxNCC();
        this.fillComboBoxDM();
        this.fillTable();
    }

    private void fillComboBoxNCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboxNCC.getModel();
        model.removeAllElements();
        List<NhaCungCap> list = nccDAO.select();
        for (NhaCungCap ncc : list) {
            model.addElement(ncc);
        }
    }

    private void fillComboBoxDM() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboxCategory.getModel();
        model.removeAllElements();
        List<DanhMuc> list = dmDAO.selectAll();
        for (DanhMuc dm : list) {
            model.addElement(dm);
        }
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tbSP.getModel();
        model.setRowCount(0);
        List<SanPham> lsp = spd.selectByName(1, searchBar.getText());
        for (SanPham sp : lsp) {
            Object data[] = {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getDonGia(),
                sp.getSoLuong(),
                nccDAO.selectById(sp.getMaNCC()).getTenNCC(),
                dmDAO.selectById(sp.getMaDM()).getDanhMuc()
            };
            model.addRow(data);
        }
    }

    private void setModel(SanPham sp) {
        fillMaSP.setText(String.valueOf(sp.getMaSP()));
        fillNameSP.setText(sp.getTenSP());
        fillPrice.setText(Double.toString(sp.getDonGia()));
        fillQuantity.setText(String.valueOf(sp.getSoLuong()));
        cboxNCC.setToolTipText(String.valueOf(sp.getMaNCC()));
        cboxCategory.setToolTipText(String.valueOf(sp.getMaDM()));
    }

    private SanPham getModel() {
        SanPham sp = new SanPham();
        if (fillMaSP.getText().isBlank()) {
            sp.setMaSP(0);
        } else {
            sp.setMaSP(Integer.parseInt(fillMaSP.getText()));
        }
        sp.setTenSP(fillNameSP.getText());
        sp.setDonGia(Double.parseDouble(fillPrice.getText()));
        sp.setSoLuong(Integer.parseInt(fillQuantity.getText()));
        NhaCungCap ncc = (NhaCungCap) cboxNCC.getSelectedItem();
        DanhMuc dm = (DanhMuc) cboxCategory.getSelectedItem();
        sp.setMaNCC(ncc.getMaNCC());
        sp.setMaDM(dm.getMaDM());
        sp.setTrangThai(true);
        return sp;
    }

    void clear() {
        fillMaSP.setText("");
        fillNameSP.setText("");
        fillPrice.setText("");
        fillQuantity.setText("");
        cboxNCC.setToolTipText("");
        cboxCategory.setToolTipText("");
    }

    void insert() {
        if (checkNull()) {
            SanPham sp = getModel();
            try {
                spd.insert(sp);
                fillTable();
                clear();
                this.index = -1;
                MsgBox.alert(this, "Thêm sản phẩm thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm sản phẩm thất bại");
            }
        }
    }

    private void update() {
        if (MsgBox.confirm(this, "Bạn thật sự muốn sửa sản phẩm này ") && checkNull()) {
            SanPham sp = getModel();
            try {
                spd.update(sp);
                fillTable();
                clear();
                this.index = -1;
                MsgBox.alert(this, "Sửa thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Sửa thất bại");
            }
        }
    }

    void delete() {
        if (MsgBox.confirm(this, "Bạn thật sự muốn xóa sản phẩm này ")) {
            int maSP = Integer.parseInt(fillMaSP.getText());
            try {
                spd.delete(maSP);
                fillTable();
                clear();
                this.index = -1;
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại");
            }
        }
    }
    
    private void anSanPham(int index) {
        int maSP = (int) tbSP.getValueAt(index, 0);
        SanPham sp = spd.selectById(maSP);
        sp.setTrangThai(false);
        spd.update(sp);
        this.index = -1;
        fillTable();
    }

    void edit() {
        try {
            int maSP = (int) tbSP.getValueAt(this.index, 0);
            SanPham sp = spd.selectById(maSP);
//            if (sp != null) {
//                this.setModel(sp);
//            }
            this.setModel(sp);
        } catch (Exception e) {
            System.out.println("Không thể truy cập dữ liệu");
        }
    }

    public boolean checkNull() {
        if (fillNameSP.getText().isBlank()) {
            return false;
        } else if (fillPrice.getText().isBlank()) {
            return false;
        } else if (fillQuantity.getText().isBlank()) {
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

        lbTitle = new javax.swing.JLabel();
        lbNameSP = new javax.swing.JLabel();
        fillNameSP = new javax.swing.JTextField();
        lbPrice = new javax.swing.JLabel();
        fillPrice = new javax.swing.JTextField();
        lbQuantity = new javax.swing.JLabel();
        fillQuantity = new javax.swing.JTextField();
        lbMaSP = new javax.swing.JLabel();
        fillMaSP = new javax.swing.JTextField();
        lbNCC = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        cboxNCC = new javax.swing.JComboBox<>();
        cboxCategory = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        lbSearch = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSP = new javax.swing.JTable();
        btnHiddenList = new javax.swing.JButton();
        btnHideSP = new javax.swing.JButton();
        btnTTSP = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTitle.setText("Sản phẩm");

        lbNameSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbNameSP.setText("Tên sản phẩm");

        fillNameSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbPrice.setText("Đơn giá");

        fillPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbQuantity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbQuantity.setText("Số lượng");

        fillQuantity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbMaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMaSP.setText("Mã sản phẩm");

        fillMaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        fillMaSP.setEnabled(false);

        lbNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbNCC.setText("Nhà cung cấp");

        lbCategory.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbCategory.setText("Danh mục");

        cboxNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboxCategory.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/New_1.png"))); // NOI18N
        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbSearch.setText("Tìm kiếm");

        tbSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Nhà cung cấp", "Danh mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSP);

        btnHiddenList.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnHiddenList.setText("Danh sách SP ẩn");
        btnHiddenList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHiddenListActionPerformed(evt);
            }
        });

        btnHideSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnHideSP.setText("Ẩn sản phẩm");
        btnHideSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHideSPActionPerformed(evt);
            }
        });

        btnTTSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTTSP.setText("Thuộc tính SP");
        btnTTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTTSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHideSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHiddenList))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNameSP)
                                        .addComponent(lbPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fillNameSP, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fillPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(400, 400, 400)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fillQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(400, 400, 400)
                                        .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fillMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchBar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fillMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbMaSP)
                        .addComponent(lbNCC)
                        .addComponent(cboxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fillNameSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameSP)
                    .addComponent(lbCategory)
                    .addComponent(cboxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fillPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPrice)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fillQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQuantity)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBar)
                    .addComponent(lbSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHiddenList)
                    .addComponent(btnHideSP)
                    .addComponent(btnTTSP))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (this.index >= 0) {
            update();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (this.index >= 0) {
            delete();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tbSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPMouseClicked
        // TODO add your handling code here:
        this.index = tbSP.getSelectedRow();
        if (this.index >= 0) {
            edit();
        }
        if (evt.getClickCount() == 2) {
            
        }
    }//GEN-LAST:event_tbSPMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnHideSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHideSPActionPerformed
        // TODO add your handling code here:
        this.index = tbSP.getSelectedRow();
        if (this.index >= 0) {
            anSanPham(index);
        }
    }//GEN-LAST:event_btnHideSPActionPerformed

    private void btnHiddenListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHiddenListActionPerformed
        // TODO add your handling code here:
        new SanPhamAn(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btnHiddenListActionPerformed

    private void btnTTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTSPActionPerformed
        // TODO add your handling code here:
        new ThuocTinhSPJDialog(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btnTTSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHiddenList;
    private javax.swing.JButton btnHideSP;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTTSP;
    private javax.swing.JComboBox<String> cboxCategory;
    private javax.swing.JComboBox<String> cboxNCC;
    private javax.swing.JTextField fillMaSP;
    private javax.swing.JTextField fillNameSP;
    private javax.swing.JTextField fillPrice;
    private javax.swing.JTextField fillQuantity;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbNCC;
    private javax.swing.JLabel lbNameSP;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbQuantity;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable tbSP;
    // End of variables declaration//GEN-END:variables
}
