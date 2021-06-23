/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daodonrac;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Hau Depp Troaiiii ^^
 */
public class DAO_DonRac {
    
  
    DuongDi mtTP[][] = new DuongDi[20][20]; // chua cost duong di
    Random generator = new Random();
    int mtKe[][] = new int[20][20]; //
    int luuDuongTam[] = new int[20];//luu chu trình hiện tại. ktra xem phải hamilton hay k 
    int Heuristic[] = new int[20];
    ;// tung` di qua r
    int quan = 19;
    ArrayList<ArrayList> luuDuongDi = new ArrayList<>();//luu chu trinh hamilton trong đường đi tạm
    int d = 0;//so duong di(chu trinh)
    int index = 0;//chu trinh tai luuduongdi[index] ngan nhat
    int min = 0;//cost thap nhat de di
    ArrayList<Integer> ketQua = new ArrayList<>();//
    int quanBatDau = 1;
    int indexdi = 0; //bntNext

    
    void Hamilton(int B[], int C[], int i)//luu duong di, luu heurictis, index(k dc trung`)
    { //ktra mtKe đủ 20 nút thì là chu trình hamilton.. k phai thi skip
        int j;
        for (j = 1; j <= quan; j++) {
            if (mtKe[B[i - 1]][j] == 1 && C[j] == 0) {
                B[i] = j;
                C[j] = 1;
                if (i < quan) {
                    Hamilton(B, C, i + 1);
                } else if (B[i] == B[0]) {
                    luuchutrinh();
                }
                //inmanhinhchutrinh();
                C[j] = 0;
            }
        }
    }

    private void inmanhinhmtKe() {
        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                System.out.print(mtKe[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void inmanhinhchutrinh() {
        this.d++;
        System.out.print("Chu trinh: ");
        for (int i = quan; i >= 0; i--) {
            System.out.print(this.luuDuongTam[i] + " ");
        }
        System.out.println();
    }

    private void luuchutrinh() {
        d++;
        ArrayList<Integer> duongdi = new ArrayList<>();
        for (int i = quan; i >= 0; i--) {
            duongdi.add(luuDuongTam[i]);
        }
        luuDuongDi.add(duongdi);
    }

    private void timIndexNganNhat() {

        for (int i = 0; i < quan; i++) {
            int j = i + 1;
            min += mtTP[Integer.parseInt(luuDuongDi.get(index).get(i).toString())][Integer.parseInt(luuDuongDi.get(index).get(j).toString())].getCost();
        }

        for (int i = 1; i < luuDuongDi.size(); i++) {
            int temp = 0;
            for (int j = 0; j < quan; j++) {
                int f = j + 1;
                temp += mtTP[Integer.parseInt(luuDuongDi.get(i).get(j).toString())][Integer.parseInt(luuDuongDi.get(i).get(f).toString())].getCost();
            }
            if (temp < min) {
                min = temp;
                index = i;
            }
        }
    }

    private void timKetQua() // in ra man hinh
    {
        if (quanBatDau == 1) {
            for (int i = 0; i <= quan; i++) {
                ketQua.add(Integer.parseInt(luuDuongDi.get(index).get(i).toString()));
            }
        } else {
            int dem = 0;
            for (; dem <= quan; dem++) {
                if (Integer.parseInt(luuDuongDi.get(index).get(dem).toString()) == quanBatDau) {
                    break;
                }
            }
            for (int i = dem; i <= quan; i++) {
                ketQua.add(Integer.parseInt(luuDuongDi.get(index).get(i).toString()));
            }
            for (int i = 1; i < dem; i++) {
                ketQua.add(Integer.parseInt(luuDuongDi.get(index).get(i).toString()));
            }
            ketQua.add(quanBatDau);
        }
    }

}
    
    
    

