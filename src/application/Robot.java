package application;

/**
 * classe qui modelise un robot
 * 
 * @author theodorbrown
 */

public class Robot {

	private int posX;
	private int posY;
	private Grille grille;
	private Dechet dechet;
	private int compteur;

	public Robot(Grille a) {
		this.posX = 0;
		this.posY = 0;
		this.grille = a;
	}

	public Robot(int departX, int departY, Grille g) {
		this.grille = g;

		// la position prevue est accessible ?
		boolean estAccessible = this.grille.etreAccessible(departX, departY); // je teste si les cases sont accessbiles
																				// en depart X/Y

		if (estAccessible) {
			this.posX = departX; // je position le robot a ces coordonnees
			this.posY = departY;
		} else {
			this.posX = 0; // sinon je le position a 0
			this.posY = 0;
		}
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Grille getGrille() {
		return this.grille;
	}

	public void setCompteur(int c) {
		this.compteur = c;
	}

	public int comptageDechet() {
		return this.compteur;
	}

	public void possedeDechet(Dechet d) {
		this.dechet = d;
	}

	public void seDeplacer(int dx, int dy) {

		int cibleX = this.posX + dx;
		int cibleY = this.posY + dy;

		if (this.grille.etreAccessible(cibleX, cibleY)) { // et si on est encore dans l'Grille et pas dans un mur
			this.posX = cibleX; // alors on change les coordonnees du Robot
			this.posY = cibleY;
		}

		// si le robot a un dechet
		if (this.dechet != null) {
			compteur += 1;
			this.dechet = null;
		}

	}

}
