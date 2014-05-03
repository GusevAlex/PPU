package com.PPU.vo;

public class MenuItemBean {
	private String icon;
	private String title;
	private int	   count;
    private String adressPage;
	
	public MenuItemBean() {
		
	}
	
	public MenuItemBean(String icon, String title, int count, String adressPage) {
		this.icon = icon;
		this.title = title;
		this.count = count;
        this.adressPage = adressPage;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = (count < 0) ? 0 : count;
	}

    public String getAdressPage() {
        return adressPage;
    }

    public void setAdressPage(String adressPage) {
        this.adressPage = adressPage;
    }
}
