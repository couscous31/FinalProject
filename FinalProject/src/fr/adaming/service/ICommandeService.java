package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

@Local
public interface ICommandeService {
	
	public Commande recordCommande(Panier pa, Client cl);

	//Test
}
