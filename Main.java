package Java.AntreVaksin.src;

import java.util.Scanner;
public class Coba{
    private static String StatusUsia(int usia) {
        if (usia>=60) {
            return "LANSIA";
        } else {
            return "BUKAN_LANSIA";
        }
    }
    public static void main(String[]args){
        Scanner input=new Scanner(System.in);
        int i=0, count=0,size=100;
        String[]line=new String[size];
        String[][]array=new String[size][10];
        QueueArray pasien_antre = new QueueArray();
        QueueArray pasien_tunggu = new QueueArray();
        while(input.hasNextLine()){
            String teks=input.nextLine();
            if(teks.equals("")||teks.equals(" "))
                break;
            line[i]=teks;
            i++;
            count++;
        }
        input.close();
        for(i=0;i<count;i++){
            String []kata=line[i].split(" ");
            for(int j=0;j<kata.length;j++){
                array[i][j]=kata[j];
            }
        }
        for(i=0;i<count;i++){
                if(i==0){
                    int ukuranAwal=Integer.parseInt(array[i][0]);
                    System.out.println("Ukuran awal "+ukuranAwal);
                }
                if(i!=0){
                    switch(array[i][0]){
                        case "BARU":
                            String Nama_Penerima=array[i][1];
                            int usia=Integer.parseInt(array[i][2]);
                            int tensi=Integer.parseInt(array[i][3]);
                            int batas = Integer.parseInt(array[0][0]);
                            if (tensi>=180) {
                                System.out.println("TOLAK "+Nama_Penerima+" "+StatusUsia(usia)+" TENSI_TIDAKBOLEH_DIVAKSIN");
                            } else {
                                if(pasien_antre.getsize()<batas) {
                                    System.out.println("ANTRE "+Nama_Penerima+" "+StatusUsia(usia));
                                    if (usia>=60) {
                                        pasien_antre.addFirst(Nama_Penerima);
                                    } else {
                                        pasien_antre.addLast(Nama_Penerima);
                                    }
                                } else {
                                    if (usia>=60) {
                                        pasien_antre.addFirst(Nama_Penerima);
                                        System.out.print("ANTRE "+Nama_Penerima+" "+StatusUsia(usia));
                                        pasien_tunggu.addLast(pasien_antre.tail.data);
                                        System.out.print(" TUNGGU "+pasien_antre.tail.data+"\n");
                                        pasien_antre.removeLast();
                                    } else {
                                        System.out.println("TUNGGU "+Nama_Penerima+" "+StatusUsia(usia));
                                        pasien_tunggu.addLast(Nama_Penerima);
                                    }
                                }
                            }
                            break;
                        case "UKURAN":
                            int n=Integer.parseInt(array[i][1]);
                            System.out.println("SUKSES UBAH "+array[0][0]+" "+n);
                            array[0][0] = Integer.toString(n);
                            if (n>pasien_antre.getsize()) {
                                pasien_antre.addLast(pasien_tunggu.head.data);
                                pasien_tunggu.removeFirst();
                            } else {
                                pasien_tunggu.addLast(pasien_antre.tail.data);
                                pasien_antre.removeLast();
                            }
                            break;
                        case "SELESAI":
                            int nSelesai=Integer.parseInt(array[i][1]);
                            System.out.println("Selesai "+nSelesai+" Orang");
                            for(int x = 0;x < nSelesai;x++) {
                                pasien_antre.removeFirst();
                            }
                            break;
                        case "SELESAI_NAMA":
                            Object x = array[i][1];
                            pasien_antre.remove(x);
                            //pasien_tunggu.removeFirst();
                            System.out.println("Selesai Nama "+array[i][1]);
                            break;
                        case "SKIP":
                        String nama_Penerima = array[i][1];
                            if(pasien_antre.isEmpty()) {
                                System.out.println("SKIP GAGAL");
                            } else {
                                pasien_antre.remove(nama_Penerima);
                                pasien_tunggu.addLast(nama_Penerima);
                                System.out.println("SKIP SUKSES");
                            }
                            break;
                        case "STATUS":
                        if (pasien_antre.isEmpty()) {
                            System.out.println("DAFTAR_ANTRE : -");
                        } else {
                            System.out.print("DAFTAR_ANTRE : ");
                            pasien_antre.print();
                        }

                        if (pasien_tunggu.isEmpty()) {
                            System.out.println("DAFTAR_TUNGGU : -");
                        } else {
                            System.out.print("DAFTAR_TUNGGU : ");
                            pasien_tunggu.print();
                        }
                            break;
                        default:
                            break;
                    }
                }
        }
    }
}

class QueueArray {
    Node head;
    Node tail;
    int size = 0;

    void inisialisasiNull() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    public void makeEmpty() {
        head=tail=null;
        size = 0;
    }

    int getsize() {
        return size;
    }

    public void removeFirst(){
    // Tuliskan kodemu di sini
        if (head == tail) {
            head=tail=null;
        } else {
            Node a = head;
            head = a.next;
        }
        size--;
    }

    public void remove(Object x) {
        Node a = head;
        int count = 0, itung = 1;
        while (!a.data.equals(x)) {
            a = a.next;
            count++;
        }
        a = head;
        while (a != null) {
            if (count == itung) {
                a.next = a.next.next;
            }
            itung++;
            a = a.next;
        }
        size--;
    }
	
	void removeLast() {
        if (head == tail) {
              inisialisasiNull();
        } else {
              Node s = head;
              while (s.next != tail)
                   s = s.next;
              tail = s;
              tail.next = null;
              size--;
        }
    }

    public void addFirst(Object x) {
        Node temp = new Node(x);
        if (isEmpty()) {
            head=tail=temp;
        } else {
            temp.next = head;
            head = temp;
        }
        size++;
    }
  
    void addLast(Object x) {
        Node input = new Node(x);
        if (isEmpty()) {
            head=tail=input;
        } else {
            tail.next = input;
            tail = input;
        }
        size++;
    }        
    
    public void print() {
        Node penetrate = head;
        while(penetrate != null) {
            System.out.print(penetrate.data+"  ");
            penetrate = penetrate.next;
        }
        System.out.println();
    }
}

class Node {
    Object data;
    Node next;
    Node prev;

    Node(Object data) {
        this.data = data;
    }

    Node(Object data, Node prev, Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
