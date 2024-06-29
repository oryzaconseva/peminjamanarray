/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ORYZA CONSEVA
 */
public class PeminjamanDao {
    private List<Peminjaman> data = 
            new ArrayList<Peminjaman>();

    public PeminjamanDao() {
        MahasiswaDao mahasiswaDao = new MahasiswaDao();
        BukuDao bukuDao = new BukuDao();
        data.add(new Peminjaman(mahasiswaDao.getMahasiswa(0), bukuDao.getBuku(0), 
                "2022-01-01", "2022-01-03"));
    }
    
    public void insert(Peminjaman peminjaman){
        data.add(peminjaman);
    }
    
    public void update(int index,Peminjaman m){
        data.set(index,m);
    }
    
    public void delete(int index){
        data.remove(index);
    }
    
    public Peminjaman getPeminjaman(int index){
        return data.get(index);
    }
    
    public List<Peminjaman> getAllPeminjaman(){
        return data;
    }
}
