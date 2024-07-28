/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.DanhMucDAO;
import dao.HoaDonChiTietDAO;
import dao.HoaDonDAO;
import dao.KhuyenMaiDAO;
import dao.NhaCungCapDAO;
import dao.SanPhamDAO;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.KhuyenMai;
import entity.SanPham;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import utils.Auth;
import utils.MsgBox;
import utils.XDate;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author PC
 */
public class BanHangJPanel extends javax.swing.JPanel {

    SanPhamDAO spDAO = new SanPhamDAO();
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    DanhMucDAO dmDAO = new DanhMucDAO();
    HoaDonDAO hdDAO = new HoaDonDAO();
    HoaDonChiTietDAO hdctDAO = new HoaDonChiTietDAO();
    KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();

    /**
     * Creates new form BanHangJPanel
     */
    public BanHangJPanel() {
        initComponents();
        ht_ThanhToan();
        fillTableDanhSachSP();
        fillTableDanhSachHD();
    }

    // Hàm đổ dữ liệu bảng danh sách sản phẩm
    private void fillTableDanhSachSP() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
        model.setRowCount(0);
        List<SanPham> list = spDAO.selectByStatus(1);
        for (SanPham sanPham : list) {
            Object[] rowData = {
                sanPham.getMaSP(),
                sanPham.getTenSP(),
                sanPham.getDonGia(),
                sanPham.getSoLuong(),
                nccDAO.selectById(sanPham.getMaNCC()).getTenNCC(),
                dmDAO.selectById(sanPham.getMaDM()).getDanhMuc()
            };
            model.addRow(rowData);
        }
    }

    // Hàm đổ dữ liệu vào bảng giỏ hàng
    private void fillTableGioHang(int maHD) {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        List<HoaDonChiTiet> list = hdctDAO.selectByMaHD(maHD);
        for (HoaDonChiTiet hdct : list) {
            SanPham sp = spDAO.selectById(hdct.getMaSP());
            Object[] rowData = {
                sp.getMaSP(),
                sp.getTenSP(),
                hdct.getGiaBan(),
                hdct.getSoLuong(),
                hdct.getGiaBan() * hdct.getSoLuong()
            };
            model.addRow(rowData);
        }
    }

    // Hàm đổ dữ liệu vào bảng danh sách hóa đơn
    private void fillTableDanhSachHD() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSachHD.getModel();
        model.setRowCount(0);
        List<HoaDon> list = hdDAO.selectByStatus(0);
        for (HoaDon hoaDon : list) {
            Object[] rowData = {
                hoaDon.getMaHD(),
                Auth.user.getHoTen(),
                hoaDon.getTenKH(),
                hoaDon.getTrangThai() == 0 ? "Chưa thanh toán" : "đã thanh toán - đã hủy",
                hoaDon.getNgayTao()
            };
            model.addRow(rowData);
        }
    }

    // Tạo một hóa đơn vơi kênh bán hàng tại quầy
    private void taoHoaDon() {
        String maGiamGia = txtMaGiamGia.getText();
        if (maGiamGia.isBlank() || checkKhuyenMai(maGiamGia)) {
            HoaDon hd = getForm(false);
            hdDAO.insert(hd);
            fillTableDanhSachHD();
            clearGioHang();
            clearForm();
        }
    }

    // tạo một hóa đơn với kênh bán hàng online
    private void datHang() {
        if (isDatHang()) {
            String maGiamGia = txtMaGiamGia.getText();
            if (maGiamGia.isBlank() || checkKhuyenMai(maGiamGia)) {
                HoaDon hd = getForm(true);
                hdDAO.insert(hd);
                fillTableDanhSachHD();
                clearGioHang();
                clearForm();
            }
        }
    }

    // Thêm sản phẩm vào giở hàng
    private void themGioHang(int tableHDRow, int tableSPRow) {
        int maHD = (int) tblDanhSachHD.getValueAt(tableHDRow, 0);
        int maSP = (int) tblDanhSachSP.getValueAt(tableSPRow, 0);
        int soLuongTon = (int) tblDanhSachSP.getValueAt(tableSPRow, 3);

        boolean coTrongGioHang = false;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            int maSP_gioHang = (int) tblGioHang.getValueAt(i, 0);
            int sl_gioHang = (int) tblGioHang.getValueAt(i, 3);
            if (maSP_gioHang == maSP) {
                if (sl_gioHang < soLuongTon) {
                    HoaDonChiTiet hdct = hdctDAO.selectByMaHD_MaSP(maHD, maSP);
                    int soLuong = (int) tblGioHang.getValueAt(i, 3) + 1;
                    hdct.setSoLuong(soLuong);
                    System.out.println(hdct.getId() + "," + hdct.getMaHD() + "," + hdct.getMaSP() + "," + hdct.getSoLuong() + "," + hdct.getGiaBan());
                    hdctDAO.update(hdct);
                } else {
                    MsgBox.alert(this, "Không có đủ hàng!");
                }
                coTrongGioHang = true;
                break;
            }
        }

        if (!coTrongGioHang) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            double giaBan = (double) tblDanhSachSP.getValueAt(tableSPRow, 2);
            hdct.setMaHD(maHD);
            hdct.setMaSP(maSP);
            hdct.setSoLuong(1);
            hdct.setGiaBan(giaBan);
            hdctDAO.insert(hdct);
        }

//        fillTableDanhSachSP();
        fillTableGioHang(maHD);
        updateThanhTien(tableHDRow);
        setForm(tableHDRow);
    }

    private void xoaGioHang(int tableHDRow, int tableGioHangRow) {
        int maHD = (int) tblDanhSachHD.getValueAt(tableHDRow, 0);
        int maSP = (int) tblGioHang.getValueAt(tableGioHangRow, 0);
        hdctDAO.deleteByMaHD_MaSP(maHD, maSP);
//        fillTableDanhSachSP();
        fillTableGioHang(maHD);
        updateThanhTien(tableHDRow);
        setForm(tableHDRow);
    }

    // Cập nhật trạng thái hóa đơn (Đã thanh toán, hủy)
    private void updateTrangThai(int row, int status, String args) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
        HoaDon hd = hdDAO.selectById(maHD);
        hd.setTrangThai(status);
        boolean ht_thanhToan = cboHinhThucTT.getSelectedIndex() == 1;
        hd.setHt_thanhToan(ht_thanhToan);
        hd.setLyDo(args);
        hdDAO.update(hd);
        fillTableDanhSachHD();
        fillTableDanhSachSP();
        clearGioHang();
        clearForm();
    }

    // Cập nhật lại thành tiền khi thêm sản phẩm vào giỏ hàng
    private void updateThanhTien(int row) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
        HoaDon hd = hdDAO.selectById(maHD);
        double thanhTien = getThanhTien();
        double giamGia = getGiamGia(hd.getMaGiamGia());
        hd.setThanhTien(thanhTien * (1 - giamGia));
        hdDAO.update(hd);
    }

    // Lấy tổng thành tiền trước giảm giá
    private double getThanhTien() {
        double thanhTien = 0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            thanhTien += (double) tblGioHang.getValueAt(i, 4);
        }
        return thanhTien;
    }

    // Lấy số tiền giảm giá
    private double getGiamGia(String maGiamGia) {
        KhuyenMai km = kmDAO.selectByTenKM(maGiamGia);
        if (km == null) {
            return 0;
        } else {
            return km.getGiamGia();
        }
    }

    // Tạo một hóa đơn trống
    private HoaDon getForm(boolean kenhBanHang) {
        HoaDon hd = new HoaDon();
        hd.setMaNV(Auth.user.getMaNV());
        hd.setTenKH(txtTenKH.getText());
        hd.setSdt(txtSDT.getText());
        hd.setDiaChi(txtDiaChi.getText());
        hd.setMaGiamGia(txtMaGiamGia.getText());
        hd.setKenhBanHang(kenhBanHang);
        boolean ht_thanhToan = cboHinhThucTT.getSelectedIndex() == 1;
        hd.setHt_thanhToan(ht_thanhToan);
        hd.setThanhTien(0);
        Date ngayTao = XDate.toDate(LocalDate.now().toString(), "yyyy-MM-dd");
        hd.setNgayTao(ngayTao);
        hd.setTrangThai(0);
        return hd;
    }

    // Hien thi thong tin thanh toan cua hoa don duoc chon
    public void setForm(int row) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
        HoaDon hd = hdDAO.selectById(maHD);

        double thanhTien = getThanhTien();
        double giamGia = thanhTien * getGiamGia(hd.getMaGiamGia());
        double thanhToan = thanhTien - giamGia;

        txtTenKH.setText(hd.getTenKH());
        txtTenKH.setEnabled(false);

        txtSDT.setText(hd.getSdt());
        txtSDT.setEnabled(false);

        txtDiaChi.setText(hd.getDiaChi());
        txtDiaChi.setEnabled(false);

        txtMaGiamGia.setText(hd.getMaGiamGia());
        txtMaGiamGia.setEnabled(false);

        txtThanhTien.setText(String.valueOf(thanhTien));
        txtGiamGia.setText(String.valueOf(giamGia));
        txtThanhToan.setText(String.valueOf(thanhToan));
    }

    // Làm trắng form
    public void clearForm() {
        txtTenKH.setEnabled(true);
        txtTenKH.setText("");

        txtSDT.setEnabled(true);
        txtSDT.setText("");

        txtDiaChi.setEnabled(true);
        txtDiaChi.setText("");

        txtMaGiamGia.setEnabled(true);
        txtMaGiamGia.setText("");

        txtThanhTien.setText("");
        txtGiamGia.setText("");
        txtThanhToan.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
    }

    public void clearGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        tblGioHang.setModel(model);
    }

    // Kiểm tra hiệu lực mã giảm giá
    public boolean checkKhuyenMai(String maKM) {
        KhuyenMai km = kmDAO.selectByTenKM(maKM);
        Date ngayHienTai = XDate.toDate(LocalDate.now().toString(), "yyyy-MM-dd");
        if (km == null) {
            MsgBox.alert(this, "Mã khuyến mại không tồn tại!");
            return false;
        } else if (ngayHienTai.before(km.getNgayBD())) {
            MsgBox.alert(this, "Mã khuyến mại chưa có hiệu lực!");
            return false;
        } else if (ngayHienTai.after(km.getNgayKT())) {
            MsgBox.alert(this, "Mã khuyến mại đã hết hiệu lực!");
            return false;
        }
        return true;
    }

    // Kiểm tra các thông tin khi đặt hàng
    public boolean isDatHang() {
        if (txtTenKH.getText().isBlank()) {
            MsgBox.alert(this, "Không được để trống tên khách hàng khi đặt hàng!");
            return false;
        } else if (txtSDT.getText().isBlank()) {
            MsgBox.alert(this, "Không được để trống SDT khi đặt hàng!");
            return false;
        } else if (txtDiaChi.getText().isBlank()) {
            MsgBox.alert(this, "Không được để trống địa chỉ khi đặt hàng!");
            return false;
        }
        return true;
    }

    private void ht_ThanhToan() {
        if (cboHinhThucTT.getSelectedIndex() == 1) {
            txtTienKhachDua.setEnabled(false);
            txtTienThua.setText("");
        } else if (cboHinhThucTT.getSelectedIndex() == 0) {
            txtTienKhachDua.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblDanhSachHD = new javax.swing.JLabel();
        lblMaQR = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblMaGiamGia = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtMaGiamGia = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblThanhTien = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lblKhachCanTra = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblHT_thanhToan = new javax.swing.JLabel();
        lblTienKhachDua = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        cboHinhThucTT = new javax.swing.JComboBox<>();
        txtTienKhachDua = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnTaoHD = new javax.swing.JButton();
        btnDatHang = new javax.swing.JButton();
        txtThanhTien = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        txtThanhToan = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        pnlMaQR = new javax.swing.JPanel();
        lblGioHang = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachHD = new javax.swing.JTable();
        btnXoaGioHang = new javax.swing.JButton();
        btnThemGioHang = new javax.swing.JButton();
        lblDanhSachSP = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachSP = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1024, 720));

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitle.setText("Bán hàng");

        lblDanhSachHD.setText("Danh sách hóa đơn");

        lblMaQR.setText("Mã QR");

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Giá bán", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenKH.setText("Tên KH:");

        lblSDT.setText("SĐT:");

        lblDiaChi.setText("Địa chỉ:");

        lblMaGiamGia.setText("Mã giảm giá:");

        lblThanhTien.setText("Tổng thành tiền:");

        lblGiamGia.setText("Giảm giá: ");

        lblKhachCanTra.setText("Khách cần trả: ");

        lblHT_thanhToan.setText("HT thanh toán: ");

        lblTienKhachDua.setText("Tiền khách đưa: ");

        lblTienThua.setText("Tiền thừa:");

        cboHinhThucTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));
        cboHinhThucTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucTTActionPerformed(evt);
            }
        });

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyPressed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnTaoHD.setText("Tạo hóa đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnDatHang.setText("Đặt hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtThanhTien.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtThanhTien.setEnabled(false);

        txtGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiamGia.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtGiamGia.setEnabled(false);

        txtThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtThanhToan.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtThanhToan.setEnabled(false);

        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTienThua.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtTienThua.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenKH)
                                    .addComponent(lblDiaChi)
                                    .addComponent(lblMaGiamGia))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenKH)
                                    .addComponent(txtSDT)
                                    .addComponent(txtDiaChi)
                                    .addComponent(txtMaGiamGia)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblHT_thanhToan)
                                .addGap(31, 31, 31)
                                .addComponent(cboHinhThucTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTienKhachDua)
                                .addGap(26, 26, 26)
                                .addComponent(txtTienKhachDua))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblThanhTien)
                                .addGap(22, 22, 22)
                                .addComponent(txtThanhTien))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGiamGia)
                                .addGap(54, 54, 54)
                                .addComponent(txtGiamGia))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblKhachCanTra)
                                .addGap(31, 31, 31)
                                .addComponent(txtThanhToan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSDT)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTienThua)
                                .addGap(60, 60, 60)
                                .addComponent(txtTienThua)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaGiamGia))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblKhachCanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHT_thanhToan)
                    .addComponent(cboHinhThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienKhachDua)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienThua)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHD)
                    .addComponent(btnDatHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnHuy))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMaQRLayout = new javax.swing.GroupLayout(pnlMaQR);
        pnlMaQR.setLayout(pnlMaQRLayout);
        pnlMaQRLayout.setHorizontalGroup(
            pnlMaQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlMaQRLayout.setVerticalGroup(
            pnlMaQRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblGioHang.setText("Giỏ hàng");

        tblDanhSachHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Tên NV", "Tên KH", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachHD);

        btnXoaGioHang.setText("Xóa khỏi giỏ hàng");
        btnXoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangActionPerformed(evt);
            }
        });

        btnThemGioHang.setText("Thêm vào giỏ hàng");
        btnThemGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGioHangActionPerformed(evt);
            }
        });

        lblDanhSachSP.setText("Danh sách sản phẩm");

        tblDanhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng ", "Nhà cung cấp", "Danh mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblDanhSachSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDanhSachHD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnlMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDanhSachSP, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnThemGioHang)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaGioHang))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(447, 447, 447))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDanhSachHD)
                            .addComponent(lblMaQR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaGioHang)
                            .addComponent(btnThemGioHang))
                        .addGap(2, 2, 2)
                        .addComponent(lblDanhSachSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        taoHoaDon();
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        // TODO add your handling code here:
        datHang();
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        int row = tblDanhSachHD.getSelectedRow();
        if (row >= 0) {
            updateTrangThai(row, 1, "");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int row = tblDanhSachHD.getSelectedRow();
        if (row >= 0) {
            String lyDoHuy = MsgBox.prompt(this, "Nhập lý do hủy");
            if (!lyDoHuy.isBlank()) {
                updateTrangThai(row, 2, lyDoHuy);
            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGioHangActionPerformed
        // TODO add your handling code here:
        int tableHDRow = tblDanhSachHD.getSelectedRow();
        int tableSPRow = tblDanhSachSP.getSelectedRow();
        if (tableHDRow >= 0 && tableSPRow >= 0) {
            themGioHang(tableHDRow, tableSPRow);
        }
    }//GEN-LAST:event_btnThemGioHangActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed
        // TODO add your handling code here:
        int tableHDRow = tblDanhSachHD.getSelectedRow();
        int tableGioHangRow = tblGioHang.getSelectedRow();
        if (tableHDRow >= 0 && tableGioHangRow >= 0) {
            xoaGioHang(tableHDRow, tableGioHangRow);
        }
    }//GEN-LAST:event_btnXoaGioHangActionPerformed


    private void tblDanhSachHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachHDMouseClicked
        // TODO add your handling code here:
        int row = tblDanhSachHD.getSelectedRow();
        if (row >= 0) {
            int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
            fillTableGioHang(maHD);
            setForm(row);
        }
    }//GEN-LAST:event_tblDanhSachHDMouseClicked

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed
        // TODO add your handling code here:
        try {
            double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
            double thanhToan = Double.parseDouble(txtThanhToan.getText());
            txtTienThua.setText(String.valueOf(tienKhachDua - thanhToan));
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Mời nhập số tiền");
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void cboHinhThucTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucTTActionPerformed
        // TODO add your handling code here:
        ht_ThanhToan();
    }//GEN-LAST:event_cboHinhThucTTActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemGioHang;
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.JComboBox<String> cboHinhThucTT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblDanhSachHD;
    private javax.swing.JLabel lblDanhSachSP;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblGioHang;
    private javax.swing.JLabel lblHT_thanhToan;
    private javax.swing.JLabel lblKhachCanTra;
    private javax.swing.JLabel lblMaGiamGia;
    private javax.swing.JLabel lblMaQR;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlMaQR;
    private javax.swing.JTable tblDanhSachHD;
    private javax.swing.JTable tblDanhSachSP;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    // End of variables declaration//GEN-END:variables
}
