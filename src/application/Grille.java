package application;

/**
 * classe qui modelise la zone de jeu et gère les éventuels obstacles
 * 
 * @author theodorbrown
 */

public class Grille {

	private int[][] tab;
	boolean fini1, fini2, fini3, fini4 = false;
	boolean lvl1, lvl2, lvl3, lvl4, lvl5 = false;

	private int[][] tableau5 = { // creation d'un tableau a double entree

			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			// colonne 1
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
			// colonne 2
			{ 0, 1, 0, 0, 0, 1, 1, 0, 1, 0 },
			// colonne 3
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			// colonne 4
			{ 0, 1, 0, 1, 0, 0, 0, 0, 1, 0 },
			// colonne 5
			{ 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
			// colonne 6
			{ 0, 1, 1, 1, 0, 1, 1, 0, 0, 1 },
			// colonne 7
			{ 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 }, // un mur fabrique (case pleine = 1)
			// colonne 8,
			{ 1, 1, 0, 0, 1, 1, 0, 1, 0, 0 },
			// colonne 9
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 } };
	// colonne 10

	private int[][] tableau4 = { // creation d'un tableau a double entree
			// colonne 0
			{ 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
			// colonne 1
			{ 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 },
			// colonne 2
			{ 1, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
			// colonne 3
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
			// colonne 4
			{ 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
			// colonne 5
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			// colonne 6
			{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
			// colonne 7
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 }, // un mur fabrique (case pleine = 1)
			// colonne 8,
			{ 1, 1, 0, 0, 1, 1, 1, 1, 0, 0 },
			// colonne 9
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	private int[][] tableau3 = { // creation d'un tableau a double entree
			// colonne 0
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 1
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 2
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
			// colonne 3
			{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
			// colonne 4
			{ 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
			// colonne 5
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			// colonne 6
			{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
			// colonne 7
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, // un mur fabrique (case pleine = 1)
			// colonne 8,
			{ 0, 0, 0, 0, 1, 0, 0, 1, 1, 0 },
			// colonne 9
			{ 1, 1, 0, 0, 0, 0, 0, 0, 1, 0 } };
	private int[][] tableau2 = { // creation d'un tableau a double entree
			// colonne 0
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			// colonne 1
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
			// colonne 2
			{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
			// colonne 3
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			// colonne 4
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 5
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 6
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 7
			{ 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 }, // un mur fabrique (case pleine = 1)
			// colonne 8,
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 9
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private int[][] tableau1 = { // creation d'un tableau a double entree
			// colonne 0
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 1
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 2
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 3
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 4
			{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
			// colonne 5
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 6
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 7
			{ 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 }, // un mur fabrique (case pleine = 1)
			// colonne 8,
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			// colonne 9
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private int tailleX;
	private int tailleY;

	public Grille() {
		this.tailleX = 10;
		this.tailleY = 10;

	}

	public int getTailleX() { // methode qui me permet de recuperer tailleX (dimension horiz.)
		return this.tailleX;
	}

	public int getTailleY() { // methode qui me permet de recuperer tailleY (dimension verti.)
		return this.tailleY;
	}

	public boolean etreAccessible(int x, int y) {
		// je dis que la case est accessible de base (true)
		boolean accessible = true;

		if (x < 0 || x >= this.tailleX || y < 0 || y >= this.tailleY || this.tab[x][y] == 1) {
			accessible = false; // si un des cas precedent s'est produit, la case n'est pas accessible (false)
		}

		return accessible;
	}

	public void selectNiveau(int iniv) {
		switch (iniv) {
		case 1:
			this.tab = tableau1;
			this.lvl1 = true;
			break;
		case 2:
			if (niveauFini1() == true) {
				this.tab = tableau2;
				this.lvl2 = true;
			}
			break;
		case 3:
			if (niveauFini2() == true) {
				this.tab = tableau3;
				this.lvl3 = true;
			}
			break;
		case 4:
			if (niveauFini3() == true) {
				this.tab = tableau4;
				this.lvl4 = true;
			}
			break;
		case 5:
			if (niveauFini4() == true) {
				this.tab = tableau5;
				this.lvl5 = true;
			}
			break;
		default:
			break;
		}
	}

	public boolean lvl1s() {
		return this.lvl1;
	}

	public boolean lvl2s() {
		return this.lvl2;
	}

	public boolean lvl3s() {
		return this.lvl3;
	}

	public boolean lvl4s() {
		return this.lvl4;
	}

	public void SetNiveauFini1(boolean b) {
		this.fini1 = b;
	}

	public void SetNiveauFini2(boolean b) {
		this.fini2 = b;
	}

	public void SetNiveauFini3(boolean b) {
		this.fini3 = b;
	}

	public void SetNiveauFini4(boolean b) {
		this.fini4 = b;
	}

	public boolean niveauFini1() {
		return fini1;
	}

	public boolean niveauFini2() {
		return fini2;
	}

	public boolean niveauFini3() {
		return fini3;
	}

	public boolean niveauFini4() {
		return fini4;
	}

}
