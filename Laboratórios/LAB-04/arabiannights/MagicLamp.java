package arabiannights;

import arabiannights.FriendlyGenie;

public class MagicLamp {
	private int inicialGenies;
	private int nGenies;
	private int nDemons = 0;
	private int timesRubbed = 0;

	public MagicLamp(int nGenies) {
		this.inicialGenies = nGenies;
		this.nGenies = nGenies;
		}

	public int getInicialGenies() {
		return this.inicialGenies;
	}
	public int getGenies() {
		return this.nGenies;
	}
	public int getDemons() {
		return this.nDemons;
	}

	public Genie rub(int wishes) {
		if (this.nGenies > 0) {
			this.nGenies--;
			this.timesRubbed++;
			if (this.timesRubbed % 2 == 0) {
				return new FriendlyGenie(wishes);
			}
			return new GrumpyGenie(wishes);
		}
		return new RecyclableDemon(wishes);
	}
	
	public void feedDemon(RecyclableDemon demon) {
		this.nGenies = this.inicialGenies;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MagicLamp) {
			MagicLamp lamp = (MagicLamp) obj; 
			return this.inicialGenies == lamp.getInicialGenies() && this.nGenies == lamp.getGenies() && this.nDemons == lamp.getDemons();
		}
		return false;
	}
}