package entities;

public class Usuario {
	private int idUsuario;
	private String nomeUsuario;
	private String telefoneUsuario;
	
	public Usuario() {
	}
	
	public Usuario(String nomeUsuario, String telefoneUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.telefoneUsuario = telefoneUsuario;
	}
	
	public Usuario(int idUsuario, String nomeUsuario, String telefoneUsuario) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.telefoneUsuario = telefoneUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	@Override
	public String toString() {
		return "Usu�rio [id=" + idUsuario + ", nome=" + nomeUsuario + ", telefone=" + telefoneUsuario + "]";
	}

}
