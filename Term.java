package DesigningClassesProject;

class Term {
    private Fraction coefficient;
    private String variable;

    Term(Fraction coefficient, String variable) throws IllegalArgumentException{
        this.coefficient = coefficient;
        if (variable.length() > 1){
            throw new IllegalArgumentException("Variable must be only one character.");
        }
        else{
            this.variable = variable;
        }
    }

    Term(String coefficient, String variable) throws IllegalArgumentException{
        this.coefficient = Fraction.valueOf(coefficient);
        if (variable.length() > 1){
            throw new IllegalArgumentException("Variable must be only one character.");
        }
        else{
            this.variable = variable;
        }
    }

    Term(int coefficient, String variable) throws IllegalArgumentException{
        this.coefficient = new Fraction(coefficient);
        if (variable.length() > 1){
            throw new IllegalArgumentException("Variable must be only one character.");
        }
        else{
            this.variable = variable;
        }
    }


    Fraction getCoefficient(){
        return coefficient;
    }

    String getVariable(){
        return variable;
    }

    void setCoefficient(Fraction coefficient){
        this.coefficient = coefficient;
    }

    void setVariable(String variable){
        this.variable = variable;
    }

    public Term clone(){
        return new Term(coefficient, variable);
    }

    boolean equals(Term other){
        return this.variable.equals(other.variable) && this.coefficient.equals(other.coefficient);
    }

    public String toString(){
        if (coefficient.equals(new Fraction(1))){
            return variable;
        }
        else{
            return String.valueOf(coefficient) + variable;
        }
    }

    static Term valueOf(String str) throws IllegalArgumentException{
        if (str.matches(termRegex())){
            String variable = str.substring(str.length() - 1);
            if (str.length() == 1){
                return new Term(1, variable);
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

    boolean likeTerms(Term other){
        return this.variable.equals(other.variable);
    }

    Term add(Term other) throws IllegalArgumentException{
        if (this.likeTerms(other)){
            return new Term(this.coefficient.add(other.coefficient), this.variable);
        }
        else {
            throw new IllegalArgumentException("Terms can only be added if they have the same variable.");
        }
    }

    Term subtract(Term other) throws IllegalArgumentException{
        if (this.likeTerms(other)){
            return new Term(this.coefficient.subtract(other.coefficient), this.variable);
        }
        else {
            throw new IllegalArgumentException("Terms can only be subtracted if they have the same variable.");
        }
    }

    Term multiply(Term other) throws IllegalArgumentException{
        if (this.variable.equals("") && !other.variable.equals("") || !this.variable.equals("") && other.variable.equals("") || this.variable.equals("") && other.variable.equals("")){
            return new Term(this.coefficient.multiply(other.coefficient), this.variable + other.variable);
        }
        else{
            throw new IllegalArgumentException("Cannot multiply two variable, not a linear equation.");
        }
    }

    Term divide(Term other) throws IllegalArgumentException{
        if (this.variable.equals("") && !other.variable.equals("") || !this.variable.equals("") && other.variable.equals("") || this.variable.equals("") && other.variable.equals("")){
            return new Term(this.coefficient.divide(other.coefficient), this.variable + other.variable);
        }
        else{
            throw new IllegalArgumentException("Cannot divide two variable, not a linear equation.");
        }
    }

    static String termRegex(){
        String decimal = "[0-9]+\\.[0-9]+";
        String whole = "[1-9][0-9]*";
        String decimalOrWhole = String.format("-{0,1}(%s|$s)", decimal, whole);
        return String.format("(%s){0,1}[a-zA-Z]", decimalOrWhole);
    }

    public static void main(String[] args) {
    }
}
