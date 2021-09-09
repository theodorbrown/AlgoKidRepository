package graphisme;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * classe chargee d'afficher la fenetre avec l'afficheur et les boutons.
 * 
 * @author theodorbrown
 *
 */
public class Gui {

	/**
	 * le modele a afficher
	 */
	private Modele m;
	JMenuItem niv2, niv3, niv4, niv5;

	public void playVideo() throws IOException, URISyntaxException {
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://www.youtube.com/watch?v=v4MZr8I8a60"));

	}

	/**
	 * le constructeur de fenetre
	 * 
	 * @param modele le modele a afficher
	 */
	public Gui(Modele modele) {
		this.m = modele;

		// creation de la frame
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// creation du Panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// creer observer et fait le lien
		Afficheur affiche = new Afficheur(m);
		m.addObserver(affiche);
		panel.add(affiche, BorderLayout.CENTER);

		// ajoute un panel sur la droite
		ControlRobot cr = new ControlRobot(modele);
		panel.add(cr, BorderLayout.WEST);

		JMenuBar menu = new JMenuBar();// onglet
		JMenu menuJeu = new JMenu("Jeu");
		JMenu menuNiv = new JMenu("Choisir un niveau");
		menu.add(menuJeu);
		menu.add(menuNiv);

		JMenuItem niv1 = new JMenuItem("Niveau 1");
		niv1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.env.selectNiveau(1);
				m.setStartAgain();
				cr.resetLvls();
			}
		});

		niv2 = new JMenuItem("Niveau 2");
		niv2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.env.selectNiveau(2);
				m.setStartAgain();
				cr.resetLvls();
			}
		});

		niv3 = new JMenuItem("Niveau 3");
		niv3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.env.selectNiveau(3);
				m.setStartAgain();
				cr.resetLvls();

			}
		});

		niv4 = new JMenuItem("Niveau 4");
		niv4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.env.selectNiveau(4);
				m.setStartAgain();
				cr.resetLvls();

			}
		});

		niv5 = new JMenuItem("Niveau 5");
		niv5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.env.selectNiveau(5);
				m.setStartAgain();
				cr.resetLvls();

			}
		});

		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem tuto = new JMenuItem("Video aide");
		tuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					playVideo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menuJeu.add(quitter);// ajoute une selec
		menuJeu.add(tuto);
		menuNiv.add(niv1);
		menuNiv.add(niv2);
		menuNiv.add(niv3);
		menuNiv.add(niv4);
		menuNiv.add(niv5);
		niv2.setEnabled(false);
		niv3.setEnabled(false);
		niv4.setEnabled(false);
		niv5.setEnabled(false);
		panel.add(menu, BorderLayout.NORTH);

		// ajout du panel
		f.setContentPane(panel);
		f.pack();
		f.setVisible(true);

	}

	public void setLvl2() {
		niv2.setEnabled(true);
	}

	public void setLvl3() {
		niv3.setEnabled(true);
	}

	public void setLvl4() {
		niv4.setEnabled(true);
	}

	public void setLvl5() {
		niv5.setEnabled(true);
	}
}
