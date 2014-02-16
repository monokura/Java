public class LIS {
	private Matrix a; // 行列A
	private Rational[] b; // 定数項列ベクトルb
	private int[] c; // 不等号列ベクトル
	private Rational[] x; // 充足解列ベクトル

	private Matrix h; // マトリクス表
	private Rational[] v; // 変数の現在値
	private Rational[] lb; // 変数の下限値
	private Rational[] ub; // 変数の上限値
	int bvIncDec; // 増加or減少を伝達するフラグ

	LIS(Matrix a, Rational[] b, int[] c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.x = new Rational[a.getNCol()];
	}

	public Rational[] getX() {
		return x;
	}

	@Override
	public String toString() {
		String tmp = "";
		for (int i = 0; i < a.getNRow(); i++) {
			for (int j = 0; j < a.getNCol(); j++) {
				tmp += a.getRowColElem(i, j).toString() + " ";
			}

			if (c[i] == 0) {
				tmp += "= ";
			} else if (c[i] == 1) {
				tmp += "≧ ";
			} else if (c[i] == 2) {
				tmp += "≦ ";
			}

			tmp += b[i].toString() + "\n";
		}
		return tmp;
	}

	void init() {
		// Aの行数n,列数mとする
		int n = a.getNRow();
		int m = a.getNCol();

		// シンプレックス表の初期化
		Rational[][] hElem = new Rational[n][n + m];
		for (int i = 0; i < n; i++) {
			// 左部分はn×nの単位行列
			for (int j = 0; j < n; j++) {
				if (i == j) {
					hElem[i][j] = new Rational(1);
				} else {
					hElem[i][j] = new Rational(0);
				}
			}
			// 右部分はn×mの行列Aにマイナスをつけたもの
			for (int j = 0; j < m; j++) {
				hElem[i][n + j] = a.getRowColElem(i, j).multiply(new Rational(-1));
			}
		}
		h = new Matrix(hElem);
		h.setCoreRow(n);
		h.setCoreCol(n);

		// 変数現在値は0で初期化
		v = new Rational[n + m];
		for (int i = 0; i < n + m; i++) {
			v[i] = new Rational(0);
		}

		lb = new Rational[n + m];
		ub = new Rational[n + m];
		// 上限値と下限値配列の設定
		for (int i = 0; i < n; i++) {
			if (c[i] == 0) {
				// = の場合
				lb[i] = b[i];
				ub[i] = b[i];
			} else if (c[i] == 1) {
				// ≧ の場合
				lb[i] = b[i];
				ub[i] = new Rational(Long.MAX_VALUE);
			} else if (c[i] == 2) {
				// ≦ の場合
				lb[i] = new Rational(-Long.MAX_VALUE);
				ub[i] = b[i];
			}
		}
		for(int i = n;i < n + m;i++){
			lb[i] = new Rational(-Long.MAX_VALUE);
			ub[i] = new Rational(Long.MAX_VALUE);
		}
	}

	public int solve() {
		int nRow = a.getNRow();
		int nCol = a.getNCol();

		while (true) {
			int bv = findBasicVar();
			if (bv == -1) {
				// 基底変数が定義域を満たす場合
				for (int i = 0; i < nCol; i++) {
					x[i] = v[nRow + i];
				}
				return 1; // 正常
			} else {
				int nbv = findNonBasicVar(bv);
				if (nbv == -1) {
					// 非基底変数を変化させても定義域を満たさない
					return 0; // 不能b
				} else {
					if(bvIncDec > 0){
						// 要増加
						// 基底変数の値を下限に合わせる
						v[h.getOrgCol(bv)] = lb[h.getOrgCol(bv)];
					}else if(bvIncDec < 0){
						// 要減少
						// 基底変数の値を上限に合わせる
						v[h.getOrgCol(bv)] = ub[h.getOrgCol(bv)];
					}

					h.exchangeColumn(bv, nbv);
					h.echelonForm();
					h.leftIdentityForm();

					Matrix h2 = h.rightLowerMatrix(0, nRow);
					Rational[] z = new Rational[nCol];
					for (int i = 0; i < nCol; i++) {
						z[i] = v[h.getOrgCol(i + nRow)].multiply(new Rational(-1));
					}

					Rational[] y = h2.substVector(z);
					for (int i = 0; i < nRow; i++) {
						v[h.getOrgCol(i)] = y[i];
					}
				}
			}
		}
	}

	private int findBasicVar() {
		int nRow = a.getNRow();

		for (int i = 0; i < nRow; i++) {
			if (v[h.getOrgCol(i)].lessThan(lb[h.getOrgCol(i)])) {
				// 基底変数の値が下限よりも小さい
				bvIncDec = 1; // 要増加
				return i;
			}
			if (v[h.getOrgCol(i)].greaterThan(ub[h.getOrgCol(i)])) {
				// 基底変数の値が上限よりも大きい
				bvIncDec = -1; // 要減少
				return i;
			}
		}
		return -1; // すべての基底変数が定義域を満たす
	}

	private int findNonBasicVar(int bv) {
		int nRow = a.getNRow();
		int nCol = a.getNCol();
		for (int nbv = nRow; nbv < nRow + nCol; nbv++) {
			int orgCol = h.getOrgCol(nbv);
			if (bvIncDec < 0) {
				// 要減少
				if (h.getRowColElem(bv, nbv).greaterThan(new Rational(0))) {
					// 値が正
					if (v[orgCol].lessThan(ub[orgCol])) {
						// 上限を下回るならば増加可
						return nbv;
					}
				} else {
					// 値が負
					if (v[orgCol].greaterThan(lb[orgCol])) {
						// 下限を上回るならば減少可
						return nbv;
					}
				}
			} else {
				// 要増加
				if (h.getRowColElem(bv, nbv).greaterThan(new Rational(0))) {
					// 値が正
					if (v[orgCol].greaterThan(lb[orgCol])) {
						// 下限を上回るならば減少可
						return nbv;
					}
				} else {
					// 値が負
					if (v[orgCol].lessThan(ub[orgCol])) {
						// 上限を下回るならば増加可
						return nbv;
					}
				}
			}
		}
		return -1;
	}
}
