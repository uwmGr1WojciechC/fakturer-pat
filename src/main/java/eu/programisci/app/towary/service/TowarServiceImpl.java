package eu.programisci.app.towary.service;

import eu.programisci.app.towary.converters.TowarConverter;
import eu.programisci.app.towary.dto.TowarDTO;
import eu.programisci.app.towary.ob.TowarOB;
import eu.programisci.app.towary.repository.ITowarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TowarServiceImpl implements ITowarService {

    @Autowired
    private ITowarRepository towarRepository;

    @Autowired
    private TowarConverter towarConverter;

    @Override
    public TowarDTO findOne(Long id) {
        TowarOB ob = towarRepository.findOne(id);
        TowarDTO dto = towarConverter.obToDto(ob);
        return dto;
    }

    @Override
    public List<TowarDTO> findByNazwaZawiera(String aNazwa){
        //List<TowarOB> pListOB = towarRepository.findByNazwaLikeIgnoreCase("%" + aNazwa + "%");
        List<TowarOB> pListOB = towarRepository.findByNazwaZawiera(aNazwa);

        return towarConverter.obListToDtoList(pListOB);
    }


    @Override
    public List<TowarDTO> findByOdNajnizszejCeny(){
        List<TowarOB> pListOB = towarRepository.findByOdNajnizszejCeny();

        return towarConverter.obListToDtoList(pListOB);
    }


    @Override
    public List<TowarDTO> findAllForTable() {
        List<TowarOB> obList = towarRepository.findAll();
        List<TowarDTO> dtoList = towarConverter.obListToDtoList(obList);
        return dtoList;
    }

    @Override
    public void deleteOne(Long id) {
        towarRepository.delete(id);
    }

    @Override
    public TowarDTO save(TowarDTO dto) {
        if (dto.getId() == null) {
            return towarConverter.obToDto(create(dto));
        } else {
            return towarConverter.obToDto(update(dto));
        }
    }

    private TowarOB create(TowarDTO dto) {
        TowarOB ob = towarConverter.dtoToOb(dto);
        ob.setCreationDate(new Date());
        ob = towarRepository.save(ob);
        return ob;
    }

    private TowarOB update(TowarDTO dto) {
        TowarOB ob = towarRepository.findOne(dto.getId());
        ob.setNazwa(dto.getNazwa());
        ob.setJednostkaMiary(dto.getJednostkaMiary());
        ob.setCenaNetto(dto.getCenaNetto());
        ob.setPkwiu(dto.getPkwiu());
        ob.setVat(dto.getVat());
        ob = towarRepository.save(ob);
        return ob;
    }
}
