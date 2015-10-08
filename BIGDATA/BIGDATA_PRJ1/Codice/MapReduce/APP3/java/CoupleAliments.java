package App3;

public class CoupleAliments {
		private String aliments1;
		private String aliments2;
		
		public CoupleAliments(String aliments1, String aliments2) {
			this.aliments1 = aliments1;
			this.aliments2 = aliments2;
		}

		public String getAliments1() {
			return aliments1;
		}

		public void setAliments1(String aliments1) {
			this.aliments1 = aliments1;
		}

		public String getAliments2() {
			return aliments2;
		}

		public void setAliments2(String aliments2) {
			this.aliments2 = aliments2;
		}
		
		public String toString() {
			return this.aliments1 + "," + this.aliments2;
		}

	}
