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
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import utils.Auth;
import utils.MsgBox;
import utils.XDate;

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
    private void fillTableGioHang(int row) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
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
                hoaDon.isKenhBanHang() ? "Online" : "Trực tiếp",
                hoaDon.getTrangThai() == 0 ? "Chưa thanh toán" : "đã thanh toán - đã hủy",
                hoaDon.getNgayTao()
            };
            model.addRow(rowData);
        }
    }

    // Tạo một hóa đơn vơi kênh bán hàng tại quầy
    private void taoHoaDon() {
        HoaDon hd = getForm(false);
        hdDAO.insert(hd);
        fillTableDanhSachHD();
    }

    // tạo một hóa đơn với kênh bán hàng online
    private void datHang() {
        HoaDon hd = getForm(true);
        hdDAO.insert(hd);
        fillTableDanhSachHD();
    }

    // Thêm sản phẩm vào giở hàng
    private void themVaoGioHang(int hdRow, int spRow) {
        int maHD = (int) tblDanhSachHD.getValueAt(hdRow, 0);
        int maSP = (int) tblDanhSachSP.getValueAt(spRow, 0);
        int soLuongTon = (int) tblDanhSachSP.getValueAt(spRow, 3);

        // Kiểm tra xem sản phẩm thêm đã có trong giỏ hàng chưa
        // Nếu có thì sẽ tăng số lượng trong giỏ hàng
        boolean coTrongGioHang = false;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            int maSP_gioHang = (int) tblGioHang.getValueAt(i, 0);
            int sl_gioHang = (int) tblGioHang.getValueAt(i, 3);
            if (maSP_gioHang == maSP) {
                if (sl_gioHang < soLuongTon) {
                    HoaDonChiTiet hdct = hdctDAO.selectByMaHD_MaSP(maHD, maSP);
                    int soLuong = (int) tblGioHang.getValueAt(i, 3) + 1;
                    hdct.setSoLuong(soLuong);
                    hdctDAO.update(hdct);
                } else {
                    MsgBox.alert(this, "Không có đủ hàng!");
                }
                coTrongGioHang = true;
                break;
            }
        }

        // Nếu không có sản phẩm trong giỏ hàng
        // thì sẽ thêm sản phẩm vào giỏ hàng
        if (!coTrongGioHang) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            double giaBan = (double) tblDanhSachSP.getValueAt(spRow, 2);
            hdct.setMaHD(maHD);
            hdct.setMaSP(maSP);
            hdct.setSoLuong(1);
            hdct.setGiaBan(giaBan);
            hdctDAO.insert(hdct);
        }

        fillTableGioHang(hdRow);
        updateThanhTien(hdRow);
        setForm(hdRow);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    private void xoakhoiGioHang(int hdRow, int spRow) {
        int maHD = (int) tblDanhSachHD.getValueAt(hdRow, 0);
        int maSP = (int) tblGioHang.getValueAt(spRow, 0);
        hdctDAO.deleteByMaHD_MaSP(maHD, maSP);
        fillTableGioHang(hdRow);
        updateThanhTien(hdRow);
        setForm(hdRow);
    }

    // Làm trống giỏ hàng
    public void clearGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        tblGioHang.setModel(model);
    }

    // Cập nhật trạng thái hóa đơn (Đã thanh toán, hủy)
    private void updateThongTinHD(int row, int status, String args) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
        HoaDon hd = hdDAO.selectById(maHD);

        // Cập nhật thông tin cho hóa đơn
        hd.setTenKH(txtTenKH.getText());
        hd.setSdt(txtSDT.getText());
        hd.setDiaChi(txtDiaChi.getText());
        hd.setMaGiamGia(txtMaGiamGia.getText());
        boolean ht_thanhToan = cboHinhThucTT.getSelectedIndex() == 1;
        hd.setHt_thanhToan(ht_thanhToan);
        hd.setTrangThai(status);
        hd.setLyDo(args);
        hdDAO.update(hd);

        if (status == 1) {
            MsgBox.alert(this, "Thanh toán thành công!");
        } else if (status == 2) {
            MsgBox.alert(this, "Hủy hóa đơn thành công!");
        }
    }

    // Cập nhật lại thành tiền khi thêm hoặc xóa sản phẩm vào giỏ hàng
    private void updateThanhTien(int row) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
        HoaDon hd = hdDAO.selectById(maHD);
        double thanhTien = getThanhTien();
        double giamGia = getGiamGia(hd.getMaGiamGia());
        hd.setThanhTien(thanhTien * (1 - giamGia));
        hdDAO.update(hd);
    }

    // Lấy tổng thành tiền trước giảm giá của các sản phẩm trong giỏ hàng
    private double getThanhTien() {
        double thanhTien = 0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            thanhTien += (double) tblGioHang.getValueAt(i, 4);
        }
        return thanhTien;
    }

    // Lấy phần trăm giảm giá
    private double getGiamGia(String maGiamGia) {
        List<KhuyenMai> list = kmDAO.selectByTenKM(maGiamGia);
        if (list.isEmpty()) {
            return 0;
        } else {
            return list.get(0).getGiamGia();
        }
    }

    // Tạo một hóa đơn trống
    private HoaDon getForm(boolean kenhBanHang) {
        HoaDon hd = new HoaDon();
        hd.setMaNV(Auth.user.getMaNV());
        hd.setKenhBanHang(kenhBanHang);
        boolean ht_thanhToan = cboHinhThucTT.getSelectedIndex() == 1;
        hd.setHt_thanhToan(ht_thanhToan);
        hd.setThanhTien(0);
        Date ngayTao = XDate.toDate(LocalDate.now().toString(), "yyyy-MM-dd");
        hd.setNgayTao(ngayTao);
        hd.setTrangThai(0);
        return hd;
    }

    // Hiển thị thông tin thanh toán của hóa đơn lên form
    private void setForm(int row) {
        int maHD = (int) tblDanhSachHD.getValueAt(row, 0);
        HoaDon hd = hdDAO.selectById(maHD);

        double thanhTien = getThanhTien();
        double giamGia = thanhTien * getGiamGia(hd.getMaGiamGia());
        double thanhToan = thanhTien - giamGia;

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

    // Kiểm tra hiệu lực mã giảm giá
    public boolean checkKhuyenMai(String maGiamGia) {
        List<KhuyenMai> list = kmDAO.selectByTenKM(maGiamGia);
        Date ngayHienTai = XDate.toDate(LocalDate.now().toString(), "yyyy-MM-dd");
        if (maGiamGia.isBlank()) {
            return false;
        } else if (list.isEmpty()) {
            MsgBox.alert(this, "Mã khuyến mại không tồn tại!");
            return false;
        } else if (ngayHienTai.before(list.get(0).getNgayBD())) {
            MsgBox.alert(this, "Mã khuyến mại chưa có hiệu lực!");
            return false;
        } else if (ngayHienTai.after(list.get(0).getNgayKT())) {
            MsgBox.alert(this, "Mã khuyến mại đã hết hiệu lực!");
            return false;
        }
        return true;
    }

    // Kiểm tra các thông tin khi đặt hàng
    private boolean isDatHang() {
        if (txtTenKH.getText().isBlank()) {
            MsgBox.alert(this, "Không được để trống tên khách hàng khi đặt hàng!");
            return false;
        } else if (txtSDT.getText().isBlank()) {
            MsgBox.alert(this, "Không được để trống SDT khi đặt hàng!");
            return false;
        } else if (txtDiaChi.getText().isBlank()) {
            MsgBox.alert(this, "Không được để trống địa chỉ khi đặt hàng!");
            return false;
        } else if (cboHinhThucTT.getSelectedIndex() != 1) {
            MsgBox.alert(this, "Đặt hàng chỉ thanh toán bằng chuyển khoản!");
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
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTenKH.setText("Tên KH:");

        lblSDT.setText("SĐT:");

        lblDiaChi.setText("Địa chỉ:");

        lblMaGiamGia.setText("Mã giảm giá:");

        txtMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGiamGiaActionPerformed(evt);
            }
        });
        txtMaGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaGiamGiaKeyPressed(evt);
            }
        });

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
                                .addComponent(lblTienThua)
                                .addGap(60, 60, 60)
                                .addComponent(txtTienThua))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGiamGia)
                                .addGap(56, 56, 56)
                                .addComponent(txtGiamGia))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSDT)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblKhachCanTra)
                                .addGap(31, 31, 31)
                                .addComponent(txtThanhToan)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKhachCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
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
                "Mã HĐ", "Tên NV", "Tên KH", "Kênh bán hàng", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
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
            String kenhBanHang = tblDanhSachHD.getValueAt(row, 3).toString();
            if (kenhBanHang.equals("Online") && !isDatHang()) {
                return;
            }
            updateThongTinHD(row, 1, "");
            fillTableDanhSachHD();
            fillTableDanhSachSP();
            clearGioHang();
            clearForm();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int row = tblDanhSachHD.getSelectedRow();
        if (row >= 0) {
            String lyDoHuy = MsgBox.prompt(this, "Nhập lý do hủy");
            if (lyDoHuy != null) {
                updateThongTinHD(row, 2, lyDoHuy);
                fillTableDanhSachHD();
                fillTableDanhSachSP();
                clearGioHang();
                clearForm();
            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGioHangActionPerformed
        // TODO add your handling code here:
        int tableHDRow = tblDanhSachHD.getSelectedRow();
        int tableSPRow = tblDanhSachSP.getSelectedRow();
        if (tableHDRow >= 0 && tableSPRow >= 0) {
            themVaoGioHang(tableHDRow, tableSPRow);
        }
    }//GEN-LAST:event_btnThemGioHangActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed
        // TODO add your handling code here:
        int tableHDRow = tblDanhSachHD.getSelectedRow();
        int tableGioHangRow = tblGioHang.getSelectedRow();
        if (tableHDRow >= 0 && tableGioHangRow >= 0) {
            xoakhoiGioHang(tableHDRow, tableGioHangRow);
        }
    }//GEN-LAST:event_btnXoaGioHangActionPerformed


    private void tblDanhSachHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachHDMouseClicked
        // TODO add your handling code here:
        int row = tblDanhSachHD.getSelectedRow();
        if (row >= 0) {
            fillTableGioHang(row);
            setForm(row);
        }
    }//GEN-LAST:event_tblDanhSachHDMouseClicked

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtThanhToan.getText().isBlank()) {
                MsgBox.alert(this, "Bạn chưa chọn hóa đơn!");
            } else {
                try {
                    double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
                    double thanhToan = Double.parseDouble(txtThanhToan.getText());
                    txtTienThua.setText(String.valueOf(tienKhachDua - thanhToan));
                } catch (NumberFormatException e) {
                    MsgBox.alert(this, "Mời nhập đúng số tiền!");
                }
            }
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void cboHinhThucTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucTTActionPerformed
        // TODO add your handling code here:
        if (cboHinhThucTT.getSelectedIndex() == 1) {
            txtTienKhachDua.setEnabled(false);
            txtTienThua.setText("");
        } else if (cboHinhThucTT.getSelectedIndex() == 0) {
            txtTienKhachDua.setEnabled(true);
        }
    }//GEN-LAST:event_cboHinhThucTTActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int ghRow = tblGioHang.getSelectedRow();
                int hdRow = tblDanhSachHD.getSelectedRow();

                if (ghRow >= 0) {
                    int maSP = (int) tblGioHang.getValueAt(ghRow, 0);
                    int maHD = (int) tblDanhSachHD.getValueAt(hdRow, 0);
                    int sl_gioHang;
                    try {
                        sl_gioHang = Integer.parseInt(model.getValueAt(ghRow, 3).toString());
                        int soLuongTon = spDAO.selectById(maSP).getSoLuong();
                        HoaDonChiTiet hdct = hdctDAO.selectByMaHD_MaSP(maHD, maSP);

                        if (sl_gioHang < soLuongTon) {
                            hdct.setSoLuong(sl_gioHang);
                            hdctDAO.update(hdct);
                        } else {
                            MsgBox.alert(tblGioHang, "Không có đủ hàng!");
                        }

                        fillTableGioHang(hdRow);
                        updateThanhTien(hdRow);
                        setForm(hdRow);
                    } catch (NumberFormatException ex) {
                        MsgBox.alert(this, "Nhập đúng số lượng");
                    }
                }
            }
        });
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtMaGiamGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaGiamGiaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = tblDanhSachHD.getSelectedRow();
            String maGiamGia = txtMaGiamGia.getText();
            if (row >= 0 && checkKhuyenMai(maGiamGia)) {
                updateThongTinHD(row, 0, "");
                updateThanhTien(row);
                setForm(row);
            } else {
                txtMaGiamGia.setText("");
            }
        }
    }//GEN-LAST:event_txtMaGiamGiaKeyPressed

    private void txtMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGiamGiaActionPerformed

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
