package personnages;

public class Romain {
	private String nom;
	private String force;

	public Romain(String nom, String force) {
		this.nom = nom;
		this.force = force;
	}

	public String getNom() {
		return nom;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte +"\"");
		
	}

	private String prendreParole() {
		return "Le romaine " + nom + " : ";
	}
}

