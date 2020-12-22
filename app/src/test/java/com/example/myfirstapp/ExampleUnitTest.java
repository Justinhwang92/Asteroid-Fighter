package com.example.myfirstapp;

import org.junit.Test;

import static org.junit.Assert.*;

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
        MathProblems m = new MathProblems(false);
        MathProblems.SqrtProb sq = m. new SqrtProb(false);

        System.out.println(sq);
//        System.out.println("Options are ");
//        System.out.println(sq.getListofOption());

        MathProblems.SqProb sqP = m. new SqProb(false);
        System.out.println(sqP);

        MathProblems.AlgProb Alg = m. new AlgProb(false);
        System.out.println(Alg);
        System.out.println(Alg.getListofOption());

        MathProblems.IneqProb Inq = m. new IneqProb(false);
        System.out.println(Inq);
        System.out.println(Inq.getListofOption());

    }
}