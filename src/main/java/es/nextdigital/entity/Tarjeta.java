package es.nextdigital.entity;


import es.nextdigital.enums.TipoTarjeta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "TARJETAS")
public class Tarjeta {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TARJETA")
    private Long idTarjeta;
	
	@Column(name = "NUM_TARJETA")
	private String numTarjeta;

	@Column(name = "TIPO_TARJETA")
	@Enumerated(EnumType.STRING)
	private TipoTarjeta tipoTarjeta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUENTA", nullable = false)
    private Cuenta cuenta;
    
    @Column(name = "PIN_HASH", length = 60, nullable = false)
	@Enumerated(EnumType.STRING)
	private String pin;

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
    
	
    
}
