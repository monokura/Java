public class Matrix {
	private int				coreRow;	// 必要行数
	private int				coreCol;	// 必要列数
	private Rational[][]	elem;		// 要素
	private int				nRow;		// 行数
	private int				nCol;		// 列数
	private int[]			orgCol;	// 各列に対する当初の列番号の配列

	Matrix(Rational[][] elem) {
		this.elem = elem;
		this.nRow = elem.length;
		this.nCol = elem[0].length;
		orgCol = new int[nCol];
		for(int i = 0;i < nCol;i++){
			orgCol[i] = i;
		}
	}

	// ========== getter,setter ===========
	public Rational getRowColElem(int row, int col) {
		return elem[row][col];
	}

	public Rational[] getRowElem(int row) {
		return elem[row];
	}

	public Rational[] getColElem(int col) {
		Rational[] temp = new Rational[nRow];
		for (int j = 0; j < nRow; j++) {
			temp[j] = elem[j][col];
		}
		return temp;
	}

	public Rational[][] getElem() {
		return elem;
	}

	public int getNRow() {
		return nRow;
	}

	public int getNCol() {
		return nCol;
	}

	public void setRowElem(int row, Rational[] v) {
		elem[row] = v;
	}

	public int getOrgCol(int col) {
		return orgCol[col];
	}

	// ============ 継承 ==============

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Matrix)) {
			return false;
		}
		Matrix m = (Matrix) o;
		if (nRow != m.getNRow()) {
			return false;
		}
		if (nCol != m.getNCol()) {
			return false;
		}
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				if (elem[i][j] != m.getRowColElem(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	protected Object clone() {
		Rational[][] elem = new Rational[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				elem[i][j] = this.elem[i][j];
			}
		}
		return new Matrix(elem);
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				if (j != 0) {
					buf.append(" ");
				}
				buf.append(elem[i][j].toString());
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	// ========= 入力 ==========
	public static Matrix arrayReader(long[][][] array) {
		if (array.length == 0) {
			return null;
		}
		if (array[0].length == 0) {
			return null;
		}
		int rowNum = array.length;
		int colNum = array[0].length;
		Rational[][] elem = new Rational[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (j < array[i].length) {
					elem[i][j] = Rational.arrayReader(array[i][j]);
				} else {
					elem[i][j] = new Rational(0);
				}
			}
		}

		return new Matrix(elem);
	}

	// ========== 演算 =========
	public Matrix add(Matrix m) {
		if (this.nRow != m.getNRow()) {
			return null;
		}
		if (this.nCol != m.getNCol()) {
			return null;
		}

		Rational[][] elem = new Rational[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				elem[i][j] = this.elem[i][j].add(m.getRowColElem(i, j));
			}
		}
		return new Matrix(elem);
	}

	public Matrix subtract(Matrix m) {
		if (this.nRow != m.getNRow()) {
			return null;
		}
		if (this.nCol != m.getNCol()) {
			return null;
		}

		Rational[][] elem = new Rational[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				elem[i][j] = this.elem[i][j].subtract(m.getRowColElem(i, j));
			}
		}

		return new Matrix(elem);
	}

	public Matrix multiply(Matrix m) {
		if (this.nCol != m.getNRow()) {
			return null;
		}
		Rational[][] elem = new Rational[nRow][m.getNCol()];

		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < m.getNCol(); j++) {
				elem[i][j] = new Rational(0);
				for (int k = 0; k < nCol; k++) {
					elem[i][j] = elem[i][j].add(this.elem[i][k].multiply(m
							.getRowColElem(k, j)));
				}
			}
		}
		return new Matrix(elem);
	}

	public Matrix multiply(Rational r) {
		Rational[][] elem = new Rational[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				elem[i][j] = this.elem[i][j].multiply(r);
			}
		}
		return new Matrix(elem);
	}

	public Rational[] substVector(Rational[] v) {
		if (nCol != v.length) {
			return null;
		}
		Rational[] elem = new Rational[nRow];
		for (int i = 0; i < nRow; i++) {
			elem[i] = new Rational(0);
			for (int j = 0; j < nCol; j++) {
				elem[i] = elem[i].add(this.elem[i][j].multiply(v[j]));
			}
		}
		return elem;
	}

	// ============= 行列の基本変形 ============
	// ------------- 行基本変形 -------------
	public void multiplyRow(int row, Rational r) {
		if ((row < 0) || (nRow <= row)) {
			return;
		}
		if (r.equals(new Rational(0))) {
			return;
		}

		for (int i = 0; i < nCol; i++) {
			elem[row][i] = elem[row][i].multiply(r);
		}
	}

	public void addMultipliedRow(int row1, Rational r, int row2) {
		if ((row1 < 0) || (nRow <= row1)) {
			return;
		}

		if ((row2 < 0) || (nRow <= row2)) {
			return;
		}

		if (r.equals(new Rational(0))) {
			return;
		}

		for (int i = 0; i < nCol; i++) {
			elem[row2][i] = elem[row2][i].add(elem[row1][i].multiply(r));
		}
	}

	public void exchangeRow(int row1, int row2) {
		if ((row1 < 0) || (nRow <= row1)) {
			return;
		}

		if ((row2 < 0) || (nRow <= row2)) {
			return;
		}

		if (row1 == row2) {
			return;
		}

		Rational[] temp = elem[row1];
		elem[row1] = elem[row2];
		elem[row2] = temp;
	}

	// ------------- 列基本変形 -------------
	public void multiplyColumn(int col, Rational r) {
		if ((col < 0) || (nCol <= col)) {
			return;
		}
		if (r.equals(new Rational(0))) {
			return;
		}

		for (int i = 0; i < nRow; i++) {
			elem[i][col] = elem[i][col].multiply(r);
		}
	}

	public void addMultipliedColumn(int col1, Rational r, int col2) {
		if ((col1 < 0) || (nCol <= col1)) {
			return;
		}

		if ((col2 < 0) || (nCol <= col2)) {
			return;
		}

		if (r.equals(new Rational(0))) {
			return;
		}

		for (int i = 0; i < nRow; i++) {
			elem[i][col2] = elem[i][col2].add(elem[i][col1].multiply(r));
		}
	}

	public void exchangeColumn(int col1, int col2) {
		if ((col1 < 0) || (nCol <= col1)) {
			return;
		}

		if ((col2 < 0) || (nCol <= col2)) {
			return;
		}

		if (col1 == col2) {
			return;
		}

		Rational[] tempElem = elem[col1];
		elem[col1] = elem[col2];
		elem[col2] = tempElem;

		int tempOrgCol = orgCol[col1];
		orgCol[col1] = orgCol[col2];
		orgCol[col2] = tempOrgCol;
	}



}
