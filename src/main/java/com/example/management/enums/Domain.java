package com.example.management.enums;

public enum Domain {
    TATA_MOTORS("Tata Motors"),
    TATA_AIRWAYS("Tata Airways"),
    TATA_CONSULTANCY("Tata Consultancy"),
    TATA_CHEMICALS("Tata Chemicals"),
    TATA_STEEl("Tata Steel");

    public String value;

    private Domain(String value){
        this.value = value;
    }
}
