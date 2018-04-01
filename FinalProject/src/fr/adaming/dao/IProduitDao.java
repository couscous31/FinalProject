package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local

public interface IProduitDao {
	
	public List<Produit> getAllProduit(); 
	
	public Produit addProduit(Produit pr);
	
	public int deleteProduit(Produit pr);
	
	public int updateProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);


}
