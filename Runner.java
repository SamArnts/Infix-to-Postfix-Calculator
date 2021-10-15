import java.util.Scanner;
import java.lang.Math;

public class Runner {

    public static void main(String [] args) {
        Stack opStack = new Stack();
        Queue output = new Queue();

        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Please enter the expression you wish to evaluate: ");
        String exp = scnr.nextLine();
        
        InfinixCalculator calc = new InfinixCalculator(){

            @Override
            public String evalExp(String exp) {
                
               int openParen = 0; 
               int closeParen = 0;
               Character first = exp.charAt(0);
               Character last = exp.charAt(exp.length() - 1);

               if (operator(Character.toString(first)) || operator(Character.toString(last))) {
                    System.out.println("Invalid expression, an operator cannot be in the first or last position.");
                    return null;
               }
            
               for (int i = 0; i < exp.length(); i++) {
                    Character c = exp.charAt(i);
                    String temp = "";

                    if (Character.isAlphabetic(c)) {
                        System.out.println("Invalid Input: Make sure to enter no letters. ");
                        return null;
                    }else if (c.equals(' ')){}
                    else if (c.equals('(')) {
                        opStack.push((Object)c);
                        openParen++;
                    }else if (c.equals(')')) {
                        while (!opStack.isEmpty() && !opStack.peek().equals('(')) {
                            output.enque(opStack.pop());
                        }
                        closeParen++;
                    }else if (operator(Character.toString(c))){
                        while (!opStack.isEmpty() && 
                               getPrecedence(c) <= getPrecedence((char)opStack.peek())){
                                output.enque((Object) opStack.pop());
                               }
                        opStack.push((Object)c);
                    }else {
                        int j = i;
                        while (!(c.equals(' ')) && j < exp.length() && !operator(Character.toString(c))) {
                            temp = temp + c;
                            if (j < exp.length() - 1) {
                                Character n = exp.charAt(j + 1);
                                if (Character.isDigit(n) || n.equals('.')) {
                                    c = exp.charAt(j + 1);
                                    j++;
                                    i++;
                                }
                                else {
                                    break;
                                }
                            }else { 
                                j ++;
                            }  
                        }if (i == exp.length() - 1 && temp.length() < 2) {
                            output.enque((Object) c);
                        }
                        else{
                            output.enque(temp);
                        }
                    }   
               }
               while (!opStack.isEmpty()) {
                output.enque((Object)opStack.pop());
                }
                if (openParen != closeParen) {
                    System.out.println("Invalid expression, check # of parantheses. ");
                    return null;
                }
                String postfix = "";
                for (int i = 0; i < output.getSize(); i++) {
                    if (!(output.get(i).equals('(')) && !(output.get(i).equals(')')))
                    postfix +=  output.get(i).toString() + " ";
                }
               
                return postfix;
            }
            
        };

        String result = calc.evalExp(exp);
        if (result == null) {
            return;
        }
        System.out.println("Postfix: " + result);
        Double numResult = evalTotal(result);
        System.out.println("Ans: " + numResult); 
    }

    public static int getPrecedence(char c) {
        switch (c) {
            case '-':
            case '+':
            return 1;

            case '/':
            case '*':
            case '%':
            return 2;

            case '^':
            return 3;

        }
        return 0;
    }

    public static double evalTotal(String postfix) {
        String[] newExp = postfix.split(" ");
        Stack eval = new Stack();

        double num1 = 0;
        double num2= 0;
        double answer = 0;

        for (int i = 0; i < newExp.length; i++) {
            if (operator(newExp[i])) {
                num2 = (Double) eval.pop();
                num1 = (Double) eval.pop();
                answer = evalSingle(num1, num2, newExp[i]);
                eval.push(answer);

            }
            else {
                eval.push(Double.valueOf(newExp[i]));
            }
        }
        return answer;
    }

    public static boolean operator(String symbol) {
        if (symbol.equals("+") || symbol.equals("-") ||
            symbol.equals("*") || symbol.equals("/") ||
            symbol.equals("%") || symbol.equals("^")) {
                return true;
            }
            return false;
    }

    public static Double evalSingle(Double num1, Double num2, String op) {
        Double answer = 0.0;
        switch(op) {
            case "+":
            answer = num1 + num2;
            break;

            case "-":
            answer = num1 - num2;
            break;

            case "*":
            answer = num1 * num2;
            break;

            case "/":
            answer = num1 / num2;
            break;

            case "%":
            answer = num1 % num2;
            break;

            case "^":
            answer = (Double) Math.pow(num1, num2);
            break;

        }
        return answer;
    }
}
