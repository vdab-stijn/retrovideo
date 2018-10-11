package be.vdab.retrovideo.entities;

public abstract class RetroVideoEntity {

	private long id;
	
	public final void setId(final long id) {
		this.id = id;
	}
	
	public final long getId() {
		return id;
	}
}
