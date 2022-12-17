package graphics;
import java.awt.*;
import java.util.*;
import javax.swing.JFrame;
public class LiangBarskyLine extends Canvas
{
static double xx1,xx2,yy1,yy2;
LiangBarskyLine(double xx1,double yy1,double xx2,double yy2)
{
  this.xx1=xx1;
  this.yy1=yy1;
  this.xx2=xx2;
  this.yy2=yy2;
}
public void paint(Graphics G)                                  
{
	G.setColor(Color.red);
	G.drawLine((int)xx1,(int) yy1,(int) xx2,(int) yy2);
} 
public static void main(String args[])
{
double x1=25,y1=55,x2=55,y2=25;
Scanner sc=new Scanner(System.in);
System.out.println("Enter left-top-right-bottom corners of the window:");
double xmin=sc.nextDouble();
double ymin=sc.nextDouble();
double xmax=sc.nextDouble();
double ymax=sc.nextDouble();
double dx=x2-x1,dy=y2-y1;
double p1=-dx,p2=dx,p3=-dy,p4=dy;
double q1=x1-xmin,q2=xmax-x1,q3=y1-ymin,q4=ymax-y1;
if(p1==0 || p2==0 || p3==0 || p4==0)
{
System.out.println("Line is parallel to boundary");
System.exit(0);
}
double u1=q1/p1,u2=q2/p2,u3=q3/p3,u4=q4/p4;
double max=0,min=1;
if(u1>0 && u1<1 && p1<0 && u1>max)
 max=u1;
else if(u1>0 && u1<1 && p1>0 && u1<min)
 min=u1;
if(u2>0 && u2<1 && p2<0 && u2>max)
	 max=u2;
else if(u2>0 && u2<1 && p2>0 && u2<min)
	 min=u2;
if(u3>0 && u3<1 && p3<0 && u3>max)
	 max=u3;
else if(u3>0 && u3<1 && p3>0 && u3<min)
	 min=u3;
if(u4>0 && u4<1 && p4<0 && u4>max)
	 max=u4;
else if(u4>0 && u4<1 && p4>0 && u4<min)
	 min=u4;
xx1=x1+max*dx;
xx2=x1+min*dx;
yy1=y1+max*dy;
yy2=y1+min*dy;

if(min>max)
{
System.out.println("Minvalue:"+min+"\nMaxvalue:"+max);
 System.out.println("No line can be drawn");
 System.exit(0);
 }
System.out.println("Endpoints:("+xx1+","+yy1+") and ("+xx2+","+yy2+")");
LiangBarskyLine d=new LiangBarskyLine(xx1,yy1,xx2,yy2);
JFrame f=new JFrame();
f.add(d);
f.setSize(800,800);
f.setVisible(true); 
sc.close();
 }
}