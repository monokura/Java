public class LES {
	private Matrix a;
	private Rational[] b;
	private Rational[] x;

	public LES(Matrix a, Rational[] b) {
		this.a = a.clone();
		this.b = b.clone();
		this.x = new Rational[a.getNCol()];
	}

	public Rational[] getX() {
		return x;
	}

	public int solve() {
		int nRow = a.getNRow();
		int nCol = a.getNCol();
		Matrix m = a.concatVector(b);

		m.echelonForm();

		Matrix a1 = m.leftUpperMatrix(nRow, nCol);
		a1.rebuiltForm();

		int coreRow = a1.getCoreRow();
		int coreCol = a1.getCoreCol();

		if (coreRow < nRow) {
			if (!m.getRowColElem(coreRow, nCol).equals(new Rational(0))) {
				return 0;
			}
		}

		// 不定解部分は0を設定
		for (int i = coreCol; i < nCol; i++) {
			x[a1.getOrgCol(i)] = new Rational(0);
		}

		Matrix h = a1.leftUpperMatrix(coreRow, coreCol);
		Rational[] b1 = new Rational[coreRow];
		for (int i = 0; i < coreRow; i++) {
			b1[i] = m.getRowColElem(i, nCol);
		}

		Matrix d = h.concatVector(b1);
		d.setCoreRow(coreRow);
		d.setCoreCol(coreCol);
		d.leftIdentityForm();

		// 不定解に1を設定
		for (int i = coreRow; i < coreCol; i++) {
			x[a1.getOrgCol(i)] = new Rational(1);
		}

		Matrix a2 = d.leftUpperMatrix(coreRow, coreCol).rightLowerMatrix(0, coreRow);
		Rational[] y = new Rational[coreRow];
		for (int i = 0; i < coreRow; i++) {
			y[i] = d.getRowColElem(i, d.getNCol() - 1);
		}

		// 非基底変数がない場合はa2がnullとなるので注意
		if (a2 != null) {
			Rational[] z = new Rational[coreCol - coreRow];
			for (int i = 0; i < coreCol - coreRow; i++) {
				z[i] = new Rational(1);
			}

			Rational[] temp = a2.substVector(z);

			for (int i = 0; i < coreRow; i++) {
				y[i] = y[i].subtract(temp[i]);
			}
		}
		for (int i = 0; i < coreRow; i++) {
			x[a1.getOrgCol(i)] = y[i];
		}

		return 1;
	}
}
