package week5.day1;

public class Result {

	private String parent;
	private String made_sla;
	private String sys_id;
	private String short_description;
	private String description;
	private String category;
	private OpenedBy opened_by;
	private SysDomain sys_domain;

	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public OpenedBy getOpened_by() {
		return opened_by;
	}

	public void setOpened_by(OpenedBy opened_by) {
		this.opened_by = opened_by;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public SysDomain getSys_domain() {
		return sys_domain;
	}

	public void setSys_domain(SysDomain sys_domain) {
		this.sys_domain = sys_domain;
	}

	public String getMade_sla() {
		return made_sla;
	}

	public void setMade_sla(String made_sla) {
		this.made_sla = made_sla;
	}

}