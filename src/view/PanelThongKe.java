/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author PC
 */
public class PanelThongKe extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeJPanel
     */
    public PanelThongKe() {
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

        lbTitle = new javax.swing.JLabel();
        pnToday = new javax.swing.JPanel();
        lbToday = new javax.swing.JLabel();
        fillToday = new javax.swing.JTextField();
        lbSuccessToday = new javax.swing.JLabel();
        lbCancelledToday = new javax.swing.JLabel();
        fillSuccessToday = new javax.swing.JTextField();
        fillCancelledToday = new javax.swing.JTextField();
        pnDay = new javax.swing.JPanel();
        lbDay = new javax.swing.JLabel();
        fillDay = new javax.swing.JTextField();
        lbSuccessDay = new javax.swing.JLabel();
        lbCancelledDay = new javax.swing.JLabel();
        fillSuccessDay = new javax.swing.JTextField();
        fillCancelledDay = new javax.swing.JTextField();
        pnMonth = new javax.swing.JPanel();
        lbMonth = new javax.swing.JLabel();
        fillMonth = new javax.swing.JTextField();
        lbSuccessMonth = new javax.swing.JLabel();
        lbCancelledMonth = new javax.swing.JLabel();
        fillSuccessMonth = new javax.swing.JTextField();
        fillCancelledMonth = new javax.swing.JTextField();
        pnYear = new javax.swing.JPanel();
        lbYear = new javax.swing.JLabel();
        fillYear = new javax.swing.JTextField();
        lbSuccessYear = new javax.swing.JLabel();
        lbCancelledYear = new javax.swing.JLabel();
        fillSuccessYear = new javax.swing.JTextField();
        fillCancelledYear = new javax.swing.JTextField();
        lbTimeFrom = new javax.swing.JLabel();
        cboxTimeFrom = new javax.swing.JComboBox<>();
        lbTimeTo = new javax.swing.JLabel();
        cboxTimeTo = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnTop10 = new javax.swing.JPanel();
        lbTop10 = new javax.swing.JLabel();
        lbMonthTop10 = new javax.swing.JLabel();
        cboxMonthTop10 = new javax.swing.JComboBox<>();
        lbYearTop10 = new javax.swing.JLabel();
        cboxYearTop10 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTop10 = new javax.swing.JTable();
        pnSales = new javax.swing.JPanel();
        lbSales = new javax.swing.JLabel();
        lbYearSales = new javax.swing.JLabel();
        cboxYearSales = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSales = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTitle.setText("Thống kê");

        pnToday.setBackground(new java.awt.Color(153, 204, 255));

        lbToday.setText("Doanh thu hôm nay");

        fillToday.setEditable(false);

        lbSuccessToday.setText("Thành công: ");

        lbCancelledToday.setText("Bị hủy: ");

        fillSuccessToday.setEditable(false);

        fillCancelledToday.setEditable(false);

        javax.swing.GroupLayout pnTodayLayout = new javax.swing.GroupLayout(pnToday);
        pnToday.setLayout(pnTodayLayout);
        pnTodayLayout.setHorizontalGroup(
            pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fillToday)
            .addGroup(pnTodayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTodayLayout.createSequentialGroup()
                        .addComponent(lbToday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnTodayLayout.createSequentialGroup()
                        .addGroup(pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSuccessToday)
                            .addComponent(lbCancelledToday))
                        .addGap(18, 18, 18)
                        .addGroup(pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fillSuccessToday, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(fillCancelledToday)))))
        );
        pnTodayLayout.setVerticalGroup(
            pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTodayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbToday)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillToday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSuccessToday)
                    .addComponent(fillSuccessToday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCancelledToday)
                    .addComponent(fillCancelledToday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnDay.setBackground(new java.awt.Color(153, 204, 255));

        lbDay.setText("Doanh thu theo ngày");

        fillDay.setEditable(false);

        lbSuccessDay.setText("Thành công: ");

        lbCancelledDay.setText("Bị hủy: ");

        fillSuccessDay.setEditable(false);

        fillCancelledDay.setEditable(false);

        javax.swing.GroupLayout pnDayLayout = new javax.swing.GroupLayout(pnDay);
        pnDay.setLayout(pnDayLayout);
        pnDayLayout.setHorizontalGroup(
            pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fillDay)
            .addGroup(pnDayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDayLayout.createSequentialGroup()
                        .addComponent(lbDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnDayLayout.createSequentialGroup()
                        .addGroup(pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSuccessDay)
                            .addComponent(lbCancelledDay))
                        .addGap(18, 18, 18)
                        .addGroup(pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fillSuccessDay, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(fillCancelledDay)))))
        );
        pnDayLayout.setVerticalGroup(
            pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSuccessDay)
                    .addComponent(fillSuccessDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCancelledDay)
                    .addComponent(fillCancelledDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnMonth.setBackground(new java.awt.Color(153, 204, 255));

        lbMonth.setText("Doanh thu tháng này");

        fillMonth.setEditable(false);

        lbSuccessMonth.setText("Thành công: ");

        lbCancelledMonth.setText("Bị hủy: ");

        fillSuccessMonth.setEditable(false);

        fillCancelledMonth.setEditable(false);

        javax.swing.GroupLayout pnMonthLayout = new javax.swing.GroupLayout(pnMonth);
        pnMonth.setLayout(pnMonthLayout);
        pnMonthLayout.setHorizontalGroup(
            pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fillMonth)
            .addGroup(pnMonthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMonthLayout.createSequentialGroup()
                        .addComponent(lbMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnMonthLayout.createSequentialGroup()
                        .addGroup(pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSuccessMonth)
                            .addComponent(lbCancelledMonth))
                        .addGap(18, 18, 18)
                        .addGroup(pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fillSuccessMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(fillCancelledMonth)))))
        );
        pnMonthLayout.setVerticalGroup(
            pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMonthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMonth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSuccessMonth)
                    .addComponent(fillSuccessMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCancelledMonth)
                    .addComponent(fillCancelledMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnYear.setBackground(new java.awt.Color(153, 204, 255));

        lbYear.setText("Doanh thu năm nay");

        fillYear.setEditable(false);

        lbSuccessYear.setText("Thành công: ");

        lbCancelledYear.setText("Bị hủy: ");

        fillSuccessYear.setEditable(false);

        fillCancelledYear.setEditable(false);

        javax.swing.GroupLayout pnYearLayout = new javax.swing.GroupLayout(pnYear);
        pnYear.setLayout(pnYearLayout);
        pnYearLayout.setHorizontalGroup(
            pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fillYear)
            .addGroup(pnYearLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnYearLayout.createSequentialGroup()
                        .addComponent(lbYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnYearLayout.createSequentialGroup()
                        .addGroup(pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSuccessYear)
                            .addComponent(lbCancelledYear))
                        .addGap(18, 18, 18)
                        .addGroup(pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fillSuccessYear, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(fillCancelledYear)))))
        );
        pnYearLayout.setVerticalGroup(
            pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnYearLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbYear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSuccessYear)
                    .addComponent(fillSuccessYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCancelledYear)
                    .addComponent(fillCancelledYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTimeFrom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTimeFrom.setText("Thời gian");

        lbTimeTo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTimeTo.setText("đến");

        pnTop10.setBackground(new java.awt.Color(255, 255, 255));

        lbTop10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTop10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTop10.setText("TOP 10 sản phẩm bán chạy");

        lbMonthTop10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMonthTop10.setText("Tháng ");

        lbYearTop10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbYearTop10.setText("Năm ");

        tbTop10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Nhà cung cấp", "Tổng số lượng bán"
            }
        ));
        jScrollPane2.setViewportView(tbTop10);

        javax.swing.GroupLayout pnTop10Layout = new javax.swing.GroupLayout(pnTop10);
        pnTop10.setLayout(pnTop10Layout);
        pnTop10Layout.setHorizontalGroup(
            pnTop10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTop10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTop10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                    .addComponent(lbTop10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnTop10Layout.createSequentialGroup()
                        .addComponent(lbMonthTop10)
                        .addGap(18, 18, 18)
                        .addComponent(cboxMonthTop10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbYearTop10)
                        .addGap(18, 18, 18)
                        .addComponent(cboxYearTop10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnTop10Layout.setVerticalGroup(
            pnTop10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTop10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTop10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTop10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxYearTop10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboxMonthTop10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnTop10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbYearTop10)
                        .addComponent(lbMonthTop10)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Top bán chạy", pnTop10);

        pnSales.setBackground(new java.awt.Color(255, 255, 255));

        lbSales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbSales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSales.setText("Chi tiết doanh thu");

        lbYearSales.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbYearSales.setText("Năm");

        tbSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tháng", "Tổng sản phẩm đã bán", "Doanh thu"
            }
        ));
        jScrollPane1.setViewportView(tbSales);

        javax.swing.GroupLayout pnSalesLayout = new javax.swing.GroupLayout(pnSales);
        pnSales.setLayout(pnSalesLayout);
        pnSalesLayout.setHorizontalGroup(
            pnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                    .addGroup(pnSalesLayout.createSequentialGroup()
                        .addComponent(lbYearSales)
                        .addGap(18, 18, 18)
                        .addComponent(cboxYearSales, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnSalesLayout.setVerticalGroup(
            pnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboxYearSales)
                    .addComponent(lbYearSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh thu", pnSales);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnToday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTimeFrom)
                        .addGap(18, 18, 18)
                        .addComponent(cboxTimeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbTimeTo)
                        .addGap(18, 18, 18)
                        .addComponent(cboxTimeTo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnToday, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTimeFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(cboxTimeFrom)
                    .addComponent(lbTimeTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboxTimeTo))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboxMonthTop10;
    private javax.swing.JComboBox<String> cboxTimeFrom;
    private javax.swing.JComboBox<String> cboxTimeTo;
    private javax.swing.JComboBox<String> cboxYearSales;
    private javax.swing.JComboBox<String> cboxYearTop10;
    private javax.swing.JTextField fillCancelledDay;
    private javax.swing.JTextField fillCancelledMonth;
    private javax.swing.JTextField fillCancelledToday;
    private javax.swing.JTextField fillCancelledYear;
    private javax.swing.JTextField fillDay;
    private javax.swing.JTextField fillMonth;
    private javax.swing.JTextField fillSuccessDay;
    private javax.swing.JTextField fillSuccessMonth;
    private javax.swing.JTextField fillSuccessToday;
    private javax.swing.JTextField fillSuccessYear;
    private javax.swing.JTextField fillToday;
    private javax.swing.JTextField fillYear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCancelledDay;
    private javax.swing.JLabel lbCancelledMonth;
    private javax.swing.JLabel lbCancelledToday;
    private javax.swing.JLabel lbCancelledYear;
    private javax.swing.JLabel lbDay;
    private javax.swing.JLabel lbMonth;
    private javax.swing.JLabel lbMonthTop10;
    private javax.swing.JLabel lbSales;
    private javax.swing.JLabel lbSuccessDay;
    private javax.swing.JLabel lbSuccessMonth;
    private javax.swing.JLabel lbSuccessToday;
    private javax.swing.JLabel lbSuccessYear;
    private javax.swing.JLabel lbTimeFrom;
    private javax.swing.JLabel lbTimeTo;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbToday;
    private javax.swing.JLabel lbTop10;
    private javax.swing.JLabel lbYear;
    private javax.swing.JLabel lbYearSales;
    private javax.swing.JLabel lbYearTop10;
    private javax.swing.JPanel pnDay;
    private javax.swing.JPanel pnMonth;
    private javax.swing.JPanel pnSales;
    private javax.swing.JPanel pnToday;
    private javax.swing.JPanel pnTop10;
    private javax.swing.JPanel pnYear;
    private javax.swing.JTable tbSales;
    private javax.swing.JTable tbTop10;
    // End of variables declaration//GEN-END:variables
}
