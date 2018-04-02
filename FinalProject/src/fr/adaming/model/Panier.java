package fr.adaming.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.OneToMany;

import fr.adaming.service.ILigneCommandeService;

public class Panier implements Serializable {

	// Transfo assos Uml et Java avec ligne de commande
//	@EJB
//	ILigneCommandeService lignecommandeService;:::

	private Map<Long, LigneCommande> listlc = new HashMap<Long, LigneCommande>();
	
	
	// private List<LigneCommande> listeLignecommandepa =new ArrayList<>();

	// public Collection<LigneCommande> AjouterProduit(Produit pr, int quantite)
	// {
	// // Si l'id du produit est associer a l'id de la ligne de commande je ne
	// // creer pas de ligne de commande
	// // Si la dans la liste l'id du produit n'existe pas alors je creer une
	// // ligne de commande
	// // if(listeLignecommande.get(0).getProduit().getId()==0);
	//
	//// int verif = 0;
	//// for (LigneCommande ligne : listlc) {
	// if (ligne.getProduit().getId() == pr.getId()) {
	// ligne.setQuantite(ligne.getQuantite() + quantite);
	//
	// verif = 1;
	// break;
	// }
	//
	// }
	//
	// if (verif == 0) {
	// LigneCommande lc = new LigneCommande();
	// lc.getProduit();
	// lc.setQuantite(quantite);
	// lc.setPrix(pr.getPrix());
	// listeLignecommandepa.add(lc);
	//
	//
	// }

	public void AjouterProduit(Produit pr, int quantite) {

		if (listlc.get(pr.getId()) == null) {
			LigneCommande lignco = new LigneCommande();
			lignco.getProduit();
			lignco.setQuantite(quantite);
			lignco.setPrix(pr.getPrix());

		} else {
			LigneCommande lignco = listlc.get(pr.getId());
			lignco.setQuantite(lignco.getQuantite() + quantite);
		}

	}

	//Recup la liste
	public Collection<LigneCommande> recupListelc() {
		return listlc.values();

	}
	//

	// Consulter le total
	public double GetAllPanier() {
		double total = 0;
		for (LigneCommande lignco : listlc.values()) {
			total += lignco.getPrix() * lignco.getQuantite();

		}
		return total;
	}

	// Supprimer
	public void Supprimerpro(Produit pr) {
		listlc.remove(pr.getId());

	}
	
	//Recuperer le nompre de produit tu recupére la taille de 
	//Recuperer la taille de ta ligne de co
	public int recuplignco(){
		return listlc.size();
	}
	
}