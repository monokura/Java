import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class LISTest {

	@Test
	public void testSolve() {
		long[][][] array = {
				{{3,1},{8,1}},
				{{2,1},{5,1}},
		};
		int[] c = new int[2];
		c[0] = 2;
		c[1] = 1;


		Rational[] b = new Rational[2];
		b[0] = new Rational(7);
		b[1] = new Rational(5);

		Matrix mat = Matrix.arrayReader(array);
		LIS lis = new LIS(mat, b, c);
		lis.init();

		if(lis.solve() == 0){
			System.out.println("error");
			return;
		}

		Rational[] x = lis.getX();
		Rational[] result = mat.substVector(x);

		for(int i = 0;i < mat.getNRow();i++){
			if(c[i] == 0){
				assertThat(result[i].equals(b[i]), is(true));
			}else if(c[i] == 1){
				boolean flag = result[i].equals(b[i]) | result[i].greaterThan(b[i]);
				assertThat(flag, is(true));
			}else if(c[i] == 2){
				boolean flag = result[i].equals(b[i]) | result[i].lessThan(b[i]);
				assertThat(flag, is(true));
			}
		}
	}
	@Test
	public void testSolve2() {
		long[][][] array = {
				{{1,1},{1,1}},
				{{2,1},{-1,1}},
				{{-1,1},{2,1}},
		};
		int[] c = new int[3];
		c[0] = 1;
		c[1] = 1;
		c[2] = 1;

		Rational[] b = new Rational[3];
		b[0] = new Rational(2);
		b[1] = new Rational(0);
		b[2] = new Rational(1);

		Matrix mat = Matrix.arrayReader(array);
		LIS lis = new LIS(mat, b, c);
		lis.init();

		if(lis.solve() == 0){
			System.out.println("error");
			return;
		}

		Rational[] x = lis.getX();
		Rational[] result = mat.substVector(x);

		for(int i = 0;i < mat.getNRow();i++){
			if(c[i] == 0){
				assertThat(result[i].equals(b[i]), is(true));
			}else if(c[i] == 1){
				boolean flag = result[i].equals(b[i]) | result[i].greaterThan(b[i]);
				assertThat(flag, is(true));
			}else if(c[i] == 2){
				boolean flag = result[i].equals(b[i]) | result[i].lessThan(b[i]);
				assertThat(flag, is(true));
			}
		}
	}
}
