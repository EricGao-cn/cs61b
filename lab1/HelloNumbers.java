public class HelloNumbers{
    public static void main(String[] args){
        int x = 0;
        int tmp = 1;
        while(tmp < 10){
            System.out.print(x + " ");
            x = x + tmp;
            tmp = tmp + 1;
        }
    }
}