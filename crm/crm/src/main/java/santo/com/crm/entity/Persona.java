package santo.com.crm.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import org.hibernate.annotations.Comment;

/**
 *
 * @author santo
 */
@Entity
@Table(name = "persona")
@Schema(description = "Tabla persona")
public class Persona {

    @Schema(description = "Primary key", example = "1")
    @Comment("Primary key")
    @Id
    private String identificacion;

    @Schema(description = "Nombre persona", maxLength = 60, nullable = false, example = "Santiago Luque")
    @Comment("Nombre persona")
    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Schema(description = "Genero persona, M= masculino, F= femenino", nullable = false, example = "M")
    @Comment("Genero persona, M= masculino, F= femenino")
    @Column(name = "genero", columnDefinition = "CHARACTER(1) CHECK(genero IN('M','F'))", length = 1, nullable = false)
    private Character genero;

    @Schema(description = "Edad de la persona", nullable = true, example = "25")
    @Comment("Edad de la persona")
    @Column(name = "edad", nullable = true)
    private Integer edad;

    @Schema(description = "Dirección de la persona", maxLength = 150, nullable = true, example = "Quito")
    @Comment("Dirección de la persona")
    @Column(name = "direccion", nullable = true, length = 150)
    private String direccion;

    @Schema(description = "Telefono de la persona", maxLength = 20, nullable = true, example = "25")
    @Comment("Telefono de la persona")
    @Column(name = "telefono", length = 20, nullable = true)
    private String telefono;

    public Persona() {
    }

    public Persona(String identificacion, String nombre, Character genero, Integer edad, String direccion, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.identificacion);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.identificacion, other.identificacion)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        return Objects.equals(this.edad, other.edad);
    }

}
