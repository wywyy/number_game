package com.example.number_games;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Time extends Activity{
	RelativeLayout layout,r_tu;
	Button btn_shuzi[];
	int s = 0,count=0;
	String num1, num2;
	public  Integer res,res1,mres,nmes;
	public Integer mubiao;
	Result_number  result;
	Result_number1  result1;
	TextView tv_result1,tv_suanshi,score,finish,tv_xianshi;
	Intent intent;
	int ids[] = new int[] { R.id.button1, R.id.button2, R.id.button3,
			R.id.button4, R.id.btn_5, R.id.button6, R.id.button7, R.id.button8,
			R.id.button9 };
	ArrayList<String> str = new ArrayList<String>();
	ArrayList<Button> btn_1 = new ArrayList<Button>();
	int width, height;
    LinearLayout lin;
	ArrayList<Point[]> points = new ArrayList<Point[]>();
	 private int recLen =60; 
  @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.two);
	init();
	this.setTitle("数字游戏");
	initiposition();
	setOnlistenr();
	  Message message = handler.obtainMessage(1);   // Message obtainmessage（）是从消息池中拿来一个msg 不需要另开辟空间new
      handler.sendMessageDelayed(message, 1000); 
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
						count++;
						 TranslateAnimation translate1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
     					 translate1.setDuration(500);
						tv_result1.startAnimation(translate1);
						getaim();
						
					 if(count==10){
						 Toast.makeText(Time.this,"距离超神还有5道", Toast.LENGTH_SHORT).show();
					 }
						tv_suanshi.setText("");	
			}else{
				tv_suanshi.setTextSize(14f);
				if(tv_suanshi.getText().toString().trim().length()<3){	
					tv_suanshi.setText("滑动长度不够");
				}
				else{
					tv_suanshi.setText("输入的算式不正确,要仔细思考哦");
				}
			 }


			}
			

			/*
			 * 获取每个button的位置
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
									if(tv_suanshi.getText().toString().trim().length()<9){
									tv_suanshi.append(str.get(s));
									}
									s++;
									// btn_1.get(i).setTag(0);//这里不能设置btn_1.get(i)，否则要崩
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
				 List<Integer> list=new ArrayList<Integer>();//符号对应的位置
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
	                }
                if(tv_suanshi.getText().toString().trim().length()==5){
                	String text_num=tv_suanshi.getText().toString().trim();
                	for(int i=0;i<text_num.length();i++){
                		if(isSign(text_num.charAt(i))){
                			list.add(i);
                		}
                	}
                	if(list.size()==2){
                	String num1=text_num.substring(0,list.get(0));
                	String f1=text_num.substring(list.get(0),list.get(0)+1);
                	String num2=text_num.substring(list.get(0)+1,list.get(1));
                	String f2=text_num.substring(list.get(1),list.get(1)+1);
                	String num3=text_num.substring(list.get(1)+1,text_num.trim().length());
                	
                	Log.e(list.size()+"res1res1res1res1res1", num1+"fffff"+f1+"fffff"+num2+"fffff"+f2+"fffff"+num3);
                	if(!num2.equals("+")&&!num2.equals("-")&&!num2.equals("")&&!num3.equals("")&&!num3.equals("+")&&!num3.equals("-")){
                	if(f1.equals("+")){
                		res1= Integer.valueOf(num1) + Integer.valueOf(num2);
                	}
                	else if(f1.equals("-")){
                		res1= Integer.valueOf(num1) - Integer.valueOf(num2);
                	}
                	
                	if(f2.equals("+")){
                		res = Integer.valueOf(res1) + Integer.valueOf(num3);
                	}
                	else if(f2.equals("-")){
                		res = Integer.valueOf(res1) - Integer.valueOf(num3);
                	}
                	}else{
						 tv_suanshi.setText("");
					}
                	list.clear();
                }}
                Log.e("tv_suanshi.getText().toString().trim().length()", ""+tv_suanshi.getText().toString().trim().length());
                if(tv_suanshi.getText().toString().trim().length()==7){
                	String text_num=tv_suanshi.getText().toString().trim();
                	for(int i=0;i<text_num.length();i++){
                		if(isSign(text_num.charAt(i))){
                			list.add(i);
                		}
                	}
                	if(list.size()==3){
                	String num1=text_num.substring(0,list.get(0));
                	String f1=text_num.substring(list.get(0),list.get(0)+1);
                	String num2=text_num.substring(list.get(0)+1,list.get(1));
                	String f2=text_num.substring(list.get(1),list.get(1)+1);
                	String num3=text_num.substring(list.get(1)+1,list.get(2));
                	String f3=text_num.substring(list.get(2),list.get(2)+1);
                	String num4=text_num.substring(list.get(2)+1,text_num.trim().length());
                	
                	Log.e(list.size()+"res1res1res1res1res1", num1+"fffff"+f1+"fffff"+num2+"fffff"+f2+"fffff"+num3+"fffff"+f3+"fffff"+num4);
                	if(!num2.equals("+")&&!num2.equals("-")&&!num2.equals("")&&!num3.equals("")&&!num3.equals("+")&&!num3.equals("-")&&!num4.equals("")&&!num4.equals("+")&&!num4.equals("-")){
                	if(f1.equals("+")){
                		res1= Integer.valueOf(num1) + Integer.valueOf(num2);
                	}
                	else if(f1.equals("-")){
                		res1= Integer.valueOf(num1) - Integer.valueOf(num2);
                	}
                	
                	if(f2.equals("+")){
                		mres = Integer.valueOf(res1) + Integer.valueOf(num3);
                	}
                	else if(f2.equals("-")){
                		mres = Integer.valueOf(res1) - Integer.valueOf(num3);
                	}
                	if(f3.equals("+")){
                		res = Integer.valueOf(mres) + Integer.valueOf(num4);
                	}
                	else if(f3.equals("-")){
                		res = Integer.valueOf(mres) - Integer.valueOf(num4);
                	}
                	Log.e("resresresresresresres", res+"");
                	
                	
                	}else{
						 tv_suanshi.setText("");
					}
                	list.clear();}}
                
                if(tv_suanshi.getText().toString().trim().length()==9){
                	String text_num=tv_suanshi.getText().toString().trim();
                	for(int i=0;i<text_num.length();i++){
                		if(isSign(text_num.charAt(i))){
                			list.add(i);
                		}
                	}
                	if(list.size()==4){
                	String num1=text_num.substring(0,list.get(0));
                	String f1=text_num.substring(list.get(0),list.get(0)+1);
                	String num2=text_num.substring(list.get(0)+1,list.get(1));
                	String f2=text_num.substring(list.get(1),list.get(1)+1);
                	String num3=text_num.substring(list.get(1)+1,list.get(2));
                	String f3=text_num.substring(list.get(2),list.get(2)+1);
                	String num4=text_num.substring(list.get(2)+1,list.get(3));
                	String f4=text_num.substring(list.get(3),list.get(3)+1);
                	String num5=text_num.substring(list.get(3)+1,text_num.trim().length());
                	
                	Log.e(list.size()+"res1res1res1res1res1", num1+"fffff"+f1+"fffff"+num2+"fffff"+f2+"fffff"+num3+"fffff"+f3+"fffff"+num4+"fffff"+f4+"fffff"+num5);
                	if(!num2.equals("+")&&!num2.equals("-")&&!num2.equals("")
                		&&!num3.equals("")&&!num3.equals("+")&&!num3.equals("-")
                		&&!num4.equals("")&&!num4.equals("+")&&!num4.equals("-")
                		&&!num5.equals("")&&!num5.equals("+")&&!num5.equals("-")){
                	if(f1.equals("+")){
                		res1= Integer.valueOf(num1) + Integer.valueOf(num2);
                	}
                	else if(f1.equals("-")){
                		res1= Integer.valueOf(num1) - Integer.valueOf(num2);
                	}
                	
                	if(f2.equals("+")){
                		mres = Integer.valueOf(res1) + Integer.valueOf(num3);
                	}
                	else if(f2.equals("-")){
                		mres = Integer.valueOf(res1) - Integer.valueOf(num3);
                	}
                	if(f3.equals("+")){
                		nmes = Integer.valueOf(mres) + Integer.valueOf(num4);
                	}
                	else if(f3.equals("-")){
                		nmes = Integer.valueOf(mres) - Integer.valueOf(num4);
                	}
                	if(f4.equals("+")){
                		res = Integer.valueOf(nmes) + Integer.valueOf(num5);
                	}
                	else if(f4.equals("-")){
                		res = Integer.valueOf(nmes) - Integer.valueOf(num5);
                	}
                	Log.e("resresresresresresres", res+"");
                	
                	
                	}else{
						 tv_suanshi.setText("");
					}
                	list.clear();}}
			}
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
	 * 获取布局的宽和高
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
		tv_xianshi= (TextView) findViewById(R.id.tv_xianshi1);
		
		Resources resources =this.getResources();
		Drawable btnDrawable = resources.getDrawable(R.drawable.t0);  
		r_tu = (RelativeLayout) findViewById(R.id.r_relative);
		r_tu.setBackground(btnDrawable); 
		
		tv_xianshi.setText("随机运算模式");
		finish=(TextView) findViewById(R.id.finish);
		finish.setText(60+"");
		
		btn_shuzi = new Button[ids.length];
		score = (TextView) findViewById(R.id.tv_score);
       
		for (int i = 0; i < ids.length; i++) {
			btn_shuzi[i] = (Button) findViewById(ids[i]);
			btn_shuzi[i].setTag(1);
		}
		getrandom();
		getaim();
		
	}
	private void getaim() {
		Random ran=new Random();
		int rj=ran.nextInt(2);
		if(rj==0){
			result=new Result_number(tv_result1,btn_shuzi);
			result.aim();
		}
		else if(rj==1){
			result1=new Result_number1(tv_result1,btn_shuzi);
			result1.aim1();
		}
		
	}

	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			  switch (msg.what) { 
	          case 1: 
	            recLen--; 
	            finish.setText("" + recLen); 
	            if(recLen > 0){ 
	              Message message = handler.obtainMessage(1); 
	              handler.sendMessageDelayed(message, 1000);   // send message 
	            }
	            if(recLen==0){
	              finish.setText("" + 0); 
	             final Builder bulider=new AlertDialog.Builder(Time.this);
	              if(count<15){
					  bulider.setTitle("提示对话框");
					  bulider.setMessage(count+"道题,还要继续努力哦");
					  bulider.setCancelable(false);
					  bulider.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							  arg0.dismiss();
							  Time.this.finish();
				              intent=new Intent();
							  intent.setClass(Time.this,Level.class);
							  startActivity(intent);
						}
					});
	              }else{
	            	bulider.setCancelable(false);
	                bulider.setTitle("你已经超神了");
	  				lin=(LinearLayout)getLayoutInflater().inflate(R.layout.success,null);
	  				bulider.setView(lin);
	  				bulider.setPositiveButton("确定",new android.content.DialogInterface.OnClickListener(){
	  							public void onClick(DialogInterface dialog, int which) {
	  							  dialog.dismiss();
	  							  Time.this.finish();
	  				              intent=new Intent();
	  							  intent.setClass(Time.this,Level.class);
	  							  startActivity(intent);
	  							
	  							}		    	
	  					    });	 
	              }
	           
	             
//	              if(!Time.this.isFinishing()){
	            	  bulider.show();
//	            	}
	              
	            }
	          } 
	        } 
	      }; 
}
	

