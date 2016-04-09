
package realtanke;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TKDZ extends JFrame {
	int LE=1;
	static  final int H=600;
	static  final int W=800;
	Plane p23 ;//Ϊ����Plane��ָ��
	Plane p22;//ָ��Ϊ��ǰ�����Plane

	public TKDZ(){
		Plane p23 = new Plane(this);//Ϊ����Plane��ָ��
		add(p23);
		p23.setFocusable(true);
		p22=p23;
	}
	//������Ϸ
	public void again(){
		p23=new Plane(this);
		setVisible(false);
		remove(p22);
		add(p23);
		repaint();
		p23.setFocusable(true);
		setVisible(true);
		p22=p23;
	}
	public void again2(){
		LE++;
		if(LE==5){
			setVisible(false);
			
			remove(p22);
			repaint();
			ImageIcon im=new ImageIcon("C:\\Users\\zb\\Documents\\elips\\TKdazhan\\src\\realtanke\\1.jpg"); 
			JPanel jp=new JPanel();
			jp.add(new JLabel(im));
			add(jp);
			jp.setBackground(Color.orange);
			jp.setVisible(true);
			LE=1;
			setVisible(true);
			return;}
		p23=new Plane(this);
		setVisible(false);
		p23.level=LE;
		remove(p22);
		add(p23);
		repaint();
		p23.setFocusable(true);
		setVisible(true);
		p22=p23;
		
	}
	public static void main(String[] args){
		TKDZ frame = new TKDZ();
		frame.setTitle( "̹�˴�ս");
		frame.setSize(W,H);
		frame.setLocationRelativeTo( null);
		frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
		frame.setVisible( true);
		frame.setResizable(false);
	}
	//��Ѫ
//	class recover {
//		Plane p;
//		int c1,c2;
//		int quit=1;
//		int r;
//		public recover(Plane p){
//			this.p=p;
//			this.c1=4*p.r;
//			this.c2=4*p.r;
//			this.r=2*p.r/3;
//		}
//		public void draw(Graphics g){
//			g.setColor(Color.RED);
//			g.fillOval(c1, c2,r, r);
//		}
//		public void  hitit(){
//			if(Math.sqrt((c1-p.x)*(c1-p.x)+(c2-p.y)*(c2-p.y))<r+p.r)
//				{p.mylife=50;
//			this.quit=0;}
//		}
//	}
	class Plane extends JPanel {
		
		private int level=1;
		final static int S=1;
		final static int X=2;
		final static  int Z=3;
		final static  int Y=4;
		int [][] ts=new int[10][4];//���ζ���ط�̹�˵�x,y,LIVE��1,0�����Լ�direction��1��2��3��4�ң�
		ArrayList<Bullet> bs1=new ArrayList<Bullet>();//�����ҷ�̹�˵��ӵ�
		ArrayList<Bullet> bs2=new ArrayList<Bullet>();//����ط���̹�˵��ӵ�
		ArrayList<Line> ll=new ArrayList<Line>();//����ط���̹�˵��ӵ�

		Graphics g=this.getGraphics();//���plane�Ļ���
		TKDZ T;
		Timer timer= new Timer(100,new TimerListener());//�滭ʱ�������
		Timer timer2=new Timer(10,new TimerListener2());//�ı�ط�̹������ʱ�������
		int r=30;//�뾶
		private int direct=1;//�ҷ�̹�˷���
		int mylife=50;//�ҷ�̹������ֵ
		private int speed=9;//�ҷ�̹���ٶ�
		private int speed2=2;//�ط�̹���ٶ�
		private boolean alldie=true;//�з�̹���Ƿ�ȫ������
		int x0,y0,xx,yy;//�߽磻
		int x = T.W-5*r;//�ҷ�̹�˺�����
		int y = T.H-5*r;//�ҷ�̹��������
		Line l=new Line(this,15*r,r);
		public Plane(TKDZ T){
			addKeyListener( new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					keypressed1(e);
				}
				public void keyReleased(KeyEvent e){
					keyReleased1(e);
					repaint();
				}
			
			});

			this.setSize(T.W, T.H);
			setBackground(Color.yellow);
			for(int i=0;i<ts.length;i++)
				for(int j=0;j<ts[0].length;j++)
				{ts[i][0]=50+r*i;//x
				ts[i][1]=50;//y
				ts[i][2]=1;//life
				ts[i][3]=(new Random()).nextInt(4)+1;}//������ط�̹�˽��и��������ĸ�ֵ��
				ll.add(l);
				for(int i=0;i<17;i++){
					if(i!=6&&i!=7&&i!=8)
				ll.add( new Line(this,15*r,(i+1)*r));
					}
				for(int i=0;i<7;i++){
					ll.add(new Line(this,r*(i+1),7*r));
				}
				timer.start();
				timer2.start();
		}
		public void keypressed1(KeyEvent e){
			switch(e.getKeyCode()){
			case KeyEvent.VK_DOWN: mymove(1,2);break;
			case KeyEvent.VK_UP:mymove(1,1);break;
			case KeyEvent.VK_LEFT: mymove(1,3);break;
			case KeyEvent.VK_RIGHT: mymove(1,4); break;
			}
		}
		public void keyReleased1(KeyEvent e){
			switch(e.getKeyCode()){
			case KeyEvent.VK_SPACE:if(mylife>0)bs1.add(new Bullet(true, x, y, direct,this));break;
			case KeyEvent.VK_ENTER:if(mylife>0)for(int i=1;i<5;i++)bs1.add(new Bullet(true, x, y, i,this));break;
			case KeyEvent.VK_1:if(mylife==0||ifalldie()==true)again();;break;
			case KeyEvent.VK_2:again2();break;//����Ҫ���ڲ��࣬������T.again���á���
			}
		}
		public void mymove(int u,int i){
			if(u==1){
				int rr=speed+r/2;
				switch(i){
				case 1:y=(y-y0>rr)&&(hitline(x,y,1))?y-speed:y;direct=S; break;
				case 2: y=(yy-y>=rr)&&(hitline(x,y,2))?y+speed:y;direct=X;break;
				case 3: x=(x-x0>=rr)&&(hitline(x,y,3))?x-speed:x;direct=Z ;break;
				case 4: x=(xx-x>=rr)&&(hitline(x,y,4))?x+speed:x;direct=Y; break;
				}
			}
		}
		public boolean hitline(int x,int y,int dir){
			int ary[][] =new int [4][2];
				ary[1][0]=x;ary[1][1]=y-r;//Shang
				ary[2][0]=x;ary[1][1]=y+r;//xia
				ary[3][0]=x-r;ary[1][1]=y;//zuo
				ary[1][0]=x+r;ary[1][1]=y;//you
			for(int i=0;i<ll.size();i++){
				Line l=ll.get(i);
				int d1,d2,d3,d4;
				d1=Math.abs(l.w1-x); 
				d2=Math.abs(l.w1+l.a-x);
				d3=Math.abs(l.w2-y);
				d4=Math.abs(l.w2+l.a-y);
				if((d2<=r/2+speed&&l.w2-r/2<=y&&y<=l.w2+l.a+r/2&&dir==3)||(d1<=r/2+speed&&l.w2-r/2<=y&&y<=l.w2+l.a+r/2&&dir==4))
					return false;
					 else if((d3<=r/2+speed&&l.w1-r/2<=x&&x<=l.w1+l.a+r/2&&dir==2)||(d4<=r/2+speed&&l.w1-r/2<=x&&x<=l.w1+l.a+r/2&&dir==1))
				  		  return false;
						 }
			
			return true;
		}
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(l.a,l.a, l.t1,l.t2);
			for(int i=0;i<ll.size();i++)
				ll.get(i).draw1(g);
			x0=l.a;y0=l.a;xx=r+l.t1;yy=r+l.t2;
			
			if(ifalldie()==false){
				for(int i=0;i<ts.length;i++){
					if(ts[i][2]==1)//����
						{
						if(i<2*level-1){
							g.setColor(Color.green);
							g.drawString("BOSS", ts[i][0]-r/2,ts[i][1]-r/2);
							g.fillOval(ts[i][0]-r/2, ts[i][1]-r/2, r, r);
						}
						else{
						g.setColor(Color.blue);
					g.fillOval(ts[i][0]-r/2, ts[i][1]-r/2, r, r);
						}
				}
				}
			}
			
			Bullet bb,bc;
			Iterator it1=bs1.iterator();
			while(it1.hasNext()){
				bb=(Bullet)(it1.next());
				if(bb.go==false)
					it1.remove();
				else
				bb.draw(g);
			}
			Iterator it2=bs2.iterator();
			while(it2.hasNext()){
				bc=(Bullet)it2.next();
				if(bc.go==false)
					it2.remove();
				else
				bc.draw(g);
			}
			if(mylife>0)
			{ g.setColor(Color.red);
			g.fillOval(x-r/2, y-r/2, r, r);}
			if(mylife!=0)
			{g.setColor(Color.GREEN);
			g.drawString(String.valueOf(mylife/10), x, y);}
			if(mylife==0||ifalldie()==true){
				g.drawString("again ��   ��������>press key 1!",getWidth()/2,getHeight()/2);
				if(ifalldie()==true&&level<4)
			    g.drawString("next chapter ��   ��������>press key 2!",getWidth()/2,getHeight()/2+r);
				if(ifalldie()==true&&level==4){
					g.drawString("Congratulations!you win!",getWidth()/2,getHeight()/2+2*r);again2();
				}
			}
				
			g.drawString("my bullets number:  "+bs1.size(),50,50);
			repaint();

		}    
		//�Ƿ�ȫ���з�̹��������
		public boolean ifalldie(){
			for(int i=0;i<ts.length;i++)
				if( ts[i
				       ][2]!=0) return
						false;
			return true;
		}
		//���Ƶз����ӵ��͵з��ķ���ı��ʱ���¼�����
		class TimerListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				armfile();//�з�����
				//removebullet();
				Random R=new Random();
				//�����ƶ��ķ���ı��Ƶ�ʣ�����Ӱ��һ�����߼�����
				for(int i=0;i<ts.length;i++){
					if(R.nextInt(10)>7)
						ts[i][3]=R.nextInt(4)+1;}

				repaint();

			}
		}
		//�з�̹������ĸı�
		class TimerListener2 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������

				Random R=new Random();
				int rr=speed2+r/2;

				for(int i=0;i<ts.length;i++)
				{ 
					if(R.nextInt(30)>R.nextInt(3)){//�ٴο����Ƿ���λ�øı�
						switch(ts[i][3]){
						case 1:ts[i][1]=(ts[i][1]-y0>=rr)&&(hitline(ts[i][0],ts[i][1],1))?ts[i][1]-speed2:ts[i][1];;break;
						case 2:ts[i][1]=(yy-ts[i][1]>=rr)&&(hitline(ts[i][0],ts[i][1],2))?ts[i][1]+speed2:ts[i][1];break;
						case 3:ts[i][0]=(ts[i][0]-x0>=rr)&&(hitline(ts[i][0],ts[i][1],3))?ts[i][0]-speed2:ts[i][0];break;
						case 4:ts[i][0]=(xx-ts[i][0]>=rr)&&(hitline(ts[i][0],ts[i][1],4))?ts[i][0]+speed2:ts[i][0];break;
						}
					}
				}}
		}
		public void removebullet(){
			Bullet bb,bc;
			Iterator it1=bs1.iterator();
			while(it1.hasNext()){
				bb=(Bullet)(it1.next());
				if(bb.go==false)
					bs1.remove(bb);
			}
			Iterator it2=bs2.iterator();
			while(it2.hasNext()){
				bc=(Bullet)it2.next();
				if(bc.go==false)
					bs1.remove(bc);
			}
		}
		//�ط�����
		public  void armfile(){
			Random R=new Random();
			if(R.nextInt(10)>=R.nextInt(6))
				return;
			for(int i=0;i<ts.length;i++){
				
				if((R.nextInt(3)-1)>0&&ts[i][2]==1)//�����Ƿ񿪻𣨵ط�̹�����������ϲ�ȷ����
				{
				 if(i<2*level-1){
					bs2.add(new Bullet(false,ts[i][0],ts[i][1],1,this));
					bs2.add(new Bullet(false,ts[i][0],ts[i][1],2,this));
					bs2.add(new Bullet(false,ts[i][0],ts[i][1],3,this));
					bs2.add(new Bullet(false,ts[i][0],ts[i][1],4,this));
				}
				else
				bs2.add( new Bullet(false, ts[i][0], ts[i][1], ts[i][3],this));
				}
			}
		}
	}

}
