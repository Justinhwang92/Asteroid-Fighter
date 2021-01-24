package com.example.myfirstapp;

import org.junit.Test;

import ASTEROID_FIGHTER_FREE.Game.Game_MathProblems;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testQuestion(){
        Game_MathProblems mt = new Game_MathProblems(false);



//        System.out.println(sq);
//        System.out.println("Options are ");
//        System.out.println(sq.getListofOption());

//        MathProblems.SqProb sqP = new MathProblems.SqProb(false);
//        System.out.println(sqP);
//
//        MathProblems.AlgProb Alg = new MathProblems.AlgProb(false);
//        System.out.println(Alg);
//        System.out.println(Alg.getWrongs());
//
//        MathProblems.IneqProb Inq = new MathProblems.IneqProb(false);
//        System.out.println(Inq);
//        System.out.println(Inq.getWrongs());



//
//        for (int i = 0; i < 100; i++) {
//            Game_MathProblems.trigProb tp = new Game_MathProblems.trigProb(false);
//            System.out.println(tp.toString() + '\n' + "Answer: " + tp.getAnswer());
//            System.out.println("Wrongs: " + tp.getWrongs().toString());
//        }


        for(int i=0; i<5;i++){
//            System.out.println(mt.getEasyQuestions());
//            System.out.println(mt.getEasyAnswers());
//
//            System.out.println(mt.getMediumQuestions());
//            System.out.println(mt.getMediumAnswers());
//
            System.out.println(mt.getHardQuestions());
            System.out.println(mt.getHardAnswers());

//            System.out.println(mt.getExtrHardQuestions());
//            System.out.println(mt.getExtrHardAnswers());
        }


    }
}