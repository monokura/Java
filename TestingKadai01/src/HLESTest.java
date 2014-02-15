import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class HLESTest {

	@Test
	public void testSolve() {
		long[][][] array = {
				{{0,1},{1,1},{1,1},{2,1},{3,1}},
				{{0,1},{2,1},{2,1},{4,1},{6,1}},
				{{0,1},{-2,1},{-2,1},{0,1},{2,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		HLES hles = new HLES(mat);
		hles.solve();

		Rational[] x = hles.getX();
		Rational[] result = mat.substVector(x);
		Rational zero = new Rational(0);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(zero), is(true));
		}
	}

	@Test
	public void testSolve2() {
		long[][][] array = {
				{{0,1},{1,1},{1,1},{2,1},{3,1},{0,1},{0,1}},
				{{0,1},{2,1},{0,1},{6,1},{-7,1},{0,1},{9,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		HLES hles = new HLES(mat);
		hles.solve();

		Rational[] x = hles.getX();
		Rational[] result = mat.substVector(x);
		Rational zero = new Rational(0);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(zero), is(true));
		}
	}

	@Test
	public void testSolve3() {
		long[][][] array = {
				{{0,1},{1,1},{1,1},{2,1},{3,1},{0,1},{0,1}},
				{{0,1},{2,1},{0,1},{6,1},{-7,1},{0,1},{9,1}},
				{{0,1},{1,1},{5,1},{1,1},{9,1},{0,1},{5,1}},
				{{0,1},{-8,1},{2,1},{2,1},{4,1},{0,1},{7,1}},
				{{0,1},{15,1},{12,1},{3,1},{1,1},{0,1},{10,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		HLES hles = new HLES(mat);
		hles.solve();

		Rational[] x = hles.getX();
		Rational[] result = mat.substVector(x);
		Rational zero = new Rational(0);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(zero), is(true));
		}
	}

	@Test
	public void testSolve4() {
		long[][][] array = {
				{{0,1},{1,1},{1,1}},
				{{0,1},{2,1},{0,1}},
				{{0,1},{1,1},{5,1}},
				{{0,1},{-8,1},{2,1}},
				{{0,1},{15,1},{12,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		HLES hles = new HLES(mat);
		hles.solve();

		Rational[] x = hles.getX();
		Rational[] result = mat.substVector(x);
		Rational zero = new Rational(0);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(zero), is(true));
		}
	}

	@Test
	public void testSolve5() {
		long[][][] array = {
				{{0,1},{0,1},{0,1}},
				{{0,1},{0,1},{0,1}},
				{{0,1},{0,1},{0,1}},
				{{0,1},{0,1},{0,1}},
				{{0,1},{0,1},{0,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		HLES hles = new HLES(mat);
		hles.solve();

		Rational[] x = hles.getX();
		Rational[] result = mat.substVector(x);
		Rational zero = new Rational(0);

		for(int i = 0;i < result.length;i++){
			assertThat(result[i].equals(zero), is(true));
		}
	}
}
