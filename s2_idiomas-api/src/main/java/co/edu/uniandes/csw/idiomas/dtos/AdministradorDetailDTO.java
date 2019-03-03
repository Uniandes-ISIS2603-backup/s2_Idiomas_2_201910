/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AdministradorDetailDTO  extends AdministradorDTO implements Serializable{
    
    
    private List<GrupoDeInteresDTO> gruposAdministrados;
    
    public AdministradorDetailDTO()
    {
        
    }

    public List<GrupoDeInteresDTO> getGruposAdministrados() 
    {
        return gruposAdministrados;
    }

    public void setGruposAdministrados(List<GrupoDeInteresDTO> gruposAdministrados) 
    {
        this.gruposAdministrados = gruposAdministrados;
    }
    
    /**
     * Convierte un objeto AuthorDetailDTO a AuthorEntity incluyendo los
     * atributos de AuthorDTO.
     *
     * @return Nueva objeto AuthorEntity.
     *
     */
    @Override
    public AuthorEntity toEntity() {
        AuthorEntity authorEntity = super.toEntity();
        if (books != null) {
            List<BookEntity> booksEntity = new ArrayList<>();
            for (BookDTO dtoBook : books) {
                booksEntity.add(dtoBook.toEntity());
            }
            authorEntity.setBooks(booksEntity);
        }
        if (prizes != null) {
            List<PrizeEntity> prizesEntity = new ArrayList<>();
            for (PrizeDTO dtoPrize : prizes) {
                prizesEntity.add(dtoPrize.toEntity());
            }
            authorEntity.setPrizes(prizesEntity);
        }
        return authorEntity;
    }
}
