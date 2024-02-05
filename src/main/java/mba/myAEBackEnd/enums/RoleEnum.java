package mba.myAEBackEnd.enums;

public enum RoleEnum {

    USER("ROLE_USER"),ADMIN("ROLE_ADMIN");

    private final String label;
    RoleEnum(String roleAdmin) {
        this.label = roleAdmin;
    }

    public String getLabel(){
        return  label;
    }
}
