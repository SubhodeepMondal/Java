package math;
class Mathematics {
    public static void main(String args[]){
        int a, b;
        a = 50;
        b = 12;
        ArithmaticOperations aops = new ArithmaticOperations();
        System.out.println(aops.addition(a,b));
        System.out.println(aops.subtraction(a,b));
        System.out.println(aops.multiplication(a,b));
        System.out.println(aops.divition(a,b));
    }
}
class ArithmaticOperations{
    public int addition(int a, int b){
        return a+b;
    }
    public int subtraction(int a, int b){
        return a-b;
    }
    public int multiplication(int a, int b){
        return a*b;
    }
    public float divition(int a, int b){
        return (float)a/b;
    }
}