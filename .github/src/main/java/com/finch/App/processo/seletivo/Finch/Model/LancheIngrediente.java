package com.finch.App.processo.seletivo.Finch.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lanche_ingrediente")
public class LancheIngrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	//private long lanche_id;
	
	@ManyToOne
	@JoinColumn(name="lanche_id")
	private Lanche lanche;
	
	@ManyToOne
	@JoinColumn(name="ingrediente_id")
	private Ingrediente ingrediente;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Lanche getLanche() {
		return lanche;
	}
	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}	
}
