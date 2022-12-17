package graphics;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.*;

public class BresenhamLineDrawing extends Canvas{
	static int x0,y0,x1,y1;
	double m;
	BresenhamLineDrawing(int x0, int y0, int x1, int y1,double m){
		this.x0=x0;
		this.y0=y0;
		this.x1=x1;
		this.y1=y1;
		this.m=m;
	}
	public void paint(Graphics g) {
		int dx,dy,pk,count=x0;
		dx=x1-x0;
		dy=y1-y0;
		pk=2*dy-dx;
		if(m<1)
			g.fillOval(x0, y0, 1, 1);
		else
			g.fillOval(y0, x0, 1, 1);
		 do{
			x0++;
			if(pk<0) {
				if(m<1)
					g.fillOval(x0, y0, 1, 1);
				else
					g.fillOval(y0, x0, 1, 1);
				System.out.println(pk+" "+x0+" "+y0);
				pk=pk+2*dy;
			}
			else {
				y0++;
				if(m<1)
					g.fillOval(x0, y0, 1, 1);
				else
					g.fillOval(y0, x0, 1, 1);
				System.out.println(pk+" "+x0+" "+y0);
				pk=pk+2*dy-2*dx;
			}
			count++;
		} while(count<x1);
		
	}
	public static void main(String args[]) {
		int temp;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first end x0 and y0");
		int x0=sc.nextInt();
		int y0=sc.nextInt();
		System.out.println("Enter last end x1 and y1");
		int x1=sc.nextInt();
		int y1=sc.nextInt();
		double dy1=y1;
		double m=(dy1-y0)/(x1-x0);
		if(m>1) {
			temp=x0;
			x0=y0;
			y0=temp;
			temp=x1;
			x1=y1;
			y1=temp;
		}
		System.out.println(m+" "+x0+" "+y0+" "+x1+" "+y1+"\n");
		BresenhamLineDrawing d=new BresenhamLineDrawing(x0,y0,x1,y1,m);
		JFrame f=new JFrame();
		f.add(d);
		f.setSize(400,400);
		f.setVisible(true);
	}

}