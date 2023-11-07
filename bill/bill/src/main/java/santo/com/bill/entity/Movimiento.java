package santo.com.bill.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.hibernate.annotations.Comment;

/**
 *
 * @author santo
 */
@Entity
@Table(name = "movimiento")
@Schema(description = "Tabla de movimientos de cuenta")
public class Movimiento {

    @Schema(description = "Primary key", example = "1")
    @Comment("Primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMovimiento;

    @Schema(description = "Fecha de movimiento", nullable = false, example = "2023-11-04 00:00:00")
    @Comment("Fecha de movimiento")
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Schema(description = "Tipo de movimiento, CR= Crédito, DB= Débito", nullable = false, example = "CR")
    @Comment("Tipo de movimiento, CR= Crédito, DB= Débito")
    @Column(name = "tipo_movimiento", columnDefinition = "CHARACTER(2) CHECK (tipo_movimiento IN('CR','DB'))", length = 2, nullable = false)
    private String tipoMovimiento;

    @Schema(description = "Valor del movimiento", nullable = false, example = "0.00")
    @Comment("Valor del movimiento")
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Schema(description = "Saldo del movimiento", nullable = false, example = "0.00")
    @Comment("Saldo del movimiento")
    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Schema(description = "Cuenta id", example = "1")
    @Comment("Cuenta id")
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta")
    @ManyToOne(optional = false)
    private Cuenta numeroCuenta;

    public Movimiento() {
    }

    public Movimiento(Long idMovimiento, Date fecha, String tipoMovimiento,
            BigDecimal valor, BigDecimal saldo, Cuenta numeroCuenta) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cuenta getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Cuenta numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Movimiento other = (Movimiento) obj;
        if (!Objects.equals(this.tipoMovimiento, other.tipoMovimiento)) {
            return false;
        }
        if (!Objects.equals(this.numeroCuenta, other.numeroCuenta)) {
            return false;
        }
        if (!Objects.equals(this.idMovimiento, other.idMovimiento)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return Objects.equals(this.saldo, other.saldo);
    }

}
