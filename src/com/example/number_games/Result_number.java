package com.example.number_games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Result_number {
	TextView text1,text2;
	Button[] btn;
    public int firstshuzi=0;
    int sPosition;
    public static Integer result;
    public Result_number() {
	}
    public Result_number(TextView text1,Button[] btn) {
		this.text1=text1;
		this.btn=btn;
	}
    public int byNumGetSigh() {
		Random random = new Random();
		int firPosition = random.nextInt(5) * 2;
		firstshuzi = firPosition;
		int arrayPosition;
		Random random2 = new Random();
		switch (firPosition) {
		case 0:
			int[] p = { 1, 3 };
			arrayPosition = random2.nextInt(2);
			return p[arrayPosition];
		case 2:
			int[] p1 = { 1, 5 };
			arrayPosition = random2.nextInt(2);
			return p1[arrayPosition];

		case 4:
			int[] p2 = { 1, 3, 5, 7 };
			arrayPosition = random2.nextInt(4);
			return p2[arrayPosition];
		case 6:
			int[] p3 = { 3, 7};
			arrayPosition = random2.nextInt(2);
			return p3[arrayPosition];

		case 8:
			int[] p4 = {5,7};
			arrayPosition = random2.nextInt(2);
			return p4[arrayPosition];
		default:
			return -1;
		}

	}

	public int[] getRuslt() {
	   sPosition = byNumGetSigh();
		int arrayPosition;
		int thPosition;
		Random random2 = new Random();
		switch (sPosition) {
		case 1:
			int p[] ={0,2,4};
			List<Integer> pl  = new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p[i]!=firstshuzi){
            		 pl.add(p[i]);
            	 }
             }
              arrayPosition = random2.nextInt(2);
              thPosition= pl.get(arrayPosition);
		return  new int[]{firstshuzi,sPosition,thPosition };
		case 3:
			int p3[] ={0,4,6};
			List<Integer> p1 = new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p3[i]!=firstshuzi){
            		 p1.add(p3[i]);
            	 }
             }
              arrayPosition = random2.nextInt(2);
              thPosition= p1.get(arrayPosition);
		return  new int[]{firstshuzi,sPosition,thPosition };
		case 5:
			int p5[] ={2,4,8};
			List<Integer> p2 = new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p5[i]!=firstshuzi){
            		 p2.add(p5[i]);
            	 }
             }
              arrayPosition = random2.nextInt(2);
              thPosition= p2.get(arrayPosition);
		return  new int[]{firstshuzi,sPosition,thPosition };
			
		case 7:
			int p7[] ={6,4,8};
			List<Integer> p4= new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p7[i]!=firstshuzi){
            		 p4.add(p7[i]);
            	 }
             }
              arrayPosition = random2.nextInt(2);
              thPosition= p4.get(arrayPosition);
		return  new int[]{firstshuzi,sPosition,thPosition };
		default:
			break;
		}
		return null;
	} 
	public void aim() {
		int[] a= getRuslt();
		String	m=btn[a[0]].getText().toString();
		String	n=btn[a[2]].getText().toString();
		String	f=btn[a[1]].getText().toString();

        if(f.equals("+")){
			result = Integer.valueOf(m) + Integer.valueOf(n);
			text1.setText(Math.abs(result) + " ");
        }
        if(f.equals("-")){	
        	result=Integer.valueOf(m)-Integer.valueOf(n);
            text1.setText(Math.abs(result)+" ");
        }
        Log.e("-11111111111---------------",a[0]+"");
        Log.e("-3333333333333333---------------", a[2]+"");
        Log.e("-222222222222222---------------", a[1]+"");
	}
	
	
}
