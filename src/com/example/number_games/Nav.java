package com.example.number_games;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.number_games.Myadapter;
import com.example.number_games.R;
import com.example.number_games.Welcome;

public class Nav extends Activity implements OnPageChangeListener,OnClickListener{
	ViewPager viewpager;
	List<View> list;
	List<TextView> tv;
	int tv_id[]={R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4};
	View view1;
    LayoutInflater inf;
    Myadapter my;
    Button btn1;
    TextView  menu_1,menu_2,menu_3;
    TextView tvs[];
    LinearLayout lin;
    Resources r;
    Drawable bg1,bg2;                          
    int ids[]={R.drawable.nav_5,R.drawable.nav_1,R.drawable.nav_2,R.drawable.nav_3}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.nav);
		this.setTitle("������Ϸ");
		inti();
		changecolor(0);
	}
	
	private void inti() {
		r=this.getResources();//��ȡ��Դ�����
		bg1=r.getDrawable(R.drawable.diandian1);//����ͼƬ
		bg2=r.getDrawable(R.drawable.diandian);
		viewpager = (ViewPager) findViewById(R.id.viewpage);//���÷�ҳ
		tvs=new TextView[tv_id.length];
		list=new ArrayList<View>();
		tv=new ArrayList<TextView>();
		for(int i=0;i<tv_id.length;i++){
			tvs[i]=(TextView) findViewById(tv_id[i]);
			tv.add(tvs[i]);
			tvs[i].setOnClickListener(this);
		}
	     
		inf=this.getLayoutInflater();//����Ͳ
		my=new Myadapter(list);
		
		 for(int i=0;i<4;i++){
			 view1=inf.inflate(R.layout.tab1, null);
			 
			 //���ò˵���ť
			 menu_1=(TextView)view1.findViewById(R.id.menu_1);
			 menu_2=(TextView)view1. findViewById(R.id.menu_2);
			 menu_3=(TextView)view1. findViewById(R.id.menu_3);
			 menu_1.setOnClickListener(new click());
			 menu_2.setOnClickListener(new click());
			 menu_3.setOnClickListener(new click());
			 if(i==0){
				 menu_1.setVisibility(View.VISIBLE);
				 menu_2.setVisibility(View.VISIBLE);
				 menu_3.setVisibility(View.VISIBLE);
			 }else{
				 menu_1.setVisibility(View.GONE);
				 menu_2.setVisibility(View.GONE);
				 menu_3.setVisibility(View.GONE);
			 }
			 
	    	 if(i==3){
	    		 btn1=(Button)view1.findViewById(R.id.btn1);
	    		 btn1.setVisibility(View.VISIBLE);
	    		 btn1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
					Intent intent=new Intent();
					intent.setClass(Nav.this, Welcome.class);
					startActivity(intent);
					}
				});
	    	 }
	    	 list.add(view1);
	    	 view1.setBackgroundResource(ids[i]);
	     }
		 viewpager.setAdapter(my);
		 viewpager.setOnPageChangeListener(this);
	}
	private void changecolor(int position) {
		for(int i=0;i<tv.size();i++){
			if(position==i){
			tv.get(i).setBackground(bg2);
			}
		else{
			tv.get(i).setBackground(bg1);}
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int arg0) {
		
		changecolor(arg0);
	}
	@Override
	public void onClick(View arg0) {
		for(int i=0;i<ids.length;i++){
		 if(arg0==tvs[i]){
			 viewpager.setCurrentItem(i);
			 changecolor(i);
		 }
         
	 
		}
	   }

	 class click implements OnClickListener{
		 android.app.AlertDialog.Builder build=new AlertDialog.Builder(Nav.this);
		@Override
		public void onClick(View arg0) {
			if (arg0.getId()==R.id.menu_1) {
				build.setTitle("��Ϸ����");
				lin=(LinearLayout)getLayoutInflater().inflate(R.layout.game_guize,null);
				build.setView(lin);
				build.setPositiveButton("ȷ��",new android.content.DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int which) {	
							    dialog.dismiss();	
							}		    	
					    });
				build.show();
			}

			if (arg0.getId()==R.id.menu_2) {
				build.setTitle("��Ϸ�з�");
				lin=(LinearLayout)getLayoutInflater().inflate(R.layout.game_info,null);
				build.setView(lin);
				build.setPositiveButton("ȷ��",new android.content.DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int which) {
							    dialog.dismiss();	
							}		    	
					    });
				build.show();
			}
			
			if (arg0.getId()==R.id.menu_3) {
				build.setTitle("�˳���Ϸ");
				build.setMessage("���Ҫ�����뿪�ˣ�");
				build.setPositiveButton("ȷ��",new android.content.DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int which) {
							   Nav.this.finish();
							}		    	
					    });
				build.setNegativeButton("ȡ��",new android.content.DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {						
						dialog.dismiss();
					}			    	
			    });
				build.show();
			}
		}
	}
	
}
