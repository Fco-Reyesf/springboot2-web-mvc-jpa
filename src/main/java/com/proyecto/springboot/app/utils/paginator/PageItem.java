package com.proyecto.springboot.app.utils.paginator;

public class PageItem {

	private int numero;
	private boolean paginaActual;
	
	public PageItem(int numero, boolean paginaActual) {
		this.numero = numero;
		this.paginaActual = paginaActual;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(boolean paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	
	
}
