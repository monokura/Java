import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class MatrixTest {
	// ======= 行列生成 =======
	// テストのときに使いまわす行列
	private Matrix createMat1(){
		/**
		 * 2*2の正方行列
		 * |1 2|
		 * |3 4|
		 */
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(2);
		Rational r3 = new Rational(3);
		Rational r4 = new Rational(4);

		Rational[][] elem = {
			{r1, r2},
			{r3, r4},
		};

		return new Matrix(elem);
	}

	private Matrix createMat2(){
		/**
		 * 2*2の正方行列
		 * | 1 -1|
		 * |-2  3|
		 */
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(-1);
		Rational r3 = new Rational(-2);
		Rational r4 = new Rational(3);

		Rational[][] elem = {
			{r1, r2},
			{r3, r4},
		};

		return new Matrix(elem);
	}

	private Matrix createMat3(){
		/**
		 * 3*2の正方行列
		 * | 1  2|
		 * | 0 -1|
		 * | 5  1|
		 */
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(2);
		Rational r3 = new Rational(0);
		Rational r4 = new Rational(-1);
		Rational r5 = new Rational(5);
		Rational r6 = new Rational(1);

		Rational[][] elem = {
			{r1, r2},
			{r3, r4},
			{r5, r6},
		};

		return new Matrix(elem);
	}

	private Matrix createMat4(){
		/**
		 * 2*4の正方行列
		 * | 1 -2  4  0|
		 * | 0 -1  2  3|
		 */
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(-2);
		Rational r3 = new Rational(4);
		Rational r4 = new Rational(0);
		Rational r5 = new Rational(0);
		Rational r6 = new Rational(-1);
		Rational r7 = new Rational(2);
		Rational r8 = new Rational(3);

		Rational[][] elem = {
			{r1, r2, r3, r4},
			{r5, r6, r7, r8},
		};

		return new Matrix(elem);
	}

	private Matrix createMat5(){
		/**
		 * 4*4の正方行列
		 * | 1  2  3  4|
		 * | 0  1  2  0|
		 * |-1 -2 -3 -4|
		 * | 5  6  7  8|
		 */
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(2);
		Rational r3 = new Rational(3);
		Rational r4 = new Rational(4);
		Rational r5 = new Rational(0);
		Rational r6 = new Rational(1);
		Rational r7 = new Rational(2);
		Rational r8 = new Rational(0);
		Rational r9 = new Rational(-1);
		Rational r10 = new Rational(-2);
		Rational r11 = new Rational(-3);
		Rational r12 = new Rational(-4);
		Rational r13 = new Rational(5);
		Rational r14 = new Rational(6);
		Rational r15 = new Rational(7);
		Rational r16 = new Rational(8);

		Rational[][] elem = {
			{r1, r2, r3, r4},
			{r5, r6, r7, r8},
			{r9, r10, r11, r12},
			{r13, r14, r15, r16},
		};

		return new Matrix(elem);
	}

	// =========== setter,getter =============

	@Test
	public void testGetter(){
		// 各getterが正常に値を取得できているか確認
		Matrix mat1 = createMat1();
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(2);
		Rational r3 = new Rational(3);
		Rational r4 = new Rational(4);
		assertThat(mat1.getRowColElem(0, 0), is(r1));
		assertThat(mat1.getRowColElem(0, 1), is(r2));
		assertThat(mat1.getRowColElem(1, 0), is(r3));
		assertThat(mat1.getRowColElem(1, 1), is(r4));

		Rational[] row1 = mat1.getRowElem(0);
		Rational[] row2 = mat1.getRowElem(1);
		assertThat(row1[0], is(r1));
		assertThat(row1[1], is(r2));
		assertThat(row2[0], is(r3));
		assertThat(row2[1], is(r4));

		Rational[] col1 = mat1.getColElem(0);
		Rational[] col2 = mat1.getColElem(1);
		assertThat(col1[0], is(r1));
		assertThat(col1[1], is(r3));
		assertThat(col2[0], is(r2));
		assertThat(col2[1], is(r4));

		Rational[][] elem = mat1.getElem();
		assertThat(elem[0][0], is(r1));
		assertThat(elem[0][1], is(r2));
		assertThat(elem[1][0], is(r3));
		assertThat(elem[1][1], is(r4));

		Matrix mat3 = createMat3();
		assertThat(mat3.getNRow(), is(3));
		assertThat(mat3.getNCol(), is(2));

		Matrix mat4 = createMat4();
		assertThat(mat4.getOrgCol(0), is(0));
		assertThat(mat4.getOrgCol(1), is(1));
		assertThat(mat4.getOrgCol(2), is(2));
	}

	@Test
	public void testSetter(){
		// setした値をgetして値を確認
		Matrix mat1 = createMat1();
		mat1.setRowColElem(0, 0, new Rational(0));
		assertThat(mat1.getRowColElem(0, 0), is(new Rational(0)));
		assertThat(mat1.getRowColElem(0, 1), is(new Rational(2)));
		assertThat(mat1.getRowColElem(1, 0), is(new Rational(3)));
		assertThat(mat1.getRowColElem(1, 1), is(new Rational(4)));

		Rational r1 = new Rational(-1);
		Rational r2 = new Rational(-2);
		Rational[] row = {r1, r2};
		mat1.setRowElem(1, row);
		assertThat(mat1.getRowColElem(0, 0), is(new Rational(0)));
		assertThat(mat1.getRowColElem(0, 1), is(new Rational(2)));
		assertThat(mat1.getRowColElem(1, 0), is(new Rational(-1)));
		assertThat(mat1.getRowColElem(1, 1), is(new Rational(-2)));
	}

	// =============== 継承 ===============
	@Test
	public void testEquals(){
		// 値が同じ正方行列と比較してtrueになるか確認
		// 他、値が異なる正方行列、非正方行列、null、型が異なるオブジェクトと比較
		Matrix mat1a = createMat1();
		Matrix mat1b = createMat1();
		Matrix mat2 = createMat2();
		Matrix mat3 = createMat3();
		Matrix mat4 = createMat4();
		assertThat(mat1a.equals(mat1b), is(true));
		assertThat(mat1a.equals(mat2), is(false));
		assertThat(mat1a.equals(mat3), is(false));
		assertThat(mat1a.equals(mat4), is(false));
		assertThat(mat1a.equals(null), is(false));
		assertThat(mat1a.equals(new Object()), is(false));
	}

	@Test
	public void testClone(){
		// クローンした時、きちんと同じ値か確認
		Matrix mat1ori = createMat1();
		Matrix mat1copy = mat1ori.clone();
		assertThat(mat1ori.equals(mat1copy), is(true));

		// クローンして生成した行列の値を変更したとき、元の行列の値も変更されてしまわないか確認
		mat1copy.setRowColElem(0, 0, new Rational(0));
		assertThat(mat1ori.equals(mat1copy), is(false));
	}

	@Test
	public void testToString(){
		// 文字列出力が空白区切りで末尾に\nが付いているか確認
		Matrix mat1 = createMat1();
		String mat1str = mat1.toString();
		String rightStr = "1/1 2/1\n3/1 4/1\n";
		assertThat(mat1str.equals(rightStr), is(true));
	}

	// =========== 入力 ============
	@Test
	public void testArrayReader(){
		// 配列による読み込みの確認
		// APIの以下が成り立っているか確認
		// array.lengthが0の場合
		// array.length[0]が0の場合
		// それ以外の場合は
		// array[i].lengthがarray[0].length以下のときは足りない分を0で埋める
		// array[i].lengthがarray[0].length以上のときはあふれた分を無効にする
		long[][][] array1 = {
				{{1,1},{2,1},},
				{{3,1},{4,1},},
		};

		long[][][] array2 = {
				{{1,1},{2,1},},
				{{0,1},{-1,1},},
				{{5,1},{1,1},},
		};

		long[][][] array3 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{1,1},{2,1}},
				{{-1,1},{-2,1},{-3,1},{-4,1}},
				{{5,1},{6,1},{7,1},{8,1},{9,0}},
		};

		long[][][] array4 = {};
		long[][][] array5 = {
				{},
				{{3,1},{4,1},},
		};

		Matrix mat1 = Matrix.arrayReader(array1);
		Matrix mat2 = Matrix.arrayReader(array2);
		Matrix mat3 = Matrix.arrayReader(array3);
		Matrix mat4 = Matrix.arrayReader(array4);
		Matrix mat5 = Matrix.arrayReader(array5);
		Matrix mat1right = createMat1();
		Matrix mat2right = createMat3();
		Matrix mat3right = createMat5();

		assertThat(mat1.equals(mat1right), is(true));
		assertThat(mat2.equals(mat2right), is(true));
		assertThat(mat3.equals(mat3right), is(true));
		assertThat(mat4, nullValue());
		assertThat(mat5, nullValue());
	}


	// ======== 演算 =========
	@Test
	public void testAdd(){
		// 足し算の計算が正常か確認
		// 行列の行数または列数が異なるときはnullを返すか確認
		Matrix mat1 = createMat1();
		Matrix mat2 = createMat2();
		Matrix mat3 = createMat3();

		Matrix mat1Add2 = mat1.add(mat2);
		Matrix mat1Add3 = mat1.add(mat3);

		long[][][] array1 = {
				{{2,1},{1,1},},
				{{1,1},{7,1},},
		};
		Matrix rightMat = Matrix.arrayReader(array1);

		assertThat(mat1Add2.equals(rightMat), is(true));
		assertThat(mat1Add3, nullValue());
	}

	@Test
	public void testSubtract(){
		// 引き算の計算が正常か確認
		// 行列の行数または列数が異なるときはnullを返すか確認
		Matrix mat1 = createMat1();
		Matrix mat2 = createMat2();
		Matrix mat3 = createMat3();

		Matrix mat1Sub2 = mat1.subtract(mat2);
		Matrix mat1Sub3 = mat1.subtract(mat3);

		long[][][] array1 = {
				{{0,1},{3,1},},
				{{5,1},{1,1},},
		};
		Matrix rightMat = Matrix.arrayReader(array1);

		assertThat(mat1Sub2.equals(rightMat), is(true));
		assertThat(mat1Sub3, nullValue());
	}

	@Test
	public void testMultiply(){
		// 乗算の計算が正常か確認
		// 正方行列、非正方行列のそれぞれについて確認
		// また、左行列の列数と右行列の行数が異なるときはnullを返すか確認
		Matrix mat1 = createMat1();
		Matrix mat2 = createMat2();
		Matrix mat3 = createMat3();
		Matrix mat4 = createMat4();

		Matrix mat1Mul2 = mat1.multiply(mat2);
		Matrix mat3Mul4 = mat3.multiply(mat4);
		Matrix mat4Mul3 = mat4.multiply(mat3);

		long[][][] array1 = {
				{{-3,1},{5,1},},
				{{-5,1},{9,1},},
		};
		Matrix rightMat1 = Matrix.arrayReader(array1);

		long[][][] array2 = {
				{{1,1},{-4,1},{8,1},{6,1}},
				{{0,1},{1,1},{-2,1},{-3,1}},
				{{5,1},{-11,1},{22,1},{3,1}},
		};
		Matrix rightMat2 = Matrix.arrayReader(array2);

		assertThat(mat1Mul2.equals(rightMat1), is(true));
		assertThat(mat3Mul4.equals(rightMat2), is(true));
		assertThat(mat4Mul3, nullValue());
	}

	@Test
	public void testSubstVector(){
		// ベクトルとの乗算の計算が正常か確認
		// 行列の列数とベクトルの要素数が異なるときはnullを返すか確認
		Matrix mat1 = createMat1();
		Rational r1 = new Rational(1);
		Rational r2 = new Rational(2);
		Rational r3 = new Rational(3);
		Rational[] vec1 = {r1,r2};
		Rational[] vec2 = {r1,r2,r3};

		Rational[] resultVec = mat1.substVector(vec1);
		assertThat(resultVec[0], is(new Rational(5)));
		assertThat(resultVec[1], is(new Rational(11)));
		assertThat(mat1.substVector(vec2), nullValue());
	}

	// ============= 行列の基本変形 ============
	// ------------- 行基本変形 -------------
	@Test
	public void testMultiplyRow(){
		// 指定行を定数倍する操作の確認
		// 範囲外の行や0倍処理のときはなにもしないことを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{6,1},{7,1}},
		};
		long[][][] array2 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{10,1},{12,1},{14,1}},
		};
		Matrix matOri = Matrix.arrayReader(array1);
		Matrix mat1 = matOri.clone();
		Matrix mat2 = matOri.clone();
		Matrix mat3 = matOri.clone();
		Matrix mat4 = matOri.clone();
		Rational r = new Rational(2);
		Matrix matAns = Matrix.arrayReader(array2);
		mat1.multiplyRow(-1, r);
		mat2.multiplyRow(3, r);
		mat3.multiplyRow(1, new Rational(0));
		mat4.multiplyRow(2, r);
		assertThat(mat1, is(matOri));
		assertThat(mat2, is(matOri));
		assertThat(mat3, is(matOri));
		assertThat(mat4, is(matAns));
	}

	@Test
	public void testAddMultipliedRow(){
		// ある行に指定した別の行の定数倍を加算する計算の確認
		// 範囲外を指定した場合や0倍だったときは何もしないことを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{6,1},{7,1}},
		};
		long[][][] array2 = {
				{{1,1},{2,1},{3,1}},
				{{2,1},{5,1},{8,1}},
				{{5,1},{6,1},{7,1}},
		};
		Matrix matOri = Matrix.arrayReader(array1);
		Matrix mat1 = matOri.clone();
		Matrix mat2 = matOri.clone();
		Matrix mat3 = matOri.clone();
		Matrix mat4 = matOri.clone();
		Rational r = new Rational(2);
		Matrix matAns = Matrix.arrayReader(array2);
		mat1.addMultipliedRow(-1, r, 1);
		mat2.addMultipliedRow(0, r, 3);
		mat3.addMultipliedRow(0, new Rational(0), 1);
		mat4.addMultipliedRow(0, r, 1);
		assertThat(mat1, is(matOri));
		assertThat(mat2, is(matOri));
		assertThat(mat3, is(matOri));
		assertThat(mat4, is(matAns));
	}

	@Test
	public void testExchangeRow(){
		// 指定した２つの行を入れ替える計算を確認
		// 範囲外の行を指定した場合は何もしないことを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{6,1},{7,1}},
		};
		long[][][] array2 = {
				{{5,1},{6,1},{7,1}},
				{{0,1},{1,1},{2,1}},
				{{1,1},{2,1},{3,1}},
		};
		Matrix matOri = Matrix.arrayReader(array1);
		Matrix mat1 = matOri.clone();
		Matrix mat2 = matOri.clone();
		Matrix mat3 = matOri.clone();
		Matrix matAns = Matrix.arrayReader(array2);
		mat1.exchangeRow(-1, 1);
		mat2.exchangeRow(0, 3);
		mat3.exchangeRow(0, 2);
		assertThat(mat1, is(matOri));
		assertThat(mat2, is(matOri));
		assertThat(mat3, is(matAns));
	}

	// ------------- 列基本変形 -------------
	@Test
	public void testMultiplyColumn(){
		// 指定列を定数倍する操作の確認
		// 範囲外の列や0倍処理のときはなにもしないことを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{6,1},{7,1}},
		};
		long[][][] array2 = {
				{{1,1},{4,1},{3,1}},
				{{0,1},{2,1},{2,1}},
				{{5,1},{12,1},{7,1}},
		};
		Matrix matOri = Matrix.arrayReader(array1);
		Matrix mat1 = matOri.clone();
		Matrix mat2 = matOri.clone();
		Matrix mat3 = matOri.clone();
		Matrix mat4 = matOri.clone();
		Rational r = new Rational(2);
		Matrix matAns = Matrix.arrayReader(array2);
		mat1.multiplyColumn(-1, r);
		mat2.multiplyColumn(3, r);
		mat3.multiplyColumn(1, new Rational(0));
		mat4.multiplyColumn(1, r);
		assertThat(mat1, is(matOri));
		assertThat(mat2, is(matOri));
		assertThat(mat3, is(matOri));
		assertThat(mat4, is(matAns));
	}

	@Test
	public void testAddMultipliedColumn(){
		// ある列に指定した別の列の定数倍を加算する計算の確認
		// 範囲外を指定した場合や0倍だったときは何もしないことを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{6,1},{7,1}},
		};
		long[][][] array2 = {
				{{1,1},{4,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{16,1},{7,1}},
		};
		Matrix matOri = Matrix.arrayReader(array1);
		Matrix mat1 = matOri.clone();
		Matrix mat2 = matOri.clone();
		Matrix mat3 = matOri.clone();
		Matrix mat4 = matOri.clone();
		Rational r = new Rational(2);
		Matrix matAns = Matrix.arrayReader(array2);
		mat1.addMultipliedColumn(-1, r, 1);
		mat2.addMultipliedColumn(0, r, 3);
		mat3.addMultipliedColumn(0, new Rational(0), 1);
		mat4.addMultipliedColumn(0, r, 1);
		assertThat(mat1, is(matOri));
		assertThat(mat2, is(matOri));
		assertThat(mat3, is(matOri));
		assertThat(mat4, is(matAns));
	}

	@Test
	public void testExchangeColumn(){
		// 指定した２つの列を入れ替える計算を確認
		// 範囲外の列を指定した場合は何もしないことを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1}},
				{{0,1},{1,1},{2,1}},
				{{5,1},{6,1},{7,1}},
		};
		long[][][] array2 = {
				{{3,1},{2,1},{1,1}},
				{{2,1},{1,1},{0,1}},
				{{7,1},{6,1},{5,1}},
		};
		Matrix matOri = Matrix.arrayReader(array1);
		Matrix mat1 = matOri.clone();
		Matrix mat2 = matOri.clone();
		Matrix mat3 = matOri.clone();
		Matrix matAns = Matrix.arrayReader(array2);
		mat1.exchangeColumn(-1, 1);
		mat2.exchangeColumn(0, 3);
		mat3.exchangeColumn(0, 2);
		assertThat(mat1, is(matOri));
		assertThat(mat2, is(matOri));
		assertThat(mat3, is(matAns));
		assertThat(mat3.getOrgCol(0), is(2));
		assertThat(mat3.getOrgCol(1), is(1));
		assertThat(mat3.getOrgCol(2), is(0));
	}

	// =========== 部分行列処理 ==========
	@Test
	public void testLeftUpperMatrix(){
		// 左上の部分行列を取得する処理を確認
		// 行列より大きい範囲を指定した場合にきちんと処理できているかも確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{1,1},{2,1},{3,1}},
				{{5,1},{6,1},{7,1},{8,1}},
		};
		long[][][] array2 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{1,1},{2,1},{3,1}},
		};
		long[][][] array3 = {
				{{1,1},{2,1}},
				{{0,1},{1,1}},
				{{5,1},{6,1}},
		};
		long[][][] array4 = {
				{{1,1},{2,1}},
				{{0,1},{1,1}},
		};
		Matrix mat1 = Matrix.arrayReader(array1);
		Matrix mat2 = Matrix.arrayReader(array2);
		Matrix mat3 = Matrix.arrayReader(array3);
		Matrix mat4 = Matrix.arrayReader(array4);
		assertThat(mat1.leftUpperMatrix(2, 5), is(mat2));
		assertThat(mat1.leftUpperMatrix(5, 2), is(mat3));
		assertThat(mat1.leftUpperMatrix(2, 2), is(mat4));
	}

	@Test
	public void testRightLowerMatrix(){
		// 右下の部分行列を取得する処理を確認
		// 行列より大きい範囲を指定した場合にきちんと処理できているかも確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{1,1},{2,1},{3,1}},
				{{5,1},{6,1},{7,1},{8,1}},
		};
		long[][][] array2 = {
				{{0,1},{1,1},{2,1},{3,1}},
				{{5,1},{6,1},{7,1},{8,1}},
		};
		long[][][] array3 = {
				{{3,1},{4,1}},
				{{2,1},{3,1}},
				{{7,1},{8,1}},
		};
		long[][][] array4 = {
				{{2,1},{3,1}},
				{{7,1},{8,1}},
		};
		Matrix mat1 = Matrix.arrayReader(array1);
		Matrix mat2 = Matrix.arrayReader(array2);
		Matrix mat3 = Matrix.arrayReader(array3);
		Matrix mat4 = Matrix.arrayReader(array4);
		assertThat(mat1.rightLowerMatrix(2, 5), is(mat2));
		assertThat(mat1.rightLowerMatrix(5, 2), is(mat3));
		assertThat(mat1.rightLowerMatrix(2, 2), is(mat4));
	}

	@Test
	public void testReplaceSubMatrix(){
		// 行列の一部分を置換する処理を確認
		// 置換先がはみ出ている場合ははみ出て分を無視することを確認
		long[][][] array1 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{1,1},{2,1},{3,1}},
				{{5,1},{6,1},{7,1},{8,1}},
		};
		long[][][] array2 = {
				{{-1,1},{-1,1},{-1,1}},
				{{-1,1},{-1,1},{-1,1}},
		};
		long[][][] array3 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{-1,1},{-1,1},{-1,1}},
				{{5,1},{-1,1},{-1,1},{-1,1}},
		};
		long[][][] array4 = {
				{{1,1},{2,1},{3,1},{4,1}},
				{{0,1},{1,1},{2,1},{3,1}},
				{{5,1},{6,1},{-1,1},{-1,1}},
		};
		Matrix mat1a = Matrix.arrayReader(array1);
		Matrix mat1b = Matrix.arrayReader(array1);
		Matrix mat2 = Matrix.arrayReader(array2);
		Matrix mat3 = Matrix.arrayReader(array3);
		Matrix mat4 = Matrix.arrayReader(array4);
		mat1a.replaceSubMatrix(1, 1, mat2);
		mat1b.replaceSubMatrix(2, 2, mat2);
		assertThat(mat1a, is(mat3));
		assertThat(mat1b, is(mat4));
	}
}
