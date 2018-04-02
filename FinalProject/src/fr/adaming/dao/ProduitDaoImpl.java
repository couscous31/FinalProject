package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Produit;

@Stateless

public class ProduitDaoImpl implements IProduitDao {
	
	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	// Get All Produit
		@Override
		public List<Produit> getAllProduit() {
			// creation de la requete SQl
			String req = "SELECT pr FROM Produit AS pr ";

			// création d'un query
			Query query = em.createQuery(req);
			List<Produit> listePr= query.getResultList();
			
			for(Produit pr : listePr){
				pr.setImage("data:image/png;base64,"+Base64.encodeBase64String(pr.getPhotoProd()));
			}
			return listePr;
		}

	// Add Produit
	@Override
	public Produit addProduit(Produit pr) {
		em.persist(pr);
		return pr;
	}

	// Delete Produit
	@Override
	public int deleteProduit(Produit pr) {

		// req jpql
		String req1 = "DELETE FROM Produit AS pr WHERE pr.id=:pId";

		// création de query
		Query query1 = em.createQuery(req1);

		// passage des params
		query1.setParameter("pId", pr.getId());

		// envoyer la req et récup résultat
		return (int) query1.executeUpdate();
	}

	// Update Produit
	@Override
	public int updateProduit(Produit pr) {

		// req jpql
		String req2 = "UPDATE Produit SET pr.designation=:pDesi," 
										+ "pr.description=:pDesc," 
										+ "pr.prix=:pPrix,"
										+ "pr.quantite=:pQt," 
										+ "pr.photoProd=:pPhoto" 
										+ " WHERE pr.id=:pIdPr";

		// création du query
		Query query2 = em.createQuery(req2);

		// passages des params
		query2.setParameter("pDesi", pr.getDesignation());
		query2.setParameter("pDesc", pr.getDescription());
		query2.setParameter("pPrix", pr.getPrix());
		query2.setParameter("pQt", pr.getQuantite());
		query2.setParameter("pPhoto", pr.getPhotoProd());
		query2.setParameter("pIdPr", pr.getId());

		// envoyer req et récup résultat
		return (int) query2.executeUpdate();
	}

	// Get Produit By Id
	@Override
	public Produit getProduitById(Produit pr) {

		// req jpql
		String req3 = "SELECT pr FROM Produit AS pr WHERE pr.id=:pIdPr";

		// création de query
		Query query3 = em.createQuery(req3);

		// passage des params
		query3.setParameter("pIdPr", pr.getId());

		// envoyer req et récup résultat
		return (Produit) query3.getSingleResult();
	}

}