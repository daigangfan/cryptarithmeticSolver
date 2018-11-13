package Main;

import java.util.ArrayList;
import java.util.TreeMap;
public class Equation {
    private ArrayList<Character> Headletter;
    private String left;
    private String right;
    private String operator;
    private String result;
    private TreeMap<Character,Integer> map;
    private boolean legal=true;
    public Equation(String left,String right,String operator,String result){
        this.left=left;
        this.right=right;
        this.operator=operator;
        this.result=result;
        this.map=new TreeMap<>();
        this.Headletter=new ArrayList<>();
        Headletter.add(left.charAt(0));
        Headletter.add(right.charAt(0));

        String whole_letter=left+right+result;
        int count=0;
        for(int i=0;i<whole_letter.length();i++){
            if (!this.map.containsKey(whole_letter.charAt(i))) {
                this.map.put(whole_letter.charAt(i), count);
                count++;
            }
            if(count>9){
                this.legal=false;
                break;
            }
        }
    }
    public boolean isLegal(){
        return legal;
    }
    @Override
    public String toString(){
        String s="%10s\n"+operator+"%9s\n";
        s=String.format(s, left,right);
        s=s+"-----------\n";
        s=s+String.format("%10s\n",this.result);
        return s;
    }

    public void deepFirstSolver(){
        System.out.println("deep first search");
    }
    public void breadthFirstSolver(){
        System.out.println("breadth first search");

    }
    public void geneticSolver(){
        System.out.println("genetic method");
    }
    public String outputResult(){
        return "Not determined yet";
    }

    //test for string output:
    public static void main(String[] args){
        Equation equation=new Equation("SIX","TWO","*","TWELVE");
        System.out.println(equation.toString());
    }
}
