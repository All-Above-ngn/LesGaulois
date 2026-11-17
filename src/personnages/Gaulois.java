package personnages;

import village_gaulois.Village;
import objets.Equipement;
import village_gaulois.Musee;

public class Gaulois {
	private String nom;
	private int effetPotion = 1;
	private Village village;
	// TP3
	private int force;
	private int nbTrophees;
	private Equipement[] trophees = new Equipement[100];

	//
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

	// TP3
	private String prendreParole() {
		return "Le gaulois " + nom + " : ";
	}
	//

//	@Override
//	public String toString() {
//		return nom;
//
//	}

//	public void frapper(Romain romain) {
//		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
//		romain.recevoirCoup(force * effetPotion / 3);
//		if (effetPotion > 1) {
//			effetPotion--;
//		}
//	}

	public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
		Equipement[] equipementsRecuperes = romain.recevoirCoup((force / 2) * effetPotion);
		effetPotion--;
		if (effetPotion < 1) {
			effetPotion = 1;
		}
		if (equipementsRecuperes != null) {
			for (int i = 0; i < equipementsRecuperes.length && nbTrophees < trophees.length; i++) {
				if (equipementsRecuperes[i] != null) {
					trophees[nbTrophees] = equipementsRecuperes[i];
					nbTrophees++;
				}
			}
		}
	}

	public void boirePotion(int forcePotion) {
		effetPotion = forcePotion;
	}

	// TP2
	public void sePresenter() {
		StringBuilder phrase = new StringBuilder();
		if (village == null) {
			phrase.append("Je voyage de villages en villages.");
		} else if (village.getChef() == this) {
			phrase.append("Je suis le chef du village : ");
			phrase.append(village.getNom());
			phrase.append(".");
		} else {
			phrase.append("J'habite le village :").append(village.getNom()).append(".");
		}
		parler("Bonjour, je m'appelle " + nom + ". " + phrase);
	}

	public void faireUneDonnation(Musee musee) {
		if (nbTrophees > 0) {
			StringBuilder texte = new StringBuilder("Je donne au musée tous mes trophées :");
			for (int i = 0; i < nbTrophees; i++) {
				texte.append("\n                     - ");
				texte.append(trophees[i]);
				musee.donnerTrophees(this, trophees[i]);
				trophees[i] = null;
			}

			parler(texte.toString());
			nbTrophees = 0;
		}
	}

	public static void main(String[] args) {
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(asterix);

	}

}