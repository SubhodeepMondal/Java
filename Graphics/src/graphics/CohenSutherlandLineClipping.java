package graphics;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.*;
import java.awt.geom.Line2D;

public class CohenSutherlandLineClipping extends Canvas{
	static int x0,y0,x1,y1,Xmax,Ymin,Xmin,Ymax;
	double m;
	CohenSutherlandLineClipping(int Xmin,int Ymin,int Xmax,int Ymax,int x0, int y0, int x1, int y1,double m){
		this.x0=x0;
		this.y0=y0;
		this.x1=x1;
		this.y1=y1;
		this.m=m;
		this.Xmin=Xmin;
		this.Ymin=Ymin;
		this.Xmax=Xmax;
		this.Ymax=Ymax;
	}
	public void paint(Graphics g) {
		// Using Bresenham Line drawing algorithm to draw the line.
		int dx,dy,pk,count=x0,temp;
		if(m>1) {
			temp=x1;
			x1=y1;
			y1=temp;
		}
		dx=x1-x0;
		dy=y1-y0;
		pk=2*dy-dx;
		
		do {
			if(m<1) {
				if(x0<Xmax && x0>Xmin) { //Drawing clipped line....
					if(y0<Ymax && y0>Ymin){
						g.setColor(Color.RED);
						g.fillOval(x0, y0, 1, 1);
						System.out.println(pk+" "+x0+" "+y0);
					}
				}
			}
			else if(y0<Xmax && y0>Xmin) { //Drawing clipped line....
					if(x0<Ymax && x0>Ymin){
						g.setColor(Color.RED);
						g.fillOval(y0, x0, 1, 1);
						System.out.println(pk+" "+x0+" "+y0);
						
					}
				}
			else {
				g.setColor(Color.BLUE);
				g.fillOval(y0, x0, 1, 1);
				System.out.println(pk+" "+x0+" "+y0);
			}
			x0++;
			if(pk<0) {
				pk=pk+2*dy;
			}
			else {
				y0++;
				pk=pk+2*dy-2*dx;
			}
			count++;
		}while(count<x1);
		// drawing the window..
		int i=Xmin;
		while(i<=Xmax) {
			g.setColor(Color.BLACK);
			g.fillOval(i, Ymin, 1, 1);
			g.fillOval(i, Ymax, 1, 1);
			i++;
		}
		i=Ymin;
		while(i<=Ymax) {
			g.setColor(Color.BLACK);
			g.fillOval(Xmin, i, 1, 1);
			g.fillOval(Xmax, i, 1, 1);
			i++;
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Xmin,Ymin,Xmax,Ymax,x0,y0,x1,y1,TBRL;
		System.out.println("Enter the top-left co-ordinate for the window:");
		Scanner sc=new Scanner(System.in);
		Xmin=sc.nextInt();
		Ymin=sc.nextInt();
		System.out.println("Enter the bottom-right co-ordinate for the window:");
		Xmax=sc.nextInt();
		Ymax=sc.nextInt();
		System.out.println("Enter the 1st co-ordinate for the line:");
		x0=sc.nextInt();
		y0=sc.nextInt();
		System.out.println("Enter the 2nd co-ordinate for the line:");
		x1=sc.nextInt();
		y1=sc.nextInt();
		TBRL=tbrl(Xmin,Ymin,Xmax,Ymax,x0,y0,x1,y1);
		if(TBRL==0)
			System.out.println("The line is completely outside of window..");
		else
			lineClipping(Xmin,Ymin,Xmax,Ymax,x0,y0,x1,y1,TBRL);
	}
	public static int tbrl(int Xmin,int Ymin,int Xmax,int Ymax,int x0, int y0, int x1, int y1) {
		int top,bottom,result;
		
		//TBRL code for coordinate (x0,y0)//
		if(x1<Xmin) top=1;
		else top=0;
		
		if(x1>Xmax) top+=2;
		
		if(y1<Ymin) top+=4;
		
		if(y1>Ymax) top+=8;
		System.out.println("TBRL(TOP):"+top);
		
		
		//TBRL code for coordinate (x1,y1)//
		if(x0<Xmin) bottom=1;
			else bottom=0;
		
		if(x0>Xmax) bottom+=2;
		
		if(y0<Ymin) bottom+=4;
		
		if(y0>Ymax) bottom+=8;
		System.out.println("TBRL(TOP):"+bottom);
		
		if(top==0 && bottom==0) 
			return 2;			//Weather line is inside the boundary.....
		
		result=top & bottom;
		
		if(result!=0)			//line is completely outside of the boundary....
			return 0;
		else
			return 1;			//line is partially inside the boundary.... need clipping....
		
	}

	public static void lineClipping(int Xmin, int Ymin, int Xmax, int Ymax, int x0, int y0, int x1,int y1, int TBRL) {
		int temp;
		double a,b,c,d;
		double dy1=y1;
		double m=(dy1-y0)/(x1-x0);
		
		// Interchanging coordinate value for slope value more that 1....
		if(m>1) {
			temp=x0;
			x0=y0;
			y0=temp;
			temp=x1;
			x1=y1;
			y1=temp;
		}
		CohenSutherlandLineClipping e=new CohenSutherlandLineClipping(Xmin,Ymin,Xmax,Ymax,x0,y0,x1,y1,m);
		JFrame f=new JFrame();
		f.add(e);
		f.setSize(400,400);
		f.setVisible(true);
	}

}