package it.mmdev.enums;

public enum UserPermission {
	
	/* ACCOUNT */
	INSERT_ACCOUNT("insert-account", "Insert account"),
	EDIT_ACCOUNT("edit-account", "Edit account"),
	DELETE_ACCOUNT("delete-account", "Delete account"),
	
	/* TRANSACTION */
	INSERT_TRANSACTION("insert-transaction", "Insert transaction"),
	EDIT_TRANSACTION("edit-transaction", "Edit transaction"),
	DELETE_TRANSACTION("delete-transaction", "Delete transaction")
	;
	
	private String code;
	private String description;
	
	private UserPermission(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public UserPermission resolve(String code) {
		for (UserPermission userPerm : values()) {
			if (userPerm.getCode().equals(code))
				return userPerm;
		}
		
		return null;
	}

}
