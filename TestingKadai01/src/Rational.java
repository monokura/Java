public class Rational extends Object {
	private long	den;	// 分母
	private long	num;	// 分子

	public long getDen() {
		return den;
	}

	public long getNum() {
		return num;
	}

	public Rational() {
		this.num = 0;
		this.den = 1;
	}

	public Rational(long num) {
		this.num = num;
		this.den = 1;
	}

	public Rational(long num, long den) {
		this.num = num;
		this.den = den;
		reduceAndNormalize();
	}

	static Rational arrayReader(long array[]) {
		if (array.length == 1) {
			return new Rational(array[0]);
		} else {
			if (array[1] == 0) {
				return null;
			}
			return new Rational(array[0], array[1]);
		}
	}

	@Override
	protected Rational clone() {
		return new Rational(this.num, this.den);
	}

	@Override
	public boolean equals(Object o) {
		// オブジェクトがnullでないこと
		if (o == null) {
			return false;
		}
		// オブジェクトが同じ型であること
		if (!(o instanceof Rational)) {
			return false;
		}
		// 同値性を比較
		if (this.num != ((Rational) o).num) {
			return false;
		}
		if (this.den != ((Rational) o).den) {
			return false;
		}
		return true;
	}

	private void reduceAndNormalize() {
		reduce();
		normalize();
	}

	private void reduce() {
		long gcd = gcd(this.num, this.den);
		this.num /= gcd;
		this.den /= gcd;
	}

	private void normalize() {
		if (this.den < 0) {
			this.den *= -1;
			this.num *= -1;
		}
	}

	private long gcd(long a, long b) {
		// ユークリッドの互除法
		if (b == 0) {
			if (a >= 0) {
				return a;
			} else {
				return a * -1;
			}
		}
		long r = a % b;
		return gcd(b, r);
	}

	public Rational add(Rational r) {
		long newNum = this.num * r.den + this.den * r.num;
		long newDen = this.den * r.den;
		return new Rational(newNum, newDen);
	}

	Rational subtract(Rational r) {
		long newNum = this.num * r.den - this.den * r.num;
		long newDen = this.den * r.den;
		return new Rational(newNum, newDen);
	}

	Rational multiply(Rational r) {
		long newNum = this.num * r.num;
		long newDen = this.den * r.den;
		return new Rational(newNum, newDen);
	}

	Rational devide(Rational r) {
		// 0割はnull
		if (r.num == 0) {
			return null;
		}
		long newNum = this.num * r.den;
		long newDen = this.den * r.num;
		return new Rational(newNum, newDen);
	}

	Rational inverse() {
		// 分子が0ならnull
		if (this.num == 0) {
			return null;
		}
		return new Rational(this.den, this.num);
	}

	Rational power(int n) {
		long m;
		if (n >= 0) {
			m = n;
		} else {
			m = n * -1;
		}
		long newNum = 1;
		long newDen = 1;
		for (int i = 0; i < m; i++) {
			newNum *= this.num;
			newDen *= this.den;
		}

		if (n >= 0) {
			return new Rational(newNum, newDen);
		} else {
			return (new Rational(newNum, newDen)).inverse();
		}
	}

	boolean greaterThan(Rational r) {
		if (this.num * r.den - this.den * r.num > 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean lessThan(Rational r) {
		if (this.num * r.den - this.den * r.num < 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (this.num >= 0) {
			buf.append(this.num);
		} else {
			buf.append("-");
			buf.append(this.num * -1);
		}
		buf.append("/");
		buf.append(this.den);
		return buf.toString();
	}
}
