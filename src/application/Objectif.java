package application;

public class Objectif {
	private int posx;
	private int posy;
	private Grille grille;

	public Objectif(Grille g) {
		this.posx = 0;
		this.posy = 0;
		this.grille = g;
	}

	public Objectif(int x, int y, Grille g) {
		this.grille = g;

		boolean ok = this.grille.etreAccessible(x, y);

		if (ok) {
			this.posx = x; // je position le robot a ces coordonnees
			this.posy = y;
		} else {
			this.posx = 0; // sinon je le position a 0
			this.posx = 0;
		}
	}

	public int getPosy() {
		return posy;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public boolean memePosRobot(Robot r) {// savoir si l'objectif et le robot sont sur la meme position
		if (this.posx == r.getPosX() && this.posy == r.getPosY()) {
			return true;
		}
		return false;
	}
}
