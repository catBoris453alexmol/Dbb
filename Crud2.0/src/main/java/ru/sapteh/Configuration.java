package ru.sapteh;

import java.util.Objects;

public class Configuration {
    public static final String TABLE_NAME = "configuration";
    public static final String ID_COLUMN = "id";
    public static final String MODELCPU_COLUMN = "modelCPU";
    public static final String RAM_COLUMN = "ram";
    public static final String HDD_COLUMN = "hdd";
    public static final String POWERSUPPLY_COLUMN = "powerSupply";


    private Long id;
    private  String modelCPU;
    private  String ram;
    private  String hdd;
    private String  powerSupply;





    public Configuration(Long id, String modelCPU, String ram, String hdd, String powerSupply){
        this.id = id;
        this.modelCPU = modelCPU;
        this.ram = ram;
        this.hdd = hdd;
        this.powerSupply = powerSupply;
    }

    public Long getId() {
        return id;
    }

    public String getHdd() {
        return hdd;
    }

    public String getModelCPU() {
        return modelCPU;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public String getRam() {
        return ram;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public void setModelCPU(String modelCPU) {
        this.modelCPU = modelCPU;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }


    @Override
    public String toString(){
        return String.format("%d %s %s %s %s  ",getId(),getModelCPU(),getRam(),getHdd(), getPowerSupply());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return id == that.id && Objects.equals(modelCPU, that.modelCPU) && Objects.equals(ram, that.ram) && Objects.equals(hdd, that.hdd) && Objects.equals(powerSupply, that.powerSupply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelCPU, ram, hdd, powerSupply);
    }
}
