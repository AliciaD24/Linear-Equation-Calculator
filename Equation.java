package DesigningClassesProject;

class Equation {

    private Expression leftSide;
    private Expression rightSide;

    Equation(Expression leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    Equation(String leftSide, String rightSide){
        this.leftSide = Expression.collectLikeTerms(leftSide);
        this.rightSide = Expression.collectLikeTerms(rightSide);
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
}
