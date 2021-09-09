package graphisme;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import application.Dechet;
import application.Enregistrement;
import application.Grille;
import application.Objectif;
import application.Robot;

/**
 * classe destinee a modeliser un environnement constitue du robot c'est un
 * observable pour gerer l'affichage
 */
public class Modele extends Observable {

	Robot robot;

	Grille env;

	Dechet[] dechet;

	Enregistrement enr;

	Objectif objectif;

	Gui g;

	public Modele() {
		// creer le labyrinthe
		env = new Grille();
		env.selectNiveau(1);
		enr = new Enregistrement();
		// modele
		initialiserRobot();
		initialiserDechets();
		initialiserObjectif();

	}

	/**
	 * appelle la construction d'une interface graphique avec les boutons
	 */
	public void creerInterface() {
		g = new Gui(this);
	}

	/**
	 * permet d'initialiser les Robots du monde
	 */
	public void initialiserRobot() {
		Random r = new Random();

		int x = 8;// en dure
		int y = 0;
		while (!env.etreAccessible(x, y)) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		this.robot = new Robot(x, y, env);
	}

	public void initialiserObjectif() {
		Random r = new Random();

		int x = 9; // en dure
		int y = 5;
		while (!env.etreAccessible(x, y)) {
			x = r.nextInt(10);
			y = r.nextInt(10);
		}
		this.objectif = new Objectif(x, y, env);
	}

	public void initialiserDechets() {
		ArrayList<Number> al = new ArrayList<>();
		Random r = new Random();
		boolean passpa = false;
		this.dechet = new Dechet[4];// nb dechet dans la grille

		for (int i = 0; i < dechet.length; i++) {

			int x = r.nextInt(10);
			int y = r.nextInt(10);
			int xi = x;
			int yi = y;

			Number n = new Number(xi, yi);

			if (al.contains(n) || !env.etreAccessible(xi, yi) || (xi == 8 && yi == 0) || (xi == 9 && yi == 5)) {
				passpa = true;
				while (passpa || !env.etreAccessible(xi, yi) || (xi == 8 && yi == 0) || (xi == 9 && yi == 5)) {
					xi = r.nextInt(10);
					yi = r.nextInt(10);

					Number ni = new Number(xi, yi);

					if (al.contains(ni) || !env.etreAccessible(xi, yi) || (xi == 8 && yi == 0)
							|| (xi == 9 && yi == 5)) {
						passpa = true;
					} else {
						passpa = false;
						al.add(ni);
					}
				}
			} else {
				al.add(n);
			}
			this.dechet[i] = new Dechet(al.get(i).getx(), al.get(i).gety(), env);
		}

	}

	/**
	 * methode pour deplacer un Robot du monde On passe par le modele pour mettre a
	 * jour la vue
	 */

	public void deplacerRobot(int dx, int dy) {

		this.robot.seDeplacer(dx, dy);
		this.setChanged();
		this.notifyObservers();
	}

	public void accepter() {// ramasser dechet ou pile ou se trouver sur l'objectif

		for (int i = 0; i < dechet.length; i++) {

			if (dechet[i].etreRamasse(robot)) {
				robot.possedeDechet(dechet[i]);
			}
		}
		objectif.memePosRobot(robot);
		this.setChanged();
		this.notifyObservers();
	}

	public void setStartAgain() {

		this.initialiserDechets();
		this.initialiserRobot();
		this.setChanged();
		this.notifyObservers();
		while (!enr.getContenuEnr().isEmpty()) {
			enr.delete();
		}
	}

	public void updateEnr(String s) {

		enr.ajout(s);
		this.setChanged();
		this.notifyObservers();
	}

	public void playEnr(int j) {

		enr.play(robot, this, j);
		this.setChanged();
		this.notifyObservers();
	}

}