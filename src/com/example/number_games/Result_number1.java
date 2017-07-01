package com.example.number_games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Result_number1 {
	Result_number rnumber=new Result_number();
	TextView text1;
	Button[] btn;
    private int threeshuzi=0;
    public  Integer result1,result2;
    int w,ff,m;
    public Result_number1() {}
    public Result_number1(TextView text1,Button[] btn) {	
		this.text1=text1;
		this.btn=btn;
	}
    public int twonumber(){
    	int[] a=rnumber.getRuslt();
    	 m=a[2];//2
    	 ff=a[1]; //+
    	 w=a[0];//3+2 -1
		threeshuzi = m;
		int arrayPosition;
		Random random2 = new Random();
		switch (m) {//最后一个数字
		case 0:
			int[] p = { 1, 3 };
			List<Integer> pl  = new ArrayList<Integer>();
			for (int i = 0; i < 2; i++) {
				if (p[i] != ff) {
					pl.add(p[i]);
				}
			}
			arrayPosition = random2.nextInt(pl.size());
			return pl.get(arrayPosition);
		case 2:
			int[] p1 = { 1, 5 };
			List<Integer> p2 = new ArrayList<Integer>();
            for(int i=0; i<2;i++){
           	 if(p1[i]!=ff){
           		 p2.add(p1[i]);
           	 }
            }
			arrayPosition = random2.nextInt(p2.size());
			return p2.get(arrayPosition);

		case 4:
			int[] p4 = { 1, 3, 5, 7 };
			List<Integer> p44  = new ArrayList<Integer>();
            for(int i=0; i<4;i++){
           	 if(p4[i]!=ff){
           		 p44.add(p4[i]);
           	 }
            }
			arrayPosition = random2.nextInt(p44.size());
			return p44.get(arrayPosition);
		case 6:
			int[] p3 = { 3, 7};
			List<Integer> p33  = new ArrayList<Integer>();
            for(int i=0; i<2;i++){
           	 if(p3[i]!=ff){
           		 p33.add(p3[i]);
           	 }
            }
			arrayPosition = random2.nextInt(p33.size());
			return p33.get(arrayPosition);

		case 8:
			int[] p8= {5,7};
			List<Integer> p88  = new ArrayList<Integer>();
            for(int i=0; i<2;i++){
           	 if(p8[i]!=ff){
           		 p88.add(p8[i]);
           	 }
            }
			arrayPosition = random2.nextInt(p88.size());
			return p88.get(arrayPosition);
		default:
			return -1;
		}
   
    }
    public int[] getRuslt() {
		int fPosition = twonumber();
		int arrayPosition;
		int fiveposition;
		Random random2 = new Random();
		switch (fPosition) {
		case 1:
			int p[] ={0,2,4};
			List<Integer> pl  = new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p[i]!=threeshuzi&&p[i]!=w){
            		 pl.add(p[i]);
            	 }
             }
              arrayPosition = random2.nextInt(pl.size());
              fiveposition= pl.get(arrayPosition);
		return  new int[]{threeshuzi,fPosition,fiveposition };
		case 3:
			int p3[] ={0,4,6};
			List<Integer> p1 = new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p3[i]!=threeshuzi&&p3[i]!=w){
            		 p1.add(p3[i]);
            	 }
             }
              arrayPosition = random2.nextInt(p1.size());
              fiveposition= p1.get(arrayPosition);
		return  new int[]{threeshuzi,fPosition,fiveposition };
		case 5:
			int p5[] ={2,4,8};
			List<Integer> p2 = new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p5[i]!=threeshuzi&&p5[i]!=w){
            		 p2.add(p5[i]);
            	 }
             }
              arrayPosition = random2.nextInt(p2.size());
              fiveposition= p2.get(arrayPosition);
		return  new int[]{threeshuzi,fPosition,fiveposition };
			
		case 7:
			int p7[] ={6,4,8};
			List<Integer> p4= new ArrayList<Integer>();
             for(int i=0; i<3;i++){
            	 if(p7[i]!=threeshuzi&&p7[i]!=w){
            		 p4.add(p7[i]);
            	 }
             }
              arrayPosition = random2.nextInt(p4.size());
              fiveposition= p4.get(arrayPosition);
		return  new int[]{threeshuzi,fPosition,fiveposition };
		default:
			break;
		}
		return null;
	} 

    public void aim1(){
    	int[]   n=getRuslt();
    	String	m=btn[w].getText().toString();
    	String	f=btn[ff].getText().toString();
		
		String	b=btn[n[0]].getText().toString();
		String	d=btn[n[1]].getText().toString();
		String	c=btn[n[2]].getText().toString();

		if(f.equals("+")){
			result1 = Integer.valueOf(m) + Integer.valueOf(b);
			result1=Math.abs(result1);
		}
		if(f.equals("-")){
        	result1=Integer.valueOf(m)-Integer.valueOf(b);
        	result1=Math.abs(result1);
		}
		
		if(d.equals("+")){
		   result2 = result1 + Integer.valueOf(c);
		   text1.setText(Math.abs(result2)+" ");
		}
		if(d.equals("-")){
	    	result2= result1-Integer.valueOf(c);
	    	text1.setText(Math.abs(result2)+" ");
		}
       Log.e("-1111111111111111--------------", w+"");
       Log.e("-2222222222222222222---------------", ff+"");
       Log.e("-3333333333333333333---------------", n[0]+"");
       Log.e("-444444444444444---------------", n[1]+"");
       Log.e("-5555555555555555555--------------",n[2]+"");
    }
    }
