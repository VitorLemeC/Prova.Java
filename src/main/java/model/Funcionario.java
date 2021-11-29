package model;

public class Funcionario {
	
	private int id;
	private int matricula;
	private String nome_completo;
	private int data_desligamento;
	private float ultimo_salario;
	
	
	
	public Funcionario() {
		
	}

	public Funcionario(int matricula, String nome_completo, int data_desligamento, float ultimo_salario) {
		this.matricula = matricula;
		this.nome_completo = nome_completo;
		this.data_desligamento = data_desligamento;
		this.ultimo_salario = ultimo_salario;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public int getData_desligamento() {
		return data_desligamento;
	}
	public void setData_desligamento(int data_desligamento) {
		this.data_desligamento = data_desligamento;
	}
	public float getUltimo_salario() {
		return ultimo_salario;
	}
	public void setUltimo_salario(float ultimo_salario) {
		this.ultimo_salario = ultimo_salario;
	}
	
	
}
