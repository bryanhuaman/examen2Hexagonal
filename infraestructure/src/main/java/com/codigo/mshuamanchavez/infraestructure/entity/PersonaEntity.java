package com.codigo.mshuamanchavez.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "tipodocumento", nullable = false, length = 5)
    private String tipoDocumento;

    @Column(name = "numerodocumento", nullable = false, length = 20, unique = true)
    private String numeroDocumento;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado", nullable = false)
    private int estado;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

}
