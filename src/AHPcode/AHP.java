package AHPcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AHP {

	public static void main(String[] args) {
		
		int i, j, n, m, k;
		double p=0, q=0;
		Scanner s = new Scanner(System.in);
		System.out.println("Nhap so phuong an:");
		n = s.nextInt();
		System.out.println("Nhap so tieu chi:");
		m = s.nextInt();
		
		//matrix quan he RI
		double RI[][] = {{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
				         {0,0,0.52,0.89,1.11,1.25,1.35,1.40,1.45,1.49,1.52,1.54,1.56,1.58,1.59}
		                };
		
		//test data
		/*
		 n=3, m=4
		  ma tran 1: 1 3 2
		             0.3333 1 0.2
		             0.5 5 1
		             
		  ma tran 2: 1 6 0.3333
		             0.1667 1 0.1111
		             3 9 1
		             
		  ma tran 3: 1 0.3333 1
		             3 1 7
		             1 0.1429 1
		            
		  ma tran 4: 1 0.3333 0.5
		             3 1 4
		             2 0.25 1
		  
		  ma tran sap hang cac tieu chi tieu chi: 1 0.2 3 4
		                                          5 1 9 7
		                                          0.3333 0.1111 1 2
		                                          0.25 0.1429 0.5 1
		 * */
		//ArrayList<Double> T = new ArrayList<Double>();
		double A[][] = new double[n][n]; //n phuong an
		double C[][] = new double[n][m];  //ma tran tieu chi
		double E[][] = new double[n][n];
		double F[][] = new double[n][1];
		double D[][] = new double[1][n];
		
		double B[][] = new double[m][m]; //m tieu chi
		double Q[][] = new double[1][m];
		double P[][] = new double[m][1];
		double Y[][] = new double[m][m];
		
		double AHP[][]= new double[n][1];
		
		int o=1;
		//double G = new double[n*m];
		//nhap ma tran do uu tien cac phuong an ung voi tung tieu chi
		 for (k=0;k<m;k++) {
			 System.out.println("Do uu tien cac phuong an ung voi tieu chi "+o+":");
			 //Nhap mang 
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 A[i][j] = s.nextDouble();
				 }
			 }
			 //cong tong cac cot
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 D[0][i]+=A[j][i];
			   }
			 }
			 //chia cac phan tu cho tong cac cot tuong ung
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 E[j][i]=A[j][i]/D[0][i];
			   }
			 }
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					F[i][0]+=E[i][j]; 
			   }
			  }
			 //tim gia tri tb cho moi hang
			 for(i=0;i<n;i++) {
				 for(j=0;j<1;j++) {
					F[i][0]=F[i][0]/n; 
			   }
			  }
			 //lay 4 chu so thap phan, luu vao mang moi
			 for(i=0;i<n;i++) {
				 C[i][k]=Math.round(F[i][0]*10000.00)/10000.00;
			 }
			 
			 //reset cac mang ve 0
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 A[i][j] = 0;
				 }
			 }
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 D[0][i]=0;
			   }
			 }
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 E[j][i]=0;
			   }
			 }
			 for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					F[i][0]=0; 
			   }
			  }
			/*
			 System.out.println();
			 System.out.println("Ma tran tieu chi la:");
			 for(i=0;i<n;i++) {
				 for(j=0;j<m;j++) {
					System.out.print(C[i][j]+"\t"); 
			   }
				 System.out.println();
			  } */
			 o++;
		 }
		/* int x=0;
		 Double[] G =T.toArray(new Double[T.size()]);
		 
		 for(i=0;i<n;i++) {
			 for(j=0;j<m;j++) {
				C[i][j]= G[x];
				x++;
		   }
		  } */

		 
		 //ma tran xep hang cac tieu chi
		 System.out.println("Ma tran xep hang cac tieu chi theo do quan trong:");
		 for(i=0;i<m;i++) {
			 for(j=0;j<m;j++) {
				 B[i][j] = s.nextDouble();
			 }
		 }
		//cong tong cac cot
		 for(i=0;i<m;i++) {
			 for(j=0;j<m;j++) {
				 Q[0][i]+=B[j][i];
		   }
		 }
		 //chia cac phan tu cho tong cac cot tuong ung
		 for(i=0;i<m;i++) {
			 for(j=0;j<m;j++) {
				 Y[j][i]=B[j][i]/Q[0][i];
		   }
		 }
		 for(i=0;i<m;i++) {
			 for(j=0;j<m;j++) {
				P[i][0]+=Y[i][j]; 
		   }
		  }
		 //tim gia tri tb cho moi hang
		 for(i=0;i<m;i++) {
				P[i][0]=P[i][0]/m; 
		  }
		 
		 //lay 4 chu so thap phan, luu vao mang moi
		 for(i=0;i<m;i++) {
			 P[i][0]=Math.round(P[i][0]*10000.00)/10000.00;
		 }
		 
		 //Ma tran cac tieu chi
		 System.out.println();
		 System.out.println("Ma tran tieu chi la:");
		 System.out.print("\t");
		 for(i=1;i<=m;i++) {
			 System.out.print("TieuChi"+i+"\t");
		 }
		 System.out.println();
		 for(i=0;i<n;i++) {
			 System.out.print("PA"+(i+1)+"\t  ");
			 for(j=0;j<m;j++) {
				System.out.print(C[i][j]+"\t  "); 
		   }
			 System.out.println();
		  }
		 
		 //Vecto do uu tien cac tieu chi
		 System.out.println();
		 System.out.println("Vecto do uu tien cac tieu chi:");
		 for(i=0;i<m;i++) {
			 System.out.print("TieuChi"+(i+1)+"\t");
			 System.out.println(P[i][0]);
		 }
		 
		 double z=0;
		 //Tinh ma tran ket qua
		 for (i = 0; i < n; i++) {
             for (j = 0; j < m; j++) {
            	// for(k=0;k < m;k++) {
                     z += C[i][j] * P[j][0];                    
                 }
            // }
             AHP[i][0] = Math.round(z*10000.00)/10000.00;
             z=0;
         }
		 //in ma tran ket qua 
		 System.out.println();
		 System.out.println("Ma tran ket qua la:");
		 for(i=0;i<n;i++) {
			 System.out.print(AHP[i][0]+"\t"); 
			 System.out.println();
		  }
		 
		 double max = AHP[0][0];
		 int l=0;
		 //
		 for (i = 0; i < n; i++) {    
		            if( max < AHP[i][0]) 
		            { 
		                max = AHP[i][0];
		                l=i+1;
		            } 
		 }
		 System.out.println("Ket qua chon phuong an thu: "+l);
		 
	}
}
