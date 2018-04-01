package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientService {

	//methode
	public List<Client> getAllListClientService();

	public Client addClientService(Client cl);

	public int updateService(Client cl);

	public int deleteService(Client cl);
	
	public Client isExist(Client cl);

}