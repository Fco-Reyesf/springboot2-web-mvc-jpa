package com.proyecto.springboot.app.utils.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPagina;
	private int numeroElementosPorPagina;
	private int paginaActual;
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		this.numeroElementosPorPagina = page.getSize();
		this.totalPagina = page.getTotalPages();
		this.paginaActual = page.getNumber() + 1; 
		
		int desde, hasta;
		if (totalPagina <= numeroElementosPorPagina) {
			desde = 1;
			hasta = totalPagina;
		} else {
			if (paginaActual <= numeroElementosPorPagina / 2) {
				desde = 1;
				hasta = numeroElementosPorPagina;
			} else if (paginaActual >= totalPagina - numeroElementosPorPagina / 2) {
				desde = totalPagina - numeroElementosPorPagina + 1;
				hasta = numeroElementosPorPagina;
			} else {
				desde = paginaActual - numeroElementosPorPagina / 2;
				hasta = numeroElementosPorPagina;
			}
		}
		
		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}
		
		
		
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPagina() {
		return totalPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
}
