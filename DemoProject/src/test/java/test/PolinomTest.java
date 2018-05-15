package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;

import java.util.Arrays;
import java.util.Collection;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import model.Polinom;

@RunWith(Enclosed.class)
public class PolinomTest {

	@RunWith(Parameterized.class)
	public static class MakeRepresentationTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	      //1 monom
	                 { "+1x^1" , "+x" },
	                 { "1x^1" , "+x" } ,
	                 { "-1x^1" , "-x" },
	                 { "+10x^1" , "+10x" },
	                 { "+112x^1" , "+112x" },
	                 { "-112x^1" , "-112x" },             
	                 { "+1.00x^1" , "+x" },
	                 { "+1.23x^1" , "+1.23x" },
	                 { "-1.22x^1" , "-1.22x" },                
	                 { "+1.123x^1" , "+1.12x" },
	                 { "-1.123x^1" , "-1.12x" },                 
	                 { "+1.01x^1" , "+1.01x" },
	                 { "-1.01x^1" , "-1.01x" },
	                 { "+1.99x^1" , "+1.99x" },
	                 { "-1.99x^1" , "-1.99x" },	                 
	                 { "0x^0" , "0" },
	                 { "0x^3" , "0" },
	                 { "0x^3" , "0" },
	                 //multiple monoms-integer and positive
	                 { "0x^0+0x^1+0x^5+0x^7" , "0" },
	                 { "1x^0+2x^1+5x^5+7x^7" , "+1+2x+5x^5+7x^7" },
	                 { "0x^0+11x^1+11x^5+9x^7" , "+11x+11x^5+9x^7" },
	                 { "2x^0+22x^1+55x^5+99x^65" ,"+2+22x+55x^5+99x^65" },
	                 { "0x^0+4x^1+21x^5+54x^11" , "+4x+21x^5+54x^11" },
	                 //multiple monoms-integer, positive and negative
	                 { "-0x^0+0x^1+0x^5-0x^7" , "0" },
	                 { "-1x^0+2x^1+5x^5+7x^7" , "-1+2x+5x^5+7x^7" },
	                 { "0x^0-11x^1+11x^5+9x^7" , "-11x+11x^5+9x^7" },
	                 { "2x^0+22x^1+55x^5-99x^65" ,"+2+22x+55x^5-99x^65" },
	                 { "0x^0-4x^1-21x^5+54x^11" , "-4x-21x^5+54x^11" },
	                 //some real monoms
	                 { "-0.0x^0+0.00x^1+0x^5-0x^7" , "0" },
	                 { "-1.00x^0+2.0x^1+5x^5+7x^7" , "-1+2x+5x^5+7x^7" },
	                 { "-1.23x^0+2x^1+5.55x^5+7x^7" , "-1.23+2x+5.55x^5+7x^7" },
	                 { "-1.2345x^0+2x^1+5.55x^5+7x^7" , "-1.23+2x+5.55x^5+7x^7" },
	                 { "-1.2345x^0+2x^1+5.55166x^5+7x^7" , "-1.23+2x+5.55x^5+7x^7" },
	                 { "-1.2345x^0+2x^1+5.551456x^5+7x^7" , "-1.23+2x+5.55x^5+7x^7" },
	                 { "-1.23456464x^0+2x^1+5.551464646x^5+7x^7" , "-1.23+2x+5.55x^5+7x^7" },
	           });
	    }

		private String actual;
		private String expected;

		public MakeRepresentationTest(String act,String exp) {
			actual = act;
			expected = exp;
		}

		@Test
		public void testGetRepresentation() {
			Polinom a = new Polinom(actual);
			assertEquals(expected, a.getRepresentation());
		}
	}

	
	
	@RunWith(Parameterized.class)
	public static class AddTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	     //only positive, only integer
	                 { "+1x^1" , "+1x^1" ,  "+2x"  },
	                 { "+10x^1" , "+1x^1" ,  "+11x"  },	                 
	                 { "+12x^1" , "+12x^1" ,  "+24x"  },
	                 { "+11x^2" , "+1x^1" ,  "+x+11x^2"  },
	                 { "+112x^2" , "+1x^1" ,  "+x+112x^2"  },
	                 { "+12x^5" , "+2x^2" ,  "+2x^2+12x^5"  },
	                 { "+24x^7" , "+4x^3" ,  "+4x^3+24x^7"  },
	                 { "+24x^8" , "+4x^5" ,  "+4x^5+24x^8"  },
	                 //pos and neg, only integer
	                 { "-1x^1" , "+1x^1" ,  "0"  },
	                 { "-10x^1" , "+1x^1" ,  "-9x^1"  },                 
	                 { "-12x^1" , "+12x^1" ,  "0"  },
	                 { "-11x^2" , "+1x^1" ,  "-11x^2+1x^1"  },
	                 { "-112x^2+12x^4" , "+1x^1+8x^4" ,  "-112x^2+1x^1+20x^4"  },
	                 { "-12x^5" , "+2x^2" ,  "+2x^2-12x^5"  },
	                 { "-24x^7" , "+4x^3" ,  "-24x^7+4x^3"  },
	                 { "-24x^8" , "+4x^5" ,  "-24x^8+4x^5"  },	         
	                 { "-1x^1" , "-1x^1" ,  "-2x^1"  },
	                 { "-10x^1" , "-1x^1" ,  "-11x^1"  },                 
	                 { "-12x^1" , "-12x^1" ,  "-24x^1"  },
	                 { "-11x^2+12x^99" , "-1x^1+1x^99" ,  "-11x^2-1x^1+13x^99"  },
	               //pos and neg, reals
	                 { "-24.00x^7" , "+4.0x^3" ,  "-24x^7+4x^3"  },
	                 { "-24.0x^8" , "+4x^5" ,  "-24x^8+4x^5"  },	         
	                 { "-1.12x^1" , "-1x^1" ,  "-2.12x^1"  },
	                 { "-10.123x^1" , "-1x^1" ,  "-11.12x^1"  },                 
	                 { "-12.01x^1" , "-12.01x^1" ,  "-24.02x^1"  },
	                 { "-11.1x^2" , "-1x^1" ,  "-11.1x^2-1x^1"  },
	           });
	    }

		private String actual;
		private String actual2;
		private String expected;

		public AddTest(String act,String act2,String exp) {
			actual = act;
			actual2 = act2;
			expected = exp;
		}

		@Test
		public void testAdd() {
			Polinom a = new Polinom(actual);
			Polinom  b=a.add(new Polinom(actual2));
			
			Polinom result=new Polinom(expected);
			assertEquals(result.getRepresentation(), b.getRepresentation());
		}
	}
	
	@RunWith(Parameterized.class)
	public static class SubTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	 //only positive, only integer
	                 { "+1x^1" , "+1x^1" ,  "0"  },
	                 { "+10x^1" , "+1x^1" ,  "+9x^1"  },	                 
	                 { "+12x^1" , "+12x^1" ,  "0"  },
	                 { "+11x^2" , "+1x^1" ,  "-1x^1+11x^2"  },
	                 { "+112x^2" , "+1x^1" ,  "-1x^1+112x^2"  },
	                 { "+12x^5" , "+2x^2" ,  "-2x^2+12x^5"  },
	                 { "+24x^7" , "+4x^3" ,  "-4x^3+24x^7"  },
	                 { "+24x^8" , "+4x^5" ,  "-4x^5+24x^8"  },
	                 //pos and neg, only integer
	                 { "-1x^1" , "+1x^1" ,  "-2x^1"  },
	                 { "-10x^1" , "+1x^1" ,  "-11x^1"  },                 
	                 { "-12x^1" , "+12x^1" ,  "-24x^1"  },
	                 { "-11x^2" , "+1x^1" ,  "-11x^2-1x^1"  },
	                 { "-112x^2" , "+1x^1" ,  "-112x^2-1x^1"  },
	                 { "-12x^5" , "+2x^2" ,  "-2x^2-12x^5"  },
	                 { "-24x^7" , "+4x^3" ,  "-24x^7-4x^3"  },
	                 { "-24x^8+4x^5" , "+4x^5" ,  "-24x^8"  },                 
	                 { "-1x^1" , "-1x^1" ,  "0"  },
	                 { "-10x^1" , "-1x^1" ,  "-9x^1"  },                 
	                 { "-12x^1" , "-12x^1" ,  "0"  },
	                 { "-11x^2+12x^3" , "-1x^1" ,  "-11x^2+1x^1+12x^3"  },
	               //pos and neg, reals
	                 { "-24.00x^7" , "+4.0x^3" ,  "-24x^7-4x^3"  },
	                 { "-24.0x^8" , "+4x^5" ,  "-24x^8-4x^5"  },	         
	                 { "-1.12x^1" , "-1x^1" ,  "-0.12x^1"  },
	                 { "-10.123x^1" , "-1x^1" ,  "-9.12x^1"  },                 
	                 { "-12.01x^1" , "-12.01x^1" ,  "0"  },
	           });
	    }

		private String actual;
		private String actual2;
		private String expected;

		public SubTest(String act,String act2,String exp) {
			actual = act;
			actual2 = act2;
			expected = exp;
		}

		@Test
		public void testSubb() {
			Polinom a = new Polinom(actual);
			Polinom  b=a.sub(new Polinom(actual2));
			
			Polinom result=new Polinom(expected);
			assertEquals(result.getRepresentation(), b.getRepresentation());
		}
	}
	
	@RunWith(Parameterized.class)
	public static class MulTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	 //only positive, only integer
	                 { "+1x^1+2x^2" , "+1x^1" ,  "+1x^2+2x^3"  },
	                 { "+10x^1+3x^3+4x^4" , "+1x^1" ,  "+10x^2+3x^4+4x^5"  },	                 
	                 { "+12x^1" , "+12x^1" ,  "+144x^2"  },
	                 { "+11x^2" , "+1x^1" ,  "+11x^3"  },
	                 { "+112x^2" , "+1x^1" ,  "+112x^3"  },
	                 { "+12x^5" , "+2x^2" ,  "+24x^7"  },
	                 { "+24x^7" , "+4x^3" ,  "+96x^10"  },
	                 { "+24x^8" , "+4x^5" ,  "+96x^13"  },
	                 //pos and neg, only integer
	                 { "-1x^1-2x^2" , "+1x^1" ,  "-1x^2-2x^3"  },
	                 { "-10x^1+22x^99+12x^111" , "+1x^1" ,  "-10x^2+22x^100+12x^112"  },                 
	                 { "-12x^1-2x^2" , "+12x^1-5x^3" ,  "-144x^2+60x^4-24x^3+10x^5"  },
	           });
	    }

		private String actual;
		private String actual2;
		private String expected;

		public MulTest(String act,String act2,String exp) {
			actual = act;
			actual2 = act2;
			expected = exp;
		}

		@Test
		public void testMul() {
			Polinom a = new Polinom(actual);
			Polinom  b=a.mul(new Polinom(actual2));
			
			Polinom result=new Polinom(expected);
			assertEquals(result.getRepresentation(), b.getRepresentation());
		}
	}
	
	@RunWith(Parameterized.class)
	public static class IntegrateTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	 //only positive
	                 { "+1x^1+2x^2" ,  "+0.5x^2+0.67x^3"  },
	                 { "+2x^1+6x^2+8x^3+15x^4" ,  "+1x^2+2x^3+2x^4+3x^5"  },
	                 { "+2x^1+6x^2+8x^3+50x^9" ,  "+1x^2+2x^3+2x^4+5x^10"  },
	                 //pos and neg, only integer
	                 { "-1x^1+2x^2" ,  "-0.5x^2+0.67x^3"  },
	                 { "-2x^1+6x^2+8x^3-15x^4" ,  "-1x^2+2x^3+2x^4-3x^5"  },
	                 { "+2x^1+6x^2+8x^3-50x^9" ,  "+1x^2+2x^3+2x^4-5x^10"  },
	           });
	    }

		private String actual;
		private String expected;

		public IntegrateTest(String act,String exp) {
			actual = act;
			expected = exp;
		}

		@Test
		public void testMul() {
			Polinom a = new Polinom(actual);
			
			Polinom result=new Polinom(expected);
			assertEquals(result.getRepresentation(), a.integrate().getRepresentation());
		}
	}
	
	
	@RunWith(Parameterized.class)
	public static class DerivateTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	 //only positive
	                 { "+1x^1+2x^2" ,  "+1x^0+4x^1"  },
	                 { "+2x^1+6x^2+8x^3+15x^4" ,  "+2x^0+12x^1+24x^2+60x^3"  },
	                 { "+2x^1+6x^2+8x^3+50x^9" ,  "+2x^0+12x^1+24x^2+450x^8"  },
	                 //pos and neg, only integer
	                 { "-1x^1+2x^2" , "-1x^0+4x^1"  },
	                 { "-2x^1+6x^2+8x^3-15x^4" ,  "-2x^0+12x^1+24x^2-60x^3"  },
	                 { "+2x^1+6x^2+8x^3-50x^9" ,   "2x^0+12x^1+24x^2-450x^8"  },
	           });
	    }

		private String actual;
		private String expected;

		public DerivateTest(String act,String exp) {
			actual = act;
			expected = exp;
		}

		@Test
		public void testMul() {
			Polinom a = new Polinom(actual);
			
			Polinom result=new Polinom(expected);
			assertEquals(result.getRepresentation(), a.derivate().getRepresentation());
		}
	}
	
	@RunWith(Parameterized.class)
	public static class DivideTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        	 //only positive
	                 { "1x^1","1x^1",     "+1x^0",  "0" },
	                 { "25x^1","1x^1",     "+25x^0",  "0" },
	                 { "25x^1+1x^0","1x^1",     "+25x^0",  "1x^0" },
	                 { "25x^1+11x^0","1x^1",     "+25x^0",  "11x^0" },
	                 { "25x^5","1x^1",     "+25x^4",  "0" },
	                 { "25x^5+99x^0","1x^1",     "+25x^4",  "99x^0" },
	                 { "25x^5+99x^0","25x^5",     "+1x^0",  "99x^0" },
	                 //multiple monoms
	                 { "1x^2+2x^1+1x^0","1x^1+1x^0",     "1x^1+1x^0",  "0" },
	                 { "1x^2+10x^1+25x^0","1x^1+5x^0",     "1x^1+5x^0",  "0" },
	                 { "1x^3-12x^2+38x^1-17x^0","1x^1-7x^0",     "1x^2-5x^1+3x^0",  "4x^0" },
	                 { "1x^3-12x^2+38x^1-17x^0","1x^2+2x^1-7x^0",     "1x^1-14x^0",  "73x^1-115x^0" },
	                 { "2x^6-12x^2+38x^1-17x^0","2x^4+2x^1-7x^0",     "1x^2",  "-2x^3-5x^2+38x^1-17x^0" },
	                 { "2x^4+1x^3-2x^2+15x^1-6x^0","1x^2+2x^1-1x^0",     "2x^2-3x^1+6x^0",  "0" },
	                 //larger divisor
	                 { "25x^5+99x^0","25x^6",     "0",  "25x^5+99x^0"},
	                 { "25x^5+99x^0","25x^6",     "0",  "25x^5+99x^0"},
	                 { "25x^5+99x^0","25x^6",     "0",  "25x^5+99x^0"},
	                 { "25x^5+99x^0+11x^1","25x^9",     "0",  "25x^5+99x^0+11x^1" },
	                 { "25x^5+99x^0+11x^1","1x^6",     "0",  "25x^5+99x^0+11x^1" },
	                 { "25x^5+99x^0+11x^1","2x^7",     "0",  "25x^5+99x^0+11x^1" },
	                 { "25x^5+99x^0+11x^1","3x^8",     "0",  "25x^5+99x^0+11x^1" },
	                 //inverse tests from the multiplications
	                 { "+1x^2+2x^3","+1x^1+2x^2" , "+1x^1" ,   "0" },
	                 { "+10x^2+3x^4+4x^5" ,"+10x^1+3x^3+4x^4" , "+1x^1" , "0"  },	                 
	                 { "+144x^2" ,"+12x^1" , "+12x^1" , "0"  },
	                 { "+11x^3", "+11x^2" , "+1x^1" ,  "0"  },
	                 { "+112x^3" , "+112x^2" , "+1x^1" , "0"  },
	                 { "+96x^10","+24x^7" , "+4x^3" ,  "0"  },
	                 { "+96x^13","+24x^8" , "+4x^5" , "0"   },
	                 { "-1x^2-2x^3","-1x^1-2x^2" , "+1x^1" ,  "0"  },
	                 { "-10x^2+22x^100+12x^112","-10x^1+22x^99+12x^111" , "+1x^1" , "0"   },                 
	                 {  "-144x^2+60x^4-24x^3+10x^5","-12x^1-2x^2" , "+12x^1-5x^3" ,  "0" },
	                 	              
	                 
	           });
	    }

		private String actual;
		private String actual2;
		private String expected_q;
		private String expected_r;
		
		public DivideTest(String act,String act2,String exp_q,String exp_r) {
			actual = act;
			actual2 = act2;
			expected_q = exp_q;
			expected_r = exp_r;
		}

		@Test
		public void testDivide() {
			Polinom a = new Polinom(actual);
			Polinom b = new Polinom(actual2);
			Polinom[] values=a.divide(b);
			
			
			
			Polinom result1=new Polinom(expected_q);
			Polinom result2=new Polinom(expected_r);
			assertEquals(result1.getRepresentation(), values[0].getRepresentation());
			assertEquals(result2.getRepresentation(), values[1].getRepresentation());
		}
	}
	
}
