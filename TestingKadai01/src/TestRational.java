import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestRational {

	@Test
	public void testConstructor(){
		// 3種類のコンストラクタで正常にインスタンスが作成できているか確認
		// いちおうマイナスも確認
		Rational r1 = new Rational();
		Rational r2 = new Rational(2);
		Rational r3 = new Rational(-3);
		Rational r4 = new Rational(2, 3);

		assertThat(r1.getNum(), is(0L));
		assertThat(r1.getDen(), is(1L));
		assertThat(r2.getNum(), is(2L));
		assertThat(r2.getDen(), is(1L));
		assertThat(r3.getNum(), is(-3L));
		assertThat(r3.getDen(), is(1L));
		assertThat(r4.getNum(), is(2L));
		assertThat(r4.getDen(), is(3L));
	}

	@Test
	public void testArrayReader() {
		// 配列による定義が正常か確認
		// 配列の大きさが1,2,3のときそれぞれについてテスト
		long[] arr1 = {4L};
		Rational r1 = Rational.arrayReader(arr1);
		assertThat(r1, is(new Rational(4)));

		long[] arr2 = {1L,2L};
		Rational r2 = Rational.arrayReader(arr2);
		assertThat(r2, is(new Rational(1, 2)));

		long[] arr3 = {1L,2L,3L};
		Rational r3 = Rational.arrayReader(arr3);
		assertThat(r3, is(new Rational(1, 2)));
	}

	@Test
	public void testClone(){
		// クローンメソッドが正常か確認
		Rational rOriginal = new Rational(1, 2);
		Rational rCopy = rOriginal.clone();
		assertThat(rCopy, is(rOriginal));
	}

	@Test
	public void testEquals(){
		// 等価がきちんと処理されるか確認
		Rational r1 = new Rational(1,2);
		Rational r2 = new Rational(1,2);
		Rational r3 = new Rational(1,3);
		assertThat(true, is(r1.equals(r2)));
		assertThat(false, is(r1.equals(r3)));
		assertThat(false, is(r1.equals(null)));
		assertThat(false, is(r1.equals(new Object())));
	}

	@Test
	public void testReduceAndNormalize(){
		// 規約化と正規化を同時に処理したときの確認
		Rational r1 = new Rational(-3, 6);
		Rational r2 = new Rational(3, -6);
		Rational r3 = new Rational(-3, -6);
		assertThat(r1, is(new Rational(-1, 2)));
		assertThat(r2, is(new Rational(-1, 2)));
		assertThat(r3, is(new Rational(1, 2)));
	}

	@Test
	public void testReduce(){
		// 規約化の確認
		Rational r1 = new Rational(2,4);
		assertThat(r1, is(new Rational(1, 2)));
	}

	@Test
	public void testNormalize(){
		// 正規化の確認
		// 分子がマイナスのとき、分母がマイナスのとき
		// 分母・分子ともにマイナスのときの
		// 3種について確認
		Rational r1 = new Rational(-1, 2);
		Rational r2 = new Rational(1, -2);
		Rational r3 = new Rational(-1, -2);
		assertThat(r1, is(new Rational(-1, 2)));
		assertThat(r2, is(new Rational(-1, 2)));
		assertThat(r3, is(new Rational(1, 2)));
	}

	@Test
	public void testAdd(){
		// 加算処理の確認
		// 正＋正、正＋負、負＋正、負＋負のそれぞれについてテスト
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(1, 3);
		assertThat(r1.add(r2), is(new Rational(5, 6)));

		Rational r3 = new Rational(1, 2);
		Rational r4 = new Rational(-1, 3);
		assertThat(r3.add(r4), is(new Rational(1, 6)));

		Rational r5 = new Rational(-1, 2);
		Rational r6 = new Rational(1, 3);
		assertThat(r5.add(r6), is(new Rational(-1, 6)));

		Rational r7 = new Rational(-1, 2);
		Rational r8 = new Rational(-1, 3);
		assertThat(r7.add(r8), is(new Rational(-5, 6)));
	}

	@Test
	public void testSubtract(){
		// 減算処理の確認
		// 正-正、正-負、負-正、負-負のそれぞれについてテスト
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(1, 3);
		assertThat(r1.subtract(r2), is(new Rational(1, 6)));

		Rational r3 = new Rational(1, 2);
		Rational r4 = new Rational(-1, 3);
		assertThat(r3.subtract(r4), is(new Rational(5, 6)));

		Rational r5 = new Rational(-1, 2);
		Rational r6 = new Rational(1, 3);
		assertThat(r5.subtract(r6), is(new Rational(-5, 6)));

		Rational r7 = new Rational(-1, 2);
		Rational r8 = new Rational(-1, 3);
		assertThat(r7.subtract(r8), is(new Rational(-1, 6)));
	}

	@Test
	public void testMultiple(){
		// 乗算処理の確認
		// 正×正、正×負、負×正、負×負のそれぞれについてテスト
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(1, 3);
		assertThat(r1.multiply(r2), is(new Rational(1, 6)));

		Rational r3 = new Rational(1, 2);
		Rational r4 = new Rational(-1, 3);
		assertThat(r3.multiply(r4), is(new Rational(-1, 6)));

		Rational r5 = new Rational(-1, 2);
		Rational r6 = new Rational(1, 3);
		assertThat(r5.multiply(r6), is(new Rational(-1, 6)));

		Rational r7 = new Rational(-1, 2);
		Rational r8 = new Rational(-1, 3);
		assertThat(r7.multiply(r8), is(new Rational(1, 6)));
	}

	@Test
	public void testDevide(){
		// 乗算処理の確認
		// 正÷正、正÷負、負÷正、負÷負のそれぞれについてテスト
		// ゼロ割の際はnullを返すか確認
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(1, 3);
		assertThat(r1.devide(r2), is(new Rational(3, 2)));

		Rational r3 = new Rational(1, 2);
		Rational r4 = new Rational(-1, 3);
		assertThat(r3.devide(r4), is(new Rational(-3, 2)));

		Rational r5 = new Rational(-1, 2);
		Rational r6 = new Rational(1, 3);
		assertThat(r5.devide(r6), is(new Rational(-3, 2)));

		Rational r7 = new Rational(-1, 2);
		Rational r8 = new Rational(-1, 3);
		assertThat(r7.devide(r8), is(new Rational(3, 2)));

		Rational r9 = new Rational(1, 2);
		Rational r10= new Rational(0, 2);
		assertThat(r9.devide(r10), nullValue());
	}

	@Test
	public void testInverse(){
		// 逆数の確認
		// 正、負の場合正常に逆数が生成できるかテスト
		// また、分子がゼロのときはnullを返すかテスト
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(0, 2);
		Rational r3 = new Rational(-1, 2);
		assertThat(r1.inverse(), is(new Rational(2, 1)));
		assertThat(r2.inverse(), nullValue());
		assertThat(r3.inverse(), is(new Rational(-2, 1)));
	}

	@Test
	public void testPower(){
		// n乗のテスト
		// 正、負の分数それぞれについて
		// 正、0、負の累乗を確認
		Rational r1 = new Rational(2, 3);
		assertThat(r1.power(5), is(new Rational(32, 243)));
		assertThat(r1.power(0), is(new Rational(1, 1)));
		assertThat(r1.power(-5), is(new Rational(243, 32)));

		Rational r2 = new Rational(-2, 3);
		assertThat(r2.power(5), is(new Rational(-32, 243)));
		assertThat(r2.power(0), is(new Rational(1, 1)));
		assertThat(r2.power(-5), is(new Rational(-243, 32)));
	}

	@Test
	public void testGreaterThan(){
		// 大なりの確認
		// 正と正、正と負、負と負のそれぞれについて確認
		// また、同値のときfalseになるか確認
		Rational r1 = new Rational(3, 4);
		Rational r2 = new Rational(1, 2);
		assertThat(r1.greaterThan(r2), is(true));
		assertThat(r2.greaterThan(r1), is(false));

		Rational r3 = new Rational(3, 4);
		Rational r4 = new Rational(-1, 2);
		assertThat(r3.greaterThan(r4), is(true));
		assertThat(r4.greaterThan(r3), is(false));

		Rational r5 = new Rational(-1, 2);
		Rational r6 = new Rational(-3, 4);
		assertThat(r5.greaterThan(r6), is(true));
		assertThat(r6.greaterThan(r5), is(false));

		Rational r7 = new Rational(1, 2);
		Rational r8 = new Rational(1, 2);
		assertThat(r7.greaterThan(r8), is(false));
	}

	@Test
	public void testLessThan(){
		// 小なりの確認
		// 正と正、正と負、負と負のそれぞれについて確認
		// また、同値のときfalseになるか確認
		Rational r1 = new Rational(3, 4);
		Rational r2 = new Rational(1, 2);
		assertThat(r1.lessThan(r2), is(false));
		assertThat(r2.lessThan(r1), is(true));

		Rational r3 = new Rational(3, 4);
		Rational r4 = new Rational(-1, 2);
		assertThat(r3.lessThan(r4), is(false));
		assertThat(r4.lessThan(r3), is(true));

		Rational r5 = new Rational(-1, 2);
		Rational r6 = new Rational(-3, 4);
		assertThat(r5.lessThan(r6), is(false));
		assertThat(r6.lessThan(r5), is(true));

		Rational r7 = new Rational(1, 2);
		Rational r8 = new Rational(1, 2);
		assertThat(r7.lessThan(r8), is(false));
	}

	@Test
	public void testToString(){
		// 文字列出力の確認
		// 正と負について文字列をきちんと返すか確認
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(-1, 2);
		assertThat(r1.toString(), is("1/2"));
		assertThat(r2.toString(), is("-1/2"));
	}
}
