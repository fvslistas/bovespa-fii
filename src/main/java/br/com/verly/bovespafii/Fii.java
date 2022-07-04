package br.com.verly.bovespafii;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fii {

	private String razao;
	private String fundo;
	private String segmento;

	@Id
	private String codigo;

	public Fii() {
	}

	public Fii(String razao, String fundo, String segmento, String codigo) {
		this.razao = razao;
		this.fundo = fundo;
		this.segmento = segmento;
		this.codigo = codigo;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getFundo() {
		return fundo;
	}

	public void setFundo(String fundo) {
		this.fundo = fundo;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}