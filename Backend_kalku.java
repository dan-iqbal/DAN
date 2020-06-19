package backend_kalku;
import java.util.Scanner;
import java.util.Stack;

public class Backend_kalku {
    
    int count = -1,operC = -1,infC = -1,sumC = -1, max = 30;
    String [] oper = new String[max]; //string output
    String [] output = new String[max]; //stack
    String [] trash = new String[max];
    String [] infix = new String[max];
    double [] sum = new double[max];
    
    public void push(String item){
        infix[infC+1] = item;
        infC++;
        if(item.equals("+") || item.equals("-")){
            if(operC== -1){
                oper[operC+1] = item;
                operC++;
            }else if(oper[operC].equals("+") || oper[operC].equals("-") || oper[operC].equals("/") || oper[operC].equals("*") || oper[operC].equals("^")){
                pop();
                oper[operC+1] = item;
                operC++;
            }else{
                oper[operC+1] = item;
                operC++;
            }
        }else if(item.equals("/") || item.equals("*")){
            if(operC== -1){
                oper[operC+1] = item;
                operC++;
            }else if(oper[operC].equals("/") || oper[operC].equals("*") || oper[operC].equals("^")){
                pop();
                oper[operC+1] = item;
                operC++;
            }else{
                oper[operC+1] = item;
                operC++;
            }
        }else if(item.equals("^")){
            if(operC== -1){
                oper[operC+1] = item;
                operC++;
            }else if(oper[operC].equals("^")){
                pop();
                oper[operC+1] = item;
                operC++;
            }else{
                oper[operC+1] = item;
                operC++;
            }
        }else if(item.equals("(")){
            oper[operC+1] = item;
            operC++;
        }else if(item.equals(")")){
            int roll = operC;
            while(!oper[roll].equals("(")){
                pop();
                roll -= 1;
            }
            trash[roll] = oper[roll];
            operC--;
            
        }else{
            if(!item.equals("=")){
                output[count+1] = item;
                count++;
            }else{
                for(int a = operC;a>-1;a--){
                    pop();
                }
                System.out.print("");
                System.out.println("Hasilnya adalah : "+hitung());
            }
        }
    }
    
    public void pop(){ //pop langsung masuk ke array postfix
        output[count+1] = oper[operC];
        count++;
        operC--;
    }
    
    public void show(){
        System.out.println("Infixnya : ");
        System.out.println("jumlah output : "+(infC+1)+" indek top : "+infC);
        for(int a = 0;a<=infC;a++){
             System.out.print(infix[a]+" ");
        }
        System.out.println();
        System.out.println("Postfixnya : ");
        System.out.println("jumlah output : "+(count+1)+" indek top : "+count);
        for(int a = 0;a<=count;a++){
             System.out.print(output[a]+" ");
        }
        System.out.println();
        System.out.println("jumlah stack : "+(operC+1)+" indek top : "+operC);
        for(int a = 0;a<=operC;a++){
             System.out.print(oper[a]+" ");
        }
        System.out.println();
    }
    
    public double hitung(){
        for(int a = 0;a<=count;a++){
            if(!output[a].equals("+") && !output[a].equals("-") && !output[a].equals("/") && !output[a].equals("*") && !output[a].equals("^")){
                sum[sumC+1] = Double.parseDouble(output[a]);
                sumC++;
            }else if(output[a].equals("+")){
                sum[sumC-1] += sum[sumC];
                sumC--;
            }else if(output[a].equals("-")){
                sum[sumC-1] -= sum[sumC];
                sumC--;
            }else if(output[a].equals("*")){
                sum[sumC-1] *= sum[sumC];
                sumC--;
            }else if(output[a].equals("/")){
                sum[sumC-1] /= sum[sumC];
                sumC--;
            }else if(output[a].equals("^")){
                sum[sumC-1] = Math.pow(sum[sumC-1],sum[sumC]);
                sumC--;
            }
        }
        return sum[0]; 
    }
    
    public void menu(){
        Scanner in = new Scanner(System.in);
        String input;
        do{
            System.out.println("----------------------------");
            System.out.print("masukan angka / operan : ");
            input = in.next();
            push(input);
            //show();
        }
        while(!input.equals("l"));
        //show();
    }
    
    public static void main(String[] args) {
        Backend_kalku tes = new Backend_kalku();
        tes.menu();
    }
    
}
