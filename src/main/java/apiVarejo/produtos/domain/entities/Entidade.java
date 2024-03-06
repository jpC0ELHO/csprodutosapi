package apiVarejo.produtos.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class Entidade implements Serializable {

    @Serial
    private static final long SerialVersion=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = JsonSerializer.class)
    @JsonDeserialize(using = JsonDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "update_at",nullable = false)
    private LocalDateTime updateAt;
}
