package santo.com.crm.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import org.hibernate.annotations.Comment;

/**
 *
 * @author santo
 */
@Entity
@Table(name = "cliente", uniqueConstraints = {
    @UniqueConstraint(
            name = "UniqueClienteId",
            columnNames = {"identificacion"})})
@Schema(description = "Tabla cliente")
public class Cliente {

    @Schema(description = "Primary key", example = "1")
    @Comment("Primary key")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @Comment("Contrasena del cliente.")
    @Schema(description = "Contrasena del cliente.", nullable = false, example = "Clave2023")
    @Column(name = "contrasena", length = 150, nullable = false)
    private String contrasena;

    @Schema(description = "Estado del cliente, TRUE= activo, FALSE= inactivo", nullable = false, example = "true")
    @Comment("Estado del cliente, TRUE= activo, FALSE= inactivo")
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Schema(description = "Persona id", example = "1")
    @Comment("Persona id")
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne(optional = true)
    private Persona identificacion;

    public Cliente() {
    }

    public Cliente(Long clienteId, String contrasena, Boolean estado, Persona identificacion) {
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
        this.identificacion = identificacion;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Persona getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Persona identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.clienteId);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.contrasena, other.contrasena)) {
            return false;
        }
        if (!Objects.equals(this.clienteId, other.clienteId)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return Objects.equals(this.identificacion, other.identificacion);
    }

}
