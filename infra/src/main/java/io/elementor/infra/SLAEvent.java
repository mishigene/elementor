package io.elementor.infra;

import io.elementor.infra.config.SLA;

public class SLAEvent {
    private SLA sla;
    private Integer time;


    public SLAEvent(SLA sla, Integer time) {
        this.sla = sla;
        this.time = time;
    }

    //region Setters & Getters
    public SLA getSla() {
        return sla;
    }

    public void setSla(SLA sla) {
        this.sla = sla;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
    //endregion
}
