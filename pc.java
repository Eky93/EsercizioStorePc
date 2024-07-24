package app;

public class pc {
	private int id;
	private String nome;
	private String marca;
	private String tipologia;
	private double prezzo;
	private int quantità;
	
	public pc (int id, String nome, String marca, String tipologia, double prezzo, int quantità) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.tipologia = tipologia;
		this.prezzo = prezzo;
		this.quantità = quantità;
	}
	
	@Override
	public String toString() {
		return marca + " " + nome + ":" + "\ttipologia = " + tipologia + "\tprezzo = "
				+ prezzo + "€" + "\tquantità = " + quantità;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
}
