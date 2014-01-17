import java.util.Arrays;

public class Matrix {
	private int coreRow; // 必要行数
	private int coreCol; // 必要列数
	private Rational[][] elem; // 要素
	private int nRow; // 行数
	private int nCol; // 列数
	private int[] orgCol; // 各列に対する当初の列番号の配列

	Matrix(Rational[][] elem) {
		this.elem = elem;
		this.nRow = elem.length;
		this.nCol = elem[0].length;
		orgCol = new int[nCol];
		for (int i = 0; i < nCol; i++) {
			orgCol[i] = i;
		}
	}

	// ========== getter,setter ===========
	public Rational getRowColElem(int row, int col) {
		return elem[row][col].clone();
	}

	public void setRowColElem(int row, int col, Rational r) {
		if ((row < 0) || (nRow <= row)) {
			return;
		}

		if ((col < 0) || (nCol <= col)) {
			return;
		}

		elem[row][col] = r;
	}

	public Rational[] getRowElem(int row) {
		return Arrays.copyOf(elem[row], elem[row].length);
	}

	public Rational[] getColElem(int col) {
		Rational[] temp = new Rational[nRow];
		for (int j = 0; j < nRow; j++) {
			temp[j] = elem[j][col].clone();
		}
		return temp;
	}

	public Rational[][] getElem() {
		return Arrays.copyOf(elem, elem.length);
	}

	public int getNRow() {
		return nRow;
	}

	public int getNCol() {
		return nCol;
	}

	public void setColElem(int col, Rational[] v) {
		if ((col < 0) || (nCol <= col)) {
			return;
		}
		for (int i = 0; i < nRow; i++) {
			this.elem[i][col] = v[i].clone();
		}
	}

	public void setRowElem(int row, Rational[] v) {
		if ((row < 0) || (nRow <= row)) {
			return;
		}
		elem[row] = Arrays.copyOf(v, v.length);
	}

	public int getOrgCol(int col) {
		return orgCol[col];
	}

	public void setOrgCol(int col, int value) {
		this.orgCol[col] = value;
	}

	public int getCoreRow() {
		return coreRow;
	}

	public int getCoreCol() {
		return coreCol;
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
				if (!elem[i][j].equals(m.getRowColElem(i, j))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	protected Matrix clone() {
		Rational[][] elem = new Rational[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				elem[i][j] = this.elem[i][j].clone();
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
				elem[i][j] = this.elem[i][j].add(m.getRowColElem(i, j)).clone();
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
				elem[i][j] = this.elem[i][j].subtract(m.getRowColElem(i, j)).clone();
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
							.getRowColElem(k, j))).clone();
				}
			}
		}
		return new Matrix(elem);
	}

	public Matrix multiply(Rational r) {
		Rational[][] elem = new Rational[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				elem[i][j] = this.elem[i][j].multiply(r).clone();
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

		for (int i = 0; i < nRow; i++) {
			Rational tempElem = elem[i][col1];
			elem[i][col1] = elem[i][col2];
			elem[i][col2] = tempElem;
		}
		int tempOrgCol = orgCol[col1];
		orgCol[col1] = orgCol[col2];
		orgCol[col2] = tempOrgCol;
	}

	// =========== 部分行列処理 ==========
	public Matrix leftUpperMatrix(int row, int col) {
		if (row > nRow) {
			row = nRow;
		}
		if (col > nCol) {
			col = nCol;
		}

		Rational[][] elem = new Rational[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				elem[i][j] = this.elem[i][j].clone();
			}
		}

		return new Matrix(elem);
	}

	public Matrix rightLowerMatrix(int row, int col) {
		if (row >= nRow) {
			return null;
		}
		if (col >= nCol) {
			return null;
		}

		Rational[][] elem = new Rational[nRow - row][nCol - col];
		for (int i = 0; i < nRow - row; i++) {
			for (int j = 0; j < nCol - col; j++) {
				elem[i][j] = this.elem[i + row][j + col].clone();
			}
		}
		return new Matrix(elem);
	}

	public void replaceSubMatrix(int row, int col, Matrix m) {
		if (row > nRow) {
			return;
		}

		if (col > nCol) {
			return;
		}

		for (int i = 0; i < nRow - row; i++) {
			for (int j = 0; j < nCol - col; j++) {
				elem[row + i][col + j] = m.getRowColElem(i, j).clone();
			}
		}
	}

	// ============ ここから5章の内容 =============
	public void eliminate(int row, int col) {
		if (this.elem[row][col].equals(new Rational(0))) {
			return;
		}
		multiplyRow(row, this.elem[row][col].inverse());

		for (int i = 0; i < this.nRow; i++) {
			if (i == row) {
				continue;
			}
			addMultipliedRow(row, this.elem[i][col].multiply(new Rational(-1)), i);
		}
	}

	public void echelonForm() {
		if (this.nRow == 1) {
			return;
		}

		if (this.elem[0][0].equals(new Rational(0))) {
			// 左上要素が0の場合
			for (int i = 0; i < this.nRow; i++) {
				if (!this.elem[i][0].equals(new Rational(0))) {
					// i行0列要素が0でないなら入れ替え
					exchangeRow(0, i);
					echelonForm();
					return;
				}
				// 0列目のすべての要素が0
				if (this.nCol == 1) {
					// 一列しかないなら終了
					return;
				} else {
					// 一番左の列以外の部分行列について同様の処理をして置換
					Matrix m = this.rightLowerMatrix(0, 1);
					if (m != null) {
						m.echelonForm();
						replaceSubMatrix(0, 1, m);
					}
				}
			}
		} else {
			// 左上要素が0ではない場合
			eliminate(0, 0);
			Matrix m = this.rightLowerMatrix(1, 1);
			if (m != null) {
				m.echelonForm();
				replaceSubMatrix(1, 1, m);
			}
		}
	}

	public void rebuiltForm() {
		Matrix tempMat = this.clone();
		int[] colClasses = new int[this.nCol];

		// 各列を分類
		// 1.基底変数に対応する列:仕様書の(2)
		// 2.非基底変数に対応する列:仕様書の(3)
		// 3.すべての要素が0となる列:仕様書の(1)
		// に分類
		// for文の都合上仕様書と少し変更
		int counter = 0;
		this.coreRow = 0;
		this.coreCol = 0;
		for (int i = 0; i < this.nCol; i++) {
			if (nonZeroRow(i) == -1) {
				colClasses[i] = 3;
			} else if (nonZeroRow(i) == counter) {
				colClasses[i] = 1;
				this.coreRow++;
				this.coreCol++;
				counter++;
			} else {
				colClasses[i] = 2;
				this.coreCol++;
			}
		}

		// 分類に従って入れ替え
		int col = 0;
		for (int cl = 1; cl <= 3; cl++) {
			for (int i = 0; i < this.nCol; i++) {
				if (colClasses[i] == cl) {
					this.setColElem(col, tempMat.getColElem(i));
					this.setOrgCol(col, tempMat.getOrgCol(i));
					col++;
				}
			}
		}

	}

	private int nonZeroRow(int col) {
		int result = -1;
		for (int i = 0; i < nRow; i++) {
			if (!this.elem[i][col].equals(new Rational(0))) {
				result = i;
			}
		}
		return result;
	}

	public void leftIdentityForm() {
		// 左方正方行列の対角成分を1に
		multiplyRow(coreRow - 1, this.elem[coreRow - 1][coreRow - 1].inverse());

		// 対角成分以外を0に
		for (int i = coreRow - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				addMultipliedRow(i, this.elem[j][i].multiply(new Rational(-1)), j);
			}
		}
	}
}
