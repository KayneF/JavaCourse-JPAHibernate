package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Persona;

public class Program {

	public static void main(String[] args) {
		
		/**
		 * No Hibernate, as entidades precisam estar sendo monitoradas para que
		 * qualquer tipo de ação seja realizada. Isso quer dizer que, não basta
		 * instanciar uma entidade. Para que essa entidade seja monitorada, ela
		 * precisa ter sido inserida, atualizada ou encontrada préviamente no 
		 * banco de dados, após o processo de intanciamento do 'EntityManager' 
		 * (e antes que ele seja fechado) para que ela passe a ser monitorada.
		 */

		// IDs gerados automaticamente pelo hibernate
		Persona p1 = new Persona(null, "Roberto Rongo", "roberto@rongo.com");
		Persona p2 = new Persona(null, "Erick Jaquim", "erick@jaquim.com");
		Persona p3 = new Persona(null, "Piru Litu", "piru@litu.com");
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		
		Persona p = em.find(Persona.class, 1);
		System.out.println(p1);
		
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		/**
		 * Com excessão de buscas, toda transação (seja inserir, atualizar ou 
		 * deletar) necessita que uma transação seja aberta antes do comando, 
		 * e fechado após o comando.
		 */
		
		em.close();
		emf.close();
		System.out.println("Pronto!");
	}
}
