package br.com.erudio.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="breakfast")
public class Breakfast implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 180)
	private String nome;
	
	@Column(name = "cpf", nullable = false)
	private Double cpf;
	
	@Column(name = "bebida", nullable = false, length = 180)
	private String bebida;
	
	@Column(name = "comida", nullable = false, length = 180)
	private String comida;
	
	@Column(name = "dataatul", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataatual;
	
	public Breakfast() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Double getCpf() {
		return cpf;
	}

	public void setCpf(Double cpf) {
		this.cpf = cpf;
	}

	public Date getDataatual() {
		return dataatual;
	}

	public void setDataatual(Date dataatual) {
		this.dataatual = dataatual;
	}
	public String getComida() {
		return comida;
	}

	public void setComida(String comida) {
		this.comida = comida;
	}
	public String getBebida() {
		return nome;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataatual == null) ? 0 : dataatual.hashCode());
		result = prime * result + ((comida == null) ? 0 : comida.hashCode());
		result = prime * result + ((bebida == null) ? 0 : bebida.hashCode());

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
		Breakfast other = (Breakfast) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (cpf == null)
		if (other.cpf != null)
			return false;
		if (!cpf.equals(other.cpf))
			return false;
		if (dataatual == null) {
			if (other.dataatual != null)
				return false;
		} else if (!dataatual.equals(other.dataatual))
			return false;
		if (comida == null) {
			if (other.comida != null)
				return false;
		} else if (!comida.equals(other.comida))
			return false;
		if (bebida == null) {
			if (other.bebida != null)
				return false;
		} else if (!bebida.equals(other.bebida))
			if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
 {
		return true;
	}
}
}
