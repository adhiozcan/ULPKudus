package gamatechno.gov.ulpkudus.model;

public class NavDrawer_M {
	private String title;
	private String subtitle;
	private int icon;

	public NavDrawer_M(String title, String subtitle, int icon) {
		this.title = title;
		this.subtitle = subtitle;
		this.icon = icon;
	}

	public String getTitle() {
		return this.title;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public int getIcon() {
		return this.icon;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

}
