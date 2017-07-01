package com.example.number_games;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

import android.util.Log;


public class MyUtil {
	
//	public static String path = "E:/eclipseAndroid/number_games/src/com/example/number_games/a.properties";
	public static String path;// = "/a.properties";
	
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
////		读取
//		int[] rs = MyUtil.f();
//		for(int t : rs){
//			System.out.println(t);
//		}
//		
//		
////		添加分数
//		MyUtil.f2(30);
//	}
	
	/**
	 * 
	 * @return 分数 数组  从大到小
	 */
	public static int[] f(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			Properties pro = new Properties();
			FileInputStream in = new FileInputStream(path);
			pro.load(in);
			Iterator<String> it=pro.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                int te = Integer.parseInt(pro.getProperty(key));
                if(te!=0){
                	list.add(te);
                }
           }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list);
		int[] rs = new int[list.size()];
		for(int i = 0;i<rs.length;i++){
			rs[i] = list.get(rs.length-i-1);
		}
		return rs;
	}
	
	public static void f2(int score){
		Log.e("xcxcxcxcxec", score+"");
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			Properties pro = new Properties();
			FileInputStream in = new FileInputStream(path);
			pro.load(in);
			Iterator<String> it=pro.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                list.add(Integer.parseInt(pro.getProperty(key)));
           }
            in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(score);
		int[] rss = new int[50];
		for(int i = 0;i<list.size();i++){
			rss[i] = list.get(i);
		}
		for(int i = 0;i<rss.length;i++){
			for(int j = i;j<rss.length;j++){
				if(rss[i]<rss[j]){
					int temp = rss[i];
					rss[i] = rss[j];
					rss[j] = temp;
				}
			}
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
			for(int i = 0;i<10;i++){
				bw.write((i+1)+"="+ rss[i]+"\n");
			}
			bw.flush();
			bw.close();
//			Log.e("111111111", "222222222222");
		} catch (Exception e) {
			Log.e("eeeeeee", e.toString());
//			e.printStackTrace();
		}
//		Log.e("aaaaaaaaa", "-------");
	}

}
