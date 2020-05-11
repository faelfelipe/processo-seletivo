package com.finch.App.processo.seletivo.Finch.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="lanche")
public class Lanche implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isLight(List<Ingrediente> ingredientes) {
		//código bacon 2
		//código alface 1
		boolean temAlface = false;
		boolean temBacon = false;
		for(int i = 0; i < ingredientes.size(); i++) {
			if(ingredientes.get(i).getId() == 1)
				temAlface = true;
			if(ingredientes.get(i).getId() == 2)
				temBacon = true;
		}
		return (temAlface && !temBacon); 
	}
}	
