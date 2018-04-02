package fr.adaming.managebean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import fr.adaming.service.EnvoyerMail;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "paMB")
@RequestScoped
public class PanierManageBean implements Serializable {

	@EJB
	ICommandeService commandeService;
	
	@EJB
	ILigneCommandeService lignecommandeService;

	Panier panier;

	// //Attribut du ManageBean
	private Client client;
	private Commande commande;
	private List<LigneCommande> listeco;
	private List<Produit> listepro;
	private Produit produit;
	private int quantite = 0;
	//
	HttpSession maSession;
	FacesContext context = FacesContext.getCurrentInstance();

	public PanierManageBean() {
		super();
		// Recuperer la session
		maSession = (HttpSession) context.getExternalContext().getSession(false);
		// Recuperer le panier dans la session
		this.panier = (Panier) maSession.getAttribute("PanierSession");
	}

	// G+S
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public List<Produit> getListepro() {
		return listepro;
	}

	public void setListepro(List<Produit> listepro) {
		this.listepro = listepro;
	}

	// Appel methode
	public void AddPanier() {
		if (quantite == 0) {
			String msg = "choisir un produit";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
		} else if (quantite > this.produit.getQuantite()) {
			String msg2 = "le produit est épuisé";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg2, null));
		} else {

			panier.AjouterProduit(this.produit, quantite);
			String msg3 = "Le produit a bien ete ajouté";
			this.listeco=lignecommandeService.getAllListLcService();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listelc", this.listeco);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg3, null));
			this.produit = null;
			this.quantite = 0;

		}

	}

	public void effacerpanier() {
		System.out.println();
		panier.Supprimerpro(this.produit);
		produit = null;
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "le produit a bien ete effacer", null));

	}

	public void validatecommande() {

		commandeService.recordCommande(this.panier, this.client);
		//Envoi d'un mail au client
		EnvoyerMail.envoyerMessageAjout(client.getEmail());
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Felicitation pour votre commande!", null));

	}

}
