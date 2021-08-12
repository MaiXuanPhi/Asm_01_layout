package com.example.asm_01_layout.model;

public class GiaoDich {
    private int maGd, tien, maLoai;
    private String tieuDe, ngay, moTa;

    public GiaoDich(int maGd, int tien, int maLoai, String tieuDe, String ngay, String moTa) {
        this.maGd = maGd;
        this.tien = tien;
        this.maLoai = maLoai;
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.moTa = moTa;
    }

    public GiaoDich(int tien, int maLoai, String tieuDe, String ngay, String moTa) {
        this.tien = tien;
        this.maLoai = maLoai;
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.moTa = moTa;
    }

    public int getMaGd() {
        return maGd;
    }

    public void setMaGd(int maGd) {
        this.maGd = maGd;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
