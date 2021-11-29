package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.FuncionarioProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Funcionario;


@WebServlet("/funcionario")
public class Funcionariohttp extends HttpServlet {
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		try {
			JSONObject obj = new JSONObject(body);
			
			int matricula =obj.getInt("peso");
			String nome_completo = obj.getString("nome_completo");
			int data_nascimento = obj.getInt("data_nascimento");
			float ultimo_salario = (float) obj.getDouble("ultimo_salario");
			
		} catch (JSONException e) {
			e.fillInStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		FuncionarioProcess ap = new FuncionarioProcess();
		
		JSONArray arr = ap.readAll();
		
		pw.write(arr.toString());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		JSONObject retJson = new JSONObject();
		
		try {
			JSONObject obj = new JSONObject(body);
			
			Funcionario a = new Funcionario();
			a.setId(obj.getInt("id"));
			a.setMatricula(obj.getInt("matricula"));
			a.setNome_completo(obj.getString("nome_completo"));
			a.setData_desligamento(obj.getInt("data_nascimento"));
			a.setUltimo_salario((float)obj.getDouble("ultimo_salario"));
			
			FuncionarioProcess ap = new FuncionarioProcess();
			
			String ret = ap.update(a);
			
			if(ret.equals("sucesso")) {
				pw.write(obj.toString());
			}else {
				resp.setStatus(401);
				retJson.put("err", ret);
				pw.write(retJson.toString());
			}
		} catch(JSONException e) {
			resp.setStatus(401);
			pw.write("{err:'" + e.toString() + "'}");
		}
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		String tempId = req.getParameter("id");
		
		int id = Integer.parseInt(tempId);
		
		FuncionarioProcess ap = new FuncionarioProcess();
		
		if(ap.delete(id) == true) {
			pw.write("{\"id\":" + id + "}");
		}else {
			resp.setStatus(401);
		}
	}
}
