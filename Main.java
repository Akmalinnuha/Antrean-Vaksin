import java.util.Scanner;
public class Main{
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
                            if (tensi>=180) {
                                System.out.println("TOLAK "+Nama_Penerima+" "+StatusUsia(usia)+" TENSI_TIDAKBOLEH_DIVAKSIN");
                            } else {
                                System.out.println("ANTRE "+Nama_Penerima+" "+StatusUsia(usia));
                                if (usia>=60) {
                                    pasien_antre.addFirst(Nama_Penerima);
                                } else {
                                    pasien_antre.addLast(Nama_Penerima);
                                }
                            }
                            break;
                        case "UKURAN":
                            int n=Integer.parseInt(array[i][1]);
                            System.out.println("Ukuran Daftar Antre berubah jadi "+n);
                            break;
                        case "SELESAI":
                            int nSelesai=Integer.parseInt(array[i][1]);
                            System.out.println("Selesai "+nSelesai+" Orang");
                            for(int x = 0;x < nSelesai;x++) {
                                pasien_antre.removeFirst();
                            }
                            break;
                        case "SELESAI_NAMA":
                            pasien_antre.removeFirst();
                            pasien_tunggu.removeFirst();
                            System.out.println("Selesai Nama "+array[i][1]);
                            break;
                        case "SKIP":
                            String nama_Penerima = array[i][1];
                            pasien_antre.remove(nama_Penerima);
                            pasien_tunggu.addLast(nama_Penerima);
                            System.out.println("Skip "+array[i][1]);
                            break;
                        case "STATUS":
                        pasien_antre.print();
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
