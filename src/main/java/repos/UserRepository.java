package repos;

import org.springframework.stereotype.Repository;

import exceptions.UserNotFoundExcepion;
import jakarta.persistence.PersistenceContext;
import tables.User;
import jakarta.persistence.EntityManager;

@Repository
public interface UserRepository {

//	@PersistenceContext
//	EntityManager em;
	
	public static User getUserFromId(Long id) throws UserNotFoundExcepion {
		
		String query = "SELECT u.* FROM public.utente WHERE u.id_utente = :userId";
		
		// SERVE FUNZIONE GENERICA PER SOSTITUIRE I PARAMETRI (FARLA NELLE UTILS), DEVE RITORNARE OGGETTO DI CLASSE "QUERY"
		
		try  {
			// eseguire query per prendere utente ( copiare tutto da CDC8 )
			
			return null;
		} catch(Exception e) {
			throw new UserNotFoundExcepion("User not found");
		}
	}
	
}



// ESEMPIO REPOSITORY NATIVO
//
//@Repository
//public class PermessoNumeroCounterNativeRepository {
//	
//	@Autowired
//	SetQueryParametersUtil setQueryParamsUtil;
//	
//	@PersistenceContext
//	EntityManager em;
//	
//
//	@Transactional
//    public void updateNumeroByAnnoAndCategoriaPermesso(Integer year, String permitCategory, Long number) {
//    	String query = "UPDATE :mainSchema.permesso_numero_counter "
//    			+ "SET numero = :number"
//    			+ " WHERE anno = :year "
//    				+ " AND categoria_permesso = :permitCategory ";
//    	
//    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
//    	queryParams.put("number", number);
//		queryParams.put("year", year);
//		queryParams.put("permitCategory", permitCategory);
//		
//		/* assegnazione parametri */
//		Query finalQuery = setQueryParamsUtil.setQueryParameter(queryParams, query, null);
//		
//		try {
//			finalQuery.executeUpdate();
//		} catch(Exception e) {
//			throw e;
//		}
//    	
//    }
//    
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Transactional
//    public PermessoNumeroCounter findByAnnoAndCategoriaPermessoAndLock(Integer year, String permitCategory) {
//    	
//		String query = "SELECT pnc.* FROM :mainSchema.permesso_numero_counter pnc "
//				+ " WHERE pnc.anno = :year"
//					+ " AND pnc.categoria_permesso = :permitCategory";
//		
//		HashMap<String, Object> queryParams = new HashMap<String, Object>();
//		queryParams.put("year", year);
//		queryParams.put("permitCategory", permitCategory);
//		
//		/* assegnazione parametri */
//		Query finalQuery = setQueryParamsUtil.setQueryParameter(queryParams, query, PermessoNumeroCounter.class);
//		
//		PermessoNumeroCounter permNumCounter = (PermessoNumeroCounter) finalQuery.getSingleResult();
//		return permNumCounter;
//		
//    }
//}
//
