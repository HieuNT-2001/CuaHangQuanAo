/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author HP
 */
public class PanelNV extends javax.swing.JPanel {

    /**
     * Creates new form PanelNV
     */
    public PanelNV() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbTitle = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        fillMaNV = new javax.swing.JTextField();
        lbTenDangNhap = new javax.swing.JLabel();
        fillTenDangNhap = new javax.swing.JTextField();
        lbConfirmPass = new javax.swing.JLabel();
        fillConfirmPass = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        fillPass = new javax.swing.JTextField();
        lbBirthyear = new javax.swing.JLabel();
        fillBirthyear = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        fillName = new javax.swing.JTextField();
        lbMail = new javax.swing.JLabel();
        fillMail = new javax.swing.JTextField();
        lbPhone = new javax.swing.JLabel();
        fillPhone = new javax.swing.JTextField();
        lbCCCD = new javax.swing.JLabel();
        fillCCCD = new javax.swing.JTextField();
        lbVaiTro = new javax.swing.JLabel();
        radAdmin = new javax.swing.JRadioButton();
        radNV = new javax.swing.JRadioButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNV = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTitle.setText("Nhân viên");

        lbMaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMaNV.setText("Mã nhân viên");

        fillMaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTenDangNhap.setText("Tên đăng nhập");

        fillTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbConfirmPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbConfirmPass.setText("Xác nhận MK");

        fillConfirmPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbPass.setText("Mật khẩu");

        fillPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbBirthyear.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbBirthyear.setText("Năm sinh");

        fillBirthyear.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbName.setText("Họ tên");

        fillName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbMail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMail.setText("Email");

        fillMail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbPhone.setText("SĐT");

        fillPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbCCCD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbCCCD.setText("CCCD");

        fillCCCD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbVaiTro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVaiTro.setText("Vai trò");

        buttonGroup1.add(radAdmin);
        radAdmin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        radAdmin.setText("Quản lý");

        buttonGroup1.add(radNV);
        radNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        radNV.setText("Nhân viên");

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAdd.setText("Thêm");

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEdit.setText("Sửa");

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDelete.setText("Xóa");

        btnNew.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnNew.setText("Mới");

        tbNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên đăng nhập", "Mật khẩu", "Họ tên", "Năm sinh", "Email", "SĐT", "CCCD", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbNV);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lbPass)
                                            .addGap(47, 47, 47)
                                            .addComponent(fillPass, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lbMaNV)
                                            .addGap(18, 18, 18)
                                            .addComponent(fillMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbConfirmPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbName)
                                    .addGap(66, 66, 66)
                                    .addComponent(fillName, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbBirthyear, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbPhone)
                                        .addComponent(lbCCCD))
                                    .addGap(71, 71, 71)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(fillPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lbMail, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(fillCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lbVaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(radAdmin)
                                    .addGap(18, 18, 18)
                                    .addComponent(radNV))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fillMail, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fillTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fillConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fillBirthyear, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fillMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMaNV)
                            .addComponent(lbTenDangNhap))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fillPass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPass)
                            .addComponent(lbConfirmPass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fillName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbName)
                            .addComponent(lbBirthyear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fillPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPhone)
                            .addComponent(lbMail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fillCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCCCD)
                            .addComponent(lbVaiTro)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAdd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDelete)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnNew))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(fillTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fillConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fillBirthyear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fillMail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radAdmin)
                                .addComponent(radNV)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField fillBirthyear;
    private javax.swing.JTextField fillCCCD;
    private javax.swing.JTextField fillConfirmPass;
    private javax.swing.JTextField fillMaNV;
    private javax.swing.JTextField fillMail;
    private javax.swing.JTextField fillName;
    private javax.swing.JTextField fillPass;
    private javax.swing.JTextField fillPhone;
    private javax.swing.JTextField fillTenDangNhap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBirthyear;
    private javax.swing.JLabel lbCCCD;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbTenDangNhap;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbVaiTro;
    private javax.swing.JRadioButton radAdmin;
    private javax.swing.JRadioButton radNV;
    private javax.swing.JTable tbNV;
    // End of variables declaration//GEN-END:variables
}
