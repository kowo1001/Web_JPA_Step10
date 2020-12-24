/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */

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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class ProbonoProject {
	@Id
	@Column(name="probono_project_id", nullable=false, precision=5)
	private int probonoProjectId;
	
	@Column(name="probono_project_name", nullable=false, length=50)
	private String probonoProjectName;
	
	@Column(name="probono_id", nullable=false, length=50)
	private String probonoId;
	
	@Column(name="activist_id", nullable=false, length=20)
	private String activistId;
	
	@Column(name="receive_id", nullable=false, length=20)
	private String recipientId;
	
	@Column(name="project_content", nullable=false, length=100)
	private String projectContent;
	
}
