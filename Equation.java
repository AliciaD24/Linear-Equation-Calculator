package DesigningClassesProject;

class Equation {

    private Expression leftSide;
    private Expression rightSide;

    /** Constructor for an Equation made up of a left side and a right side where both are Expressions.
     * @param leftSide represents the expression to the left of the equal sign.
     * @param rightSide represents the expression to the right of the equal sign.
     */
    Equation(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    /** Constructor for an Equation made up of a left side and a right side where both are Expressions.
     * @param leftSide represents the expression to the left of the equal sign.
     * @param rightSide represent the expression to the right of the equal sign.
     */
    Equation(String leftSide, String rightSide){
        this.leftSide = Expression.simplifyFully(leftSide);
        this.rightSide = Expression.simplifyFully(rightSide);
    }

    /** Gets the left side of the equation.
     * @return returns the left side of the equation.
     */
    Expression getLeftSide(){
        return leftSide;
    }

    /** Gets the right side of the equation.
     * @return returns the right side of the equation.
     */
    Expression getRightSide(){
        return rightSide;
    }

    /** Sets the left side to a new expression.
     * @param leftSide represents the new expression value of the left side.
     */
    void setLeftSide(Expression leftSide){
        this.leftSide = leftSide;
    }

    /** Sets the right side to a new expression.
     * @param leftSide represents the new expression value of the right side.
     */
    void setRightSide(Expression rightSide){
        this.rightSide = rightSide;
    }

    /** Clones an Equation into a new Equation object.
     * @return Equation with identical values to the Equation it was called on.
     */
    public Equation clone(){
        return new Equation(leftSide, rightSide);
    }

    /** Checks if an Equation is equal to another Equation.
     * @param other represents the other Equation.
     * @return boolean
     */
    boolean equals(Equation other){
        return this.leftSide.equals(other.leftSide) && this.rightSide.equals(other.rightSide);
    }
    
    /** Returns the string value of an Equation formatted as an Equation would be written.
     * @return String version of the Equation.
     */
    public String toString(){
        return String.format("%s = %s", leftSide.toString(), rightSide.toString());
    }

    /** Returns the Equation value of a string.
     * @param str represents the string being converted to an Equation.
     * @return Equation object with the variable Term and constant Term taken from the string.
     * @throws IllegalArgumentException throws an exception if the string is not a valid Equation.
     */
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
                throw new IllegalArgumentException("Must have variable and constant");
            }
        }
        else if (leftVariable.equals(rightVariable) || rightVariable.equals("") || leftVariable.equals("")){
            return new Equation(leftSide, rightSide);
        }
        else {
            throw new IllegalArgumentException("More than one variable in the equation.");
        }
    }

    /** Checks if a string is a valid Equation.
     * @param str represents the string being checked.
     * @return boolean
     */
    static boolean validEquation(String str){
        boolean matchesRegex = str.matches(equationRegex());
        boolean containsVariable = str.matches(String.format(".*[a-zA-Z].*"));
        String forConstant = str + " ";
        boolean containsConstant = forConstant.matches(String.format(".*%s ", Term.termRegexConstant()));
        return matchesRegex && containsVariable && containsConstant;
    }

    /** Regex for an Equation.
     * @return returns a formatted string with the regex of an Equation.
     */
    static String equationRegex(){
        return String.format("%s = %s", Expression.expressionRegexAll(), Expression.expressionRegexAll());
    }

    /** Gets the variable of an equation.
     * @param eqn represents the equation.
     * @return returns a String containing the variable in the equation.
     * @throws IllegalArgumentException throws the exception if the equation does not match acceptable equation regex.
     */
    private static String getVariableStrForm(Equation eqn) throws IllegalArgumentException{
        if (!eqn.toString().matches(equationRegex())){
            throw new IllegalArgumentException("Invalid equation format. Can not get the variable.");
        }
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

    /** Solves an equation for the variable.
     * @param eqn represents the equation.
     * @return returns the variable with its value.
     * @throws IllegalArgumentException throws an exception if the string equation does not match acceptable equation regex.
     */
    static String solve(String eqn) throws IllegalArgumentException{
        if (!eqn.matches(equationRegex())){
            throw new IllegalArgumentException("Invalid equation format. Can not solve the equation.");
        }
        Equation equation = Equation.valueOf(eqn);
        //ax + b = cx + d
        // (d - b) / (a - c)
        // (a - c) != 0
        Fraction a = equation.leftSide.getVariableTerm().getCoefficient();
        Fraction b = equation.leftSide.getConstantTerm().getCoefficient();
        Fraction c = equation.rightSide.getVariableTerm().getCoefficient();
        Fraction d = equation.rightSide.getConstantTerm().getCoefficient();
        Fraction answerNumerator = d.subtract(b);
        Fraction answerDenominator = a.subtract(c);
        if (eqn.toString().matches(".* \\/ [a-zA-Z].*")){
            answerNumerator = a.subtract(c);
            answerDenominator = d.subtract(b);
        }
        if (answerDenominator.equals(new Fraction(0))){
            return "This can not be solved. (division by 0)";
        }
        Fraction answer = answerNumerator.divide(answerDenominator);
        return String.format("%s = %s", getVariableStrForm(equation), answer.toString());
    }

    public static void main(String[] args) {
    }
}
