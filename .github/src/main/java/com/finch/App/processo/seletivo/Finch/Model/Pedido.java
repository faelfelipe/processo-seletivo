package com.finch.App.processo.seletivo.Finch.Model;

public class Pedido {
	private long id_lanche;
	private long id_ingrediente;
	private int qtde;
	
	public long getId_lanche() {
		return id_lanche;
	}
	
	public void setId_lanche(long id_lanche) {
		this.id_lanche = id_lanche;
	}
	
	public long getId_ingrediente() {
		return id_ingrediente;
	}
	
	public void setId_ingrediente(long id_ingrediente) {
		this.id_ingrediente = id_ingrediente;
	}
	
	public int getQtde() {
		return qtde;
	}
	
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}	
}
