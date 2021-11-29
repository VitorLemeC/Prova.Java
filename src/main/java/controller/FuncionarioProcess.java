package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Funcionario;

public class FuncionarioProcess {
	
	private Connection con;
	private ResultSet rs;

	public FuncionarioProcess() {
		this.con = new ConnectionDB().getConnection();
	}

	public JSONArray readAll() {
		JSONArray arr = new JSONArray();
		
		String query = "SELECT * FROM funcionario";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id" ,rs.getInt(1));
				obj.put("matricula" ,rs.getInt(2));
				obj.put("nome_completo" ,rs.getString(3));
				obj.put("data_desligamento" ,rs.getInt(4));
				obj.put("ultimo_salario" ,rs.getFloat(5));
				
				arr.put(obj);
				
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public boolean create(Funcionario funcionario) {

		String query = "INSERT aluno (nome, peso, altura, nascimento) VALUES (?,?,?,?)";

		try {

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, funcionario.getMatricula());
			ps.setString(2, funcionario.getNome_completo());
			ps.setInt(3, funcionario.getData_desligamento());
			ps.setFloat(4, funcionario.getUltimo_salario());
			

			if (ps.executeUpdate() > 0) {
				rs = ps.getGeneratedKeys();
				rs.next();
				con.close();
				funcionario.setId(rs.getInt(1));
				return true;
			} else {
				con.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
		return false;
	}

	public String update(Funcionario funcionario) {
		
		String query = "UPDATE aluno SET nome = ?, altura = ?, peso = ?, nascimento = ? WHERE id = ?";
		
	try {
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, funcionario.getMatricula());
		ps.setString(2, funcionario.getNome_completo());
		ps.setInt(3, funcionario.getData_desligamento());
		ps.setFloat(4, funcionario.getUltimo_salario());
		ps.setInt(5, funcionario.getId());
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return "sucesso";
		}
		
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
		return e.toString();
		
	}
		
		return "falha ao atualizar cadastro";
	}

		public boolean delete (int id) {
			
			String query = "DELETE FROM aluno WHERE id = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setInt(1, id);
				
				if(ps.executeUpdate() > 0) {
					con.close();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					return false;
		}


}
