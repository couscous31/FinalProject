package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Agent;

@Local
public interface IAgentService {
	
	//methode
	public Agent isExist(Agent a);

}