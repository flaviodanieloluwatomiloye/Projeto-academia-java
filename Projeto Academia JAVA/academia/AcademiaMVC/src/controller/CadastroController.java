package controller;

import java.util.List;

import model.AlunoCadastrado;

public class CadastroController {
	private final CadstroDAO CadastroDAO = new CadastroDAO();
	
	public List<AlunoCadastrado> listarAlunos() { return alunoDAO.listar(); }
}
