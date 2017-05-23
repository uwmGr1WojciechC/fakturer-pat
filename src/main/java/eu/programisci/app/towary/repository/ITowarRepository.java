package eu.programisci.app.towary.repository;


import eu.programisci.app.towary.ob.TowarOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITowarRepository extends JpaRepository<TowarOB,Long> {

    List<TowarOB> findByNazwaLikeIgnoreCaseOrderByCreationDate(String aNazwa);

    @Query("SELECT t FROM TowarOB t WHERE UPPER(t.nazwa) LIKE CONCAT('%',UPPER(:nazwa),'%') ORDER BY t.creationDate DESC")
    List<TowarOB> findByNazwaZawiera(@Param("nazwa") String aNazwa);


    @Query("SELECT t FROM TowarOB t ORDER BY t.cenaNetto ASC")
    List<TowarOB> findByOdNajnizszejCeny();
}
