package DesigningClassesProject;

class Term {
    private Fraction coefficient;
    private String variable;

    /** Constructor for a Term made up of a Fraction coefficient and a String variable.
     * @param coefficient represents the coefficient (Fraction).
     * @param variable represents the variable (String).
     * @throws IllegalArgumentException throws an exception if the variable is more than one character.
     */
    Term(Fraction coefficient, String variable) throws IllegalArgumentException{
        this.coefficient = coefficient;
        if (variable.length() > 1){
            throw new IllegalArgumentException("Variable must be only one character.");
        }
        else{
            this.variable = variable;
        }
    }

    /** Constructor for a Term made up of a String coefficient and a String variable.
     * @param coefficient represents the coefficient (String).
     * @param variable represents the variable (String).
     * @throws IllegalArgumentException throws an exception if the variable is more than one character.
     */
    Term(String coefficient, String variable) throws IllegalArgumentException{
        this.coefficient = Fraction.valueOf(coefficient);
        if (variable.length() > 1){
            throw new IllegalArgumentException("Variable must be only one character.");
        }
        else{
            this.variable = variable;
        }
    }

    /** Constructor for a Term made up of an Integer coefficient and a String variable.
     * @param coefficient represents the coefficient (Integer).
     * @param variable represents the variable (String).
     * @throws IllegalArgumentException
     */
    Term(int coefficient, String variable) throws IllegalArgumentException{
        this.coefficient = new Fraction(coefficient);
        if (variable.length() > 1){
            throw new IllegalArgumentException("Variable must be only one character.");
        }
        else{
            this.variable = variable;
        }
    }

    /** Gets the coefficient.
     * @return returns the coefficient (Fraction).
     */
    Fraction getCoefficient(){
        return coefficient;
    }

    /** Gets the variable.
     * @return returns the variable (String).
     */
    String getVariable(){
        return variable;
    }

    /** Sets the coefficient to a new value.
     * @param coefficient represents the new coefficient value (Fraction).
     */
    void setCoefficient(Fraction coefficient){
        this.coefficient = coefficient;
    }

    /** Sets the variable to a new value.
     * @param variable represents the new variable value (String).
     */
    void setVariable(String variable){
        this.variable = variable;
    }

    /** Clones a Term into a new Term object.
     * @return Term with identical values to the Term it was called on.
     */
    public Term clone(){
        return new Term(coefficient, variable);
    }

    /** Checks if a Term is equal to another Term.
     * @param other represents the other Term.
     * @return boolean
     */
    boolean equals(Term other){
        return this.variable.equals(other.variable) && this.coefficient.equals(other.coefficient);
    }
    
    /** Returns the string value of a Term formatted as a Term would be written.
     * @return String version of the Term.
     */
    public String toString(){
        if (coefficient.equals(new Fraction(1)) && !variable.equals("")){
            return variable;
        }
        if (coefficient.equals(new Fraction(-1)) && !variable.equals("")){
            return "-" + variable;
        }
        else{
            return String.valueOf(coefficient) + variable;
        }
    }

    /** Returns the Term value of a string whether the string is in the form of a fraction, decimal, whole number, no coefficient or no variable.
     * @param str represents the string being converted to a Term.
     * @return Term object with the variable and coefficient taken from the string.
     * @throws IllegalArgumentException throws the exception if the string does not match any of the acceptable formats for a Term.
     */
    static Term valueOf(String str) throws IllegalArgumentException{
        if (str.matches(termRegexBoth())){
            String variable = str.substring(str.length() - 1);
            if (str.matches("[a-zA-Z]")){
                return new Term(1, variable);
            }
            else if (str.matches("-[a-zA-Z]")){
                return new Term(-1, variable);
            }
            else if (!str.matches(".*[a-zA-Z]")){
                return new Term(Fraction.valueOf(str), "");
            }
            else{
                Fraction coefficient = Fraction.valueOf(str.substring(0, str.length() - 1));
                return new Term(coefficient, variable);
            }
        }
        else{
            throw new IllegalArgumentException("Term does not match acceptable format.");
        }
    }

    /** Checks if the variables are equivalent in two Terms to determine if they are like terms or not.
     * @param other represents the term being compared to the term the method is called on.
     * @return boolean 
     */
    private boolean likeTerms(Term other){
        return this.variable.equals(other.variable);
    }
    
    /** Adds two Terms together.
     * @param other represents the second Term.
     * @return Term result of adding the two Terms.
     * @throws IllegalArgumentException throws the exception if the terms do not have the same variable (are not like terms).
     */
    Term add(Term other) throws IllegalArgumentException{
        if (this.likeTerms(other)){
            Fraction newCoefficient = this.coefficient.add(other.coefficient);
            if (newCoefficient.equals(new Fraction(0))){
                return new Term(0, this.variable);
            }
            return new Term(newCoefficient, this.variable);
        }
        else {
            throw new IllegalArgumentException("Terms can only be added if they have the same variable.");
        }
    }

    /** Subtracts one Term from another.
     * @param other represents the Term being subtracted from the Term the method was called on.
     * @return Term result of subtracting the other Term from the Term the method was called on.
     * @throws IllegalArgumentException throws the excepttion if the terms do not hae the same variable (are not like terms).
     */
    Term subtract(Term other) throws IllegalArgumentException{
        if (this.likeTerms(other)){
            Fraction newCoefficient = this.coefficient.subtract(other.coefficient);
            if (newCoefficient.equals(new Fraction(0))){
                return new Term(0, "");
            }
            return new Term(newCoefficient, this.variable);
            
        }
        else {
            throw new IllegalArgumentException("Terms can only be subtracted if they have the same variable.");
        }
    }

    /** Checks if a Term can be multiplied of divided by another specified term. If there is a variable in both terms, will return false.
     * @param other represents the Term that is checked against the Term the method is called on.
     * @return boolean
     */
    private boolean canMultiplyOrDivide(Term other){
        return this.variable.equals("") && !other.variable.equals("") || !this.variable.equals("") && other.variable.equals("") || this.variable.equals("") && other.variable.equals("");
    }

    /** Multiplies one Term by another.
     * @param other represents the Term being multiplied by the Term the method is called on.
     * @return Term result of multiplying the Term the method was called on by the other specified Term.
     * @throws IllegalArgumentException throws the exception if both terms are variables as this will not result in a linear equation.
     */
    Term multiply(Term other) throws IllegalArgumentException{
        if (this.canMultiplyOrDivide(other)){
            return new Term(this.coefficient.multiply(other.coefficient), this.variable + other.variable);
        }
        else{
            throw new IllegalArgumentException("Cannot multiply two variables, not a linear equation.");
        }
    }

    /** Divides one Term by another.
     * @param other represemts the Term that the Term the method is called on is being divided by.
     * @return Term result of dividing the Term the method was called on by the other specified Term.
     * @throws IllegalArgumentException throws an exception if both terms are variables as this will not result in a linear equation.
     */
    Term divide(Term other) throws IllegalArgumentException{
        if (this.canMultiplyOrDivide(other)){
            return new Term(this.coefficient.divide(other.coefficient), this.variable + other.variable);
        }
        else{
            throw new IllegalArgumentException("Cannot divide two variables, not a linear equation.");
        }
    }

    /** Regex for a Term with a variable.
     * @return returns a formatted string with regex for a Term with a variable.
     */
    static String termRegexVariable(){
        return String.format("-{0,1}(%s){0,1}[a-zA-Z]", Fraction.fractionRegex("all"));
    }

    /** Regex for a Term without a variable.
     * @return returns a formatted string with regex for a constant Term.
     */
    static String termRegexConstant(){
        return String.format("-{0,1}(%s){0,1}", Fraction.fractionRegex("all"));
    }

    /** Regex for a Term either variable or constant.
     * @return returns a formatted string with regex for either a variable Term or a constant Term.
     */
    static String termRegexBoth(){
        return String.format("-{0,1}(%s){0,1}[a-zA-Z]{0,1}", Fraction.fractionRegex("all"));
    }

    /** Checks if a string matches the regex of a Term.
     * @param str represents the string being checked to see if it is a Term or not.
     * @return boolean
     */
    static boolean isTerm(String str){
        return str.matches(termRegexBoth());
    }

    public static void main(String[] args) {
    }
}
