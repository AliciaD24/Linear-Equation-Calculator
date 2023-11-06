package DesigningClassesProject;

class Expression {
    
    private Term variableTerm;
    private Term constantTerm;

    Expression(Term variableTerm, Term constantTerm){
        this.variableTerm = variableTerm;
        this.constantTerm = constantTerm;
    }

    Term getVariableTerm(){
        return variableTerm;
    }

    Term getConstantTerm(){
        return constantTerm;
    }

    void setVariableTerm(Term variableTerm){
        this.variableTerm = variableTerm;
    }

    void setConstantTerm(Term constantTerm){
        this.constantTerm = constantTerm;
    }

    public Expression clone(){
        return new Expression(variableTerm, constantTerm);
    }

    boolean equals(Expression other){
        return this.variableTerm.equals(other.variableTerm) && this.constantTerm.equals(other.constantTerm);
    }

    public String toString(){
        if (constantTerm.toString().substring(0, 1).equals("-")){
            return String.format("%s - %s", variableTerm.toString(), constantTerm.toString().substring(1));
        }
        else {
            return String.format("%s + %s", variableTerm.toString(), constantTerm.toString());
        }
    }

    static Expression valueOf(String str) throws IllegalArgumentException{
        if (str.matches(expressionRegex())){
            String varTerm = str.split(" [\\+-] ")[0];
            String constant = str.split(" [\\+-] ")[1];
            if (str.substring(varTerm.length() + 1, varTerm.length() + 2).equals("-")){
                if (constant.substring(0, 1).equals("-"))
                    return new Expression(Term.valueOf(varTerm), Term.valueOf(constant).multiply(new Term(-1, "")));
                }
                else {
                    constant = "-" + str.split(" [\\+-] ")[1];
                }
            return new Expression(Term.valueOf(varTerm), Term.valueOf(constant));
        }
        else {
            throw new IllegalArgumentException("Does not match acceptable expression format.");
        }
    }

    static String expressionRegex(){
        return String.format("(%s) [\\+-] (%s)", Term.termRegexVariable(), Term.termRegexConstant());
    }

    

    public static void main(String[] args) {
    }
}
