package fr.adaming.managebean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ProduitServiceImpl;

@ManagedBean(name="paMB")
@RequestScoped
public class PanierManageBean implements Serializable {
	
	
	Panier panier;
	
//	//Attribut du ManageBean
	private Client client;
	private LigneCommande ligneco;
	private List<Produit> listepro;
	private Produit produit;
	private int quantite=0;
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


	//G+S
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


	//Appel methode
	public void AddPanier(){
		if(quantite==0){
			String msg="choisir un produit";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
		}else if (quantite > this.produit.getQuantite()){
			String msg2="le produit est épuisé";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg2, null));
		}else{
			
		 panier.AjouterProduit(this.produit, quantite);
		 String msg3="Le produit a bien ete ajouté";
		 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg3, null));
		this.produit = null;
		this.quantite=0;
	
	}
		
	}
//		// Recuperer la liste des lignes de commande
//		List<LigneCommande> listcl=(List<LigneCommande>) panier.recupListelc();
//		// Mettre a jour la liste dans la session
//		maSession.setAttribute("listeco",listcl );
//		
//		return "panier";
	

}
