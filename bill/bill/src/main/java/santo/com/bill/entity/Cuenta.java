package santo.com.bill.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.annotations.Comment;

/**
 *
 * @author santo
 */
@Entity
@Table(name = "cuenta")
@Schema(description = "Tabla de cuentas")
public class Cuenta {

    @Schema(description = "Primary key", example = "1002030001")
    @Comment("Primary key")
    @Column(name = "numero_cuenta")
    @Id
    private String numeroCuenta;

    @Schema(description = "Tipo de cuenta, A= Ahorros, C= Corriente", nullable = false, example = "A")
    @Comment("Tipo de cuenta, A= Ahorros, C= Corriente")
    @Column(name = "tipo_cuenta", columnDefinition = "CHARACTER(1) CHECK(tipo_cuenta IN('A','C'))", length = 1, nullable = false)
    private Character tipoCuenta;

    @Schema(description = "Saldo inicial de la cuenta", nullable = false, example = "0.00")
    @Comment("Saldo inicial de la cuenta")
    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @Schema(description = "Estado de la cuenta, TRUE= activa, FALSE= Inactiva", nullable = false, example = "true")
    @Comment("Estado de la cuenta, TRUE= activa, FALSE= Inactiva")
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Schema(description = "Cliente id", example = "1")
    @Comment("Cliente id")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    public Cuenta() {
    }

    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cuenta(String numeroCuenta, Character tipoCuenta, BigDecimal saldoInicial, Boolean estado, Long clienteId) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Character getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(Character tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.numeroCuenta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.numeroCuenta, other.numeroCuenta)) {
            return false;
        }
        if (!Objects.equals(this.tipoCuenta, other.tipoCuenta)) {
            return false;
        }
        if (!Objects.equals(this.saldoInicial, other.saldoInicial)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return Objects.equals(this.clienteId, other.clienteId);
    }

}
