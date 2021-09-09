package application;

import java.util.ArrayList;

import graphisme.Modele;

public class Enregistrement {

	private ArrayList<String> contenu = new ArrayList<>();

	public Enregistrement() {
	}

	/*
	 * ajouter un déplacement sous forme de string (ex: up)
	 */
	public void ajout(String s) {
		contenu.add(s);
	}

	/*
	 * permet de voir le contenu de la liste de déplacements
	 */
	public void see() {
		System.out.println(contenu);
	}

	/*
	 * méthode qui permet de savoir dans quel sens le robot doit se déplacer (pas
	 * vraiment un play)
	 */
	public void play(Robot r, Modele m, int i) {
		switch (contenu.get(i)) {
		case "haut":
			r.seDeplacer(0, -1);
			break;
		case "bas":
			r.seDeplacer(0, 1);
			break;
		case "gauche":
			r.seDeplacer(-1, 0);
			break;
		case "droite":
			r.seDeplacer(1, 0);
			break;
		default:
			break;
		}
	}

	/*
	 * supprime le contenu de l'arraylist
	 */
	public void delete() {
		for (int i = 0; i < contenu.size(); i++) {
			contenu.remove(i);
		}
	}

	/*
	 * retourne le contenu de l'arraylist (la liste des déplacements)
	 */
	public ArrayList<String> getContenuEnr() {
		return this.contenu;
	}
}
