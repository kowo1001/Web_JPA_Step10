/*
CREATE TABLE probono (
       probono_id          	VARCHAR2(50) PRIMARY KEY,
       probono_name      VARCHAR2(50) NOT NULL,
       probono_purpose  	VARCHAR2(200) NOT NULL
);  */

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
public class Probono {
	@Id
	@Column(name="probono_id", length=50)
	private String probonoId;
	
	@Column(name="probono_name", nullable=false, length=50)
	private String probonoName;
	
	@Column(name="probono_purpose", nullable=false, length=200)
	private String probonoPurpose;
}
