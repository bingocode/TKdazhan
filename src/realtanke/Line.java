package realtanke;

import java.awt.Color;
import java.awt.Graphics;

import realtanke.TKDZ.Plane;

public class Line {
	Bullet bu;
	 Plane p=null;
	  int a=30;
	 int t1=0,t2=0;
	 int w1,w2;
	 int s=0;
	 public Line(Plane p,int w1,int w2){
		 this.p=p;
		 this.w1=w1;
		 this.w2=w2;
		 this.s=s;

	}
	public void draw1(Graphics g){
		 t1=p.getWidth()-2*a;
		 t2= p.getHeight()-2*a;
		 g.setColor(Color.yellow);
		 g.fill3DRect(w1, w2, a, a, false);
	 }
}
