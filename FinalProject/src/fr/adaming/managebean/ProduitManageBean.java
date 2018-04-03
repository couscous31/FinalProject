package fr.adaming.managebean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@RequestScoped

public class ProduitManageBean implements Serializable {

	// Transfo de l'assos Uml en Java
	@EJB
	private IProduitService produitService;

	// Attributs du MB
	private Produit produit;
	private Agent agent;
	private Client client;
	private boolean indice;
	private UploadedFile uf;
	HttpSession maSession;

	private List<Produit> produits;
	private List<Produit> filtreProduits;

	// Constructeur vide
	public ProduitManageBean() {
		this.produit = new Produit();
		this.indice = false;
		this.uf = new UploadedFileWrapper();
	}

	// Méthodes Session
	@PostConstruct
	public void init() {
		// récup de la session ouverte
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		// récup agent de session
		this.agent = (Agent) maSession.getAttribute("agentListe");

	}

	// Getter et setter
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Produit> getFiltreProduits() {
		return filtreProduits;
	}

	public void setFiltreProduits(List<Produit> filtreProduits) {
		this.filtreProduits = filtreProduits;
	}

	// Méthodes

	// ajouter un produit à la liste
	public String ajouterProduit() {
		this.produit.setPhotoProd(this.uf.getContents());

		// appel de la méthode
		Produit prAjout = produitService.addProduit(produit);

		if (prAjout.getId() != 0) {
			// récup et mettre à jour la liste
			List<Produit> liste = produitService.getAllProduit();
			maSession.setAttribute("produitListe", liste);

			return "accueilAgent";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout produit : fail !!!"));
			return "ajouterProduit";
		}
	}

	// modifier les attributs d'un produit
	public void editTable(RowEditEvent event) {
		// appel de la methode modifier d'un produit :
		produitService.updateProduit((Produit) event.getObject());

		// récup et mettre à jour la liste :
		List<Produit> liste = produitService.getAllProduit();
		maSession.setAttribute("produitListe", liste);

	}

	// supprimer un produit de la liste :
	public void supprimerProduit() {
		int prSuppr = produitService.deleteProduit(produit);

		if (prSuppr != 0) {
			// recuperation de la liste
			List<Produit> liste = produitService.getAllProduit();
			maSession.setAttribute("produitListe", liste);

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression produit : fail !!!"));

		}

	}

	// rechercher un produit par son id :
	public void rechercherProduitById() {

		try {
			Produit prSear = produitService.getProduitById(produit);

			this.produit = prSear;
			this.indice = true;

		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le produit n'existe pas"));
			this.indice = false;

		}

	}

}