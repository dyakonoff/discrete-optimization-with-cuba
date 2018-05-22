package io.haulmont.dyakonoff.cargoship.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s - col %s : row %s|hold,column,row")
@Table(name = "CARGOSHIP_CONTAINER_POSITION")
@Entity(name = "cargoship$ContainerPosition")
public class ContainerPosition extends StandardEntity {
    private static final long serialVersionUID = -6000950638809295396L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HOLD_ID")
    protected CargoHold hold;

    @NotNull
    @Column(name = "ROW_", nullable = false)
    protected Integer row;

    @NotNull
    @Column(name = "COLUMN_", nullable = false)
    protected Integer column;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LOADING_SCHEME_ID")
    protected LoadingScheme loadingScheme;

    public void setLoadingScheme(LoadingScheme loadingScheme) {
        this.loadingScheme = loadingScheme;
    }

    public LoadingScheme getLoadingScheme() {
        return loadingScheme;
    }


    public void setHold(CargoHold hold) {
        this.hold = hold;
    }

    public CargoHold getHold() {
        return hold;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getRow() {
        return row;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getColumn() {
        return column;
    }


}