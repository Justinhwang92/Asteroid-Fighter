package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Generates math problems questions and Answers
 */
public class MathProblems {

    /**
     * Boolean flag for boss stage
     */
    private boolean myBoss;
    /**
     * Random class object.
     */
    private static Random myRandom;

    /**
     * List of arithmetic operands as characters.
     */
    List<Character> listOfOperator;

    /**
     * BasicsOps class object for easy questions part.
     */
    BasicOps myBasicOpsObject;

    /**
     * Set of Integers to store 4 choices of answers.
     */
    Set<Integer> mySet;

    /**
     * Base math problem class contructor - contains different classes
     * @param theBoss flag to notify if it is boss stage.
     */
    public MathProblems(boolean theBoss)
    {
        myBoss = theBoss;
        myRandom = new Random();
        mySet = new HashSet<>();
        listOfOperator = new ArrayList<>();
        listOfOperator.add('+');
        listOfOperator.add('-');
        listOfOperator.add('*');
        listOfOperator.add('/');
    }

    /**
     * void method to set make boss stage on.
     * @param theState boolean value, true if boss stage
     */
    public void setBoss(boolean theState)
    {
        myBoss = theState;
    }

    /**
     * To get easy question - 1 digit arithmetic
     * @return String representation of the question
     */
    public String getEasyQuestions() {

        Collections.shuffle(listOfOperator);
        char operator = (listOfOperator).get(0);

        //Basic ops
        myBasicOpsObject = new BasicOps(operator, 1, false);

        return myBasicOpsObject.toString();
    }

    /**
     * To get 4 choices of the answers.
     * @return Set<Integer> containing 4 different choice to be displayed.
     */
    public Set<Integer> getEasyAnswers(){
        mySet = new HashSet<>();
        mySet = myBasicOpsObject.getWrongs();
        mySet.add(myBasicOpsObject.mySolution);
        return (mySet);
    }

    /**
     * To get medium question - 2 digit arithmetic
     * @return String representation of the question.
     */
    public String getMediumQuestions() {

        Collections.shuffle(listOfOperator);
        char operator = (listOfOperator).get(0);

        //Basic ops
        myBasicOpsObject = new BasicOps(operator, 2, false);

        return myBasicOpsObject.toString();
    }

    /**
     * To get 4 choices of the answers.
     * @return Set<Integer> containing 4 different choice to be displayed.
     */
    public Set<Integer> getMediumAnswers(){
        mySet = new HashSet<>();
        mySet = myBasicOpsObject.getWrongs();
        mySet.add(myBasicOpsObject.mySolution);
        return (mySet);
    }

    /**
     * To get hard question - Problems related to square, square root, inequality, algebra
     * @return String representation of the question.
     */
    public String getHardQuestions(){

        String theS = "";
        int c = myRandom.nextInt(3)+1;

        switch (c){

            //for square problem
            case 1:
                SqProb sqr = new SqProb(false);
                theS = sqr.toString();
                mySet = new HashSet<>();
                mySet = sqr.getWrongs();
                mySet.add(sqr.mySolution);
                break;

            //For square root problem
            case 2:
                SqrtProb sqrRt = new SqrtProb(false);
                theS = sqrRt.toString();
                mySet = new HashSet<>();
                mySet = sqrRt.getWrongs();
                mySet.add(sqrRt.mySolution);
                break;

            //For Inequality part
            case 3:
                IneqProb inqO = new IneqProb(false);
                theS = inqO.toString();
                mySet = new HashSet<>();
                mySet = inqO.getWrongs();
                mySet.add(inqO.mySolution);
                break;

            //For algebra part
            case 4:
                AlgProb algO = new AlgProb(false);
                theS = algO.toString();
                mySet = new HashSet<>();
                mySet = algO.getWrongs();
                mySet.add(algO.mySolution);
                break;

            default:
                break;
        }

        return theS;
    }

    /**
     * To get 4 choices of the answers.
     * @return Set<Integer> containing 4 different choice to be displayed.
     */
    public Set<Integer> getHardQueAnswers(){
        return (mySet);
    }

    /**
     * Class for basic arithmetic operation - 1 digit and 2 digits
     * Extends MathProblem class
     */
    public static class BasicOps extends MathProblems
    {
        /**
         * character variable for operator
         */
        private char myOp;

        /**
         * private int field for first operand
         */
        private int myOperand1;

        /**
         * private int field for second operand
         */
        private int myOperand2;

        /**
         * private int to store correct solution
         */
        private int mySolution;

        /**
         * Set of Integer to store wrong choices for the question.
         */
        private Set<Integer> myWrongs;

        /**
         * Contructor for basic operation class
         * @param theOp character arithmetic operator (+,-, *, /)
         * @param theDigits the number of digits (1 or 2) for operands
         * @param theBoss boolean flag to notify it its boss stage
         */
        public BasicOps(char theOp, int theDigits, boolean theBoss)
        {
            super(theBoss);
            char op = theOp;
            if(theOp == 'x' || theOp == '*')
            {
                op = 'X';
            }

            myOp = op;
            initializeOperands(theDigits, theBoss);
            initializeSolutions(myOp);
        }

        /**
         * method to initialize the basic operation (easy/medium) question
         * @param theDigits the number of digits (1 or 2) for operands
         * @param theBoss boolean flag to notify it its boss stage
         */
        private void initializeOperands(int theDigits, boolean theBoss)
        {

            switch(theDigits)
            {

                case 1:
                    myOperand1 = myRandom.nextInt(9)+1;
                    myOperand2 = myRandom.nextInt(9)+1;
                    break;

                case 2:
                    myOperand1 = myRandom.nextInt(99)+1;
                    myOperand2 = myRandom.nextInt(99)+1;
                    break;

                default:

                    try
                    {
                        throw new Exception();
                    }

                    catch(Exception e)
                    {
                        System.out.println(e + "\nInvalid amount of digits in "
                                + "math problem");
                    }
                    break;
            }

        }

        /**
         * Method to initialize the solution and the other wrong choices for the question
         * @param theOp the character variable for the operator (+,-,*,/)
         */
        private void initializeSolutions(char theOp)
        {
            myWrongs = new HashSet<>();

            switch(theOp)
            {
                case '+':
                    mySolution = myOperand1 + myOperand2;
                    while(myWrongs.size() < 3)
                    {
                        int nextWrong = myRandom.nextInt(17) + 2;
                        if(nextWrong != mySolution)
                        {
                            myWrongs.add(nextWrong);
                        }
                    }
                    break;

                case '-':
                    mySolution = myOperand1 - myOperand2;
                    boolean negative = myOperand1 < myOperand2;
                    while(myWrongs.size() < 3)
                    {
                        int nextWrong = myRandom.nextInt(17) + 2;
                        if(negative)
                        {
                            nextWrong *= -1;
                        }
                        if(nextWrong != mySolution)
                        {
                            myWrongs.add(nextWrong);
                        }
                    }
                    break;

                case 'X':
                    mySolution = myOperand1 * myOperand2;
                    while(myWrongs.size() < 3)
                    {
                        int nextWrong = myRandom.nextInt(82);
                        if(nextWrong != mySolution)
                        {
                            myWrongs.add(nextWrong);
                        }
                    }
                    break;

                case '/':
                    //whole numbers
//                    int divOp2 = myRandom.nextInt(20) + 1;
//                    while(myOperand1 % divOp2 != 0)
//                    {
//                        divOp2 = myRandom.nextInt(20) + 1;
//                    }
                    myOperand1 = myOperand2*myOperand1;
                    mySolution = myOperand1 / myOperand2;

                    while(myWrongs.size() < 3)
                    {
                        int nextWrong = myRandom.nextInt(20) + 1;
                        if(nextWrong != mySolution)
                        {
                            myWrongs.add(nextWrong);
                        }
                    }
                    break;

                default:
                    try
                    {
                        throw new Exception();
                    }

                    catch(Exception e)
                    {
                        System.out.println(e + "\nInvalid operation in "
                                + "math problem");
                    }

            }

        }

        /**
         * Method to get correct solution for the question
         * @return int right solution
         */
        public int getSolution()
        {
            return mySolution;
        }

        /**
         * Method to get set of wrong answer choices for the questions.
         * @return set<Integer> containing incorrect answers
         */
        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        /**
         * To get string representation of the question.
         * @return String representing the question
         */
        @Override
        public String toString()
        {
            return myOperand1 + " " + myOp + " " + myOperand2 + " = ?";
        }

    }


    /**
     * Class for the questions related to square of a number
     * Extends MathProblem class
     */
    public static class SqProb extends MathProblems
    {
        /**
         * int variable for the operands of the square problem.
         */
        private int myOperand;

        /**
         * private int to store correct solution
         */
        private int mySolution;

        /**
         * Set of Integer to store wrong choices for the question.
         */
        private Set<Integer> myWrongs;

        /**
         * Constructor for SquareProblem class
         * @param theBoss boolean flag to notify it its boss stage
         */
        public SqProb(boolean theBoss)
        {
            super(theBoss);
            myOperand = myRandom.nextInt(9) + 1;
            initializeSolutions();
        }

        /**
         * Method to initialize the solution and the other wrong choices for the question.
         */
        private void initializeSolutions()
        {
            mySolution = (int)Math.pow(myOperand, 2);
            myWrongs = new HashSet<>();
            while(myWrongs.size() < 3)
            {
                int nextWrong = myRandom.nextInt(82);
                if(nextWrong != mySolution)
                {
                    myWrongs.add(nextWrong);
                }
            }
        }

        /**
         * Method to get correct solution for the question
         * @return int right solution
         */
        public int getSolution()
        {
            return mySolution;
        }

        /**
         * Method to get set of wrong answer choices for the questions.
         * @return set<Integer> containing incorrect answers
         */
        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        /**
         * To get string representation of the question.
         * @return String representing the question
         */
        @Override
        public String toString()
        {
            return myOperand + "² = ?";
        }
    }

    /**
     * Class for the questions related to square root of a number
     * Extends MathProblem class
     */
    public static class SqrtProb extends MathProblems
    {
        //whole numbers
        //unicode for square root function \u221A

        /**
         * int variable for the operands of the square problem.
         */
        private final int myOperand;
        /**
         * private int to store correct solution
         */
        private int mySolution;
        /**
         * Set of Integer to store wrong choices for the question.
         */
        private Set<Integer> myWrongs;

        /**
         * Constructor for SquareRootProblem class
         * @param theBoss boolean flag to notify it its boss stage
         */
        public SqrtProb(boolean theBoss)
        {
            super(theBoss);
            myOperand = (int) Math.pow(myRandom.nextInt(19) + 1, 2) ;
            initializeSolutions();
        }

        /**
         * Method to initialize the solution and the other wrong choices for the question.
         */
        private void initializeSolutions()
        {
            mySolution = (int)Math.sqrt(myOperand);
            myWrongs = new HashSet<>();

            while(myWrongs.size() < 3)
            {
                int nextWrong = myRandom.nextInt(mySolution + 5) + 1;
                if(nextWrong != mySolution)
                {
                    myWrongs.add(nextWrong);
                }
            }

        }

        /**
         * Method to get set of wrong answer choices for the questions.
         * @return set<Integer> containing incorrect answers
         */
        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        /**
         * Method to get correct solution for the question
         * @return int right solution
         */
        public int getSolution()
        {
            return mySolution;
        }

        /**
         * To get string representation of the question.
         * @return String representing the question
         */
        @Override
        public String toString()
        {
            return "\u221A" + myOperand + " = ?";
        }
    }

    /**
     * Class for the questions related to Inequality of x
     * Extends MathProblem class
     * General format is : Ax + B > C or Ax + B < C
     */
    public static class IneqProb extends MathProblems
    {
        /**
         * private int to store correct solution
         */
        private int mySolution;
        /**
         * Set of Integer to store wrong choices for the question.
         */
        private Set<Integer> myWrongs;
        /**
         * Int variable for the x coefficient (A)
         */
        private final int myXCoefficient;
        /**
         * int variable for left hand side constant (B)
         */
        private int myLeftConstant;
        /**
         * int variable for right hand side constant (C)
         */
        private int myRightConstant;
        /**
         * Char variable to store the conditional operator (<, >)
         */
        private char myConditionalOperator;

        /**
         * Constructor for Inequality problem class
         * @param theBoss boolean flag to notify it its boss stage
         */
        public IneqProb(boolean theBoss){
            super(theBoss);
            myXCoefficient = myRandom.nextInt(9) + 1;
            myLeftConstant = myRandom.nextInt(5) + 1;
            myRightConstant = (myRandom.nextInt(myXCoefficient)+ 1 ) * myXCoefficient + myLeftConstant;

            //Conditional operator decision
            if(myRightConstant % 2 == 0)
                myConditionalOperator = '<';
            else
                myConditionalOperator = '>';

            initializeSolutions();
        }

        /**
         * Method to initialize the solution and the other wrong choices for the question.
         */
        private void initializeSolutions()
        {
            mySolution = (myRightConstant - myLeftConstant)/ myXCoefficient;
            myWrongs = new HashSet<>();

            while(myWrongs.size() < 3)
            {
                int nextWrong = myRandom.nextInt(mySolution + 3) + 1;
                if(nextWrong != mySolution)
                {
                    myWrongs.add(nextWrong);
                }

            }
        }

        /**
         * Method to get set of wrong answer choices for the questions.
         * @return set<Integer> containing incorrect answers
         */
        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        /**
         * Method to get correct solution for the question
         * @return int right solution
         */
        public int getSolution()
        {
            return mySolution;
        }

        /**
         * To get string representation of the question.
         * @return String representing the question
         */
        @Override
        public String toString(){
            return myXCoefficient +"x + "+ myLeftConstant + " " + myConditionalOperator + " "+ myRightConstant + "; then x "+ myConditionalOperator+ " ?";
        }
    }

    public static class AlgProb extends MathProblems
    {
        /**
         * private int to store correct solution
         */
        private int mySolution;
        /**
         * Set of Integer to store wrong choices for the question.
         */
        private Set<Integer> myWrongs;
        /**
         * Int variable for the x coefficient (A)
         */
        private int myXCoefficient;
        /**
         * Int variable for the left hand side constant (B)
         */
        private int myLeftCoefficient;
        /**
         * Int variable for the Right hand side constant (C)
         */
        private int myRightCoefficient;

        /**
         * Constructor for Algebra problem class
         * @param theBoss boolean flag to notify it its boss stage
         */
        public AlgProb(boolean theBoss){
            super(theBoss);
            myXCoefficient = myRandom.nextInt(9) + 1;
            myLeftCoefficient = myRandom.nextInt(5) + 1;
            myRightCoefficient = (myRandom.nextInt(myXCoefficient) + 1) * myXCoefficient + myLeftCoefficient;
            initializeSolutions();
        }

        /**
         * Method to initialize the solution and the other wrong choices for the question.
         */
        private void initializeSolutions()
        {
            mySolution = (myRightCoefficient - myLeftCoefficient) / myXCoefficient;
            myWrongs = new HashSet<>();

            while(myWrongs.size() < 3)
            {
                int nextWrong = myRandom.nextInt(mySolution+ 3) + 1;
                if(nextWrong != mySolution)
                {
                    myWrongs.add(nextWrong);
                }
            }
        }

        /**
         * Method to get set of wrong answer choices for the questions.
         * @return set<Integer> containing incorrect answers
         */
        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        /**
         * Method to get correct solution for the question
         * @return int right solution
         */
        public int getSolution()
        {
            return mySolution;
        }

        /**
         * To get string representation of the question.
         * @return String representing the question
         */
        @Override
        public String toString()
        {
            return myXCoefficient +"x + "+ myLeftCoefficient +" = "+ myRightCoefficient;
        }
    }

}
