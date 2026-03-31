package village_gaulois;

import objets.Equipement;
import objets.Trophee;
import personnages.Gaulois;

public class Musee {
	private static final int NB_MAX_TROPHEES = 200;
	private Trophee[] trophees = new Trophee[NB_MAX_TROPHEES];
	private int nbTrophees = 0;

	public void donnerTrophees(Gaulois gaulois, Equipement trophee) {
		if (nbTrophees < NB_MAX_TROPHEES) {
			this.trophees[nbTrophees] = new Trophee(gaulois, trophee);
			nbTrophees++;
		} else {
			System.out.println("Ce musee n'a plus de place!");
		}
	}

	public String extraireInstructionsOCaml() {
		String instruction = "let musee = [\n";
		for (int i = 0; i < nbTrophees; i++) {
			instruction += "\"" + this.trophees[i].donnerNom() + "\", \"" + this.trophees[i].getEquipement() + "\";\n";
		}
		instruction += "]";
		return instruction;
	}
}
