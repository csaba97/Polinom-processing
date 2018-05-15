package test;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.experimental.runners.Enclosed;

import java.util.Arrays;
import java.util.Collection;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.Monom;


@RunWith(Enclosed.class)
public class MonomTest {

	@RunWith(Parameterized.class)
	public static class MakeRepresentationTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {    
	        		//integers
	                 { "+1x^1" , "+x" },
	                 { "1x^1" , "+x" } ,
	                 { "-1x^1" , "-x" },
	                 { "+10x^1" , "+10x" },
	                 { "+112x^1" , "+112x" },
	                 { "-112x^1" , "-112x" },
	                 //reals, 2 precision
	                 { "+1.00x^1" , "+x" },
	                 { "+1.23x^1" , "+1.23x" },
	                 { "-1.22x^1" , "-1.22x" },
	                 { "+1.01x^1" , "+1.01x" },
	                 { "-1.01x^1" , "-1.01x" },
	                 { "+1.99x^1" , "+1.99x" },
	                 { "-1.99x^1" , "-1.99x" },
	                 //reals, 3 precision
	                 { "+1.123x^1" , "+1.12x" },
	                 { "-1.123x^1" , "-1.12x" },	           
	                 //zero
	                 { "0x^0" , "0" },
	                 { "0x^3" , "0" },
	                 { "0x^5" , "0" },
	                 //TESTING THE PARTIAL INPUTS WHERE THE COEFFICIENT OR/AND DEGREE IS MISSING
	                 { "1x^5" , "+x^5" },
	                 { "+1x^5" , "+x^5" },
	                 { "-1x^5" , "-x^5" },
	                 { "-x^5" , "-x^5" },
	                 { "-x^1" , "-x" },
	                 { "-x" , "-x" },
	                 { "+x" , "+x" },
	                 { "x" , "+x" },
	                 { "-x^1" , "-x" },
	                 { "1" , "+1" },
	                 { "+1" , "+1" },
	                 { "0" , "0" },
	                 { "-1" , "-1" },
	                 { "-11" , "-11" },
	                 { "-2x" , "-2x" },
	                 { "-22x" , "-22x" },
	                 { "-2222x" , "-2222x" },
	                 { "-2222.00x" , "-2222x" },
	                 { "-2222.23x" , "-2222.23x" },
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
			Monom a = new Monom(actual);
			assertEquals(expected, a.getRepresentation());
		}
	}

	
	
	@RunWith(Parameterized.class)
	public static class DivTest {
		
	    @Parameters
	    public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	        		 //each one positive
	                 { "+1x^1" , "+1x^1" ,  "+1x^0"  },
	                 { "+10x^1" , "+1x^1" ,  "+10x^0"  },	                 
	                 { "+12x^1" , "+12x^1" ,  "+1x^0"  },
	                 { "+11x^2" , "+1x^1" ,  "+11x^1"  },
	                 { "+112x^2" , "+1x^1" ,  "+112x^1"  },
	                 { "+12x^5" , "+2x^2" ,  "+6x^3"  },
	                 { "+24x^7" , "+4x^3" ,  "+6x^4"  },
	                 { "+24x^8" , "+4x^5" ,  "+6x^3"  },
	                 //1 positive, one negative
	                 { "-1x^1" , "+1x^1" ,  "-1x^0"  },
	                 { "-10x^1" , "+1x^1" ,  "-10x^0"  },                 
	                 { "-12x^1" , "+12x^1" ,  "-1x^0"  },
	                 { "-11x^2" , "+1x^1" ,  "-11x^1"  },
	                 { "-112x^2" , "+1x^1" ,  "-112x^1"  },
	                 { "-12x^5" , "+2x^2" ,  "-6x^3"  },
	                 { "-24x^7" , "+4x^3" ,  "-6x^4"  },
	                 { "-24x^8" , "+4x^5" ,  "-6x^3"  },
	                 //2 negatives
	                 { "-1x^1" , "-1x^1" ,  "+1x^0"  },
	                 { "-10x^1" , "-1x^1" ,  "+10x^0"  },                 
	                 { "-12x^1" , "-12x^1" ,  "+1x^0"  },
	                 { "-11x^2" , "-1x^1" ,  "+11x^1"  },
	                 { "-112x^2" , "-1x^1" ,  "+112x^1"  },
	                 { "-12x^5" , "-2x^2" ,  "+6x^3"  },
	                 { "-24x^7" , "-4x^3" ,  "+6x^4"  },
	                 { "-24x^8" , "-4x^5" ,  "+6x^3"  },
	           });
	    }

		private String actual;
		private String actual2;
		private String expected;

		public DivTest(String act,String act2,String exp) {
			actual = act;
			actual2 = act2;
			expected = exp;
		}

		@Test
		public void testDiv() {
			Monom a = new Monom(actual);
			Monom b=a.div(new Monom(actual2));
			Monom c=new Monom(expected);
			assertEquals( c.getRepresentation(), b.getRepresentation());
		}
	}
	
}
