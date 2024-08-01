/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.*;
import entity.*;
import java.awt.Frame;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import utils.*;

/**
 *
 * @author PC
 */
public class PanelHoaDon extends javax.swing.JPanel {

    int index = 0;
    HoaDonDAO hdd = new HoaDonDAO();

    /**
     * Creates new form HoaDonJPanel
     */
    public PanelHoaDon() {
        initComponents();
        fillTableHoaDon();
//        this.fillTableByStatus();
    }

    void fillTableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tbBill.getModel();
        model.setRowCount(0);
        int status = cboxStatus.getSelectedIndex();
        List<HoaDon> lhd = null;
        if (status == 3) {
            lhd = hdd.selectAll();
        } else {
            lhd = hdd.selectByStatus(status);
        }
        try {
            for (HoaDon hd : lhd) {
                Object data[] = {
                    hd.getMaHD(),
                    hd.getMaNV(),
                    hd.getTenKH(),
                    hd.getSdt(),
                    hd.getDiaChi(),
                    hd.getMaGiamGia(),
                    hd.getThanhTien(),
                    hd.isKenhBanHang(),
                    hd.isHt_thanhToan(),
                    hd.getNgayTao(),
                    hd.getTrangThai(),
                    hd.getLyDo()
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            System.out.println("Không thể truy vấn dữ liệu");
        }
    }

    void fillTableByID() {
        DefaultTableModel model = (DefaultTableModel) tbBill.getModel();
        model.setRowCount(0);
        try {
            int id = Integer.parseInt(searchBar.getText());
            HoaDon hd = hdd.selectById(id);
//            List<HoaDon> lhd = (List<HoaDon>) hdd.selectById(id);
//            for (HoaDon hd : lhd) {
            Object data[] = {hd.getMaHD(), hd.getMaNV(), hd.getTenKH(), hd.getSdt(), hd.getDiaChi(),
                hd.getMaGiamGia(), hd.getThanhTien(), hd.isKenhBanHang(), hd.isHt_thanhToan(), hd.getNgayTao(),
                hd.getTrangThai(), hd.getLyDo()};
            model.addRow(data);
//            }
        } catch (Exception e) {
            System.out.println("Không thể truy vấn dữ liệu");
        }
    }

    private int getMaHD(int index) {
        this.index = tbBill.getSelectedRow();
        int maHD = (int) tbBill.getValueAt(index, 0);
        return maHD;
    }

//    void fillTableByStatus() {
//        DefaultTableModel model = (DefaultTableModel) tbBill.getModel();
//        model.setRowCount(0);
//        try {
//            int status = cboxStatus.getSelectedIndex();
//            if (status == 3) {
//                fillTableHoaDon();
//            } else {
//                List<HoaDon> lhd = hdd.selectByStatus(status);
//                for (HoaDon hd : lhd) {
//                    Object data[] = {hd.getMaHD(), hd.getMaNV(), hd.getTenKH(), hd.getSdt(), hd.getDiaChi(),
//                        hd.getMaGiamGia(), hd.getThanhTien(), hd.isKenhBanHang(), hd.isHt_thanhToan(), hd.getNgayTao(),
//                        hd.getTrangThai(), hd.getLyDo()};
//                    model.addRow(data);
//                }
//
//            }
//        } catch (Exception e) {
//            System.out.println("Không thể truy vấn dữ liệu");
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbBill = new javax.swing.JTable();
        lbTitle = new javax.swing.JLabel();
        lbSearch = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        cboxStatus = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        tbBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Người tạo", "Khách hàng", "SĐT", "Địa chỉ", "Mã giảm giá", "Thành tiền", "Kênh bán hàng", "HT thanh toán", "Ngày tạo", "Trạng thái", "Lý do"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBill);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTitle.setText("Hóa đơn");

        lbSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbSearch.setText("Tìm kiếm");

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lbStatus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbStatus.setText("Trạng thái");

        cboxStatus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán", "Đã hủy", "Tất cả" }));
        cboxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBar)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        fillTableByID();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBillMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tbBill.getSelectedRow();
            int maHD = (int) tbBill.getValueAt(index, 0);
//            Frame homeJDFrame = (Frame) new HomeJFrame();
            DialogCTHD hdctDialog = new DialogCTHD(new javax.swing.JFrame(), true, maHD);
            hdctDialog.setVisible(true);
        }
    }//GEN-LAST:event_tbBillMouseClicked

    private void cboxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxStatusActionPerformed
        // TODO add your handling code here:
        fillTableHoaDon();
    }//GEN-LAST:event_cboxStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboxStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable tbBill;
    // End of variables declaration//GEN-END:variables
}
