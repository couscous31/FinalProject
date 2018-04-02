package fr.adaming.managebean;

import java.io.Serializable;
import java.util.ArrayList;
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
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManageBean implements Serializable {

	// Transfo association UML en Java
	@EJB
	ICategorieService categorieService;

	// Déclaration des attributs envoyées à la page
	private Categorie categorie;
	private Agent agent;

	private boolean indice;

	HttpSession catSession;
	
	private UploadedFile uf;

	// Constructeur vide
	public CategorieManageBean() {
		this.categorie = new Categorie();
		this.indice = false;
		this.uf=new UploadedFileWrapper();
	}

	@PostConstruct
	public void init() {
		// récupérer la session ouverte
		catSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		// récupérer l'agent stocké dans la session :
		this.agent = (Agent) catSession.getAttribute("agentListe");

	}

	// Getter et setter
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
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

	// Méthodes métier


	// ajouter une categorie :
		public String ajouterCategorie() {
			this.categorie.setPhotoCat(this.uf.getContents());
			
			// appel de la methode
			Categorie catOut = categorieService.ajouterCategorie(categorie);
			
			if (catOut.getId() != 0) {
				// récup et mettre à jour la liste
				List<Categorie> liste = categorieService.consultationCategorie();
				catSession.setAttribute("categorieListe", liste);

				return "accueilAgent";

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout categorie : fail !!!"));
				return "ajouterCategorie";
			}

		}
		
		
	// modifier categorie :
	public void edittable(RowEditEvent event) {
		// appel de la méthode
		categorieService.modifierCategorie((Categorie) event.getObject());

		// récup et mettre à jour la liste
		List<Categorie> liste1 = categorieService.consultationCategorie();
		catSession.setAttribute("categorieListe", liste1);

	}

	// supprimer categories :
	public void supprimerCategorie() {
		// appel de la méthode
		int verif = categorieService.supprimerCategorie(categorie);

		if (verif != 0) {
			// récup et mettre à jour la liste :
			List<Categorie> liste = categorieService.consultationCategorie();
			catSession.setAttribute("categorieListe", liste);

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression categorie : fail !!!"));
		}
	}

	// rechercher une categorie par son id :
	public void rechercherCategorieById() {
		try {

			this.categorie = categorieService.getCategorieById(categorie);
			this.indice = true;

		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la categorie n'existe pas"));
			this.indice = false;
		}
	}

}