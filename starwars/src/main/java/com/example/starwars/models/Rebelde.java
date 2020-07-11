package com.example.starwars.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="rebelde")
public class Rebelde implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "idade")
	private int idade;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "traidor")
	private boolean traidor;
	
	@OneToOne
	private Localizacao localizacao;
	
	@OneToOne
	private Inventario inventario;
	
	public Rebelde() {
		super();
	}

	public Rebelde(Long id, String name, int idade, String genero, boolean traidor, Localizacao localizacao,
			Inventario inventario) {
		super();
		this.id = id;
		this.name = name;
		this.idade = idade;
		this.genero = genero;
		this.traidor = traidor;
		this.localizacao = localizacao;
		this.inventario = inventario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isTraidor() {
		return traidor;
	}

	public void setTraidor(boolean traidor) {
		this.traidor = traidor;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rebelde other = (Rebelde) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
