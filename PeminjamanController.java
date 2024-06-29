/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Buku;
import model.BukuDao;
import model.Mahasiswa;
import model.MahasiswaDao;
import model.Peminjaman;
import model.PeminjamanDao;
import view.FormPeminjaman;

/**
 *
 * @author ORYZA CONSEVA
 */
public class PeminjamanController {
    FormPeminjaman viewPeminjaman;
    Peminjaman peminjaman;
    MahasiswaDao mahasiswaDao;
    BukuDao bukuDao;
    PeminjamanDao peminjamanDao;

    public PeminjamanController(FormPeminjaman viewPeminjaman) {
        this.viewPeminjaman = viewPeminjaman;
        mahasiswaDao = new MahasiswaDao();
        bukuDao = new BukuDao();
        peminjamanDao = new PeminjamanDao();
    }
    
    public void clearForm(){
        viewPeminjaman.getTxtTglPinjam().setText("");
        viewPeminjaman.getTxtTglKembali().setText("");
    }
    
    public void isiCboMahasiswa(){
        List<Mahasiswa> list = mahasiswaDao.getAllMahasiswa();
        viewPeminjaman.getCboMahasiswa().removeAllItems();
        for (Mahasiswa mahasiswa : list) {
            viewPeminjaman.getCboMahasiswa()
                    .addItem(mahasiswa.getNobp()+"-"+mahasiswa.getNama());
        }
    }
    
    public void isiCboBuku(){
        List<Buku> list = bukuDao.getAllBuku();
        viewPeminjaman.getCboBuku().removeAllItems();
        for (Buku buku : list) {
            viewPeminjaman.getCboBuku()
                    .addItem(buku.getKodeBuku()+"-"+buku.getJudul());
        }
    }
    
    public void insert(){
        peminjaman = new Peminjaman();
        Mahasiswa mahasiswa = mahasiswaDao.getMahasiswa(
                viewPeminjaman.getCboMahasiswa().getSelectedIndex());
        Buku buku = bukuDao.getBuku(
                viewPeminjaman.getCboBuku().getSelectedIndex());
        peminjaman.setMahasiswa(mahasiswa);
        peminjaman.setBuku(buku);
        peminjaman.setTglpinjam(viewPeminjaman.getTxtTglPinjam().getText());
        peminjaman.setTglkembali(viewPeminjaman.getTxtTglKembali().getText());
        peminjamanDao.insert(peminjaman);
        JOptionPane.showMessageDialog(viewPeminjaman, "Entri Data Ok");
    }
    
    public void update(){
        int index = viewPeminjaman.getTabelPeminjaman().getSelectedRow();
        peminjaman = new Peminjaman();
        Mahasiswa mahasiswa = mahasiswaDao.getMahasiswa(
                viewPeminjaman.getCboMahasiswa().getSelectedIndex());
        Buku buku = bukuDao.getBuku(
                viewPeminjaman.getCboBuku().getSelectedIndex());
        peminjaman.setMahasiswa(mahasiswa);
        peminjaman.setBuku(buku);
        peminjaman.setTglpinjam(viewPeminjaman.getTxtTglPinjam().getText());
        peminjaman.setTglkembali(viewPeminjaman.getTxtTglKembali().getText());
        peminjamanDao.update(index, peminjaman);
        JOptionPane.showMessageDialog(viewPeminjaman, "Update Data Ok");
    }
    
    public void delete(){
        int index = viewPeminjaman.getTabelPeminjaman().getSelectedRow();
        peminjamanDao.delete(index);
        JOptionPane.showMessageDialog(viewPeminjaman, "Delete Data Ok");
    }
    
    
    public void viewData(){
        DefaultTableModel model = 
                (DefaultTableModel)viewPeminjaman.getTabelPeminjaman().getModel();
        model.setNumRows(0);
        List<Peminjaman> list = peminjamanDao.getAllPeminjaman();
        for(Peminjaman peminjaman : list){
            Object[] data = {
                peminjaman.getMahasiswa().getNobp(),
                peminjaman.getBuku().getKodeBuku(),
                peminjaman.getTglpinjam(),
                peminjaman.getTglkembali()
                     
            };
            model.addRow(data);
        }
    }
    
    public void getPeminjaman(){
        int index = viewPeminjaman.getTabelPeminjaman().getSelectedRow();
        peminjaman = peminjamanDao.getPeminjaman(index);
        if(peminjaman != null) {
            viewPeminjaman.getCboMahasiswa().setSelectedItem(
                    peminjaman.getMahasiswa().getNobp()+"-"+peminjaman.getMahasiswa().getNama());
            viewPeminjaman.getCboBuku().setSelectedItem(
                    peminjaman.getBuku().getKodeBuku()+"-"+peminjaman.getBuku().getJudul());
            viewPeminjaman.getTxtTglPinjam().setText(peminjaman.getTglpinjam());
            viewPeminjaman.getTxtTglKembali().setText(peminjaman.getTglkembali());
        }
    }  
}
