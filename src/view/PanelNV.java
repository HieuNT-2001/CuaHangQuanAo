/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import dao.*;
import entity.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;
/**
 *
 * @author HP
 */
public class PanelNV extends javax.swing.JPanel {
    int index=0;
    NhanVienDAO nvd=new NhanVienDAO();
    /**
     * Creates new form PanelNV
     */
    public PanelNV() {
        initComponents();
        fillTable();
    }
    void fillTable(){
        DefaultTableModel model=(DefaultTableModel) tbNV.getModel();
        model.setRowCount(0);
        List<NhanVien> lnv=nvd.select();
        for(NhanVien nv:lnv){
            Object data[]={nv.getMaNV(),nv.getTenDangNhap(),nv.getMatKhau(),nv.getHoTen(),
                nv.getNamSinh(),null,nv.getSdt(),nv.getCccd(),nv.isVaiTro() ? "Quản lý":"Nhân viên"
            };
            model.addRow(data);
        }
    }
    void setModel(NhanVien nv){
        fillMaNV.setText(String.valueOf(nv.getMaNV()));
        fillTenDangNhap.setText(nv.getTenDangNhap());
        fillPass.setText(nv.getMatKhau());
        fillConfirmPass.setText(nv.getMatKhau());
        fillName.setText(nv.getHoTen());
        fillBirthyear.setText(String.valueOf(nv.getNamSinh()));
        fillPhone.setText(nv.getSdt());
        fillMail.setText("");
        fillCCCD.setText(nv.getCccd());
        radAdmin.setSelected(nv.isVaiTro());
        radNV.setSelected(!nv.isVaiTro());
    }
    
    NhanVien getModel(){
        NhanVien nv=new NhanVien();
        nv.setMaNV(Integer.parseInt(fillMaNV.getText()));
        nv.setTenDangNhap(fillTenDangNhap.getText());
        nv.setMatKhau(fillPass.getText());
        nv.setHoTen(fillName.getText());
        nv.setNamSinh(Integer.parseInt(fillBirthyear.getText()));
        nv.setSdt(fillPhone.getText());
        //nv.setEmail(fillMail.getText());
        nv.setCccd(fillCCCD.getText());
        nv.setVaiTro(radAdmin.isSelected());
        return nv;
    }
    void clear(){
        fillMaNV.setText("");
        fillTenDangNhap.setText("");
        fillPass.setText("");
        fillName.setText("");
        fillBirthyear.setText("");
        fillPhone.setText("");
        fillMail.setText("");
        fillCCCD.setText("");
        buttonGroup1.clearSelection();
    }
    
    void insert(){
        NhanVien nv=getModel();
        String confirm=fillConfirmPass.getText();
        if(confirm.equals(nv.getMatKhau())){
            try{
                nvd.insert(nv);
                fillTable();
                clear();
                System.out.println("Thêm nhân viên thành công!");
            }
            catch(Exception e){
                MsgBox.alert(this, "Thêm nhân viên thất bại!");
                System.out.println("Thêm nhân viên thất bại!");
            }
        }
        else MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
    }
    
    void update(){
        NhanVien nv=getModel();
        String confirm=fillConfirmPass.getText();
        if(confirm.equals(nv.getMatKhau())){
            try{
                nvd.update(nv);
                fillTable();
                clear();
                System.out.println("Cập nhật thành công!");
            }
            catch(Exception e){
                MsgBox.alert(this, "Cập nhật thất bại!");
                System.out.println("Cập nhật thất bại!");
            }
        }
        else MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
    }
    void delete(){
        if(MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")){
            int maNV=Integer.parseInt(fillMaNV.getText());
            try{
                nvd.delete(maNV);
                fillTable();
                clear();
                MsgBox.alert(this, "Xóa thành công!");
                System.out.println("Xóa thành công!");
            }
            catch(Exception e){
                MsgBox.alert(this, "Xóa thất bại!");
                System.out.println("Xóa thất bại!");
            }
        }
    }
    void edit(){
        try{
            int maNV=(int) tbNV.getValueAt(this.index, 0);
            NhanVien nv=nvd.selectById(maNV);
            if(nv!=null){
                this.setModel(nv);
            }
        }
        catch(Exception e){
            System.out.println("Không thể truy vấn dữ liệu");
        }
    }
    
    public boolean checkNull(){
        if (fillTenDangNhap.getText().isBlank()) {
            return false;
        }else if(fillPass.getText().isBlank()){
            return false;
        }else if(fillConfirmPass.getText().isBlank()){
            return false;
        }else if(fillName.getText().isBlank()){
            return false;
        }else if(fillBirthyear.getText().isBlank()){
            return false;
        }else if(fillPhone.getText().isBlank()){
            return false;
        }else if(fillCCCD.getText().isBlank()){
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
        fillMaNV.setEnabled(false);

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
        tbNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNVMouseClicked(evt);
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
//        if(fillTenDangNhap.getText().isBlank()||fillPass.getText().isBlank()||fillConfirmPass.getText().isBlank()||
//            fillName.getText().isBlank()||fillBirthyear.getText().isBlank()||fillPhone)
        if(checkNull()==false){
            MsgBox.alert(this, "Vui lòng điền hết thông tin");
            return;
        }
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if(checkNull()==false){
            MsgBox.alert(this, "Vui lòng điền hết thông tin");
            return;
        }
        update();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tbNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNVMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            this.index=tbNV.rowAtPoint(evt.getPoint());
            if(this.index>=0){
                edit();
            }
        }
    }//GEN-LAST:event_tbNVMouseClicked


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
