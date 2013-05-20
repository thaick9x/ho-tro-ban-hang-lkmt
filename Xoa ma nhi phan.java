import java.lang.System;
import java.util.Scanner;

import javax.naming.BinaryRefAddr;

public class fuckingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stu
		Scanner in = new Scanner(System.in);
		System.out.print("Hay nhap so thap phan chuong trinh se xu ly:\t");
		int x = in.nextInt();
		System.out.println("Chuyen sang so nhi phan: " + 
							Integer.toBinaryString(x));
		
		System.out.print("Hay nhap thu tu can xoa bit:\t");
		int Ndel = in.nextInt();
		System.out.print("Hay nhap thu tu can kiem tra bit:\t");
		int Ncheck = in.nextInt();
		
		System.out.println("Sau khi xoa bit thu " + Ndel +
							": " + DeleteBit(x, Ndel) + 
							"(" + Integer.toBinaryString(DeleteBit(x, Ndel)) + ")");
		System.out.println("Chen them bit vao (bit left-most):" 
							+ InsertBit(x) + 
							"(" + Integer.toBinaryString(InsertBit(x)) + ")" 
				);
		System.out.println("Bit thu " + Ncheck + " la " + CheckBit(x,Ncheck));
		in.nextInt();

	}
	
	/*Ham delete mot bit tai vi tri thu N */
	public static int DeleteBit (int x, int N){
		if (N == 1)
			return x >> 1;
		int temp = 1 << N - 2; 
		temp = temp | (temp - 1);
		return ((x >> N) << N - 1) + (x & (temp));
	}
	
	/*Ham Insert mot tai vi tri cuoi cung ben trai cua Binary
	 * chu y: Ham nay se insert bit 1 vao, con bit 0 thi xem nhu khong
	 * lam thay doi gia tri
	 * */
	public static int InsertBit (int x) {
		return x + (Integer.highestOneBit(x) << 1);
	}
	/* Ham kiem tra gia tri cua bit thu N */
	public static int CheckBit (int x, int N){
		if ((x & (1 << N - 1)) == 0)
			return 0;
		else
			return 1;
	}

}
