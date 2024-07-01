/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ForumNilai;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


/**
 *
 * @author pramu
 */
public class ForumNIlaiMhs extends javax.swing.JFrame {

    /**
     * Creates new form ForumNIlaiMhs
     */
    public class DBConnection{
        public Connection getConnection(){
            Connection con = null;
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/pbo_test", "root", "");
                //JOptionPane.showMessageDialog(null, "Koneksi Sukses");
                return con;
            }catch(SQLException ex){
                java.util.logging.Logger.getLogger(ForumNIlaiMhs.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }
    
    DefaultTableModel tabel1;
    Object[] listMhs = new Object[9];
    int x = 0;
    DBConnection dbConn = new DBConnection();
    Connection con = dbConn.getConnection();
    public ForumNIlaiMhs(){
        initComponents();
        try {
            tampilkanDiTabel();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menampilkan data dari database", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        //getConnection();
        tabel1 =new DefaultTableModel();
        table.setModel(tabel1);
        tabel1.addColumn("No");
        tabel1.addColumn("Nim");
        tabel1.addColumn("Nama");
        tabel1.addColumn("Nilai Uts");
        tabel1.addColumn("Nilai Uas");
        tabel1.addColumn("Nilai Tugas");
        tabel1.addColumn("Nilai Akhir");
        tabel1.addColumn("Nilai Huruf");
        tabel1.addColumn("Predikat");
        setResizable(false);
        
        nUTS1.setEditable(false);
        nUAS1.setEditable(false);
        nTugas1.setEditable(false);
        nUTS2.setEditable(false);
        nTugas2.setEditable(false);
        nUAS2.setEditable(false);
        simpan.setEnabled(false);
        update.setEnabled(false);
        hapus.setEnabled(false);
        proses1.setEnabled(false);
    }
    public void kosongkanTextField(){
        nim.setText("");
        nama.setText("");
        nUTS.setText("");
        nUAS.setText("");
        nTugas.setText("");
        
        nUTS1.setText("");
        nUAS1.setText("");
        nTugas1.setText("");
        nUTS2.setText("");
        nTugas2.setText("");
        nUAS2.setText("");

    }
    public void prosesHitungNilai(){
        try{
            String ni = nim.getText();  String na = nama.getText();
            Double ts = Double.parseDouble(nUTS.getText());
            Double as = Double.parseDouble(nUAS.getText());
            Double tgs = Double.parseDouble(nTugas.getText());
                
                Mhs m = new Mhs(ni, na, ts, as, tgs);
                // Mengatur nilai pada komponen-komponen GUI sesuai dengan nilai mahasiswa
                nUTS1.setText(String.valueOf(m.getNilai_uts()));
                nUAS1.setText(String.valueOf(m.getNilai_uas()));
                nTugas1.setText(String.valueOf(m.getNilai_tugas()));
                nUTS2.setText(String.valueOf(m.getNilai_akhir()));
                nUAS2.setText(String.valueOf(m.getNilai_huruf()));
                nTugas2.setText(m.getPredikat());
                simpan.setEnabled(true);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Inputan Anda Kosong","Warning",JOptionPane.WARNING_MESSAGE);
        }

    }
    public ArrayList[] getMhsList() throws SQLException{      
        String queryCount = "SELECT COUNT(*) as c FROM mhs";
        Statement st;
        ResultSet rsCount, rs;       
        st = con.createStatement();        
        rsCount = st.executeQuery(queryCount);
        int sizeTable=0;

        while(rsCount.next()){
            sizeTable = rsCount.getInt("c");
        }       
        ArrayList[] mhsList = new ArrayList[sizeTable];       
        String query = "SELECT * FROM mhs";
                rs = st.executeQuery(query);

       int x=0;
        while(rs.next()){
            mhsList[x] = new ArrayList<>();
            mhsList[x].add(rs.getString("nim"));
            mhsList[x].add(rs.getString("nama"));
            mhsList[x].add(rs.getDouble("nilai_uts"));
            mhsList[x].add(rs.getDouble("nilai_uas"));
            mhsList[x].add(rs.getDouble("nilai_tugas"));
            mhsList[x].add(rs.getDouble("nilai_akhir"));
            mhsList[x].add(rs.getString("nilai_huruf"));
            mhsList[x].add(rs.getString("predikat"));
            x++;
        }
        return mhsList;
    }
    public void tampilkanDiTabel() throws SQLException{
        ArrayList[] list = getMhsList();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
       
        Object[] row = new Object[9];
        for(int i = 0; i<list.length; i++){
            row[0] = i+1;
            row[1] = list[i].get(0); row[2] = list[i].get(1);
            row[3] = list[i].get(2); row[4] = list[i].get(3);
            row[5] = list[i].get(4); row[6] = list[i].get(5);
            row[7] = list[i].get(6); row[8] = list[i].get(7);

            model.addRow(row);
        }       
    }
    public void kosongkanTabel(){
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        model.setRowCount(0);
    }
    public void terpilih(int index) throws SQLException{
        ArrayList[] list = getMhsList();
        nim.setText((String) list[index].get(0));
        nama.setText((String) list[index].get(1));
        nUTS.setText((String) list[index].get(2).toString());
        nUAS.setText((String) list[index].get(3).toString());
        nTugas.setText((String) list[index].get(4).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        proses7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        proses8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nim = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nUTS = new javax.swing.JTextField();
        nUAS = new javax.swing.JTextField();
        nTugas = new javax.swing.JTextField();
        proses = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        tambahLain = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        proses1 = new javax.swing.JButton();
        update = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nTugas2 = new javax.swing.JTextField();
        nUAS2 = new javax.swing.JTextField();
        nUTS2 = new javax.swing.JTextField();
        nUAS1 = new javax.swing.JTextField();
        nTugas1 = new javax.swing.JTextField();
        nUTS1 = new javax.swing.JTextField();

        jLabel11.setText("jLabel7");

        proses7.setText("Proses");

        jLabel12.setText("jLabel7");

        proses8.setText("Proses");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Forum Nilai Mahasiswa");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setText("NIM");

        jLabel3.setText("Nama");

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });

        jLabel4.setText("Nilai UTS");

        jLabel5.setText("Nilai UAS");

        jLabel6.setText("Nilai Tugas");

        nUTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nUTSActionPerformed(evt);
            }
        });

        nUAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nUASActionPerformed(evt);
            }
        });

        nTugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nTugasActionPerformed(evt);
            }
        });

        proses.setText("Proses");
        proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesActionPerformed(evt);
            }
        });

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        tambahLain.setText("Tambah Lain");
        tambahLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahLainActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        proses1.setText("Proses");
        proses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proses1ActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        jLabel7.setText("UTS 35%");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel8.setText("Nilai Akhir");

        jLabel9.setText("UAS 35%");

        jLabel10.setText("Predikat");

        jLabel13.setText("Tugas 30%");

        jLabel14.setText("Nilai Huruf");

        nTugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nTugas2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(nUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(nUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(nTugas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(proses1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(proses)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(simpan)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tambahLain)))
                                        .addGap(18, 18, 18)
                                        .addComponent(keluar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(nUAS1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13)
                                        .addGap(49, 49, 49))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(540, 540, 540)
                                .addComponent(nTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nim, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(123, 123, 123)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(nUTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(nUTS2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel1)))
                        .addGap(97, 97, 97))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(545, 545, 545))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(nUAS2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(nTugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(91, 91, 91)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(nUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nTugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(proses)
                            .addComponent(tambahLain)
                            .addComponent(simpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(keluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proses1)
                    .addComponent(update)
                    .addComponent(hapus))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(nUTS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(nTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(nUAS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(nTugas2)
                        .addComponent(nUAS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(nUTS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nUASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nUASActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nUASActionPerformed

    private void nUTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nUTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nUTSActionPerformed

    private void proses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proses1ActionPerformed
        // TODO add your handling code here:
        prosesHitungNilai();
        update.setEnabled(true);
    }//GEN-LAST:event_proses1ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        String updateQuery = "UPDATE mhs SET nim=?, nama=?, nilai_uts=?, nilai_uas=?, nilai_tugas=?, nilai_akhir=?, nilai_huruf=?, predikat=? " +
                         "WHERE nim=?";
        try {
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.setString(1, nim.getText());
            ps.setString(2, nama.getText());
            ps.setString(3, nUTS.getText());
            ps.setString(4, nUAS.getText());
            ps.setString(5, nTugas.getText());
            ps.setString(6, nUTS2.getText());
            ps.setString(7, nUAS2.getText());
            ps.setString(8, nTugas2.getText());
            ps.setString(9, nim.getText()); // nim untuk WHERE clause

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak berhasil diupdate");
            }

            kosongkanTextField();
            kosongkanTabel();
            tampilkanDiTabel();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data tidak berhasil diupdate");
            ex.printStackTrace(); // Tambahkan ini untuk melihat detail kesalahan pada konsol
        }
    }//GEN-LAST:event_updateActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM mhs WHERE nim = ?");
            String nimNya = nim.getText();
            ps.setString(1, nimNya);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Mahasiswa berhasil dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Mahasiswa tidak berhasil dihapus");
            java.util.logging.Logger.getLogger(ForumNIlaiMhs.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                kosongkanTextField(); // Pastikan metode ini tidak menyebabkan NullPointerException
                kosongkanTabel(); // Pastikan metode ini tidak menyebabkan NullPointerException
                tampilkanDiTabel(); // Pastikan metode ini tidak menyebabkan SQLException
            } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ForumNIlaiMhs.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keluarActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO mhs(nim, nama, nilai_uts, nilai_uas, nilai_tugas, nilai_akhir, nilai_huruf, predikat) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nim.getText());
            ps.setString(2, nama.getText());
            ps.setString(3, nUTS.getText());
            ps.setString(4, nUAS.getText());
            ps.setString(5, nTugas.getText());
            ps.setString(6, nUTS2.getText());
            ps.setString(7, nUAS2.getText());
            ps.setString(8, nTugas2.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data tersimpan");

            kosongkanTextField();
            kosongkanTabel();
            tampilkanDiTabel();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan");
            ex.printStackTrace(); // Tambahkan ini untuk melihat detail kesalahan pada konsol
        }
      
    }//GEN-LAST:event_simpanActionPerformed

    private void prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesActionPerformed
        // TODO add your handling code here:
        prosesHitungNilai();
    }//GEN-LAST:event_prosesActionPerformed

    private void tambahLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahLainActionPerformed
        // TODO add your handling code here:
        kosongkanTextField();
        simpan.setEnabled(false);
    }//GEN-LAST:event_tambahLainActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int index = table.getSelectedRow();
        try {
            terpilih(index);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ForumNIlaiMhs.class.getName()).log(Level.SEVERE, null, ex);
        }
        proses1.setEnabled(true);
        hapus.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void nTugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nTugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nTugasActionPerformed

    private void nTugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nTugas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nTugas2ActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//               
//            }
//        });
//    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField nTugas;
    private javax.swing.JTextField nTugas1;
    private javax.swing.JTextField nTugas2;
    private javax.swing.JTextField nUAS;
    private javax.swing.JTextField nUAS1;
    private javax.swing.JTextField nUAS2;
    private javax.swing.JTextField nUTS;
    private javax.swing.JTextField nUTS1;
    private javax.swing.JTextField nUTS2;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nim;
    private javax.swing.JButton proses;
    private javax.swing.JButton proses1;
    private javax.swing.JButton proses7;
    private javax.swing.JButton proses8;
    private javax.swing.JButton simpan;
    private javax.swing.JTable table;
    private javax.swing.JButton tambahLain;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
