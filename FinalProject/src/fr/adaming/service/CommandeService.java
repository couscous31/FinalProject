package fr.adaming.service;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

@Stateless
public class CommandeService implements ICommandeService {

	@EJB
	ICommandeDao commandeService;
	
	@Override
	public Commande recordCommande(Panier pa, Client cl) {
		
		return commandeService.recordCommande(pa, cl);
	}

}
