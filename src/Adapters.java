
public class Adapters {
	private int idAdapter, count;
	private String title, description;
	public Adapters(int idAdapter, String title, String description, int count) {
		super();
		this.idAdapter = idAdapter;	
		this.title = title;
		this.description = description;
		this.count = count;
	}
	public int getIdAdapter() {
		return idAdapter;
	}
	public void setIdAdapter(int idAdapter) {
		this.idAdapter = idAdapter;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
