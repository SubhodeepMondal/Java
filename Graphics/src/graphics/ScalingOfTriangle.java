package graphics;

public class ScalingOfTriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] x= {4,10,10};
		int [] y= {6,6,10};
		
		int cx=0,cy=0,i=0,fac=2,area;
		System.out.println("Vertex coordinate of the triangle:");
		while(i<=2) {
			System.out.println("("+x[i]+","+y[i]+")");
			i++;
		}
		/*for(i=0;i<=2;i++) {
			cx+=x[i];
			cy+=y[i];
		}
		cx/=3;
		cy/=3;*/
		area=(x[0]*(y[1]-y[2])+x[1]*(y[2]-y[0])+x[2]*(y[0]-y[2]))/2;
		System.out.println("area"+area);
		/*System.out.println("Cx="+cx+"Cy="+cy);
		for(i=0;i<=2;i++) {
			x[i]=((x[i]-cx)/fac)+cx;
			y[i]=((x[i]-cy)/fac)+cy;
		}*/
		System.out.println("Vertex coordinate of the triangle after scaling:");
		i=0;
		while(i<=2) {
			x[i]/=2;
			y[i]/=2;
			System.out.println("("+x[i]+","+y[i]+")");
			i++;
		}
		area=(x[0]*(y[1]-y[2])+x[1]*(y[2]-y[0])+x[2]*(y[0]-y[2]))/2;
		System.out.println("area"+area);

	}

}