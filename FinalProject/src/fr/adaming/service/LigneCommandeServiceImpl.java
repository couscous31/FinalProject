package fr.adaming.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

import fr.adaming.dao.ILigneCommandeDao;

import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	private ILigneCommandeDao lignecommandedao;

	@Override
	public List<LigneCommande> getAllListLcService() {

		return lignecommandedao.getAllListLc();
	}

}