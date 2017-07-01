package com.example.number_games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Two extends Activity{
	private Toast toast;
	RelativeLayout layout;
	Button btn_shuzi[];
	int s = 0,count=0;
	String num1, num2;
	public  Integer res,res1;
	public Integer mubiao;
	Result_number  result;
	TextView tv_result1,tv_suanshi,score,finish;
	Intent intent;
	int ids[] = new int[] { R.id.button1, R.id.button2, R.id.button3,
			R.id.button4, R.id.btn_5, R.id.button6, R.id.button7, R.id.button8,
			R.id.button9 };
	ArrayList<String> str = new ArrayList<String>();
	ArrayList<Button> btn_1 = new ArrayList<Button>();
	int width, height;
	ArrayList<Point[]> points = new ArrayList<Point[]>();
  @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.two);
	init();
	this.setTitle("������Ϸ");
	initiposition();
	setOnlistenr();
}
  private void setOnlistenr() {
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					tv_suanshi.setText("");
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) arg1.getX();
					int y = (int) arg1.getY();
					getposition(x, y);
					break;

				case MotionEvent.ACTION_UP:
					for (int i = 0; i < btn_shuzi.length;) {
						btn_shuzi[i].setBackgroundColor(Color.parseColor("#ADDBF7"));
						btn_shuzi[i].setTag(1);
						i=i+2;
					}
					for (int i = 1; i < btn_shuzi.length;) {
						btn_shuzi[i].setBackgroundColor(Color.parseColor("#E7D3D6"));
						btn_shuzi[i].setTag(1);
						i=i+2;
					}
					take_out();							
					getScore();		
					btn_1.clear();
					str.clear();
					s = 0;					
//					tv_suanshi.setText("");
					break;
				default:
					break;
				}
				return true;
			}		

			public void getScore() {
					if (tv_result1.getText().toString().trim().equals(res+"")) {
						score.setText(Integer.valueOf(score.getText().toString()) + 10 + "");
						getrandom();
						 TranslateAnimation translate1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
     					 translate1.setDuration(500);
						tv_result1.startAnimation(translate1);
						count++;
						result = new Result_number(tv_result1, btn_shuzi);
						result.aim();
					 if(count==10){
					 Toast.makeText(Two.this,"���볬����10��", Toast.LENGTH_SHORT).show();
					 }
					 if(count==20){
							alter() ;
						}
					 tv_suanshi.setText("");
			}else{
				tv_suanshi.setTextSize(14f);
				if(tv_suanshi.getText().toString().trim().length()<3){	
					tv_suanshi.setText("�������Ȳ���");
				}else if(tv_suanshi.getText().toString().trim().length()>3){
					tv_suanshi.setText("��������̫��");
				}else{
					tv_suanshi.setText("�������ʽ����ȷ,Ҫ��ϸ˼��Ŷ");
				}
			}
			}
			
	/*
	 * ������
	 */
			private void alter() {
				 android.app.AlertDialog.Builder build=new AlertDialog.Builder(Two.this);
				 build.setCancelable(false);
				 build.setTitle("��ʾ�Ի���");
				if(count==20){
				build.setMessage("���Ѿ�������,��Ҫ����������");
				   build.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							arg0.dismiss();
						}
						
					});

					build.setNegativeButton("ȡ��",new android.content.DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {						
							Two.this.finish();	
							intent=new Intent();
							intent.setClass(Two.this, Level.class);
							startActivity(intent);
						}			    	
				    });
				}
				build.show();
				
				
			}

			/*
			 * ��ȡÿ��button��λ��
			 */
			private void getposition(int x, int y) {
				tv_suanshi.setTextSize(20f);
				if (x > 0 && y > 0) {
					getpoint();
					for (int i = 0; i < btn_shuzi.length; i++) {
						Point[] point_d = points.get(i);
						if (x > point_d[0].x && y > point_d[0].y
								&& x < point_d[1].x && y < point_d[1].y) {
							if (tv_suanshi.getText().toString().trim().equals("")&&
							   (btn_shuzi[i].getText().toString().equals("+") || 
						    	btn_shuzi[i].getText().toString().equals("-"))) {
                              tv_suanshi.setText("");
							} else {
								if (btn_shuzi[i].getTag().equals(1)) {
									btn_shuzi[i].setBackgroundColor(Color.parseColor("#A4C639"));
									btn_1.add(btn_shuzi[i]);
									String test_1 = btn_shuzi[i].getText()
											.toString();
									str.add(test_1);
									if(tv_suanshi.getText().toString().trim().length()<5){
									tv_suanshi.append(str.get(s));
									}
									s++;
									// btn_1.get(i).setTag(0);//���ﲻ������btn_1.get(i)������Ҫ��
									btn_shuzi[i].setTag(0);
								}
								if (btn_shuzi[i].getTag().equals(0)) {
									if (btn_1.size() >= 2) {
										if (btn_1.get(btn_1.size() - 2) == btn_shuzi[i]) {
											if (tv_suanshi.length() > 1) {
												btn_1.get(btn_1.size() - 1).setTag(1);
												if((btn_1.size() - 1)%2==0){
												btn_1.get(btn_1.size() - 1).setBackgroundColor(Color.parseColor("#ADDBF7"));
												}else{
												btn_1.get(btn_1.size() - 1).setBackgroundColor(Color.parseColor("#E7D3D6"));	
												}
												tv_suanshi.setText(tv_suanshi.getText().toString().substring(0,tv_suanshi.getText().length() - 1));
												btn_1.remove(btn_1.size() - 1);
												s = s - 1;
												str.remove(str.size() - 1);
											}
										}
									}
								}

							}
						}
					}

				}
				
			}

			private void take_out() {
				 List<Integer> list=new ArrayList<Integer>();//���Ŷ�Ӧ��λ��
	                if(tv_suanshi.getText().toString().trim().length()==3){

	                	String text_num=tv_suanshi.getText().toString().trim();
	                	for(int i=0;i<text_num.length();i++){
	                		if(isSign(text_num.charAt(i))){
	                			list.add(i);
	                		}
	                	}
	                	if(list.size()!=0){
	                	String num1=text_num.substring(0,list.get(0));
	                	String f1=text_num.substring(list.get(0),list.get(0)+1);
	                	String num2=text_num.substring(list.get(0)+1,text_num.trim().length());
	                	
	                	Log.e(list.size()+"res1res1res1res1res1", num1+"fffff"+f1);
	                	if(!num2.equals("+")&&!num2.equals("-")&&!num2.equals("")){
	                	if(f1.equals("+")){
	                		res = Integer.valueOf(num1) + Integer.valueOf(num2);
	                	}
	                	else if(f1.equals("-")){
	                		res = Integer.valueOf(num1) - Integer.valueOf(num2);
	                	}
	                	
	                	}else{
							 tv_suanshi.setText("");
						}
	                
	                	}
	                }			}
			private boolean isSign(char num){
				switch (num) {
				case '+':
					return true;
				case '-':
					return true;
				default:
					break;
				}
				return false;
			}
			private void getpoint() {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						Point point = new Point(j * width / 3, i * height / 3);
						Point point1 = new Point((j + 1) * width / 3, (i + 1)
								* height / 3);
						Point[] pointa = new Point[] { point, point1 };
						points.add(pointa);
					}
				}
			}
		});

	}

	public  void getrandom() {

		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();

		while (list.size() < 5) {
			Integer temp = random.nextInt(9)+1;
			if (!list.contains(temp)) {
				list.add(temp);
			}
		}
		for (int i = 0, j = 0; i < list.size(); i++) {
			btn_shuzi[j].setText(list.get(i).toString());
			j += 2;
		}

	}
	/*
	 * ��ȡ���ֵĿ�͸�
	 */
	private void initiposition() {
		layout.post(new Runnable() {

			@Override
			public void run() {
				width = layout.getWidth();
				height = layout.getHeight();
			}
		});
	}

	private void init() {
		layout = (RelativeLayout) findViewById(R.id.layout_a);
		tv_result1 = (TextView) findViewById(R.id.tv_result1);
		tv_suanshi = (TextView) findViewById(R.id.tv_suanshi);
		finish=(TextView) findViewById(R.id.finish);
		finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

			  android.app.AlertDialog.Builder build=new AlertDialog.Builder(Two.this);
				build.setMessage("���ĵ÷���:"+score.getText().toString().trim()+"\n��ȷ��Ҫ�˳�����");
				   build.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {

							Two.this.finish();
							intent=new Intent();
							intent.setClass(Two.this, Level.class);
							startActivity(intent);
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
		});
		btn_shuzi = new Button[ids.length];
		score = (TextView) findViewById(R.id.tv_score);
       
		for (int i = 0; i < ids.length; i++) {
			btn_shuzi[i] = (Button) findViewById(ids[i]);
			btn_shuzi[i].setTag(1);
		}
		getrandom();
		result=new Result_number(tv_result1,btn_shuzi);
		result.aim();
		
	}

}
	

