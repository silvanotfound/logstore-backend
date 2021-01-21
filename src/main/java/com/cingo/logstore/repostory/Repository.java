package com.cingo.logstore.repostory;

import javax.persistence.EntityManager;

import com.cingo.logstore.entity.Log;
import com.cingo.logstore.jpa.LogStoreEntityManagerFactory;
import org.hibernate.service.Service;

public class Repository {

	private EntityManager manager;
	private Log log;
	
	public EntityManager getManager() {
		if (manager == null){
			manager = LogStoreEntityManagerFactory.getInstance().createEntityManager();
		}
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void add(Object o) {
		this.getManager().getTransaction().begin();
		this.getManager().persist(o);
		this.getManager().getTransaction().commit();
	}
	
	public void update(Object o) {
		this.getManager().getTransaction().begin();
		this.getManager().merge(o);
		this.getManager().getTransaction().commit();
	}

	public void delete(Object o) {
		this.getManager().getTransaction().begin();
		this.getManager().remove(this.getManager().merge(o));
		this.getManager().getTransaction().commit();
	}
}
