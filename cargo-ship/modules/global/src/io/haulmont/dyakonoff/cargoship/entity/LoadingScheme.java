package io.haulmont.dyakonoff.cargoship.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s|name")
@Table(name = "CARGOSHIP_LOADING_SCHEME")
@Entity(name = "cargoship$LoadingScheme")
public class LoadingScheme extends StandardEntity {
    private static final long serialVersionUID = -6096891426478758471L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100, columnDefinition = "Loading scheme name")
    protected String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHIP_ID")
    protected Ship ship;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "loadingScheme")
    protected List<ContainerPosition> positions;

    public void setPositions(List<ContainerPosition> positions) {
        this.positions = positions;
    }

    public List<ContainerPosition> getPositions() {
        return positions;
    }


    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}