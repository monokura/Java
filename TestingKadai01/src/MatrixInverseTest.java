import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class MatrixInverseTest {

	@Test
	public void testInverse() {
		long[][][] array = {
				{{1,1},{2,1},{5,1}},
				{{1,1},{-1,1},{1,1}},
				{{0,1},{1,1},{2,1}},
		};
		long[][][] array2 = {
				{{1,1},{0,1},{0,1}},
				{{0,1},{1,1},{0,1}},
				{{0,1},{0,1},{1,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		Matrix answer = Matrix.arrayReader(array2);
		Matrix inverse = mat.inverse();
		Matrix result = mat.multiply(inverse);
		assertThat(result.equals(answer), is(true));
	}

	@Test
	public void testInverse2() {
		long[][][] array = {
				{{3,1},{2,1},{2,1},{-1,1}},
				{{2,1},{1,1},{1,1},{0,1}},
				{{2,1},{2,1},{1,1},{-1,1}},
				{{1,1},{2,1},{1,1},{-1,1}},
		};
		long[][][] array2 = {
				{{1,1},{0,1},{0,1},{0,1}},
				{{0,1},{1,1},{0,1},{0,1}},
				{{0,1},{0,1},{1,1},{0,1}},
				{{0,1},{0,1},{0,1},{1,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		Matrix answer = Matrix.arrayReader(array2);
		Matrix inverse = mat.inverse();
		Matrix result = mat.multiply(inverse);
		assertThat(result.equals(answer), is(true));
	}

	@Test
	public void testInverse3() {
		// 逆行列がない場合
		long[][][] array = {
				{{0,1},{2,1},{0,1}},
				{{1,1},{0,1},{0,1}},
				{{0,1},{1,1},{0,1}},
		};
		Matrix mat = Matrix.arrayReader(array);
		Matrix inverse = mat.inverse();
		assertThat(inverse, is(nullValue()));
	}

}
