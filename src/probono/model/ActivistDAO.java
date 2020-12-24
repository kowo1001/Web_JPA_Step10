/* CREATE TABLE activist (
       activist_id          VARCHAR2(20)  PRIMARY KEY,
       name                 VARCHAR2(20) NULL,
       password             VARCHAR2(20) NULL,
       major                VARCHAR2(50) NULL
);  */
package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import probono.model.domain.Activist;
import probono.model.dto.ActivistDTO;
import probono.model.util.PublicCommon;


public class ActivistDAO {
	
		public static boolean addActivist(String id, String name, String pw, String major) throws SQLException{
			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Activist adto = Activist.builder().activistid(id).name(name).password(pw).major(major).build();
			em.persist(adto);
			tx.commit();
			
			return true;
		}
	
		//수정  
		//기부자 id로 주요 기부 내용 수정하기
		public static boolean updateActivist(String activistId, String major) throws SQLException, NoResultException{
			boolean result = false;
			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			int updatedCount = em.createNativeQuery("update activist set major = ? where activist_id = ?", Activist.class).setParameter(1, major).setParameter(2, activistId).executeUpdate();
			tx.commit();
			if(updatedCount!=0) {
				result = true;
				return result;
			}
			throw new NoResultException();
		}
		
		//id로 해당 기부자의 모든 정보 반환
		public static Activist getActivist(String activistId) throws SQLException, NoResultException {
			EntityManager em = PublicCommon.getEntityManager();
			Activist adto = (Activist) em.createNativeQuery("select * from activist where activist_id=?", Activist.class).setParameter(1, activistId).getSingleResult();
			if( adto != null) {
				return adto;
			}
			throw new NoResultException();
		}
	
		//삭제
		//sql - delete from activist where activist_id=?
		public static boolean deleteActivist(String activistId) throws SQLException, NoResultException{
			boolean result = false;
			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			int deletedCount = em.createNativeQuery("delete from activist where activist_id=?", Activist.class).setParameter(1, activistId).executeUpdate();
			tx.commit();
			if (deletedCount != 0) {
				result = true;
				return result;
			}
			throw new NoResultException();
		}
	
		//모든 기부자 검색해서 반환
		public static ArrayList<Activist> getAllActivists() throws SQLException, NoResultException {
			EntityManager em = PublicCommon.getEntityManager();
			ArrayList<Activist> adto = (ArrayList<Activist>) em.createNativeQuery("select * from activist", Activist.class).getResultList();
			if(adto.size() != 0) {
				return adto;
			}
			throw new NoResultException();
		}
}
