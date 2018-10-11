package be.vdab.retrovideo.entities;

public class Genre extends RetroVideoEntity {

	private String name;
	
	public Genre(final long id, final String name) {
		setId(id);
		setName(name);
	}
	
	public final void setName(final String name) {
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}
}
