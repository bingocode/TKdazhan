package realtanke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import realtanke.TKDZ.Plane;

class Bullet {
	 int ra=10;//�ӵ��뾶
   boolean go=true;//�ӵ��Ƿ��ܹ������ƶ�
	private boolean good;//�ӵ����Ҵ�Ļ��ǵ��˴��
	private int speed=3;//�ӵ��ٶ�
	 int a,b;//�ӵ�����
	private int a1,a2;
	private int dir=(new Random()).nextInt(4)+1;//�ӵ�����shang 1,xia 2,zuo 3,you 4
	Plane p;
	public Bullet(boolean good,  int a, int b, int dir,Plane p) {
		this.good = good;
		this.dir = dir;
			this.a=a;
			this.b=b;
		this.p=p;
		Timer timer= new Timer(10,new TimerListener1());
       timer.start();
	}
	//�ӵ��Ƿ� ��Ŀ���ʱ�����
	class TimerListener1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			if(ifgo()==false)
				 return;
			{move(dir);
			hit1(a,b,good);
			}
		}
     }
	//�ӵ��ƶ�����ı�
	public void  move(int dir){
		
		switch(dir){
		case 1:b-=speed;break;
		case 2:b+=speed;break;
		case 3:a-=speed;break;
		case 4:a+=speed;break;
		}
			}
	//���ӵ�
	 public void draw(Graphics g){
		g.setColor(Color.green);
		if(ifgo())
		g.fillOval(a-ra/2, b-ra/2, ra, ra);
	}
	 //�Ե��Ƿ��ܹ��ƶ�
	 public boolean ifgo(){
		 if(a>p.x0&&a<p.xx&&b>p.y0&&b<p.yy&&go==true)
			 return true;
		 else go=false;
		 return false;
		
		 }
			
	
	 //�ӵ�����Ŀ���
	 public void hit1(int x,int y,boolean good){
		 //�Ƿ�ײǽ
		 for(int i=0;i<p.ll.size();i++){
		Line l=p.ll.get(i);
  	  int d1,d2,d3,d4;
  	  d1=Math.abs(l.w1-a); 
  	  d2=Math.abs(l.w1+l.a-a);
  	  d3=Math.abs(l.w2-b);
  	  d4=Math.abs(l.w2+l.a-b);
  	  if((d2<=ra/2-2&&l.w2<=b&&b<=l.w2+l.a)||(d1<=ra/2-2&&l.w2<=b&&b<=l.w2+l.a))
  		  go=false;
  	  else if((d3<=ra/2-2&&l.w1<=a&&a<=l.w1+l.a)||(d4<=ra/2-2&&l.w1<=a&&a<=l.w1+l.a))
  		  go=false;
		 }
  	 if(good){
  	  for(int i=0;i<p.ts.length;i++){
  		  if(p.ts[i][2]==1){ 
  		  int x2=p.ts[i][0],y2=p.ts[i][1];
  	      if(Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2))<p.r/2)
  		  {p.ts[i][2]=0;go=false;}
  	  }}}
  	  else 
  	  {   
  		  if(p.mylife==0) return;
  		  int x2=p.x,y2=p.y;
  		if(Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2))<p.r/2)
		  {p.mylife-=10;go=false;}
  	  }
    }
}
