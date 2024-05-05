package com.codigo.mshuamanchavez.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "empresa_info")
@Getter
@Setter
public class EmpresaEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "razonsocial", nullable = false)
    private String razonSocial;

    @Column(name = "tipodocumento", nullable = false, length = 5)
    private String tipoDocumento;

    @Column(name = "numerodocumento", nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "condicion", nullable = false, length = 50)
    private String condicion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "distrito", length = 100)
    private String distrito;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "departamento", length = 100)
    private String departamento;

    @Column(name = "esagenteretencion", nullable = false, columnDefinition = "false")
    private boolean esAgenteRetencion;

    @Column(name = "usuacrea", nullable = false)
    private String usuaCrea;

    @Column(name = "datecreate", nullable = false)
    private Timestamp dateCreate;

    @Column(name = "usuamodif")
    private String usuaModif;

    @Column(name = "datemodif")
    private Timestamp dateModif;

    @Column(name = "usuadelet")
    private String usuaDelet;

    @Column(name = "datedelet")
    private Timestamp dateDelet;

}
