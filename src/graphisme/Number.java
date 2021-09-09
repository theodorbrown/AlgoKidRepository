package graphisme;

/*
 * classe qui génère un couple de coordonnées aléatoire différent de tout ceux déjà construits
 * on utilise cette classe pour générer les déchets.
 */
public class Number {
	private int x;
	private int y;

	public Number(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Number() {
		// TODO Auto-generated constructor stub
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object o) {// redéfinition pour notre usage de coordonées
		if (o == null) {
			return false;
		}
		if (!(o instanceof Number)) {
			return false;
		}
		return (x == ((Number) o).x && y == ((Number) o).y);
	}
}
