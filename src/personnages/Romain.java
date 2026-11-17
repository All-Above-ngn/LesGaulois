package personnages;

import objets.Equipement;

public class Romain {
	private String nom;
	private int force;

	private Equipement[] equipements = new Equipement[2];
	private int nbEquipement = 0;

	// TP3
	//

	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		assert isInvariantVerified();
	}

	public int getForce() {
		return force;
	}

	public String getNom() {
		return nom;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");

	}

	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}

//	public void recevoirCoup(int forceCoup) {
//		assert forceCoup > 0;
//		int forceAvant = force;
//		force = force - forceCoup;
//		if (force < 1) {
//			parler("J'abandonne");
//		} else {
//			parler("Aïe !");
//		}
//		assert force < forceAvant;
//		assert isInvariantVerified();
//	}

//TP3

	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;

		forceCoup = calculerResistanceEquipement(forceCoup);
		force -= forceCoup;

		if (force <= 0) {
			force = 0;
			equipementEjecte = ejecterEquipement();
			parler("J'abandonne...");
		} else {
			parler("Aïe");
		}

		return equipementEjecte;
	}

	
	private int calculerResistanceEquipement(int forceCoup) {
	    StringBuilder texte = new StringBuilder("Ma force est de ").append(force);
	    texte.append(", et la force du coup est de ");
	    texte.append(forceCoup);
	    int resistanceEquipement = 0;
	    for (int i = 0; i < nbEquipement; i++) {

	        if (equipements[i] == Equipement.BOUCLIER) {
	            resistanceEquipement += 6;

	        } else if (equipements[i] == Equipement.CASQUE) {
	            resistanceEquipement += 3;
	        }
	    }
	    if (resistanceEquipement > 0) {
	        texte.append("\nMais heureusement, grâce à mon équipement ");

	        if (forceCoup <= resistanceEquipement) {
	            texte.append("sa force a été complètement absorbée.");

	        } else {
	            texte.append("sa force a été diminuée de ");
	            texte.append(resistanceEquipement);
	            texte.append(" !");
	        }
	    }

	    parler(texte.toString());
	    forceCoup -= resistanceEquipement;
	    if (forceCoup < 0) {
	        forceCoup = 0;
	    }
	    return forceCoup;
	}

	private Equipement[] ejecterEquipement() {
		System.out.println("L'équipement de " + nom + " s'envole sous la force du coup.");
		Equipement[] equipementEjecte = new Equipement[nbEquipement];
		for (int i = 0; i < nbEquipement; i++) {
			equipementEjecte[i] = equipements[i];
			equipements[i] = null;
		}
		nbEquipement = 0;
		return equipementEjecte;
	}

	//

	private boolean isInvariantVerified() {
		return force >= 0;
	}

	public void sEquiper(Equipement equipement) {
		switch (nbEquipement) {

		case 0:
			ajouterEquipement(equipement);
			break;

		case 1:
			if (equipements[0] == equipement) {
				System.out.println("Le soldat " + nom + " possède déjà un " + equipement + ".");
			} else {
				ajouterEquipement(equipement);
			}
			break;

		default:
			System.out.println("Le soldat " + nom + " est déjà bien protégé !");
		}
	}

	private void ajouterEquipement(Equipement equipement) {
		equipements[nbEquipement] = equipement;
		nbEquipement++;
		System.out.println("Le soldat " + nom + " s'équipe avec un " + equipement + ".");
	}

	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 6);
		System.out.println(Equipement.CASQUE);
		System.out.println(Equipement.BOUCLIER);

		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.CASQUE);
	}

}
