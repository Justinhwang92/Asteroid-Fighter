package com.example.myfirstapp;

import java.util.HashSet;
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
     * int answer of a specific question.
     */
    private int myAns;

    /**
     * Random class object.
     */
    private static Random myRandom;

    /**
     * List of arithmetic operands as characters.
     */
    private static final char[] listOfOperator = {'+', '-', 'X', '/'};

    /**
     * BasicsOps class object for easy questions part.
     */
    private BasicOps myBasicOpsObject;

    /**
     * Set of Integers to store 4 choices of answers.
     */
    private Set<Integer> mySet;

    /**
     * number of types of questions for hard questions:
     * currently includes squares, square roots, inequalities, and algebra problems
     */
    public final int NUM_OF_HARD_QUESTION_TYPES = 4;

    /**
     * number of wrong choices a question can have
     */
    public final int NUM_OF_WRONG_ANSWERS = 3;

    /**
     * Base math problem class constructor - contains different classes
     * @param theBoss flag to notify if it is boss stage.
     */
    public MathProblems(boolean theBoss)
    {
        myBoss = theBoss;
        myRandom = new Random();
        mySet = new HashSet<>();
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
     * gets a random 1 digit number
     * @return a number from 1 to 9 inclusive
     */
    public int getRandOneDigitNum()
    {
        return myRandom.nextInt(9) + 1;
    }

    /**
     * gets a random 2 digit number
     * @return a number from 10 to 99 inclusive
     */
    public int getRandTwoDigitNum()
    {
        return myRandom.nextInt(89) + 10;
    }

    /**
     * method to get the answer of a specific question
     * @return int the answer
     */
    public int getAnswer(){  return myAns; }

    /**
     * To get easy question - 1 digit arithmetic
     * @return String representation of the question
     */
    public String getEasyQuestions() {

        char operator = listOfOperator[myRandom.nextInt(listOfOperator.length)];

        //Basic ops
        myBasicOpsObject = new BasicOps(operator, 1, false);

        return myBasicOpsObject.toString();
    }

    /**
     * To get 4 choices of the answers.
     * @return Set<Integer> containing 4 different choice to be displayed.
     */
    public Set<Integer> getEasyAnswers(){
        mySet = new HashSet<>(myBasicOpsObject.getWrongs());
        myAns = myBasicOpsObject.mySolution;
        mySet.add(myAns);
        return (mySet);
    }

    /**
     * To get medium question - 2 digit arithmetic
     * @return String representation of the question.
     */
    public String getMediumQuestions() {
        //nextInt(2) gets addition or subtraction
        char operator = listOfOperator[myRandom.nextInt(2)];

        //Basic ops
        myBasicOpsObject = new BasicOps(operator, 2, false);

        return myBasicOpsObject.toString();
    }

    /**
     * To get 4 choices of the answers.
     * @return Set<Integer> containing 4 different choice to be displayed.
     */
    public Set<Integer> getMediumAnswers(){
        mySet = new HashSet<>(myBasicOpsObject.getWrongs());
        myAns = myBasicOpsObject.mySolution;
        mySet.add(myAns);
        return (mySet);
    }

    /**
     * To get hard question - Problems related to square, square root, inequality, algebra
     * @return String representation of the question.
     */
    public String getHardQuestions(){

        String theS = "";

        int randomChoice = myRandom.nextInt(NUM_OF_HARD_QUESTION_TYPES);

        switch (randomChoice){

            //for square problem
            case 0:
                SqProb sqr = new SqProb(false);
                theS = sqr.toString();
                mySet = new HashSet<>();
                mySet = sqr.getWrongs();
                myAns = sqr.mySolution;
                mySet.add(myAns);
                break;

            //For square root problem
            case 1:
                SqrtProb sqrRt = new SqrtProb(false);
                theS = sqrRt.toString();
                mySet = new HashSet<>();
                mySet = sqrRt.getWrongs();
                myAns = sqrRt.mySolution;
                mySet.add(myAns);
//                mySet.add(sqrRt.mySolution);
                break;

            //For Inequality part
            case 2:
                IneqProb inqO = new IneqProb(false);
                theS = inqO.toString();
                mySet = new HashSet<>();
                mySet = inqO.getWrongs();
                myAns = inqO.mySolution;
                mySet.add(myAns);
//                mySet.add(inqO.mySolution);
                break;

            //For algebra part
            case 3:
                AlgProb algO = new AlgProb(false);
                theS = algO.toString();
                mySet = new HashSet<>();
                mySet = algO.getWrongs();
                myAns = algO.mySolution;
                mySet.add(myAns);
//                mySet.add(algO.mySolution);
                break;

            default:
                try
                {
                    throw new Exception();
                }
                catch(Exception e)
                {
                    System.out.println(e + "\nInvalid hard question");
                }
                break;
        }

        return theS;
    }

    /**
     * To get 4 choices of the answers.
     * @return Set<Integer> containing 4 different choice to be displayed.
     */
    public Set<Integer> getHardAnswers(){
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
            initializeSolutions(myOp, theDigits);
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
                    myOperand1 = getRandOneDigitNum();
                    myOperand2 = getRandOneDigitNum();
                    break;

                case 2:
                    myOperand1 = getRandTwoDigitNum();
                    myOperand2 = getRandTwoDigitNum();
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
        private void initializeSolutions(char theOp, int theDigits)
        {
            myWrongs = new HashSet<>();

            switch(theOp)
            {
                case '+':
                    mySolution = myOperand1 + myOperand2;
                    while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
                    {
                        //nextInt(17) + 2 yields the range of possible sums of 2 one digit numbers
                        int nextWrong;
                        if(theDigits == 1)
                        {
                            nextWrong = myRandom.nextInt(17) + 2;
                        }
                        //2 digit case yields all possible 2 digit sums
                        else
                        {
                            nextWrong = myRandom.nextInt(178) + 20;
                        }

                        if(nextWrong != mySolution)
                        {
                            myWrongs.add(nextWrong);
                        }
                    }
                    break;

                case '-':
                    mySolution = myOperand1 - myOperand2;
                    boolean negative = myOperand1 < myOperand2;
                    while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
                    {
                        //we do the same random numbers with subtraction
                        //(subtraction is just addition with negatives)
                        //but it could be negative too
                        //so the negative conditional takes care of that
                        //nextInt(17) + 2 yields the range of possible sums of 2 one digit numbers
                        int nextWrong;
                        if(theDigits == 1)
                        {
                            nextWrong = myRandom.nextInt(17) + 2;
                        }
                        //2 digit case yields all possible 2 digit sums
                        else
                        {
                            nextWrong = myRandom.nextInt(178) + 20;
                        }
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
                    while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
                    {
                        //nextInt(82) yields 0 to 81
                        //which is the range of 1 digit times 1 digit products
                        int nextWrong = myRandom.nextInt(82);
                        if(nextWrong != mySolution)
                        {
                            myWrongs.add(nextWrong);
                        }
                    }
                    break;

                case '/':
                    //will yield whole numbers
                    myOperand1 = myOperand2 * myOperand1;
                    mySolution = myOperand1 / myOperand2;

                    while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
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
            myOperand = getRandOneDigitNum();
            initializeSolutions();
        }

        /**
         * Method to initialize the solution and the other wrong choices for the question.
         */
        private void initializeSolutions()
        {
            mySolution = (int)Math.pow(myOperand, 2);
            myWrongs = new HashSet<>();
            while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
            {
                //we restrict highest answer to be 81 because we only include 1 digit squares
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
            myOperand = (int) Math.pow(myRandom.nextInt(21), 2) ;
            initializeSolutions();
        }

        /**
         * Method to initialize the solution and the other wrong choices for the question.
         */
        private void initializeSolutions()
        {
            mySolution = (int)Math.sqrt(myOperand);
            myWrongs = new HashSet<>();

            while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
            {
                //we set upper bound to 21 to have square roots between 0 and 20
                int nextWrong = myRandom.nextInt(21);
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
            myXCoefficient = getRandOneDigitNum();
            //random number from 1 to 5 inclusive
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

            while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
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
            myXCoefficient = getRandOneDigitNum();
            //random number from 1 to 5 inclusive
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

            while(myWrongs.size() < NUM_OF_WRONG_ANSWERS)
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
        public String toString()
        {
            return myXCoefficient +"x + "+ myLeftCoefficient +" = "+ myRightCoefficient + ", then x = ?";
        }
    }

}
