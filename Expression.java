package DesigningClassesProject;

import java.util.Scanner;

class Expression {
    
    private Term variableTerm;
    private Term constantTerm;

    /** Constructor for an Expression made up of a variable Term and a constant Term.
     * @param variableTerm represents the variable Term.
     * @param constantTerm represents the constant Term.
     */
    Expression(Term variableTerm, Term constantTerm){
        this.variableTerm = variableTerm;
        this.constantTerm = constantTerm;
    }

    /** Gets the variable Term.
     * @return returns the variable Term.
     */
    Term getVariableTerm(){
        return variableTerm;
    }

    /** Gets the constant Term.
     * @return returns the constant Term.
     */
    Term getConstantTerm(){
        return constantTerm;
    }

    /** Sets the variable Term to a new value.
     * @param variableTerm represents the new variable Term value.
     */
    void setVariableTerm(Term variableTerm){
        this.variableTerm = variableTerm;
    }

    /** Sets the constant Term to a new value.
     * @param constantTerm represents the new constant Term value.
     */
    void setConstantTerm(Term constantTerm){
        this.constantTerm = constantTerm;
    }

    /** Clones an Expression into a new Expression object.
     * @return Expression with identical values to the Expression it was called on.
     */
    public Expression clone(){
        return new Expression(variableTerm, constantTerm);
    }

    /** Checks if an Expression is equal to another Expression.
     * @param other represents the other Expression.
     * @return boolean
     */
    boolean equals(Expression other){
        return this.variableTerm.equals(other.variableTerm) && this.constantTerm.equals(other.constantTerm);
    }
    
    /** Returns the string value of an Expression formatted as an Expression would be written.
     * @return String version of the Expression.
     */
    public String toString(){
        if (constantTerm.getCoefficient().equals(new Fraction(0)) && variableTerm.getCoefficient().equals(new Fraction(0))){
            return "0";
        }
        else if (constantTerm.getCoefficient().equals(new Fraction(0))){
            return variableTerm.toString();
        }
        else if (variableTerm.getCoefficient().equals(new Fraction(0))){
            return constantTerm.toString();
        }
        if (constantTerm.toString().substring(0, 1).equals("-")){
            return String.format("%s - %s", variableTerm.toString(), constantTerm.toString().substring(1));
        }
        else {
            return String.format("%s + %s", variableTerm.toString(), constantTerm.toString());
        }
    }
    
    /** Returns the Expression value of a string.
     * @param str represents the string being converted to an Expression.
     * @return Expression object with the variable Term and constant Term taken from the string.
     * @throws IllegalArgumentException throws an exception if the string does not match any of the acceptable formats for an Expression.
     */
    static Expression valueOf(String str) throws IllegalArgumentException{
        if (str.matches(expressionRegex())){
            String varTerm = "";
            String constant = "";
            if (str.matches(Term.termRegexBoth())){
                if (str.matches(Term.termRegexVariable())){
                    varTerm = str;
                    constant = "0";
                }
                else{
                    constant = str;
                    varTerm = "0";
                }
            }
            else{
                if (str.split(" [\\+-] ")[0].matches(Term.termRegexVariable())){
                    varTerm = str.split(" [\\+-] ")[0];
                }
                else {
                    constant = str.split(" [\\+-] ")[0];
                }
                if (str.split(" [\\+-] ")[1].matches(Term.termRegexConstant())){
                    constant = str.split(" [\\+-] ")[1];
                }
                else {
                    varTerm = str.split(" [\\+-] ")[1];
                }
                
                if (str.substring(varTerm.length() + 1, varTerm.length() + 2).equals("-")){
                    if (constant.substring(0, 1).equals("-"))
                        return new Expression(Term.valueOf(varTerm), Term.valueOf(constant).multiply(new Term(-1, "")));
                    }
                    else {
                        constant = "-" + str.split(" [\\+-] ")[1];
                    }
            }
            return new Expression(Term.valueOf(varTerm), Term.valueOf(constant));
        }
        else {
            throw new IllegalArgumentException("Does not match acceptable expression format. Can not make expression.");
        }
    }

    /** Regex for a fully simplified Expression.
     * @return returns a formatted string with regex for a fully simplified Expression.
     */
    private static String expressionRegex(){
        return String.format("((%s)( [\\+-] (%s))*)|((%s)( [\\+-] (%s))*)", Term.termRegexVariable(), Term.termRegexConstant(), Term.termRegexConstant(), Term.termRegexVariable());
    }

    /** Regex for a not simplified Expression that allows multiplication and/or division along with addition and/or subtraction. Also allows simplified Expressions.
     * @return returns a formatted string with regex for an Expression with multiplication, division, addition and/or subtraction OR simplified Expression.
     */
    private static String expressionRegexMultDiv(){
        return String.format("(%s)( [\\+\\*\\/-] (%s))*", Term.termRegexBoth(), Term.termRegexBoth());
    }

    /** Regex for a not simplified Expression with only addition and/or subtraction. Also allows simplified Expressions.
     * @return returns a formatted string with regex for an Expression with addition and/or subtraction OR simplified Expression.
     */
    private static String expressionRegexAddSub(){
        return String.format("[\\+]{0,1}(%s)~{0,1}(~[\\+-]{0,1}(%s))*", Term.termRegexBoth(), Term.termRegexBoth());
    }

    /** Regex for a not simplified Expression with addition, subtraction, multiplication and/or division. Also allows simplified Expressions and brackets.
     * @return returns a formatted string with regex for an Expression with brackets, addition, subtraction, multiplication and/or division OR simplified Expression.
     */
    static String expressionRegexAll(){
        return String.format("((%s){0,1}(\\(%s\\)){0,1}( [\\+\\*\\/-] %s){0,1})+", expressionRegexMultDiv(), expressionRegexMultDiv(), expressionRegexMultDiv());
    }

    /** Find the last Term value in a string.
     * @param str represents the string that is being checked for the last Term value.
     * @return Term value of the last Term in the specified string.
     * @throws IllegalArgumentException throws the exception if the string does not match the regex for an Expression with multipliaction and/or division.
     */
    private static String lastTerm(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Not a valid expression. Does not match acceptable format. Can not get last term.");
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

    /** Find the first Term value in a string.
     * @param str represents the string that is being checked for the first Term value.
     * @return Term value of the first Term in the specified string.
     * @throws IllegalArgumentException throws the exception if the string does not match the regex for an Expression with multiplication and/or division.
     */
    private static String firstTerm(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Not a valid expression. Does not match acceptable format. Can not get first term.");
        }
        Scanner expression  = new Scanner(str);
        expression.useDelimiter(" [\\+\\-\\*\\/] ");
        String firstTerm = expression.next();
        expression.close();
        return firstTerm;
    }

    /** Multiplies or divides two terms depending on the operator passed in.
     * @param firstTerm represents the first Term in the operation.
     * @param secondTerm represents the second Term in the operation.
     * @param operator represents the operator of the operation.
     * @return returns the result of the operation.
     * @throws IllegalArgumentException throws an exception if the operator is not an asterisk (multiplication) or a slash (division).
     */
    private static Term resultingTerm(Term firstTerm, Term secondTerm, String operator) throws IllegalArgumentException{ 
        if (!operator.matches("[\\*\\/]")){
            throw new IllegalArgumentException("Invalid operator.");
        }
        Term resultingTerm = new Term(1, "");
        if (operator.equals("*")){
            resultingTerm = firstTerm.multiply(secondTerm);
        }
        else if (operator.equals("/")){
            resultingTerm = firstTerm.divide(secondTerm);
        }
        return resultingTerm;
    }

    /** Gets the operator from the passed in string.
     * @param str represents the expression string.
     * @param firstPortion represents the portion of the string containing all the Terms before the operator.
     * @return returns the operator directly after the first portion of the string.
     * @throws IllegalArgumentException throws an exception if the expresion string or the first portion of the string does not match Expression regex.
     */
    private static String getOperator(String str, String firstPortion) throws IllegalArgumentException{
        if (!str.matches(expressionRegexMultDiv()) || !firstPortion.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Not a valid expression. Does not match acceptable format. Can not get operator.");
        }
        return str.substring(firstPortion.length() + 1, firstPortion.length() + 2);
    }

    /** Identifies the first multiplication or division operation in the expression and evaluates it.
     * @param str represents the expression.
     * @return returns a string with the updated expression where the first multiplication or division operation is solved and the result of it is in place of the two Terms and operator.
     * @throws IllegalArgumentException throws an exception if the expression string does not match multiplication/division Expression regex.
     */
    private static String multAndDivide(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Invalid expression. Does not match acceptable format. Can not multiply or divide.");
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
        String newFirstPart = firstPortion.substring(0, firstPortion.length() - lastTerm(firstPortion).length());
        return newFirstPart + resultingTerm.toString() + endOfExpression;
    }

    /** Evaluates all multiplication and division in an Expression.
     * @param str represents the Expression being evaluated.
     * @return returns the string of the expression with all the multiplication and division operations evaluated.
     * @throws IllegalArgumentException throws an exception if the expression does not match multiplication/division Expression regex.
     */
    private static String evaluateMultAndDivision(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Invalid expression. Does not match acceptable format. Can not evaluate multiplication and division.");
        }
        String initialExpression = str;
        for (int i = 0; i < count(initialExpression, " [\\/\\*] ", 3); i ++){
            str = multAndDivide(str);
        }
        return str;
    }

    /** Counts the number of instances of certain characters in a string.
     * @param str represents the string in which the instances of specified characters are being counted.
     * @param charactersToCount represents the charaters being counted in the specified string.
     * @param numOfChar represents the number of characters that are being counted.
     * @return returns an Integer with the value of the number of instances of the specified characters in the specified string.
     */
    private static int count(String str, String charactersToCount, int numOfChar){
        int count = 0;
        for (int i = 0; i < str.length() - (numOfChar - 1) ; i++){
            if (str.substring(i, i + numOfChar).matches(charactersToCount)){
                count ++;
            }
        }
        return count;
    }

    /** Makes a Term out of a string taking into account the operator infront of the term.
     * @param str represents the string of the term with the operator.
     * @return returns the Term value of the original string.
     * @throws IllegalArgumentException throws the exception if the term does not match acceptable term regex.
     */
    private static Term makeTerm(String str) throws IllegalArgumentException{
        if (!Term.isTerm(str.substring(1)) && !Term.isTerm(str)){
            throw new IllegalArgumentException("Does not match Term format. Cannot make term.");
        }
        if (str.substring(0, 1).equals("-")){
            return Term.valueOf(str.substring(1)).multiply(new Term(-1, ""));
        }  
        else if (str.substring(0, 1).matches("[\\+\\*\\/]")){
            return Term.valueOf(str.substring(1));
        }
        else {
            return Term.valueOf(str);
        }
    }

    /** Collects all like terms in an expression making it into one variable term and one constant term.
     * @param str represents the string of the initial equation before collecting like terms.
     * @return Expression object with the variable term and constant term of all the variable terms and constant terms collected respectively.
     * @throws IllegalArgumentException throws the exception if the string representing the expression does not match acceptable expression regex.
     */
    private static Expression collectLikeTerms(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Invalid expression. Does not match acceptable format. Can not collect like terms.");
        }
        str = str.replace(" - -", " + ");
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

    /** Identifies the first addition or subtraction operation in the expression and evaluates it.
     * @param str represents the expression.
     * @return returns a string with the updated expression where the first addition or subtraction operation is solved and the result of it is in place of the two Terms and operator.
     * @throws IllegalArgumentException throws the exception if the expression does not match the addition/subtraction expression regex.
     */
    private static String addAndSubtractTerms(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexAddSub())){
            throw new IllegalArgumentException("Invalid format. Can not add or subtract terms.");
        }
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

    /** Identifies the number of addition/subtraction operations and evaluates them all.
     * @param str represents the expression being evaluated.
     * @return returns the Term that is the value of all the other terms and operations evaluated.
     * @throws IllegalArgumentException throws the exception if the string does not match the addition/subtraction expression regex.
     */
    private static Term evaluateCollectingTerms(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexAddSub())){
            throw new IllegalArgumentException("Invalid format. Can not collect like terms.");
        }
        String initialString = str;
        if (str.equals("")){
            return new Term(0, "");
        }
        if (str.substring(0, 1).matches("[\\+]")){
            str = str.substring(1);
        }
        for (int i = 0; i < count(initialString, "~[\\+-]", 2); i ++){
            str = addAndSubtractTerms(str);
        }
        return Term.valueOf(str.substring(0, str.length() - 1));
    }

    /** Evaluates brackets and outputs the expression with all brackets replaced with the evaluated equivalent.
     * @param str represents the expression.
     * @return returns the original expression with brackets in the specified expression evaluated and replaced with the answer.
     * @throws IllegalArgumentException throws the exception if the specified string does not match the regex for an expression.
     */
    private static String evaluateBrackets(String str) throws IllegalArgumentException{
        if (!str.matches(expressionRegexAll())){
            throw new IllegalArgumentException("Invalid format. Can not evaluate brackets.");
        }
        String beginningBracket = str.split("\\(")[1];
        String bracketExpression = beginningBracket.split("\\)")[0];
        String solvedExpression = simplify(bracketExpression).toString();
        Expression solved = Expression.valueOf(solvedExpression);
        Term multiplier = new Term(1, "");
        if (!str.substring(0, 1).equals("(")){
            multiplier = Term.valueOf(lastTerm(str.split("\\(")[0]));
        }
        Term varTerm = solved.variableTerm.multiply(multiplier);
        Term constTerm = solved.constantTerm.multiply(multiplier);
        Expression finalBracketExpression = new Expression(varTerm, constTerm);
        String PortionToReplace = "";
        if (!str.substring(0, 1).equals("(")){
            PortionToReplace = String.format("%s(%s)", lastTerm(str.split("\\(")[0]), bracketExpression);
        }
        else{
            PortionToReplace = String.format("(%s)", bracketExpression);
        }
        String bracketExpressionRegex = bracketExpression.replace("+", "\\+").replace("*", "\\*").replace("/", "\\/");
        String output = "";
        if (!str.matches(String.format("%s\\(%s\\).*", multiplier, bracketExpressionRegex))){
            output = str.replace(PortionToReplace, String.format("%s", finalBracketExpression.toString()));
        }
        else {
            output = str.replace(PortionToReplace, finalBracketExpression.toString());
        }
        return output.replace("--", "-");
    }

    /** Solves all addition/subtraction/multiplication/division to simplify the expression.
     * @param rawExpression represents the expression that is being simplified.
     * @return returns the expression in the simplest form (one variable term and one constant term).
     * @throws IllegalArgumentException
     */
    private static Expression simplify(String rawExpression) throws IllegalArgumentException{
        if (!rawExpression.matches(expressionRegexMultDiv())){
            throw new IllegalArgumentException("Invalid formatting. Can not simplify the expression.");
        }
        String expression = evaluateMultAndDivision(rawExpression);
        return collectLikeTerms(expression);
    }

    /** Simplifies an expression fully by evaluating brackets, multiplication, division, addition and subtraction.
     * @param rawExpression represents the expression being simplified.
     * @return returns the expression in the simplest form (variable term and constant term).
     */
    static Expression simplifyFully(String rawExpression){
        if (!rawExpression.matches(expressionRegexAll())){
            throw new IllegalArgumentException("Invalid formatting. Can not simplify the expression fully.");
        }
        String expression = rawExpression;
        if (rawExpression.contains("(")){
            for (int i = 0; i < count(rawExpression, "\\(" , 1); i++){
                expression = evaluateBrackets(expression);
            }
        }
        return simplify(expression);
    }

    public static void main(String[] args) {
    }
}
