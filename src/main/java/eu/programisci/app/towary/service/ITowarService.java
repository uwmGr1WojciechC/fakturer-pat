package eu.programisci.app.towary.service;

import eu.programisci.app.towary.dto.TowarDTO;

import java.util.List;

public interface ITowarService {

    TowarDTO findOne(Long id);

    List<TowarDTO> findAllForTable();

    void deleteOne(Long id);

    TowarDTO save(TowarDTO userDTO);

    List<TowarDTO> findByNazwaZawiera(String aNazwa);

    List<TowarDTO> findByOdNajnizszejCeny();
}
