package io.elementor.infra.config;

public enum SLA {
    //** DEFAULT **//
    DEFAULT("default"),
    HTTP_CUSTOMER_EXPERIENCE_API("Http_Customer_Experience_Api"),
    HTTP_INTERNAL_USER_EXPERIENCE_API("Http_Internal_User_Experience_Api"),
    HTTP_CUSTOMER_EXPERIENCE_UI("Http_Customer_Experience_Ui"),
    HTTP_INTERNAL_USER_EXPERIENCE_UI("Http_Internal_User_Experience_Ui"),
    DB_MYSQL("DB_Mysql"),
    DB_POSTGRES("DB_Postgres"),
    DD_ELASTIC_SEARCH("DB_Elastic_Search"),
    GET_DOTA_2_HEROES("GET_DOTA_2_HEROES");

    //** SPECICIFIC SLA DEFINITIONS **//


    private String name;

    SLA(String name) {
        this.name = name;
    }

    public static SLA fromString(String name) {
        for (SLA e : SLA.values()) {
            if (name.equals(e.name)) return e;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}
