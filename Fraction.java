package DesigningClassesProject;

class Fraction {
    private int numerator;
    private int denominator;

    /** Constructor for a Fraction made up of a numerator and denominator value
     * @param numerator represents the numerator
     * @param denominator represents the denominator
     * @throws IllegalArgumentException throws the exception if the denominator is 0
     */
    Fraction(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }


    /** Constructor for a Fraction made up of a whole number, numerator and denominator value
     * @param whole represents the whole number
     * @param numerator represents the numerator
     * @param denominator represents the denominator
     * @throws IllegalArgumentException throws the exception if the denominator is 0
     */
    Fraction(int whole, int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannoe be zero");
        }

        if (whole < 0){
            numerator = -1 * (Math.abs(whole) * denominator + numerator);
        }
        else{
            numerator = whole * denominator + numerator;
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    /** Constructor for a Fraction made up of a decimal value
     * @param decimal represents the decimal 
     */
    Fraction(double decimal){
        int digitsAfterDecimal = String.valueOf(decimal).length() - String.valueOf(decimal).indexOf('.') - 1;
        double numerator = decimal;
        int denominator = 1;
        numerator *= Math.pow(10, digitsAfterDecimal);
        denominator *= Math.pow(10, digitsAfterDecimal);
        numerator = Math.round(numerator);
        this.numerator = getSimplifiedNumerator((int)numerator, denominator);
        this.denominator = getSimplifiedDenominator((int)numerator, denominator);
    }


    /** Constructor for a Fraction made up of only a whole number
     * @param whole represents the whole number
     */
    Fraction(int whole){
        numerator = whole;
        denominator = 1;
    }

    /** Returns the greatest common denominator of two denominators
     * @param num1 represents the first of the two denominators
     * @param num2 represents the second of the two denominators
     * @return returns the greatest common denominator between the two denominators
     */
    static int gcd(int num1, int num2){
        if (num1 == 0 || num2 == 0){
            return Math.max(Math.abs(num1), Math.abs(num2));
        }
        int lower = Math.min(Math.abs(num1), Math.abs(num2));
        while (num1 % lower != 0 || num2 % lower != 0){
            lower --;
        }
        return lower;
    }

    /** Returns the most simplified version of the numerator using the greatest common denominator method
     * @param numerator represents the value of the original numerator
     * @param denominator represents the value of the denominator
     * @return returns the simplified version of the numerator using the denominator
     * @throws IllegalArgumentException throws the exception if the denominator is 0
     */
    static int getSimplifiedNumerator(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        
        if (numerator * denominator < 0){
            return -1 * Math.abs(numerator) / gcd(numerator, denominator);
        }
        return Math.abs(numerator) / gcd(numerator, denominator);
    }


    /** Returns the most simplified version of the denominator using the greatest common denominator method
     * @param numerator represents the value of the numerator
     * @param denominator represents the value of the original denominator
     * @return returns the simplified version of the numerator using the denominator
     */
    static int getSimplifiedDenominator(int numerator, int denominator){
        return Math.abs(denominator) / gcd(numerator, denominator);
    }


    /** Gets the numerator
     * @return returns the numerator
     */
    int getNumerator(){
        return numerator;
    }

    /** Gets the denominator
     * @return returns the denominator
     */
    int getDenominator(){
        return denominator;
    }

    /** Gets the whole value
     * @return returns the whole value
     */
    int getWhole(){
        return numerator / denominator;
    }

    /** Sets the numerator to a new value
     * @param numeratorVal represents new numerator value
     */
    void setNumerator(int numeratorVal){
        numerator = numeratorVal;
    }

    /** Sets the denominator to a new value
     * @param denominatorVal represents new denominator value
     * @throws IllegalArgumentException throws the exceotion if the new denominator is 0
     */
    void setDenominator(int denominatorVal) throws IllegalArgumentException{
        if (denominatorVal == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        denominator = denominatorVal;
    }
    
    /** Checks if a Fraction is equal to another Fraction
     * @param other represents another Fraction
     * @return boolean 
     */
    boolean equals(Fraction other){
        return numerator == other.numerator && denominator == other.denominator;
    }

    
    /** Returns the String value of a Fraction formatted to be read as a fraction
     * @return String version of the Fraction
     */
    public String toString(){
        if (numerator % denominator == 0){
            return String.valueOf(getWhole());
        }
        else if (Math.abs(numerator) < Math.abs(denominator)){
            return String.format("%d/%d", numerator, denominator);
        }
        int whole = getWhole();
        int newNum = Math.abs(numerator) % Math.abs(denominator);
        return String.format("%d %d/%d", whole, newNum, denominator);
    }

    
    /** Clones a Fraction into a new Fraction object
     * @return Fraction with identical values to the Fraction it was called on
     */
    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }

    /** Checks if the numerator is less than zero
     * @return boolean true if less than zero
     */
    boolean lessThanZero(){
        return numerator < 0;
    }

    /** Checks if the numerator is greater than zero
     * @return boolean true if greater than zero
     */
    boolean greaterThanZero(){
        return numerator > 0;
    }

    /** Adds two fractions together
     * @param other Represents second Fraction
     * @return Returns the result of adding two Fractions
     */
    Fraction add(Fraction other){
        //a/b + c/d
        //= (a * d + b * c) / b * d
        int commonDenominator = denominator * other.denominator; 
        int numeratorOfResult = numerator * other.denominator + other.numerator * denominator;
        return new Fraction(numeratorOfResult, commonDenominator);
    }

    /** Subtracts one Fraction from another
     * @param other Represents second Fraction
     * @return Returns the result of subtracting one Fraction from the other
     */
    Fraction subtract(Fraction other){
        int commonDenominator = denominator * other.denominator; 
        int numeratorOfResult = numerator * other.denominator - other.numerator * denominator;
        return new Fraction(numeratorOfResult, commonDenominator);
    }

    /** Multiplies one Fraction with second Fraction
     * @param other Represents second Fraction
     * @return Returns the result of multiplying two Fractions together
     */
    Fraction multiply(Fraction other){
        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    /** Divides one Fraction by a second Fraction
     * @param other Represents second Fraction
     * @return Returns the result of dividing one Fraction by another
     * @throws IllegalArgumentException throws the exception if the denominator of the other fraction is 0
     */
    Fraction divide(Fraction other) throws IllegalArgumentException{
        if (other.numerator == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return this.multiply(new Fraction(other.denominator, other.numerator));
    }

    static String fractionRegex(String type){
        String mixedFractionRegex = "-{0,1}[1-9][0-9]* [1-9][0-9]*\\/-{0,1}[1-9][0-9]*";
        String wholeFractionRegex = "-{0,1}[0-9]+";
        String improperFractionRegex = "-{0,1}[0-9]+\\/-{0,1}[1-9][0-9]*";
        String decimalRegex = "-{0,1}[0-9]+\\.[0-9]+";
        if (type.equals("mixed")){
            return "-{0,1}[1-9][0-9]* [1-9][0-9]*\\/-{0,1}[1-9][0-9]*";
        }
        else if (type.equals("whole")){
            return "-{0,1}[0-9]+";
        }
        else if (type.equals("improper")){
            return "-{0,1}[0-9]+\\/-{0,1}[1-9][0-9]*";
        }
        else if (type.equals("decimal")){
            return "-{0,1}[0-9]+\\.[0-9]+";
        }
        else{
            return String.format("%s || %s || %s || %s", mixedFractionRegex, wholeFractionRegex, improperFractionRegex, decimalRegex);
        }
    }

    
    /** Returns the Fraction value of the expression whether the expression is in mixed, proper, improper, whole number or decimal number form
     * @param expression Represents the String that is being converted to a fraction 
     * @return Returns a Fraction object with the numerator and denominator values taken from the expression
     * @throws IllegalArgumentException throws the exception if the expression does not match any of the acceptable formats for the String version of the fraction
     */
    static Fraction valueOf(String expression) throws IllegalArgumentException{
        if (expression.matches(fractionRegex("mixed"))){
            int whole = Integer.valueOf(expression.split(" ")[0]);
            int numerator = Integer.valueOf((expression.split(" ")[1]).split("/")[0]);
            int denominator = Integer.valueOf((expression.split(" ")[1]).split("/")[1]);
            return new Fraction(whole, numerator, denominator);
        }
        else if (expression.matches(fractionRegex("whole"))){
            return new Fraction(Integer.valueOf(expression));
        }
        else if (expression.matches(fractionRegex("improper"))){
            int numerator = Integer.valueOf(expression.split("/")[0]);
            int denominator = Integer.valueOf(expression.split("/")[1]);
            return new Fraction(numerator, denominator);
        }
        else if (expression.matches(fractionRegex("decimal"))){
            return new Fraction(Double.valueOf(expression));
        }
        else {
            throw new IllegalArgumentException("This cannot be a fraction");
        }
    }
    
    public static void main(String[] args) {
    }
}




