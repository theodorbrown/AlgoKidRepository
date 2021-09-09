package graphisme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import application.Dechet;
import application.Objectif;
import application.Robot;

/**
 * classe qui permet d'afficher un modele constitue d'un robot
 */
public class Afficheur extends JPanel implements Observer {

	/**
	 * constante correspondant la taille des cases
	 */
	public final static int TAILLE = 50;

	/**
	 * le modele a afficher
	 */
	private Modele m = null;

	/**
	 * construit un afficheur de robot a partir du modele
	 * 
	 * @param modele modele a afficher
	 */
	public Afficheur(Modele modele) {
		// recuperation du modele
		this.m = modele;
		// initialisation du Panel
		int tailleX = m.env.getTailleX();
		int tailleY = m.env.getTailleY();
		Dimension taille = new Dimension(TAILLE * tailleX, TAILLE * tailleY);// 500,500
		this.setPreferredSize(taille);
	}

	/**
	 * mis a jour de l'affichage : affiche du robot
	 */
	public void paint(Graphics g) {
		super.paint(g);

		// dessine grille
		dessinerGrille(g);

		// dessin Robot

		for (int i = 0; i < m.dechet.length; i++) {
			dessinerDechets(g, m.dechet);
		}

		dessinerObjectif(g, m.objectif);

		dessinerRobot(g, m.robot);

	}

	public static SpriteSheet sheet;
	public static Sprite robot, fond, dechet, dechet2, dechet3, tic, objectif;

	private void dessinerGrille(Graphics g) {

		sheet = new SpriteSheet("/image.png");
		fond = new Sprite(sheet, 2, 1);
		g.setColor(Color.black);

		// pour le sprite de fond et pour le dessins de la grille en noir
		for (int i = 0; i < m.env.getTailleX(); i++)
			for (int j = 0; j < m.env.getTailleY(); j++) {
				g.drawImage(Afficheur.fond.getBufferedImage(), 0, 0, m.env.getTailleX() * TAILLE,
						m.env.getTailleY() * TAILLE, null, null);

			}

		for (int i = 0; i < m.env.getTailleX(); i++)
			for (int j = 0; j < m.env.getTailleY(); j++) {
				if (!m.env.etreAccessible(i, j)) {
					g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
				} else {
					g.drawRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
				}
			}
	}

	private void dessinerRobot(Graphics g, Robot Robot) {
		int decaly = Robot.getPosY() * TAILLE + 10;
		int decalx = Robot.getPosX() * TAILLE + 10;

		sheet = new SpriteSheet("/image.png");
		robot = new Sprite(sheet, 1, 1);
		g.drawImage(Afficheur.robot.getBufferedImage(), decalx - 8, decaly - 8, this.getHeight() / 10,
				this.getWidth() / 10, null);

	}

	private void dessinerDechets(Graphics g, Dechet[] d) {
		dechet = new Sprite(sheet, 3, 1);
		dechet2 = new Sprite(sheet, 4, 1);
		dechet3 = new Sprite(sheet, 5, 1);
		tic = new Sprite(sheet, 1, 2);

		for (int i = 0; i < d.length / 2 - 1; i++) {
			int decaly = d[i].getPosY() * TAILLE + 10;
			int decalx = d[i].getPosX() * TAILLE + 10;
			// System.out.println(0 + " s1 " + (d.length / 2 - 1));

			g.drawImage(Afficheur.dechet.getBufferedImage(), decalx - 10, decaly - 10, this.getHeight() / 10,
					this.getWidth() / 10, null);
			if (d[i].dessinCoche()) {// methode appelee si le robot veut ramasser le dechet
				g.drawImage(Afficheur.tic.getBufferedImage(), decalx - 10, decaly - 10, this.getHeight() / 10,
						this.getWidth() / 10, null);
			}
		}

		for (int j = d.length / 2 - 1; j < d.length / 2 + 1; j++) {
			int decaly2 = d[j].getPosY() * TAILLE + 10;
			int decalx2 = d[j].getPosX() * TAILLE + 10;
			// System.out.println((d.length / 2 - 1) + " s2 " + (d.length / 2 + 1));

			g.drawImage(Afficheur.dechet2.getBufferedImage(), decalx2 - 10, decaly2 - 10, this.getHeight() / 10,
					this.getWidth() / 10, null);

			if (d[j].dessinCoche()) {// methode appelee si le robot veut ramasser le dechet
				g.drawImage(Afficheur.tic.getBufferedImage(), decalx2 - 10, decaly2 - 10, this.getHeight() / 10,
						this.getWidth() / 10, null);

			}
		}

		for (int k = d.length / 2 + 1; k < d.length; k++) {
			int decaly3 = d[k].getPosY() * TAILLE + 10;
			int decalx3 = d[k].getPosX() * TAILLE + 10;
			// System.out.println((d.length / 2 + 1) + " s3 " + (d.length));

			g.drawImage(Afficheur.dechet3.getBufferedImage(), decalx3 - 10, decaly3 - 10, this.getHeight() / 10,
					this.getWidth() / 10, null);

			if (d[k].dessinCoche()) {// methode appelee si le robot veut ramasser le dechet
				g.drawImage(Afficheur.tic.getBufferedImage(), decalx3 - 10, decaly3 - 10, this.getHeight() / 10,
						this.getWidth() / 10, null);

			}
		}

	}

	private void dessinerObjectif(Graphics g, Objectif oj) {
		int decaly = oj.getPosy() * TAILLE + 10;
		int decalx = oj.getPosx() * TAILLE + 10;

		sheet = new SpriteSheet("/image.png");
		objectif = new Sprite(sheet, 2, 2);
		g.drawImage(Afficheur.objectif.getBufferedImage(), decalx - 8, decaly - 8, this.getHeight() / 10,
				this.getWidth() / 10, null);

	}

	@Override
	/**
	 * methode de mise a jour quand observer est notifie
	 */
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}