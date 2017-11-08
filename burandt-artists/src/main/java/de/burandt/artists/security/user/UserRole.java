package de.burandt.artists.security.user;

public enum UserRole {

	ADMIN("Admin");
	
	private UserRole(String roleName) {
		this.roleName = roleName;
	}
	
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}
}
