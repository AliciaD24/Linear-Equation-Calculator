package DesigningClassesProject;

import java.util.Scanner;

class CalculatorFixed {

    /**
     * Prints the calculator menu.
     */
    static void menu(){
        System.out.println("Welcome to Linear Equation Calculator!\n");
        System.out.println("Enter an equation with each term and operator separated by a space.");
        System.out.println("Example: 2(3 * 4r) = r - 4\n");
        System.out.println("Your equation must have at least one constant term and one variable term. A constant term multiplied or divided by a variable term will count as a variable term. You must have at least one constant term that is never multiplied or divided with a variable term.");
        System.out.println("This is a linear equation calculator ONLY. Any equation to a higher degree than 1 will not be evaluated.\n");
    }

    /** User interface for calculator taking in user input for the equation.
     * @return returns the answer to the equation the user inputs.
     */
    static String calculate(){
        Scanner input = new Scanner(System.in);
        String equation = "";
        String output = "";
        while (true){
            System.out.println("Enter your equation: ");
            equation = input.nextLine();
            try{
                output =  Equation.solve(equation);
                break;
            }
            catch (IllegalArgumentException error) {
                System.out.println("Invalid equation.");
            }
        }
        input.close();
        return output;
    }

    public static void main(String[] args) {
        menu();
        System.out.println(calculate());
    }
}
