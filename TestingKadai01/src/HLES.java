
public class HLES {
	private Matrix a;
	private Rational[] x;

	public HLES(Matrix a){
		this.a = a.clone();

		x = new Rational[a.getNCol()];
	}

	public Rational[] getX(){
		return x.clone();
	}

	public int solve(){
		a.echelonForm();
		a.rebuiltForm();

		// 不定変数には0を代入
		for(int i = a.getCoreCol();i < a.getNCol();i++){
			x[a.getOrgCol(i)] = new Rational(0);
		}

		if(a.getCoreRow() == a.getCoreCol()){
			for(int i = 0;i < a.getNCol();i++){
				x[a.getOrgCol(i)] = new Rational(0);
			}
			return 0; // 正常終了（自明解）
		}

		if(a.getCoreRow() > a.getCoreCol()){
			return -1; // 異常終了
		}

		a.leftIdentityForm();
		Matrix d = a.leftUpperMatrix(a.getCoreRow(), a.getCoreCol());

		// 非基底変数には1を代入
		for(int i = a.getCoreRow();i < a.getCoreCol();i++){
			x[a.getOrgCol(i)] = new Rational(1);
		}

		// 基底変数を導出
		// y = (-1)*a2*z
		Matrix a2 = d.rightLowerMatrix(0, a.getCoreRow());
		int size = a.getCoreCol()-a.getCoreRow();
		Rational[] z = new Rational[size];
		for(int i = 0;i < size;i++){
			z[i] = new Rational(-1);
		}
		Rational[] y = a2.substVector(z);
		for(int i = 0;i < a.getCoreRow();i++){
			x[a.getOrgCol(i)] = y[i];
		}

		return 1; // 正常終了（基本解）
	}
}
