/*CREATE TABLE recipient (
recipient_id         VARCHAR2(20) PRIMARY KEY,
name                 VARCHAR2(20) NULL,
password             VARCHAR2(20) NULL,
receiveHopeContent   VARCHAR2(50) NULL
); */

package probono.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;  

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
public class Recipient {

@Id
@Column(name="recipient_id", length=20)
private String recipientId;

@Column(name="name", nullable=false, length=20)
private String name;

@Column(name="password", nullable=false, length=20)
private String password;

@Column(name="receivehopecontent", nullable=false, length=20)
private String receiveContent;

}
