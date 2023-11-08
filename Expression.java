package DesigningClassesProject;

import java.util.Scanner;

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
        return String.format("(%s)( [\\+-] (%s))*", Term.termRegexVariable(), Term.termRegexConstant());
    }

    static String expressionRegexGeneral(){
        return String.format("(%s)( [\\+\\*\\/-] (%s))*", Term.termRegexBoth(), Term.termRegexBoth());
    }

    private static String lastTerm(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexGeneral())){
            throw new IllegalArgumentException("Not a valid expression");
        }
        Scanner expression  = new Scanner(str);
        expression.useDelimiter(" [\\+\\-\\*\\/] ");
        String value = "";
        while (expression.hasNext()){
            value = expression.next();
        }
        expression.close();
        return value;
    }

    private static String firstTerm(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexGeneral())){
            throw new IllegalArgumentException("Not a valid expression");
        }
        Scanner expression  = new Scanner(str);
        expression.useDelimiter(" [\\+\\-\\*\\/] ");
        String firstTerm = expression.next();
        expression.close();
        return firstTerm;
    }

    private static Term resultingTerm(Term firstTerm, Term secondTerm, String operator){ 
        Term resultingTerm = new Term(1, "");
        if (operator.equals("*")){
            resultingTerm = firstTerm.multiply(secondTerm);
        }
        else if (operator.equals("/")){
            resultingTerm = firstTerm.divide(secondTerm);
        }
        return resultingTerm;
    }

    private static String getOperator(String str, String firstPortion){
        return str.substring(firstPortion.length() + 1, firstPortion.length() + 2);
    }

    private static String multAndDivide(String str){
        if (!str.matches(expressionRegexGeneral())){
            throw new IllegalArgumentException("Not a valid expression");
        }
        Scanner expression = new Scanner(str);
        expression.useDelimiter(" [\\*\\/] ");
        String firstPortion = expression.next(); 
        String secondPortion = expression.next(); 
        Term firstTerm = new Term(1, "");
        if (Term.isTerm(firstPortion)){
            firstTerm = Term.valueOf(firstPortion);
        }
        else{
            firstTerm = Term.valueOf(lastTerm(firstPortion));
        }
        Term secondTerm = Term.valueOf(firstTerm(secondPortion));
        Term resultingTerm = resultingTerm(firstTerm, secondTerm, getOperator(str, firstPortion));
        String endOfExpression = str.substring(firstPortion.length() + firstTerm(secondPortion).length() + 3);
        expression.close();
        String newFirstPart = firstPortion.replace(lastTerm(firstPortion).toString(), "");
        return newFirstPart + resultingTerm.toString() + endOfExpression;
    }

    private static String evaluateMultAndDivision(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexGeneral())){
            throw new IllegalArgumentException("Not a valid expression");
        }
        String initialExpression = str;
        for (int i = 0; i < count(initialExpression, " [\\/\\*] ", 3); i ++){
            str = multAndDivide(str);
        }
        return str;
    }

    private static int count(String str, String charactersToCount, int numOfChar){
        int count = 0;
        for (int i = 0; i < str.length() - (numOfChar - 1) ; i++){
            if (str.substring(i, i + numOfChar).matches(charactersToCount)){
                count ++;
            }
        }
        return count;
    }

    private static Term makeTerm(String str){
        if (str.substring(0, 1).equals("-")){
            return Term.valueOf(str.substring(1, str.length())).multiply(new Term(-1, ""));
        }  
        else if (str.substring(0, 1).matches("[\\+\\*\\/]")){
            return Term.valueOf(str.substring(1, str.length()));
        }
        else {
            return Term.valueOf(str);
        }
    }

    static Expression collectLikeTerms(String str) throws IllegalArgumentException{
        str = str.replace(" - ", " - -").replace(" + ", " + +");
        Scanner expression = new Scanner(str);
        expression.useDelimiter(" [\\+-] ");
        StringBuilder variables = new StringBuilder();
        StringBuilder constants = new StringBuilder();
        while (expression.hasNext()){
            String term = expression.next();
            String modified = term.replace("+", "");
            if (modified.matches(Term.termRegexVariable())){
                variables.append(term + "~");
            }
            else if (modified.matches(Term.termRegexConstant())){
                constants.append(term + "~");
            }
        }
        String variableString = variables.toString();
        String constantString = constants.toString();
        Term varTerm = evaluateCollectingTerms(variableString);
        Term constTerm = evaluateCollectingTerms(constantString);
        expression.close();
        return new Expression(varTerm, constTerm);
    }

    private static String addAndSubtractTerms(String str){
        Scanner terms = new Scanner(str.substring(0, str.length() - 1));
        terms.useDelimiter("~");
        String current = terms.next();
        Term currentTerm = makeTerm(current);
        String next = terms.next();
        Term nextTerm = makeTerm(next);
        Term resultingTerm = currentTerm.add(nextTerm);
        String endOfExpression = str.substring(current.length() + next.length() + 1);
        terms.close();
        return resultingTerm + endOfExpression.substring(0, endOfExpression.length());
    }

    private static Term evaluateCollectingTerms(String str){
        String initialString = str;
        if (str.substring(0, 1).matches("[\\+]")){
            str = str.substring(1);
        }
        for (int i = 0; i < count(initialString, "~[\\+-]", 2); i ++){
            str = addAndSubtractTerms(str);
        }
        return Term.valueOf(str.substring(0, str.length() - 1));
    }

    static Expression simplifyFully(String rawExpression){
        String expression = evaluateMultAndDivision(rawExpression);
        return collectLikeTerms(expression);
    }

    public static void main(String[] args) {
        System.out.println(simplifyFully("-2x + 5x - 7 + 8 * 4x - 8x + 9")); // 27x + 2
        System.out.println(simplifyFully("3y * 4 - 12y + 36 / 6 * 2 - 4y * 3 + 18y")); // 6y + 12
        System.out.println(simplifyFully("8 * 8t - 12 + 34/2t / 2 + t - 8 - 6/2t")); // 70 1/2t - 20
        System.out.println(simplifyFully("4/3 - 8/2 * 6/9 / 3/7W - 8W * 2")); // -22 2/9W + 1 1/3
        System.out.println(simplifyFully("12.3 * 4p - 12/2p * 8 / 3/2 + 2.4")); // 17 1/5p + 2 2/5
    }
}
