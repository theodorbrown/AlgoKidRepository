package graphisme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import application.Objectif;
import application.Robot;

/**
 * permet de controler un Robot
 * 
 * @author theodorbrown
 *
 */

public class ControlRobot extends JPanel implements Observer {

	/**
	 * le modele manipule
	 */
	private Modele modele;
	private JTextPane descriptif;
	public int j;
	String message = "";
	String contenuPlay = "";
	JButton jbs, jb1, jb2, jb3, jb4;
	Objectif obj;
	Robot r;

	/**
	 * constructeur de controleur
	 * 
	 * @param i indice du robot
	 * @param m modele correspondant (pour observable)
	 */
	public ControlRobot(Modele m) {

		m.addObserver(this);// maj du textpane par ex
		this.modele = m;

		JPanel p = new JPanel();// panel 1
		this.add(p);

		p.setPreferredSize(new Dimension(280, 480));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		// creation JLabel au dessus
		descriptif = new JTextPane();
		descriptif.setEditable(false);
		descriptif.setBackground(Color.lightGray);
		descriptif.setLayout(new GridBagLayout());

		p.add(descriptif);

		// met ajour contenu
		update(null, null);

		// ajoute les boutons deplacement
		JPanel boutonsDep = new JPanel();
		boutonsDep.setLayout(new GridLayout(4, 0));// pour donner le bon guidage

		boutonsDep.add(new JPanel());
		boutonsDep.add(creerBoutonHaut());

		boutonsDep.add(new JPanel());
		boutonsDep.add(creerBoutonGauche());

		boutonsDep.add(new JPanel());
		boutonsDep.add(creerBoutonDroite());

		boutonsDep.add(new JPanel());
		boutonsDep.add(creerBoutonBas());

		boutonsDep.add(new JPanel());
		p.add(boutonsDep);

		JPanel boutonautres = new JPanel();
		boutonautres.setLayout(new BoxLayout(boutonautres, 0));

		boutonautres.add(new JPanel());
		boutonautres.add(creerBoutonDepart());

		boutonautres.add(new JPanel());
		boutonautres.add(creerBoutonPlay());

		boutonautres.add(new JPanel());
		p.add(boutonautres);// , BorderLayout.CENTER);

	}

	public void playSon(String chemin) {
		InputStream son;
		try {
			son = new FileInputStream(new File(chemin));
			//AudioStream audio = new AudioStream(son);
			//AudioPlayer.player.start(audio);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "erreur");
		}
	}

	private JButton creerBoutonHaut() {
		jb1 = new JButton("Haut");
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// modele.deplacerRobot(0, -1);
				modele.updateEnr("haut");
				playSon("sons/clic.wav");

			}
		});
		return (jb1);
	}

	private JButton creerBoutonBas() {
		jb2 = new JButton("Bas");
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// modele.deplacerRobot(0, 1);
				modele.updateEnr("bas");
				playSon("sons/clic.wav");

			}
		});
		return (jb2);
	}

	private JButton creerBoutonDroite() {
		jb3 = new JButton("Droite");
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// modele.deplacerRobot(1, 0);
				modele.updateEnr("droite");
				playSon("sons/clic.wav");

			}
		});
		return (jb3);
	}

	private JButton creerBoutonGauche() {
		jb4 = new JButton("Gauche");
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// modele.deplacerRobot(-1, 0);
				modele.updateEnr("gauche");
				playSon("sons/clic.wav");

			}
		});
		return (jb4);
	}

	private JButton creerBoutonDepart() {
		JButton b = new JButton("Recommencer");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setStartAgain();
				modele.robot.setCompteur(0);
				jbs.setEnabled(true);// JButton de la classe
				jb1.setEnabled(true);
				jb2.setEnabled(true);
				jb3.setEnabled(true);
				jb4.setEnabled(true);
				// contenuPlay = "";
				modele.deplacerRobot(0, 0);
				playSon("sons/clic2.wav");

			}
		});
		return (b);
	}

	private JButton creerBoutonPlay() {
		jbs = new JButton("Lancer");

		Timer chrono = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playSon("sons/robot.wav");
				j++;

				modele.playEnr(j);
				modele.accepter();

				// contenuPlay += modele.enr.getContenuEnr().get(j);
				modele.deplacerRobot(0, 0);

				// System.out.println("pass1"); //att codé en dure 4
				if (j + 1 == modele.enr.getContenuEnr().size() || (r.comptageDechet() == 4 && obj.memePosRobot(r))) {
					// un peu de répétitions dans le if mais picc
					// System.out.println("pass2");
					((Timer) e.getSource()).stop();// cast peut pas faire chrono.stop();
					while (!modele.enr.getContenuEnr().isEmpty()) {
						modele.enr.delete();
					}
				} else {
					// System.out.println("pass3");
					// contenuPlay += " + ";
					modele.deplacerRobot(0, 0);

				}
			}
		});

		jbs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modele.enr.getContenuEnr().size() == 0) {
					playSon("sons/clic2.wav");
					message += "Impossible, tu n'as pas donné de directions.";
					message += "\n";
					message += "\n";
					message += "Clique sur les boutons de déplacements et essaie de ramasser tous les déchets !";
					descriptif.setText(message);
					message = "";

					// System.out.println("pass0");
				} else {
					j = 0;
					jbs.setEnabled(false);// JButton de la classe
					jb1.setEnabled(false);
					jb2.setEnabled(false);
					jb3.setEnabled(false);
					jb4.setEnabled(false);
					modele.playEnr(j);
					modele.accepter();

					// System.out.println("pass-1");

					if (modele.enr.getContenuEnr().size() == 1) {
						// contenuPlay += modele.enr.getContenuEnr().get(j);
						modele.deplacerRobot(0, 0);

						// System.out.println("pass-2");
						while (!modele.enr.getContenuEnr().isEmpty()) {
							modele.enr.delete();
						}
					} else {
						// contenuPlay += modele.enr.getContenuEnr().get(j) + " + ";
						modele.deplacerRobot(0, 0);
						chrono.start();
						// System.out.println("pass-3");

					}
				}
			}
		});
		return (jbs);
	}

	public void resetLvls() {
		modele.robot.setCompteur(0);
		jbs.setEnabled(true);// JButton de la classe
		jb1.setEnabled(true);
		jb2.setEnabled(true);
		jb3.setEnabled(true);
		jb4.setEnabled(true);
		// contenuPlay = "";
		modele.deplacerRobot(0, 0);
	}

	@Override
	public void update(Observable o, Object arg) {
		obj = modele.objectif;
		r = modele.robot;

		message += "         Déchets ramassés: " + r.comptageDechet() + " sur 4";
		message += "\n";
		message += "\n";
		message += "Ton objectif est de ramasser tous les déchets et d'arriver sur la zone rouge !";
		message += "\n";
		message += "\n";
		message += "Il faut que ton robot effectue une suite de déplacements. Déplace ton robot pour atteindre les dêchets et dirige toi enfin vers la zone rouge !";
		message += "\n";
		message += "\n";
		message += "Pour déplacer ton robot, tu dois cliquer sur les touhces : Haut, Bas, Gauche, Droite puis lancer les déplacements.";
		message += "\n";
		message += "\n";
		message += "Pour lancer les déplacements clique sur le bouton : Lancer";
		message += "\n";
		message += "\n";
		message += "Tu peux recommencer à tout moment en cliquant sur le bouton Reconmmencer.";
		message += "\n";
		message += "\n";

		if (r.comptageDechet() == 4 && obj.memePosRobot(r)) {
			// contenuPlay = "";
			playSon("sons/aplau.wav");
			message = "";
			message += "\n";
			message += "  Tu as ramassé tous les déchets et tu as atteint l'objectif, bravo !";
			message += "\n";
			descriptif.setText(message);
			if (modele.env.lvl1s()) {
				modele.env.SetNiveauFini1(true);
				modele.g.setLvl2();

			}
			if (modele.env.lvl2s()) {
				modele.env.SetNiveauFini2(true);
				modele.g.setLvl3();
			}
			if (modele.env.lvl3s()) {
				modele.env.SetNiveauFini3(true);
				modele.g.setLvl4();
			}
			if (modele.env.lvl4s()) {
				modele.env.SetNiveauFini4(true);
				modele.g.setLvl5();
			}
		}

		descriptif.setText(message);

		// descriptif.setText(descriptif.getText());// .concat(contenuPlay)
		message = "";

	}

}
