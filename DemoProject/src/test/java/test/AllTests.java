package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.MonomTest.DivTest;
import test.MonomTest.MakeRepresentationTest;
import test.PolinomTest.AddTest;
import test.PolinomTest.DerivateTest;
import test.PolinomTest.DivideTest;
import test.PolinomTest.IntegrateTest;
import test.PolinomTest.MulTest;
import test.PolinomTest.SubTest;

@RunWith(Suite.class)
@SuiteClasses({ AddTest.class, DerivateTest.class, DivideTest.class, DivTest.class, IntegrateTest.class,
		MakeRepresentationTest.class, MakeRepresentationTest.class, MonomTest.class, MulTest.class, PolinomTest.class,
		SubTest.class })
public class AllTests {

}
