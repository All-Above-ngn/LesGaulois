package village_gaulois;

import objets.Trophee;
import objets.Equipement;
import personnages.Gaulois;

public class Musee {

	private Trophee[] trophees = new Trophee[200];
	private int nbTrophees = 0;

	public void donnerTrophees(Gaulois gaulois, Equipement equipement) {
		if (nbTrophees < trophees.length) {
			trophees[nbTrophees] = new Trophee(gaulois, equipement);
			nbTrophees++;
		}
	}

	public String extraireInstructionsOCaml() {
		StringBuilder instruction = new StringBuilder("let musee = [\n");
		for (int i = 0; i < nbTrophees; i++) {
			instruction.append("\"");
			instruction.append(trophees[i].donnerNom());
			instruction.append("\", \"");
			instruction.append(trophees[i].getEquipement());
			instruction.append("\"");
			if (i < nbTrophees - 1) {
				instruction.append(";\n");
			}
		}
		instruction.append("\n]");
		return instruction.toString();
	}
}
