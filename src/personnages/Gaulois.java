package personnages;

import java.util.Iterator;

import objets.Equipement;
import objets.Trophee;
import village_gaulois.Musee;
import village_gaulois.Village;

public class Gaulois {
	private String nom;
	private int effetPotion = 1;
	private Village village;
	private int force;
	private int nbTrophees;
	private Equipement[] trophees = new Equipement[100];

	public Gaulois(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}

	public String getNom() {
		return nom;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");
	}

	private String prendreParole() {
		return "Le gaulois " + nom + " : ";
	}

	public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
		Equipement[] tropheesObtenus = romain.recevoirCoup((force / 2) * effetPotion);
		effetPotion--;
		if (effetPotion < 1) {
			effetPotion = 1;
		}
		for (int i = 0; tropheesObtenus != null && i < tropheesObtenus.length; i++, nbTrophees++) {
			this.trophees[nbTrophees] = tropheesObtenus[i];
		}
	}

	public void boirePotion(int forcePotion) {
		this.effetPotion = forcePotion;
	}

	public void sePresenter() {
		String introduction = "Bonjour, je m'appelle ";
		if (village == null) {
			parler(introduction + getNom() + ". Je voyage de villages en villages.");
		} else if (village.getChef() == this) {
			parler(introduction + getNom() + ". Je suis le chef du village : " + village.getNom() + ".");
		} else {
			parler(introduction + getNom() + ". J'habite le village : " + village.getNom() + ".");
		}
	}

	public void faireUneDonnation(Musee musee) {
		if (nbTrophees > 0) {
			this.parler("Je donne au musee tout mes trophees :");
			while (nbTrophees > 0) {
				Equipement trophee = this.trophees[--nbTrophees];
				System.out.println("- " + trophee);
				musee.donnerTrophees(this, trophee);
			}
		}
	}

	public static void main(String[] args) {
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(asterix);
	}
}
