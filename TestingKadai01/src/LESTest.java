import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class LESTest {

	@Test
	public void testSolve1() {
		long[][][] array = {
				{{3,1},{8,1}},
				{{2,1},{5,1}},
		};
		Rational[] b = new Rational[2];
		b[0] = new Rational(7);
		b[1] = new Rational(5);

		Matrix mat = Matrix.arrayReader(array);

		LES les = new LES(mat, b);

		if(les.solve() == 0){
			System.out.println("error");
			return;
		}

		Rational[] x = les.getX();
		Rational[] result = mat.substVector(x);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(b[i]), is(true));
		}
	}
	@Test
	public void testSolve2() {
		long[][][] array = {
				{{1,1},{1,1},{1,1},{2,1},{3,1}},
				{{1,1},{2,1},{2,1},{4,1},{6,1}},
				{{1,1},{-2,1},{-2,1},{0,1},{2,1}},
		};
		Rational[] b = new Rational[3];
		b[0] = new Rational(2);
		b[1] = new Rational(3);
		b[2] = new Rational(4);

		Matrix mat = Matrix.arrayReader(array);

		LES les = new LES(mat, b);

		if(les.solve() == 0){
			System.out.println("error");
			return;
		}

		Rational[] x = les.getX();
		Rational[] result = mat.substVector(x);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(b[i]), is(true));
		}
	}

	@Test
	public void testSolve3() {
		long[][][] array = {
				{{1,1},{1,1},{1,1},{2,1},{3,1}},
				{{2,1},{2,1},{2,1},{4,1},{6,1}},
				{{1,1},{-2,1},{-2,1},{0,1},{2,1}},
		};
		Rational[] b = new Rational[3];
		b[0] = new Rational(2);
		b[1] = new Rational(3);
		b[2] = new Rational(4);

		Matrix mat = Matrix.arrayReader(array);

		LES les = new LES(mat, b);

		assertThat(les.solve(), is(0));
	}

}
