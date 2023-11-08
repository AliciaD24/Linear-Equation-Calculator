package DesigningClassesProject;

class Equation {

    private Expression leftSide;
    private Expression rightSide;

    Equation(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    Equation(String leftSide, String rightSide){
        this.leftSide = Expression.simplifyFully(leftSide);
        this.rightSide = Expression.simplifyFully(rightSide);
    }

    Expression getLeftSide(){
        return leftSide;
    }

    Expression getRightSide(){
        return rightSide;
    }

    void setLeftSide(Expression leftSide){
        this.leftSide = leftSide;
    }

    void setRightSide(Expression rightSide){
        this.rightSide = rightSide;
    }

    public Equation clone(){
        return new Equation(leftSide, rightSide);
    }

    boolean equals(Equation other){
        return this.leftSide.equals(other.leftSide) && this.rightSide.equals(other.rightSide);
    }

    public String toString(){
        return String.format("%s = %s", leftSide.toString(), rightSide.toString());
    }

    static Equation valueOf(String str) throws IllegalArgumentException{
        if (!validEquation(str)){
            throw new IllegalArgumentException("Does not match acceptable equation format.");
        }
        Expression leftSide = Expression.simplifyFully(str.split(" = ")[0]);
        Expression rightSide = Expression.simplifyFully(str.split(" = ")[1]);
        String leftVariable = leftSide.getVariableTerm().getVariable();
        String rightVariable = rightSide.getVariableTerm().getVariable();
        String leftConstant = leftSide.getConstantTerm().getCoefficient().toString();
        String rightConstant = rightSide.getConstantTerm().getCoefficient().toString();
        if (leftConstant.equals("0") && rightConstant.equals("0")){
            if (leftSide.toString().equals("0") || rightSide.toString().equals("0")){
                return new Equation(leftSide, rightSide);
            }
            else {
                throw new IllegalArgumentException("Must have one variable");
            }
        }
        else if (leftVariable.equals(rightVariable) || rightVariable.equals("") || leftVariable.equals("")){
            return new Equation(leftSide, rightSide);
        }
        else {
            throw new IllegalArgumentException("More than one variable in the equation.");
        }
    }

    static boolean validEquation(String str){
        boolean matchesRegex = str.matches(equationRegex());
        boolean containsVariable = str.matches(String.format(".*[a-zA-Z].*"));
        String forConstant = str + " ";
        boolean containsConstant = forConstant.matches(String.format(".*%s ", Term.termRegexConstant()));
        return matchesRegex && containsVariable && containsConstant;
    }

    static String equationRegex(){
        return String.format("%s = %s", Expression.expressionRegexMultDiv(), Expression.expressionRegexMultDiv());
    }

    private static String getVariableString(Equation eqn){
        String leftVariable = eqn.leftSide.getVariableTerm().getVariable();
        String rightVariable = eqn.rightSide.getVariableTerm().getVariable();
        if (leftVariable.equals("")){
            return rightVariable;
        }
        else if (rightVariable.equals("")){
            return leftVariable;
        }
        else{
            return leftVariable;
        }
    }

    static String solve(Equation equation){
        //ax + b = cx + d
        // (d - b) / (a - c)
        // (a - c) != 0
        Fraction a = equation.leftSide.getVariableTerm().getCoefficient();
        Fraction b = equation.leftSide.getConstantTerm().getCoefficient();
        Fraction c = equation.rightSide.getVariableTerm().getCoefficient();
        Fraction d = equation.rightSide.getConstantTerm().getCoefficient();
        Fraction answerNumerator = d.subtract(b);
        Fraction answerDenominator = a.subtract(c);
        if (answerDenominator.equals(new Fraction(0))){
            return "This can not be solved. (division by 0)";
        }
        Fraction answer = answerNumerator.divide(answerDenominator);
        return String.format("%s = %s", getVariableString(equation), answer.toString());
    }

    public static void main(String[] args) {
    }
}
