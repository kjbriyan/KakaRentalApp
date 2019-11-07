package id.kakarental.store.Model.User;


import com.google.gson.annotations.SerializedName;


public class DataItem {

	@SerializedName("password")
	private String password;

	@SerializedName("foto")
	private String foto;

	@SerializedName("level")
	private String level;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("id_session")
	private String idSession;

	@SerializedName("id_users")
	private String idUsers;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("blokir")
	private String blokir;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setIdSession(String idSession){
		this.idSession = idSession;
	}

	public String getIdSession(){
		return idSession;
	}

	public void setIdUsers(String idUsers){
		this.idUsers = idUsers;
	}

	public String getIdUsers(){
		return idUsers;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setBlokir(String blokir){
		this.blokir = blokir;
	}

	public String getBlokir(){
		return blokir;
	}
}