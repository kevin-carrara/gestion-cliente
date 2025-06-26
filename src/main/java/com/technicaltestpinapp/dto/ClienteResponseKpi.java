package com.technicaltestpinapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@RequiredArgsConstructor
public class ClienteResponseKpi {

    private double promedioEdades;

    private double desviacionEstandar;

    public ClienteResponseKpi(double promedioEdades, double desviacionEstandar) {
        this.promedioEdades = redondear(promedioEdades,0);
        this.desviacionEstandar = redondear(desviacionEstandar,1);
    }

    private double redondear(double valor, int numeroDecimal) {
        return BigDecimal.valueOf(valor)
                .setScale(numeroDecimal, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
