package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MathProblems {
    boolean myBoss;
    Random myRandom;

    public MathProblems(boolean theBoss)
    {
        myBoss = theBoss;
        myRandom = new Random();
    }


    public void setBoss(boolean theState)
    {
        myBoss = theState;
    }

    private class BasicOps
    {
        private char myOp;
        private int myOperand1;
        private int myOperand2;
        private int mySolution;
        private Set<Integer> myWrongs;
        private int myWrong1;
        private int myWrong2;
        private int myWrong3;

        private BasicOps(char theOp, int theDigits, boolean theBoss)
        {
            char op = theOp;
            if(theOp == 'x' || theOp == '*')
            {
                op = 'X';
            }

            myOp = op;
            initializeOperands(theDigits, theBoss);
            initializeSolutions(myOp, myOperand1, myOperand2, theDigits);
        }

        private void initializeOperands(int theDigits, boolean theBoss)
        {

            switch(theDigits)
            {

                case 1:
                    myOperand1 = myRandom.nextInt(10);
                    break;

                case 2:
                    myOperand2 = myRandom.nextInt(10);
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

        private void initializeSolutions(char theOp,
                                         int theOperand1, int theOperand2, int theDigits)
        {
            myWrongs = new HashSet<Integer>();
            //reduce usage of iterator()
            switch(theOp)
            {
                case '+':
                    mySolution = myOperand1 + myOperand2;
                    while(myWrongs.size() < 3)
                    {
                        myWrongs.add(myRandom.nextInt(17) + 2);
                    }
                    myWrong1 = myWrongs.iterator().next();
                    myWrong2 = myWrongs.iterator().next();
                    myWrong3 = myWrongs.iterator().next();
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
                        myWrongs.add(nextWrong);
                    }
                    myWrong1 = myWrongs.iterator().next();
                    myWrong2 = myWrongs.iterator().next();
                    myWrong3 = myWrongs.iterator().next();
                    break;

                case 'X':
                    mySolution = myOperand1 * myOperand2;
                    while(myWrongs.size() < 3)
                    {
                        myWrongs.add(myRandom.nextInt(82));
                    }
                    myWrong1 = myWrongs.iterator().next();
                    myWrong2 = myWrongs.iterator().next();
                    myWrong3 = myWrongs.iterator().next();
                    break;

                case '/':
                    //need to make it divide cleanly? (or decimals/fractions)
                    int divOp2 = myRandom.nextInt(9) + 1;
                    mySolution = myOperand1 / divOp2;

                    while(myWrongs.size() < 3)
                    {
                        myWrongs.add(myRandom.nextInt(9) + 1);
                    }
                    myWrong1 = myWrongs.iterator().next();
                    myWrong2 = myWrongs.iterator().next();
                    myWrong3 = myWrongs.iterator().next();
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

        @Override
        public String toString()
        {
            return myOperand1 + " " + myOp + " " + myOperand2 + " = ?";
        }

    }


    class SqProb
    {
        private int myNum;
        private int mySolution;
        private Set<Integer> myWrongs;
        private int myWrong1;
        private int myWrong2;
        private int myWrong3;

        public SqProb(boolean theBoss)
        {
            myNum = myRandom.nextInt(9) + 1;
            initializeSolutions(myNum);
        }

        private void initializeSolutions(int theNum)
        {
            mySolution = (int)Math.pow(myNum, 2);
            myWrongs = new HashSet<Integer>();
            while(myWrongs.size() < 3)
            {
                myWrongs.add(myRandom.nextInt(82));
            }
            myWrong1 = myWrongs.iterator().next();
            myWrong2 = myWrongs.iterator().next();
            myWrong3 = myWrongs.iterator().next();
        }

        public String toString()
        {
            //superscript the square?
            return myNum + "² = ?";
        }
    }

    class SqrtProb
    {
        //whole numbers?
        //unicode for square root function \u221A
        private int myNum;
        private int mySolution;
        private List<Integer> myAllOption;

        public SqrtProb(boolean theBoss)
        {
            myNum = (int) Math.pow(myRandom.nextInt(19) + 1, 2) ;
            initializeSolutions(myNum);
        }

        private void initializeSolutions(int theNum)
        {
            mySolution = (int)Math.sqrt(myNum);
            myAllOption = new ArrayList<>();
            myAllOption.add(mySolution);

            while(myAllOption.size() < 4)
            {
                int n = myRandom.nextInt(mySolution+5)+1;
                if(!myAllOption.contains(new Integer(n)))
                    myAllOption.add(n);
            }

            Collections.shuffle(myAllOption);
        }

        public List<Integer> getListofOption(){
            return myAllOption;
        }

        public String toString()
        {
            return "\u221A" + myNum + " = ?";
        }
    }

    class IneqProb
    {
        private int mySolution;
        private List<Integer> myAllOption;
        private String myEquation;
        private int Xcoefficient;
        private int LeftConstant;
        private int rightConstant;

        public IneqProb(boolean theBoss){
            Xcoefficient = myRandom.nextInt(9)+1;
            LeftConstant = myRandom.nextInt(5)+1;
            rightConstant = (myRandom.nextInt(Xcoefficient)+1)*Xcoefficient + LeftConstant;
            initializeSolutions();
        }

        private void initializeSolutions()
        {
            mySolution = (rightConstant-LeftConstant)/Xcoefficient;
            myAllOption = new ArrayList<>();
            myAllOption.add(mySolution);

            while(myAllOption.size() < 4)
            {
                int n = myRandom.nextInt(mySolution+3)+1;
                if(!myAllOption.contains(new Integer(n)))
                    myAllOption.add(n);
            }

            Collections.shuffle(myAllOption);
        }

        public List<Integer> getListofOption(){
            return myAllOption;
        }

        public String toString(){
            myEquation = Xcoefficient+"x + "+LeftConstant+" < "+rightConstant + "; then x < ?";
            return myEquation;
        }
    }

    class AlgProb
    {
        private int mySolution;
        private List<Integer> myAllOption;
        private String myEquation;
        private int Xcoefficient;
        private int LeftConstant;
        private int rightConstant;

        public AlgProb(boolean theBoss){
            Xcoefficient = myRandom.nextInt(9)+1;
            LeftConstant = myRandom.nextInt(5)+1;
            rightConstant = (myRandom.nextInt(Xcoefficient)+1)*Xcoefficient + LeftConstant;
            initializeSolutions();
        }

        private void initializeSolutions()
        {
            mySolution = (rightConstant-LeftConstant)/Xcoefficient;
            myAllOption = new ArrayList<>();
            myAllOption.add(mySolution);

            while(myAllOption.size() < 4)
            {
                int n = myRandom.nextInt(mySolution+3)+1;
                if(!myAllOption.contains(new Integer(n)))
                    myAllOption.add(n);
            }

            Collections.shuffle(myAllOption);
        }

        public List<Integer> getListofOption(){
            return myAllOption;
        }

        public String toString(){
            myEquation = Xcoefficient+"x + "+LeftConstant+" = "+rightConstant;
            return myEquation;
        }
    }

}
