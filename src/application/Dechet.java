package application;

public class Dechet {

	private int posx;
	private int posy;
	private Grille grille;
	private Robot robot;
	private boolean dechetCoche;

	public Dechet(Grille g) {
		this.posx = 0;
		this.posy = 0;
		this.grille = g;
	}

	public Dechet(int x, int y, Grille g) {
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

	public int getPosY() {
		return posy;
	}

	public int getPosX() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public boolean dessinCoche() {
		return dechetCoche;
	}

	public boolean etreRamasse(Robot r) {

		int xr;
		int yr;

		if (r != null) {
			xr = r.getPosX();
			yr = r.getPosY();

			if (this.robot == null && this.posx == xr && this.posy == yr) {

				this.robot = r;
				dechetCoche = true;
				return true;
			}
		}
		return false;
	}
}
