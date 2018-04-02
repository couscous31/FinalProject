package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {

	// Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cat")
	private long id;
	private String nomCategorie;
	@Lob
	private byte[] photoCat;
	private String description;
	@Transient
	private String image;

	
	// Transformation UML en java avec ligne de commande
	@OneToMany(mappedBy="categorie")
	private List<Produit> listeProduit;
	
	//Transfo assos Uml en Java avec agent
	@ManyToOne
	@JoinColumn(name="a_id",referencedColumnName="id_a")
	private Agent agent;

	

	// Constructeurs
	public Categorie() {
		super();
	}

	public Categorie(String nomCategorie, byte[] photoCat, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photoCat = photoCat;
		this.description = description;
	}

	public Categorie(long id, String nomCategorie, byte[] photoCat, String description) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
		this.photoCat = photoCat;
		this.description = description;
	}
	// Declaration des guetter et setter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public byte[] getPhotoCat() {
		return photoCat;
	}

	public void setPhotoCat(byte[] photoCat) {
		this.photoCat = photoCat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	// Methode ToString


	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nomCategorie=" + nomCategorie + ", photoCat=" + photoCat
				+ ", description=" + description + "]";
	}

}