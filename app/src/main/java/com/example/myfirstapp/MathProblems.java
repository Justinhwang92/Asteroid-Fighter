package com.example.myfirstapp;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class MathProblems {
    private boolean myBoss;
    private static Random myRandom;

    public MathProblems(boolean theBoss)
    {
        myBoss = theBoss;
        myRandom = new Random();
    }

    public void setBoss(boolean theState)
    {
        myBoss = theState;
    }

    public static class BasicOps extends MathProblems
    {
        private char myOp;
        private int myOperand1;
        private int myOperand2;
        private int mySolution;
        private Set<Integer> myWrongs;

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

        private void initializeOperands(int theDigits, boolean theBoss)
        {

            switch(theDigits)
            {

                case 1:
                    myOperand1 = myRandom.nextInt(10);
                    myOperand2 = myRandom.nextInt(10);
                    break;

                case 2:
                    myOperand1 = myRandom.nextInt(100);
                    myOperand2 = myRandom.nextInt(100);
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
                    int divOp2 = myRandom.nextInt(20) + 1;
                    while(myOperand1 % divOp2 != 0)
                    {
                        divOp2 = myRandom.nextInt(20) + 1;
                    }
                    mySolution = myOperand1 / divOp2;

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

        public int getSolution()
        {
            return mySolution;
        }

        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        public String toString()
        {
            return myOperand1 + " " + myOp + " " + myOperand2 + " = ?";
        }

    }


    public static class SqProb extends MathProblems
    {
        private int myOperand;
        private int mySolution;
        private Set<Integer> myWrongs;

        public SqProb(boolean theBoss)
        {
            super(theBoss);
            myOperand = myRandom.nextInt(9) + 1;
            initializeSolutions();
        }

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

        public int getSolution()
        {
            return mySolution;
        }

        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        @Override
        public String toString()
        {
            return myOperand + "² = ?";
        }
    }

    public static class SqrtProb extends MathProblems
    {
        //whole numbers
        //unicode for square root function \u221A
        private final int myOperand;
        private int mySolution;
        private Set<Integer> myWrongs;

        public SqrtProb(boolean theBoss)
        {
            super(theBoss);
            myOperand = (int) Math.pow(myRandom.nextInt(19) + 1, 2) ;
            initializeSolutions();
        }

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

        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        public int getSolution()
        {
            return mySolution;
        }

        public String toString()
        {
            return "\u221A" + myOperand + " = ?";
        }
    }

    //so far only accepts less than inequalities, must extend to greater than
    public static class IneqProb extends MathProblems
    {
        private int mySolution;
        private Set<Integer> myWrongs;
        private final int myXCoefficient;
        private int myLeftConstant;
        private int myRightConstant;

        public IneqProb(boolean theBoss){
            super(theBoss);
            myXCoefficient = myRandom.nextInt(9) + 1;
            myLeftConstant = myRandom.nextInt(5) + 1;
            myRightConstant = (myRandom.nextInt(myXCoefficient)+ 1 ) * myXCoefficient + myLeftConstant;
            initializeSolutions();
        }

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

        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        public int getSolution()
        {
            return mySolution;
        }

        public String toString(){
            return myXCoefficient +"x + "+ myLeftConstant +" < "+ myRightConstant + "; then x < ?";

        }
    }

    public static class AlgProb extends MathProblems
    {
        private int mySolution;
        private Set<Integer> myWrongs;
        private int myXCoefficient;
        private int myLeftCoefficient;
        private int myRightCoefficient;

        public AlgProb(boolean theBoss){
            super(theBoss);
            myXCoefficient = myRandom.nextInt(9) + 1;
            myLeftCoefficient = myRandom.nextInt(5) + 1;
            myRightCoefficient = (myRandom.nextInt(myXCoefficient) + 1) * myXCoefficient + myLeftCoefficient;
            initializeSolutions();
        }

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

        public Set<Integer> getWrongs()
        {
            return myWrongs;
        }

        public int getSolution()
        {
            return mySolution;
        }

        public String toString()
        {
            return myXCoefficient +"x + "+ myLeftCoefficient +" = "+ myRightCoefficient;
        }
    }

}
